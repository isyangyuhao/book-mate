// pages/book-detail/book-detail.js
Page({
  data:{
    scanCode: 0
  },
  onLoad:function(options){
    if (options.scanCode == 1) {
      this.setData({scanCode: 1});
    }
  }
})