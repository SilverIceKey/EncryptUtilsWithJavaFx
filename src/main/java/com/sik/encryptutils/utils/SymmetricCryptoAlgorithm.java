package com.sik.encryptutils.utils;

/**
 * 对称加密算法类型
 */
public enum SymmetricCryptoAlgorithm {

    SM4("SM4");

    private final String value;

    SymmetricCryptoAlgorithm(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public boolean equalTo(SymmetricCryptoAlgorithm symmetricCryptoAlgorithm) {
        return this.getValue().equals(symmetricCryptoAlgorithm.getValue());
    }
}
