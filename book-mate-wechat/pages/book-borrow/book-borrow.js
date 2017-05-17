// pages/book-borrow/book-borrow.js
Page({
  data:{
    codeShow: false,
    progress: true,
    title: "加载中"
  },
  onLoad:function(options){
    var that = this;
    setTimeout(function() {
      that.setData({codeShow: true, progress: false, title: "请向管理员出示下方的借书码"});
    }, 4000)
  }
})