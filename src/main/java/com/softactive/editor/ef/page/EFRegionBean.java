package com.softactive.editor.ef.page;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.softactive.editor.common.view.PickList;
import com.softactive.grwa.object.MyConstants;
import com.softactive.grwa.object.Region;
import com.softactive.grwa.service.GrwaContext;
import com.softactive.grwa.service.GrwaContextWrapper;
import com.softactive.grwa.service.RegionService;

import lombok.Getter;
import lombok.Setter;

@Component @Lazy
public class EFRegionBean implements Serializable, MyConstants {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7612018794041979310L;
	private RegionService rs;
	@Getter
	@Setter
	private PickList<Region> pl;

	@PostConstruct
	protected void init() {
		GrwaContext c = GrwaContextWrapper.getContext();
		rs = c.getRs();
		List<Region> source = rs.getRegionsBySource(SOURCE_ECONOMIC_FREEDOM, false);
		List<Region> target = rs.getRegionsBySource(SOURCE_ECONOMIC_FREEDOM, true);
		pl = new PickList<>(source, target);
	}

	public void addCountries() {
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
}
