package com.softactive.editor.pr.page;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.softactive.editor.ef.manager.EFRiskFactorGenerator;
import com.softactive.grwa.object.MyConstants;

@Component @Lazy
public class PRRiskFactorBean implements Serializable, MyConstants {
	private static final long serialVersionUID = -184329787567297839L;

	@Autowired
	private EFRiskFactorGenerator rGenerator;

	public void generate() {
		rGenerator.start();
	}
}
