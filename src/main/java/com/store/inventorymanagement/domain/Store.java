/**
 * 
 */
package com.store.inventorymanagement.domain;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;


/**
 * @author Naveen Kankanala
 *
 */
@Entity
public class Store {
	
	@Id
	private String storeName;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="store",targetEntity=Item.class)
	private Set<Item> items = new HashSet<>();

	private BigDecimal profit;
	
    private static Store BarclaysStore = null;
    
	private Store(){
		profit = new BigDecimal(0.0);
	}
	
    public static Store getInstance()
    {
        if (BarclaysStore == null)
        	BarclaysStore = new Store();
 
        return BarclaysStore;
    }

	/**
	 * @return the storeName
	 */
	public String getStoreName() {
		return storeName;
	}

	/**
	 * @param storeName the storeName to set
	 */
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	/**
	 * @return the items
	 */
	public Set<Item> getItems() {
		return items;
	}

	/**
	 * @param items the items to set
	 */
	public void setItems(Set<Item> items) {
		this.items = items;
	}
    
    public void addItems(Item item){
    	getItems().add(item);
    }

	public BigDecimal getProfit() {
		return profit;
	}

	public void setProfit(BigDecimal profit) {
		this.profit = profit;
	}
}
