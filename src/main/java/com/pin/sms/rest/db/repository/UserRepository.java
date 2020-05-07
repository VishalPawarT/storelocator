package com.pin.sms.rest.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pin.sms.rest.db.model.AppUser;

public interface UserRepository extends JpaRepository<AppUser, Long>{

	AppUser findByUsernameAndPassword(String username, String password);

	AppUser findByUserIdAndToken(Long userId, String otp);

}
