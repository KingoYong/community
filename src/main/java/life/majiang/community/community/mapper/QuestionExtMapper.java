package life.majiang.community.community.mapper;

import life.majiang.community.community.model.Question;
import life.majiang.community.community.model.QuestionExample;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface QuestionExtMapper {

    List<Question> selectByExampleWithRowbounds(QuestionExample example, RowBounds rowBounds);

    int incViewCount(Question record);

}