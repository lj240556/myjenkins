package com.mmall.dao;

import com.mmall.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    //传递多个参数时要用@Param注解，这个方法用于根据用户名，密码来查询数据库
    User selectLogin(@Param("username") String username, @Param("password")String password);

    int deleteByPrimaryKey(Integer id);
   //插入数据
    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    //检查用户名是否存在
    int checkUsername(String username);
    //检查邮箱是否存在
    int checkEmail(String email);



    String selectQuestionByUsername(String username);

    int checkAnswer(@Param("username")String username,@Param("question")String question,@Param("answer")String answer);

    int updatePasswordByUsername(@Param("username")String username,@Param("passwordNew")String passwordNew);

    int checkPassword(@Param(value="password")String password,@Param("userId")Integer userId);

    int checkEmailByUserId(@Param(value="email")String email,@Param(value="userId")Integer userId);
}