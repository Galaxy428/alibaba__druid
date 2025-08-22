package com.alibaba.druid.bvt.sql.odps;

import static org.junit.*;

import com.alibaba.druid.sql.SQLUtils;

import junit.framework.TestCase;

public class OdpsFormatCommentTest7 extends TestCase {
    public void test_column_comment() throws Exception {
        String sql = "--这里是注释" //
                + "\nselect * from table1;" //
                + "\nselect * from table2;;";//
        assertEquals("-- 这里是注释"
                + "\nSELECT *"
                + "\nFROM table1;"
                + "\n"
                + "\nSELECT *"
                + "\nFROM table2;", SQLUtils.formatOdps(sql));
    }

}
