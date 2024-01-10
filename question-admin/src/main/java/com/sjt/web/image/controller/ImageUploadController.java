package com.sjt.web.image.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

/**
 * 图片上传控制器
 * @author Rocky
 * @create 2022-02-0417:06
 */
@RestController
@RequestMapping("/api/upload/")
public class ImageUploadController {
    //图片上传的路径
    @Value("${web.uploadpath}")
    private String webUploadpath;

    @RequestMapping("/uploadImage")
    public String uploadImage(@RequestParam("file")MultipartFile file){
        String Url = null;
        //获取文件名字
        String fileName = file.getOriginalFilename();
        //获取文件后缀
        String fileExtenionName = fileName.substring(fileName.indexOf("."));
        //生成新的名称
        String newName = UUID.randomUUID().toString()+fileExtenionName;
        String path = webUploadpath;
        File fileDir = new File(path);
        if(!fileDir.exists()){
            //设置权限
            fileDir.setWritable(true);
            fileDir.mkdirs();
        }
        File targetFile = new File(path, newName);
        try{
            file.transferTo(targetFile);
            Url = "/" + targetFile.getName();
        }catch (Exception e){
            return null;
        }
        return "/images" + Url;
    }

}
