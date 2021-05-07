package com.hcj.springcloud.dao;

import com.hcj.springcloud.domain.Order;
import org.apache.ibatis.annotations.*;

@Mapper
public interface OrderDao {
    @Insert("insert into t_order (id,user_id,product_id,count,money,status)" +
            "        values (null,#{userId},#{productId},#{count},#{money},0)")
//    @Results(value = {
//            @Result(id=true,column = "id",property = "id"),
//            @Result(column = "user_id",property = "userId"),
//            @Result(column = "product_id",property = "productId"),
//            @Result(column = "count",property = "count"),
//            @Result(column = "money",property = "money"),
//            @Result(column = "status",property = "status")
//    })
    Integer create(Order order);

    @Update("update t_order set status = 1" +
            "        where user_id=#{userId} and status = #{status}")
    Integer update( @Param("userId") Long userId, @Param("status") Integer status);

}
