package com.sik.encryptutils.utils.encryptAlgorithm;

import cn.hutool.crypto.symmetric.AES;
import com.sik.encryptutils.utils.EncryptUtils;
import com.sik.encryptutils.utils.IEncrypt;

import java.nio.charset.Charset;
import java.util.Arrays;

public class AESSecurity implements IEncrypt {
    @Override
    public String encryptBase64(String mode, String padding, String key, String iv, String text) {
        AES aes = new AES(EncryptUtils.getMode(mode), EncryptUtils.getPadding(padding), key.getBytes(Charset.defaultCharset()));
        if (EncryptUtils.isNeedIV(mode)) {
            aes.setIv(Arrays.copyOfRange(iv.getBytes(Charset.defaultCharset()),0,16));
        }
        return aes.encryptBase64(text);
    }

    @Override
    public String decryptBase64(String mode, String padding, String key, String iv, String text) {
        AES aes = new AES(EncryptUtils.getMode(mode), EncryptUtils.getPadding(padding), key.getBytes(Charset.defaultCharset()));
        if (EncryptUtils.isNeedIV(mode)) {
            aes.setIv(Arrays.copyOfRange(iv.getBytes(Charset.defaultCharset()),0,16));
        }
        return aes.decryptStr(text);
    }
}
