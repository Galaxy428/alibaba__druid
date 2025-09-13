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
package com.alibaba.druid.bvt.pool;

import com.alibaba.druid.mock.MockDriver;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.stat.DruidDataSourceStatManager;
import junit.framework.TestCase;

import javax.management.ObjectName;

import java.lang.management.ManagementFactory;
import java.sql.Connection;

public class TestIdle3 extends TestCase {
    protected void setUp() throws Exception {
        DruidDataSourceStatManager.clear();
    }

    protected void tearDown() throws Exception {
        assertEquals(0, DruidDataSourceStatManager.getInstance().getDataSourceList().size());
    }

    public void test_idle2() throws Exception {
        MockDriver driver = new MockDriver();

        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl("jdbc:mock:xxx");
        dataSource.setDriver(driver);
        dataSource.setInitialSize(1);
        dataSource.setMaxActive(14);
        dataSource.setMaxIdle(14);
        dataSource.setMinIdle(1);
        dataSource.setMinEvictableIdleTimeMillis(30 * 100); // 300 / 10
        dataSource.setTimeBetweenEvictionRunsMillis(18 * 100); // 180 / 10
        dataSource.setTestWhileIdle(true);
        dataSource.setTestOnBorrow(false);
        dataSource.setValidationQuery("SELECT 1");
        dataSource.setFilters("stat");

        ManagementFactory.getPlatformMBeanServer().registerMBean(dataSource,
                new ObjectName(
                        "com.alibaba:type=DataSource,name=mysql"));
        ManagementFactory.getPlatformMBeanServer().registerMBean(dataSource,
                new ObjectName(
                        "com.alibaba:type=DataSource,name=oracle"));

        // 第一次创建连接
        {
            assertEquals(0, dataSource.getCreateCount());
            assertEquals(0, dataSource.getActiveCount());

            Connection conn = dataSource.getConnection();

            assertEquals(dataSource.getInitialSize(), dataSource.getCreateCount());
            assertEquals(dataSource.getInitialSize(), driver.getConnections().size());
            assertEquals(1, dataSource.getActiveCount());

            conn.close();
            assertEquals(0, dataSource.getDestroyCount());
            assertEquals(1, driver.getConnections().size());
            assertEquals(1, dataSource.getCreateCount());
            assertEquals(0, dataSource.getActiveCount());
        }

        {
            // 并发创建14个
            int count = 14;
            Connection[] connections = new Connection[count];
            for (int i = 0; i < count; ++i) {
                connections[i] = dataSource.getConnection();
                assertEquals(i + 1, dataSource.getActiveCount());
            }

            assertEquals(dataSource.getMaxActive(), dataSource.getCreateCount());
            assertEquals(count, driver.getConnections().size());

            // 全部关闭
            for (int i = 0; i < count; ++i) {
                connections[i].close();
                assertEquals(count - i - 1, dataSource.getActiveCount());
            }

            assertEquals(dataSource.getMaxActive(), dataSource.getCreateCount());
            assertEquals(0, dataSource.getActiveCount());
            assertEquals(14, driver.getConnections().size());
        }

        // 连续打开关闭单个连接
        for (int i = 0; i < 1000; ++i) {
            assertEquals(0, dataSource.getActiveCount());
            Connection conn = dataSource.getConnection();

            assertEquals(1, dataSource.getActiveCount());

            Thread.sleep(10);
            conn.close();
        }
        assertEquals(true, dataSource.getPoolingCount() == 2 || dataSource.getPoolingCount() == 1);

        dataSource.close();
    }
}
