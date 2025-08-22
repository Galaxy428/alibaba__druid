package com.alibaba.druid.bvt.sql.eval;

import junit.framework.TestCase;

import static org.junit.*;

import com.alibaba.druid.sql.visitor.SQLEvalVisitorUtils;
import com.alibaba.druid.util.JdbcConstants;

public class EvalLikeTest extends TestCase {
    public void test_like() throws Exception {
        assertEquals(true, SQLEvalVisitorUtils.evalExpr(JdbcConstants.MYSQL, "'a' LIKE '[a-d]'"));
    }

    public void test_not_like() throws Exception {
        assertEquals(false, SQLEvalVisitorUtils.evalExpr(JdbcConstants.MYSQL, "'a' NOT LIKE '[a-d]'"));
    }
}
