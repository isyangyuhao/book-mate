<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
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

<body data-type="widgets">
    <script src="assets/js/theme.js"></script>
    <div class="am-g tpl-g">
  		
  		<!-- 加载顶部导航栏 -->
        <jsp:include page="../index/header.jsp"/>
        
        <!-- 加载侧边导航栏 -->
        <jsp:include page="../index/list.jsp"/>
  		
        <!-- 内容区域 -->
        <div class="tpl-content-wrapper">
            <div class="row-content am-cf">
                <div class="row">
                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
                        <div class="widget am-cf">
                            <div class="widget-head am-cf">
                                <div class="widget-title  am-cf">管理员列表</div>
                            </div>
                            <div class="widget-body  am-fr">
                                <div class="am-u-sm-12 am-u-md-6 am-u-lg-6">
                                    <div class="am-form-group"></div>
                                </div>
                                <div class="am-u-sm-12 am-u-md-6 am-u-lg-3">
                                    <div class="am-form-group tpl-table-list-select">
                                    </div>
                                </div>
                                <div class="am-u-sm-12 am-u-md-12 am-u-lg-3">
                                <form action="${pageContext.request.contextPath }/admin-admin-searchadmin-show" method="get">
                                    <div class="am-input-group am-input-group-sm tpl-form-border-form cl-p">
                                        <input type="text" class="am-form-field" name="name">
                                        <span class="am-input-group-btn">
            								<button class="am-btn  am-btn-default am-btn-success tpl-table-list-field am-icon-search" type="submit"></button>
          								</span>
                                    </div>
                                </form>
                                </div>
                                <div class="am-u-sm-12">
                                    <table width="100%" class="am-table am-table-compact am-table-striped tpl-table-black ">
                                        <thead>
                                            <tr>
                                                <th>管理员名</th>
                                                <th>邮箱</th>
                                                <th>时间</th>
                                                <th>操作</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${requestScope.pageAdmins }" var="admin">
                                            <tr class="gradeX">
                                                <td class="am-text-middle">${admin.adminUsername }</td>
                                                <td class="am-text-middle">${admin.adminEmail }</td>
                                                <td class="am-text-middle">
                                                	<fmt:formatDate value="${admin.adminCreateTime }" pattern="yyyy-MM-dd hh:mm"/>
                                                </td>
                                                <td class="am-text-middle">
                                                    <div class="tpl-table-black-operation">
                                                        <a href="admin-admin-editadmin-show/${admin.adminId }">
                                                            <i class="am-icon-pencil"></i> 编辑
                                                        </a>
                                                        <a href="admin-admin-removeadmin-execute/${admin.adminId }" class="tpl-table-black-operation-del">
                                                            <i class="am-icon-trash"></i> 删除
                                                        </a>
                                                    </div>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                     </tbody>
                                    </table>
                                </div>
                                <c:if test="${requestScope.pagePoint != null}">
                                <div class="am-u-lg-12 am-cf" style="font-size:14px;">
                                    <div class="am-fr">
                                        <ul class="am-pagination tpl-pagination">
                                            <li><a href="${pageContext.request.contextPath }/admin-admin-list-show?page=${requestScope.pagePoint - 1 }">«</a></li>
                                            <li><a>当前第${requestScope.pagePoint }页(共${requestScope.pageMax }页)</a></li>
                                            <li><a href="#!" id="skipBtn"> 跳转到&nbsp;<input type="text" name="pagePoint" style="width:20px;height: 14px; color:black; font-size: 10px;">&nbsp;页</a></li>
                                            <li><a href="${pageContext.request.contextPath }/admin-admin-list-show?page=${requestScope.pagePoint + 1 }">»</a></li>
                                        </ul>
                                    </div>
                                </div>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="assets/js/amazeui.min.js"></script>
    <script src="assets/js/app.js"></script>
    <script type="text/javascript">
    
    $(function() {
    	$("#skipBtn").on("click", function() {
    		var page = $("#skipBtn > input").val();
			if (page == "") {
				page = 1;
			}
			window.location = "${pageContext.request.contextPath}/admin-admin-list-show?page=" + page;
    	});
    	$("#skipBtn > input").on("click", function() {
    		return false;
    	});
    })	
    
    </script>
</body>

</html>