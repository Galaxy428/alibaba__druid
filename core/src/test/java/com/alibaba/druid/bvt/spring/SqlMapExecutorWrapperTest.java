package com.alibaba.druid.bvt.spring;

import com.alibaba.druid.support.ibatis.SqlMapClientWrapper;
import com.ibatis.sqlmap.engine.impl.SqlMapClientImpl;
import junit.framework.TestCase;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Connection;

public class SqlMapExecutorWrapperTest extends TestCase {
    private ClassPathXmlApplicationContext context;

    protected void setUp() throws Exception {
        context = new ClassPathXmlApplicationContext("com/alibaba/druid/pool/ibatis/spring-config-ibatis.xml");
    }

    protected void tearDown() throws Exception {
        context.close();
    }

    public void test_wrap() throws Exception {
        SqlMapClientImpl client = (SqlMapClientImpl) context.getBean("master-sqlMapClient");
        assertNotNull(client);

        SqlMapClientWrapper wrapper = new SqlMapClientWrapper(client);
        wrapper.getClient();
        wrapper.startTransaction();
        wrapper.endTransaction();
        wrapper.startTransaction(Connection.TRANSACTION_NONE);
        wrapper.endTransaction();
        wrapper.setUserConnection(wrapper.getUserConnection());
        wrapper.getCurrentConnection();
        wrapper.getDataSource();
        wrapper.openSession().close();
        wrapper.openSession(wrapper.getCurrentConnection()).close();
        wrapper.getSession();
        wrapper.flushDataCache();
        Exception error = null;
        try {
            wrapper.flushDataCache(null);
        } catch (Exception ex) {
            error = ex;
        }
        assertNotNull(error);
        wrapper.startTransaction();
        wrapper.commitTransaction();

        wrapper.getMappedStatement("Sequence.getValue");

        wrapper.isEnhancementEnabled();
        wrapper.isLazyLoadingEnabled();

        wrapper.getSqlExecutor();

        wrapper.getDelegate();

        wrapper.getResultObjectFactory();
    }
}
