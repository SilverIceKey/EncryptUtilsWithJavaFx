package com.sik.encryptutils.utils.encryptAlgorithm;

import cn.hutool.crypto.symmetric.DESede;
import com.sik.encryptutils.utils.EncryptUtils;
import com.sik.encryptutils.utils.IEncrypt;

import java.nio.charset.Charset;
import java.util.Arrays;

public class DESedeSecurity implements IEncrypt {
    @Override
    public String encryptBase64(String mode, String padding, String key, String iv, String text) {
        DESede deSede = new DESede(EncryptUtils.getMode(mode), EncryptUtils.getPadding(padding), key.getBytes(Charset.defaultCharset()));
        if (EncryptUtils.isNeedIV(mode)) {
            deSede.setIv(Arrays.copyOfRange(iv.getBytes(Charset.defaultCharset()),0,16));
        }
        return deSede.encryptBase64(text);
    }

    @Override
    public String decryptBase64(String mode, String padding, String key, String iv, String text) {
        DESede deSede = new DESede(EncryptUtils.getMode(mode), EncryptUtils.getPadding(padding), key.getBytes(Charset.defaultCharset()));
        if (EncryptUtils.isNeedIV(mode)) {
            deSede.setIv(Arrays.copyOfRange(iv.getBytes(Charset.defaultCharset()),0,16));
        }
        return deSede.decryptStr(text);
    }
}
