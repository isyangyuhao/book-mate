// pages/user-setting/user-setting.js
Page({
  data:{},
  cancelBtn: function() {
    wx.navigateBack({
      delta: 1
    })
  }
})