package com.lieqihezi.www.test;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;

import java.io.File;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		/*
		 * Configuration 表示带指定Zone对象的配置类 其中Zone 0 表示华东地区
		 */
		Configuration cfg = new Configuration(Zone.zone0());
		UploadManager uploadManager = new UploadManager(cfg);
		String accessKey = "你的ak";
		String secretKey = "你的sk";
		String bucket = "store";
		String localFilePath = "D:/uploads/images/0dc627c5-5f55-47aa-8b83-ad0113821d7b.jpeg";
		String key = "0dc627c5-5f55-47aa-8b83-ad0113821d7b.jpeg";
		Auth auth = Auth.create(accessKey, secretKey);
		String upToken = auth.uploadToken(bucket);
		try {
			Response response = uploadManager.put(localFilePath, key, upToken);
			// 解析上传成功的结果
			DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
			System.out.println(putRet.key);
			System.out.println(putRet.hash);
		} catch (QiniuException ex) {
			Response r = ex.response;
			System.err.println(r.toString());
			try {
				System.err.println(r.bodyString());
			} catch (QiniuException ex2) {
				// ignore
			}
		}
	}
}