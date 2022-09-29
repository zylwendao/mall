package com.customer.dao;


import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TestDao<TDncCustomer, String> {
    List<TDncCustomer> test();
}
