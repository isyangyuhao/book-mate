//index.js
Page({
  data: {
  },
  loginBtnOnClick: () => {
    wx.navigateTo({
      url: '/pages/login/login'
    })
  },
  registerBtnOnClick: () => {
    wx.navigateTo({
      url: '/pages/register/register'
    })
  },
  scanEnterBtn: function() {
    wx.redirectTo({
      url: '/pages/scan-index/scan-index',
    })
  }
})
