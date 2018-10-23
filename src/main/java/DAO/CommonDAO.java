package DAO;

import Model.MysqlColumn;
import Model.MysqlTable;
import util.FileManager;
import util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by safayat on 10/20/18.
 */

public class CommonDAO {

    private String dbUserName = "root";

    private String dbPassword = "";

    private String dbName  = "rssdesk";
//    private String dbName  = "schoolmanagement";

    private String dbUrl = "jdbc:mysql://localhost:3306/rssdesk?useSSL=false";
//    private String dbUrl = "jdbc:mysql://localhost:3306/schoolmanagement";



    private Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;

    }

    public MysqlTable readSchema(String table) {
        Connection dbConnection = null;
        Statement statement = null;

        String readTableSchemaSQL = "DESC " + dbName + "." + table;
        MysqlTable mysqlTable = new MysqlTable(table);
        try {
            dbConnection = getConnection();
            statement = dbConnection.createStatement();
            ResultSet rs = statement.executeQuery(readTableSchemaSQL);

            while (rs.next()){
                String fieldName = rs.getString("Field");
                String fieldType = rs.getString("Type");
                MysqlColumn mysqlColumn = new MysqlColumn(fieldName, fieldType);
                mysqlTable.getMysqlColumnList().add(mysqlColumn);
            }

        }catch (Exception e){
            e.printStackTrace();
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
            if(dbConnection!=null){
                try {
                    dbConnection.close();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }

        }
        return mysqlTable;

    }

    public void readDatabaseSchema(String db) {
        Connection dbConnection = null;
        Statement statement = null;

        String databaseChangeSQL = "use " + db;
        String readDatabaseSQL = "show tables";
        try {
            dbConnection = getConnection();
            statement = dbConnection.createStatement();
            statement.executeQuery(databaseChangeSQL);
            ResultSet rs = statement.executeQuery(readDatabaseSQL);
            while (rs.next()){
                String tableName = rs.getString(1);
                MysqlTable mysqlTable  =readSchema(tableName);
                FileManager.write("javaFiles/" + tableName + ".java", mysqlTable.asJavaClass());
            }

        }catch (Exception e){
            e.printStackTrace();
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
            if(dbConnection!=null){
                try {
                    dbConnection.close();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }

        }

    }

    public static void main(String[] args){
        new CommonDAO().readDatabaseSchema("rssdesk");
    }



}
