package com.kaishengit.service;

import com.kaishengit.dao.MovieDao;
import com.kaishengit.pojo.Movie;
import com.kaishengit.util.Page;
import com.kaishengit.util.QueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional//加上该注解后不需要用到Hibernate中的手动开启和关闭事务
public class MovieService {

    @Autowired
    private MovieDao movieDao;

    public void save(Movie movie) {
        movieDao.save(movie);
    }

    @Transactional(readOnly = true)//只读事务性能更高
    public List<Movie> findAll() {
        return movieDao.findAll("id","desc");
    }

    @Transactional(readOnly = true)
    public Movie findById(Integer id) {
        return movieDao.findById(id);
    }

    @Transactional(readOnly = true)
    public Page<Movie> findByPage(int pageNo, List<QueryParam> queryParamList) {
        return movieDao.findByPage(pageNo,10,queryParamList);
    }

}
