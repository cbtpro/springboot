package com.lieqihezi.www.utils;

import java.io.File;
import java.util.Base64;
import org.springframework.web.multipart.MultipartFile;

public class ImageUtil {

	public boolean GenerateImage(String imgStr) {
		return false;
	}

	public static MultipartFile base64ToMultipart(String base64) {
		String[] baseStrs = base64.split(",");

		byte[] b = new byte[0];
		b = Base64.getDecoder().decode(baseStrs[1]);

		for (int i = 0; i < b.length; ++i) {
			if (b[i] < 0) {
				b[i] += 256;
			}
		}

		return new BASE64DecodedMultipartFile(b, baseStrs[0]);
	}
	/**  
     * 删除  
     *   
     * @param files  
     */  
    public static void deleteFile(File... files) {  
        for (File file : files) {  
            if (file.exists()) {  
                file.delete();  
            }  
        }  
    }
}
