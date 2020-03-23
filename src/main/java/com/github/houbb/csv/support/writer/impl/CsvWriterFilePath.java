package com.github.houbb.csv.support.writer.impl;

import com.github.houbb.csv.exception.CsvException;
import com.github.houbb.csv.support.writer.ICsvWriter;
import com.github.houbb.csv.util.CsvBomUtil;
import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.constant.CharsetConst;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

/**
 * 控台输出，主要用于调试和自测
 * @author binbin.hou
 * @since 0.0.8
 */
@ThreadSafe
public class CsvWriterFilePath implements ICsvWriter {

    /**
     * 文件路径
     * @since 0.0.8
     */
    private final String filePath;

    /**
     * 文件编码
     * @since 0.0.8
     */
    private final String charset;

    public CsvWriterFilePath(String filePath, String charset) {
        this.filePath = filePath;
        this.charset = charset;
    }

    public CsvWriterFilePath(String filePath) {
        this(filePath, CharsetConst.UTF8);
    }

    /**
     * 针对字符串列表的写入
     *
     * @param list 列表
     * @since 0.0.8
     */
    @Override
    public void write(final List<String> list) {
        try {
            // 父类文件夹创建
            //2. 统一写入文件
            // 默认使用添加的方式写入，后期再开放这个开关
            Path path = Paths.get(filePath);
            //2.1 创建父类文件夹
            File parent = path.getParent().toFile();
            if (!parent.exists()) {
                boolean result = parent.mkdirs();
                if(!result) {
                    throw new CsvException("File mkdirs failed!");
                }
            }
            //2.2 创建文件
            File file = path.toFile();
            if (!file.exists()) {
                boolean result = file.createNewFile();
                if(!result) {
                    throw new CsvException("File create failed!");
                }
            }
            // 默认写入 bom

            //2.3 写入（首先记录状态，然后写入信息）
            // 2.3.1 写入 bom 头(默认清空文件内容)
            final byte[] bomBytes = CsvBomUtil.getBom(charset);
            Files.write(path, bomBytes, StandardOpenOption.TRUNCATE_EXISTING);

            //2.4 新增的方式写入内容
            Files.write(path, list, Charset.forName(charset), StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new CsvException(e);
        }
    }

}
