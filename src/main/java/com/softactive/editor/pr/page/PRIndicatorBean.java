package com.softactive.editor.pr.page;

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
public class PRIndicatorBean implements Serializable, MyConstants {
	private static final long serialVersionUID = 6276875329933202159L;
	private IndicatorService is;
	@Getter
	@Setter
	private PRIndicatorDataTable dt;

	@PostConstruct
	protected void init() {
		GrwaContext context = GrwaContextWrapper.getContext();
		is = context.getIs();
		dt = new PRIndicatorDataTable(is.getIndicatorsBySource(SOURCE_POLITICAL_RISK));
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
