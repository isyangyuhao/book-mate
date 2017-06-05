<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>借阅伴侣后台管理</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <meta name="apple-mobile-web-app-title" content="Amaze UI" />
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
                <div class="tpl-login-title">注册管理员</div>
                <span class="tpl-login-content-info">
                  创建一个新的管理员
              </span>
				<form autocomplete="off" class="am-form tpl-form-line-form" action="admin-admin-register-execute" method="post">
                    <div class="am-form-group">
                        <input type="text" class="tpl-form-input" placeholder="管理员名" name="name">

                    </div>
                    <div class="am-form-group">
                        <input type="text" class="tpl-form-input" placeholder="邮箱" name="email">
                    </div>
                    <div class="am-form-group">
                        <input type="text" class="tpl-form-input" placeholder="管理员职能" name="work">
                    </div>
                    <div class="am-form-group">
                        <input type="password" class="tpl-form-input" placeholder="请输入密码" name="password">
                    </div>
                    <div class="am-form-group">
                        <input type="password" class="tpl-form-input" placeholder="再次输入密码" name="password2">
                    </div>
                    <div class="am-form-group">
                        <button type="submit" class="am-btn am-btn-primary  am-btn-block tpl-btn-bg-color-success  tpl-login-btn">提交</button>
                    </div>
                    <div class="am-form-group" style="text-align: center;">
						<a href="admin-index-index-show" style="color: #666; font-size: 14px;">返回控制台</a>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <script src="assets/js/amazeui.min.js"></script>
    <script src="assets/js/app.js"></script>
</body>

</html>