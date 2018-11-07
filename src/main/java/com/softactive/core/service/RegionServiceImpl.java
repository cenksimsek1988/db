package com.softactive.core.service;

import org.springframework.stereotype.Repository;

import com.softactive.grwa.object.Region;

@Repository
public class RegionServiceImpl extends AbstractDataService<Region> {

	public RegionServiceImpl() {
		super("cmn_region");
	}
}
