package com.vlaser.demo.service.impl;

import com.vlaser.demo.domain.Info;
import com.vlaser.demo.repository.InfoRepository;
import com.vlaser.demo.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InfoServiceImpl implements InfoService {

    @Autowired
    private InfoRepository infoRepository;

    @Override
    public int add(Info info) {
        return infoRepository.add(info);
    }
}
