// pages/main/main.js
Page({
  data:{},
  onLoad: function() {
    if (getApp().globalData.user.userCredit >= 90) {
      wx.setNavigationBarTitle({ title: '借阅伴侣+' });
    }
  },
  onPullDownRefresh: function() {
    wx.showNavigationBarLoading();
    wx.stopPullDownRefresh();
    wx.hideNavigationBarLoading();
  },
  bookDetailBtn: function() {
    wx.navigateTo({
      url: '/pages/book-detail/book-detail?scanCode=0'
    })
  }
})