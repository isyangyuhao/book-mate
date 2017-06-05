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
    <link rel="stylesheet" href="${pageContext.request.contextPath }/assets/css/amazeui.min.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath }/assets/css/amazeui.datatables.min.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath }/assets/css/app.css">
    <script src="${pageContext.request.contextPath }/assets/js/jquery.min.js"></script>
</head>

<body data-type="widgets">
    <script src="${pageContext.request.contextPath }/assets/js/theme.js"></script>
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
                                <div class="widget-title am-fl">修改图书</div>
                                <div class="widget-function am-fr">
                                </div>
                            </div>
                            <div class="widget-body am-fr">

                                <form autocomplete="off" class="am-form tpl-form-line-form" action="${pageContext.request.contextPath }/admin-book-editbook-execute" method="post">
                                    <input type="hidden" name="bookId" value="${requestScope.book.bookId }">
                                    <div class="am-form-group">
                                        <label for="book-name" class="am-u-sm-3 am-form-label">图书编号</label>
                                        <div class="am-u-sm-9">
                                            <input type="text" class="tpl-form-input" id="book-number" placeholder="" name="bookNumber" value="${requestScope.book.bookNumber }">
                                        </div>
                                    </div>
                                    
                                    <div class="am-form-group">
                                        <label for="book-name" class="am-u-sm-3 am-form-label">图书名称</label>
                                        <div class="am-u-sm-9">
                                            <input type="text" class="tpl-form-input" id="book-name" placeholder="" name="bookName" value="${requestScope.book.bookName }">
                                        </div>
                                    </div>
                                    
                                    <div class="am-form-group">
                                        <label for="book-author" class="am-u-sm-3 am-form-label">图书作者</label>
                                        <div class="am-u-sm-9">
                                            <input type="text" class="tpl-form-input" id="book-author" placeholder="" name="bookAuthor" value="${requestScope.book.bookAuthor }">
                                        </div>
                                    </div>
                                    
                                    <div class="am-form-group">
                                        <label for="book-press" class="am-u-sm-3 am-form-label">出版社</label>
                                        <div class="am-u-sm-9">
                                            <input type="text" class="tpl-form-input" id="book-press" placeholder="" name="bookPress" value="${requestScope.book.bookPress }">
                                        </div>
                                    </div>
                                    
                                    <div class="am-form-group">
                                        <label for="book-classifyOne" class="am-u-sm-3 am-form-label">一级分类</label>
                                        <div class="am-u-sm-9">
                                            <input type="text" class="tpl-form-input" id="book-classifyOne" placeholder="" name="bookClassifyOne" value="${requestScope.book.bookClassifyOne }"></input>
                                        </div>
                                    </div>
                                    
                                    <div class="am-form-group">
                                        <label for="book-classifyTwo" class="am-u-sm-3 am-form-label">二级分类</label>
                                        <div class="am-u-sm-9">
                                            <input type="text" class="tpl-form-input" id="book-classifyTwo" placeholder="" name="bookClassifyTwo" value="${requestScope.book.bookClassifyTwo }"></input>
                                        </div>
                                    </div>
                                    
                                    <div class="am-form-group">
                                        <label for="book-classifyOne" class="am-u-sm-3 am-form-label">图像网络地址</label>
                                        <div class="am-u-sm-9">
                                            <input type="text" class="tpl-form-input" id="" placeholder="" name="bookBigImage" value="${requestScope.book.bookImageBig }"></input>
                                        </div>
                                    </div>
                                    
                                    <div class="am-form-group">
                                        <label for="book-classifyTwo" class="am-u-sm-3 am-form-label">缩略图网络地址分类</label>
                                        <div class="am-u-sm-9">
                                            <input type="text" class="tpl-form-input" id="" placeholder="" name="bookSmallImage" value="${requestScope.book.bookImageSmall }"></input>
                                        </div>
                                    </div> 
                                    
                                    <div class="am-form-group">
                                        <label for="book-desc" class="am-u-sm-3 am-form-label">图书描述</label>
                                        <div class="am-u-sm-9">
                                            <textarea class="" rows="10" id="book-desc" placeholder="" name="bookDesc">${requestScope.book.bookDesc }</textarea>
                                        </div>
                                    </div>
                                    
                                    <div class="am-form-group">
                                        <label for="book-address" class="am-u-sm-3 am-form-label">图书存放地址</label>
                                        <div class="am-u-sm-9">
                                            <input type="text" class="tpl-form-input" id="book-address" placeholder="" name="bookAddress" value="${requestScope.book.bookAddress }"></input>
                                        </div>
                                    </div>
                                    
                                    <div class="am-form-group">
                                        <label for="book-sum" class="am-u-sm-3 am-form-label">图书数量</label>
                                        <div class="am-u-sm-9">
                                            <input type="text" class="tpl-form-input"  id="book-sum" placeholder="" name="bookSum" value="${requestScope.book.bookSum }"></input>
                                        </div>
                                    </div>
                                    
                                    <div class="am-form-group">
                                        <label for="book-residue" class="am-u-sm-3 am-form-label">图书剩余</label>
                                        <div class="am-u-sm-9">
                                            <input type="text" class="tpl-form-input"  id="book-residue" placeholder="" name="bookResidue" value="${requestScope.book.bookResidue }"></input>
                                        </div>
                                    </div>

                                    <div class="am-form-group">
                                        <div class="am-u-sm-9 am-u-sm-push-3">
                                            <button type="submit" class="am-btn am-btn-primary tpl-btn-bg-color-success ">提交</button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    
    
    
    
    
    
    </div>
    <script src="${pageContext.request.contextPath }/assets/js/amazeui.min.js"></script>
    <script src="${pageContext.request.contextPath }/assets/js/app.js"></script>
</body>

</html>
