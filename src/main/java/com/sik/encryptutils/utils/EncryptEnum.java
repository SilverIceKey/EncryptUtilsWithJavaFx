package com.sik.encryptutils.utils;

import com.sik.encryptutils.utils.encryptAlgorithm.AESSecurity;
import com.sik.encryptutils.utils.encryptAlgorithm.DESSecurity;
import com.sik.encryptutils.utils.encryptAlgorithm.DESedeSecurity;

public enum EncryptEnum {
    ALGORITHM_AES("AES", new AESSecurity()),
    ALGORITHM_DES("DES", new DESSecurity()),
    ALGORITHM_TRIPLE_DES("DESede", new DESedeSecurity());

    EncryptEnum(String algorithm, IEncrypt iEncrypt) {
        this.algorithm = algorithm;
        this.iEncrypt = iEncrypt;
    }

    private String algorithm;
    private IEncrypt iEncrypt;

    public static IEncrypt getIEncrypt(String algorithm) {
        for (EncryptEnum value : values()) {
            if (value.algorithm.equals(algorithm)) {
                return value.iEncrypt;
            }
        }
        return null;
    }
}
