package com.softactive.editor.pr.view;

import java.util.List;

import com.softactive.editor.common.view.DataTable;
import com.softactive.grwa.object.Indicator;

public class PRIndicatorDataTable extends DataTable<Indicator> {
	public PRIndicatorDataTable(List<Indicator> source) {
		super(source);
	}

	public void onAddNew() {
		Indicator newIndicator = new Indicator();
		newIndicator.setSourceCode(SOURCE_POLITICAL_RISK);
		source.add(newIndicator);
	}
}
