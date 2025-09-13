package com.alibaba.druid.bvt.sql.eval;

import com.alibaba.druid.sql.visitor.SQLEvalVisitorUtils;
import com.alibaba.druid.util.JdbcConstants;
import junit.framework.TestCase;

public class EvalMethodTest_left extends TestCase {
    public void test_method() throws Exception {
        assertEquals("fooba",
                SQLEvalVisitorUtils.evalExpr(JdbcConstants.MYSQL, "LEFT('foobarbar', 5)"));
    }
}
