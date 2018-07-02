/**
 * 
 */
package com.store.inventorymanagement.serviceImpl;

import java.math.BigDecimal;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.inventorymanagement.domain.Item;
import com.store.inventorymanagement.domain.Store;
import com.store.inventorymanagement.repository.StoreRepository;
import com.store.inventorymanagement.service.InventoryService;

import org.springframework.transaction.annotation.Transactional;


/**
 * @author Naveen Kankanala
 *
 */
@Service
public class InventoryServiceImpl implements InventoryService{

	@Autowired
	private StoreRepository storeRepository;

	private Store barclaysStore;

	public  InventoryServiceImpl(){}

	@Autowired
	public  InventoryServiceImpl(StoreRepository storeRepository) {

		this.storeRepository = storeRepository;
	}

	public Store retrieveStore(){
		String storeName = "Barclays";
		Store store = storeRepository.getStore(storeName);
		return store;
	}

	@Override
	@Transactional
	public String addItem(String item_name, BigDecimal costPrice, BigDecimal sellingPrice) {
		Item new_item;
		try{
			barclaysStore = retrieveStore();
			new_item = new Item(item_name, costPrice, sellingPrice,barclaysStore);
			storeRepository.createItem(new_item);
			barclaysStore.addItems(new_item);
			storeRepository.updateStore(barclaysStore);
			return "Item added to inventory Successfully";
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("Failed to create item"+e);
			return "Failed to add item to inventory";
		}

	}

	@Override
	@Transactional
	public String deleteItem(String item_name) {
		Store store = getStoreInventory();
		Item item = storeRepository.getItemByName(item_name);
		if(item != null){

			BigDecimal profit = store.getProfit();
			store.setProfit(profit.subtract(item.getCostPrice()).multiply(new BigDecimal(item.getQuantity())));
			storeRepository.updateStore(store);

			storeRepository.deleteItem(item);

			return "Item removed from inventory successfully";
		}
		return "Item not found";
	}

	@Override
	@Transactional
	public String updateItemQuantity(String item_name, int quantity) {
		Item item = storeRepository.getItemByName(item_name);
		if(item != null){
			try{
				int currentQuantity = item.getQuantity();
				int updatedQuantity = currentQuantity+quantity;
				item.setQuantity(updatedQuantity);
				storeRepository.updateItem(item);
				return item_name+" quantity updated successfully";
			}
			catch(Exception e){
				System.out.println("Failed to update quantity of  item"+e);
				return " Failed to update item quantity";
			}
		}
		return " Item not found ";
	}

	@Override
	@Transactional
	public String sellItem(String item_name, int quantity) {
		Store store = getStoreInventory();
		Item item = storeRepository.getItemByName(item_name);
		if(item != null){
			try{
				int currentQuantity = item.getQuantity();
				if(currentQuantity != 0 && currentQuantity >= quantity){
					int updatedQuantity = currentQuantity-quantity;
					item.setQuantity(updatedQuantity);
					int currentSoldQuantity = 0;
					if(item.getSold_quantity() != 0)
					{
						currentSoldQuantity= item.getSold_quantity();
					}
					int updatedSoldQuantity = currentSoldQuantity+quantity;
					item.setSold_quantity(updatedSoldQuantity);
					storeRepository.updateItem(item);
					// Update Store Profit
					BigDecimal profit = store.getProfit();
					store.setProfit(profit.add(item.getSellingPrice().subtract(item.getCostPrice()).multiply(new BigDecimal(quantity))));
					storeRepository.updateStore(store);
					return item_name+" Available quantity and sell quantity updated successfully";
				}
				else{
					return item_name+" has not enough stock to sell ";
				}
			}
			catch(Exception e){
				e.printStackTrace();
				System.out.println("Failed to update quantity of item after selling"+e);
				return "Failed to update sell quantity";
			}
		}
		return " Item not found ";
	}


	@Override
	@Transactional
	public void generateReport() {
		System.out.println("               INVENTORY REPORT                ");
		System.out.println("Item Name 	Bought At    	Sold At  	AvailableQty    	Value");
		System.out.println("--------- 	---------    	-------  	-----------     	-------");
		Store store = getStoreInventory();
		Set<Item> itemsList = store.getItems();
		itemsList.stream()
				.forEach(item -> System.out.println(item.getItemName()+"           "+item.getCostPrice()+"        	  "+
						item.getSellingPrice()+"          "+item.getQuantity()+"                     "+
						item.getCostPrice().multiply(new BigDecimal(item.getQuantity()))));
		System.out.println("--------------------------------------------------------------------------------");
		BigDecimal totalValue = itemsList.stream()
				.map(item -> item.getCostPrice().multiply(new BigDecimal(item.getQuantity())))
				.reduce(new BigDecimal(0), (x1,x2) -> x1.add(x2));

		System.out.println("Total Value                                                              "+totalValue);
		System.out.println("Profit since previous report                                             "+store.getProfit());
		store.setProfit(new BigDecimal(0.0));
		storeRepository.updateStore(store);
	}

	@Override
	@Transactional
	public Store getStoreInventory() {
		return storeRepository.getStore("Barclays");
	}

	@Override
	@Transactional
	public void updateSellingPrice(String item_name, BigDecimal sellingPrice) {
		Item item = storeRepository.getItemByName(item_name);
		if(item != null){
			try{
				item.setSellingPrice(sellingPrice);
				storeRepository.updateItem(item);
			}
			catch(Exception e){
				System.out.println("Failed to update selling price of  item"+e);
			}
		}
	}

}
