<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/4/21
  Time: 22:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>支付页面</title>
    <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
    <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
    <script>Vue.config.productionTip = false;</script>
</head>
<body>
<%@include file="title.jsp"%>
<div id="app">
    订单号：{{acc.order_id}}<br>
    商品号：{{acc.shop_id}}<br>
    支付金额：{{acc.money}}<br>
    点击支付：<img src="/pay.jpg" @click="paynow()" height="50" width="70" >
</div>
</body>
<script>
    var app = new Vue({
        el: '#app',
        data: {
            message: '加载中',
            acc: [],
            money:''
        },
        mounted: function () {
            var that = this;
            axios.post("/orderbyid/"+"${order_id}").then(function (response) {
                that.acc = response.data.data;
                that.money = response.data.data.money;
            }, function (reason) {
                alert('err');
                console.log(reason);
            })
        },
        methods: {
            paynow:function () {
                var that = this;
                axios.post("/paynow",{data:JSON.stringify(this.acc)}).then(function (response) {
                    alert(response.data.msg);
                    location.href="/main"
                }, function (reason) {
                    alert('err');
                })

            }
        }
    })
</script>
</html>
