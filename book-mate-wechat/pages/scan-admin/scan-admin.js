// pages/scan-admin/scan-admin.js
Page({
  data: {
    admin: {}
  },
  onLoad: function() {
    var admin = getApp().globalData.admin;
    this.setData({admin: admin});
    console.log(admin);
  }
})