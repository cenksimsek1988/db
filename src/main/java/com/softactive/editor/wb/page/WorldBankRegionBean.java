package com.softactive.editor.wb.page;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.softactive.editor.common.view.DataTable;
import com.softactive.editor.common.view.PickList;
import com.softactive.editor.wb.manager.RegionalRiskFactorGenerator;
import com.softactive.grwa.object.MyConstants;
import com.softactive.grwa.object.Region;
import com.softactive.grwa.service.GrwaContext;
import com.softactive.grwa.service.GrwaContextWrapper;
import com.softactive.grwa.service.RegionService;

import lombok.Getter;
import lombok.Setter;

@Component @Lazy
public class WorldBankRegionBean implements Serializable, MyConstants {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2004696082868214632L;

	// searcher
	@Getter
	@Setter
	private PickList<Region> pl;

	// Generator
	private RegionService rs;

	@Getter @Setter
	@Autowired
	private RegionalRiskFactorGenerator rGenerator;

	@Getter
	@Setter
	private DataTable<Region> dtGenerator = new DataTable<>();

	@PostConstruct
	protected void init() {
		GrwaContext context = GrwaContextWrapper.getContext();
		rs = context.getRs();
		List<Region> source = rs.getRegionsBySource(SOURCE_WORLD_BANK, false);
		List<Region> target = rs.getRegionsBySource(SOURCE_WORLD_BANK, true);
		pl = new PickList<>(source, target);
	}

	private void addCountries() {
		List<Region> toAdd = pl.getList().getTarget();
		for(Region r:toAdd) {
			r.setEf(true);
			rs.update(r);
		}
		List<Region> toRemove = pl.getList().getSource();
		for(Region r:toRemove) {
			r.setEf(false);
			rs.update(r);
		}
	}

	// generator
	public void refreshGeneratorList() {
		dtGenerator = new DataTable<Region>(pl.getList().getTarget());
		addCountries();
	}

	public void generate() {
		rGenerator.startForRegions(dtGenerator.getSource());
	}
}
