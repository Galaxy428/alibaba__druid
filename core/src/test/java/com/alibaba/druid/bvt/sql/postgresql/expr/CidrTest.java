package com.alibaba.druid.bvt.sql.postgresql.expr;


import static org.junit.*;

import com.alibaba.druid.sql.PGTest;
import com.alibaba.druid.sql.dialect.postgresql.ast.expr.PGCidrExpr;
import com.alibaba.druid.sql.dialect.postgresql.parser.PGExprParser;


public class CidrTest extends PGTest {
    public void test_timestamp() throws Exception {
        String sql = "cidr '10.1.0.0/16'";
        PGExprParser parser = new PGExprParser(sql);
        PGCidrExpr expr = (PGCidrExpr) parser.expr();
        assertEquals("cidr '10.1.0.0/16'", expr.toString());

    }
}
