// pages/book-return/book-return.js
Page({
  data: {
    codeShow: false,
    progress: true,
    title: "加载中",
    borrowId: 0,
    errorMsg: "",
    redit: 80
  },
  onLoad: function (options) {
    var that = this;
    var bookId = options.id;
    var userId = getApp().globalData.user.userId;
    var userCredit = getApp().globalData.user.userCredit;
    this.setData({credit: userCredit});
    wx.request({
      url: getApp().globalData.url + "api-scan-return/" + userId + "/" + bookId,
      data: {},
      method: 'GET',
      success: function (res) {
        that.setData({ borrowId: res.data });
      }
    })
    var that = this;
    setTimeout(function () {
      that.setData({
        codeShow: true, progress: false, title: "请向管理员出示下方的还书码",
        errorMsg: "还书错误！"
      });
      var timer = setInterval(function () {
        wx.request({
          url: getApp().globalData.url + 'api-scan-borrow-byid/' + that.data.borrowId,
          data: {},
          method: 'GET',
          success: function (res) {
            if (res.data.borrowStatus == 2) {
              clearTimeout(timer);
              wx.showToast({
                title: '还书成功',
                icon: 'success',
                duration: 2000
              })
              setTimeout(function () {
                // wx.navigateBack({
                //   delta: 2
                // })
                wx.redirectTo({
                  url: '/pages/book-grade/book-grade?id=' + that.data.borrowId,
                })
              }, 2000)
            }
          }
        })
      }, 1000)
    }, 4000)
  }
})