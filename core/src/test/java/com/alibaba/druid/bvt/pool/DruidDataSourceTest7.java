package com.alibaba.druid.bvt.pool;

import com.alibaba.druid.filter.FilterAdapter;
import com.alibaba.druid.filter.FilterChain;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.proxy.jdbc.ConnectionProxy;
import junit.framework.TestCase;

import java.sql.SQLException;
import java.util.Properties;

public class DruidDataSourceTest7 extends TestCase {
    private DruidDataSource dataSource;

    protected void setUp() throws Exception {
        dataSource = new DruidDataSource();
        dataSource.setUrl("jdbc:mock:xxx");
        dataSource.setInitialSize(1);
        dataSource.getProxyFilters().add(new FilterAdapter() {
            @Override
            public ConnectionProxy connection_connect(FilterChain chain, Properties info) throws SQLException {
                throw new RuntimeException();
            }
        });
    }

    protected void tearDown() throws Exception {
        dataSource.close();
    }

    public void testInitError() throws Exception {
        assertEquals(0, dataSource.getCreateErrorCount());
        Throwable error = null;
        try {
            dataSource.init();
        } catch (RuntimeException e) {
            error = e;
        }
        assertNotNull(error);
        assertTrue(dataSource.getCreateErrorCount() > 0);

        dataSource.getCompositeData();
    }
}
