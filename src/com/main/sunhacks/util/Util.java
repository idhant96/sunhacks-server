package com.main.sunhacks.util;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;

public class Util {
	
	public static String encodeData(String s) throws UnsupportedEncodingException {
		return URLEncoder.encode(s, "UTF-8");
	}

}
