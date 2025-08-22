package com.alibaba.druid.bvt.sql.odps;

import static org.junit.*;

import com.alibaba.druid.sql.SQLUtils;

import junit.framework.TestCase;

public class OdpsFormatCommentTest22 extends TestCase {
    public void test_if() throws Exception {
        String sql = "select if(a>0,1, 0) from dual";
        assertEquals("SELECT IF(a > 0, 1, 0)"
                + "\nFROM dual", SQLUtils.formatOdps(sql));
    }

    public void test_coalesce() throws Exception {
        String sql = "select coalesce(f1,f2) from dual";
        assertEquals("SELECT COALESCE(f1, f2)"
                + "\nFROM dual", SQLUtils.formatOdps(sql));
    }

    public void test_count() throws Exception {
        String sql = "select count(*) from dual";
        assertEquals("SELECT count(*)"
                + "\nFROM dual", SQLUtils.formatOdps(sql));
    }

}
