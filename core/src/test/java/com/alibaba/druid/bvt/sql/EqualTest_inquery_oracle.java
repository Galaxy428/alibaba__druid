package com.alibaba.druid.bvt.sql;

import junit.framework.TestCase;

import static org.junit.*;

import com.alibaba.druid.sql.ast.expr.SQLInSubQueryExpr;
import com.alibaba.druid.sql.dialect.oracle.parser.OracleExprParser;
import com.alibaba.druid.sql.parser.SQLExprParser;

public class EqualTest_inquery_oracle extends TestCase {
    public void test_exits() throws Exception {
        String sql = "fstate in (select state from t_status)";
        String sql_c = "fstate_c in (select state from t_status)";
        SQLInSubQueryExpr exprA, exprB, exprC;
        {
            SQLExprParser parser = new OracleExprParser(sql);
            exprA = (SQLInSubQueryExpr) parser.expr();
        }
        {
            SQLExprParser parser = new OracleExprParser(sql);
            exprB = (SQLInSubQueryExpr) parser.expr();
        }
        {
            SQLExprParser parser = new OracleExprParser(sql_c);
            exprC = (SQLInSubQueryExpr) parser.expr();
        }
        assertEquals(exprA, exprB);
        assertNotEquals(exprA, exprC);
        assertTrue(exprA.equals(exprA));
        assertFalse(exprA.equals(new Object()));
        assertEquals(exprA.hashCode(), exprB.hashCode());

        assertEquals(new SQLInSubQueryExpr(), new SQLInSubQueryExpr());
        assertEquals(new SQLInSubQueryExpr().hashCode(), new SQLInSubQueryExpr().hashCode());
    }
}
