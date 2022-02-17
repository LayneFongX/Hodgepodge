package com.laynefongx.hodgepodge.utils;

import com.alibaba.fastjson.JSON;
import com.laynefongx.hodgepodge.domain.languagedata.ExcelLanguageItem;
import com.laynefongx.hodgepodge.domain.languagedata.ExcelSheetPage;
import com.laynefongx.hodgepodge.domain.languagedata.SheetLineData;
import com.laynefongx.hodgepodge.domain.operate.FileResult;
import com.laynefongx.hodgepodge.enums.ErrorType;
import com.laynefongx.hodgepodge.exception.FlowException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.springframework.util.CollectionUtils;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author falcon
 * @since 2022/2/11
 */
@Slf4j
public class ExcelUtils {

    public static List<ExcelSheetPage> parseExcel(String path, byte[] data) {
        log.debug("parseExcel path={} ", path);
        Workbook workbook = getWorkbook(data);
        if (Objects.isNull(workbook)) {
            throw new FlowException(ErrorType.EXCEL_PARSE_ERROR);
        }

        List<ExcelSheetPage> excelSheetPages = new ArrayList<>();
        int size = workbook.getNumberOfSheets();
        for (int sheetNum = 0; sheetNum < size; sheetNum++) {
            ExcelSheetPage sheetPage = new ExcelSheetPage();
            Sheet sheet = workbook.getSheetAt(sheetNum);
            sheetPage.setSheetName(sheet.getSheetName());

            List<SheetLineData> dataList = handleSingleSheet(sheet, sheetPage);
            sheetPage.setSheetLineDataList(dataList);
            excelSheetPages.add(sheetPage);
        }


        try {
            workbook.close();
        } catch (IOException e) {
            log.error("close workbook error", e);
        }
        log.debug("parse excel sheet={}", excelSheetPages);
        return excelSheetPages;
    }

    private static List<SheetLineData> handleSingleSheet(Sheet sheet, ExcelSheetPage sheetPage) {
        List<SheetLineData> dataList = new ArrayList<>();
        Map<Integer, String> titleMap = new HashMap<>();
        for (int rowNum = 0; rowNum <= sheet.getLastRowNum(); rowNum++) {
            SheetLineData sheetLineData = handleLanguage(sheet, rowNum, titleMap);

            if (rowNum != 0) {
                dataList.add(sheetLineData);
            }
        }
        sheetPage.setTitleMap(titleMap);
        return dataList;
    }

    private static SheetLineData handleLanguage(Sheet sheet, int rowNum, Map<Integer, String> titleMap) {
        SheetLineData sheetLineData = new SheetLineData();
        List<ExcelLanguageItem> languageItems = new ArrayList<>();

        Row row = sheet.getRow(rowNum);
        Iterator<Cell> cellIterator = row.cellIterator();
        while (cellIterator.hasNext()) {
            Cell cell = cellIterator.next();
            String value = getCellStringVal(cell);
            int index = cell.getColumnIndex();
            //第一行是title，放到Map中去
            if (rowNum == 0) {
                titleMap.put(index, value);
                continue;
            }

            //每行第一列是词条Id
            if (index == 0) {
                sheetLineData.setKey(value);
                continue;
            }

            ExcelLanguageItem languageItem = new ExcelLanguageItem();
            languageItem.setLang(titleMap.get(index));
            languageItem.setValue(value);
            languageItems.add(languageItem);
        }

        sheetLineData.setLanguageItems(languageItems);
        return sheetLineData;
    }

    private static Workbook getWorkbook(byte[] data) {
        try (InputStream inputStream = new ByteArrayInputStream(data)) {
            return WorkbookFactory.create(inputStream);
        } catch (Exception e) {
            log.error("get excel workbook error", e);
        }
        return null;
    }

    private static String getCellStringVal(Cell cell) {
        CellType cellType = cell.getCellTypeEnum();
        switch (cellType) {
            case NUMERIC:
            case STRING:
                return cell.getStringCellValue();
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            case BLANK:
                return "";
            case ERROR:
                return String.valueOf(cell.getErrorCellValue());
            default:
                return StringUtils.EMPTY;
        }
    }

