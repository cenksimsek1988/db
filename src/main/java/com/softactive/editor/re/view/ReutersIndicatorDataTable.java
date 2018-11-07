package com.softactive.editor.re.view;

import java.util.List;

import com.softactive.editor.common.view.DataTable;
import com.softactive.grwa.object.Indicator;

public class ReutersIndicatorDataTable extends DataTable<Indicator> {
	public ReutersIndicatorDataTable(List<Indicator> source) {
		super(source);
	}

	public void onAddNew() {
		Indicator newIndicator = new Indicator();
		newIndicator.setSourceCode(SOURCE_REUTERS);
		source.add(newIndicator);
	}
}
