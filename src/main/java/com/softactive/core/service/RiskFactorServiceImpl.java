package com.softactive.core.service;

import org.springframework.stereotype.Repository;

import com.softactive.grwa.object.RiskFactor;

@Repository
public class RiskFactorServiceImpl extends AbstractDataService<RiskFactor> {

	public RiskFactorServiceImpl() {
		super("risk_factor");
	}
}
