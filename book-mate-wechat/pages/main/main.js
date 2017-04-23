// pages/main/main.js
Page({
  data:{},
  onPullDownRefresh: function() {
    wx.showNavigationBarLoading();
    wx.stopPullDownRefresh();
    wx.hideNavigationBarLoading();
  }
})