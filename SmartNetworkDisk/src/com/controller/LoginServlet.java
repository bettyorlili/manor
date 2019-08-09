package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;

import com.model.*;

/**
* Servlet implementation class ListServlet
*/
public class LoginServlet extends HttpServlet {


        /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		/**
         * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
         */
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                this.doPost(request, response);
        }

        /**
         * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
         */
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                String operate = request.getParameter("operate");
                UserBeanCl ubc = new UserBeanCl();
                if(!"".equals(username)&&!"".equals(password)&&!"".equals(operate)) {
        			if("register".equals(operate)) {
        				Boolean b = ubc.register(username, password);
        				if(b) {
        					//创建用户在hdfs上的文件夹
        					Configuration conf = HdfsDAO.config();
		                    HdfsDAO hdfs = new HdfsDAO(conf);
		                    hdfs.mkdirs("/SmartNFS/"+username);
        					request.getRequestDispatcher("registersuccess.jsp").forward(request, response);
        					System.out.println(username+"用户注册成功");
        				}else {
        					System.out.println(username+"用户注册失败");
        					response.sendRedirect("login.jsp");
        				}
        			}else if("login".equals(operate)) 
        			{
			              if(ubc.checkUser(username, password)){
			                      //用户合法，跳转到界面
			                      HttpSession session = request.getSession(); 
			                      session.setAttribute("username", username);
			                      Configuration conf = HdfsDAO.config();
			                      HdfsDAO hdfs = new HdfsDAO(conf);
			                      FileStatus[] list = hdfs.ls("/SmartNFS/"+username);
			                      request.setAttribute("list",list);
			                      request.getRequestDispatcher("index.jsp").forward(request, response);
		        				  System.out.println(username+"用户登陆成功");
			              }else{
	        					  System.out.println(username+"用户登陆失败");
			                      //用户不合法，调回登录界面，并提示错误信息
			                      request.getRequestDispatcher("login.jsp").forward(request, response);
			              }
        			}else {
    					System.out.println(username+"用户发生未知错误1");
                        request.getRequestDispatcher("login.jsp").forward(request, response);
        			}
        		}else {			
        			System.out.println(username+"用户发生未知错误2");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
        		}
                
//                
//                if(ubc.checkUser(username, password)){
//                        //用户合法，跳转到界面
//                        HttpSession session = request.getSession(); 
//                        session.setAttribute("username", username);
//                        
//                        Configuration conf = HdfsDAO.config();
//                        HdfsDAO hdfs = new HdfsDAO(conf);
//                        FileStatus[] list = hdfs.ls("/SmartNFS/"+username);
//                        request.setAttribute("list",list);
//                        request.getRequestDispatcher("index.jsp").forward(request, response);
//                }else{
//                        //用户不合法，调回登录界面，并提示错误信息
//                        request.getRequestDispatcher("login.jsp").forward(request, response);
//                }

                
         
        }

}