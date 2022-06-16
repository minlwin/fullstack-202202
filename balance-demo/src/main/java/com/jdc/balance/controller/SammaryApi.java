package com.jdc.balance.controller;

import java.time.Month;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.balance.model.domain.vo.HomeVO;

@RestController
@RequestMapping("user/api")
public class SammaryApi {

	@GetMapping("years")
	List<Integer> years() {
		return List.of(2022);
	}
	
	@GetMapping("months")
	List<Month> months(@RequestParam  Integer year) {
		return List.of(Month.values());
	}
	
	@GetMapping
	HomeVO load(@RequestParam Integer year, @RequestParam Month month) {
		return new HomeVO();
	}
}
