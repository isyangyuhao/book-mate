// pages/scan-index/scan-index.js
Page({
  data: {
  },
  loginBtnOnClick: () => {
    wx.redirectTo({
      url: '/pages/scan-login/scan-login'
    })
  },
  returnIndexBtn: () => {
    wx.redirectTo({
      url: '/pages/index/index'
    })
  }
})
