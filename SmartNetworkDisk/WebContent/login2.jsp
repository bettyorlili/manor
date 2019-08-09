<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="js/jquery-3.2.1.js"></script>
</head>
<body>
	<form id="form" action="LoginServlet" method="post" onsubmit="return checkUser1()">
		<input type="hidden" name="operate" id="operate">
		帐号：<input type="text" name="username" id="username"><br>
		密码：<input type="password" name="password" id="password"><br>
		<input type="submit" value="注册">
		<input id="login" type="button" value="登陆" onclick="checkUser2()">
	</form>
</body>
</html>
<script>
	//注册使用表单提交事件进行验证提交
    function checkUser1() {
    	var username=$('#username').val();
    	debugger;
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
