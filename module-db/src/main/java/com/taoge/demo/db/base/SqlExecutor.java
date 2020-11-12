package com.taoge.demo.db.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Desc:
 *
 * @author taoxuefeng
 * @email xuefeng.txf@alibaba-inc.com
 * @date 2020/11/12
 */
@Component
public class SqlExecutor {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Map<String,Object>> executeQuery(String sql){
        return jdbcTemplate.queryForList(sql);
    }

    public int executeUpdate(String sql){
        return jdbcTemplate.update(sql);
    }
}
