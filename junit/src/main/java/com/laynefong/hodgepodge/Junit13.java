package com.laynefong.hodgepodge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

/**
 * @Author Banchao
 * @Date 2022/3/21 13:41
 */
public class Junit13 {

    private final static String SCHNEIDER_GROUP_NAME_PREFIX = "Radiator Thermostats ";

    public static void main(String[] args) {
        List<String> groupsNameList =
                Arrays.asList("Radiator Thermostats 7", "Radiator Thermostats 1", "Radiator Thermostats 5", "Radiator Thermostats 3");
        // List<String> groupsNameList = new ArrayList<>();
        List<Integer> standardGroupsNameOrderList = new ArrayList<>();
        for (String groupName : groupsNameList) {
            String[] groupNameSplit = groupName.split(SCHNEIDER_GROUP_NAME_PREFIX);
            if (groupNameSplit.length != 2) {
                continue;
            }
            String groupNameSuffix = groupNameSplit[1];
            standardGroupsNameOrderList.add(Integer.parseInt(groupNameSuffix));
        }
        // 对设备序号进行升序排序
        standardGroupsNameOrderList.sort(Integer::compareTo);
        System.out.println("新群组名称:" + SCHNEIDER_GROUP_NAME_PREFIX + getNewGroupNumber(standardGroupsNameOrderList));
    }

    /**
     * 获取新的设备序号
     *
     * @param sortNumberList
     * @return
     */
    public static Integer getNewGroupNumber(List<Integer> sortNumberList) {
        Hashtable<Integer, Integer> hasht = new Hashtable<>();
        for (Integer integer : sortNumberList) {
            hasht.put(integer, 1);
        }
        for (int i = 1; i <= sortNumberList.size() + 1; i++) {
            if (hasht.get(i) == null) {
                return i;
            }
        }
        return 1;
    }

}
