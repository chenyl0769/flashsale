<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/3/30
  Time: 22:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
    <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
    <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
    <script>Vue.config.productionTip = false;</script>
</head>
<body>
<%@include file="title.jsp"%>
<div id="app">
    <form action="/" method="post">
        <input name="user_name" type="text" v-model="user_name"><br>
        <input name="user_pwd" type="password" v-model="user_pwd"><br>
        <input type="button" value="注册" @click="sub">
    </form>
</div>
</body>
<script>
    var app = new Vue({
        el: '#app',
        data: {
            user_name:"",
            user_pwd:""
        },
        methods: {
            sub:function () {
                var that=this;
                var data={"user_name":this.user_name,"user_pwd":this.user_pwd}
                axios.post("/log",data).then(function (response) {
                    if (!response.data.result) {
                        alert(response.data.msg);
                    }else {
                        location.href="/index";
                    }
                }, function (reason) {
                    alert('err');
                    console.log(reason);
                })
            }
        }
    })
</script>
</html>
