package com.rental.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.rental.util.FileHandleUtil;

/**
 * 上传图片操作控制器
 * @author 张宝玲
 *
 */
@Controller
public class UploadPhotoController {
	
	/**
	 * 上传图片
	 * @param photo
	 * @param request
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("/upload_photo")
    @ResponseBody
    public Map<String, String> upload(@RequestParam("photo") MultipartFile photo, HttpServletRequest requet) throws IOException {
		Map<String, String> ret = new HashMap<String, String>();
		if(!photo.isEmpty()) {
            String result = FileHandleUtil.upload(photo.getInputStream(), "file/", photo.getOriginalFilename());
            System.out.println("上传成功：" + result);
            ret.put("type", "success");
    		ret.put("msg", "上传成功！");
    		ret.put("filepath", result );
        }else {
        	System.out.println("上传失败");
        	ret.put("type", "error");
    		ret.put("msg", "上传失败！");
        }
		return ret;
	}
}
