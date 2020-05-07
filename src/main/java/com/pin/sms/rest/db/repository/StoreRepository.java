package com.pin.sms.rest.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pin.sms.rest.db.model.Store;

public interface StoreRepository  extends JpaRepository<Store, Long>{

}
