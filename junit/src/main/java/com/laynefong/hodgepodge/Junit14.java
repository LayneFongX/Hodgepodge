package com.laynefong.hodgepodge;

import com.laynefong.hodgepodge.domain.SchneiderBridgeDeviceVO;
import com.laynefong.hodgepodge.domain.basedo.DeviceBO;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author Banchao
 * @Date 2022/3/21 14:14
 */
public class Junit14 {

    public static void main(String[] args) {
        List<DeviceBO> allDeviceList = new ArrayList<>();
        allDeviceList.add(new DeviceBO("bridge bridge 4"));
        allDeviceList.add(new DeviceBO("bridge bridge"));
        allDeviceList.add(new DeviceBO("bridge bridge 2"));

        List<String> deviceNames = getDeviceNames(allDeviceList);


        List<SchneiderBridgeDeviceVO> bridgeSubDevicesList = new ArrayList<>();
        // bridgeSubDevicesList.add(new SchneiderBridgeDeviceVO("bridge bridge 1"));
        // bridgeSubDevicesList.add(new SchneiderBridgeDeviceVO("bridge bridge_2"));
        // bridgeSubDevicesList.add(new SchneiderBridgeDeviceVO("bridge bridge3"));
        // bridgeSubDevicesList.add(new SchneiderBridgeDeviceVO("bridge bridge"));
        bridgeSubDevicesList.add(new SchneiderBridgeDeviceVO("bridge bridge2"));


        repeatDeviceNameHandle(allDeviceList, deviceNames, bridgeSubDevicesList);
        for (DeviceBO deviceBO : allDeviceList) {
            System.out.println(deviceBO.getCustomName());
        }

        System.out.println("====================================");

        for (SchneiderBridgeDeviceVO bridgeDeviceVO : bridgeSubDevicesList) {
            System.out.println(bridgeDeviceVO.getDeviceName());
        }
    }

    private static List<String> getDeviceNames(List<DeviceBO> allDeviceList) {
        return allDeviceList.stream().map(x -> {
            String customName = x.getCustomName();
            if (StringUtils.isBlank(customName)) {
                return x.getName();
            }
            return customName;
        }).collect(Collectors.toList());
    }

    /**
     * 设备重名处理
     *
     * @param allDeviceList
     * @param bridgeSubDevicesList
     */
    public static void repeatDeviceNameHandle(List<DeviceBO> allDeviceList, List<String> allDeviceNameList,
                                              List<SchneiderBridgeDeviceVO> bridgeSubDevicesList) {
        // 如果设备名称命名规则 设备名称+空格+序号 以该名称进行重名处理
        for (SchneiderBridgeDeviceVO bridgeDeviceVO : bridgeSubDevicesList) {
            String bridgeDeviceName =
                    StringUtils.isEmpty(bridgeDeviceVO.getDeviceName()) ? bridgeDeviceVO.getName() : bridgeDeviceVO.getDeviceName();
            if (!allDeviceNameList.contains(bridgeDeviceName)) {
                changeAllDeviceList(bridgeDeviceName, allDeviceList);
                continue;
            }

            // 获取bridge子设备前缀名称
            String bridgeDevicePrefixName = getBridgeDevicePrefixName(bridgeDeviceName);
            // 获取匹配重名的其他设备名称
            List<String> devicesNameList = getDevicesName(bridgeDevicePrefixName, allDeviceList);
            // 对设备名称进行升序排序
            devicesNameList.sort(String::compareTo);

            String newDeviceName = getNewDeviceName(bridgeDevicePrefixName, devicesNameList);
            // 获取新的设备名称
            bridgeDeviceVO.setDeviceName(newDeviceName);
            changeAllDeviceList(newDeviceName, allDeviceList);
        }
    }

