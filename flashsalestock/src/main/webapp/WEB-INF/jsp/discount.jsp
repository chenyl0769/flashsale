<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/2/18
  Time: 20:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>adddiscount</title>
    <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
    <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
    <script>Vue.config.productionTip = false;</script>
</head>


<body>
<form action="/add" method="post" id="app">
    选择商品：
    <select id="shops" @change="change(shop_id)" v-model="shop_id" name="shop_id">
        <option selected="selected" value="" disabled>请选择</option>
        <option v-for="item in arr" :value="item.shop_id">{{item.title}}</option>
    </select><br>
    原价：{{price}}<br>
    库存：{{stock}}<br>
    折扣价：<input type="text" name="discount"><br>
    折扣数量：<input type="text" name="stock"><br>
    开始时间：<input type="date" name="starttime"><br>
    开始时间：<input type="date" name="endtime"><br>

    <input type="submit" value="点击提交">

</form>


<script>
    var app = new Vue({
        el: '#app',
        data: {
            message: 'Hello Vue!',
            arr: [],
            price: '',
            shop_id: '',
            stock: ''
        },
        mounted: function () {
            this.test();
        },
        methods: {
            test: function () {
                var that = this;
                axios.post("/stocks").then(function (response) {
                    that.arr = response.data.data;
                }, function (reason) {
                    alert('err');
                    console.log(reason);
                })
            },
            change: function (shop_id) {
                var that = this;
                console.log(shop_id);
                axios.post("/stock/" + shop_id).then(function (response) {
                    console.log(response.data.data);
                    that.price = response.data.data[0].shop.price;
                    that.stock = response.data.data[0].shop.stock;
                }, function (reason) {
                    alert('err');
                    console.log(reason);
                })
            }
        }
    })
</script>
</body>
</html>
