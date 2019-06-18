package com.github.houbb.csv.util;

import java.nio.charset.StandardCharsets;

/**
 * Bom 工具类
 * 针对常见的编码 bom 进行整理，避免 windows 打开 csv 乱码。
 *
 * 注意：一般 windows 默认使用（Little Endian）
 * @author binbin.hou
 * @since 0.0.4
 */
public final class CsvBomUtil {

    private CsvBomUtil(){}

    /**
     * UTF8_BOM 行信息，避免 excel 打开乱码
     */
    private static final byte[] UTF8_BOM = new byte[]{(byte) 0xEF, (byte) 0xBB, (byte) 0xBF};

    /**
     * UTF-16（大端序）
     */
    private static final byte[] UTF16_BIG_BOM = new byte[]{(byte) 0xFE, (byte) 0xFF};

    /**
     * UTF-16（小端序）
     */
    private static final byte[] UTF16_LITTLE_BOM = new byte[]{(byte) 0xFF, (byte) 0xFE};

    /**
     * UTF-32（大端序）
     */
    private static final byte[] UTF32_BIG_BOM = new byte[]{(byte) 0x00, (byte) 0x00, (byte) 0xFE, (byte) 0xFF};

    /**
     * UTF-32（小端序）
     */
    private static final byte[] UTF32_LITTLE_BOM = new byte[]{(byte) 0xFF, (byte) 0xFE, (byte) 0x00, (byte) 0x00};

    /**
     * UTF-7
     * 2B 2F 76和以下的一个字节：[ 38 | 39 | 2B | 2F ]
     */
    private static final byte[] UTF7_BOM = new byte[]{(byte) 0x2B, (byte) 0x2F, (byte) 0x76, (byte) 0x38};


    /**
     * UTF-1
     * F7 64 4C
     */
    private static final byte[] UTF1_BOM = new byte[]{(byte) 0xF7, (byte) 0x64, (byte) 0x4C};

    /**
     * en:UTF-EBCDIC
     * DD 73 66 73
     */
    private static final byte[] UTF_EBCDIC_BOM = new byte[]{(byte) 0xDD, (byte) 0x73, (byte) 0x66, (byte) 0x73};

    /**
     * en:Standard Compression Scheme for Unicode
     * 0E FE FF
     */
    private static final byte[] SCSFU_BOM = new byte[]{(byte) 0x0E, (byte) 0xFE, (byte) 0xFF};

    /**
     * en:BOCU-1
     * FB EE 28及可能跟随着FF
     */
    private static final byte[] BOCU1_BOM = new byte[]{(byte) 0xFB, (byte) 0xEE, (byte) 0x28};

    /**
     * GB-18030
     * 84 31 95 33
     */
    private static final byte[] GB_18030_BOM = new byte[]{(byte) 0x84, (byte) 0x31, (byte) 0x95, (byte) 0x33};

    /**
     * 获取对应的 bom 头
     * @param charset 编码
     * @return 结果信息
     */
    public static byte[] getBom(final String charset) {
        if(StandardCharsets.UTF_8.name().equals(charset)) {
            return UTF8_BOM;
        }
        return new byte[]{};
    }

}
