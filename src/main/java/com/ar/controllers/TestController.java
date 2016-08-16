package com.ar.controllers;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ar.models.Book;
import com.ar.services.TestService;

@CacheConfig(cacheNames="TestController")
@Controller
@RequestMapping("/test")
public class TestController {

	private static final Logger log = LoggerFactory.getLogger(TestController.class);
	
	@Autowired
	private TestService testService;
	
	
	@RequestMapping(path = "/test1", method = RequestMethod.GET)
//	@ResponseBody
	public ResponseEntity<?> test() {
		
		System.out.println("Testing!");
		
		log.error("ERROR level");
		log.warn("WARN level");
		log.info("INFO level");
		log.debug("DEBUG level");
		
		return new ResponseEntity<>("body", null, HttpStatus.OK);
	}
	
	
	// should the @Cacheable annotation be put here instead?
	// the second time with the same parameters results in the result being returned directly (without the function being triggered)
	// use this only on things that do NOT change
	//@Cacheable(cacheNames="test-getAll", sync=true)
	@Cacheable(sync=true)
	@RequestMapping(path = "/getAll", method = RequestMethod.GET)
	public ResponseEntity<?> getAll(Boolean criteria) {
		
		log.info("*** The parameter is: {} ***", criteria);
		
		log.info("Start date: {}", new Date());
		
		List<Book> bookList = testService.getAll(criteria);
		
		log.info("End date: {}", new Date());
		
		return new ResponseEntity<>(bookList, HttpStatus.OK);
	}

	//@Cacheable(cacheNames="test-string", sync=true)
	@Cacheable(sync=true)
	@RequestMapping(path="/string", method=RequestMethod.GET)
	public ResponseEntity<?> string() {
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new ResponseEntity<>("STRING", HttpStatus.OK);
	}
	
	
	@RequestMapping(path="/saveBook", method=RequestMethod.POST)
	public ResponseEntity<?> saveBook(
			String isbn,
			String title) {
		
		testService.saveBook(isbn, title);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	/**
	 * Clear cache (all).
	 * @return
	 */
	@CacheEvict(cacheNames={"TestController", "AnotherController"}, allEntries=true)
	@RequestMapping(path="evictCache", method=RequestMethod.GET)
	public ResponseEntity<?> evictCache() {
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
