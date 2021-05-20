package com.jjkj.core.util;

import com.jjkj.aqxj.image.service.impl.ImageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * 文件路径相关
 * Created by list on 2017/9/11.
 */
@Component
public class FileUtils {

    @Autowired
    ImageServiceImpl   imageServiceImpl;
//    /**
//     * 注入配置
//     */
//    @Value("${config_files_address}")    //图片上传服务器地址
//    private  String  PATH_CONFIG_FILES_ADDRESS;
//    @Value("${config_files_address_url}")
//    private  String PATH_CONFIG_FILES_ADDRESS_IP;//返回的图片服务器地址
//
//    @Value("${config_files_address_file}")    //附件上传服务器地址
//    private  String  PATH_CONFIG_FILES_ADDRESS_FILE;
//    @Value("${config_files_address_url_file}")
//    private  String PATH_CONFIG_FILES_ADDRESS_IP_FILE;//返回的附件服务器地址
//
//    @Value("${config_files_address_file_QR}")    //二维码上传服务器地址
//    private  String  PATH_CONFIG_FILES_ADDRESS_FILE_QR;
//    @Value("${config_files_address_url_file_QR}")
//    private  String PATH_CONFIG_FILES_ADDRESS_IP_FILE_QR;//返回的二维码服务器地址

    /**
     * 获取图片保存路径
     * @param fileType
     * @param
     * @return
     */
    public  String getFilePath(String fileType,String id,String business,String date){
        if (fileType==null){
            return null;
        }
        Map<String, Object> parameterMap = new HashMap<String, Object>();
        parameterMap.put("contentfileType","1");
        Map<String,Object> map= imageServiceImpl.getPathList(parameterMap);
        String file= map.get("file")==null?"":map.get("file").toString();
        return file + File.separator + business + File.separator + fileType + File.separator + date + File.separator + id + File.separator;

    }

    /**
     * 获得返回路径
     * @param fileType
     * @return
     */
    public  String getBackFilePath(String fileType,String date,String id,String business){
        //判断ip是内网还是外网
//        String finalPath=null;
//        if (IpUtils.isInner()){
//            finalPath=PATH_CONFIG_FILES_ADDRESS_IP_KQ_INNER+"/"+fileType+"/";
//        }else{
//            finalPath=PATH_CONFIG_FILES_ADDRESS_IP_KQ+"/"+fileType+"/";
//        }
        Map<String, Object> parameterMap = new HashMap<String, Object>();
        if (fileType!=null){
            parameterMap.put("contentfileType","1");
        }
        Map<String,Object> map= imageServiceImpl.getPathList(parameterMap);
        String urlFile= map.get("urlFile")==null?"":map.get("urlFile").toString();

        return urlFile + File.separator + business + File.separator + fileType + File.separator + date + File.separator + id + File.separator;
    }
    /**
     * 获取附件保存路径
     * @param fileType
     * @param
     * @return
     */
    public  String getFilePath1(String fileType,String date,String id,String business){
        if (fileType==null){
            return null;
        }
        Map<String, Object> parameterMap = new HashMap<String, Object>();
        String contentfileType="0";
        parameterMap.put("contentfileType",contentfileType);
        Map<String,Object> map= imageServiceImpl.getPathList(parameterMap);
       // 附件上传服务器地址
        String file= map.get("file")==null?"":map.get("file").toString();
        return file + File.separator + business + File.separator + fileType + File.separator + date + File.separator + id + File.separator;

    }

    /**
     * 获得附件返回路径
     * @param fileType
     * @return
     */
    public  String getBackFilePath1(String fileType,String date,String id,String business ){
        //判断ip是内网还是外网
//        String finalPath=null;
//        if (IpUtils.isInner()){
//            finalPath=PATH_CONFIG_FILES_ADDRESS_IP_KQ_INNER+"/"+fileType+"/";
//        }else{
//            finalPath=PATH_CONFIG_FILES_ADDRESS_IP_KQ+"/"+fileType+"/";
//        }
        Map<String, Object> parameterMap = new HashMap<String, Object>();
        if (fileType!=null){
            parameterMap.put("contentfileType","0");
        }
        Map<String,Object> map= imageServiceImpl.getPathList(parameterMap);
        // 附件上传服务器地址
        String urlFile= map.get("urlFile")==null?"":map.get("urlFile").toString();
        System.out.println("urlFile"+urlFile);
        return urlFile + File.separator + business + File.separator + fileType + File.separator + date + File.separator + id + File.separator;
    }

    /**
     * 获取附件保存路径
     * @param fileType
     * @param
     * @return
     */
    public  String getRadioPath1(String fileType,String date,String id,String business){
        if (fileType==null){
            return null;
        }
        Map<String, Object> parameterMap = new HashMap<String, Object>();
        String contentfileType="2";
        parameterMap.put("contentfileType",contentfileType);
        Map<String,Object> map= imageServiceImpl.getPathList(parameterMap);
        // 附件上传服务器地址
        String file= map.get("file")==null?"":map.get("file").toString();
        return file + File.separator + business + File.separator + fileType + File.separator + date + File.separator + id + File.separator;

    }

    /**
     * 获得附件返回路径
     * @param fileType
     * @return
     */
    public  String getBackRidioPath1(String fileType,String date,String id,String business){
        //判断ip是内网还是外网
//        String finalPath=null;
//        if (IpUtils.isInner()){
//            finalPath=PATH_CONFIG_FILES_ADDRESS_IP_KQ_INNER+"/"+fileType+"/";
//        }else{
//            finalPath=PATH_CONFIG_FILES_ADDRESS_IP_KQ+"/"+fileType+"/";
//        }
        Map<String, Object> parameterMap = new HashMap<String, Object>();
        if (fileType!=null){
            parameterMap.put("contentfileType","1");
        }
        Map<String,Object> map= imageServiceImpl.getPathList(parameterMap);
        // 附件上传服务器地址
        String urlFile= map.get("urlFile")==null?"":map.get("urlFile").toString();
        System.out.println("urlFile"+urlFile);
        return urlFile + File.separator + business + File.separator + fileType + File.separator + date + File.separator + id + File.separator;
    }




}
