/**
 * 
 */
package com.store.inventorymanagement.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.store.inventorymanagement.service.InventoryService;

/**
 * @author Naveen Kankanala
 *
 */
@Component
public class StoreController {

	@Autowired
	private InventoryService inventoryService;

	public StoreController(){}

	public void addItems(String item_name,BigDecimal cost_price, BigDecimal selling_price){
		String response = inventoryService.addItem(item_name,cost_price,selling_price);
	}

	public void updateItemQuantity(String item_name,int item_quantity){
		String response =inventoryService.updateItemQuantity(item_name, item_quantity);
	}

	public void updateSell(String item_name,int sellQty){
		String response =inventoryService.sellItem(item_name, sellQty);
	}

	public void deleteItem(String item_name){
		String response =inventoryService.deleteItem(item_name);
	}

	public void generateReport(){
		inventoryService.generateReport();
	}

	public void updateSellingPrice(String item_name,BigDecimal selling_price){
		inventoryService.updateSellingPrice(item_name, selling_price);
	}
}
