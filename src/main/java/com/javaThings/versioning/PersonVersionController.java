package com.javaThings.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersionController {
	
	//URI versioning
	@GetMapping("/v1/person")
	public PersonV1 personV1()
	{
		return new PersonV1("Vijay Jatti");
	}

	@GetMapping("/v2/person")
	public PersonV2 personV2()
	{
		return new PersonV2(new Name("Vijay","Jatti"));
	}
	
	//Request Parameter versioning
	@GetMapping(value="/person",params="version=1")
	public PersonV1 paramsV1()
	{
		return new PersonV1("Vijay Jatti");
	}

	@GetMapping(value="/person",params="version=2")
	public PersonV2 paramsV2()
	{
		return new PersonV2(new Name("Vijay","Jatti"));
	}
	
	//headers versioning
	@GetMapping(value="/person",headers="PERSON-API-ID=1")
	public PersonV1 headersV1()
	{
		return new PersonV1("Vijay Jatti");
	}

	@GetMapping(value="/person",headers="PERSON-API-ID=2")
	public PersonV2 headersV2()
	{
		return new PersonV2(new Name("Vijay","Jatti"));
	}
	
	//Media type versioning
	
	@GetMapping(value="/person",produces="application/vnd-company-v1+json")
	public PersonV1 MIMEV1()
	{
		return new PersonV1("Vijay Jatti");
	}

	@GetMapping(value="/person",produces="application/vnd-company-v2+json")
	public PersonV2 MIMEV2()
	{
		return new PersonV2(new Name("Vijay","Jatti"));
	}
}
