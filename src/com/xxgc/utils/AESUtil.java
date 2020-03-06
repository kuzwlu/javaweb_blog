package com.xxgc.utils;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;

public class AESUtil {
	/*
	 * AES加密算法作为新一代的数据加密标准汇聚了强安全性、高性能、高效率、易用和灵活等优点。 AES设计有三个密钥长度：128，192，256 位。
	 * 相对而言，AES的128密钥比DES的56密钥强了1021倍
	 */

	/**
	 * 用户自定义的密钥，由前后端协商确定 ！！！（不应该在加解密方法的参数中暴露），AES的安全性，取决于密钥的安全性。
	 */
	private static final String KEY = "agdfscxcvz";
	/**
	 * AES:算法，ECB:模式，PKCS5Padding:补码方式
	 */
	private static final String ALGORITHM = "AES/ECB/PKCS5Padding";

	/**
	 * AES加密
	 * 
	 * @param message
	 *            要加密的信息
	 * @return 加密后的字符串
	 */
	public static String encrypt(String message) {
		return doAES(message, KEY, Cipher.ENCRYPT_MODE);
	}

	/**
	 * AES解密
	 * 
	 * @param message
	 *            要解密的信息
	 * @return 解密后的字符串
	 */
	public static String decrypt(String message) {
		return doAES(message, KEY, Cipher.DECRYPT_MODE);
	}

	/**
	 * 加密或解密的实际操作过程
	 * 
	 * @param message
	 *            待处理的信息
	 * @param key
	 *            AES加解密过程需要的密钥
	 * @param mode
	 *            加解密mode
	 * @return 加密或解密后的信息
	 */
	private static String doAES(String message,String key, int mode){
			if (StringUtils.isBlank(message) || StringUtils.isBlank(key)){
                return null;
			}
            // 由于AES算法要求密钥的长度为16的倍数，（1,2,3,4）步的目的: 把用户自定义的密钥替换成16位的密钥

            // 1. 构造密钥生成器，指定为AES算法
            KeyGenerator keyGenerator = null;
			try {
				keyGenerator = KeyGenerator.getInstance("AES");
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            // 2. 根据用户自定义密钥对应的字节数组，生成一个128位的随机源（只能是128 or 192 or 256中的一个）
            keyGenerator.init(128, new SecureRandom(key.getBytes()));
            // 3. 生成AES算法的原始对称密钥
            SecretKey secretKey = keyGenerator.generateKey();
            // 4. 获取原始对称密钥的字节数组
            byte[] enCodeFormat = secretKey.getEncoded();

            // 5. 根据字节数组生成AES密钥
            SecretKeySpec secretKeySpec = new SecretKeySpec(enCodeFormat,"AES");
            // 6.根据指定算法AES自成密码器
            Cipher cipher = null;
			try {
				cipher = Cipher.getInstance(ALGORITHM);
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchPaddingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            // 7.初始化密码器，第一个参数为加密(Encrypt_mode)或者解密(Decrypt_mode)操作，第二个参数为使用的KEY
            try {
				cipher.init(mode, secretKeySpec);
			} catch (InvalidKeyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

            if (mode == Cipher.ENCRYPT_MODE) {
                byte[] content = message.getBytes();
                byte[] result = null;
				try {
					result = cipher.doFinal(content);
				} catch (IllegalBlockSizeException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (BadPaddingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                // !!! 加密，AES加密后的结果默认是Base64格式的字节数组
                return Base64.encodeBase64String(result);
            } else {
                byte[] content = Base64.decodeBase64(message);
                byte[] result = null;
				try {
					result = cipher.doFinal(content);
				} catch (IllegalBlockSizeException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (BadPaddingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                return new String(result);
            }
	}
}
