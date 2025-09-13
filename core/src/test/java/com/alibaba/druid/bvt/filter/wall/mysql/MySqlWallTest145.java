package com.alibaba.druid.bvt.filter.wall.mysql;

import com.alibaba.druid.wall.WallProvider;
import com.alibaba.druid.wall.spi.MySqlWallProvider;
import junit.framework.TestCase;

public class MySqlWallTest145 extends TestCase {
    public void test_false() throws Exception {
        WallProvider provider = new MySqlWallProvider();

        String sql = "SHOW FULL TABLES WHERE Table_type != 'VIEW'";
        assertTrue(
                provider.checkValid(sql)
        );
    }
}
