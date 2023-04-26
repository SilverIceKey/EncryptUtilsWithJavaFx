package com.sik.encryptutils.utils;

import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;

/**
 * 加解密工具
 */
public class EncryptUtils {
    /**
     * 加密
     *
     * @param algorithm 算法
     * @param mode      加密模式例如ECB CBC
     * @param padding   填充模式例如zero pkcs7
     * @param key       密钥
     * @param iv        偏移
     * @param text      需要加密的文本
     * @return 返回加密结果Base64
     */
    public static String encryptBase64(String algorithm, String mode, String padding, String key, String iv, String text) {
        try {
            return EncryptEnum.getIEncrypt(algorithm).encryptBase64(mode, padding, key, iv, text);
        } catch (Exception e) {
            return "加密异常";
        }
    }

    /**
     * 解密
     *
     * @param algorithm 算法
     * @param mode      加密模式例如ECB CBC
     * @param padding   填充模式例如zero pkcs7
     * @param key       密钥
     * @param iv        偏移
     * @param text      需要解密的文本Base64
     * @return 返回解密结果
     */
    public static String decryptBase64(String algorithm, String mode, String padding, String key, String iv, String text) {
        try {
            return EncryptEnum.getIEncrypt(algorithm).decryptBase64(mode, padding, key, iv, text);
        } catch (Exception e) {
            return "加密异常";
        }
    }

    /**
     * 根据字符串加密获取加密模式
     * @param mode
     * @return
     */
    public static Mode getMode(String mode) {
        switch (mode) {
            case "ECB":
                return Mode.ECB;
            case "CBC":
                return Mode.CBC;
        }
        return Mode.ECB;
    }

    /**
     * 是否需要偏移
     * @param mode
     * @return
     */
    public static boolean isNeedIV(String mode){
        return "CBC".equals(mode);
    }

    /**
     * 根据字符串填充获取填充方式
     * @param padding
     * @return
     */
    public static Padding getPadding(String padding) {
        switch (padding) {
            case "ZeroPadding":
                return Padding.NoPadding;
            case "Pkcs7Padding":
                return Padding.PKCS5Padding;
        }
        return Padding.PKCS5Padding;
    }
}
