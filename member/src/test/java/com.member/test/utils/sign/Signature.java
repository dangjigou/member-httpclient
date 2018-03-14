package com.member.test.utils.sign;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class Signature {
	private static String request;
	private static String request_encode;
	public static String getByRequest(Map<String, String> params, String sk) throws UnsupportedEncodingException {
		request = "";
		String sig = null;
		sortMap(params);
		System.out.println("request = "+request);
//		String request_encode = URIEncoder
		request_encode = request + sk;
		
		System.out.println("request_encode = " + request_encode);
		sig = Md5.getServerMd5(request_encode);
		System.out.println(sig);

		return sig;
	}

	private static void sortMap(Map<String, String> params) throws UnsupportedEncodingException {
		class ConcurrentHashMap<M, N> {
			private M key;
			private Object value;

			private M getKey() {
				return key;
			}

			private void setKey(M key) {
				this.key = key;
			}

			private Object getValue() {
				return value;
			}

			private void setValue(Object object) {
				this.value = object;
			}
		}

		List<ConcurrentHashMap<String, Object>> list = new ArrayList<ConcurrentHashMap<String, Object>>();
		for (Iterator<String> i = params.keySet().iterator(); i.hasNext();) {
			ConcurrentHashMap<String, Object> my = new ConcurrentHashMap<String, Object>();
			String key = i.next();
			my.setKey(key);
			my.setValue(URLEncoder.encode(params.get(key), "UTF-8"));
			list.add(my);
		}
		
		Collections.sort(list, new Comparator<ConcurrentHashMap<String, Object>>() {
			public int compare(ConcurrentHashMap<String, Object> o1,
							   ConcurrentHashMap<String, Object> o2) {

				return o1.getKey().compareTo(o2.getKey());
			}
		});

		for (int i = 0, k = list.size(); i < k; i++) {
			Object value;
			if  (list.get(i).getValue()==null)
				value = "";
			else
				value  = list.get(i).getValue();
			request = request + list.get(i).getKey() + "=" + value + "&";
//			System.out.println("request = " + request);
		}
		request = request.substring(0, request.length() - 1);
		System.out.println("request = " + request);
	}
	
	public static void main(String[] args) throws UnsupportedEncodingException{
		String mobile = "15067163153";
		String type = "sms";
		String ak = "aMdi6T4a2kA=";
		String sk = "iS0Dc7zK2/Ef/rxoe480Yg==";
		Map<String, String> params = new LinkedHashMap<String,String>();
		params.put("mobile", mobile);
		params.put("type", type);
		params.put("ak", ak);
		getByRequest(params, sk);
	}

//	public static void main(String[] args) throws UnsupportedEncodingException{
//		String lng = "114.099264";
//		String lat = "32.153186";
////		String lng = "120.075803";
////		String lat = "30.295115";
//		String ak = "aMdi6T4a2kA=";
//		String nlid = "1256465";
//		String sk = "iS0Dc7zK2/Ef/rxoe480Yg==";
//		Map<String, String> params = new LinkedHashMap<String,String>();
//		params.put("lng", lng);
//		params.put("lat", lat);
//		params.put("ak", ak);
//		params.put("nlid",nlid);
//		getByRequest(params,sk);
//	}
}
