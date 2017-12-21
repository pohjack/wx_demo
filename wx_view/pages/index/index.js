//index.js
//获取应用实例
const app = getApp()

Page({
  data: {
    //底部商品信息
    productInfo: [{
      productImage: "/images/content/product/item01.png",
      productName: "商品名称",
      starImage: "/images/content/star_3.5.png",
      saleAmount: "100",
      price: "10.0"
    }, {
      productImage: "/images/content/product/item01.png",
      productName: "商品名称",
      starImage: "/images/content/star_2.5.png",
      saleAmount: "156",
      price: "10.0"
    }, {
      productImage: "/images/content/product/item01.png",
      productName: "商品名称",
      starImage: "/images/content/star_1.5.png",
      saleAmount: "200",
      price: "10.0"
    }, {
      productImage: "/images/content/product/item01.png",
      productName: "商品名称",
      starImage: "/images/content/star_3.5.png",
      saleAmount: "100",
      price: "10.0"
    }],
    // 分类数据
    classifyInfo: [
      {
        img: "/images/classify/classify1.png",
        name: "美食",
        backgroundColor: "#EE7600"
      }, {
        img: "/images/classify/classify2.png",
        name: "生活用品",
        backgroundColor: "lightblue"
      }, {
        img: "/images/classify/classify3.png",
        name: "饮料",
        backgroundColor: "lightgreen"
      }, {
        img: "/images/classify/classify4.png",
        name: "面包",
        backgroundColor: "lightpink"
      },
      {
        img: "/images/classify/classify1.png",
        name: "美食",
        backgroundColor: "#9370DB"
      }, {
        img: "/images/classify/classify2.png",
        name: "生活用品",
        backgroundColor: "#63B8FF"
      }, {
        img: "/images/classify/classify3.png",
        name: "饮料",
        backgroundColor: "lightgreen"
      }, {
        img: "/images/classify/classify4.png",
        name: "面包",
        backgroundColor: "lightpink"
      }

    ]
  },
  //事件处理函数
  bindViewTap: function () {
    wx.navigateTo({
      url: '../logs/logs'
    })
  },
  onLoad: function () {
    if (app.globalData.userInfo) {
      this.setData({
        userInfo: app.globalData.userInfo,
        hasUserInfo: true
      })
    } else if (this.data.canIUse) {
      // 由于 getUserInfo 是网络请求，可能会在 Page.onLoad 之后才返回
      // 所以此处加入 callback 以防止这种情况
      app.userInfoReadyCallback = res => {
        this.setData({
          userInfo: res.userInfo,
          hasUserInfo: true
        })
      }
    } else {
      // 在没有 open-type=getUserInfo 版本的兼容处理
      wx.getUserInfo({
        success: res => {
          app.globalData.userInfo = res.userInfo
          this.setData({
            userInfo: res.userInfo,
            hasUserInfo: true
          })
        }
      })
    }
  },
  getUserInfo: function (e) {
    console.log(e)
    app.globalData.userInfo = e.detail.userInfo
    this.setData({
      userInfo: e.detail.userInfo,
      hasUserInfo: true
    })
  }
})
