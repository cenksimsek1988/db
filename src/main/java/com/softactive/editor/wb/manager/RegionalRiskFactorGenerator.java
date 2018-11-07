package com.softactive.editor.wb.manager;

import java.io.Serializable;
import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.softactive.grwa.object.Indicator;
import com.softactive.grwa.object.Region;
import com.softactive.grwa.service.IndicatorService;
import com.softactive.grwa.service.RegionService;

@Component @Lazy
public class RegionalRiskFactorGenerator extends AbstractWBRiskFactorGenerator implements Serializable{
	private static final long serialVersionUID = -54707498213071787L;

	public void startForIndicators(List<Indicator> list) {
		List<Region> worldBankRegions = ((RegionService) rs).getRegionsBySource(SOURCE_WORLD_BANK, true);
		start(list, worldBankRegions);
	}

	public void startForRegions(List<Region> list) {
		List<Indicator> worldBankIndicators = ((IndicatorService) is).getIndicatorsBySource(SOURCE_WORLD_BANK);
		start(worldBankIndicators, list);
	}
}
