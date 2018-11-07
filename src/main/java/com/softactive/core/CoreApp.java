package com.softactive.core;

import java.util.Calendar;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.softactive.grwa.bean.Requester;
import com.softactive.grwa.service.GrwaContextWrapper;
@SpringBootApplication
@ComponentScan(basePackages="com.softactive")
public class CoreApp 
implements CommandLineRunner
{
	private Requester requester;
	@Autowired GrwaContextImpl context;

	public static void main(String[] args) throws Exception {
		SpringApplication.run(CoreApp.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		GrwaContextWrapper.registerGrwaContext(context);
		requester = new Requester();
		requester.update();
	}
}
