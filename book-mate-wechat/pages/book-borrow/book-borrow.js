// pages/book-borrow/book-borrow.js
Page({
  data:{
    codeShow: false,
    progress: true,
    title: "加载中",
    borrowId: 0,
    errorMsg: "",
    credit: 80
  },
  onLoad:function(options){
    var that = this;
    var bookId = options.id;
    var userId = getApp().globalData.user.userId;
    var userCredit = getApp().globalData.user.userCredit;
    this.setData({credit: userCredit});
    wx.request({
      url: getApp().globalData.url + "api-scan-borrow-book/" + userId + "/" + bookId,
      data: {},
      method: 'GET',
      success: function (res) {
        that.setData({borrowId: res.data});
      }
    })    
    var that = this;
    setTimeout(function() {
      that.setData({codeShow: true, progress: false, title: "请向管理员出示下方的借书码", 
        errorMsg: "借书错误！请检查该书是否已被借阅或已超出借书上限"});
        var timer = setInterval(function() {
          wx.request({
            url: getApp().globalData.url + 'api-scan-borrow-byid/' + that.data.borrowId,
            data: {},
            method: 'GET',
            success: function (res) {
              if (res.data.borrowStatus == 1) {
                clearTimeout(timer);
                wx.showToast({
                  title: '借书成功',
                  icon: 'success',
                  duration: 2000
                })
                setTimeout(function() {
                  wx.navigateBack({
                    delta: 2
                  })
                }, 2000)
              }
            }
          })
        }, 1000)
    }, 4000)
  }
})