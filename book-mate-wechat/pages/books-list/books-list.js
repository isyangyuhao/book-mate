// pages/books-list/books-list.js
Page({
  data:{},
  bookDetailBtn: function() {
    wx.navigateTo({
      url: '/pages/book-detail/book-detail?scanCode=0'
    })
  }
})