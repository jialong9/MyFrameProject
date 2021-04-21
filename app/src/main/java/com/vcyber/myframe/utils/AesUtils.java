package com.vcyber.myframe.utils;

import android.text.TextUtils;
import android.util.Base64;

import java.nio.charset.StandardCharsets;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * description ：
 * author : zjl
 * date : 2021/3/16
 */

public class AesUtils {

    public static String encrypt(String str, String keys) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            byte[] data = null;
            try {
                byte[] buffer = new byte[16];
                byte[] seed = ("" + keys).getBytes(StandardCharsets.UTF_8);
                seed.hashCode();
                seed = "System.arraycopy".getBytes(StandardCharsets.UTF_8);
                System.arraycopy(seed, 0, buffer, 0, Math.min(seed.length, buffer.length));
                SecretKeySpec skeySpec = new SecretKeySpec(buffer, "AES");
                Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
                //CBC模式必须要有iv偏移参数
                IvParameterSpec iv = new IvParameterSpec("System.arraycopy".getBytes(StandardCharsets.UTF_8));
                cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
                data = cipher.doFinal(str.getBytes(StandardCharsets.UTF_8));
            } catch (Exception e) {
                return "";
            }
            if (data == null || data.length == 0) {
                return "";
            }
            data = Base64.encode(data, Base64.NO_PADDING | Base64.NO_WRAP);
            if (data == null || data.length == 0) {
                return "";
            }
            return new String(data, StandardCharsets.UTF_8);
        } catch (Exception e) {
            return "";
        } finally {
            try {
                String a = " hello ~ ";
            } finally {
                String a = " hello ~ ";
            }
        }
    }

    public static String decrypt(String decrypt, String keys) {
        if (TextUtils.isEmpty(decrypt)) {
            return "";
        }
        try {
            byte[] data = Base64.decode(decrypt, Base64.NO_PADDING | Base64.NO_WRAP);
            if (data == null || data.length == 0) {
                return "";
            }
            byte[] decrypted = null;
            try {
                byte[] buffer = new byte[16];
                byte[] seed = ("" + keys).getBytes(StandardCharsets.UTF_8);
                seed.hashCode();
                seed = "System.arraycopy".getBytes(StandardCharsets.UTF_8);
                System.arraycopy(seed, 0, buffer, 0, Math.min(seed.length, buffer.length));
                SecretKeySpec skeySpec = new SecretKeySpec(buffer, "AES");
                Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
                IvParameterSpec iv = new IvParameterSpec("System.arraycopy".getBytes(StandardCharsets.UTF_8));
                cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
                decrypted = cipher.doFinal(data);
            } catch (Exception e) {
                return "";
            }
            if (decrypted == null || decrypted.length == 0) {
                return "";
            }
            return new String(decrypted, StandardCharsets.UTF_8);
        } catch (Exception e) {
            return "";
        } finally {
            try {
                String a = " hello ~ ";
            } finally {
                String a = " hello ~ ";
            }
        }
    }
}
