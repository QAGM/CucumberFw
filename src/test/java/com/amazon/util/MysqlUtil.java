package com.amazon.util;

import java.sql.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import static com.amazon.util.PropertyReaderUtil.auto;

public class MysqlUtil {
    public static Connection connection = null;
    public static Statement st = null;
    public static PreparedStatement ps = null;
    public static ResultSet rs = null;

    public static void connectToDB()

    {
        try {
            connection = DriverManager.getConnection(auto.getProperty("dburl")+"/"+auto.getProperty("dbschema"), auto.getProperty("dbuser"), auto.getProperty("dbpassword"));
        }
        catch (SQLException e){
            System.out.println("There is a problem connecting to your mysql database");
            e.printStackTrace();
            throw new IllegalStateException("There is a problem conneting to your mysql database");
        }
    }

    public static ResultSet getRDBData(String query)
    {
        try {
            st = connection.createStatement();
            rs = st.executeQuery(query);
        }
        catch (SQLException e){
            System.out.println("There is a problem running your query in mysql database");
            e.printStackTrace();
            throw new IllegalStateException("There is a problem running your query in mysql database");
        }
        return rs;

    }
    public static List<HashMap<String,String>> getRDBDataMap(String query)
    {
        List<HashMap<String,String>> data = new LinkedList<>();
        HashMap<String, String> row = new HashMap<>();
        ResultSet rs = getRDBData(query);
        try{
            while(rs.next())
            {
                row = new HashMap<>();
                row.put("product_id",rs.getString("product_id"));
                row.put("product_name",rs.getString("product_name"));
                row.put("product_price",rs.getString("product_price"));
                row.put("product_gender",rs.getString("product_gender"));
                data.add(row);

            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return data;

    }
    public static void updateRDBData(String query)
    {
        try {
            ps = connection.prepareStatement(query);
            ps.execute();
        }
        catch (SQLException e) {
            System.out.println("There is a problem running your query in mysql database");
            e.printStackTrace();
            throw new IllegalStateException("There is a problem running your query in mysql database");
        }

    }
    public static void closeDB()
    {
        try {
            connection.close();
        }
        catch (SQLException e){
            System.out.println("There is a problem closing connection to mysql database");
            e.printStackTrace();
            throw new IllegalStateException("There is a problem closing connection to mysql database");
        }
    }


    public static void main(String[] args) {
        connectToDB();
        //String insertQuery = "insert into product values ('5','short','34.55','female');";
        //updateRDBData(insertQuery);
        System.out.println(getRDBDataMap("select * from product;").toString());
        closeDB();
    }

    public static String getPriceFromDB(String productName)
    {
        String price =null;
        String query = "select * from product where product_name ='"+productName+"'";
        System.out.println(query);
        connectToDB();
        try{
            rs = getRDBData(query);
            while(rs.next())
            {
                price = rs.getString("product_price");
            }

        }catch (Exception e)
        {
            e.printStackTrace();
        }




        return price;
    }

}
