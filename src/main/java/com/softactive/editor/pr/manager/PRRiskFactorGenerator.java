package com.softactive.editor.pr.manager;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.softactive.core.manager.AbstractRiskFactorGenerator;
import com.softactive.grwa.object.Indicator;
import com.softactive.grwa.object.Region;
import com.softactive.grwa.service.IndicatorService;
import com.softactive.grwa.service.RegionService;

@Component @Lazy
public class PRRiskFactorGenerator extends AbstractRiskFactorGenerator<Region, Indicator> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9076856539762251033L;

	public void start() {
		start(((IndicatorService) is).getIndicatorsBySource(SOURCE_POLITICAL_RISK), ((RegionService) rs).getRegionsBySource(SOURCE_POLITICAL_RISK, true));
	}

}
