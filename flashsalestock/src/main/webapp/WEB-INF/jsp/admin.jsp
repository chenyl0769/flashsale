<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/4/24
  Time: 22:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>商品管理</title>
    <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
    <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
</head>
<body>
<div id="app">
增加商品：<br>
商品标题：<input type="text" name="title" v-model="title"><br>
商品价格：<input type="text" name="price" v-model="price"><br>
商品库存：<input type="text" name="stock" v-model="stock"><br>
简介图片：<input type="text" name="image" v-model="image"><br>
详细图片：<input type="text" name="description" v-model="description"><br>
<button @click="add">点击添加商品</button>
<hr>
更新库存：<br>

        选择商品：<select v-model="shop_id">
        <option selected="selected" value="" disabled>请选择</option>
        <option v-for="item in arr" :value="item.shop_id">{{item.title}}</option>
    </select><br>
        增加数量:<input type="text" name="incr" v-model="incr"><br>
        减少数量：<input type="text" name="decr" v-model="decr"><br>
        <button @click="update">点击修改库存</button>
    </div>
</body>
<script>
    var app = new Vue({
        el: '#app',
        data: {
            message: 'Hello Vue!',
            arr: [],
            shop_id: '',
            incr:'',
            decr:'',
            image:'',
            title:'',
            price:'',
            stock:'',
            description:''
        },
        mounted: function () {
            this.loading();
        },
        methods: {
            loading: function () {
                var that = this;
                axios.post("/stocks").then(function (response) {
                    that.arr = response.data.data;
                }, function (reason) {
                    alert('err');
                    console.log(reason);
                })
            },

            update:function () {
                var that = this;
                var data = {"shop_id":this.shop_id,"incr":this.incr,"decr":this.decr};
                axios.post("/updatestock",data).then(function (response) {
                    console.log(response);
                    alert(response.data.msg);
                }, function (reason) {
                    alert('err');
                    console.log(reason);
                })
            },
            add:function () {
                var that = this;
                var data = {"title":this.title,"image":this.image,"stock":this.stock,"price":this.price,"description":this.description};
                axios.post("/addshops",data).then(function (response) {
                    console.log(response);
                    alert(response.data.msg);
                }, function (reason) {
                    alert('err');
                    console.log(reason);
                })
            }

        }
    })
</script>
</html>
