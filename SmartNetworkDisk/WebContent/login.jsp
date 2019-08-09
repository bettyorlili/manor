<%@ include file="head.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="js/jquery-3.2.1.js"></script>
</head>

<body style="text-align:center;margin-bottom:100px;">
           <div class="navbar" >
             <div class="navbar-inner">
               <a class="brand" href="#" style="margin-left:200px;">2016级网盘</a>
             </div>
           </div>
           <div  style="text-align:left;margin:0px auto;margin-top:50px; width:1200px;height:500px;">
                      <div style="float:left;width:800px; height:100%;background:#009900">
                      </div>
                         <div style="float:left;width:400px; height:100%; background:#00CC33">
                           <fieldset>
                           <form id="form" action="LoginServlet" method="post" onsubmit="return checkUser1()">
								<input type="hidden" name="operate" id="operate">
								帐号：<input type="text" name="username" id="username"><br>
								密码：<input type="password" name="password" id="password"><br>
								<input type="submit" value="注册">
								<input id="login" type="button" value="登陆" onclick="checkUser2()">
							</form>       
                           </fieldset>
                         </div>
            </div>
</body>
</html>
<script>
	//注册使用表单提交事件进行验证提交
    function checkUser1() {
    	var username=$('#username').val();
        var password=$('#password').val();
        if(''==username){
            alert('用户名不能为空！');
            return false;
        }
        if(''==password){
            alert('密码不能为空！');
            return false;
        }
        $('#operate').val('register');
        return true;
    }
    //登陆使用单击事件进行验证提交
    function checkUser2() {
        var username=$('#username').val();
        var password=$('#password').val();
        if(''==username){
            alert('用户名不能为空！');
            return false;
		}
		if(''==password){
            alert('密码不能为空！');
            return false;
		}
		$('#operate').val('login');
        $('#form').get(0).action='LoginServlet';
        $('#form').get(0).submit();
    }
</script>
