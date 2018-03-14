package com.member.test.utils.rsaUtils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public final class RSAUtils {
	private static final String DEFAULT_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCIBeKWiLSAJvoAwfiQZQvdwx4VHyAyPlSPoRhZ"
			+ "YJJzTvQP0n6pm+vueONxZ8XXK2947boHG35a9Ee4oq4wCkCnN8pQ775GRZKypibIzmKUnwofuvJ0"
			+ "GgJXvDBkaI20d1+4o76MSMMDt5V4BH7R5ZD9N+RKQ8u+24aQ1SR5ONpAYQIDAQAB";

	private static final String DEFAULT_PRIVATE_KEY = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAIgF4paItIAm+gDB+JBlC93DHhUf"
			+ "IDI+VI+hGFlgknNO9A/Sfqmb6+5443Fnxdcrb3jtugcbflr0R7iirjAKQKc3ylDvvkZFkrKmJsjO"
			+ "YpSfCh+68nQaAle8MGRojbR3X7ijvoxIwwO3lXgEftHlkP035EpDy77bhpDVJHk42kBhAgMBAAEC"
			+ "gYBl9rtnCmz8D7uia8UPQJ/Hieb4AFAbObwbuq/M9+ZwR4QOhETskXQnXDlqnlagt++k39duUn7C"
			+ "YZGN52zZmCXjztBKlRsEqffhKyTKqvVELU2rf9ylhLEPACqE6+CkB2iH3wv2FZJOmyOCXtSEKbaA"
			+ "iG09CQGgK1HfZjoi6JwkjQJBAPzyFGXig9d1Mud6EukA6FMH+7cjIsjzVFvZW7sSqThEFcPqrGPw"
			+ "ReJ8YMElW/t8EZhKjfZx/PKe0lNLrR4oSssCQQCJql4JsO/UC2E0AApUtZcLJeZEZN2dit6UlSSl"
			+ "7U+VRhwIKq8Fv9cidwLQO/S/n9a3o9ZVv02Qftfjr302GCADAkEAwXsiozR7CMn7IBi+ckBhdXG7"
			+ "10AnEXMfagp/Ij+J9SIPTAcryl419qKRF+zNyTYD92u/320dwTA/TburNlA3tQJAW4fQ2nSwILYo"
			+ "PODB/ax27syWG2dNChBpCTVYlpIAVem1faCTmikBK0Mhtb4HbtkhdBwUyBJXyfjsJdBqWesUbQJB"
			+ "AJUa3d/I7gEVjKrcDYrcISp4FnvAP7vT3y5r0JqJJjtED++nijwM/Tc6UwxrJqu++tKpopoFcsPh" + "q3V2ZdqSWt4=";

	/**
	 * 私钥
	 */
	private static RSAPrivateKey privateKey;

	/**
	 * 公钥
	 */
	private static RSAPublicKey publicKey;

	/**
	 * 字节数据转字符串专用集合
	 */
	private static final char[] HEX_CHAR = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	static {
		// 加载公钥
		try {
			loadPublicKey(RSAUtils.DEFAULT_PUBLIC_KEY);
			System.out.println("加载公钥成功");
		} catch (Exception e) {
			System.err.println(e.getMessage());
			System.err.println("加载公钥失败");
		}
		// 加载私钥
		try {
			loadPrivateKey(RSAUtils.DEFAULT_PRIVATE_KEY);
			System.out.println("加载私钥成功");
		} catch (Exception e) {
			System.err.println(e.getMessage());
			System.err.println("加载私钥失败");
		}
	}

	/**
	 * 获取私钥
	 * 
	 * @return 当前的私钥对象
	 */
	private static RSAPrivateKey getPrivateKey() {
		return privateKey;
	}

	/**
	 * 获取公钥
	 * 
	 * @return 当前的公钥对象
	 */
	private static RSAPublicKey getPublicKey() {
		return publicKey;
	}

	/**
	 * 随机生成密钥对
	 */
	private static void genKeyPair() {
		KeyPairGenerator keyPairGen = null;
		try {
			keyPairGen = KeyPairGenerator.getInstance("RSA");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		keyPairGen.initialize(1024, new SecureRandom());
		KeyPair keyPair = keyPairGen.generateKeyPair();
		RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();

		byte[] privateBytes = privateKey.getEncoded();
		BASE64Encoder encoder = new BASE64Encoder();
		String privateStr = encoder.encode(privateBytes);
		byte[] publicBytes = publicKey.getEncoded();
		String publicStr = encoder.encode(publicBytes);
		System.out.println("privateKey:" + privateStr);
		System.out.println("publicKey:" + publicStr);
	}

	/**
	 * 从字符串中加载公钥
	 * 
	 * @param publicKeyStr
	 *            公钥数据字符串
	 * @throws Exception
	 *             加载公钥时产生的异常
	 */
	private static void loadPublicKey(String publicKeyStr) throws Exception {
		try {
			BASE64Decoder base64Decoder = new BASE64Decoder();
			byte[] buffer = base64Decoder.decodeBuffer(publicKeyStr);
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);
			publicKey = (RSAPublicKey) keyFactory.generatePublic(keySpec);
		} catch (NoSuchAlgorithmException e) {
			throw new Exception("无此算法");
		} catch (InvalidKeySpecException e) {
			throw new Exception("公钥非法");
		} catch (IOException e) {
			throw new Exception("公钥数据内容读取错误");
		} catch (NullPointerException e) {
			throw new Exception("公钥数据为空");
		}
	}

	/**
	 * 从字符串中加载私钥
	 * 
	 * @param privateKeyStr
	 * @throws Exception
	 */
	private static void loadPrivateKey(String privateKeyStr) throws Exception {
		try {
			BASE64Decoder base64Decoder = new BASE64Decoder();
			byte[] buffer = base64Decoder.decodeBuffer(privateKeyStr);
			PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			privateKey = (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
		} catch (NoSuchAlgorithmException e) {
			throw new Exception("无此算法");
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
			throw new Exception("私钥非法");
		} catch (IOException e) {
			throw new Exception("私钥数据内容读取错误");
		} catch (NullPointerException e) {
			throw new Exception("私钥数据为空");
		}
	}

	/**
	 * 公钥加密过程
	 * 
	 * @param publicKey
	 *            公钥
	 * @param plainTextData
	 *            明文数据
	 * @return
	 * @throws Exception
	 *             加密过程中的异常信息
	 */
	private static byte[] encrypt(RSAPublicKey publicKey, byte[] plainTextData) throws Exception {
		if (publicKey == null) {
			throw new Exception("加密公钥为空, 请设置");
		}
		Cipher cipher = null;
		try {
			cipher = Cipher.getInstance("RSA");// , new BouncyCastleProvider());
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
			byte[] output = cipher.doFinal(plainTextData);
			return output;
		} catch (NoSuchAlgorithmException e) {
			throw new Exception("无此加密算法");
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
			return null;
		} catch (InvalidKeyException e) {
			throw new Exception("加密公钥非法,请检查");
		} catch (IllegalBlockSizeException e) {
			throw new Exception("明文长度非法");
		} catch (BadPaddingException e) {
			throw new Exception("明文数据已损坏");
		}
	}

	/**
	 * 私钥加密过程
	 * 
	 * @param publicKey
	 *            公钥
	 * @param plainTextData
	 *            明文数据
	 * @return
	 * @throws Exception
	 *             加密过程中的异常信息
	 */
	private static byte[] encryptByPrivate(RSAPrivateKey privateKey, byte[] plainTextData) throws Exception {
		if (privateKey == null) {
			throw new Exception("加密公钥为空, 请设置");
		}
		Cipher cipher = null;
		try {
			cipher = Cipher.getInstance("RSA");// , new BouncyCastleProvider());
			cipher.init(Cipher.ENCRYPT_MODE, privateKey);
			byte[] output = cipher.doFinal(plainTextData);
			return output;
		} catch (NoSuchAlgorithmException e) {
			throw new Exception("无此加密算法");
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
			return null;
		} catch (InvalidKeyException e) {
			throw new Exception("加密公钥非法,请检查");
		} catch (IllegalBlockSizeException e) {
			throw new Exception("明文长度非法");
		} catch (BadPaddingException e) {
			throw new Exception("明文数据已损坏");
		}
	}

	/**
	 * 私钥解密过程
	 * 
	 * @param privateKey
	 *            私钥
	 * @param cipherData
	 *            密文数据
	 * @return 明文
	 * @throws Exception
	 *             解密过程中的异常信息
	 */
	private static byte[] decrypt(RSAPrivateKey privateKey, byte[] cipherData) throws Exception {
		if (privateKey == null) {
			throw new Exception("解密私钥为空, 请设置");
		}
		Cipher cipher = null;
		try {
			cipher = Cipher.getInstance("RSA");// , new BouncyCastleProvider());
			cipher.init(Cipher.DECRYPT_MODE, privateKey);
			byte[] output = cipher.doFinal(cipherData);
			return output;
		} catch (NoSuchAlgorithmException e) {
			throw new Exception("无此解密算法");
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
			return null;
		} catch (InvalidKeyException e) {
			throw new Exception("解密私钥非法,请检查");
		} catch (IllegalBlockSizeException e) {
			throw new Exception("密文长度非法");
		} catch (BadPaddingException e) {
			throw new Exception("密文数据已损坏");
		}
	}

	/**
	 * 公钥解密过程
	 * 
	 * @param privateKey
	 *            私钥
	 * @param cipherData
	 *            密文数据
	 * @return 明文
	 * @throws Exception
	 *             解密过程中的异常信息
	 */
	private static byte[] decryptByPublicKey(RSAPublicKey publicKey, byte[] cipherData) throws Exception {
		if (publicKey == null) {
			throw new Exception("解密私钥为空, 请设置");
		}
		Cipher cipher = null;
		try {
			cipher = Cipher.getInstance("RSA");// , new BouncyCastleProvider());
			cipher.init(Cipher.DECRYPT_MODE, publicKey);
			byte[] output = cipher.doFinal(cipherData);
			return output;
		} catch (NoSuchAlgorithmException e) {
			throw new Exception("无此解密算法");
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
			return null;
		} catch (InvalidKeyException e) {
			throw new Exception("解密私钥非法,请检查");
		} catch (IllegalBlockSizeException e) {
			throw new Exception("密文长度非法");
		} catch (BadPaddingException e) {
			throw new Exception("密文数据已损坏");
		}
	}

	/**
	 * 字节数据转十六进制字符串
	 * 
	 * @param data
	 *            输入数据
	 * @return 十六进制内容
	 */
	public static String byteArrayToString(byte[] data) {
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < data.length; i++) {
			// 取出字节的高四位 作为索引得到相应的十六进制标识符 注意无符号右移
			stringBuilder.append(HEX_CHAR[(data[i] & 0xf0) >>> 4]);
			// 取出字节的低四位 作为索引得到相应的十六进制标识符
			stringBuilder.append(HEX_CHAR[(data[i] & 0x0f)]);
			if (i < data.length - 1) {
				stringBuilder.append(' ');
			}
		}
		return stringBuilder.toString();
	}

	/**
	 * decrypt String by Ras(sub) by privateKey
	 * 
	 * @return success:return decode original
	 */
	public static String decryptByPrivateKey(String ciphertext) throws Exception {
		try {
			BASE64Decoder decoder = new BASE64Decoder();
			byte[] cipher = decoder.decodeBuffer(ciphertext);
			byte[] plainText = decrypt(getPrivateKey(), cipher);
			return new String(plainText, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * decrypt String by Ras(sub) by publicKey
	 * 
	 * @return success:return decode original
	 */
	public static String decryptByPublicKey(String ciphertext) throws Exception {
		try {
			BASE64Decoder decoder = new BASE64Decoder();
			byte[] cipher = decoder.decodeBuffer(ciphertext);
			byte[] plainText = decryptByPublicKey(getPublicKey(), cipher);
			return new String(plainText, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * encrypt String by Ras(add) by publicKey
	 * 
	 * @return success:return encrypt Base64 String ; fail:"fail"
	 * @throws Exception
	 */
	public static String encryptByPublicKey(String plaintext) throws Exception {
		try {
			BASE64Encoder encoder = new BASE64Encoder();
			// encrypt process
			byte[] cipher = encrypt(getPublicKey(), plaintext.getBytes("utf-8"));
			// byte[] to String by Base64
			return encoder.encode(cipher);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * encrypt String by Ras(add) by PrivateKey
	 * 
	 * @return success:return encrypt Base64 String
	 * @throws Exception
	 */
	public static String encryptByPrivateKey(String plaintext) throws Exception {
		try {
			BASE64Encoder encoder = new BASE64Encoder();
			// encrypt process
			byte[] cipher = encryptByPrivate(getPrivateKey(), plaintext.getBytes("utf-8"));
			// byte[] to String by Base64
			return encoder.encode(cipher);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	// 公钥加密
	public static String publicKeyEncryption(String encryptStr) throws Exception {
		// 公钥加密
		System.out.println("明文：" + encryptStr);
		long encryptstart = System.currentTimeMillis();
		String cipherStr = encryptByPublicKey(encryptStr);
		System.out.println("公钥加密密文：" + cipherStr);
		long encryptend = System.currentTimeMillis();
		System.out.println("encrypt use time:" + (encryptend - encryptstart) + "ms");
		return cipherStr;
	}

	// 私钥加密
	public static String privateKeyEncryption(String encryptStr) throws Exception {
		// 公钥加密
		System.out.println("明文：" + encryptStr);
		long encryptstart = System.currentTimeMillis();
		String cipherStr = encryptByPrivateKey(encryptStr);
		System.out.println("私钥加密密文：" + cipherStr);
		long encryptend = System.currentTimeMillis();
		System.out.println("encrypt use time:" + (encryptend - encryptstart) + "ms");
		return cipherStr;
	}

	public static void main(String[] args) {
		// 测试字符串
		String encryptStr = "qwe123";
		try {
			System.out.println("=============私钥加密，公钥解密！===============");
			// 私钥加密
			System.out.println("明文：" + encryptStr);
			long encryptstart = System.currentTimeMillis();
			String cipherStr = encryptByPrivateKey(encryptStr);
			System.out.println("私钥加密密文：" + cipherStr);
			long encryptend = System.currentTimeMillis();
			System.out.println("encrypt use time:" + (encryptend - encryptstart) + "ms");
			// 公钥钥解密
			long decryptstart = System.currentTimeMillis();
			String plaineText = decryptByPublicKey(cipherStr);
			System.out.println("解密：" + plaineText);
			long decryptend = System.currentTimeMillis();
			System.out.println("decrypt use time:" + (decryptend - decryptstart) + "ms");
			System.out.println("=============公钥加密，私钥解密！===============");
			// 公钥加密
			System.out.println("明文：" + encryptStr);
			encryptstart = System.currentTimeMillis();
			cipherStr = encryptByPublicKey(encryptStr);
			System.out.println("公钥加密密文：" + cipherStr);
			encryptend = System.currentTimeMillis();
			System.out.println("encrypt use time:" + (encryptend - encryptstart) + "ms");
			// 私钥解密
			decryptstart = System.currentTimeMillis();
			plaineText = decryptByPrivateKey(cipherStr);
			System.out.println("解密：" + plaineText);
			decryptend = System.currentTimeMillis();
			System.out.println("decrypt use time:" + (decryptend - decryptstart) + "ms");
			System.out.println("===========获取私钥公钥============");
			genKeyPair();// 获取密钥对
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
