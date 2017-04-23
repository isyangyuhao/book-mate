// pages/register/register.js
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
  formSubmit: function (e) {
    console.log('registerMsg: ', e.detail.value);
    let username = e.detail.value.username;
    let password = e.detail.value.password;
    let password2 = e.detail.value.password2;
    //执行注册
    let registerStatus = 0;
    if (registerStatus == 1) {
      //注册成功
      wx.redirectTo({
        url: '/pages/login/login'
      })
    } else {
      //注册失败
      wx.redirectTo({
        url: '/pages/register/register?errorStatus=1'
      })
    }
  },
  navBtnOnClick: () => {
    wx.redirectTo({
      url: '/pages/login/login'
    })
  }
})