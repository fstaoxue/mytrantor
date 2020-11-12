package com.taoge.demo.db.base;

import lombok.Data;

import java.sql.JDBCType;

/**
 * Desc:
 *
 * @author taoxuefeng
 * @email xuefeng.txf@alibaba-inc.com
 * @date 2020/11/12
 */
@Data
public class ColumnInfo {

    private String columnName;

    private JDBCType jdbcType;

}
