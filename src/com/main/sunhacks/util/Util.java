package com.main.sunhacks.util;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;

public class Util {
	
	public static File getFileFromResources(String fileName) {
		ClassLoader classLoader = new Util().getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file is not found!");
        } else {
            return new File(resource.getFile());
        }
	}
	
	public static String encodeData(String s) throws UnsupportedEncodingException {
		return URLEncoder.encode(s, "UTF-8");
	}

}
