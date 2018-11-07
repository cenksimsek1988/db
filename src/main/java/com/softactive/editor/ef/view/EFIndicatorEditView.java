package com.softactive.editor.ef.view;

import java.util.List;

import com.softactive.editor.common.view.DataTable;
import com.softactive.grwa.object.Indicator;

public class EFIndicatorEditView extends DataTable<Indicator> {
	public EFIndicatorEditView(List<Indicator> source) {
		super(source);
	}

	public void onAddNew() {
		Indicator newIndicator = new Indicator();
		newIndicator.setSourceCode(SOURCE_ECONOMIC_FREEDOM);
		source.add(newIndicator);
	}
}
