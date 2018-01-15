// pages/cart/cart.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    //购物车是否为空标识
    shopCartIsEmpty: false,

    //底部商品信息,购物为空时展示
    productInfo: [{
      productImage: "/images/content/product/item01.png",
      productName: "商品名称",
      starImage: "/images/content/star_3.5.png",
      saleAmount: "100",
      price: "10.0"
    }, {
      productImage: "/images/content/product/item01.png",
      productName: "商品名称",
      starImage: "/images/content/star_1.5.png",
      saleAmount: "200",
      price: "10.0"
    }],

    //购物车内容
    shopCartContent: [{
      productImage: "/images/content/product/item01.png",
      productName: "商品名称",
      productDesc: "商品描述",
      price: "10.0"
    },
    {
      productImage: "/images/content/product/item01.png",
      productName: "商品名称",
      productDesc: "商品描述",
      price: "10.0"
    },

    {
      productImage: "/images/content/product/item01.png",
      productName: "商品名称",
      productDesc: "商品描述",
      price: "10.0"
    },
    {
      productImage: "/images/content/product/item01.png",
      productName: "商品名称",
      productDesc: "商品描述",
      price: "10.0"
    },

    {
      productImage: "/images/content/product/item01.png",
      productName: "商品名称",
      productDesc: "商品描述",
      price: "10.0"
    },
    {
      productImage: "/images/content/product/item01.png",
      productName: "商品名称",
      productDesc: "商品描述",
      price: "10.0"
    },

    {
      productImage: "/images/content/product/item01.png",
      productName: "商品名称",
      productDesc: "商品描述",
      price: "10.0"
    }]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})