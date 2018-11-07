package com.softactive.core.manager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.poi.ss.usermodel.Row;
import org.joda.time.LocalDate;

import com.softactive.grwa.manager.MyException;
import com.softactive.grwa.object.Indicator;
import com.softactive.grwa.object.Price;
import com.softactive.grwa.object.Region;
import com.softactive.grwa.object.RiskFactor;
import com.softactive.grwa.service.GrwaContext;
import com.softactive.grwa.service.GrwaContextWrapper;
import com.softactive.grwa.service.IndicatorService;
import com.softactive.grwa.service.PriceService;

public abstract class AbstractExcelPriceHandler extends AbstractExcelHandler<List<Price>> {
	
	@PostConstruct
	public void init() {
		GrwaContext context = GrwaContextWrapper.getContext();
		ps = context.getPs();
		is = context.getIs();
	}
	private static final long serialVersionUID = 1073563475246636507L;
	private PriceService ps;
	protected IndicatorService is;

	@Override
	protected int getArrayStartIndex() {
		return 0;
	}
	@Override
	protected List<Price> getObject(Row row, Map<String, Object> sharedParams) throws MyException {
		String regionCode = null;
		Integer regionId = null;
		List<Price> list = new ArrayList<>();
		try {
			regionCode = row.getCell(regionCodeIndex).getStringCellValue();
			Region r = rs.findRegionByIsoCode(regionCode);
			if(r!=null) {
				regionId = r.getId();
			} else {
				System.out.println("Couldnt get region with region code: " + regionCode);
			}
		} catch (NullPointerException e) {
			System.out.println("Couldnt get region code from specific row: "+row.getRowNum()+ " and column: "+regionCodeIndex);
			return null;
		}
		LocalDate dataDate = resolveDate(String.valueOf((int) row.getCell(dateIndex).getNumericCellValue()));
		System.out.println("Region Code: " + regionCode);
		Map<Integer, Indicator> iMap = (Map<Integer, Indicator>) sharedParams.get(PARAM_INDICATOR_MAP);
		Iterator<Integer> keys = iMap.keySet().iterator();
		while (keys.hasNext()) {
			int key = keys.next();
			Indicator indicator = (Indicator) iMap.get(key);
			System.out.println(indicator.getName());
			RiskFactor rf = rfs.findRiskFactorByIndicatorRegionAndFrequency(indicator.getId(), regionId, indicator.getFrequencyId());
			Double value = getDoubleValue(row.getCell(key));
			if (value == null) {
				continue;
			}
			Price p = new Price();
			p.setDataDate(sqlDate(dataDate));
			p.setRiskFactorId(rf.getId());
			p.setPrice(value);
			ps.save(p);
			list.add(p);
		}
		return list;
	}
	@Override
	protected boolean hasNext(Map<String, Object> arg0) {
		return false;
	}
}
