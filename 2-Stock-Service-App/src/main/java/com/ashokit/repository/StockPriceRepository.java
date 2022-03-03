package com.ashokit.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashokit.entity.StockPrice;

public interface StockPriceRepository extends JpaRepository<StockPrice, Serializable> {
	
	public StockPrice findByCompanyName(String companyName);

}
