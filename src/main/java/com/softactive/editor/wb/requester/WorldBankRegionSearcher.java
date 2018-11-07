package com.softactive.editor.wb.requester;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.softactive.editor.wb.manager.WorldBankIndicatorHandler;
import com.softactive.editor.wb.manager.WorldBankRegionHandler;
import com.softactive.grwa.object.Region;
import com.softactive.grwa.service.GrwaContext;
import com.softactive.grwa.service.GrwaContextWrapper;
import com.softactive.grwa.service.RegionService;
import com.softactive.grwa.wb.AbstractWorldBankRequester;

public class WorldBankRegionSearcher extends AbstractWorldBankRequester<Region> {
	private static final long serialVersionUID = 8775488992413017849L;
	public static final String WORLD_BANK_REGION_URL = "http://api.worldbank.org/v2/countries";
	RegionService rs;
	
	public WorldBankRegionSearcher() {
		super();
		GrwaContext context = GrwaContextWrapper.getContext();
		rs = context.getRs();
		setHandler(new WorldBankRegionHandler());
	}
	
	@Override
	protected String onSetUrl(Map<String, Object> sharedParams) {
		return WORLD_BANK_REGION_URL;
	}
}
