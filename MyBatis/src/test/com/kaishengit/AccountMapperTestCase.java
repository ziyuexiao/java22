package com.kaishengit;

import com.kaishengit.mapper.AccountMapper;
import com.kaishengit.pojo.Account;
import com.kaishengit.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

/**
 * Created by lenovo on 2017/1/5.
 */
public class AccountMapperTestCase {
    @Test
    public void save(){
        SqlSession sqlSession = SqlSessionFactoryUtil.getSqlSession();
        AccountMapper accountMapper = sqlSession.getMapper(AccountMapper.class);
        Account account = new Account();
        account.setName("刘");
        account.setPassword("1122");
        accountMapper.save(account);
        sqlSession.commit();
        sqlSession.close();
    }
    @Test
    public void findByid(){
        SqlSession sqlSession = SqlSessionFactoryUtil.getSqlSession();
        AccountMapper accountMapper = sqlSession.getMapper(AccountMapper.class);
        Account account = accountMapper.findByid(3749);
        System.out.println(account);
    }
    @Test
    public void update(){
        SqlSession sqlSession = SqlSessionFactoryUtil.getSqlSession();
        AccountMapper accountMapper = sqlSession.getMapper(AccountMapper.class);
        Account account = accountMapper.findByid(3749);
        account.setName("李");
        account.setPassword("0000");
        accountMapper.update(account);
        sqlSession.commit();
        sqlSession.close();
    }
    @Test
    public void del(){
        SqlSession sqlSession = SqlSessionFactoryUtil.getSqlSession();
        AccountMapper accountMapper = sqlSession.getMapper(AccountMapper.class);
        Account account = accountMapper.findByid(3749);
        accountMapper.del(account);
        sqlSession.commit();
        sqlSession.close();
    }
    @Test
    public void findAll(){
        SqlSession sqlSession = SqlSessionFactoryUtil.getSqlSession();
        AccountMapper accountMapper = sqlSession.getMapper(AccountMapper.class);
        List<Account> accountList = accountMapper.findAll();
        for(Account account:accountList){
            System.out.println(account);
        }
    }

    @Test
    public void findByPage(){
        SqlSession sqlSession = SqlSessionFactoryUtil.getSqlSession();
        AccountMapper accountMapper = sqlSession.getMapper(AccountMapper.class);
        List<Account> accountList = accountMapper.findByPage(0,2);
        for(Account account:accountList){
            System.out.println(account);
        }
    }
}
