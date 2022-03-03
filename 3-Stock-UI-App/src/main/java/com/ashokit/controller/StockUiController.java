package com.ashokit.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ashokit.service.StockUiService;

@Controller
public class StockUiController {

	@Autowired
	private StockUiService service;

	@GetMapping("/")
	public String loadForm() {
		return "index";
	}

	@GetMapping("/getTotalCost")
	public String getTotalCost(HttpServletRequest req, Model model) {

		String companyName = req.getParameter("companyName");
		String quantity = req.getParameter("quantity");

		String response = service.getTotalStockPrice(companyName, Integer.parseInt(quantity));

		model.addAttribute("msg", response);

		return "index";
	}

}
