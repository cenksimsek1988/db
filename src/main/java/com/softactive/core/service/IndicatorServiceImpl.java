package com.softactive.core.service;

import org.springframework.stereotype.Repository;

import com.softactive.grwa.object.Indicator;

@Repository
public class IndicatorServiceImpl extends AbstractDataService<Indicator> {
	public IndicatorServiceImpl() {
		super("cmn_indicator");
	}
}
