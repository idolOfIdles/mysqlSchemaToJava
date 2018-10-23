package Model;

import util.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by safayat on 10/22/18.
 */
public class MysqlTable {

    String Name;
    List<MysqlColumn> mysqlColumnList;
    public  static Map<String, String> JAVA_TYPE_BY_MYSQL_TYPE = new HashMap<String, String>();
    static {
        JAVA_TYPE_BY_MYSQL_TYPE.put("int","Integer");
        JAVA_TYPE_BY_MYSQL_TYPE.put("bigint","Long");
        JAVA_TYPE_BY_MYSQL_TYPE.put("varchar","String");
        JAVA_TYPE_BY_MYSQL_TYPE.put("datetime","Date");
        JAVA_TYPE_BY_MYSQL_TYPE.put("text","String");
        JAVA_TYPE_BY_MYSQL_TYPE.put("tinyint","Boolean");
    }
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

    public String asJavaClass() {
        StringBuilder stringBuilder = new StringBuilder("public class ").append(Name).append("{\n");
        for(MysqlColumn mysqlColumn : mysqlColumnList){
            stringBuilder.append("  private ").append(JAVA_TYPE_BY_MYSQL_TYPE.getOrDefault(mysqlColumn.getTypeName().toLowerCase(), "String")).append(" ").append(mysqlColumn.getName()).append(";\n");
        }

        for(MysqlColumn mysqlColumn : mysqlColumnList){
            String javaType = JAVA_TYPE_BY_MYSQL_TYPE.getOrDefault(mysqlColumn.getTypeName().toLowerCase(), "String");
            stringBuilder.append("  public ")
                    .append(javaType)
                    .append(" ").append("get").append(Util.toTitle(mysqlColumn.getName())).append("(){\n")
                    .append("    return ").append(mysqlColumn.getName()).append(";\n")
                    .append("  }\n");

            stringBuilder.append("  public ")
                    .append(javaType)
                    .append(" ").append("set")
                    .append(Util.toTitle(mysqlColumn.getName()))
                    .append("(").append(javaType).append(" ").append("value").append("){\n")
                    .append("    this.").append(mysqlColumn.getName()).append("=value").append(";\n")
                    .append("  }\n");
        }


        stringBuilder.append("}");

        return stringBuilder.toString();
    }
}
