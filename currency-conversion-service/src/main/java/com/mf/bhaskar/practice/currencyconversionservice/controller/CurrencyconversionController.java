package com.mf.bhaskar.practice.currencyconversionservice.controller;

import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.mf.bhaskar.practice.currencyconversionservice.bean.CurrencyConversionBean;
import com.mf.bhaskar.practice.currencyconversionservice.proxy.CurrencyExchangeProxy;

@RestController
public class CurrencyconversionController {
	private static final Logger log = LoggerFactory.getLogger(CurrencyconversionController.class);
	
	@Autowired
	private CurrencyExchangeProxy proxy;

	@GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean convertCurrencyFeign(@PathVariable("from") String from, @PathVariable("to") String to,
			@PathVariable("quantity") BigDecimal quantity) {
		log.info(" ****** CurrencyconversionController.convertCurrencyFeign(from,to,quantity) **** START **** ");
		log.info("CurrencyconversionController.convertCurrencyFeign request param  from : {} , to : {}, quantity : {} ", from, to, quantity);
	
		if(StringUtils.isBlank(from)) {
			log.error("Invalid form parameter");
			throw new IllegalArgumentException("Invalid form parameter");
		}
		
		if(StringUtils.isBlank(to)) {
			throw new IllegalArgumentException("Invalid to parameter");
		}
		
		if(quantity == null || quantity.compareTo(BigDecimal.ZERO) <= 0) {
			
			throw new IllegalArgumentException("Invalid quantity");
		}
		
		CurrencyConversionBean response = null;
		try {
			response = proxy.retriveExchangeValue(from, to);
			
		} catch (Exception e) {
			
			log.error("Exception Occured  ",e);
			throw new IllegalArgumentException("Invalid request params");
		}
		log.info("Response from CurrencyExchangeProxy.retriveExchangeValue : {}", response);
		return new CurrencyConversionBean(response.getId(), from, to, response.getConversionMutiple(), quantity,
				quantity.multiply(response.getConversionMutiple()), response.getPort());
	}

}
