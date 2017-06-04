// pages/user-borrow/user-borrow.js
Page({
  data:{
    borrowList: 0
  },
  onLoad:function(options){
    var that = this;
    var userId = getApp().globalData.user.userId;
    wx.request({
      url: getApp().globalData.url + 'api-scan-borrowed-byuserid/' + userId,
      data: {},
      method: 'GET',
      success: function (res) {
        var data = res.data;
        for (var i = 0; i < data.length; ++i) {
          //借书时间
          data[i].borrowStartTime = new Date(data[i].borrowStartTime);
          var mydate = data[i].borrowStartTime.getFullYear() + "-" + (data[i].borrowStartTime.getMonth() + 1) + "-" + data[i].borrowStartTime.getDate();
          //应还时间
          var mydate2 = data[i].borrowStartTime.getFullYear() + "-" + (data[i].borrowStartTime.getMonth() + 2) + "-" + data[i].borrowStartTime.getDate();
          if ((data[i].borrowStartTime.getMonth() + 2) == 13) {
            mydate2 = data[i].borrowStartTime.getFullYear() + 1 + "-" + (data[i].borrowStartTime.getMonth() + 1 - 11) + "-" + data[i].borrowStartTime.getDate();
          }
          data[i].borrowStartTime = mydate;
          data[i].borrowShouldTime = mydate2;
        }
        that.setData({borrowList: data});
      }
    })
  },
  returnBookBtn: function(e) {
     var bookId = e.currentTarget.id;
     console.log(bookId);
     wx.navigateTo({
       url: '/pages/book-return/book-return?id=' + bookId,
     })
  },
  removeReserveBookBtn: function(e) {
    var bookId = e.currentTarget.id;
    var userId = getApp().globalData.user.userId;
    wx.showModal({
      title: '提示',
      content: '请问您确定取消预订本书吗?',
      confirmColor: '#4db6ac',
      success: function (res) {
        if (res.confirm) {
          wx.request({
            url: getApp().globalData.url + "api-book-remove-reserve/" + bookId + "/" + userId,
            data: {},
            method: 'GET',
            success: function (res) {
              wx.showToast({
                title: '取消预订成功!',
              })
              wx.redirectTo({
                url: '/pages/user-borrow/user-borrow',
              })
            }
          })
        } else if (res.cancel) {

        }
      }
    })
  },
  borrowReserveBookBtn: function(e) {
    var bookId = e.currentTarget.id;
    var userId = getApp().globalData.user.userId;
    wx.showModal({
      title: '提示',
      content: '请问您确定借阅本书吗?',
      confirmColor: '#4db6ac',
      success: function (res) {
        if (res.confirm) {
          wx.request({
            url: getApp().globalData.url + "api-book-remove-reserve/" + bookId + "/" + userId,
            data: {},
            method: 'GET',
            success: function (res) {
              wx.navigateTo({
                url: '/pages/book-borrow/book-borrow?id=' + bookId,
              })
            }
          })
        } else if (res.cancel) {

        }
      }
    })
  }
})