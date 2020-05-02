package com.cyl.flashsalestock.mapper;

import com.cyl.flashsalestock.entity.Discount;
import com.cyl.flashsalestock.entity.Shop;
import com.cyl.flashsalestock.entity.ShopDetail;
import com.cyl.flashsalestock.entity.StockLog;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ShopMapper {

    //查询所有商品
    @Select("select shop_id,title,image,stock,price from shops")
    @Results({@Result(property = "shop_id", column = "shop_id", javaType = String.class),
            @Result(property = "title", column = "title", javaType = String.class),
            @Result(property = "image", column = "image", javaType = String.class),
            @Result(property = "stock", column = "stock", javaType = int.class),
            @Result(property = "price", column = "price", javaType = double.class)/*,
            @Result(property = "discount",column = "shop_id",
            one = @One(select = "com.cyl.flashsalestock.mapper.ShopMapper.getDiscountByShopid"))*/
    })
    List<Shop> getAll();

    //根据ID查询商品详细
    @Select("select shop_id,description from shop_detail where shop_id=#{shop_id}")
    @Results({@Result(property = "shop_id", column = "shop_id", javaType = String.class),
            @Result(property = "description", column = "description", javaType = String.class),
            @Result(property = "shop", column = "shop_id",
                    one = @One(select = "com.cyl.flashsalestock.mapper.ShopMapper.getshopbyshopid1"))})
    List<ShopDetail> getShopDetailByShopId(@Param("shop_id") String shop_id);


    //根据shopid查询商品信息
    @Select("select shop_id,title,image,stock,price from shops where shop_id=#{shop_id}")
    @Results({@Result(property = "shop_id", column = "shop_id", javaType = String.class),
            @Result(property = "title", column = "title", javaType = String.class),
            @Result(property = "image", column = "image", javaType = String.class),
            @Result(property = "stock", column = "stock", javaType = int.class),
            @Result(property = "price", column = "price", javaType = double.class),
            @Result(property = "discount", column = "shop_id",
                    one = @One(select = "com.cyl.flashsalestock.mapper.ShopMapper.getDiscountByShopid"))
    })
    Shop getShopByShopId(@Param("shop_id") String shop_id);


    //获取秒杀信息
    @Select("select dis_id,shop_id,stock,discount,starttime,endtime from discount where shop_id=#{shop_id}")
    @Results({@Result(property = "dis_id", column = "dis_id", javaType = String.class),
            @Result(property = "shop_id", column = "shop_id", javaType = String.class),
            @Result(property = "stock", column = "stock", javaType = Integer.class),
            @Result(property = "discount", column = "discount", javaType = Double.class),
            @Result(property = "starttime", column = "starttime", javaType = Date.class),
            @Result(property = "endtime", column = "endtime", javaType = Date.class)
    })
    Discount getDiscountByShopid(@Param("shop_id") String shop_id);


    //添加秒杀活动
    @Insert("insert into discount(dis_id,shop_id,stock,discount,starttime,endtime) values (#{discount.dis_id},#{discount.shop_id},#{discount.stock},#{discount.discount},#{discount.starttime},#{discount.endtime}) ")
    int adddiscount(@Param("discount") Discount discount);

    //增加商品
    @Insert("insert into shops(shop_id,title,image,stock,price) values (#{shop.shop_id},#{shop.title},#{shop.image},#{shop.stock},#{shop.price}) ")
    int addshop (@Param("shop")Shop shop);


    //添加商品详细
    @Insert("replace into shop_detail(shop_id,description) values (#{shopdetail.shop_id},#{shopdetail.description}) ")
    int addshopdetail (@Param("shopdetail")ShopDetail shopDetail);


    //更新商品库存
    @Update("update shops set stock = #{shop.stock} where shop_id = #{shop.shop_id}")
    int stockupdate (@Param("shop") Shop shop);

    //添加库存记录
    @Insert("insert into stocklog (shop_id,incr,decr) values (#{stocklog.shop_id},#{stocklog.incr},#{stocklog.decr})")
    int addstocklog (@Param("stocklog") StockLog stockLog);

    //更新订单状态
    @Update("update orders set statu =#{statu} where order_id = #{order_id}")
    int orderstatuupdate(@Param("order_id") String order_id,@Param("statu") Integer statu);

}
