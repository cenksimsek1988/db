package com.softactive.editor.common.page;

import java.io.Serializable;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.softactive.editor.common.view.DataTable;
import com.softactive.editor.wb.manager.WorldBankRegionHandler;
import com.softactive.editor.wb.requester.WorldBankRegionSearcher;
import com.softactive.grwa.manager.PostTask;
import com.softactive.grwa.object.MyConstants;
import com.softactive.grwa.object.Region;
import com.softactive.grwa.service.GrwaContext;
import com.softactive.grwa.service.GrwaContextWrapper;
import com.softactive.grwa.service.RegionService;

import lombok.Getter;
import lombok.Setter;

@Component @Lazy
public class RegionBean implements Serializable, MyConstants {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7089023679490231774L;

	// searcher
	private WorldBankRegionSearcher rRequester;

	// Generator
	private RegionService rs;
	@Getter
	@Setter
	private DataTable<Region> dt;

	private PostTask post = new PostTask() {
		@Override
		public void onPost() {
			dt = new DataTable<Region>(((WorldBankRegionHandler)rRequester.getHandler()).getRegions());
		};
	};

	@PostConstruct
	protected void init() {
		GrwaContext c = GrwaContextWrapper.getContext();
		rs = c.getRs();
		rRequester = new WorldBankRegionSearcher();
		rRequester.setPostTask(post);
//		rRequester.start();
	}

	public void save() {
		((RegionService) rs).updateDelicate(dt.getSelected());
	}
}
