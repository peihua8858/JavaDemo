package com.fz.demo.encrypt;

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

public class EncrityTest {
	public static final String TRANSFORMATION = "RSA/ECB/PKCS1Padding";
	public static final String RSA = "RSA";

	public static void main(String[] args) throws Exception {
		String content = "dingpeihua";
		KeyPair keyPair = generateRSAKeyPair(4096);
		
//		String encryptContent = encryptByPublicKey(content, getPublicKey(keyPair));
//		System.out.println("加密：" + encryptContent);
//		String decryptContent = decryptByPrivateKey(encryptContent, getPrivateKey(keyPair));
//		System.out.println("解密：" + decryptContent);
		
		String encryptContent = encryptByPrivateKey(content, getPrivateKey(keyPair));
		System.out.println("加密：" + encryptContent);
		String decryptContent = decryptByPublicKey(getPublicKey(keyPair),encryptContent );
		System.out.println("解密：" + decryptContent);
		
		
//		String encryptContent1 = encryptByPrivateKey(PRIVATE_KEY, content);//(content, getPrivateKey(keyPair));
//		System.out.println("加密：" + encryptContent1);
//		String decryptContent1 = decryptByPublicKey(PUBLIC_KEY, encryptContent1);//(encryptContent, getPublicKey(keyPair));
//		System.out.println("解密：" + decryptContent1);

	}

	/** 生成密钥对，即公钥和私钥。key长度是512-2048，一般为1024 */
	public static KeyPair generateRSAKeyPair(int keyLength) throws NoSuchAlgorithmException {
		KeyPairGenerator kpg = KeyPairGenerator.getInstance(RSA);
		kpg.initialize(keyLength);
		return kpg.genKeyPair();
	}

	/** 获取公钥，打印为48-12613448136942-12272-122-913111503-126115048-12...等等一长串用-拼接的数字 */
	public static byte[] getPublicKey(KeyPair keyPair) {
		RSAPublicKey rsaPublicKey = (RSAPublicKey) keyPair.getPublic();
		return rsaPublicKey.getEncoded();
	}

	/** 获取私钥，同上 */
	public static byte[] getPrivateKey(KeyPair keyPair) {
		RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();
		return rsaPrivateKey.getEncoded();
	}

