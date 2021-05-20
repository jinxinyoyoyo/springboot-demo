package com.jjkj.core.util;

import sun.misc.BASE64Encoder;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 工具类
 */
public class Util {

    public static String State_1 = "1";//待审批
    public static String State_2 = "2";//审批驳回
    public static String State_3 = "3";//待销假
    public static String State_4 = "4";//待核销
    public static String State_5 = "5";//销假驳回
    public static String State_6 = "6";//已核销
    public static String State_7 = "7";//已撤销

    //获取随即数主键
    public static String getPK(){
        SimpleDateFormat sdf =   new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String date = sdf.format(new Date());//当前时间
        String num = String.valueOf((int)(Math.random() * 900) + 100);//3位随即数
        return date.concat(num);
    }

    //拼接get请求参数
    public static String prepareParam(Map<String,Object> paramMap){
        StringBuffer sb = new  StringBuffer();
        if (paramMap.isEmpty()){
            return   ""  ;
        }else {
            for (String key: paramMap.keySet()){
                Object value = paramMap.get(key);
                String valueStr = value==null?"":value.toString();
                if (sb.length()< 1 ){
                    sb.append(key).append("=" ).append(valueStr);
                }else {
                    sb.append("&" ).append(key).append( "=" ).append(valueStr);
                }
            }
            return  sb.toString();
        }
    }
    /**
     * 将文件转成base64 字符串
     * @param path
     * @return  *
     * @throws Exception
     */

    public static String encodeBase64File(String path) {

        try {
            File file = new File(path);
            FileInputStream inputFile = new FileInputStream(file);
            byte[] buffer = new byte[(int) file.length()];
            inputFile.read(buffer);
            inputFile.close();
            return new BASE64Encoder().encode(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
       return null;
    }

    /**
     * 图片转化成byte[]数组
     */
    public static byte[] encodeBase64FileToByte(String path) {

        try {
            File file = new File(path);
            FileInputStream inputFile = new FileInputStream(file);
            byte[] buffer = new byte[(int) file.length()];
            inputFile.read(buffer);
            inputFile.close();
            return buffer ;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //转换部门主键集合字符串
    public static String getDepartIDs(List<Map<String, Object>> list_manage){
        String  DepartIDs = "";//我管理的部门集合字符串

        int i = 0;
        for(Map<String, Object> depart : list_manage){
            String DepartID = depart.get("DepartID").toString();//你们主键
            if(i == list_manage.size() - 1){//最后一次遍历
                DepartIDs = DepartIDs.concat("'").concat(DepartID).concat("'");
            }else{
                DepartIDs = DepartIDs.concat("'").concat(DepartID).concat("',");
            }
            i ++;
        }
        return DepartIDs;
    }

    //判断部门是否可以选择
    public static void checkPermissionSelect(List<Map<String, Object>> list, List<Map<String, Object>> list_manage, String name, String DepartID){
        if(list != null && list.size() >0){//集合为不空
            if(list.get(0) != null){
                for(Map<String, Object> m : list){//遍历下级部门
                    String Depart_next = m.get(DepartID).toString();
                    m.put(name, "1");
                    for(Map<String, Object> depart : list_manage){
                        if(depart.get("DepartID").toString().contains(Depart_next)){//如果管理部门存在跟目录以外的
                            m.put(name, "0");
                            break;
                        }
                    }
                }
            }
        }
    }

    public static void getSubDepart(String departID, List<Map<String, Object>> departList, List<Map<String, Object>> subDepartList){
        for(Map<String, Object> depart : departList){//遍历全部部门
            String PID = depart.get("PID").toString();//上级部门主键
            if(departID.equals(PID)){//如果上级部门主键等于传递部门主键
                subDepartList.add(depart);//封装集合
                getSubDepart(depart.get("departID").toString(), departList, subDepartList);//递归调用
            }
        }
    }

    public static List<Map<String, Object>>  initAllDepart(String departIDs, List<Map<String, Object>> departList){
        List<Map<String, Object>> allList = new ArrayList<>();//全部部门集合

        String[] departID_ = departIDs.split(",");//部门主键数组

        for(int i = 0; i < departID_.length; i ++){//遍历部门主键数组

            String departID = departID_[i];//部门主键

            for(Map<String, Object> depart : departList){//遍历全部部门
                if(departID.equals(depart.get("departID").toString())){//部门主键相等
                    boolean f = false;//判断集合中是否存在
                    for(Map<String, Object> map : allList){//遍历返回集合
                        if(departID.equals(map.get("departID").toString())){//部门在集合中存在
                            f = true;
                            break;
                        }
                    }
                    if(!f){
                        allList.add(depart);//当前部门封装入集合
                    }
                }
            }

            List<Map<String, Object>> subDepartList = new ArrayList<>();//下级部门集合

            Util.getSubDepart(departID, departList, subDepartList);//获取下级部门集合

            for(Map<String, Object> subMap : subDepartList){//遍历下级部门
                boolean f = false;//判断集合中是否存在
                for(Map<String, Object> map : allList){//遍历返回集合
                    if(subMap.get("departID").toString().equals(map.get("departID").toString())){//下级部门在集合中存在
                        f = true;
                        break;
                    }
                }
                if(!f){
                    allList.add(subMap);//下级部门封装入集合
                }
            }
        }

        return allList;
    }
}
