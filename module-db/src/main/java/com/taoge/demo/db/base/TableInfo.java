package com.taoge.demo.db.base;

import lombok.Data;

import java.util.List;

/**
 * Desc:
 *
 * @author taoxuefeng
 * @email xuefeng.txf@alibaba-inc.com
 * @date 2020/11/12
 */
@Data
public class TableInfo {

    private String tableName;

    private List<ColumnInfo> columnInfoList;

    private String tableType;

    private String tableCat;

    private String tableSchema;

    private String remark;
}
