package com.laynefong.hodgepodge;

import com.laynefong.hodgepodge.domain.SchneiderIhcDeviceVO;
import com.laynefong.hodgepodge.domain.basedo.DeviceVO;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Junit5 {

    private final static String BLANK = " ";

    public static void main(String[] args) {
        List<DeviceVO> allDeviceList = initAllDevicesData();
        List<String> allDeviceNameList = allDeviceList.stream().map(DeviceVO::getCustomName).collect(Collectors.toList());
        List<SchneiderIhcDeviceVO> ihcSubDevicesList = initIhcDevicesData();
        // 获取前缀 标准的命名规范是前缀+空格+序号 且序号没有前0
        for (SchneiderIhcDeviceVO ihcDeviceVO : ihcSubDevicesList) {
            String ihcDeviceName = StringUtils.isEmpty(ihcDeviceVO.getDeviceName()) ? ihcDeviceVO.getName() : ihcDeviceVO.getDeviceName();
            // 获取ihc子设备前缀名称
            String ihcDevicePrefixName = getIhcDevicePrefixName(ihcDeviceName);
            // 获取重名的其他设备名称
            List<String> devicesNameList = getDevicesName(ihcDevicePrefixName, allDeviceList);
            sortDescDeviceNameList(devicesNameList);
            System.out.println("设备名称:" + ihcDeviceName + ",全部设备名称: " + allDeviceNameList + ",满足条件的其他设备名称为:" + devicesNameList);
        }
    }

    private static String getIhcDevicePrefixName(String ihcDeviceName) {
        if (ihcDeviceName.contains(BLANK)) {  // 以rack _1和 rack 1为例
            // 获取后缀
            String deviceLastName = ihcDeviceName.substring(ihcDeviceName.lastIndexOf(BLANK) + 1); // _1和1
            // 如果后缀是数字
            if (isInteger(deviceLastName)) { // 1
                return ihcDeviceName.substring(0, ihcDeviceName.lastIndexOf(BLANK)); // 前缀为rack
            } else { // _1
                return ihcDeviceName; // 前缀为rack _1
            }
        } else { // 以rack_1为例
            return ihcDeviceName; // 前缀为rack_1

        }
    }

    private static List<String> getDevicesName(String ihcDeviceName, List<DeviceVO> allDeviceList) {
        List<String> deviceNamesList = new ArrayList<>();
        for (DeviceVO deviceVO : allDeviceList) {
            String deviceName = StringUtils.isBlank(deviceVO.getCustomName()) ? deviceVO.getName() : deviceVO.getCustomName();
            if (deviceName.contains(BLANK)) {
                String devicePrefixName = deviceName.substring(0, deviceName.lastIndexOf(BLANK));
                if (devicePrefixName.equals(ihcDeviceName)) {
                    deviceNamesList.add(deviceName);
                }
            }
        }
        return deviceNamesList;
    }

    private static void sortDescDeviceNameList(List<String> devicesNameList) {
        devicesNameList.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
    }

    private static Boolean isInteger(String value) {
        try {
            Integer valueInt = Integer.parseInt(value);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static List<SchneiderIhcDeviceVO> initIhcDevicesData() {
        List<SchneiderIhcDeviceVO> ihcDeviceList = new ArrayList<>();
        SchneiderIhcDeviceVO v1 = new SchneiderIhcDeviceVO();
        v1.setId("1");
        v1.setName("rack 1");
        v1.setCustomName("rack 1");
        ihcDeviceList.add(v1);

        SchneiderIhcDeviceVO v2 = new SchneiderIhcDeviceVO();
        v2.setId("2");
        v2.setName("rack");
        v2.setCustomName("rack");
        ihcDeviceList.add(v2);

        SchneiderIhcDeviceVO v3 = new SchneiderIhcDeviceVO();
        v3.setId("3");
        v3.setName("rack_3");
        v3.setCustomName("rack_3");
        ihcDeviceList.add(v3);

        return ihcDeviceList;
    }

    private static List<DeviceVO> initAllDevicesData() {
        List<DeviceVO> allDeviceList = new ArrayList<>();
        DeviceVO v1 = new DeviceVO();
        v1.setId("1");
        v1.setName("rack 1");
        v1.setCustomName("rack 1");
        allDeviceList.add(v1);

        DeviceVO v2 = new DeviceVO();
        v2.setId("2");
        v2.setName("rack 3");
        v2.setCustomName("rack 3");
        allDeviceList.add(v2);

        DeviceVO v3 = new DeviceVO();
        v3.setId("3");
        v3.setName("rack_3");
        v3.setCustomName("rack_3");
        allDeviceList.add(v3);

        DeviceVO v4 = new DeviceVO();
        v4.setId("4");
        v4.setName("1G Dimmar 4");
        v4.setCustomName("1G Dimmar 4");
        allDeviceList.add(v4);

        DeviceVO v5 = new DeviceVO();
        v5.setId("5");
        v5.setName("rack Dimmar 5");
        v5.setCustomName("rack Dimmar 5");
        allDeviceList.add(v5);

        DeviceVO v6 = new DeviceVO();
        v6.setId("6");
        v6.setName("rack 1-6");
        v6.setCustomName("rack 1-6");
        allDeviceList.add(v6);

        return allDeviceList;
    }
}
