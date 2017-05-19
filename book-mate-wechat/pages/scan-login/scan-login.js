// pages/scan-login/scan-login.js
// pages/login/login.js
Page({
  data: {
    errorStatus: 0
  },
  onLoad: function (option) {
    let errorStatus = option.errorStatus;
    if (errorStatus == 1) {
      this.setData({ errorStatus: 1 });
    }
  },
  formSubmit: (e) => {
    wx.request({
      url: getApp().globalData.url + 'api-scan-admin-login',
      data: { name: e.detail.value.username, password: e.detail.value.password },
      method: 'GET',
      success: function (res) {
        if (res.data.adminId != null) {
          console.log(res.data);
          //登录成功
          //微信端登录
          var app = getApp();
          app.getUserInfo();
          getApp().globalData.admin = res.data;
          wx.redirectTo({
            url: '/pages/scan-main/scan-main'
          })
        } else {
          //登录失败
          wx.redirectTo({
            url: '/pages/scan-login/scan-login?errorStatus=1'
          })
        }
      }
    });
  },
  returnIndexBtn: () => {
    wx.redirectTo({
      url: '/pages/scan-index/scan-index'
    })
  }
})