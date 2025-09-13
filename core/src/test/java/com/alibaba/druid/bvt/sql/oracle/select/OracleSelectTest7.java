/*
 * Copyright 1999-2017 Alibaba Group Holding Ltd.
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
package com.alibaba.druid.bvt.sql.oracle.select;

import com.alibaba.druid.sql.OracleTest;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.dialect.oracle.parser.OracleStatementParser;
import com.alibaba.druid.sql.dialect.oracle.visitor.OracleSchemaStatVisitor;
import com.alibaba.druid.stat.TableStat;

import java.util.List;

public class OracleSelectTest7 extends OracleTest {
    public void test_0() throws Exception {
        String sql = "SELECT * FROM employees " +
                "   WHERE department_id NOT IN " +
                "   (SELECT department_id FROM departments " +
                "       WHERE location_id = 1700)" +
                "   ORDER BY last_name;";

        OracleStatementParser parser = new OracleStatementParser(sql);
        List<SQLStatement> statementList = parser.parseStatementList();
        SQLStatement stmt = statementList.get(0);
        print(statementList);

        assertEquals(1, statementList.size());

        OracleSchemaStatVisitor visitor = new OracleSchemaStatVisitor();
        stmt.accept(visitor);

        System.out.println("Tables : " + visitor.getTables());
        System.out.println("fields : " + visitor.getColumns());
        System.out.println("coditions : " + visitor.getConditions());
        System.out.println("relationships : " + visitor.getRelationships());
        System.out.println("orderBy : " + visitor.getOrderByColumns());

        assertEquals(2, visitor.getTables().size());

        assertTrue(visitor.getTables().containsKey(new TableStat.Name("departments")));
        assertTrue(visitor.getTables().containsKey(new TableStat.Name("employees")));

        assertEquals(5, visitor.getColumns().size());

        assertTrue(visitor.getColumns().contains(new TableStat.Column("departments", "department_id")));
        assertTrue(visitor.getColumns().contains(new TableStat.Column("employees", "last_name")));
        assertTrue(visitor.getColumns().contains(new TableStat.Column("departments", "location_id")));
        assertTrue(visitor.getColumns().contains(new TableStat.Column("employees", "*")));
        assertTrue(visitor.getColumns().contains(new TableStat.Column("employees", "department_id")));

        assertTrue(visitor.getOrderByColumns().contains(new TableStat.Column("employees", "last_name")));
    }
}
