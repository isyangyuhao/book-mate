// pages/scan-main/scan-main.js
Page({
  data: {
    borrowMsg: {}
  },
  adminBtn: function () {
    wx.navigateTo({
      url: '/pages/scan-admin/scan-admin',
    })
  },
  exitBtn: function () {
    wx.redirectTo({
      url: '/pages/scan-index/scan-index'
    })
  },
  scanBorrowBtn: function (e) {
    var that = this;
    wx.scanCode({
      success: function (res) {
        var timestamp = new Date().getTime();
        var msg = JSON.stringify(res.result);
        if (msg.indexOf("book-mate-borrow-id:") != -1) {
          var borrowId = msg.replace("book-mate-borrow-id:", "");
          var reg = new RegExp('"', "g");
          borrowId = borrowId.replace(reg, "");
          wx.request({
            url: getApp().globalData.url + 'api-scan-borrow-byid/' + borrowId,
            data: {},
            method: 'GET',
            success: function (res) {
              that.setData({ borrowMsg: res.data });
              console.log(that.data.borrowMsg);
              var borrowBookName = that.data.borrowMsg.book.bookName;
              var borrowUserName = that.data.borrowMsg.user.userUsername;
              //判断二维码是否过期
              var borrowStartTime = that.data.borrowMsg.borrowStartTime;
              console.log("borrowStartTime: " + borrowStartTime);
              console.log("timestamp:" + timestamp);
              console.log(timestamp - borrowStartTime);
              if (timestamp - borrowStartTime > 90000000) {
                wx.showModal({
                  title: '借阅提示',
                  content: '该验证码已过期,请重新扫码借书',
                  success: function (res) {
                    if (res.confirm) {
                    } else if (res.cancel) {
                    }
                  }
                })
              } else {
                wx.showModal({
                  title: '借阅提示',
                  content: '是否允许将《' + borrowBookName + '》借给' + borrowUserName,
                  success: function (res) {
                    if (res.confirm) {
                      //请求批准借阅接口
                      wx.request({
                        url: getApp().globalData.url + 'api-scan-allow-borrow/' + borrowId,
                        data: {},
                        method: 'GET',
                        success: function (res) {
                        }
                      })
                    } else if (res.cancel) {
                    }
                  }
                })
              }
            }
          })
        }
        if (msg.indexOf("book-mate-borrow2-id:") != -1) {
          var borrowId = msg.replace("book-mate-borrow2-id:", "");
          var reg = new RegExp('"', "g");
          borrowId = borrowId.replace(reg, "");
          wx.request({
            url: getApp().globalData.url + 'api-scan-borrow-byid/' + borrowId,
            data: {},
            method: 'GET',
            success: function (res) {
              that.setData({ borrowMsg: res.data });
              console.log(that.data.borrowMsg);
              var borrowBookName = that.data.borrowMsg.book.bookName;
              var borrowUserName = that.data.borrowMsg.user.userUsername;
              //判断二维码是否过期
              var borrowStartTime = that.data.borrowMsg.borrowStartTime;
              console.log("borrowStartTime: " + borrowStartTime);
              console.log("timestamp:" + timestamp);
              console.log(timestamp - borrowStartTime);
              if (timestamp - borrowStartTime > 90000000) {
                wx.showModal({
                  title: '借阅提示',
                  content: '该验证码已过期,请重新扫码借书',
                  success: function (res) {
                    if (res.confirm) {
                    } else if (res.cancel) {
                    }
                  }
                })
              } else {
                wx.showModal({
                  title: '借阅提示',
                  content: '是否允许将《' + borrowBookName + '》借给' + borrowUserName,
                  success: function (res) {
                    if (res.confirm) {
                      //请求批准借阅接口
                      wx.request({
                        url: getApp().globalData.url + 'api-scan-allow-borrow/' + borrowId,
                        data: {},
                        method: 'GET',
                        success: function (res) {
                        }
                      })
                    } else if (res.cancel) {
                    }
                  }
                })
              }
            }
          })
        }
      }
    })
  },
  scanReturnBtn: function (e) {
    var that = this;
    wx.scanCode({
      success: function (res) {
        var timestamp = new Date().getTime();
        var msg = JSON.stringify(res.result);
        if (msg.indexOf("book-mate-return-id:") != -1) {
          var borrowId = msg.replace("book-mate-return-id:", "");
          var reg = new RegExp('"', "g");
          borrowId = borrowId.replace(reg, "");
          wx.request({
            url: getApp().globalData.url + 'api-scan-borrow-byid/' + borrowId,
            data: {},
            method: 'GET',
            success: function (res) {
              that.setData({ borrowMsg: res.data });
              console.log(that.data.borrowMsg);
              var borrowBookName = that.data.borrowMsg.book.bookName;
              var borrowUserName = that.data.borrowMsg.user.userUsername;
              wx.showModal({
                title: '借阅提示',
                content: '确认归还《' + borrowBookName + '》吗?',
                success: function (res) {
                  if (res.confirm) {
                    //请求批准借阅接口
                    wx.request({
                      url: getApp().globalData.url + 'api-scan-allow-return/' + borrowId,
                      data: {},
                      method: 'GET',
                      success: function (res) {
                      }
                    })
                  } else if (res.cancel) {
                  }
                }
              })
            }
          })
        }
        if (msg.indexOf("book-mate-return2-id:") != -1) {
          var borrowId = msg.replace("book-mate-return2-id:", "");
          var reg = new RegExp('"', "g");
          borrowId = borrowId.replace(reg, "");
          wx.request({
            url: getApp().globalData.url + 'api-scan-borrow-byid/' + borrowId,
            data: {},
            method: 'GET',
            success: function (res) {
              that.setData({ borrowMsg: res.data });
              console.log(that.data.borrowMsg);
              var borrowBookName = that.data.borrowMsg.book.bookName;
              var borrowUserName = that.data.borrowMsg.user.userUsername;
              wx.showModal({
                title: '借阅提示',
                content: '确认归还《' + borrowBookName + '》吗?',
                success: function (res) {
                  if (res.confirm) {
                    //请求批准借阅接口
                    wx.request({
                      url: getApp().globalData.url + 'api-scan-allow-return/' + borrowId,
                      data: {},
                      method: 'GET',
                      success: function (res) {
                      }
                    })
                  } else if (res.cancel) {
                  }
                }
              })
            }
          })
        }
      }
    })
  },
  scanAutoBtn: function() {
    var that = this;
    wx.scanCode({
      success: function (res) {
        var timestamp = new Date().getTime();
        var msg = JSON.stringify(res.result);
        //自助借书
        if (msg.indexOf("book-mate-borrow2-id:") != -1) {
          var borrowId = msg.replace("book-mate-borrow2-id:", "");
          var reg = new RegExp('"', "g");
          borrowId = borrowId.replace(reg, "");
          wx.request({
            url: getApp().globalData.url + 'api-scan-borrow-byid/' + borrowId,
            data: {},
            method: 'GET',
            success: function (res) {
              that.setData({ borrowMsg: res.data });
              console.log(that.data.borrowMsg);
              var borrowBookName = that.data.borrowMsg.book.bookName;
              var borrowUserName = that.data.borrowMsg.user.userUsername;
              //判断二维码是否过期
              var borrowStartTime = that.data.borrowMsg.borrowStartTime;
              console.log("borrowStartTime: " + borrowStartTime);
              console.log("timestamp:" + timestamp);
              console.log(timestamp - borrowStartTime);
              if (timestamp - borrowStartTime > 90000000) {
                wx.showModal({
                  title: '借阅提示',
                  content: '该验证码已过期,请重新扫码借书',
                  success: function (res) {
                    if (res.confirm) {
                    } else if (res.cancel) {
                    }
                  }
                })
              } else {
                //请求批准借阅接口
                wx.request({
                  url: getApp().globalData.url + 'api-scan-allow-borrow/' + borrowId,
                  data: {},
                  method: 'GET',
                  success: function (res) {
                  }
                })
              }
            }
          })
        }
        //自助还书
        if (msg.indexOf("book-mate-return2-id:") != -1) {
          var borrowId = msg.replace("book-mate-return2-id:", "");
          var reg = new RegExp('"', "g");
          borrowId = borrowId.replace(reg, "");
          wx.request({
            url: getApp().globalData.url + 'api-scan-borrow-byid/' + borrowId,
            data: {},
            method: 'GET',
            success: function (res) {
              that.setData({ borrowMsg: res.data });
              console.log(that.data.borrowMsg);
              var borrowBookName = that.data.borrowMsg.book.bookName;
              var borrowUserName = that.data.borrowMsg.user.userUsername;
              wx.request({
                url: getApp().globalData.url + 'api-scan-allow-return/' + borrowId,
                data: {},
                method: 'GET',
                success: function (res) {
                }
              })
            }
          })
        }
      }
    })
  }
})