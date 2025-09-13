/*
 * Copyright 1999-2018 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alibaba.druid.bvt.sql.oracle.block;

import com.alibaba.druid.sql.OracleTest;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.dialect.oracle.parser.OracleStatementParser;
import com.alibaba.druid.sql.dialect.oracle.visitor.OracleSchemaStatVisitor;
import com.alibaba.druid.stat.TableStat;

import java.util.List;

public class OracleBlockTest10 extends OracleTest {
    public void test_0() throws Exception {
        String sql = "DECLARE" +
                "  CURSOR c1 IS" +
                "    SELECT last_name, department_name" +
                "    FROM employees, departments" +
                "    WHERE employees.department_id = departments.department_id " +
                "    AND job_id = 'SA_MAN'" +
                "    FOR UPDATE OF salary;" +
                "BEGIN" +
                "  NULL;" +
                "END;";

        OracleStatementParser parser = new OracleStatementParser(sql);
        List<SQLStatement> statementList = parser.parseStatementList();
        print(statementList);

        assertEquals(1, statementList.size());

        OracleSchemaStatVisitor visitor = new OracleSchemaStatVisitor();
        for (SQLStatement statement : statementList) {
            statement.accept(visitor);
        }

        System.out.println("Tables : " + visitor.getTables());
        System.out.println("fields : " + visitor.getColumns());
        System.out.println("coditions : " + visitor.getConditions());
        System.out.println("relationships : " + visitor.getRelationships());
        System.out.println("orderBy : " + visitor.getOrderByColumns());

        assertEquals(2, visitor.getTables().size());

        assertTrue(visitor.getTables().containsKey(new TableStat.Name("employees")));
        assertTrue(visitor.getTables().containsKey(new TableStat.Name("departments")));

        assertEquals(6, visitor.getColumns().size());
        assertEquals(3, visitor.getConditions().size());

//        assertTrue(visitor.getColumns().contains(new TableStat.Column("employees", "salary")));
    }
}
