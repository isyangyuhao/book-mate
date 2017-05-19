// pages/user-history/user-history.js
Page({
  data:{
    history: {}
  },
  onLoad:function(options){
    var that = this;
    var userId = getApp().globalData.user.userId;
    wx.request({
      url: getApp().globalData.url + 'api-scan-borrow-history/' + userId,
      data: {},
      method: 'GET',
      success: function (res) {
        var data = res.data;
        for (var i = 0; i < data.length; ++i) {
          //借书时间
          data[i].borrowStartTime = new Date(data[i].borrowStartTime);
          var mydate = data[i].borrowStartTime.getFullYear() + "-" + (data[i].borrowStartTime.getMonth() + 1) + "-" + data[i].borrowStartTime.getDate();
          data[i].borrowStartTime = mydate;
          //换书时间
          data[i].borrowEndTime = new Date(data[i].borrowEndTime);
          var mydate2 = data[i].borrowEndTime.getFullYear() + "-" + (data[i].borrowEndTime.getMonth() + 1) + "-" + data[i].borrowEndTime.getDate();
          data[i].borrowEndTime = mydate2;
        }
        that.setData({history: data});
        console.log(data);
      }
    })
  }
})