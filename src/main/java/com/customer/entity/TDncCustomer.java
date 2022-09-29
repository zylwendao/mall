package com.customer.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class TDncCustomer implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
}