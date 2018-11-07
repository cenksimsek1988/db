package com.softactive.editor.wb.page;

import java.io.Serializable;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.softactive.editor.wb.manager.RegionalRiskFactorGenerator;
import com.softactive.editor.wb.requester.WorldBankIndicatorSearcher;
import com.softactive.grwa.object.MyConstants;
import com.softactive.grwa.service.GrwaContext;
import com.softactive.grwa.service.GrwaContextWrapper;
import com.softactive.grwa.service.IndicatorService;

import lombok.Getter;
import lombok.Setter;

@Component @Lazy
public class WorldBankGetIndicatorBean implements MyConstants, Serializable {

	private static final long serialVersionUID = 5981084383947615574L;
	@Setter
	private Integer progress = null;
	@Getter @Setter
	@Autowired
	private RegionalRiskFactorGenerator rGenerator;
	IndicatorService is;
	
	@PostConstruct
	public void init() {
		GrwaContext c = GrwaContextWrapper.getContext();
		is = c.getIs();
	}

	public Integer getProgress() {
		if(progress==null) {
			progress = 0;
		}
		int count = rGenerator.getIndicatorCount();
		if(count==0) {
			return 0;
		}
		int index = rGenerator.getIndicatorIndex();
		return 100 * index / count;
	}

	// for searcher
	@Getter @Setter
	@Autowired
	private WorldBankIndicatorSearcher iSearcher;

	public void requestIndicators() {
//		iSearcher.start();
		rGenerator.startForIndicators(is.getIndicatorsBySource(SOURCE_WORLD_BANK));
	}
}
