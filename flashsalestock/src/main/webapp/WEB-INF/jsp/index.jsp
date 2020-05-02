<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/2/24
  Time: 21:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>index</title>
    <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
    <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
    <script>Vue.config.productionTip = false;</script>
</head>
<body>
<%@include file="title.jsp"%>
<div id="app">
<table border="1" v-if="tt">
        <tr v-for="(i,index) in acc.data">
            <td>{{index}}</td>
            <td>商品名：<a :href="'/detail/'+i.shop_id">{{i.title}}</a></td>
            <td>图片：{{i.image}}</td>
            <td>剩下：{{i.stock}}</td>
            <td>价格：{{i.price}}</td>
            <td>优惠：
                <div v-if="i.discount!=null">
                    开始时间：{{i.discount.starttime}},结束时间：{{i.discount.endtime}},秒杀价：{{i.discount.discount}}
                </div>
            </td>

        </tr>
</table>
 <div v-else>{{message}}</div>
</div>
</body>
<script>
    var app = new Vue({
        el: '#app',
        data: {
            message: '加载中',
            acc: [],
            tt:false
        },
        mounted: function () {
            var that = this;
            axios.post("/stocks").then(function (response) {
                that.acc = response.data;
                that.tt = response.data.result;
                that.message = response.data.msg;
                console.log(response.data);
            }, function (reason) {
                alert('err');
                console.log(reason);
            })
        },
        methods: {}
    })
</script>
</html>
