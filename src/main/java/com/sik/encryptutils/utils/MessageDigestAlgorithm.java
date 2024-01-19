package com.sik.encryptutils.utils;

/**
 * 信息摘要算法类型
 */
public enum MessageDigestAlgorithm {

    SM3("SM3");

    private final String value;

    MessageDigestAlgorithm(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public boolean equalTo(MessageDigestAlgorithm messageDigestAlgorithm) {
        return this.getValue().equals(messageDigestAlgorithm.getValue());
    }
}
