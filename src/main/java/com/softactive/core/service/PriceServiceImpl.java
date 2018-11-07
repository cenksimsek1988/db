package com.softactive.core.service;

import org.springframework.stereotype.Repository;

import com.softactive.grwa.object.Price;

@Repository
public class PriceServiceImpl extends AbstractDataService<Price> {
	public PriceServiceImpl() {
		super("prc_price");
	}
}
