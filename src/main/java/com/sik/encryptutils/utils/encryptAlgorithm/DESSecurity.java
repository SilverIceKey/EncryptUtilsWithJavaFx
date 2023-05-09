package com.sik.encryptutils.utils.encryptAlgorithm;

import cn.hutool.crypto.symmetric.DES;
import com.sik.encryptutils.utils.EncryptUtils;
import com.sik.encryptutils.utils.IEncrypt;

import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.util.Arrays;

public class DESSecurity implements IEncrypt {
    @Override
    public String encryptBase64(String mode, String padding, String key, String iv, String text) {
        DES des = new DES(EncryptUtils.getMode(mode), EncryptUtils.getPadding(padding), new SecretKeySpec(key.getBytes(Charset.defaultCharset()),"DES"));
        if (EncryptUtils.isNeedIV(mode)) {
            des.setIv(new IvParameterSpec(iv.getBytes(Charset.defaultCharset())));
        }
        return des.encryptBase64(text);
    }

    @Override
    public String decryptBase64(String mode, String padding, byte[] key, String iv, String text) {
        DES des = new DES(EncryptUtils.getMode(mode), EncryptUtils.getPadding(padding), key);
        if (EncryptUtils.isNeedIV(mode)) {
            des.setIv(new IvParameterSpec(iv.getBytes(Charset.defaultCharset())));
        }
        return des.decryptStr(text);
    }
}
