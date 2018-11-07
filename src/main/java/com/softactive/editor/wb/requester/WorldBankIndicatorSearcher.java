package com.softactive.editor.wb.requester;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.softactive.editor.wb.manager.WorldBankIndicatorHandler;
import com.softactive.grwa.object.Indicator;
import com.softactive.grwa.service.GrwaContext;
import com.softactive.grwa.service.GrwaContextWrapper;
import com.softactive.grwa.wb.AbstractWorldBankRequester;

public class WorldBankIndicatorSearcher extends AbstractWorldBankRequester<Indicator> {
	
	private static final long serialVersionUID = -250754457223681390L;

	public static final String WORLD_BANK_INDICATOR_URL = "http://api.worldbank.org/v2/indicators";
	
	public WorldBankIndicatorSearcher() {
		super();
		setHandler(new WorldBankIndicatorHandler());
	}

	@Override
	protected String onSetUrl(Map<String, Object> sharedParams) {
		return WORLD_BANK_INDICATOR_URL;
	}
}
