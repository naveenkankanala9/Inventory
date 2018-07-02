package com.store.inventorymanagement;
import java.math.BigDecimal;
import java.util.Scanner;

import com.store.inventorymanagement.controller.StoreController;
import com.store.inventorymanagement.util.InputValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(exclude = {WebMvcAutoConfiguration.class})
public class Application implements ApplicationRunner{

	@Autowired
	private StoreController storeController;

	@Autowired
	private StoreInitializr storeInitializr;

	@Autowired
	private InputValidator inputValidator;

	public static void main(String[] args) {
		ConfigurableApplicationContext context= SpringApplication.run(Application.class, args);
		//Initilizing store using StoreInitializr class
		context.getBean(StoreInitializr.class).initilizeStore();
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		storeInitializr.initilizeStore();
		Scanner input = new Scanner(System.in);
		System.out.println("Inventory Management");
		String key = input.nextLine();
		String userInput[] = key.split(" ");
		while(!input.equals("exit")){
			switch (userInput[0]) {
				case "create":
					if(inputValidator.validateInput(userInput)){
						storeController.addItems(userInput[1], new BigDecimal(userInput[2]), new BigDecimal(userInput[3]));
					}
					break;
				case "updateBuy":
					if(inputValidator.validateInput(userInput)){
						storeController.updateItemQuantity(userInput[1], Integer.parseInt(userInput[2]));
					}
					break;
				case "updateSell":
					if(inputValidator.validateInput(userInput)){
						storeController.updateSell(userInput[1], Integer.parseInt(userInput[2]));
					}
					break;
				case "delete":
					if(inputValidator.validateInput(userInput)){
						storeController.deleteItem(userInput[1]);
					}
					break;
				case "report":
					storeController.generateReport();
					break;
				case "updateSellPrice":
					if(inputValidator.validateInput(userInput)){
						storeController.updateSellingPrice(userInput[1], new BigDecimal(userInput[2]));
					}
					break;
			}
			key = input.nextLine();
			userInput = key.split(" ");
		}

		input.close();
		System.exit(200);
	}

}

