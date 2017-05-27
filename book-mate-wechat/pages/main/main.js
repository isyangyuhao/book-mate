// pages/main/main.js
Page({
  data: {
    creditGood: false,
    specialOn: false,
    messageBtnClass: 'top-tag-active',
    specialBtnClass: 'top-tag',
    user: {},
    borrowWillOverdue: {},
    borrowOverdue: {},
    hotBookList: {},
    recommendBookList: {},
    newBookList: {},
    forumBookList: {}
  },
  onLoad: function () {
    var that = this;
    //获取用户信息
    that.setData({user: getApp().globalData.user});
    //判断信用是否达标
    if (getApp().globalData.user.userCredit >= 90) {
      wx.setNavigationBarTitle({ title: '借阅伴侣+' });
      that.setData({creditGood: true});
    }
    //获取即将借阅到期的图书集合
    wx.request({
      url: getApp().globalData.url + 'api-index-getBorrowWillOverdue/' + that.data.user.userId,
      data: {},
      method: 'GET',
      success: function (res) {
        that.setData({borrowWillOverdue: res.data});
      }
    })
    //获取已经逾期未还的图书集合
    wx.request({
      url: getApp().globalData.url + 'api-index-getBorrowOverdue/' + that.data.user.userId,
      data: {},
      method: 'GET',
      success: function (res) {
        that.setData({ borrowOverdue: res.data });
      }
    })
    //获取热门图书
    wx.request({
      url: getApp().globalData.url + 'api-index-getHotBook',
      data: {},
      method: 'GET',
      success: function (res) {
        that.setData({ hotBookList: res.data });
      }
    })
    //获取推荐图书
    wx.request({
      url: getApp().globalData.url + 'api-index-getRecommendBook/' + that.data.user.userId,
      data: {},
      method: 'GET',
      success: function (res) {
        that.setData({ recommendBookList: res.data });
      }
    })
    //获取新书
    wx.request({
      url: getApp().globalData.url + 'api-index-getNewBook',
      data: {},
      method: 'GET',
      success: function (res) {
        that.setData({ newBookList: res.data });
      }
    })
    //获取书友图书
    wx.request({
      url: getApp().globalData.url + 'api-index-getForumBook',
      data: {},
      method: 'GET',
      success: function (res) {
        that.setData({ forumBookList: res.data });
      }
    })
  },
  onPullDownRefresh: function () {
    wx.showNavigationBarLoading();
    var that = this;
    //获取用户信息
    that.setData({ user: getApp().globalData.user });
    //判断信用是否达标
    if (getApp().globalData.user.userCredit >= 90) {
      wx.setNavigationBarTitle({ title: '借阅伴侣+' });
      that.setData({ creditGood: true });
    }
    //获取即将借阅到期的图书集合
    wx.request({
      url: getApp().globalData.url + 'api-index-getBorrowWillOverdue/' + that.data.user.userId,
      data: {},
      method: 'GET',
      success: function (res) {
        that.setData({ borrowWillOverdue: res.data });
      }
    })
    //获取已经逾期未还的图书集合
    wx.request({
      url: getApp().globalData.url + 'api-index-getBorrowOverdue/' + that.data.user.userId,
      data: {},
      method: 'GET',
      success: function (res) {
        that.setData({ borrowOverdue: res.data });
      }
    })
    //获取热门图书
    wx.request({
      url: getApp().globalData.url + 'api-index-getHotBook',
      data: {},
      method: 'GET',
      success: function (res) {
        that.setData({ hotBookList: res.data });
      }
    })
    //获取推荐图书
    wx.request({
      url: getApp().globalData.url + 'api-index-getRecommendBook/' + that.data.user.userId,
      data: {},
      method: 'GET',
      success: function (res) {
        that.setData({ recommendBookList: res.data });
      }
    })
    //获取新书
    wx.request({
      url: getApp().globalData.url + 'api-index-getNewBook',
      data: {},
      method: 'GET',
      success: function (res) {
        that.setData({ newBookList: res.data });
      }
    })
    //获取书友图书
    wx.request({
      url: getApp().globalData.url + 'api-index-getForumBook',
      data: {},
      method: 'GET',
      success: function (res) {
        that.setData({ forumBookList: res.data });
      }
    })
    wx.stopPullDownRefresh();
    wx.hideNavigationBarLoading();
  },
  bookDetailBtn: function (e) {
    var bookId = e.currentTarget.id;
    wx.navigateTo({
      url: '/pages/book-detail/book-detail?scanCode=0&id=' + bookId
    })
  },
  specialBtn: function (e) {
    var name = e.target.dataset.name;
    wx.navigateTo({
      url: '/pages/books-list/books-list?classifyone=' + name
    })
  },
  borrowMoreBtn: function() {
    wx.navigateTo({
      url: '/pages/user-borrow/user-borrow',
    })
  },
  specialOnBtn: function() {
    this.setData({ specialOn: true, messageBtnClass: 'top-tag', specialBtnClass: 'top-tag-active' });
    console.log(this.data.specialOn);
  },
  specialOffBtn: function() {
    this.setData({ specialOn: false, messageBtnClass: 'top-tag-active', specialBtnClass: 'top-tag' });
    console.log(this.data.specialOn);
  },
  forumCenterBtn: function(e) {
    var forumId = e.currentTarget.id;
    wx.navigateTo({
      url: '/pages/forum-content/forum-content?id=' + forumId,
    })
  }
})