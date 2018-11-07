package com.softactive.editor.fred.manager;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.softactive.core.manager.AbstractRiskFactorGenerator;
import com.softactive.grwa.object.Region;
import com.softactive.grwa.object.RiskFactor;
import com.softactive.grwa.utils.ParserUtils;

@Component @Lazy
public class FredRegionalRiskFactorGenerator extends AbstractRiskFactorGenerator<Region, FredIndicator> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8241607309722725108L;

	@Override
	protected RiskFactor getRiskFactor(RiskFactor rf, Region r, FredIndicator i) {
		rf.setStartDate(ParserUtils.sqlDate(i.getStartDate()));
		rf.setEndDate(ParserUtils.sqlDate(i.getEndDate()));
		return rf;
	}

	public void start(List<FredIndicator> indicators) {
		Region rUs = new Region();
		rUs.setIsoCode(CODE_US);
		List<Region> usRegion = new ArrayList<>();
		usRegion.add(rUs);
		start(indicators, usRegion);
	}

	public void start(List<FredIndicator> indicators, String isoCode) {
		Region r = new Region();
		r.setIsoCode(isoCode);
		List<Region> regions = new ArrayList<>();
		regions.add(r);
		start(indicators, regions);
	}
}