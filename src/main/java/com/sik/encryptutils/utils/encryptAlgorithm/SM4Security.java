package com.sik.encryptutils.utils.encryptAlgorithm;

import cn.hutool.crypto.symmetric.SymmetricEncryptor;
import com.sik.encryptutils.utils.IEncrypt;
import com.sik.encryptutils.utils.SymmetricCryptoAlgorithm;
import com.sik.encryptutils.utils.SymmetricCryptoFacade;

public class SM4Security implements IEncrypt {
    @Override
    public String encryptBase64(String mode, String padding, String key, String iv, String text) {
        return SymmetricCryptoFacade.getInstance(SymmetricCryptoAlgorithm.SM4,key.getBytes()).encryptHex(text);
    }

    @Override
    public String decryptBase64(String mode, String padding, byte[] key, String iv, String text) {
        return SymmetricCryptoFacade.getInstance(SymmetricCryptoAlgorithm.SM4,key).decryptStr(text);
    }
}
