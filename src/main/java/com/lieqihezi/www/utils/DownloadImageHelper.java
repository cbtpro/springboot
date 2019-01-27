package com.lieqihezi.www.utils;

import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DownloadImageHelper {

	public String buildImage(HttpServletRequest request, HttpServletResponse response, String path) {
		ServletOutputStream servletOutputStream = null;
		try {
			response.setContentType("image/jpeg");
			FileInputStream fileInputStream = new FileInputStream(path);
			servletOutputStream = response.getOutputStream();
			int len = 0;
			// 创建数据缓冲区
			byte[] buffer = new byte[1024];
			// 通过response对象获取outputStream流
			servletOutputStream = response.getOutputStream();
			// 将FileInputStream流写入到buffer缓冲区
			while ((len = fileInputStream.read(buffer)) > 0) {
				// 使用OutputStream将缓冲区的数据输出到浏览器
				servletOutputStream.write(buffer, 0, len);
			}
			servletOutputStream.flush();
			fileInputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (servletOutputStream != null) {
				try {
					servletOutputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return "";
	}
}
