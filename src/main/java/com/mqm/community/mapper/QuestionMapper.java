package com.mqm.community.mapper;

import com.mqm.community.dto.QuestionDTO;
import com.mqm.community.model.Question;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface QuestionMapper {
    @Insert("insert into question (title,description,gmt_create,gmt_modify,creator,tag) values (#{title},#{description},#{gmtCreate},#{gmtModify},#{creator},#{tag})")
    void create(Question question);

    @Select("select * from question limit #{offset}, #{size}")
    List<Question> list(@Param(value = "offset") Integer offset, @Param(value = "size") Integer size);

    @Select("Select count(1) from question")
    Integer count();

    @Select("select * from question where creator=#{userId} limit #{offset}, #{size}")
    List<Question> listByUserId(@Param(value = "userId") Integer userId, @Param(value = "offset") Integer offset, @Param(value = "size") Integer size);

    @Select("Select count(1) from question where creator=#{userId}")
    Integer countByUserId(@Param(value = "userId") Integer userId);

    @Select("select * from question where id=#{id}")
    Question getById(@Param(value = "id") Integer id);

    @Update("update question set description=#{description}, tag=#{tag}, title=#{title},gmt_modify=#{gmtModify} where id=#{id}")
    void update(Question question);
}