    public static byte[] writeExcel2Zip(List<FileResult> resultItems) {
        try {
            Map<String, byte[]> dataMap = transformData2ExcelBytes(resultItems);

            try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                 ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream)) {
                dataMap.keySet().forEach(key -> {
                    byte[] bytes = dataMap.get(key);
                    write2Zip(key, bytes, zipOutputStream);
                });

                zipOutputStream.finish();
                return outputStream.toByteArray();
            }
        } catch (Exception e) {
            log.error("write excel error", e);
            throw new FlowException(ErrorType.EXCEL_WRITE_ERROR);
        }
    }

    private static Map<String, byte[]> transformData2ExcelBytes(List<FileResult> resultItems) throws IOException {
        Map<String, byte[]> dataMap = new HashMap<>();
        List<FileResult> errorResults = new ArrayList<>();
        for (FileResult fileResult : resultItems) {
            byte[] excelBytes = excel2bytes(fileResult, true);
            String path = fileResult.getPath();
            dataMap.put(path, excelBytes);

            List<ExcelSheetPage> errorSheetPages = getErrorSheetPages(fileResult.getResultItems());
            if (!CollectionUtils.isEmpty(errorSheetPages)) {
                FileResult errorResult = new FileResult();
                String errorPath = getErrorPath(path);
                errorResult.setPath(errorPath);
                errorResult.setResultItems(errorSheetPages);
                errorResults.add(errorResult);
            }
        }

        for (FileResult errorResult : errorResults) {
            byte[] excelBytes = excel2bytes(errorResult, false);
            String path = errorResult.getPath();
            dataMap.put(path, excelBytes);
        }

        return dataMap;
    }

    private static String getErrorPath(String path) {
        String[] tmp = path.split("\\.");
        String prefix = tmp[tmp.length - 1];
        return path.replace("." + prefix, "-error." + prefix);
    }

    private static byte[] excel2bytes(FileResult fileResult, boolean isSetColor) throws IOException {
        Workbook workbook = null;
        List<ExcelSheetPage> sheetPages = fileResult.getResultItems();
        sheetPages.forEach(sheetPage -> writeSheetPage(workbook, sheetPage, isSetColor));

        byte[] excelBytes = null;
        try (ByteArrayOutputStream up = new ByteArrayOutputStream()) {
            workbook.write(up);
            excelBytes = up.toByteArray();
        } catch (IOException e) {
            log.error("write excel file error");
        } finally {
            workbook.close();
        }
        return excelBytes;
    }

    private static List<ExcelSheetPage> getErrorSheetPages(List<ExcelSheetPage> sheetPages) {
        return sheetPages.stream().map(page -> {
                    List<SheetLineData> errorList = page.getSheetLineDataList().stream()
                            .filter(line -> Objects.equals(line.getLineColor().getIndex(), IndexedColors.RED.getIndex()))
                            .collect(Collectors.toList());
                    ExcelSheetPage errorSheetPage = new ExcelSheetPage();
                    errorSheetPage.setSheetName(page.getSheetName());
                    errorSheetPage.setTitleMap(page.getTitleMap());
                    errorSheetPage.setSheetLineDataList(errorList);
                    return errorSheetPage;
                }).filter(sheetPage -> !CollectionUtils.isEmpty(sheetPage.getSheetLineDataList()))
                .collect(Collectors.toList());
    }

    public static void write2Zip(String path, byte[] data, ZipOutputStream zipOutputStream) {
        try {
            ZipEntry zipEntry = new ZipEntry(path);
            zipOutputStream.putNextEntry(zipEntry);
            int len;
            try (InputStream in = new ByteArrayInputStream(data)) {
                byte[] byteArray = new byte[1024];
                while ((len = in.read(byteArray)) != -1) {
                    zipOutputStream.write(byteArray, 0, len);
                }
            }
            zipOutputStream.flush();
            zipOutputStream.closeEntry();
        } catch (Exception e) {
            log.error("ex", e);
        }
    }

    private static void writeSheetPage(Workbook workbook, ExcelSheetPage sheetPage, boolean isSetColor) {
        Sheet sheet = workbook.createSheet(sheetPage.getSheetName());
        Map<Integer, String> titleMap = sheetPage.getTitleMap();

        Row titleRow = sheet.createRow(0);
        titleMap.keySet().stream().sorted()
                .forEach(index -> titleRow.createCell(index).setCellValue(titleMap.get(index)));

        int rowIndex = sheetPage.getSheetLineDataList().size();
        for (int i = 0; i < rowIndex; i++) {
            Row row = sheet.createRow(i + 1);


            //第一列先写Key
            SheetLineData sheetLineData = sheetPage.getSheetLineDataList().get(i);
            Cell firstCell = row.createCell(0);
            firstCell.setCellValue(sheetLineData.getKey());

            if (!Objects.equals(sheetLineData.getLineColor().getIndex(), IndexedColors.WHITE.getIndex())
                    && isSetColor) {
                CellStyle firstStyle = workbook.createCellStyle();
                firstStyle.setFillForegroundColor(sheetLineData.getLineColor().getIndex());
                firstStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                firstCell.setCellStyle(firstStyle);
            }


            int columnIndex = 1;
            for (ExcelLanguageItem item : sheetLineData.getLanguageItems()) {
                Cell cell = row.createCell(columnIndex);
                cell.setCellValue(item.getValue());

                //设置单元格的颜色
                if (!Objects.equals(sheetLineData.getLineColor().getIndex(), IndexedColors.WHITE.getIndex())
                        && isSetColor) {
                    CellStyle cellStyle = workbook.createCellStyle();
                    short color = getCellColor(sheetLineData, item);
                    cellStyle.setFillForegroundColor(color);
                    cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                    cell.setCellStyle(cellStyle);
                }
                columnIndex++;
            }
        }
    }

    private static short getCellColor(SheetLineData sheetLineData, ExcelLanguageItem item) {
        short color = sheetLineData.getLineColor().getIndex();
        short cellColor = item.getCellColor().getIndex();
        if (!Objects.equals(cellColor, color) && !Objects.equals(cellColor, IndexedColors.WHITE.getIndex())) {
            color = item.getCellColor().getIndex();
        }
        return color;
    }

    public static void main(String[] args) throws IOException {
//        File file = new File("/Users/falcon/Desktop/For Anna/All/Device Related/IP Outdoor Camera/export_i18n_nhes5al5_1642993738270.xlsx");
//        byte[] data = new FileInputStream(file).readAllBytes();
//        List<ExcelSheetPage> sheetData = parseExcel("ALL/Device Related/IP Outdoor Camera/export_i18n_nhes5al5_1642993738270.xlsx", data);
//        System.out.println(JSON.toJSONString(sheetData));

        SheetLineData sheetLineData = new SheetLineData();
        sheetLineData.setLineColor(IndexedColors.RED);
        System.out.println(JSON.toJSONString(sheetLineData));

        SheetLineData test = JSON.parseObject(JSON.toJSONString(sheetLineData), SheetLineData.class);

        String data =
                "{\"path\":\"All/Device Relate/export_i18n_r6ujohuyq1k4hf2h_1629863333425.xls\",\"resultItems\":[{\"sheetName\":\"Data Point\",\"titleMap\":{\"0\":\"id\",\"1\":\"ENGLISH\",\"2\":\"JAPANESE\",\"3\":\"CHINESE\",\"4\":\"FRENCH\"},\"sheetLineDataList\":[{\"key\":\"dp_1\",\"lineColor\":\"RED\",\"languageItems\":[{\"lang\":\"ENGLISH\",\"value\":\"dp_1英文\",\"cellColor\":\"WHITE\"},{\"lang\":\"JAPANESE\",\"value\":\"dp_1日语\",\"cellColor\":\"WHITE\"},{\"lang\":\"CHINESE\",\"value\":\"dp_1中文\",\"cellColor\":\"WHITE\"},{\"lang\":\"FRENCH\",\"value\":\"dp_1法语\",\"cellColor\":\"WHITE\"}]},{\"key\":\"dp_2\",\"lineColor\":\"WHITE\",\"languageItems\":[{\"lang\":\"ENGLISH\",\"value\":\"dp_2_rule_1英文\",\"cellColor\":\"GREEN\"},{\"lang\":\"JAPANESE\",\"value\":\"dp_2日语\",\"cellColor\":\"WHITE\"},{\"lang\":\"CHINESE\",\"value\":\"dp_2中文\",\"cellColor\":\"WHITE\"},{\"lang\":\"FRENCH\",\"value\":\"dp_2法语\",\"cellColor\":\"WHITE\"}]},{\"key\":\"dp_3\",\"lineColor\":\"WHITE\",\"languageItems\":[{\"lang\":\"ENGLISH\",\"value\":\"dp_3英文\",\"cellColor\":\"WHITE\"},{\"lang\":\"JAPANESE\",\"value\":\"\",\"cellColor\":\"GREEN\"},{\"lang\":\"CHINESE\",\"value\":\"dp_3中文\",\"cellColor\":\"WHITE\"},{\"lang\":\"FRENCH\",\"value\":\"dp_3法语\",\"cellColor\":\"WHITE\"}]},{\"key\":\"dp_4\",\"lineColor\":\"RED\",\"languageItems\":[{\"lang\":\"ENGLISH\",\"value\":\"dp_4英文\",\"cellColor\":\"WHITE\"},{\"lang\":\"JAPANESE\",\"value\":\"dp_4日语\",\"cellColor\":\"WHITE\"},{\"lang\":\"CHINESE\",\"value\":\"\",\"cellColor\":\"GREEN\"},{\"lang\":\"FRENCH\",\"value\":\"dp_4法语\",\"cellColor\":\"WHITE\"}]},{\"key\":\"dp_5\",\"lineColor\":\"RED\",\"languageItems\":[{\"lang\":\"ENGLISH\",\"value\":\"\",\"cellColor\":\"WHITE\"},{\"lang\":\"JAPANESE\",\"value\":\"dp_5日语\",\"cellColor\":\"WHITE\"},{\"lang\":\"CHINESE\",\"value\":\"dp_5中文\",\"cellColor\":\"WHITE\"},{\"lang\":\"FRENCH\",\"value\":\"dp_5法语\",\"cellColor\":\"WHITE\"}]}]}]}";
        FileResult fileResult = JSON.parseObject(data, FileResult.class);
        byte[] bytes = writeExcel2Zip(Collections.singletonList(fileResult));

        try (FileOutputStream fileOutputStream = new FileOutputStream("/Users/falcon/Desktop/For Anna/test.zip")) {
            fileOutputStream.write(bytes);
            fileOutputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
