// pages/books-search/books-search.js
Page({
  data:{
      books: {}
    },
  onLoad: function(options) {
    var search = options.search;
    var that = this;
    wx.showNavigationBarLoading();
    wx.request({
      url: getApp().globalData.url + 'api-book-book-bynamelike/' + search,
      data: {},
      method: 'GET',
      success: function(res){
        that.setData({books: res.data});
        console.log(res.data);
      }
    })
    wx.hideNavigationBarLoading();
  },
  bookDetailBtn: function (event) {
    var bookId = event.currentTarget.id;
    wx.navigateTo({
      url: '/pages/book-detail/book-detail?scanCode=0&id=' + bookId
    })
  }
})