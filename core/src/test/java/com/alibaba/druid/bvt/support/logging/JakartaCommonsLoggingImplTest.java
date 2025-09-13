package com.alibaba.druid.bvt.support.logging;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.logging.JakartaCommonsLoggingImpl;
import junit.framework.TestCase;

public class JakartaCommonsLoggingImplTest extends TestCase {
    public void test_0() throws Exception {
        JakartaCommonsLoggingImpl impl = new JakartaCommonsLoggingImpl(DruidDataSource.class.getName());

        impl.isDebugEnabled();
        impl.isInfoEnabled();
        impl.isWarnEnabled();
        impl.debug("");
        impl.debug("", new Exception());
        impl.info("");
        impl.warn("");
        impl.warn("", new Exception());
        impl.error("");
        impl.error("", new Exception());
        assertEquals(1, impl.getInfoCount());
        assertEquals(2, impl.getErrorCount());
        assertEquals(2, impl.getWarnCount());
        assertEquals(1, impl.getInfoCount());
    }
}
