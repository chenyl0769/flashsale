<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/3/26
  Time: 23:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>DETAIL</title>
    <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
    <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
    <script>Vue.config.productionTip = false;</script>
</head>
<body>
<%@include file="title.jsp"%>
<div id="app">
    <div v-if="result">
        商品名字：{{s_title}}<br>
        <div v-if="s_discount === null">价格：{{s_price}}<br></div>
        <div v-else>原价：{{s_price}}<br>优惠价：{{s_discount}}<br></div>
        详细：{{s_description}}<br>
        <button v-on:click="createorder()">立即购买</button>
    </div>
    <div v-else>{{message}}</div>
</div>
</body>

<script>
    var app =new Vue({
    el:"#app",
    data:{
        message:'',
        res:[],
        s_title:"",
        s_price:"",
        s_description:"",
        s_discount:null,
        result:false
    },
    mounted:function () {
        var that=this;
        axios.post("/stock/"+${shop_id}).then(function (response) {
            that.result=response.data.result;
            that.message=response.data.msg;
            that.res = response.data.data[0];
            that.s_title=that.res.shop.title;
            that.s_price=that.res.shop.price;
            that.s_description=that.res.description;
            that.s_discount=that.res.shop.discount.discount;
            console.log(response.data.data[0]);
        }, function (reason) {
            alert('err');
            console.log(reason);
        })
    },
        methods:{
            createorder:function () {
                var that=this;
                axios.post("/order/"+${shop_id}).then(function (response) {

                    that.message=response.data.msg;
                    if (that.message=="抢到了"){
                        console.log(response.data.data);
                    }
                    console.log(response.data.msg);
                }, function (reason) {
                    alert('err');
                    console.log(reason);
                })
            }
        }
    })
</script>

</html>
