package com.sik.encryptutils.utils;

import cn.hutool.crypto.digest.SM3;


/**
 * 基于国密SM3杂凑（摘要）算法实现的信息摘要能力
 * @author ntaibia
 */
public class MessageDigestSM3 implements MessageDigestFacade{

    /**
     * 消息摘要实现算法
     */
    private final SM3 sm3;

    /**
     * 构造
     */
    public MessageDigestSM3() {
        this.sm3 = new SM3();
    }

    /**
     * 构造
     *
     * @param salt 盐值
     */
    public MessageDigestSM3(byte[] salt) {
        this.sm3 = new SM3(salt);
    }

    /**
     * 构造
     *
     * @param salt        盐值
     * @param digestCount 摘要次数，当此值小于等于1,默认为1。
     */
    public MessageDigestSM3(byte[] salt, int digestCount) {
        this.sm3 = new SM3(salt, digestCount);
    }

    /**
     * 构造
     * todo 位置和次数有问题
     * @param salt         盐值
     * @param saltPosition 加盐位置，即将盐值字符串放置在数据的index数，默认0
     * @param digestCount  摘要次数，当此值小于等于1,默认为1。
     */
    public MessageDigestSM3(byte[] salt, int saltPosition, int digestCount) {
        this.sm3 = new SM3(salt, saltPosition, digestCount);
    }

    /**
     * 设置加盐内容
     *
     * @param salt 盐值
     * @return this
     */
    @Override
    public MessageDigestFacade setSalt(byte[] salt) {
        this.sm3.setSalt(salt);
        return this;
    }

    /**
     * 设置加盐的位置，只有盐值存在时有效<br>
     * 加盐的位置指盐位于数据byte数组中的位置，例如：
     *
     * <pre>
     * data: 0123456
     * </pre>
     * <p>
     * 则当saltPosition = 2时，盐位于data的1和2中间，即第二个空隙，即：
     *
     * <pre>
     * data: 01[salt]23456
     * </pre>
     *
     * @param saltPosition 盐的位置
     * @return this
     */
    @Override
    public MessageDigestFacade setSaltPosition(int saltPosition) {
        this.sm3.setSaltPosition(saltPosition);
        return this;
    }

    /**
     * 设置重复计算摘要值次数
     *
     * @param digestCount 摘要值次数
     * @return this
     */
    @Override
    public MessageDigestFacade setDigestCount(int digestCount) {
        this.sm3.setDigestCount(digestCount);
        return this;
    }


    /**
     * 生成摘要
     *
     * @param data 数据bytes
     * @return 摘要bytes
     */
    @Override
    public byte[] digest(byte[] data) {

        return this.sm3.digest(data);
    }
}
