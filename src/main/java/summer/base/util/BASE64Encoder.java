package summer.base.util;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class BASE64Encoder {
	private static final char last2byte = (char) Integer
			.parseInt("00000011", 2);
	private static final char last4byte = (char) Integer
			.parseInt("00001111", 2);
	private static final char last6byte = (char) Integer
			.parseInt("00111111", 2);
	private static final char lead6byte = (char) Integer
			.parseInt("11111100", 2);
	private static final char lead4byte = (char) Integer
			.parseInt("11110000", 2);
	private static final char lead2byte = (char) Integer
			.parseInt("11000000", 2);
	private static final char[] encodeTable = { 'A', 'B', 'C', 'D', 'E', 'F',
			'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S',
			'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f',
			'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
			't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5',
			'6', '7', '8', '9', '+', '/' };

	public String encode(byte[] from) {
		StringBuffer to = new StringBuffer((int) (from.length * 1.34D) + 3);
		int num = 0;
		char currentByte = '\000';
		for (int i = 0; i < from.length; i++) {
			num %= 8;
			while (num < 8) {
				switch (num) {
				case 0:
					currentByte = (char) (from[i] & lead6byte);
					currentByte = (char) (currentByte >>> '\002');
					break;
				case 2:
					currentByte = (char) (from[i] & last6byte);
					break;
				case 4:
					currentByte = (char) (from[i] & last4byte);
					currentByte = (char) (currentByte << '\002');
					if (i + 1 >= from.length)
						break;
					currentByte = (char) (currentByte | (from[(i + 1)] & lead2byte) >>> 6);
					break;
				case 6:
					currentByte = (char) (from[i] & last2byte);
					currentByte = (char) (currentByte << '\004');
					if (i + 1 >= from.length)
						break;
					currentByte = (char) (currentByte | (from[(i + 1)] & lead4byte) >>> 4);
				case 1:
				case 3:
				case 5:
				}
				to.append(encodeTable[currentByte]);
				num += 6;
			}
		}
		if (to.length() % 4 != 0) {
			for (int i = 4 - to.length() % 4; i > 0; i--) {
				to.append("=");
			}
		}
		return to.toString();
	}

	/**
	 * 使用HmacSHA算法计算
	 * 
	 * @param data
	 *            字符基串
	 * @param key
	 *            密钥
	 * @return 加密后的签名（长度为16的字节数组）
	 */
	public static byte[] encodeHmacSHA(byte[] data, byte[] key) {
		String method = "HmacSHA1";
		Key k = new SecretKeySpec(key, method);
		Mac mac = null;
		try {
			mac = Mac.getInstance(method);
			mac.init(k);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		}

		return mac.doFinal(data);
	}

	/*
	 * 获取随机字符
	 */
	public static int getRandomString(int length) {
		// StringBuffer buffer=new
		// StringBuffer("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");
		StringBuffer buffer = new StringBuffer("123456789");
		StringBuffer sb = new StringBuffer();
		Random r = new Random();
		int range = buffer.length();
		for (int i = 0; i < length; i++) {
			sb.append(buffer.charAt(r.nextInt(range)));
		}
		return Integer.parseInt(sb.toString());
	}

	/*
	 * 获取随机字符
	 */
	public static String getRandomStringToken(int length) {
		StringBuffer buffer = new StringBuffer(
				"0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");
		// StringBuffer buffer=new StringBuffer("0123456789");
		StringBuffer sb = new StringBuffer();
		Random r = new Random();
		int range = buffer.length();
		for (int i = 0; i < length; i++) {
			sb.append(buffer.charAt(r.nextInt(range)));
		}
		return sb.toString();
	}

	/*
	 * 获得用户登录令牌
	 */
	public static String getToken(String telphone) {
		// 获取当前系统时间
		Long test = System.currentTimeMillis();

		String nonce = BASE64Encoder.getRandomStringToken(8);

		String version = "1.0";

		String baseString = telphone + test + "*" + nonce + version;
		String secret = telphone;
		StringBuffer term = new StringBuffer("");
		term.append(new BASE64Encoder().encode(BASE64Encoder.encodeHmacSHA(
				baseString.getBytes(), secret.getBytes())));
		return term.toString().replace("/", "+");
	}

	/*
	 * 重新设置图片名称
	 */
	public static String getPicName(String picName) {
		String houZui = picName.substring(picName.lastIndexOf("."),
				picName.length());
		// 获取当前系统时间
		Long test = System.currentTimeMillis();

		String nonce = BASE64Encoder.getRandomStringToken(8);

		String version = "1.0";

		String baseString = picName + test + "*" + nonce + version;
		String secret = picName;
		StringBuffer term = new StringBuffer("");
		term.append(new BASE64Encoder().encode(BASE64Encoder.encodeHmacSHA(
				baseString.getBytes(), secret.getBytes())));
		return term.toString().replace("/", "+") + houZui;
	}
	
	public static String getMD5(String value) {
		MessageDigest md = null;
		try {
			byte[] valueByte = value.getBytes();
			md = MessageDigest.getInstance("MD5");
			md.update(valueByte);
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		}
		if(md != null){
			return toHex(md.digest());
		}else{
			return null;
		}
	}
	// 将传递进来的字节数组转换成十六进制的字符串形式并返回
	private static String toHex(byte[] buffer) {
		StringBuffer sb = new StringBuffer(buffer.length * 2);
		for (int i = 0; i < buffer.length; i++) {
			sb.append(Character.forDigit((buffer[i] & 0xf0) >> 4, 16));
			sb.append(Character.forDigit(buffer[i] & 0x0f, 16));
		}
		return sb.toString();
	}

}
