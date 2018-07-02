/**
 * 
 */
package com.store.inventorymanagement.service;

import java.math.BigDecimal;

import com.store.inventorymanagement.domain.Store;


/**
 * @author Naveen Kankanala
 *	Service that helps controller processing the request of the user
 */
public interface InventoryService {
	
	public String addItem(String item_name, BigDecimal costPrice, BigDecimal sellingPrice);
	
	public String deleteItem(String item_name);
	
	public String updateItemQuantity(String item_name, int quantity);
	
	public String sellItem(String item_name, int quantity);
	
	public void generateReport();
	
	public Store getStoreInventory();

	public void updateSellingPrice(String item_name, BigDecimal sellingPrice);

}
