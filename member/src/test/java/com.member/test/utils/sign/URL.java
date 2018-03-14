package com.member.test.utils.sign;

import java.util.Iterator;
import java.util.Map;

public class URL {
	
	public static String create(String url,Map<String, String> params){
		String request = "";
		Iterator <String> it =params.keySet().iterator();
		while (it.hasNext()){
			Object value;
			String key = (String) it.next();
			
			if (params.get(key)==null)
				value = "";
			else
				value  = params.get(key);
			request = request + key + "=" + value + "&";
			
		}
		request = request.substring(0,request.length()-1);
		request = url +request;
		return request;
	}

}
