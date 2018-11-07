package com.softactive.editor.fred.manager;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.softactive.grwa.fred.AbstractFredRequester;
import com.softactive.grwa.fred.manager.FredPriceHandler;
import com.softactive.grwa.object.Indicator;
import com.softactive.grwa.object.Region;

import okhttp3.HttpUrl;

public class FredIndicatorSearcher extends AbstractFredRequester<Indicator, FredIndicator> {
	private static final long serialVersionUID = 5279016158869632132L;

	public static final String FRED_INDICATOR_URL = "https://api.stlouisfed.org/fred/tags/series";
	public static final String TAG_NATION = "nation";
	
	public FredIndicatorSearcher() {
		super();
		setHandler(new FredIndicatorHandler());
	}

	@Override
	protected HttpUrl onSetParameters(HttpUrl.Builder builder, Map<String, Object> sharedParams) {
		Region r = (Region) sharedParams.get(PARAM_REGION);
		builder.addQueryParameter(TAGS, TAG_NATION + ";" + r.getName());
		System.out.println(TAGS + ": " + r.getName());
		return super.onSetParameters(builder, sharedParams);
	}

	@Override
	protected String onSetUrl(Map<String, Object> sharedParams) {
		return FRED_INDICATOR_URL;
	}
}
