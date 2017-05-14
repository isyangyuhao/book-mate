// pages/book-detail/book-detail.js
Page({
  data:{
    scanCode: 0,
    book: {}
  },
  onLoad:function(options){
    var that = this;
    if (options.scanCode == 1) {
      this.setData({scanCode: 1});
    }
    let bookId = options.id;
    wx.request({
      url: getApp().globalData.url + "api-book-book-byid/" + bookId,
      data: {},
      method: 'GET', 
      success: function(res){
        that.setData({book: res.data});
      }
    })
  },
  addForumBtn: function() {
    wx.navigateTo({
      url: '/pages/forum-add/forum-add?bookId=' + this.data.book.bookId
    })
  }
})