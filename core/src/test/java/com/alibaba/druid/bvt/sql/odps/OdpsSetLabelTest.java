package com.alibaba.druid.bvt.sql.odps;

import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.dialect.odps.parser.OdpsStatementParser;
import com.alibaba.druid.sql.parser.Token;
import junit.framework.TestCase;

public class OdpsSetLabelTest extends TestCase {
    public void test_odps() throws Exception {
        String sql = "SET LABEL S3 TO USER aliyun$abc@alibaba-inc.com";
        OdpsStatementParser parser = new OdpsStatementParser(sql);
        SQLStatement stmt = parser.parseStatementList().get(0);
        parser.match(Token.EOF);

        assertEquals("SET LABEL S3 TO USER aliyun$abc@alibaba-inc.com", SQLUtils.toOdpsString(stmt));
        assertEquals("set label S3 to user aliyun$abc@alibaba-inc.com", SQLUtils.toOdpsString(stmt, SQLUtils.DEFAULT_LCASE_FORMAT_OPTION));
    }
}
