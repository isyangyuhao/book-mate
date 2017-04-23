// pages/books/books.js
Page({
  data:{},
  scanBtnOnClick: function() {
    wx.scanCode({
      success: function(res){
        console.log(res);
      }
    })
  }
})