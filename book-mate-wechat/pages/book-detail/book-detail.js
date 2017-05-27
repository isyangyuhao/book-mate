// pages/book-detail/book-detail.js
Page({
  data: {
    scanCode: 0,
    book: {},
    bookRecommend: {}
  },
  onLoad: function (options) {
    var that = this;
    if (options.scanCode == 1) {
      this.setData({ scanCode: 1 });
    }
    let bookId = options.id;
    wx.request({
      url: getApp().globalData.url + "api-book-book-byid/" + bookId,
      data: {},
      method: 'GET',
      success: function (res) {
        that.setData({ book: res.data });
      }
    })
    wx.request({
      url: getApp().globalData.url + "api-record-browse/" + getApp().globalData.user.userId + "/" + bookId,
      data: {},
      method: 'GET',
      success: function (res) {
        
      }
    })
    wx.request({
      url: getApp().globalData.url + "api-book-recommend/" + bookId,
      data: {},
      method: 'GET',
      success: function (res) {
        that.setData({ bookRecommend: res.data });
      }
    })
  },
  addForumBtn: function () {
    wx.navigateTo({
      url: '/pages/forum-add/forum-add?bookId=' + this.data.book.bookId
    })
  },
  borrowBtn: function (event) {
    var bookId = event.currentTarget.id;
    wx.showModal({
      title: '请问您是否借阅本书',
      content: '借阅后请自觉向管理员出示借书码',
      confirmColor: '#4db6ac',
      success: function (res) {
        if (res.confirm) {
          wx.navigateTo({
            url: '/pages/book-borrow/book-borrow?id=' + bookId,
          })
        } else if (res.cancel) {

        }
      }
    })
  },
  bookDetailBtn: function (event) {
    var bookId = event.currentTarget.id;
    wx.redirectTo({
      url: '/pages/book-detail/book-detail?id=' + bookId,
    })
  }
})