package com.customer.service;

import com.customer.dao.TestDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TestService {
    @Resource
    TestDao testDao;

    public List testServiceMethod(){
        return testDao.test();
    }
}
