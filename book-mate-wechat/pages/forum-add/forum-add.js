// pages/forum-add/forum-add.js
Page({
  data: {
    bookId: null,
    userId: null
  },
  onLoad: function (options) {
    var bookId = options.bookId;
    var userId = getApp().globalData.user.userId;
    this.setData({ bookId: bookId, userId: userId });
  },
  cancelBtn: function () {
    wx.navigateBack({
      delta: 1
    })
  },
  formSubmit: function (e) {
    var title = e.detail.value.title;
    var content = e.detail.value.content;
    var userId = this.data.userId;
    var bookId = this.data.bookId;
    wx.request({
      url: getApp().globalData.url + "api-forum-add-forum",
      data: { title: title, content: content, userId: userId, bookId: bookId },
      method: 'GET',
      success: function (res) {
        wx.navigateBack({
          delta: 1
        })
      }
    })
  }
})