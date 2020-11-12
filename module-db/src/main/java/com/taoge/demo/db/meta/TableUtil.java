package com.taoge.demo.db.meta;

import com.taoge.demo.db.base.ColumnInfo;
import com.taoge.demo.db.base.TableInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
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
public class TableUtil {

    @Autowired
    private DataSource dataSource;

    private static Map<String,TableInfo> tableInfoMap=new LinkedHashMap<>();


    public TableInfo getTableInfo(String tableName){
        if(tableInfoMap.containsKey(tableName)){
            return tableInfoMap.get(tableName);
        }
        return null;
    }

    @PostConstruct
    private void getTableInfoList(){
        try {
            Connection conn = dataSource.getConnection();
            DatabaseMetaData meta = conn.getMetaData();
            ResultSet rs = meta.getTables(null, null, null,
                    new String[] { "TABLE" });
            while(rs.next()){
                TableInfo tableInfo=new TableInfo();
                tableInfo.setTableName(rs.getString("TABLE_NAME"));
                tableInfo.setTableType(rs.getString("TABLE_TYPE"));
                tableInfo.setTableCat(rs.getString("TABLE_CAT"));
                tableInfo.setTableSchema(rs.getString("TABLE_SCHEM"));
                tableInfo.setRemark(rs.getString("REMARKS"));
                tableInfo.setColumnInfoList(getColumnInfo(tableInfo.getTableName()));
                tableInfoMap.put(tableInfo.getTableName(),tableInfo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private List<ColumnInfo> getColumnInfo(String table){
        String sql="select * from "+table;
        List<ColumnInfo> columnInfoList = new ArrayList<>();
        try {
            PreparedStatement ps = dataSource.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData meta = rs.getMetaData();
            int columnCount = meta.getColumnCount();
            for (int i = 1; i < columnCount + 1; i++) {
                ColumnInfo columnInfo=new ColumnInfo();
                columnInfo.setColumnName(meta.getColumnName(i));
                columnInfo.setJdbcType(JDBCType.valueOf(meta.getColumnType(i)));
                columnInfoList.add(columnInfo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return columnInfoList;
    }
}
