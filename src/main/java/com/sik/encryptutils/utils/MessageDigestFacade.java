package com.sik.encryptutils.utils;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IORuntimeException;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.HexUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.CryptoException;

import java.io.File;
import java.io.InputStream;

/**
 * 信息摘要能力定义
 * @author ntaibai
 */
public interface MessageDigestFacade {


    /**
     * 设置加盐内容
     *
     * @param salt 盐值
     * @return this
     */
    MessageDigestFacade setSalt(byte[] salt);

    /**
     * 设置加盐的位置，只有盐值存在时有效<br>
     * 加盐的位置指盐位于数据byte数组中的位置，例如：
     *
     * <pre>
     * data: 0123456
     * </pre>
     *
     * 则当saltPosition = 2时，盐位于data的1和2中间，即第二个空隙，即：
     *
     * <pre>
     * data: 01[salt]23456
     * </pre>
     *
     *
     * @param saltPosition 盐的位置
     * @return this
     */
    MessageDigestFacade setSaltPosition(int saltPosition);

    /**
     * 设置重复计算摘要值次数
     *
     * @param digestCount 摘要值次数
     * @return this
     */
    MessageDigestFacade setDigestCount(int digestCount);




    /**
     * 生成摘要
     *
     * @param data 数据bytes
     * @return 摘要bytes
     */
    byte[] digest(byte[] data);

    /**
     * 返回实现指定摘要算法的MessageDigestFacade对象。
     * @return 信息摘要能力实例
     */
    static MessageDigestFacade getInstance(MessageDigestAlgorithm messageDigestAlgorithm) {

        if (MessageDigestAlgorithm.SM3.equalTo(messageDigestAlgorithm)) {
            return new MessageDigestSM3();
        }
        return null;
    }

    /**
     * 生成摘要，并转为16进制字符串<br>
     *
     * @param data 被摘要数据
     * @return 摘要bytes
     */
    default byte[] digest(String data) {
        return digest(StrUtil.bytes(data, CharsetUtil.UTF_8));
    }


    /**
     * 生成摘要，并转为16进制字符串<br>
     * 读取完毕后关闭流
     * @param data {@link InputStream} 数据流
     * @return 摘要bytes
     */
    default byte[] digest(InputStream data) throws IORuntimeException {
        return digest(IoUtil.readBytes(data, true));
    }

    /**
     * 生成文件摘要<br>
     *
     * @param file 被摘要文件
     * @return 摘要bytes
     * @throws CryptoException Cause by IOException
     */
    default byte[] digest(File file) throws CryptoException {
        InputStream in = null;
        try {
            in = FileUtil.getInputStream(file);
            return digest(in);
        } finally {
            IoUtil.close(in);
        }
    }

    /**
     * 生成摘要，并转为16进制字符串<br>
     *
     * @param data 被摘要数据
     * @return 摘要
     */
    default String digestHex(String data) {
        return HexUtil.encodeHexStr(digest(StrUtil.bytes(data, CharsetUtil.UTF_8)));
    }

    /**
     * 生成摘要，并转为16进制字符串<br>
     *
     * @param data 被摘要数据
     * @return 摘要
     */
    default String digestHex(byte[] data) {
        return HexUtil.encodeHexStr(digest(data));
    }

    /**
     * 生成摘要，并转为16进制字符串<br>
     * 读取完毕后关闭流
     * @param data {@link InputStream} 数据流
     * @return 摘要
     */
    default String digestHex(InputStream data) throws IORuntimeException {
        return HexUtil.encodeHexStr(digest(IoUtil.readBytes(data, true)));
    }

    /**
     * 生成文件摘要，并转为16进制字符串<br>
     *
     * @param file 被摘要文件
     * @return 摘要
     * @throws CryptoException Cause by IOException
     */
    default String digestHex(File file) throws CryptoException {
        InputStream in = null;
        try {
            in = FileUtil.getInputStream(file);
            return HexUtil.encodeHexStr(digest(in));
        } finally {
            IoUtil.close(in);
        }
    }

}
