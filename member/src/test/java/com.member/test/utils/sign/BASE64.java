package com.member.test.utils.sign;

import sun.misc.BASE64Decoder; 

public class BASE64 {
	 
	// 将 s 进行 BASE64 编码 
	public static String getBASE64(String s) { 
	if (s == null) return null; 
	return (new sun.misc.BASE64Encoder()).encode( s.getBytes() ); 
	} 
	 
	// 将 BASE64 编码的字符串 s 进行解码 
	public static String getFromBASE64(String s) { 
	if (s == null) return null; 
	BASE64Decoder decoder = new BASE64Decoder(); 
	try { 
	byte[] b = decoder.decodeBuffer(s);
		return new String(b,"utf-8");
	} catch (Exception e) { 
	return null; 
	} 
	}

	public static void main(String[] args){
//		String str = "XlpolF6uf3u4kGT+2S73LQ==";
//		System.out.print(getFromBASE64(str));
		String str = "99c2cbbf65e9ea9d";
		System.out.println(getBASE64(str));
	}
}
