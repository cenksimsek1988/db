package com.softactive.editor.re.page;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.softactive.editor.common.view.PickList;
import com.softactive.editor.re.view.ReutersRegion;
import com.softactive.grwa.object.MyConstants;
import com.softactive.grwa.object.Region;
import com.softactive.grwa.service.GrwaContext;
import com.softactive.grwa.service.GrwaContextWrapper;
import com.softactive.grwa.service.RegionService;

import lombok.Getter;
import lombok.Setter;

@Component @Lazy
public class ReutersRegionBean implements Serializable, MyConstants {
	/**
	 * 
	 */
	private static final long serialVersionUID = 529701482771625933L;
	private RegionService rs;
	@Getter
	@Setter
	private PickList<ReutersRegion> pl;

	@PostConstruct
	protected void init() {
		GrwaContext context = GrwaContextWrapper.getContext();
		rs = context.getRs();
		List<ReutersRegion> source = ReutersRegion.getReutersRegionList(rs.getRegionsBySource(SOURCE_REUTERS, false));
		List<ReutersRegion> target = ReutersRegion.getReutersRegionList(rs.getRegionsBySource(SOURCE_REUTERS, true));
		pl = new PickList<>(source, target);
	}

	public void addCountries() {
		List<Region> toAdd = ReutersRegion.getRegionList(pl.getList().getTarget());
		List<Region> toRemove = ReutersRegion.getRegionList(pl.getList().getSource());
		for(Region r:toAdd) {
			r.setEf(true);
			rs.update(r);
		}
		for(Region r:toRemove) {
			r.setEf(false);
			rs.update(r);
		}
	}
}
