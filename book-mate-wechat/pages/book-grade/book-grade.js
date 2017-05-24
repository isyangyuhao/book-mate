// pages/book-grade/book-grade.js
Page({
  data: {
    star: ["/images/star/star_1.png", "/images/star/star_0.png", "/images/star/star_0.png", "/images/star/star_0.png", "/images/star/star_0.png"],
    grade: 1,
    borrowId: ""
  },
  onLoad: function (options) {
    this.setData({borrowId: options.id});
    console.log(options.id);
  },
  onClick: function(e) {
    var that = this;
    var id = e.currentTarget.id;
    var starArr = [];
    for (var i = 0; i < id; ++i) {
      starArr[i] = "/images/star/star_1.png";
    }
    for (var i = id; i < 5; ++i) {
      starArr[i] = "/images/star/star_0.png";
    }
    this.setData({star: starArr, grade: id});
  },
  submitGrade: function() {
    var that = this;
    var credit = getApp().globalData.user.userCredit;
    if (credit <= 98) {
      getApp().globalData.user.userCredit += 2;
    } else {
      getApp().globalData.user.userCredit = 100;
    }
    wx.request({
      url: getApp().globalData.url + 'api-scan-grade-book/' + that.data.borrowId +  '/' + that.data.grade,
      data: {},
      method: 'GET',
      success: function (res) {
        wx.switchTab({
          url: '/pages/user/user'
        })
      }
    })
  }
})