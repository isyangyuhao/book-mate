// pages/user/user.js
Page({
  data: {
    userInfo: {}
  },
  onLoad: function (options) {
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
  },
  userForumBtn: function() {
    wx.navigateTo({
      url: '/pages/user-forum/user-forum'
    })
  },
  userCommentBtn: function() {
    wx.navigateTo({
      url: '/pages/user-comment/user-comment'
    })
  },
  userBorrowBtn: function() {
    wx.navigateTo({
      url: '/pages/user-borrow/user-borrow'
    })
  },
  userHistoryBtn: function() {
    wx.navigateTo({
      url: '/pages/user-history/user-history'
    })
  },
  userSettingBtn: function() {
    wx.navigateTo({
      url: '/pages/user-setting/user-setting'
    })
  }
})