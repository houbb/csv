package com.github.houbb.csv.bs;

import com.github.houbb.csv.model.*;
import org.junit.Ignore;
import org.junit.Test;

import java.util.*;

/**
 * @author binbin.hou
 * @since 0.0.1
 */
@Ignore
public class CsvWriteBsTest {

    @Test
    public void commonTest() {
        final String path = "src\\test\\resources\\common.csv";
        CsvWriteBs.newInstance(path)
                .write(buildCommonList());
    }

    /**
     * 基于注解的写入测试
     * @since 0.0.2
     */
    @Test
    public void annotationTest() {
        final String path = "src\\test\\resources\\annotation.csv";
        CsvWriteBs.newInstance(path)
                .write(buildAnnotationList());
    }

    /**
     * 集合测试
     * @since 0.0.3
     */
    @Test
    public void collectionTest() {
        final String path = "src\\test\\resources\\collection.csv";
        CsvWriteBs.newInstance(path)
                .write(buildCollectionList());
    }

    /**
     * 构建明细信息测试
     * @since 0.0.5
     */
    @Test
    public void entryTest() {
        final String path = "src\\test\\resources\\entry.csv";
        CsvWriteBs.newInstance(path)
                .write(buildEntryList());
    }

    /**
     * 构建特殊信息转义信息测试
     * @since 0.0.6
     */
    @Test
    public void escapeTest() {
        final String path = "src\\test\\resources\\escape.csv";
        CsvWriteBs.newInstance(path)
                .escape(true)
                .write(buildUserEscapeList());
    }

    /**
     * 自引用测试
     * @since 0.0.7
     */
    @Test
    public void selfRefTest() {
        final String path = "src\\test\\resources\\selfRef.csv";
        CsvWriteBs.newInstance(path)
                .write(buildUserSelfRefs());
    }

    /**
     * 构建自引用列表
     * @return 列表
     * @since 0.0.7
     */
    private List<UserSelfRef> buildUserSelfRefs() {
        UserSelfRef userSelfRef = new UserSelfRef();
        UserSelfRef child = new UserSelfRef();
        child.name("child");

        userSelfRef.name("123");
        userSelfRef.selfRef(child);
        return Collections.singletonList(userSelfRef);
    }

    /**
     * 构建待转换的结果表
     * @return 结果列表
     * @since 0.0.6
     */
    private List<UserEscape> buildUserEscapeList() {
        UserEscape escape = new UserEscape();
        Map<String, String> map = new HashMap<>();
        map.put("key=key", "value=value");
        User user = new User();
        user.name("entry:name");

        escape.name("one,one");
        escape.nameList(Arrays.asList("one|one", "two|two"));
        escape.map(map);
        escape.user(user);

        return Collections.singletonList(escape);
    }

    /**
     * 构建通用测试列表
     * @return 列表
     */
    private List<User> buildCommonList() {
        User user = new User();
        short s = 4;
        byte b = 1;
        user.age(10)
        .name("你好")
        .id(1L)
        .score(60)
        .coin(b)
        .level(s)
        .money(200)
        .sex(true)
        .status('Y');
        return Arrays.asList(user);
    }

    /**
     * 构建基于注解的测试列表
     * @return 列表
     */
    private List<UserAnnotation> buildAnnotationList() {
        UserAnnotation user = new UserAnnotation();
        user.name("你好")
                .password("123")
                .birthday(new Date());
        return Arrays.asList(user);
    }

    /**
     * 构建基于集合的测试列表
     * @return 列表
     * @since 0.0.3
     */
    private List<UserCollection> buildCollectionList() {
        UserCollection user = new UserCollection();
        String[] arrays = new String[]{"a", "b", "c"};
        LinkedList<String> lists = new LinkedList<>(Arrays.asList(arrays));
        Map<String, String> maps = new HashMap<>();
        maps.put("key", "value");
        maps.put("key2", "value2");
        Set<String> sets = new HashSet<>();
        sets.add("set1");
        sets.add("set2");

        user.setLists(lists);
        user.setArrays(arrays);
        user.setMaps(maps);
        user.setSets(sets);
        return Arrays.asList(user);
    }

    /**
     * 用户明细列表
     * @return 明细列表
     * @since 0.0.5
     */
    private List<UserEntry> buildEntryList() {
        UserEntry userEntry = new UserEntry();
        userEntry.name("test");
        userEntry.user(buildCommonList().get(0));
        return Collections.singletonList(userEntry);
    }

}