	/** 使用公钥加密 */
	public static String encryptByPublicKey(String data, byte[] publicKey) throws Exception {
		// 得到公钥对象
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKey);
		KeyFactory keyFactory = KeyFactory.getInstance(RSA);
		PublicKey pubKey = keyFactory.generatePublic(keySpec);
		// 加密数据
		Cipher cp = Cipher.getInstance(TRANSFORMATION);
		cp.init(Cipher.ENCRYPT_MODE, pubKey);
		byte plaintext[] = data.getBytes("UTF-8");
		byte[] output = cp.doFinal(plaintext);
		return new String(Base64.encode(output, Base64.NO_WRAP));
	}

	public static String decryptByPublicKey(byte[] publicKey, String data) throws Exception {
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(publicKey);
		KeyFactory keyFactory = KeyFactory.getInstance(RSA);
		Key publicK = keyFactory.generatePublic(x509KeySpec);
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, publicK);
		byte[] arr = cipher.doFinal(Base64.decode(data.getBytes("UTF-8"), Base64.NO_WRAP));
		return new String(arr, "UTF-8");
	}
	public static String decryptByPublicKey( String publicKey,String data)
            throws Exception {
        byte[] keyBytes = Base64.decode(publicKey,Base64.NO_WRAP);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA);
        Key publicK = keyFactory.generatePublic(x509KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, publicK);
        byte[] arr = cipher.doFinal(Base64.decode(data.getBytes("UTF-8"), Base64.NO_WRAP));
		return new String(arr, "UTF-8");
    }

	/** 使用私钥解密 */
	public static String decryptByPrivateKey(String encrypted, byte[] privateKey) throws Exception {
		// 得到私钥对象
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKey);
		KeyFactory kf = KeyFactory.getInstance(RSA);
		PrivateKey keyPrivate = kf.generatePrivate(keySpec);
		// 解密数据
		Cipher cp = Cipher.getInstance(TRANSFORMATION);
		cp.init(Cipher.DECRYPT_MODE, keyPrivate);
		byte[] arr = cp.doFinal(Base64.decode(encrypted, Base64.NO_WRAP));
		return new String(arr, "UTF-8");
	}
	
	/** 使用私钥加密 */
	public static String encryptByPrivateKey(String encrypted, byte[] privateKey) throws Exception {
		// 得到私钥对象
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKey);
		KeyFactory kf = KeyFactory.getInstance(RSA);
		PrivateKey keyPrivate = kf.generatePrivate(keySpec);
		// 加密数据
		Cipher cp = Cipher.getInstance(TRANSFORMATION);
		cp.init(Cipher.ENCRYPT_MODE, keyPrivate);
		byte plaintext[] = encrypted.getBytes("UTF-8");
		byte[] output = cp.doFinal(plaintext);
		return new String(Base64.encode(output, Base64.NO_WRAP));
				
	}

	public static String encryptByPrivateKey(String key, String data) throws NoSuchAlgorithmException, InvalidKeySpecException,
			NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, IOException {
		byte[] decode = Base64.decode(key, Base64.NO_WRAP);
		PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(decode);
		KeyFactory kf = KeyFactory.getInstance(RSA);
		PrivateKey generatePrivate = kf.generatePrivate(pkcs8EncodedKeySpec);
		Cipher ci = Cipher.getInstance(TRANSFORMATION);
		ci.init(Cipher.ENCRYPT_MODE, generatePrivate);

		byte[] bytes = data.getBytes();
		byte[] encryptedData = ci.doFinal(bytes);// bops.toByteArray();
		String encodeToString = new String(Base64.encode(encryptedData, Base64.NO_WRAP), "UTF-8");
		return encodeToString;
	}
	
	static String PUBLIC_KEY = "MIICIjANBgkqhkiG9w0BAQEFAAOCAg8AMIICCgKCAgEAu/zw3WHLALniW/hOl1Fk\n"+
            "bGnnRPtfFf7/TWXyeKDJGL6OcOt78mSNHhe0bUATHqV7Xs2TPsVjR9m5sPCcSBDm\n"+
            "jatHXBral7efd7jqSwXZNG7B5oUVa3QBK0qcxydAPBF2M7CgwaogjddNKvYnUXJP\n"+
            "wpxt/h6HvlGKsCOiKnypCU0iGA15Q8L9qja0DEYTC3un7PuDGH8iRAWMM7xTSIZ6\n"+
            "glu1S+6Irp8238az299wr4044uTVZo68ii2b/aLT2GAihuDUmA/TGT6vgtV5y2dB\n"+
            "9AGDWmoKOHIvFWB/H6JJrkx0HAjMfkrdUjvm9TdjP2tjFI0OhAfjphJY41d4ftDW\n"+
            "okVm3VxZ3oozBfW/KMKOvj2QFL9NzGqOPALRlAcBOPrSqoTyvs6WLf1HqxHrZ+Bw\n"+
            "zTRRh5Zu74omY+jyd8RK9it7Fc8h6dNgyvcvEtyLm0L2pE2IiHeMvZmb/et0elkq\n"+
            "5gXm74/g9696S+xcrqJjV9iJ/1frsj/ifsjfjZMwvRaBq8INO7k7BJlyxHqqabsE\n"+
            "Z7jrPJzg5In/jBivJ9m2FxHyg3aDvXGX9iNMRX98b3wjaWZJpInIXaW9Lzj20T8x\n"+
            "hRlg4ZFx73q1YjbuzGzRRkqcivSHZTmivsqPKRz+OI91kA6+YPB59sVxK1dgvLuU\n"+
            "Wh+beg9ukosj/wOsVM6vC40CAwEAAQ==";

	static String PRIVATE_KEY = "MIIJQQIBADANBgkqhkiG9w0BAQEFAASCCSswggknAgEAAoICAQC7/PDdYcsAueJb\n"+
            "+E6XUWRsaedE+18V/v9NZfJ4oMkYvo5w63vyZI0eF7RtQBMepXtezZM+xWNH2bmw\n"+
            "8JxIEOaNq0dcGtqXt593uOpLBdk0bsHmhRVrdAErSpzHJ0A8EXYzsKDBqiCN100q\n"+
            "9idRck/CnG3+Hoe+UYqwI6IqfKkJTSIYDXlDwv2qNrQMRhMLe6fs+4MYfyJEBYwz\n"+
            "vFNIhnqCW7VL7oiunzbfxrPb33CvjTji5NVmjryKLZv9otPYYCKG4NSYD9MZPq+C\n"+
            "1XnLZ0H0AYNaago4ci8VYH8fokmuTHQcCMx+St1SO+b1N2M/a2MUjQ6EB+OmEljj\n"+
            "V3h+0NaiRWbdXFneijMF9b8owo6+PZAUv03Mao48AtGUBwE4+tKqhPK+zpYt/Uer\n"+
            "Eetn4HDNNFGHlm7viiZj6PJ3xEr2K3sVzyHp02DK9y8S3IubQvakTYiId4y9mZv9\n"+
            "63R6WSrmBebvj+D3r3pL7FyuomNX2In/V+uyP+J+yN+NkzC9FoGrwg07uTsEmXLE\n"+
            "eqppuwRnuOs8nODkif+MGK8n2bYXEfKDdoO9cZf2I0xFf3xvfCNpZkmkichdpb0v\n"+
            "OPbRPzGFGWDhkXHverViNu7MbNFGSpyK9IdlOaK+yo8pHP44j3WQDr5g8Hn2xXEr\n"+
            "V2C8u5RaH5t6D26SiyP/A6xUzq8LjQIDAQABAoICACUxJ95EyMr9+8DCYZoiakHg\n"+
            "ufa13NwXNR90+gHPnbmdicThcgZEyn6XpgSvVpkLmFrrRSR4HOpMMybEk8bWQSbq\n"+
            "KhYY4gJ+O7iOBxyMrapn90jKL7CwpMKR4m6sX1MDCWW0njlylTo6zHOhe6nrechA\n"+
            "9qn+FFEdBNlQU3cgbrj+X65ui/1SCKmyZPDAtGfjMRf7srYYAQmAJ3rc2YVRxc8g\n"+
            "ob/CE5yot0ySnWb0z/CLeiO5rla6Cqcz2tbVbiAy9OHGfYkQKG2oL0UtQwlxBmVm\n"+
            "Hzy2GguM26/ZBY2WqAkp6xbYzsx1t0/bthfxu1sCvfmE5shMX0et/ZQM7DP60Vu2\n"+
            "LMs3vVrY1/YQMxXVgV6+XoH2axecF6mFF60Mgr0V2kKG2ma6X14ZaqnouMp066La\n"+
            "tOIeSCtIDE3+l9SHMIhT6L0UPXJSfkZTVTZbJ7I+Nqk5d3qSmrBH63YMMZZ+JW1d\n"+
            "tymk//UzAILtJpBdLrpn1QHdmZ+ZaDS5YbLf6hhVImZlwt6/4dV0cUO0gZY5fnCt\n"+
            "efqzxvRFGaVYCm0mjw5acUIEw6/TmqF3eppCyAQUp6k6/Z8sVLBwPLBy+bzAJGCu\n"+
            "YvP36a3FYiVl6QQL/HLD2givVWCro3pvRwmVnbENapsvtWnaPKPX03bVMvzJdR0F\n"+
            "650Og9+JuPC6SGVhLy/pAoIBAQDym3hv5Wj6Tlk0kr8/g+0ncbh/q05aTAvaAkhb\n"+
            "i9bcAIkuqb3lflRzBodQ+xfsyYBc2L5J6H/IdOMMCZWH8Ls2eFcBqjM/xETuxHVn\n"+
            "qpjOKxmjcGRtFZoI5Yq45GUIqXvyYjMbtufaxLqdJ4xCx0FOAr0x3hPokgCUJJoY\n"+
            "NVveUFtO/rBpWUZS8HIU2PLPI/u6fXfhp3li4KOkoeNfIozuVtyz4kd95Xjah8xs\n"+
            "Xaruj4RaCUEW43lJP9Wb1NekFu4BCvBgrCcFYuFL8OK+8NP/UfvfMHrAJK4X6cya\n"+
            "HgYYowFMw9woPhtvWQQvMOQaBuYXBFatjHsjZXxZH7I79GDHAoIBAQDGXZckR4zG\n"+
            "7GWD57PbHa/h2fX05x8wsUZ0hh8pyNREqcQfp7QrlsEpGWN/zzK8hs5a7qQyYORm\n"+
            "SX3N/JsE2d1cr1PPgrgeKaMajO+pwI/7jKYG6pxHpY8oZAoWakeglqshNdobIIB4\n"+
            "jNtlNl4z4APuZ6Jylz/nubnDCUo+MOGH53ssEnGC97YsycrC57ilu6e9nb4kRNRj\n"+
            "EKTlTsEfZQtaduELQTeTOCJ/2gnZdl/YfcikqG+SkOzzjE1rLUYiwxGcY/QOWJry\n"+
            "gCLbTpZhJFnOQb4WIMcm/9Isb+C0K8OFUj/k6d0LZJtRNiaXTzMrAwnowCWXVNeZ\n"+
            "zi2f56lLXQULAoIBAFGk3oARnSV3Tm/x3qiaVioLDELyvHiI1Qg18nHUeqcpCwGI\n"+
            "IB7mR7c6ADnRdni6Sxdz3X3z32TvaSU46HiKHAa1stodlAemnmgX90a+05O2T3eA\n"+
            "MFvBhEfwL6IPQq0KlGq6f2aZRCeMQxz2HS6v7Zs6Mr9gdCS1VbqFItJ4yoTjWAca\n"+
            "kPWzFIZ+4nV/mR76hhF5femTubb70DZsQJ9YekMPOlDTGqoUmno4byQc1ZRY+fpu\n"+
            "2NfCD6QF9nXtx/Axw0DYlJKD/NHEN/udgkEtvLpUtcWjg/u7nFbx2GgZKIYL/Tj/\n"+
            "RMzKMeNj+pODCZB0t+QRErZu61W7JoC7uPiSQisCggEAPlrDHQNkzE8ZJhqaneE6\n"+
            "HXu/ZgmU82B2bkCO4TqhByh6Xxz/dMhB1eKGNXHzV/HkQg9MDA4LHeAyLKMgM6ks\n"+
            "fWsbpFpJ+ClMfRcaazj9yawPsJLlJ1xyoNa5mQw1kdRq3Nipw+c2my5j9O/fMt1T\n"+
            "PwjStsS2sOff7eAot130Nx1byaHOjsXBPVj9ZMEkeOyBMQi58Xq1bKKZwv87k9vW\n"+
            "csx1t2z7xEpcBeaLPBGK1WnMvX24/NCTu4c5+tvTdS/uhEOjPzM5GcuPqXKNsOGV\n"+
            "XVGrKBeLosW90nwknV44attM/G9LLb6lWCT2go/B3N5qqgBkkKp5z35RNzfCLs+B\n"+
            "uwKCAQA45O8RcrkM06MgFC+Gz2n74k1IZSsS++ZpdRDWZTQpWQdH0u85vz2tqE2b\n"+
            "XTCgg0iFoaVpE1P7p7KtWO0iBaCRDMh0Du1n8F3aOjnXtBGVGV2CUlpbeYsY/vUK\n"+
            "K87GdPfIj85f69G0mbQ2iHtL9577/1azbmGd5i24Q2M1ZcK/D9OlMuUk0acQaAdJ\n"+
            "bouDM25dT4MyKmv8iPfNy3RyuOxWDrk0/GEjPOr+NFiP0fJh8BOcysiEPcKZrOPp\n"+
            "QLNeOlD1/DfCOdBrDO0yqctsSQglHqXcufeXX391WMTfppfA1Sdibvu86T8WR3UY\n"+
            "UYSxn9iEKlU6clYgl/8TcLXzykOf";
}
