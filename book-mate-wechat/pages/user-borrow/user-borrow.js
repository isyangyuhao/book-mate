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
  }
})