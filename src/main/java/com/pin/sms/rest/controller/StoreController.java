package com.pin.sms.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pin.sms.rest.db.model.Store;
import com.pin.sms.rest.db.repository.StoreRepository;
import com.pin.sms.rest.response.ApiResponseBuilderUtil;

@RestController
@RequestMapping("/")
public class StoreController {

	@Autowired
	private StoreRepository storeRepository;
	
	@PostMapping("search/store")
	public ResponseEntity<List<Store>> getStores() {
		return ApiResponseBuilderUtil.toResponseEntity(storeRepository.findAll(), HttpStatus.valueOf(200));
	}
	
	@PostMapping("create/store")
	public ResponseEntity<Store> createStore(@RequestBody Store store) {
		storeRepository.save(store);
		return ApiResponseBuilderUtil.toResponseEntity(store, HttpStatus.valueOf(200));
	}
	
	
	

}
