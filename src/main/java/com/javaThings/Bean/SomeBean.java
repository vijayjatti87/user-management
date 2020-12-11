package com.javaThings.Bean;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//@JsonIgnoreProperties(value="field2")
@JsonFilter(value="SomeBeanFilter")
public class SomeBean {
	//@JsonIgnore
private String field1;
private String field2;
public String getField1() {
	return field1;
}
public void setField1(String field1) {
	this.field1 = field1;
}
public String getField2() {
	return field2;
}
public void setField2(String field2) {
	this.field2 = field2;
}
public SomeBean(String field1, String field2) {
	super();
	
	this.field1 = field1;
	this.field2 = field2;
}

}
