package com.javaThings.Controller;

import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.javaThings.Bean.SomeBean;
import com.javaThings.Bean.User;

@RestController
public class HelloWorld {
	
	@Autowired
	MessageSource messageSource;

	@GetMapping(value="/")
	public String HelloWorldPoint()
	{
		return "Hello World";
	}
	@GetMapping(value="/hello-internationalized")
	public String HelloWorldi18n(@RequestHeader(name="Accept-Language",required=false) Locale locale)
	{
		return messageSource.getMessage("good.morning.message", null, locale);
	}
	
	@GetMapping(value="/Users")
	public User sendUsers()
	{
		return new User("Vijay", "Bijapur", new Date());
	}
	
	@GetMapping(value="/ignore-fields")
	public MappingJacksonValue getBeans()
	{
		
		SimpleBeanPropertyFilter filter=SimpleBeanPropertyFilter.filterOutAllExcept("field1");
		
		FilterProvider filterProvider =new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
		SomeBean bean=new SomeBean("field1","field2");
		MappingJacksonValue  jacksonValue=new MappingJacksonValue(bean);
		
		jacksonValue.setFilters(filterProvider);
		return jacksonValue;
	}
}
