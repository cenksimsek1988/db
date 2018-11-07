package com.softactive.editor.re.page;

import java.io.Serializable;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.softactive.editor.re.view.ReutersIndicatorDataTable;
import com.softactive.grwa.object.MyConstants;
import com.softactive.grwa.service.GrwaContext;
import com.softactive.grwa.service.GrwaContextWrapper;
import com.softactive.grwa.service.IndicatorService;

import lombok.Getter;
import lombok.Setter;

@Component @Lazy
public class ReutersIndicatorBean implements Serializable, MyConstants {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2920641229010638737L;
	private IndicatorService is;
	@Getter
	@Setter
	private ReutersIndicatorDataTable dt;

	@PostConstruct
	protected void init() {
		GrwaContext c = GrwaContextWrapper.getContext();
		is = c.getIs();
		dt = new ReutersIndicatorDataTable(is.getIndicatorsBySource(SOURCE_REUTERS));
	}

	public void addIndicator() {
		dt.onAddNew();
	}

	public void save() {
		is.save(dt.getSource());
	}
}
