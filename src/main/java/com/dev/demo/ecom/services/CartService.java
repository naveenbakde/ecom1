package com.dev.demo.ecom.services;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.dev.demo.ecom.domain.Mobile;
import com.dev.demo.ecom.domain.Stock;
import com.dev.demo.ecom.domain.User;
import com.dev.demo.ecom.domain.Wallet;
import com.dev.demo.ecom.repository.MobileRepository;
import com.dev.demo.ecom.repository.UserRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class CartService {

	private MobileRepository mobileRepository;
	private UserRepository userRepository;

	public List<Mobile> getCartForUser(User user) {
		return userRepository.getUserCart(user.getUserId());
	}

	@Transactional
	public List<Mobile> addProductToCart(Mobile mobile, User user) throws Exception {
			return addToUserCart(mobile, user).getCart();
	}
	
	@Transactional
	public User addToUserCart(Mobile mobile, User user) throws Exception {
		log.info("Add Product : {}, for user : {}", mobile, user.getFirstName());
		Stock stock = getStock(mobile);
		if (null != stock && stock.getStock() > 0) {
			List<Mobile> mobList = mobileRepository.findByCompanyAndModelAndColorAndRamAndMemory(mobile.getCompany(),
					mobile.getModel(), mobile.getColor(), mobile.getRam(), mobile.getMemory());
			if (mobList != null && !mobList.isEmpty()) {
				List<Mobile> cart = Optional.ofNullable(getCartForUser(user)).orElse(Collections.emptyList());
				cart.add(mobList.get(0));
				user.setCart(cart);
			}
			return userRepository.save(user);
		} else {
			// TO-DO: handle out of stock
			throw new Exception("Out of stock");
		}
	}

	@Transactional(rollbackOn = Exception.class)
	public String placeOrder(User user) {
		// TO-DO: modify after wallet implementation
		String result = "SUCCESS";
		if (balanceAvailable(user)) {
			for (Mobile mob : Optional.of(user.getCart()).orElse(Collections.emptyList())) {
				if (getStock(mob).getStock() > 0) {
					//reduce stock 
					updateStock(mob);
					//update user balance
					updateUserWallet(user, mob);
				} else {
					result = "PARTIAL SUCCESS";
				}
			}
			return result;
		}
		return "FAIL";
	}

	private void updateUserWallet(final User user, final Mobile mob) {
		User user1 = userRepository.findById(user.getUserId()).get();
		user1.getWallet().setBalance(user.getWallet().getBalance() - mob.getPrice());
		userRepository.save(user1);
	}

	private void updateStock(Mobile mob) {
		mob.getStock().setStock(mob.getStock().getStock() - 1);
		mobileRepository.save(mob);
	}

	private boolean balanceAvailable(final User user1) {
		User user = user1;
		double totalPrice = 0;
		List<Mobile> mobiles = Optional.of(user.getCart()).orElse(Collections.emptyList());
		if (!mobiles.isEmpty()) {
			totalPrice = mobiles.stream().mapToDouble(Mobile::getPrice).sum();
		}
		Optional<Wallet> walletOpt = Optional.ofNullable(user.getWallet());
		if (!walletOpt.isPresent()) {
			user = updateUserWallet(user1);
		}
		return user.getWallet().getBalance() >= totalPrice;
	}

	private User updateUserWallet(final User user) {
		User user1 = userRepository.findById(user.getUserId()).get();
		user1.setWallet(Wallet.builder().balance(1000).build());
		return userRepository.save(user1);		
	}

	private Stock getStock(final Mobile mobile) {
		return mobileRepository.getStock(mobile.getCompany(), mobile.getModel(), mobile.getColor(), mobile.getRam(),
				mobile.getMemory()).orElse(Stock.builder().stock(0).build());
	}

}
