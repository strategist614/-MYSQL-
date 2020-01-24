package com.example.myapplication;
import android.util.Log;
import android.util.StateSet;
import android.view.View;

import com.example.myapplication.bean.Commodity;
import com.example.myapplication.bean.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DBHelper {
    private static final String DBDRIVER = "com.mysql.jdbc.Driver";
    private static final String DBURL = "jdbc:mysql://198.13.53.11:3306/CommodityPool?useSSL=FALSE";
    private static final String DBUSER = "user";
    private static final String DBPASSWORD = ")(*&^%$#@!";
    private static final String DBVISTOR = "visitor";
    private static final String DBpWD = "!@#$%^&*()";
    private static String TAG = "DBHelper";
    //mysql -u root -h ip -p
    public static boolean linkMysql(String User,String Password) {
        Connection conn=null;
        PreparedStatement stmt=null;
        try {
            Class.forName(DBDRIVER).newInstance();
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
        try{
            conn = DriverManager.getConnection(DBURL,DBUSER,DBPASSWORD);
            Log.i("aaa", "连接成功");
            String sql = "select UserName,Password from AccountPool.UserTable";
            stmt= conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            boolean flag = false;
            while (rs.next()) {
                if(Password.equals(rs.getString("Password")) && User.equals(rs.getString("UserName"))) flag = true;
            }
            System.out.println(flag);
            if(flag) return true;
            else return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        finally {
            if(conn!=null){
                try {
                    conn.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            if(stmt != null)
            {
                try {
                    stmt.close();
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
    public static HashMap<String, String> SetUser(String User)
    {
        HashMap<String,String> mp = new HashMap<String, String>();
        Connection conn=null;
        PreparedStatement stmt=null;
        try {
            Class.forName(DBDRIVER).newInstance();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        try{
            conn = DriverManager.getConnection(DBURL,DBUSER,DBPASSWORD);
            Log.i("bbb", "连接成功");
            String sql = "select *from ProfilePool.ProfileTable";
            stmt= conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                 Log.i("ssss",rs.getString("PhoneNumber"));
                  if(rs.getString("UserName").equals(User))
                  {
                      mp.put("User",rs.getString("UserName"));
                      mp.put("Nick",rs.getString("NickName"));
                      mp.put("Phone",rs.getString("PhoneNumber"));
                      mp.put("Q",rs.getString("QQ"));
                      mp.put("Dormitory",rs.getString("DormitoryNumber"));
                      mp.put("Pro",rs.getString("Profession"));
                  }
            }
        } catch (Exception e) {
            e.printStackTrace();
            //return false;
        }
        finally {
            if(conn!=null){
                try {
                    conn.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            if(stmt != null)
            {
                try {
                    stmt.close();
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
        return mp;
    }
    public static boolean modify(String StuName,String StuNickname,String StuMajor,String StuPhone,String StuQq,String StuAddress)
    {
        Connection conn=null;
        PreparedStatement stmt=null;
        Connection con = null;
        PreparedStatement st = null;
        try {
            Class.forName(DBDRIVER).newInstance();
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
        try{
            con = DriverManager.getConnection(DBURL,DBUSER,DBPASSWORD);
            conn = DriverManager.getConnection(DBURL,DBUSER,DBPASSWORD);
            Log.i("aaa", "连接成功");
            String sx = "select *from ProfilePool.ProfileTable";
            String sql = "update ProfilePool.ProfileTable set NickName='" + StuNickname + "',Profession='"+StuMajor+"',PhoneNumber='"+ StuPhone +"',QQ='"+ StuQq +"',DormitoryNumber='"+StuAddress+"'";
            stmt= conn.prepareStatement(sx);
            st = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            boolean flag = false;
            while (rs.next()) {
                 if(rs.getString("UserName").equals(StuName))
                 {
                     int s = st.executeUpdate();
                     flag = true;
                 }
            }
           if(flag) return true;
           else return  false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        finally {
            if(conn!=null){
                try {
                    conn.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            if(stmt != null)
            {
                try {
                    stmt.close();
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
            if(con != null)
            {
                try {
                    con.close();
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
            if(st != null)
            {
                try{
                    st.close();
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
    public static String GetId(String stuName)
    {
        Connection conn=null;
        PreparedStatement stmt=null;
        String Id = "";
        try {
            Class.forName(DBDRIVER).newInstance();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        try{
            conn = DriverManager.getConnection(DBURL,DBUSER,DBPASSWORD);
            Log.i("aaa", "连接成功");
            String sql = "select * from ProfilePool.ProfileTable";
            stmt= conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                if(rs.getString("UserName").equals(stuName))
                {
                    Id = rs.getString("ID");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if(conn!=null){
                try {
                    conn.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            if(stmt != null)
            {
                try {
                    stmt.close();
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
        return Id;
    }
    public static int Publish(String title,int ownerID,float price,String description,String type)
    {
        Connection conn=null;
        PreparedStatement stmt=null;
        Connection con = null;
        PreparedStatement st = null;
        int reselut = 0;
        try {
            Class.forName(DBDRIVER).newInstance();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        try{
            conn = DriverManager.getConnection(DBURL,DBUSER,DBPASSWORD);
            Log.i("aaa", "连接成功");
            String sql = "insert into CommodityPool.CommodityTable(ID,Name,Price,Type,OwnerID,Introduction) values(right(uuid_short(),8),?,?,?,?,?);";
            stmt= conn.prepareStatement(sql);
            stmt.setString(1,title);
            stmt.setFloat(2,price);
            stmt.setString(3,type);
            stmt.setInt(4,ownerID);
            stmt.setString(5,description);
            reselut = stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if(conn!=null){
                try {
                    conn.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            if(stmt != null)
            {
                try {
                    stmt.close();
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
        return  reselut;
    }
    public static int register(String stuName,String stuPassword)
    {
        Connection conn=null;
        PreparedStatement stmt=null;
        Connection con = null;
        PreparedStatement st = null;
        int id = 0;
        int reselut = 0;
        try {
            Class.forName(DBDRIVER).newInstance();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        try{
            con = DriverManager.getConnection(DBURL,DBVISTOR,DBpWD);
            conn = DriverManager.getConnection(DBURL,DBVISTOR,DBpWD);
            Log.i("aaa", "连接成功");
            String sql = "insert into  AccountPool.UserTable(UserName,Password) values(?,?);";
            String sx = "select * from AccountPool.UserTable";
            stmt= conn.prepareStatement(sql);
            st = con.prepareStatement(sx);
            stmt.setString(1,stuName);
            stmt.setString(2,stuPassword);
            reselut = stmt.executeUpdate();
            ResultSet rs = st.executeQuery();
            while(rs.next())
            {
                if(rs.getString("UserName").equals(stuName))
                   id = rs.getInt("ID");
            }
            System.out.println(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if(conn!=null){
                try {
                    conn.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            if(stmt != null)
            {
                try {
                    stmt.close();
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
        return  id;
    }
    public static int Insert(int id,String UserName)
    {
        Connection conn=null;
        PreparedStatement stmt=null;
        Connection con = null;
        PreparedStatement st = null;
        int reselut = 0;
        try {
            Class.forName(DBDRIVER).newInstance();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        try{
            conn = DriverManager.getConnection(DBURL,DBUSER,DBPASSWORD);
            Log.i("aaa", "连接成功");
            String sql = "insert into  ProfilePool.ProfileTable(ID,UserName) values(?,?);";
            stmt= conn.prepareStatement(sql);
            stmt.setString(2,UserName);
            stmt.setInt(1,id);
            reselut = stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if(conn!=null){
                try {
                    conn.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            if(stmt != null)
            {
                try {
                    stmt.close();
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
        return  reselut;
    }
    public static List<Commodity> Adapter ()
    {
        List<Commodity> commodityList = new ArrayList<>();
        Connection conn=null;
        PreparedStatement stmt=null;
        Connection con = null;
        PreparedStatement st = null;
        int reselut = 0;
        try {
            Class.forName(DBDRIVER).newInstance();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        try{
            conn = DriverManager.getConnection(DBURL,DBUSER,DBPASSWORD);
            Log.i("ccc", "连接成功");
            String sql = "select * from CommodityPool.CommodityTable;";
            stmt= conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {
                Commodity tmp = new Commodity();
                tmp.setTitle(rs.getString("Name"));
                tmp.setPrice(rs.getFloat("Price"));
                if(rs.getString("Type").equals("S"))
                {
                    tmp.setCategory("学习用品");
                }
                if(rs.getString("Type").equals("E"))
                {
                    tmp.setCategory("电子产品");
                }
                if(rs.getString("Type").equals("L"))
                {
                    tmp.setCategory("生活用品");
                }
                if(rs.getString("Type").equals("P"))
                {
                    tmp.setCategory("体育用品");
                }
                commodityList.add(tmp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if(conn!=null){
                try {
                    conn.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            if(stmt != null)
            {
                try {
                    stmt.close();
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
        return commodityList;
    }
    public static List<Commodity> Search(String Type)
    {
        List<Commodity> commodityList = new ArrayList<>();
        Connection conn=null;
        PreparedStatement stmt=null;
        Connection con = null;
        PreparedStatement st = null;
        int reselut = 0;
        try {
            Class.forName(DBDRIVER).newInstance();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        try{
            conn = DriverManager.getConnection(DBURL,DBUSER,DBPASSWORD);
            Log.i("ccc", "连接成功");
            String sql = "select * from CommodityPool.CommodityTable;";
            stmt= conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {
                Commodity tmp = new Commodity();
                if(rs.getString("Type").equals(Type)) {
                    tmp.setTitle(rs.getString("Name"));
                    tmp.setPrice(rs.getFloat("Price"));
                    if (rs.getString("Type").equals("S")) {
                        tmp.setCategory("学习用品");
                    }
                    if (rs.getString("Type").equals("E")) {
                        tmp.setCategory("电子产品");
                    }
                    if (rs.getString("Type").equals("L")) {
                        tmp.setCategory("生活用品");
                    }
                    if (rs.getString("Type").equals("P")) {
                        tmp.setCategory("体育用品");
                    }
                    commodityList.add(tmp);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if(conn!=null){
                try {
                    conn.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            if(stmt != null)
            {
                try {
                    stmt.close();
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
        return commodityList;
    }
    public static boolean CheckInputStuName(String Name)
    {
        Connection conn=null;
        PreparedStatement stmt=null;
        try {
            Class.forName(DBDRIVER).newInstance();
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
        try{
            conn = DriverManager.getConnection(DBURL,DBUSER,DBPASSWORD);
            Log.i("ddd", "连接成功");
            String sql = "select * from AccountPool.UserTable;";
            stmt= conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            boolean flag = false;
            while(rs.next()) {
                //System.out.println("Stud" + rs.getString("count(*)"));
                if (rs.getString("UserName").equals(Name)) flag = true;
                //System.out.println(rs.getString("UserName"));
            }
            System.out.println(flag);
            if(flag) return false;
            else return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        finally {
            if(conn!=null){
                try {
                    conn.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            if(stmt != null)
            {
                try {
                    stmt.close();
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
}
