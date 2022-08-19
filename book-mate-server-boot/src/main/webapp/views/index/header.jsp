<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- 顶部导航栏 -->
<script src="${pageContext.request.contextPath }/assets/js/theme.js"></script>
<header>
    <!-- logo -->
    <div class="am-fl tpl-header-logo">
        <img src="${pageContext.request.contextPath }/assets/img/index-logo.png" style="width:30px; height: 30px; float: left; margin-top:14px; margin-left: 15px;">
       	<div style="color: #cfcfcf; line-height: 57px; margin-left:5px; font-weight:bold; float: left;">借阅伴侣后台管理</div>
    </div>
    <!-- 右侧内容 -->
    <div class="tpl-header-fluid">
        <!-- 侧边切换 -->
        <div class="am-fl tpl-header-switch-button am-icon-list">
            <span>
        </span>
        </div>
        <!-- 搜索 -->
		
        <!-- 其它功能-->
        <div class="am-fr tpl-header-navbar">
            <ul>
                <!-- 欢迎语 -->
                <li class="am-text-sm tpl-header-navbar-welcome">
                    <a href="javascript:;">欢迎你, <span>${sessionScope.admin.adminUsername }</span> </a>
                </li>

                <!-- 退出 -->
                <li class="am-text-sm">
                    <a href="${pageContext.request.contextPath }/admin-index-exit-execute">
                        <span class="am-icon-sign-out"></span> 退出
                    </a>
                </li>
            </ul>
        </div>
    </div>
</header>