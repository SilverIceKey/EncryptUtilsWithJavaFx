package com.sik.encryptutils.utils;

import cn.hutool.core.io.IORuntimeException;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.HexUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * 对称加密能力定义
 * @author ntaibai
 */
public interface SymmetricCryptoFacade {

    /**
     * 返回实现指定对称加密算法的SymmetricCryptoFacade对象。
     * @param symmetricCryptoAlgorithm  对称加密算法类型
     * @param key 密钥，支持密钥长度：128位
     * @return 对称加密能力实例
     */
    static SymmetricCryptoFacade getInstance(SymmetricCryptoAlgorithm symmetricCryptoAlgorithm, byte[] key) {

        if (SymmetricCryptoAlgorithm.SM4.equalTo(symmetricCryptoAlgorithm)) {
            return new SymmetricCryptoSM4(key);
        }
        return null;
    }

    /**
     * 加密
     *
     * @param data 被加密的bytes
     * @return 加密后的bytes
     */
    byte[] encrypt(byte[] data);

    /**
     * 加密，针对大数据量，可选结束后是否关闭流
     *
     * @param data    被加密的字符串
     * @param out     输出流，可以是文件或网络位置
     * @param isClose 是否关闭流
     * @throws IORuntimeException IO异常
     */
    void encrypt(InputStream data, OutputStream out, boolean isClose);

    /**
     * 加密
     *
     * @param data 数据
     * @return 加密后的Hex
     */
    default String encryptHex(byte[] data) {
        return HexUtil.encodeHexStr(encrypt(data));
    }



    /**
     * 加密，使用UTF-8编码
     *
     * @param data 被加密的字符串
     * @return 加密后的bytes
     */
    default byte[] encrypt(String data) {
        return encrypt(StrUtil.bytes(data, CharsetUtil.CHARSET_UTF_8));
    }

    /**
     * 加密，使用UTF-8编码
     *
     * @param data 被加密的字符串
     * @return 加密后的Hex
     */
    default String encryptHex(String data) {
        return HexUtil.encodeHexStr(encrypt(data));
    }

    /**
     * 加密，加密后关闭流
     *
     * @param data 被加密的字符串
     * @return 加密后的bytes
     * @throws IORuntimeException IO异常
     */
    default byte[] encrypt(InputStream data) throws IORuntimeException {
        return encrypt(IoUtil.readBytes(data));
    }

    /**
     * 加密
     *
     * @param data 被加密的字符串
     * @return 加密后的Hex
     */
    default String encryptHex(InputStream data) {
        return HexUtil.encodeHexStr(encrypt(data));
    }


    /**
     * 解密
     *
     * @param bytes 被解密的bytes
     * @return 解密后的bytes
     */
    byte[] decrypt(byte[] bytes);

    /**
     * 解密，针对大数据量，结束后不关闭流
     *
     * @param data    加密的字符串
     * @param out     输出流，可以是文件或网络位置
     * @param isClose 是否关闭流，包括输入和输出流
     * @throws IORuntimeException IO异常
     */
    void decrypt(InputStream data, OutputStream out, boolean isClose);

    /**
     * 解密为字符串，默认UTF-8编码
     *
     * @param bytes 被解密的bytes
     * @return 解密后的String
     */
    default String decryptStr(byte[] bytes) {
        return StrUtil.str(decrypt(bytes), CharsetUtil.CHARSET_UTF_8);
    }


    /**
     * 解密Hex（16进制）或Base64表示的字符串
     *
     * @param data 被解密的String，必须为16进制字符串或Base64表示形式
     * @return 解密后的bytes
     */
    default byte[] decrypt(String data) {
        return decrypt(SecureUtil.decode(data));
    }

    /**
     * 解密Hex（16进制）或Base64表示的字符串，默认UTF-8编码
     *
     * @param data 被解密的String
     * @return 解密后的String
     */
    default String decryptStr(String data) {
        return StrUtil.str(decrypt(data), CharsetUtil.CHARSET_UTF_8);
    }


    /**
     * 解密，会关闭流
     *
     * @param data 被解密的字节流
     * @return 解密后的bytes
     * @throws IORuntimeException IO异常
     */
    default byte[] decrypt(InputStream data) throws IORuntimeException {
        return decrypt(IoUtil.readBytes(data));
    }

    /**
     * 解密
     *
     * @param data 被解密的InputStream
     * @return 解密后的String
     */
    default String decryptStr(InputStream data) {

        return StrUtil.str(decrypt(data), CharsetUtil.CHARSET_UTF_8);
    }
}
