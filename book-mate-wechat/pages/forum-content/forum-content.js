// pages/forum-content/forum-content.js
Page({
  data: {
    forumId: 0,
    forumMsg: {}
  },
  onLoad: function (options) {
    var that = this;
    var forumId = options.id;
    this.setData({ forumId: forumId });
    wx.request({
      url: getApp().globalData.url + "api-forum-byid/" + forumId,
      data: {},
      method: 'GET',
      success: function (res) {
        var data = res.data;
        data.forum.forumTime = new Date(data.forum.forumTime);
        var mydate = data.forum.forumTime.getFullYear() + "-" + (data.forum.forumTime.getMonth() + 1) + "-" + data.forum.forumTime.getDate();
        data.forum.forumTime = mydate;
        for (var i = 0; i < data.forumComments.length; ++i) {
          data.forumComments[i].forumCommentTime = new Date(data.forumComments[i].forumCommentTime);
          var mydate = data.forumComments[i].forumCommentTime.getFullYear() + "-" + (data.forumComments[i].forumCommentTime.getMonth() + 1) + "-" + data.forumComments[i].forumCommentTime.getDate();
          data.forumComments[i].forumCommentTime = mydate;
        }
        that.setData({ forumMsg: data });
        console.log(data);
      }
    })
  },
  changeInput: function (e) {
    var that = this;
    var content = e.detail.value;
    if (content != "") {
      var userId = getApp().globalData.user.userId;
      var forumId = this.data.forumId;
      wx.request({
        url: getApp().globalData.url + 'api-forum-add-comment',
        data: {
          content: content,
          userId: userId,
          forumId: forumId
        },
        method: 'GET',
        success: function (res) {
          wx.request({
            url: getApp().globalData.url + "api-forum-byid/" + forumId,
            data: {},
            method: 'GET',
            success: function (res) {
              var data = res.data;
              data.forum.forumTime = new Date(data.forum.forumTime);
              var mydate = data.forum.forumTime.getFullYear() + "-" + (data.forum.forumTime.getMonth() + 1) + "-" + data.forum.forumTime.getDate();
              data.forum.forumTime = mydate;
              for (var i = 0; i < data.forumComments.length; ++i) {
                data.forumComments[i].forumCommentTime = new Date(data.forumComments[i].forumCommentTime);
                var mydate = data.forumComments[i].forumCommentTime.getFullYear() + "-" + (data.forumComments[i].forumCommentTime.getMonth() + 1) + "-" + data.forumComments[i].forumCommentTime.getDate();
                data.forumComments[i].forumCommentTime = mydate;
              }
              that.setData({ forumMsg: data });
              console.log(data);
            }
          })
        }
      })
      wx.redirectTo({
        url: '/pages/forum-content/forum-content?id=' + this.data.forumId
      })
    }
  }
})