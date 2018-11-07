package com.softactive.core.service;

import org.springframework.stereotype.Repository;

import com.softactive.grwa.object.UpdateError;

@Repository
public class UpdateErrorServiceImpl extends AbstractDataService<UpdateError> {
	protected UpdateErrorServiceImpl() {
		super("update_error");
	}
}
