package com.alibaba.druid.bvt.support.json;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.druid.util.Utils;
import junit.framework.TestCase;

import java.util.List;
import java.util.Map;

public class JSONParserTest2 extends TestCase {
    public void test_parse() throws Exception {
        String text = Utils.readFromResource("bvt/sql.json");
        Map<String, Object> result = (Map<String, Object>) JSONUtils.parse(text);
        List<Map<String, Object>> sqlList = (List<Map<String, Object>>) result.get("Content");
        assertEquals(82, sqlList.size());
    }
}
