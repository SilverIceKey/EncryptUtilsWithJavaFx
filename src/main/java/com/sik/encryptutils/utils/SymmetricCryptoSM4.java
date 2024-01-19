package com.sik.encryptutils.utils;

import cn.hutool.crypto.symmetric.SM4;

import java.io.InputStream;
import java.io.OutputStream;

public class SymmetricCryptoSM4 implements SymmetricCryptoFacade{

    private final SM4 sm4;

    /**
     * 构造
     * @param key 密钥，支持密钥长度：128位
     */
    public SymmetricCryptoSM4(byte[] key) {
        this.sm4 = new SM4(key);
    }

    /**
     * 加密
     *
     * @param data 被加密的bytes
     * @return 加密后的bytes
     */
    @Override
    public byte[] encrypt(byte[] data) {
        return this.sm4.encrypt(data);
    }

    /**
     * 加密，针对大数据量，可选结束后是否关闭流
     *
     * @param data    被加密的字符串
     * @param out     输出流，可以是文件或网络位置
     * @param isClose 是否关闭流
     */
    @Override
    public void encrypt(InputStream data, OutputStream out, boolean isClose) {

        this.sm4.encrypt(data, out, isClose);
    }

    /**
     * 解密
     *
     * @param bytes 被解密的bytes
     * @return 解密后的bytes
     */
    @Override
    public byte[] decrypt(byte[] bytes) {

        return this.sm4.decrypt(bytes);
    }

    /**
     * 解密，针对大数据量，结束后不关闭流
     *
     * @param data    加密的字符串
     * @param out     输出流，可以是文件或网络位置
     * @param isClose 是否关闭流，包括输入和输出流
     */
    @Override
    public void decrypt(InputStream data, OutputStream out, boolean isClose) {

        this.sm4.decrypt(data, out, isClose);
    }
}
