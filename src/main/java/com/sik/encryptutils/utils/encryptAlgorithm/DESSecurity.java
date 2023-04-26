package com.sik.encryptutils.utils.encryptAlgorithm;

import cn.hutool.crypto.symmetric.DES;
import com.sik.encryptutils.utils.EncryptUtils;
import com.sik.encryptutils.utils.IEncrypt;

import java.nio.charset.Charset;
import java.util.Arrays;

public class DESSecurity implements IEncrypt {
    @Override
    public String encryptBase64(String mode, String padding, String key, String iv, String text) {
        DES des = new DES(EncryptUtils.getMode(mode), EncryptUtils.getPadding(padding), key.getBytes(Charset.defaultCharset()));
        if (EncryptUtils.isNeedIV(mode)) {
            des.setIv(Arrays.copyOfRange(iv.getBytes(Charset.defaultCharset()),0,16));
        }
        return des.encryptBase64(text);
    }

    @Override
    public String decryptBase64(String mode, String padding, String key, String iv, String text) {
        DES des = new DES(EncryptUtils.getMode(mode), EncryptUtils.getPadding(padding), key.getBytes(Charset.defaultCharset()));
        if (EncryptUtils.isNeedIV(mode)) {
            des.setIv(Arrays.copyOfRange(iv.getBytes(Charset.defaultCharset()),0,16));
        }
        return des.decryptStr(text);
    }
}
