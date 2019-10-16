package life.majiang.community.community.mapper;

import life.majiang.community.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface QuestionMapper {

    @Insert("insert into question (title,description,gmt_create,gmt_modified,creator,tag) values (#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag})")
    void create(Question question);

    @Select("select * from question limit #{offset},#{pageSize}")
    List<Question> getList(@Param("offset") Integer offset, @Param("pageSize") Integer pageSize);

    @Select("select count(1) from question")
    Integer getCount();

    @Select("select * from question where creator = #{userId} limit #{offset},#{pageSize}")
    List<Question> getListByUserId(@Param("userId") Integer userId, @Param("offset") Integer offset, @Param("pageSize") Integer pageSize);

    @Select("select count(1) from question where creator = #{userId}")
    Integer getCountByUserId(@Param("userId") Integer userId);

    @Select("select * from question where id = #{id}")
    Question getListById(@Param("id") Integer id);

}
