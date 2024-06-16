package com.programmingtechie.inventoryservice;

import com.programmingtechie.inventoryservice.model.Inventory;
import com.programmingtechie.inventoryservice.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import java.util.Collections;

@SpringBootApplication
@EnableEurekaClient
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(InventoryRepository inventoryRepository) {
		return args -> {
			if (inventoryRepository.findBySkuCodeIn(Collections.singletonList("iphone_13")) == null) {
				Inventory inventory = new Inventory();
				inventory.setSkuCode("iphone_13");
				inventory.setQuantity(100);
				inventoryRepository.save(inventory);
			}
			if (inventoryRepository.findBySkuCodeIn(Collections.singletonList("iphone_13_red")) == null) {
				Inventory inventory1 = new Inventory();
				inventory1.setSkuCode("iphone_13_red");
				inventory1.setQuantity(0);
				inventoryRepository.save(inventory1);
			}
		};
	}
}
