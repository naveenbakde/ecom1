package com.dev.demo.ecom.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dev.demo.ecom.domain.Mobile;
import com.dev.demo.ecom.domain.Stock;

@Repository
public interface MobileRepository extends JpaRepository<Mobile, Long> {

	List<Mobile> findByCompany(String company);
	
	List<Mobile> findByName(String name);

	List<Mobile> findByRamAndMemory(Integer ram, Integer memory);
	
	@Query("Update Mobile set inStock =:stock where id =:id")
	Integer updateMobileStock(int stock, Long id);
	
	@Query("select stock from Mobile where company=:company and model=:model and color=:color and ram=:ram and memory=:memory")
	Optional<Stock> getStock(String company, String model, String color, Integer ram, Integer memory);


	List<Mobile> findByCompanyAndModelAndColorAndRamAndMemory(String company, String model, String color, Integer ram, Integer memory);
	
}
