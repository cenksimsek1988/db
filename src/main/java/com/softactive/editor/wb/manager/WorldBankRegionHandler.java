package com.softactive.editor.wb.manager;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.json.JSONObject;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.softactive.grwa.manager.MyException;
import com.softactive.grwa.object.Region;
import com.softactive.grwa.service.GrwaContext;
import com.softactive.grwa.service.GrwaContextWrapper;
import com.softactive.grwa.service.RegionService;
import com.softactive.grwa.wb.AbstractWorldBankHandler;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorldBankRegionHandler extends AbstractWorldBankHandler<Region> {
	
	public WorldBankRegionHandler() {
		super();
		GrwaContext c = GrwaContextWrapper.getContext();
		rs = c.getRs();
	}
	
	private static final long serialVersionUID = 1524637164436733725L;
	public static final String SAVE_WORLD_BANK_REGION_STATUS = "region";
	public static final String ID = "id";
	public static final String ISO2c = "iso2code";
	public static final String ISO2C = "iso2Code";
	public static final String NAME = "name";
	public static final String REGION = "region";
	public static final String ADMIN_REGION = "adminregion";
	public static final String INCOME = "incomeLevel";
	public static final String LENDING = "lendingType";
	public static final String CAPITAL = "capitalCity";
	public static final String TYPE_AGGREGATES = "Aggregates";
	public static final String WORLD_ISO2 = "1W";

	private List<Region> regions;
	private RegionService rs;

	@Override
	protected Region getObject(JSONObject jo, Map<String, Object> requestParams) throws MyException {
		Region item = new Region();

		String code = resolveValidString(jo, ISO2C, null);
		item.setIsoCode(code);

		Region in = rs.findRegionByIsoCode(code);
		if(in!=null) {
			return in;
		}

		String code3 = resolveValidString(jo, ID, null);
		item.setCode3(code3);

		item.setName(resolveValidString(jo, NAME, null));

		// WB parent region and admin region if it is a country
		// or set type as GROUP or WORLD
		JSONObject regionJO = resolveValidJSONObject(jo, REGION);

		String parentRegionCode = optValidString(regionJO, ISO2c, 2);
		if (parentRegionCode != null) {
			item.setRegionCode(parentRegionCode);
			item.setAggregate(false);
		} else {
			item.setAggregate(true);
		}

		JSONObject adminRegionJO = resolveValidJSONObject(jo, ADMIN_REGION);
		String adminRegionCode = optValidString(adminRegionJO, ISO2c, 2);
		if (adminRegionCode != null) {
			item.setAdminRegionCode(adminRegionCode);
		}

		JSONObject incomeLevelJO = resolveValidJSONObject(jo, INCOME);
		String incomeCode = optValidString(incomeLevelJO, ID, 3);
		if (incomeCode != null) {
			item.setIncomeCode(incomeCode);
		}

		JSONObject lendingTypeJO = resolveValidJSONObject(jo, LENDING);
		String lendingCode = optValidString(lendingTypeJO, ID, 3);
		if (lendingCode != null) {
			item.setLendingCode(lendingCode);
		}

		String capital = optValidString(jo, CAPITAL, null);
		if (capital != null) {
			item.setCapital(capital);
		}

		Integer id = rs.insert(item);
		item.setId(id);
		return item;
	}

	@Override
	public void onListSuccesfullyParsed(List<Region> list, Map<String, Object> requestParams) {
		regions = list;
	}
}
