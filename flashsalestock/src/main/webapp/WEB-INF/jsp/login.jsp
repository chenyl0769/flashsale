<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/3/15
  Time: 22:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User</title>
    <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
    <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
    <script>Vue.config.productionTip = false;</script>
</head>
<body>
<div id="app">
<form action="/log" method="post">
    <input name="user_name" type="text" v-model="user_name"><br>
    <input name="user_pwd" type="password" v-model="user_pwd"><br>
    <input type="button" value="登录" @click="sub">
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
                        var forward=document.referrer;
                        if (forward==""||forward==undefined||forward==null) {
                            location.href="/index";
                        }
                        else {
                            location.href=""+forward+"";
                        }
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
