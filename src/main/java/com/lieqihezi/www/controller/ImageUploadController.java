package com.lieqihezi.www.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.lieqihezi.www.utils.FileUtil;
import com.lieqihezi.www.utils.ImageUtil;

@RestController
@RequestMapping(value = "/image")
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

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String uploadImage(@RequestBody String imageJson) {
		Gson gson = new Gson();
		ImageOfPrimitives image = gson.fromJson(imageJson, ImageOfPrimitives.class);
		UUID uuid = UUID.randomUUID();
		String fileName = uuid.toString();
		MultipartFile mpf = ImageUtil.base64ToMultipart(image.getBase64());
		String originalfileName = mpf.getOriginalFilename();
		String fileNameSuffix = originalfileName.substring(originalfileName.lastIndexOf("."), originalfileName.length());
		String filePath = "D:/" + fileName + fileNameSuffix;
		boolean isSuccess = FileUtil.storageFile(mpf, filePath);
		if (isSuccess) {
			return "success";
		}
		return "fail";
	}

	@RequestMapping(value = "/download/{UUID}", method = RequestMethod.GET)
	public String downloadImage(@PathVariable("UUID") String UUID) {
		return "success";
	}
}
