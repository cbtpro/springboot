package com.lieqihezi.www.utils;

import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public class FileUtil {

	public static boolean storageFile(MultipartFile mpf, String filePath) {

		File file = new File(filePath);
		if (!file.getParentFile().exists()) {
			file.mkdirs();
		}
		try {
			mpf.transferTo(file);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
