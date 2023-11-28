package com.github.houbb.csv.support.csv;

import com.github.houbb.csv.api.ICsv;
import com.github.houbb.csv.api.IReadContext;
import com.github.houbb.csv.api.IWriteContext;
import com.github.houbb.csv.support.writer.ICsvWriter;
import com.github.houbb.csv.support.writer.impl.CsvWriterContext;
import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.util.guava.Guavas;
import com.github.houbb.heaven.util.lang.StringUtil;
import com.github.houbb.heaven.util.util.CollectionUtil;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 处理为 list
 *
 * @author binbin.hou
 * @since 0.2.0
 */
@ThreadSafe
public class DefaultStringListCsv implements ICsv<List<String>> {

    @Override
    public List<String> write(IWriteContext<List<String>> context) {
        final List<List<String>> writeList = context.list();
        if (CollectionUtil.isEmpty(writeList)
            || writeList.size() <= 0) {
            return Collections.emptyList();
        }

        // 2.1 存储字符串列表
        final int size = context.list().size();
        List<String> list = Guavas.newArrayList(size);

        // 2.2 构建每一行的内容
        final char quoteChar = context.quoteChar();
        for (int i = 1; i < writeList.size(); i++) {
            List<String> t = writeList.get(i);

            String writeLine = joinCsvLine(t, quoteChar);
            if (StringUtil.isEmpty(writeLine)) {
                continue;
            }
            list.add(writeLine);
        }

        // 2.3 处理字符串列表
        final ICsvWriter csvWriter = context.writer();
        final CsvWriterContext csvWriterContext = CsvWriterContext.newInstance()
                .writeBom(context.writeBom())
                .writeHead(context.writeHead())
                .list(list);

        // 2.4 写入头信息
        final boolean needWriteHead = context.writeHead();
        if (needWriteHead) {
            // 只有需要写入时，才进行计算。
            String headLine = joinCsvLine(writeList.get(0), quoteChar);
            csvWriterContext.head(headLine);
        }

        //2.5 执行写入
        csvWriter.write(csvWriterContext);

        //3. 返回结果列表
        return list;
    }

    @Override
    public List<List<String>> read(IReadContext<List<String>> context) {
        //2. 文件内容
        List<String> allLines = context.reader().read();
        List<String> readableLines = CollectionUtil.subList(allLines,
                context.startIndex(), context.endIndex());
        if (CollectionUtil.isEmpty(readableLines)) {
            return Collections.emptyList();
        }

        final char quoteChar = context.quoteChar();
        List<List<String>> resultList = new ArrayList<>();
        for (String line : readableLines) {
            // 跳过空白行
            if (StringUtil.isEmpty(line)) {
                continue;
            }

            // 设置对象内容
            List<String> lineSplit = splitCsvLine(line, quoteChar);
            resultList.add(lineSplit);
        }

        return resultList;
    }

    protected String joinCsvLine(List<String> splitList, char quoteChar) {
        //fast-fail
        if(CollectionUtil.isEmpty(splitList)) {
            return "";
        }

        StringBuilder stringBuilder = new StringBuilder();


        for(int i = 0; i < splitList.size()-1; i++) {
            String field = splitList.get(i);
            String escapedField = getJoinEscapeField(field, quoteChar);

            stringBuilder.append(escapedField)
                    .append(",");
        }

        String lastField = splitList.get(splitList.size()-1);
        String escapedField = getJoinEscapeField(lastField, quoteChar);
        stringBuilder.append(escapedField);
        return stringBuilder.toString();
    }

    protected String getJoinEscapeField(String field, char quoteChar) {
        if(field.contains(",")) {
            return quoteChar + field + quoteChar;
        }
        return field;
    }

    protected List<String> splitCsvLine(String csvData, char quoteChar) {
        List<String> result = new ArrayList<>();

        // 定义 CSV 数据的正则表达式
        String regex = "(?:" +                     // 开始非捕获组
                "([^" + quoteChar + ",]+)" +      // 匹配非引号非逗号的内容
                "|\"" +                           // 或匹配引号开头的内容
                "([^" + quoteChar + "]*)\"" +    // 匹配引号中的内容
                ")";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(csvData);

        while (matcher.find()) {
            String field = matcher.group(1) != null ? matcher.group(1) : matcher.group(2);
            result.add(field);
        }

        return result;
    }

}
