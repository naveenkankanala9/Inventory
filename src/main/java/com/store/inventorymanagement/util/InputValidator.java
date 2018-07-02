/**
 * 
 */
package com.store.inventorymanagement.util;


import org.springframework.stereotype.Component;

/**
 * @author Naveen Kankanala
 *
 */
@Component
public class InputValidator {

	public boolean validateInput(String[] userInput){
	  switch (userInput[0]) {
		case "create":
			if(userInput.length == 4){
						if(userInput[1] != null && userInput[2] != null && userInput[2].matches("[0-9.]+")  && userInput[3] != null && userInput[3].matches("[0-9.]+")){
					return true;
					}
			}
			break;
		case "updateBuy":
			if(userInput.length == 3){
				if(userInput[1] != null && userInput[2] != null && userInput[2].matches("[0-9]+")){
					return true;
				}
			}
			break;
		case "updateSell":
			if(userInput.length == 3){
				if(userInput[1] != null && userInput[2] != null && userInput[2].matches("[0-9]+")){
					return true;
				}
			}
			break;
		case "delete":
			if(userInput.length == 2){
				if(userInput[1] != null){
					return true;
				}
			}
			break;
		case "updateSellPrice":
			if(userInput.length == 3){
				if(userInput[1] != null && userInput[2] != null && userInput[2].matches("[0-9.]+")){
					return true;
				}
			}
			break;
		}
		return false;
	}
}
