// pages/user-forum/user-forum.js
Page({
  data: {
    forumAndComments: 0
  },
  onLoad: function () {
    var userId = getApp().globalData.user.userId;
    var that = this;
    wx.request({
      url: getApp().globalData.url + "api-forum-byuserid/" + userId,
      data: {},
      method: 'GET',
      success: function (res) {
        var data = res.data;
        for (var i = 0; i < data.length; ++i) {
          data[i].forum.forumTime = new Date(data[i].forum.forumTime);
          var mydate = data[i].forum.forumTime.getFullYear() + "-" + (data[i].forum.forumTime.getMonth() + 1) + "-" + data[i].forum.forumTime.getDate();
          data[i].forum.forumTime = mydate;
        }
        that.setData({ forumAndComments: data });
        console.log(data);
      }
    })
  },
  showForumContentBtn: function (e) {
    var forumId = e.currentTarget.id;
    wx.navigateTo({
      url: '/pages/forum-content/forum-content?id=' + forumId
    })
  },
  showBookDetailBtn: function (e) {
    var bookId = e.currentTarget.id;
    wx.navigateTo({
      url: '/pages/book-detail/book-detail?scanCode=0&id=' + bookId
    })
  },
  deleteBtn: function (e) {
    var forumId = e.currentTarget.id;
    wx.showModal({
      title: '提示',
      content: '确认删除吗?',
      confirmColor: '#4db6ac',
      success: function (res) {
        if (res.confirm) {
          wx.request({
            url: getApp().globalData.url + "api-forum-remove-forum/" + forumId,
            data: {},
            method: 'GET',
            success: function (res) {
              wx.redirectTo({
                url: '/pages/user-forum/user-forum'
              })
            }
          })
        } else if (res.cancel) {

        }
      }
    })

  }
})