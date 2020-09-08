package com.dev.demo.ecom.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dev.demo.ecom.domain.Mobile;
import com.dev.demo.ecom.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findById(Long id);
	
	@Query("update User set cart=:items where user_id=:userId")
	Optional<List<Mobile>> updateUserCart(@Param("items") List<Mobile> items, Long userId);

	@Query("select cart from User where user_id=:userId")
	List<Mobile> getUserCart(Long userId);
}
