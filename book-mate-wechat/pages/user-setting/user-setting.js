// pages/user-setting/user-setting.js
Page({
  data:{
    recommendMsg: "中等"
  },
  cancelBtn: function() {
    wx.navigateBack({
      delta: 1
    })
  },
  sliderChange: function(e) {
    let msg = "";
    if (e.detail.value == 0) {
      msg = "关闭";
    } else if (e.detail.value == 30) {
      msg = "少量"
    } else if (e.detail.value == 60) {
      msg = "适中";
    } else if (e.detail.value == 90) {
      msg = "大量";
    }
    this.setData({recommendMsg: msg});
  }
})