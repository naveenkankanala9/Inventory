/**
 * 
 */
package com.store.inventorymanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.store.inventorymanagement.domain.Store;
import com.store.inventorymanagement.repository.StoreRepository;

/**
 * @author Naveen Kankanala
 *	StoreInitilizr helps in initilizing singleton store object
 */
@Component
public class StoreInitializr {

	@Autowired
	private StoreRepository storeRepository;

	public StoreInitializr(StoreRepository storeRepository){
		this.storeRepository = storeRepository;
	}
	private String storeName;
	public void initilizeStore(){
		storeName = "Barclays";
		Store store = storeRepository.getStore(storeName);
		if(store == null){
			store = Store.getInstance();
			store.setStoreName(storeName);
			storeRepository.createStore(store);
		}
		store = storeRepository.getStore(storeName);
		System.out.println(store.getStoreName());
	}

}

