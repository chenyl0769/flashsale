<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/4/20
  Time: 19:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>我的主页</title>
    <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
    <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
    <script>Vue.config.productionTip = false;</script>
</head>
<body>
<%@include file="title.jsp"%>
<div id="app">
    <table border="1" >
        <tr v-for="(i,index) in res">
            <td>{{index}}</td>
            <td>订单号：{{i.order_id}}</td>
            <td>状态：<div v-if="i.statu === 0">未支付</div><div v-else>已支付</div></td>
            <td>商品id：{{i.shop_id}}</td>
            <td>价格：{{i.money}}</td>
            <td>创建时间： {{i.create_time}}</td>
            <td v-if="i.statu === 0"><a :href="'/pay/'+i.order_id">点击支付</a></td>
            <td v-else><a :href="'/pay/'+i.order_id">查看物流</a></td>
        </tr>
    </table>
</div>
</body>
<script>
    var app =new Vue({
        el:"#app",
        data:{
            message:'加载中',
            res:[]
        },
        mounted:function () {
            var that=this;
            axios.post("/orderlist/"+${user.user_Id}).then(function (response) {
                console.log(response.data.data);
                that.res =response.data.data;

            }, function (reason) {
                alert('出错了');
                console.log(reason);
            })

        }
    })
</script>
</html>
