package com.sik.encryptutils.utils.encryptAlgorithm;

import cn.hutool.crypto.symmetric.AES;
import com.sik.encryptutils.utils.EncryptUtils;
import com.sik.encryptutils.utils.IEncrypt;

import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;

public class AESSecurity implements IEncrypt {
    @Override
    public String encryptBase64(String mode, String padding, String key, String iv, String text) {
        AES aes = new AES(EncryptUtils.getMode(mode), EncryptUtils.getPadding(padding),new SecretKeySpec(key.getBytes(Charset.defaultCharset()),"AES"));
        if (EncryptUtils.isNeedIV(mode)) {
            aes.setIv(new IvParameterSpec(iv.getBytes(Charset.defaultCharset())));
        }
        return aes.encryptBase64(text);
    }

    @Override
    public String decryptBase64(String mode, String padding, byte[] key, String iv, String text) {
        AES aes = new AES(EncryptUtils.getMode(mode), EncryptUtils.getPadding(padding), key);
        if (EncryptUtils.isNeedIV(mode)) {
            aes.setIv(new IvParameterSpec(iv.getBytes(Charset.defaultCharset())));
        }
        return aes.decryptStr(text);
    }
}
