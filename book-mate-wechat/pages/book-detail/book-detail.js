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
  },
  reserveBtn: function (event) {
    var bookId = event.currentTarget.id;
    var userId = getApp().globalData.user.userId;
    wx.showModal({
      title: '提示',
      content: '请问您确定预订本书吗?',
      confirmColor: '#4db6ac',
      success: function (res) {
        if (res.confirm) {
          wx.request({
            url: getApp().globalData.url + "api-scan-reserve-book/" + bookId + "/" + userId,
            data: {},
            method: 'GET',
            success: function (res) {
              if (res.data == 1) {
                wx.showToast({
                  title: '预订成功!',
                })
                wx.redirectTo({
                  url: '/pages/user-borrow/user-borrow',
                })
              }
            }
          })
        } else if (res.cancel) {

        }
      }
    })
  }
})