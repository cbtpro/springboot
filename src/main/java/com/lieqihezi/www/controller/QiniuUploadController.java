package com.lieqihezi.www.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.lieqihezi.www.controller.ImageUploadController.ImageOfPrimitives;
import com.lieqihezi.www.utils.DownloadImageHelper;
import com.lieqihezi.www.utils.ImageUtil;
import com.lieqihezi.www.utils.QiniuHelper;

@RestController
@RequestMapping(value = "/qiniu")
public class QiniuUploadController {

	@RequestMapping(value = "/image", method = RequestMethod.POST)
	public ResponseEntity<Map<String,Object>> uploadImage(@RequestBody String imageJson) {
		Gson gson = new Gson();
		ImageOfPrimitives image = gson.fromJson(imageJson, ImageOfPrimitives.class);
		UUID uuid = UUID.randomUUID();
		String UUID = uuid.toString();
		MultipartFile multfile = ImageUtil.base64ToMultipart(image.getBase64());

		String originalfileName = multfile.getOriginalFilename();
		String fileNameSuffix = originalfileName.substring(originalfileName.lastIndexOf("."), originalfileName.length());
		String fileName = UUID + fileNameSuffix;
		String qiniuFileName = "";
		File tempFile;
		try {
			tempFile = File.createTempFile(UUID, fileNameSuffix);
	        // MultipartFile to File
	        multfile.transferTo(tempFile);
			qiniuFileName = QiniuHelper.upload(tempFile.getPath(), fileName);
	        //程序结束时，删除临时文件
	        ImageUtil.deleteFile(tempFile);
		} catch (IOException e) {
			e.printStackTrace();
		}

		Map<String, Object> result = new HashMap<String, Object>();
		HttpStatus httpStatus = null;
		if (!qiniuFileName.equals("")) {
			result.put("imageName", fileName);
			result.put("message", "上传图片到七牛成功");
			httpStatus = HttpStatus.OK;
		} else {
			result.put("imageName", "");
			result.put("message", "上传图片到七牛失败");
			httpStatus = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<Map<String,Object>>(result, httpStatus);
	}
	
	@RequestMapping(value = "/image/{imageName}", method = RequestMethod.GET)
	public String downloadImage(@PathVariable("imageName") String imageName, HttpServletRequest request, HttpServletResponse response) {
		DownloadImageHelper downloadImageHelper = new DownloadImageHelper();
		String filePath = "D:/uploads/images/";
		String fileName = imageName;
		downloadImageHelper.buildImage(request, response, filePath + fileName);
		return "success";
	}
}
