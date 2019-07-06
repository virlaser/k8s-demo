package com.vlaser.demo.repository.impl;

import com.vlaser.demo.domain.Info;
import com.vlaser.demo.repository.InfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class InfoRepositoryImpl implements InfoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int add(Info info) {
        return jdbcTemplate.update("insert into info (time, ip) values (?, ?)", info.getTime(), info.getIp());
    }

}
