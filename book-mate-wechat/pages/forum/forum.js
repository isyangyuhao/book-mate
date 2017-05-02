// pages/forum/forum.js
Page({
  data: {},
  showForumContentBtn: function () {
    wx.navigateTo({
      url: '/pages/forum-content/forum-content'
    })
  },
  showBookDetailBtn: function () {
    wx.navigateTo({
      url: '/pages/book-detail/book-detail?scanCode=0'
    })
  }
})