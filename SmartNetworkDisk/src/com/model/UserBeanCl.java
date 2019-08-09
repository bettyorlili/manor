package com.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class UserBeanCl {
        private Statement sm = null;
        private Connection ct = null;
        private ResultSet rs = null;
        private PreparedStatement ps =null;
        
        
        public void close(){
                try {
                        
                        
                if(sm != null){        
                        sm.close();
                        sm = null;
                }
                
                if(ct != null){
                        ct.close();
                        ct = null;
                }
                
                
                if(rs != null){
                        rs.close();
                        rs = null;
                }
                
                
                }
                catch (SQLException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }
        }
        //注册用户
    	public boolean register(String user, String password) {
    		 boolean b = false;
             try {
            	 	 Integer row = 0;
                     //获得连接
                     ct = new ConnDB().getConn();
                   //判断是否为已有用户
                     sm = ct.createStatement();
                     rs = sm.executeQuery("select * from user where username="+"'"+user+"'");
	                 if(rs.next()){
	                         //说明用户已经存在
	                         b = false;
	                         sm.close();
	                         rs.close();
	                         ct.close();
	                         return b;
	                 }else{
	                     String sql = "insert into user(username,password) values(?,?)";
	                     ps = ct.prepareStatement(sql);
	                     ps.setString(1, user);
	         			 ps.setString(2, password);
	         			 row = ps.executeUpdate();
	         			if (row > 0) {
	         				b =  true;
	         			}
	         			else
	         			{
	         				b = false;
	         			}
	                     ct.close();
	                     ps.close();
	                 }
             } catch (SQLException e) {
                     e.printStackTrace();
             }finally{
                     this.close();
             }
             return b;
    	}
        
        
        //检查登录用户是否合法
                public boolean checkUser(String user, String password){
                        boolean b = false;                
                        try {
                                //获得连接
                                ct = new ConnDB().getConn();
                                //创建statement
                                sm = ct.createStatement();
                                rs = sm.executeQuery("select * from user where username="+"'"+user+"'");
                                if(rs.next()){
                                        //说明用户存在
                                        String pwd = rs.getString(3);
                                        if(password.equals(pwd)){
                                                //说明密码正确
                                                b = true;
                                        }else{
                                                b = false;
                                        }
                                }else{
                                        b = false;
                                }      
                                ct.close();
                                sm.close();
                                rs.close();
                        } catch (SQLException e) {
                                e.printStackTrace();
                        }finally{
                                this.close();
                        }
                        return b;
                }


                
}