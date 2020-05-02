# flashsale
springcloud简单构建的电商秒杀系统<br>
框架使用springboot+mybaits+redis+rabbitmq,前端简单使用vue<br>
时间服务：flashsaletimeserver 用于统一系统时间，获取当前时间<br>
用户服务：flashsaleuser 用于用户登录和注册服务<br>
服务中心：flashsaleserver 用于服务注册和服务发现<br>
商品服务：flashsalestock 管理商品信息，商品查询、商品增加、库存增减、创建秒杀活动 <br>
订单服务：flashsaleorder 订单信息的增查改操作<br>