    /**
     * 设备重名处理
     *
     * @param allDeviceList
     * @param bridgeSubDevicesList
     */
    public static void repeatDeviceNameHandle(List<DeviceBO> allDeviceList, List<SchneiderBridgeDeviceVO> bridgeSubDevicesList) {
        // 如果设备名称命名规则 设备名称+空格+序号 以该名称进行重名处理
        for (SchneiderBridgeDeviceVO bridgeDeviceVO : bridgeSubDevicesList) {
            String bridgeDeviceName =
                    StringUtils.isEmpty(bridgeDeviceVO.getDeviceName()) ? bridgeDeviceVO.getName() : bridgeDeviceVO.getDeviceName();

            // 获取bridge子设备前缀名称
            String bridgeDevicePrefixName = getBridgeDevicePrefixName(bridgeDeviceName);
            // 获取匹配重名的其他设备名称
            List<String> devicesNameList = getDevicesName(bridgeDevicePrefixName, allDeviceList);
            // 对设备名称进行升序排序
            devicesNameList.sort(String::compareTo);

            String newDeviceName = getNewDeviceName(bridgeDevicePrefixName, devicesNameList);
            // 获取新的设备名称
            bridgeDeviceVO.setDeviceName(newDeviceName);
            changeAllDeviceList(newDeviceName, allDeviceList);
        }
    }

    /**
     * 获取子设备前缀名称
     *
     * @param bridgeDeviceName
     * @return
     */
    private static String getBridgeDevicePrefixName(String bridgeDeviceName) {
        if (bridgeDeviceName.contains(" ")) {
            String deviceLastName = bridgeDeviceName.substring(bridgeDeviceName.lastIndexOf(" ") + 1);
            if (isInteger(deviceLastName)) {
                return bridgeDeviceName.substring(0, bridgeDeviceName.lastIndexOf(" "));
            }
        }
        return bridgeDeviceName;
    }

    /**
     * 获取符合规则的其他设备名称
     *
     * @param bridgeDeviceName
     * @param allDeviceList
     * @return
     */
    private static List<String> getDevicesName(String bridgeDeviceName, List<DeviceBO> allDeviceList) {
        Set<String> deviceNamesSet = new HashSet<>();
        for (DeviceBO deviceVO : allDeviceList) {
            String deviceName = StringUtils.isBlank(deviceVO.getCustomName()) ? deviceVO.getName() : deviceVO.getCustomName();
            if (StringUtils.isBlank(deviceName)) {
                continue;
            }

            if (deviceName.equals(bridgeDeviceName)) {
                deviceNamesSet.add(deviceName);
                continue;
            }

            if (!deviceName.contains(" ")) {
                continue;
            }

            String devicePrefixName = deviceName.substring(0, deviceName.lastIndexOf(" "));
            if (!devicePrefixName.equals(bridgeDeviceName)) {
                continue;
            }

            String deviceLastName = deviceName.substring(deviceName.lastIndexOf(" ") + 1);
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
     * 修改设备名称
     *
     * @param newDeviceName
     * @param allDeviceList
     */
    private static void changeAllDeviceList(String newDeviceName, List<DeviceBO> allDeviceList) {
        DeviceBO tempBO = new DeviceBO();
        tempBO.setName(newDeviceName);
        tempBO.setCustomName(newDeviceName);
        allDeviceList.add(tempBO);
    }

    /**
     * 获取新的设备名称
     *
     * @param bridgeDevicePrefixName
     * @param devicesNameList
     * @return
     */
    private static String getNewDeviceName(String bridgeDevicePrefixName, List<String> devicesNameList) {
        // 判断前缀名称在当前设备名称群组里是否存在
        if (!devicesNameList.contains(bridgeDevicePrefixName)) {
            return bridgeDevicePrefixName;
        }
        List<Integer> sortNumberList = getSortNumberList(devicesNameList);
        return bridgeDevicePrefixName + " " + getNewDeviceNumber(sortNumberList);
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
            String deviceLastName = deviceName.substring(deviceName.lastIndexOf(" ") + 1);
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
