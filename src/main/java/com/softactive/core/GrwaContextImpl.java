package com.softactive.core;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.softactive.core.service.IndicatorServiceImpl;
import com.softactive.core.service.PriceServiceImpl;
import com.softactive.core.service.RegionServiceImpl;
import com.softactive.core.service.RiskFactorServiceImpl;
import com.softactive.core.service.UpdateErrorServiceImpl;
import com.softactive.grwa.object.Indicator;
import com.softactive.grwa.object.Price;
import com.softactive.grwa.object.Region;
import com.softactive.grwa.object.RiskFactor;
import com.softactive.grwa.object.UpdateError;
import com.softactive.grwa.service.GrwaContext;
import com.softactive.grwa.service.IndicatorService;
import com.softactive.grwa.service.PriceService;
import com.softactive.grwa.service.RegionService;
import com.softactive.grwa.service.RiskFactorService;
import com.softactive.grwa.service.UpdateErrorService;

@Component
public class GrwaContextImpl extends GrwaContext {
	@Autowired
	PriceServiceImpl ps;
	@Autowired
	RiskFactorServiceImpl rfs;
	@Autowired
	IndicatorServiceImpl is;
	@Autowired
	UpdateErrorServiceImpl ues;
	@Autowired
	RegionServiceImpl rs;

	@Override
	public PriceService priceService() {
		return new PriceService() {

			@Override
			public void delete(int id) {
				ps.delete(id);
			}

			@Override
			public int insert(Price p) {
				return ps.insert(p);
			}

			@Override
			public List<Price> query(String sql) {
				return ps.query(sql);
			}

			@Override
			public void update(Price p) {
				ps.update(p);
			}
			
		};
	}

	@Override
	public RiskFactorService riskFactorService() {
		return new RiskFactorService() {

			@Override
			public void delete(int id) {
				rfs.delete(id);
			}

			@Override
			public int insert(RiskFactor rf) {
				return rfs.insert(rf);
			}

			@Override
			public List<RiskFactor> query(String sql) {
				return rfs.query(sql);
			}

			@Override
			public void update(RiskFactor rf) {
				rfs.update(rf);
			}
			
		};
	}

	@Override
	public UpdateErrorService updateErrorService() {
		return new UpdateErrorService() {

			@Override
			public void delete(int id) {
				ues.delete(id);
			}

			@Override
			public int insert(UpdateError ue) {
				return ues.insert(ue);
			}

			@Override
			public List<UpdateError> query(String sql) {
				return ues.query(sql);
			}

			@Override
			public void update(UpdateError ue) {
				ues.update(ue);
			}
			
		};
	}

	@Override
	public RegionService regionService() {
		return new RegionService() {

			@Override
			public void delete(int id) {
				rs.delete(id);
			}

			@Override
			public int insert(Region r) {
				return rs.insert(r);
			}

			@Override
			public List<Region> query(String sql) {
				return rs.query(sql);
			}

			@Override
			public void update(Region r) {
				rs.update(r);
			}
			
		};
	}

	@Override
	public IndicatorService indicatorService() {
		// TODO Auto-generated method stub
		return new IndicatorService() {

			@Override
			public void delete(int id) {
				is.delete(id);
			}

			@Override
			public int insert(Indicator i) {
				return is.insert(i);
			}

			@Override
			public List<Indicator> query(String sql) {
				return is.query(sql);
			}

			@Override
			public void update(Indicator i) {
				is.update(i);
			}
			
		};
	}

}
