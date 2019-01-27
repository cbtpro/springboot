package com.lieqihezi.www.controller;

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
import com.lieqihezi.www.utils.DownloadImageHelper;
import com.lieqihezi.www.utils.FileUtil;
import com.lieqihezi.www.utils.ImageUtil;

@RestController
@RequestMapping(value = "/api")
public class ImageUploadController {

	class ImageOfPrimitives {
		private String base64;
		private String imageType;

		ImageOfPrimitives() {
		}

		public String getBase64() {
			return base64;
		}

		public void setBase64(String base64) {
			this.base64 = base64;
		}

		public String getImageType() {
			return imageType;
		}

		public void setImageType(String imageType) {
			this.imageType = imageType;
		}
	}

	@RequestMapping(value = "/image", method = RequestMethod.POST)
	public ResponseEntity<Map<String,Object>> uploadImage(@RequestBody String imageJson) {
		Gson gson = new Gson();
		ImageOfPrimitives image = gson.fromJson(imageJson, ImageOfPrimitives.class);
		UUID uuid = UUID.randomUUID();
		String UUID = uuid.toString();
		MultipartFile mpf = ImageUtil.base64ToMultipart(image.getBase64());
		String originalfileName = mpf.getOriginalFilename();
		String fileNameSuffix = originalfileName.substring(originalfileName.lastIndexOf("."), originalfileName.length());
		String filePath = "D:/uploads/images/";
		String fileName = UUID + fileNameSuffix;
		boolean isSuccess = FileUtil.storageFile(mpf, filePath, fileName);
		Map<String, Object> result = new HashMap<String, Object>();
		HttpStatus httpStatus = null;
		if (isSuccess) {
			result.put("imageName", fileName);
			result.put("message", "上传图片成功");
			httpStatus = HttpStatus.OK;
		} else {
			result.put("imageName", "");
			result.put("message", "上传图片失败");
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
