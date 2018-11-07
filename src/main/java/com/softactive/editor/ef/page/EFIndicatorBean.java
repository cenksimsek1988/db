package com.softactive.editor.ef.page;

import java.io.Serializable;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.softactive.editor.pr.view.PRIndicatorDataTable;
import com.softactive.grwa.object.Indicator;
import com.softactive.grwa.object.MyConstants;
import com.softactive.grwa.service.GrwaContext;
import com.softactive.grwa.service.GrwaContextWrapper;
import com.softactive.grwa.service.IndicatorService;

import lombok.Getter;
import lombok.Setter;

@Component @Lazy
public class EFIndicatorBean implements Serializable, MyConstants {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5985157771582263818L;
	private IndicatorService is;
	@Getter
	@Setter
	private PRIndicatorDataTable dt;

	@PostConstruct
	protected void init() {
		GrwaContext c = GrwaContextWrapper.getContext();
		is = c.getIs();
		dt = new PRIndicatorDataTable(is.getIndicatorsBySource(SOURCE_ECONOMIC_FREEDOM));
	}

	public void addIndicator() {
		dt.onAddNew();
	}

	public void save() {
		for(Indicator i:dt.getSource()) {
			is.save(i);
		}
	}
}
