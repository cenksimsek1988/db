package com.softactive.core.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.softactive.core.service.AbstractDataService;
import com.softactive.grwa.manager.AbstractHandler;
import com.softactive.grwa.manager.MyException;
import com.softactive.grwa.object.Pullable;

public abstract class AbstractSqlHandler<OUT, DATA_OBJECT extends Pullable> extends AbstractHandler<AbstractDataService<DATA_OBJECT>, OUT, List<DATA_OBJECT>, List<DATA_OBJECT>, DATA_OBJECT> {
	private static final long serialVersionUID = 2985193212196151772L;

	@Override
	protected List<DATA_OBJECT> onFormatResponse(AbstractDataService<DATA_OBJECT> rowInput) throws MyException {
		return rowInput.query(rowInput.initQuery());
	}

	@Override
	protected void mapMetaData(List<DATA_OBJECT> r, Map<String, Object> sharedParams) throws MyException {
	}

	@Override
	protected boolean hasNext(Map<String, Object> metaMap) {
		return false;
	}

	@Override
	protected List<DATA_OBJECT> getArray(List<DATA_OBJECT> r, Map<String, Object> sharedParams) {
		return r;
	}

	@Override
	protected List<OUT> getList(List<DATA_OBJECT> array, Map<String, Object> sharedParams) {
		List<OUT> list = new ArrayList<OUT>();
		for(DATA_OBJECT o:array) {
			try {
				list.add(getObject(o, sharedParams));
			} catch (MyException e) {
				System.out.println(e);
			}
		}
		return list;
	}

}
