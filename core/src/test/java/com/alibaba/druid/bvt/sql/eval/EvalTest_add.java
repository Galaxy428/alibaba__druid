package com.alibaba.druid.bvt.sql.eval;

import junit.framework.TestCase;

import static org.junit.*;

import com.alibaba.druid.sql.visitor.SQLEvalVisitorUtils;
import com.alibaba.druid.util.JdbcConstants;

public class EvalTest_add extends TestCase {
    public void test_byte() throws Exception {
        assertEquals(3, SQLEvalVisitorUtils.evalExpr(JdbcConstants.MYSQL, "? + ?", (byte) 1, (byte) 2));
    }

    public void test_byte_1() throws Exception {
        assertEquals(3, SQLEvalVisitorUtils.evalExpr(JdbcConstants.MYSQL, "? + ?", (byte) 1, "2"));
    }

    public void test_byte_2() throws Exception {
        assertEquals(null, SQLEvalVisitorUtils.evalExpr(JdbcConstants.MYSQL, "? + ?", (byte) 1, null));
    }

    public void test_byte_3() throws Exception {
        assertEquals(3, SQLEvalVisitorUtils.evalExpr(JdbcConstants.MYSQL, "? + ?", "2", (byte) 1));
    }
}
