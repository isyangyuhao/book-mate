<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>借阅伴侣后台管理</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <link rel="stylesheet" href="assets/css/amazeui.min.css" />
    <link rel="stylesheet" href="assets/css/amazeui.datatables.min.css" />
    <link rel="stylesheet" href="assets/css/app.css">
    <script src="assets/js/jquery.min.js"></script>
    
</head>

<body data-type="login">
    <script src="assets/js/theme.js"></script>
    <div class="am-g tpl-g">
        <div class="tpl-login">
            <div class="tpl-login-content">
            	<div>
            		<img src="assets/img/index-logo.png" style="width:30px; height: 30px; float: left; margin-top:14px; margin-left: 15px; margin-right: 10px;">
            		<p style="font-size:24px; color: #cfcfcf; line-height: 2.4em;">借阅伴侣后台管理</p>
            	</div>
            	<c:if test="${requestScope.adminLoginError == 1 }">
            		<span style="color:#f44336;">用户名或密码输入有误!请检查后重新输入</span>
            	</c:if>
                <form autocomplete="off" class="am-form tpl-form-line-form" action="admin-index-login-execute" method="post">
                    <div class="am-form-group">
                        <input type="text" class="tpl-form-input" id="user-name" placeholder="请输入管理员名" name="name">
                    </div>
                    <div class="am-form-group">
                        <input type="password" class="tpl-form-input" id="user-name" placeholder="请输入密码" name="password">
                    </div>
					<br>
                    <div class="am-form-group">
                        <button type="submit" class="am-btn am-btn-primary  am-btn-block tpl-btn-bg-color-success  tpl-login-btn">登录</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <script src="assets/js/amazeui.min.js"></script>
    <script src="assets/js/app.js"></script>
</body>

</html>