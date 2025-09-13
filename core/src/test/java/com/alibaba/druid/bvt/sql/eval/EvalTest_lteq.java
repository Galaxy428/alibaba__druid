package com.alibaba.druid.bvt.sql.eval;

import com.alibaba.druid.sql.visitor.SQLEvalVisitorUtils;
import com.alibaba.druid.util.JdbcConstants;
import junit.framework.TestCase;

import java.math.BigDecimal;
import java.math.BigInteger;

public class EvalTest_lteq extends TestCase {
    public void test_long() throws Exception {
        assertEquals(true, SQLEvalVisitorUtils.evalExpr(JdbcConstants.MYSQL, "? <= ?", (long) 1, (byte) 2));
    }

    public void test_int() throws Exception {
        assertEquals(true, SQLEvalVisitorUtils.evalExpr(JdbcConstants.MYSQL, "? <= ?", (int) 1, (byte) 2));
    }

    public void test_short() throws Exception {
        assertEquals(true, SQLEvalVisitorUtils.evalExpr(JdbcConstants.MYSQL, "? <= ?", (short) 1, (byte) 2));
    }

    public void test_byte() throws Exception {
        assertEquals(true, SQLEvalVisitorUtils.evalExpr(JdbcConstants.MYSQL, "? <= ?", (byte) 1, (byte) 2));
    }

    public void test_BigInteger() throws Exception {
        assertEquals(true, SQLEvalVisitorUtils.evalExpr(JdbcConstants.MYSQL, "?<=?", BigInteger.ONE, (byte) 2));
    }

    public void test_BigDecimal() throws Exception {
        assertEquals(true, SQLEvalVisitorUtils.evalExpr(JdbcConstants.MYSQL, "?<=?", BigDecimal.ONE, (byte) 2));
    }

    public void test_float() throws Exception {
        assertEquals(true, SQLEvalVisitorUtils.evalExpr(JdbcConstants.MYSQL, "?<=?", (float) 1, (byte) 2));
    }

    public void test_double() throws Exception {
        assertEquals(true, SQLEvalVisitorUtils.evalExpr(JdbcConstants.MYSQL, "?<=?", (double) 1, (byte) 2));
    }

    public void test_String() throws Exception {
        assertEquals(true, SQLEvalVisitorUtils.evalExpr(JdbcConstants.MYSQL, "?<=?", "1", "2"));
    }
}
