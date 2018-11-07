package com.softactive.updater.pr.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.poi.ss.usermodel.Workbook;
import org.joda.time.LocalDate;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.softactive.core.manager.AbstractExcelPriceHandler;
import com.softactive.grwa.manager.MyException;
import com.softactive.grwa.object.Indicator;

@Component @Lazy
public class PRHandler extends AbstractExcelPriceHandler {

	private static final long serialVersionUID = -526130472012738712L;
	public static final String PR_FILE_NAME = "C:\\\\Users\\\\riskactive\\\\Desktop\\\\GRWA\\\\excels\\pr.xlsx";

	@PostConstruct
	public void init() {
		super.init();
		dateIndex = 5;
		regionCodeIndex = 3;
	}

	protected Map<Integer, Indicator> getIndicatorMap() {
		Map<Integer, Indicator> map = new HashMap<>();
		List<Indicator> prIndicators = is.getIndicatorsBySource(SOURCE_POLITICAL_RISK);
		for(Indicator i:prIndicators) {
			map.put(i.getExcelColumnIndex(), i);
		}

		//		map.put(11, "PR:priq");
		//		map.put(6, "PR:cp");
		//		map.put(7, "PR:prcl");
		//		map.put(7, "PR:da");
		//		map.put(9, "PR:pg");
		//		map.put(10, "PR:pa");
		return map;
	}

	@Override
	protected LocalDate resolveDate(String dateString) {
		return LocalDate.parse(dateString + "-12-31");
	}

	@Override
	protected int getArrayStartIndex() {
		return 1;
	}

	@Override
	protected void mapMetaData(Workbook wb, Map<String, Object> sharedParams) throws MyException {
		sharedParams.put(PARAM_INDICATOR_MAP, getIndicatorMap());
	}
}
