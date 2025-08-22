package com.alibaba.druid.bvt.sql.eval;

import junit.framework.TestCase;

import static org.junit.*;

import com.alibaba.druid.sql.visitor.SQLEvalVisitorUtils;
import com.alibaba.druid.util.JdbcConstants;

public class EvalMethodTest_insert_1 extends TestCase {
    public void test_method() throws Exception {
        assertEquals("QuWhattic",
                SQLEvalVisitorUtils.evalExpr(JdbcConstants.MYSQL, "INSERT('Quadratic', 3, 4, 'What')"));
    }
}
