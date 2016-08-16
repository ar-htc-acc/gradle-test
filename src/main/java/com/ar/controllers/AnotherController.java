package com.ar.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@CacheConfig(cacheNames="AnotherController")
@Controller
@RequestMapping("/another")
public class AnotherController {

	private static final Logger log = LoggerFactory.getLogger(AnotherController.class);
	
	
	@Cacheable(sync=true)
	@RequestMapping(path="/test", method=RequestMethod.GET)
	public ResponseEntity<?> test() {
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new ResponseEntity<>("ANOTHER", HttpStatus.OK);
	}
}
