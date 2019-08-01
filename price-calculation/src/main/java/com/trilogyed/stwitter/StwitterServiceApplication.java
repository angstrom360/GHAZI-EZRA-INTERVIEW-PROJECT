/*
					#############################################################
					# ---------- SUMMATIVE ASSESSMENT UNIT 2 MODULE 2 --------- #
					#   				STWITTER SYSTEM PROJECT					#
					#      			 NAME: GHAZI MUHAMMAD						#
					#      			 DUE DATE: 07/19/2019                       #
					#############################################################

 */


package com.trilogyed.stwitter;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients
@EnableCaching
public class StwitterServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StwitterServiceApplication.class, args);
	}

}
