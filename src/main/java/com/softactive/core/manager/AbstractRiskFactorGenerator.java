package com.softactive.core.manager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.softactive.grwa.object.Indicator;
import com.softactive.grwa.object.MyConstants;
import com.softactive.grwa.object.Region;
import com.softactive.grwa.object.RiskFactor;

public abstract class AbstractRiskFactorGenerator<R extends Region, I extends Indicator> extends AbstractBaseRiskFactorGenerator<R, I, RiskFactor>
implements MyConstants, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7225464527078250382L;
	
	@Override
	protected RiskFactor getRiskFactor(R r, I i) {
		RiskFactor rf = new RiskFactor();
		String apiCode = i.getApiCode();
		rf.setApiCode(apiCode);
		String sourceCode = i.getSourceCode();
		rf.setDataSourceCode(sourceCode);
		if(i.getId()==null) {
			System.out.println("MyError: indicator id is null");
		}
		rf.setIndicatorCode(i.getId());
		if(i.getFrequencyId() == null) {
			i.setFrequencyId(FREQUENCY_ANNUAL);
		}
		rf.setFrequencyCode(i.getFrequencyId());
		rf.setRegionId(r.getId());
		return getRiskFactor(rf, r, i);
	}
	
	protected RiskFactor getRiskFactor(RiskFactor rf, R r, I i) {
		return rf;
	}

	protected List<RiskFactor> getRiskFactorsForIndicator(List<R> regions, I i) {
		List<RiskFactor> list = new ArrayList<>();
		for (R r : regions) {
			list.add(getRiskFactor(r, i));
		}
		return list;
	}
}
