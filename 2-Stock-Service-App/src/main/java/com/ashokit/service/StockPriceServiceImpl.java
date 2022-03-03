package com.ashokit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashokit.entity.StockPrice;
import com.ashokit.exception.CompanyNotFoundException;
import com.ashokit.repository.StockPriceRepository;

@Service
public class StockPriceServiceImpl implements StockPriceService {

	@Autowired
	private StockPriceRepository stockPriceRepo;
	
	@Override
	public Double getStockPrice(String companyName) {
		
		StockPrice stockPrice = stockPriceRepo.findByCompanyName(companyName);
		
		if(stockPrice == null) {
			throw new CompanyNotFoundException("Company Not Availabe");
		}
		
		return stockPrice.getStockPrice();
	}

}
