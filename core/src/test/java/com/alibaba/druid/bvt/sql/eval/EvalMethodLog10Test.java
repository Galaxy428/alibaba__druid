package com.alibaba.druid.bvt.sql.eval;

import com.alibaba.druid.sql.visitor.SQLEvalVisitorUtils;
import com.alibaba.druid.util.JdbcConstants;
import junit.framework.TestCase;

public class EvalMethodLog10Test extends TestCase {
    public void test_reverse() throws Exception {
        assertEquals(Math.log10(1), SQLEvalVisitorUtils.evalExpr(JdbcConstants.MYSQL, "log10(1)"));
        assertEquals(Math.log10(1.001), SQLEvalVisitorUtils.evalExpr(JdbcConstants.MYSQL, "log10(1.001)"));
        assertEquals(Math.log10(0), SQLEvalVisitorUtils.evalExpr(JdbcConstants.MYSQL, "log10(0)"));
    }

    public void test_error() throws Exception {
        Exception error = null;
        try {
            SQLEvalVisitorUtils.evalExpr(JdbcConstants.MYSQL, "log10()", 12L);
        } catch (Exception e) {
            error = e;
        }
        assertNotNull(error);
    }

    public void test_error_1() throws Exception {
        Exception error = null;
        try {
            SQLEvalVisitorUtils.evalExpr(JdbcConstants.MYSQL, "log10(a)");
        } catch (Exception e) {
            error = e;
        }
        assertNotNull(error);
    }
}
