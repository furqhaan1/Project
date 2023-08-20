package com.furru;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
//edit
@SpringBootApplication
@PropertySource(value = { "classpath:messages.properties" })
public class HolidayBooking {

	public static void main(String[] args) {
		SpringApplication.run(HolidayBooking.class, args);
	}

}
