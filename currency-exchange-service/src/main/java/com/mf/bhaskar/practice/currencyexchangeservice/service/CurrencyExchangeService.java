package com.mf.bhaskar.practice.currencyexchangeservice.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.mf.bhaskar.practice.currencyexchangeservice.model.ExchangeValue;
import com.mf.bhaskar.practice.currencyexchangeservice.repository.ExchangeValueRepository;

@Service
public class CurrencyExchangeService {

	private static final Logger log = LoggerFactory.getLogger(CurrencyExchangeService.class);

	@Autowired
	private Environment environment;

	@Autowired
	private ExchangeValueRepository exchangeValueRepository;
	
	public ExchangeValue retriveExchangeValue(String from, String to) {

		log.info(" Inside CurrencyExchangeService.retriveExchangeValue(from,to) @param from : {} , @param to : {}",
				from, to);

		

			if (StringUtils.isBlank(from)) {
				log.info("Invalid form parameter");
				throw new IllegalArgumentException("Invalid form parameter");
			}
			if (StringUtils.isBlank(to)) {
				log.info("Invalid to parameter");
				throw new IllegalArgumentException("Invalid to parameter");
			}

			ExchangeValue exchangeValue = exchangeValueRepository.findByFromAndTo(from, to);
			exchangeValue.setPort(Integer.parseInt(environment.getProperty("server.port")));
			log.info("Resulted exchangeValue : {}", exchangeValue);
			return exchangeValue;

	}

	public ExchangeValue createExchangeValue(ExchangeValue exchangeValue) {

		log.info(" Inside CurrencyExchangeService.createExchangeValue(exchangeValue) @param exchangeValue : {} ",
				exchangeValue);
		try {

			if (exchangeValue == null) {

				log.error("Invalid exchangeValue for create");
				throw new IllegalArgumentException("Invalid exchangeValue for update");

			}

			ExchangeValue exValue = exchangeValueRepository.save(exchangeValue);
			exValue.setPort(Integer.parseInt(environment.getProperty("server.port")));
			log.info("Resulted exchangeValue : {}", exchangeValue);
			return exValue;
		} catch (Exception e) {
			log.error("Exception occured ", e);
			throw new InternalError("Exception occured while creating exchangeValue");
		}

	}
	
	public List<ExchangeValue> retriveALLExchangeValue() {

		log.info(" Inside CurrencyExchangeService.retriveALLExchangeValue()");

		try {

			List<ExchangeValue> exchangeValues = exchangeValueRepository.findAll();
			exchangeValues.forEach(e ->{
				e.setPort(Integer.parseInt(environment.getProperty("server.port")));
			});
			log.info("Resulted exchangeValues : {}", exchangeValues);
			return exchangeValues;
		} catch (Exception e) {
			log.error("Exception occured ", e);
			throw new InternalError("Exception occured while retriving exchangeValue");
		}

	}

	public ExchangeValue updateExchangeValue(ExchangeValue exchangeValue) {

		log.info(" Inside CurrencyExchangeService.updateExchangeValue(exchangeValue) @param exchangeValue : {} ",
				exchangeValue);
		try {

			if (exchangeValue == null) {

				log.error("Invalid exchangeValue for update ");
				throw new IllegalArgumentException("Invalid exchangeValue for update");
			}

			if (exchangeValue.getId() == null || exchangeValue.getId() <= 0
					|| !exchangeValueRepository.existsById(exchangeValue.getId())) {

				log.error("Invalid exchangeValue for update ");
				throw new IllegalArgumentException("Invalid exchangeValue for update");
			}

			ExchangeValue exValue = exchangeValueRepository.save(exchangeValue);
			exValue.setPort(Integer.parseInt(environment.getProperty("server.port")));
			log.info("Resulted exchangeValue : {}", exchangeValue);
			
			return exValue;
		} catch (Exception e) {
			log.error("Exception occured ", e);
			throw new InternalError("Exception occured while updating exchangeValue");
		}

	}

	public void deleteExchangeValue(Long id) {
		log.info(" Inside CurrencyExchangeService.deleteExchangeValue(id) @param id : {} ",
				id);
		if(id == null || id <= 0) {
			log.error("Invalid request param");
			throw new IllegalArgumentException("Invalid request param");
		}
		
		try {
			
			exchangeValueRepository.deleteById(id);
		} catch (Exception e) {
			
			log.error("Exception occured ", e);
			throw new InternalError("Exception occured while deleting exchangeValue");
		}
		
		
	}
}
