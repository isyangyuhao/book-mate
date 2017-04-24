// pages/user/user.js
Page({
  data: {
    userInfo: {}
  },
  onLoad: function (options) {
    var app = getApp();
    app.getUserInfo();
    var that = this;
    wx.getUserInfo({
      success: function (res) {
        var userInfo = res.userInfo;
        that.setData({ userInfo: userInfo });
      }
    })
  },
  exitBtnOnClick: function() {
    wx.redirectTo({
      url: '/pages/index/index'
    })
  }
})