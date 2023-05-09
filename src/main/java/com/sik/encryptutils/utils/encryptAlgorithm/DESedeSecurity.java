package com.sik.encryptutils.utils.encryptAlgorithm;

import cn.hutool.crypto.symmetric.DESede;
import com.sik.encryptutils.utils.EncryptUtils;
import com.sik.encryptutils.utils.IEncrypt;

import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.util.Arrays;

public class DESedeSecurity implements IEncrypt {
    @Override
    public String encryptBase64(String mode, String padding, String key, String iv, String text) {
        DESede deSede = new DESede(EncryptUtils.getMode(mode), EncryptUtils.getPadding(padding), new SecretKeySpec(key.getBytes(Charset.defaultCharset()),"DESede"));
        if (EncryptUtils.isNeedIV(mode)) {
            deSede.setIv(new IvParameterSpec(iv.getBytes(Charset.defaultCharset())));
        }
        return deSede.encryptBase64(text);
    }

    @Override
    public String decryptBase64(String mode, String padding, byte[] key, String iv, String text) {
        DESede deSede = new DESede(EncryptUtils.getMode(mode), EncryptUtils.getPadding(padding), key);
        if (EncryptUtils.isNeedIV(mode)) {
            deSede.setIv(new IvParameterSpec(iv.getBytes(Charset.defaultCharset())));
        }
        return deSede.decryptStr(text);
    }
}
