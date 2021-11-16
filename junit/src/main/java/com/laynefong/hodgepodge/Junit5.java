package com.laynefong.hodgepodge;

import com.laynefong.hodgepodge.domain.SchneiderIhcDeviceVO;
import com.laynefong.hodgepodge.domain.basedo.DeviceVO;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

public class Junit5 {

    private final static String BLANK_SPLIT = " ";

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

            // 对设备名称进行升序排序
            devicesNameList.sort(String::compareTo);

            // 获取新的设备名称
            String newDeviceName = getNewDeviceName(ihcDevicePrefixName, devicesNameList);
            System.out.println(
                    "设备名称:" + ihcDeviceName + ",设备的前缀名称为:" + ihcDevicePrefixName + ",全部设备名称: " + allDeviceNameList + ",满足条件的其他设备名称为:" +
                            devicesNameList + ",新设备名称为:" + newDeviceName);

            DeviceVO vo = new DeviceVO();
            vo.setId("1");
            vo.setName(newDeviceName);
            vo.setCustomName(newDeviceName);
            allDeviceNameList.add(newDeviceName);
            allDeviceList.add(vo);
        }
    }


    /**
     * 初始化全部数据
     *
     * @return
     */
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

        DeviceVO v7 = new DeviceVO();
        v7.setId("7");
        v7.setName("rack 1 7");
        v7.setCustomName("rack 1 7");
        allDeviceList.add(v7);

        DeviceVO v8 = new DeviceVO();
        v8.setId("8");
        v8.setName("rack");
        v8.setCustomName("rack");
        allDeviceList.add(v8);

        DeviceVO v9 = new DeviceVO();
        v9.setId("9");
        v9.setName("rack GM");
        v9.setCustomName("rack GM");
        allDeviceList.add(v9);

        DeviceVO v10 = new DeviceVO();
        v10.setId("10");
        v10.setName("rack_3_1 1");
        v10.setCustomName("rack_3_1 1");
        allDeviceList.add(v10);

        DeviceVO v11 = new DeviceVO();
        v11.setId("11");
        v11.setName("rack_3 3");
        v11.setCustomName("rack_3 3");
        allDeviceList.add(v11);


        return allDeviceList;
    }

    /**
     * 初始化ihc待导入的子设备
     *
     * @return
     */
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

        SchneiderIhcDeviceVO v4 = new SchneiderIhcDeviceVO();
        v4.setId("4");
        v4.setName("rack GM");
        v4.setCustomName("rack GM");
        ihcDeviceList.add(v4);

        return ihcDeviceList;
    }

    /**
     * 获取子设备前缀名称
     *
     * @param ihcDeviceName
     * @return
     */
    private static String getIhcDevicePrefixName(String ihcDeviceName) {
        if (ihcDeviceName.contains(BLANK_SPLIT)) {
            String deviceLastName = ihcDeviceName.substring(ihcDeviceName.lastIndexOf(BLANK_SPLIT) + 1);
            if (isInteger(deviceLastName)) {
                return ihcDeviceName.substring(0, ihcDeviceName.lastIndexOf(BLANK_SPLIT));
            }
        }
        return ihcDeviceName;
    }

    /**
     * 获取符合规则的其他设备名称
     *
     * @param ihcDeviceName
     * @param allDeviceList
     * @return
     */
    private static List<String> getDevicesName(String ihcDeviceName, List<DeviceVO> allDeviceList) {
        Set<String> deviceNamesSet = new HashSet<>();
        for (DeviceVO deviceVO : allDeviceList) {
            String deviceName = StringUtils.isBlank(deviceVO.getCustomName()) ? deviceVO.getName() : deviceVO.getCustomName();
            if (StringUtils.isBlank(deviceName)) {
                continue;
            }

            if (deviceName.equals(ihcDeviceName)) {
                deviceNamesSet.add(deviceName);
                continue;
            }

            if (!deviceName.contains(BLANK_SPLIT)) {
                continue;
            }

            String devicePrefixName = deviceName.substring(0, deviceName.lastIndexOf(BLANK_SPLIT));
            if (!devicePrefixName.equals(ihcDeviceName)) {
                continue;
            }

            String deviceLastName = deviceName.substring(deviceName.lastIndexOf(BLANK_SPLIT) + 1);
            if (!isInteger(deviceLastName)) {
                continue;
            }
            deviceNamesSet.add(deviceName);
        }
        return new ArrayList<>(deviceNamesSet);
    }

    /**
     * 判断是否为整数
     *
     * @param value
     * @return
     */
    private static Boolean isInteger(String value) {
        try {
            Integer valueInt = Integer.parseInt(value);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 获取新的设备名称
     *
     * @param ihcDevicePrefixName
     * @param devicesNameList
     * @return
     */
    private static String getNewDeviceName(String ihcDevicePrefixName, List<String> devicesNameList) {
        // 判断前缀名称在当前设备名称群组里是否存在
        if (!devicesNameList.contains(ihcDevicePrefixName)) {
            return ihcDevicePrefixName;
        }
        List<Integer> sortNumberList = getSortNumberList(devicesNameList);
        return ihcDevicePrefixName + BLANK_SPLIT + getNewDeviceNumber(sortNumberList);
    }

    /**
     * 获取新的设备排序
     *
     * @param devicesNameList
     * @return
     */
    private static List<Integer> getSortNumberList(List<String> devicesNameList) {
        List<Integer> sortNumberList = new ArrayList<>();
        for (String deviceName : devicesNameList) {
            String deviceLastName = deviceName.substring(deviceName.lastIndexOf(BLANK_SPLIT) + 1);
            if (isInteger(deviceLastName)) {
                sortNumberList.add(Integer.parseInt(deviceLastName));
                continue;
            }
            sortNumberList.add(0);
        }
        // 对设备序号进行升序排序
        sortNumberList.sort(Integer::compareTo);
        return sortNumberList;
    }

    /**
     * 获取新的设备序号
     *
     * @param sortNumberList
     * @return
     */
    private static Integer getNewDeviceNumber(List<Integer> sortNumberList) {
        Hashtable<Integer, Integer> hasht = new Hashtable<>();
        for (Integer integer : sortNumberList) {
            hasht.put(integer, 1);
        }
        for (int i = 1; i <= sortNumberList.size(); i++) {
            if (hasht.get(i) == null) {
                return i;
            }
        }
        return 0;
    }
}
