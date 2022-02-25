package com.laynefong.hodgepodge;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Junit8 {

    public static void main(String[] args) {
        String res = "[{\"deviceId\":\"123\",\"pid\":\"456\"},{\"deviceId\":\"789\",\"pid\":\"102\"}]";
        JSONArray resIdJsonArray = JSON.parseArray(res);
        List<Map<String, String>> resIdList = parseJson2T(resIdJsonArray, List.class);
        boolean resIdListIsList = resIdList instanceof List;
        System.out.println("resIdList is list :" + resIdListIsList);

        System.out.println(resIdList.size());

        Map<String, String> map1 = new HashMap<>();
        map1.put("deviceId", "243");
        map1.put("pid", "984");
        resIdList.add(map1);
        System.out.println(resIdList);

        List<String> list1 = Arrays.asList("1", "2", "3");
        boolean list1ListIsList = list1 instanceof List;
        System.out.println("list1 is list :" + list1ListIsList);
    }

    private static <T> T parseJson2T(Object res, Class<T>... tClasses) {
        Type[] argumentsTypes = new Type[tClasses.length];
        for (int i = 0; i < tClasses.length; i++) {
            argumentsTypes[i] = getSuperClassGenricType(tClasses[i]);
        }
        TypeReference<T> typeReference = new TypeReference<>(argumentsTypes) {
        };
        return JSONObject.parseObject(JSONObject.toJSONString(res), typeReference);
    }

    /**
     * 通过反射,获得定义Class时声明的父类的范型参数的类型.
     * 如public BookManager extends GenricManager<Book>
     *
     * @param tClass The class to introspect
     * @return the first generic declaration, or <code>Object.class</code> if cannot be determined
     */
    public static <T> Type getSuperClassGenricType(Class<T> tClass) {
        return getSuperClassGenricType(tClass, 0);
    }

    /**
     * 通过反射,获得定义Class时声明的父类的范型参数的类型.
     * 如public BookManager extends GenricManager<Book>
     *
     * @param clazz clazz The class to introspect
     * @param index the Index of the generic ddeclaration,start from 0.
     */
    public static Class getSuperClassGenricType(Class clazz, int index) throws IndexOutOfBoundsException {

        Type genType = clazz.getGenericSuperclass();

        if (!(genType instanceof ParameterizedType)) {
            return Object.class;
        }

        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

        if (index >= params.length || index < 0) {
            return Object.class;
        }
        if (!(params[index] instanceof Class)) {
            return Object.class;
        }
        return (Class) params[index];
    }
}
