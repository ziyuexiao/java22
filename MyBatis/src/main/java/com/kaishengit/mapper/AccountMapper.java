package com.kaishengit.mapper;

import com.kaishengit.pojo.Account;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2017/1/5.
 */
public interface AccountMapper {
    @Insert("insert into tt_account(name,password) values(#{name},#{password})")
    @Options(useGeneratedKeys = true,keyProperty = "id",
            flushCache = Options.FlushCachePolicy.FALSE)
    void save(Account account);
    @Select("select*from tt_account where id =#{id}")
    Account findByid(Integer id);
    @Update("update tt_account set name = #{name} , password = #{password} where id =#{id}")
    void update(Account account);
    @Delete("delete from tt_account where id =#{id}")
    void del(Account account);
    @Select("select * from tt_account")
    List<Account> findAll();


    List<Account> findByParam(Map<String,Object> param);

    @Select("select * from tt_account limit ${start},${size}")
    List<Account> findByPage(@Param("start") int start,
                             @Param("size") int size);
}
