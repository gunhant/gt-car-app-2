package com.tatman.gtcarapp2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import com.tatman.gtcarapp2.domain.Car;
import com.tatman.gtcarapp2.domain.CarRepository;
import com.tatman.gtcarapp2.domain.Owner;
import com.tatman.gtcarapp2.domain.OwnerRepository;

@SpringBootApplication
public class GtCarApp2Application {

	@Autowired
	private CarRepository repository;

	@Autowired
	private OwnerRepository ownerRepository;

	public static void main(String[] args) {
		SpringApplication.run(GtCarApp2Application.class, args);
	}

	@Bean
	CommandLineRunner runner()
	{
		return args -> {

			Owner owner1 = new Owner("John", "Johnson");
			Owner owner2 = new Owner("Bob", "Carter");
			ownerRepository.save(owner1);
			ownerRepository.save(owner2);

			repository.save(new Car("Ford", "Mustang", "Red", "ADF-1121", 2017, 5900, owner1));
			repository.save(new Car("Nissan", "Leaf", "Blue", "ADF-2222", 2014, 3000, owner2));
			repository.save(new Car("Mazda", "300", "White", "ADF-3333", 2015, 6000, owner2));
		};
	}
}
