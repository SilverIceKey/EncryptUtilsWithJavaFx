package com.sik.encryptutils.utils;

public interface IEncrypt {
    /**
     * 加密
     * @param mode 加密模式例如ECB CBC
     * @param padding 填充模式例如zero pkcs7
     * @param key 密钥
     * @param iv 偏移
     * @param text 需要加密的文本
     * @return 返回加密结果Base64
     */
    public String encryptBase64(String mode, String padding, String key, String iv, String text);

    /**
     * 解密
     * @param mode 加密模式例如ECB CBC
     * @param padding 填充模式例如zero pkcs7
     * @param key 密钥
     * @param iv 偏移
     * @param text 需要解密的文本Base64
     * @return 返回解密结果
     */
    public String decryptBase64(String mode, String padding, byte[] key, String iv, String text);
}
