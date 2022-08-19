<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>借阅伴侣后台管理</title>
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <script src="assets/js/echarts.min.js"></script>
    <link rel="stylesheet" href="assets/css/amazeui.min.css" />
    <link rel="stylesheet" href="assets/css/amazeui.datatables.min.css" />
    <link rel="stylesheet" href="assets/css/app.css">
    <script src="assets/js/jquery.min.js"></script>
	<script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
	<script src="http://code.highcharts.com/highcharts.js"></script>    
    <style type="text/css">
    	table.dataTable thead .sorting_asc:after {
    		display: none;
    	}
    	table.dataTable thead .sorting:after {
    		display: none;
    	}
    </style>
    
</head>

<body data-type="index">
    <div class="am-g tpl-g">
        
        <!-- 加载顶部导航栏 -->
        <jsp:include page="header.jsp"/>
        
        <!-- 加载侧边导航栏 -->
        <jsp:include page="list.jsp"/>

        <!-- 内容区域 -->
        <div class="tpl-content-wrapper">
            <div class="container-fluid am-cf">
                <div class="row">
                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-9">
                        <div class="page-header-heading"><span class="am-icon-home page-header-heading-icon"></span> "借阅伴侣"控制台主页</div>
                    </div>
                </div>

            </div>
            <div class="row-content am-cf">
                <div class="row  am-cf">
                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-4">
                        <div class="widget am-cf">
                            <div class="widget-head am-cf">
                                <div class="widget-title am-fl">相关数据检索</div>
                                <div class="widget-function am-fr">
                                </div>
                            </div>
                            <div class="widget-body am-fr">
                                <div class="am-fl">
                                    <div class="widget-fluctuation-description-amount text-success" am-cf style="font-size: 14px;">
                                        论坛发帖量: ${requestScope.forumCount } | 用户评论量: ${requestScope.forumCommentCount }
                                        <br>
                                        用户借阅量: ${requestScope.borrowCount } | 用户还书量: ${requestScope.returnCount } 
<!--                                         <button class="widget-fluctuation-tpl-btn">
                        <i class="am-icon-calendar"></i>  更多记录
                      </button> -->
                                    </div>
                                </div>
                                <div class="am-fr am-cf">
                                    <div class="widget-fluctuation-description-amount text-success" am-cf style="font-size: 18px;">
                                        
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                    <div class="am-u-sm-12 am-u-md-6 am-u-lg-4">
                        <div class="widget widget-primary am-cf">
                            <div class="widget-statistic-header" >
                                当前总用户量
                            </div>
                            <div class="widget-statistic-body">
                                <div class="widget-statistic-value">
                                    ${requestScope.userCount }
                                </div>
                            </div>
                             <div class="widget-statistic-description">
                              	<a href="${pageContext.request.contextPath }/admin-user-list-show?page=1" style="color:#9cdcf2;">点击查看用户列表与更多用户相关操作</a>    
                              </div>
                            <span class="widget-statistic-icon am-icon-user"></span>
                        </div>
                    </div>
                    <div class="am-u-sm-12 am-u-md-6 am-u-lg-4">
                        <div class="widget widget-purple am-cf">
                            <div class="widget-statistic-header">
                                当前图书总量
                            </div>
                            <div class="widget-statistic-body">
                                <div class="widget-statistic-value">
                                   ${requestScope.bookCount }
                                </div>
                                <div class="widget-statistic-description">
                              	<a href="${pageContext.request.contextPath }/admin-book-list-show?page=1" style="color:#ded5e7;">点击查看图书列表与详细图书信息</a>     
                              </div>
                                <span class="widget-statistic-icon am-icon-leanpub"></span>
                            </div>
                        </div>
                    </div>
                </div>
				<!--两个图表-->
                <div class="row am-cf">
                    <div class="am-u-sm-12 am-u-md-12">
                        <div class="widget am-cf">
                            <div class="widget-head am-cf">
                                <div class="widget-title am-fl">近期浏览与借阅流量 <span style="color: #666;">(展示用表)</span></div>
                            </div><div id="container" style="width: 750px; height: 400px; margin: 0 auto"></div>
                            <div class="widget-body-md widget-body tpl-amendment-echarts am-fr" id="tpl-echarts" style="display:none;">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row am-cf">
                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-4 widget-margin-bottom-lg ">
                        <div class="tpl-user-card am-text-center widget-body-lg">
                            <div class="tpl-user-card-title" style="font-size: 20px;">
                                	员工证
                            </div>
                            
                            <img class="achievement-image" src="${pageContext.request.contextPath }/adminPhoto/admin.png" alt="" width=80 height=80>
                            <div class="achievement-subheading">
                                <p style="font-size:18px; font-weight: bold;">${sessionScope.admin.adminUsername }</p>
                            </div>
                            <div class="achievement-description" style="text-align: left; margin-left: 20px;">
                                	<p style="font-size:16px">管理员编号：${sessionScope.admin.adminId }
                                	<br>
                                	负责职能：${sessionScope.admin.adminWork }
                                	<br>
                                	联系邮箱：${sessionScope.admin.adminEmail }
                                	</p>
                            </div>
                        </div>
                    </div>
                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-8 widget-margin-bottom-lg">
                        <div class="widget am-cf widget-body-lg">
                            <div class="widget-body  am-fr">
                                <div class="am-scrollable-horizontal ">
                                    <table width="100%"
                                    	 class="am-table am-table-compact am-text-nowrap tpl-table-black " id="">
                                        <thead>
                                            <tr>
                                                <th>管理员通知</th>
                                                <th>操作</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${requestScope.adminMessages }" end="5" var="adminMessage">
                                        	<tr class="gradeX">
                                                <td>${adminMessage.adminMessageTitle}</td>
                                                <td>
                                                    <div class="tpl-table-black-operation">
                                                        <a href="${pageContext.request.contextPath }/admin-index-message-show/${adminMessage.adminMessageId }">
                                                            <i class="am-icon-list-alt"></i> 查看
                                                        </a>
                                                    </div>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                               
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
<script src="assets/js/amazeui.min.js"></script>
<script src="assets/js/amazeui.datatables.min.js"></script>
<script src="assets/js/dataTables.responsive.min.js"></script>
<script src="assets/js/app.js"></script>
<script language="JavaScript" src="${pageContext.request.contextPath }/assets/js/chart.js"></script>
</body>

</html>