package com.softactive.editor.re.page;

import java.io.Serializable;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.softactive.editor.common.view.DataTable;
import com.softactive.editor.re.manager.ReutersRiskFactorGenerator;
import com.softactive.editor.re.view.ReutersRegion;
import com.softactive.grwa.object.MyConstants;
import com.softactive.grwa.service.GrwaContext;
import com.softactive.grwa.service.GrwaContextWrapper;
import com.softactive.grwa.service.RegionService;

import lombok.Getter;
import lombok.Setter;

@Component @Lazy
public class ReutersRiskFactorBean implements Serializable, MyConstants {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6615522152077468084L;
	@Autowired
	private ReutersRiskFactorGenerator rGenerator;
	private RegionService rs;

	@Getter
	@Setter
	private DataTable<ReutersRegion> dt;

	@PostConstruct
	protected void init() {
		GrwaContext c = GrwaContextWrapper.getContext();
		rs = c.getRs();
		dt = new DataTable<ReutersRegion>(
				ReutersRegion.getReutersRegionList(rs.getRegionsBySource(SOURCE_REUTERS, true)));
	}

	public void generate() {
		rGenerator.startForRegions(dt.getSource());
	}
}
