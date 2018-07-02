/**
 * 
 */
package com.store.inventorymanagement.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.store.inventorymanagement.domain.Item;
import com.store.inventorymanagement.domain.Store;

/**
 * @author Naveen Kankanala
 *	Repository that handles the database transactions
 * 	Stores and retrieves the items data from the relational database
 * 	using entity manager and hibernate
 */
@Repository
@Transactional
public class StoreRepository {
	
	@PersistenceContext
    private EntityManager entityManager;
	
	public void createStore(Store store){
		entityManager.persist(store);
	}
	
	public void updateStore(Store store){
		entityManager.persist(store);
	}
	
	public Store getStore(String storeName){
		return entityManager.find(Store.class, storeName);
	}
	
	public void createItem(Item item){
		entityManager.persist(item);
	}

	public Item getItemByName(String item_name){
		return entityManager.find(Item.class, item_name);
	}
	
	public void updateItem(Item item){
		entityManager.persist(item);
	}
	
	public void deleteItem(Item item){
		if(entityManager.contains(item)){
		entityManager.remove(item);
		}
		else{
			entityManager.remove(entityManager.merge(item));
		}
	}
}
