package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by safayat on 10/22/18.
 */
public class MysqlTable {

    String Name;
    List<MysqlColumn> mysqlColumnList;

    public MysqlTable(String name) {
        Name = name;
        this.mysqlColumnList = new ArrayList<MysqlColumn>();
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public List<MysqlColumn> getMysqlColumnList() {
        return mysqlColumnList;
    }

    public void setMysqlColumnList(List<MysqlColumn> mysqlColumnList) {
        this.mysqlColumnList = mysqlColumnList;
    }
}
