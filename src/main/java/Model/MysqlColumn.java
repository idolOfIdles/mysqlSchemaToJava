package Model;

/**
 * Created by safayat on 10/22/18.
 */
public class MysqlColumn {

    String Name;
    String Type;

    public MysqlColumn(String name, String type) {
        Name = name;
        Type = type;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getType() {
        return Type;
    }

    public String getTypeName() {
        return Type.indexOf("(") >= 0 ? Type.substring(0, Type.indexOf("(")) : Type;
    }

    public void setType(String type) {
        Type = type;
    }
}
