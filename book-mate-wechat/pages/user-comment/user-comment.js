// pages/user-comment/user-comment.js
Page({
  data: {
    comments: {}
  },
  onLoad: function (options) {
    var that = this;
    var userId = getApp().globalData.user.userId;
    wx.request({
      url: getApp().globalData.url + "api-forum-comment-byuserid/" + userId,
      data: {},
      method: 'GET',
      success: function (res) {
        var data = res.data;
        for (var i = 0; i < data.length; ++i) {
          data[i].forumCommentTime = new Date(data[i].forumCommentTime);
          var mydate = data[i].forumCommentTime.getFullYear() + "-" + (data[i].forumCommentTime.getMonth() + 1) + "-" + data[i].forumCommentTime.getDate();
          data[i].forumCommentTime = mydate;
        }
        that.setData({comments: data});
      }
    })
  },
  deleteBtn: function(e) {
    var commentId = e.currentTarget.id;
    wx.request({
      url: getApp().globalData.url + "api-forum-remove-comment/" + commentId,
      data: {},
      method: 'GET', 
      success: function(res){
        wx.redirectTo({
          url: '/pages/user-comment/user-comment'
        })
      }
    })
  }
})