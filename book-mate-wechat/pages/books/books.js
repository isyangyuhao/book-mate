// pages/books/books.js
Page({
  data: {},
  scanBtnOnClick: function () {
    wx.scanCode({
      success: function (res) {
        console.log(res);
        wx.navigateTo({
          url: '/pages/book-detail/book-detail?scanCode=1'
        })
      }
    })
  },
  booksListBtn: function () {
    wx.navigateTo({
      url: '/pages/books-list/books-list'
    })
  }
})