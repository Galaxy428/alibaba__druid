package com.alibaba.druid.bvt.support.odps.udf;

import com.alibaba.druid.support.opds.udf.ExportConditions;
import junit.framework.TestCase;

public class ExportConditionsTest4 extends TestCase {
    ExportConditions udf = new ExportConditions();

    public void test_export_conditions() throws Exception {
        String result = udf.evaluate("select * from t where trim(name) <> 'abc'");
        assertEquals("[[\"t\",\"name\",\"<>\",\"abc\"]]", result);
    }
}
