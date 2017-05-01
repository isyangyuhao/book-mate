// pages/main/main.js
Page({
  data:{},
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