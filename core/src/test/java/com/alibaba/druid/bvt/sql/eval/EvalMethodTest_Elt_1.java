package com.alibaba.druid.bvt.sql.eval;

import junit.framework.TestCase;

import static org.junit.*;

import com.alibaba.druid.sql.visitor.SQLEvalVisitorUtils;
import com.alibaba.druid.util.JdbcConstants;

public class EvalMethodTest_Elt_1 extends TestCase {
    public void test_method() throws Exception {
        assertEquals(null,
                SQLEvalVisitorUtils.evalExpr(JdbcConstants.MYSQL, "ELT(11, 'ej', 'Heja', 'hej', 'foo')"));
    }
}
