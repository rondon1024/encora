package com.prueba.runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)

@CucumberOptions(features = { "src/test/resources/features" },tags = { "@login" },glue = { "com.prueba" })
public class Runner {
	
	
}