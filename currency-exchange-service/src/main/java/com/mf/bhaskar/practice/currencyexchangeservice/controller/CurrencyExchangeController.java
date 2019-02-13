package com.mf.bhaskar.practice.currencyexchangeservice.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mf.bhaskar.practice.currencyexchangeservice.model.ExchangeValue;
import com.mf.bhaskar.practice.currencyexchangeservice.service.CurrencyExchangeService;

@RestController
@RequestMapping("/currency-exchange")
public class CurrencyExchangeController {

	private static final Logger log = LoggerFactory.getLogger(CurrencyExchangeController.class);

	@Autowired
	private CurrencyExchangeService currencyExchangeService;
	
	@GetMapping()
	public List<ExchangeValue> retriveALLExchangeValue() {
		log.info(" ****** CurrencyExchangeController.retriveALLExchangeValue() **** START **** ");
		log.info(" CurrencyExchangeController.retriveExchangeValue()");

		List<ExchangeValue> exchangeValues = currencyExchangeService.retriveALLExchangeValue();

		log.info(" ****** CurrencyExchangeController.retriveExchangeValue(from,to) **** END **** ");
		return exchangeValues;
	}

	@GetMapping("/from/{from}/to/{to}")
	public ExchangeValue retriveExchangeValue(@PathVariable("from") String from, @PathVariable("to") String to) {
		log.info(" ****** CurrencyExchangeController.retriveExchangeValue(from,to) **** START **** ");
		log.info(" CurrencyExchangeController.retriveExchangeValue(from,to) @param from : {} , @param to : {} ", from,
				to);

		ExchangeValue exchangeValue = currencyExchangeService.retriveExchangeValue(from, to);

		log.info(" ****** CurrencyExchangeController.retriveExchangeValue(from,to) **** END **** ");
		return exchangeValue;
	}

	@PostMapping()
	public ExchangeValue createExchangeValue(@RequestBody ExchangeValue exchangeValue) {
		log.info(" ****** CurrencyExchangeController.createExchangeValue(exchangeValue) **** START **** ");
		log.info(" CurrencyExchangeController.createExchangeValue(exchangeValue) @param exchangeValue : {} ",
				exchangeValue);

		ExchangeValue exValue = currencyExchangeService.createExchangeValue(exchangeValue);

		log.info(" ****** CurrencyExchangeController.createExchangeValue(exchangeValue) **** END **** ");

		return exValue;
	}

	@PutMapping()
	public ExchangeValue updateExchangeValue(@RequestBody ExchangeValue exchangeValue) {
		log.info(" ****** CurrencyExchangeController.updateExchangeValue(exchangeValue) **** START **** ");
		log.info(" CurrencyExchangeController.updateExchangeValue(exchangeValue) @param exchangeValue : {} ",
				exchangeValue);

		ExchangeValue exValue = currencyExchangeService.updateExchangeValue(exchangeValue);

		log.info(" ****** CurrencyExchangeController.updateExchangeValue(exchangeValue) **** END **** ");

		return exValue;

	}

	@DeleteMapping("/{id}")
	public void deleteExchangeValue(@PathVariable("id") Long id) {
		log.info(" ****** CurrencyExchangeController.deleteExchangeValue(id) **** START **** ");
		log.info(" CurrencyExchangeController.deleteExchangeValue(id) @param exchangeValue : {} ", id);
		
		currencyExchangeService.deleteExchangeValue(id);
		log.info(" ****** CurrencyExchangeController.deleteExchangeValue(id) **** END **** ");

	}
}
