package com.ashokit.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ashokit.client.StockPriceClient;

@RestController
@RequestMapping("/calc")
public class StockCalculationRestController {

	@Autowired
	private StockPriceClient stockPriceClient;

	@GetMapping(value = "/calculate/{companyName}/{quantity}")
	public ResponseEntity<?> calculate(@PathVariable("companyName") String companyName,
			@PathVariable("quantity") Integer quantity) {

		Double totalPrice = null;
		ResponseEntity<?> resEntity = null;

		try {
			resEntity = stockPriceClient.getStockPrice(companyName);
			int statusCode = resEntity.getStatusCode().value();
			if (statusCode == 200) {
				Double companyStockPrice = (Double) resEntity.getBody();
				totalPrice = quantity * companyStockPrice;
				String response = "Total Price :: " + totalPrice;
				resEntity = new ResponseEntity<String>(response, HttpStatus.OK);
			}
		} catch (Exception e) {
			resEntity = new ResponseEntity<String>("Company Name Not Found", HttpStatus.BAD_REQUEST);
		}
		return resEntity;
	}

}
