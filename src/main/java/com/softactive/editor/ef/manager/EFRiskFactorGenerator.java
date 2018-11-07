package com.softactive.editor.ef.manager;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.softactive.core.manager.AbstractRiskFactorGenerator;
import com.softactive.grwa.object.Indicator;
import com.softactive.grwa.object.Region;
import com.softactive.grwa.object.RiskFactor;
import com.softactive.grwa.service.IndicatorService;
import com.softactive.grwa.service.RegionService;

@Component @Lazy
public class EFRiskFactorGenerator extends AbstractRiskFactorGenerator<Region, Indicator> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1151644399715661512L;

	@Override
	protected RiskFactor getRiskFactor(RiskFactor rf, Region r, Indicator i) {
		rf.setFrequencyCode(FREQUENCY_ANNUAL);
		rf.setDataSourceCode(SOURCE_ECONOMIC_FREEDOM);
		rf.setIndicatorCode(i.getId());
		rf.setRegionId(r.getId());
		return rf;
	}

	public void start() {
		start(((IndicatorService) is).getIndicatorsBySource(SOURCE_ECONOMIC_FREEDOM), ((RegionService) rs).getRegionsBySource(SOURCE_ECONOMIC_FREEDOM, true));
	}

}
