package life.majiang.community.community.mapper;

import life.majiang.community.community.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: Ye YinYong
 * @create: 2019-07-21 15:09
 **/
@Mapper
@Component
public interface UserMapper {
    @Insert("insert into user (name,account_id,token,gmt_create,gmt_modified,avatar_url) values (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified},#{avatarUrl})")
    void insert(User user);

    //如果形参是类就自动把参数放进#{}里面，如果不是类就要加入注解,比如上面是user类，就不用加注解
    @Select("select * from user where token = #{token}")
    User findByToken(@Param("token") String token);

    @Select("select * from user where id = ${id}")
    User findById(@Param("id") Integer id);

    @Select("select * from user where account_id = ${accountId}")
    User findByAccountId(@Param("accountId") String accountId);

    @Update("update user set gmt_modified=#{gmtModified},avatar_url=#{avatarUrl},name=#{name},token=#{token} where id= #{id}")
    void update(User dbUser);
}
