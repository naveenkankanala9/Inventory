/**
 * 
 */
package com.store.inventorymanagement.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


/**
 * @author Naveen Kankanala
 *
 */
@Entity
public class Item {
	
	@Id
	private String itemName;
	private BigDecimal costPrice;
	private BigDecimal sellingPrice;
	private int quantity;
	private int sold_quantity;
	
	@ManyToOne
	@JoinColumn(name="store")
	private Store store;
	
	public Item(String itemName,BigDecimal costPrice,BigDecimal sellingPrice,Store store){
		this.itemName = itemName;
		this.costPrice = costPrice;
		this.sellingPrice = sellingPrice;
		this.store = store;
	}

	public Item(){}

	/**
	 * @return the itemName
	 */
	public String getItemName() {
		return itemName;
	}


	/**
	 * @param itemName the itemName to set
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}


	/**
	 * @return the costPrice
	 */
	public BigDecimal getCostPrice() {
		return costPrice;
	}


	/**
	 * @param costPrice the costPrice to set
	 */
	public void setCostPrice(BigDecimal costPrice) {
		this.costPrice = costPrice;
	}


	/**
	 * @return the sellingPrice
	 */
	public BigDecimal getSellingPrice() {
		return sellingPrice;
	}


	/**
	 * @param sellingPrice the sellingPrice to set
	 */
	public void setSellingPrice(BigDecimal sellingPrice) {
		this.sellingPrice = sellingPrice;
	}


	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}


	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Item [itemName=" + itemName + ", costPrice=" + costPrice + ", sellingPrice="
				+ sellingPrice + ", quantity=" + quantity + "]";
	}

	/**
	 * @return the sold_quantity
	 */
	public int getSold_quantity() {
		return sold_quantity;
	}

	/**
	 * @param sold_quantity the sold_quantity to set
	 */
	public void setSold_quantity(int sold_quantity) {
		this.sold_quantity = sold_quantity;
	}

	/**
	 * @return the store
	 */
	public Store getStore() {
		return store;
	}

	/**
	 * @param store the store to set
	 */
	public void setStore(Store store) {
		this.store = store;
	}
	
	
}
