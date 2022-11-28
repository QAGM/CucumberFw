package com.amazon.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import com.amazon.pages.WebHomePage;

public class WebsiteHomeSteps{
	WebHomePage home ;
	@Given("User open up the website")
	public void user_open_up_the_websites() {
	   	home = new WebHomePage();
	}

	@When("user clicks on Contact Us link")
	public void user_clicks_on_contact_us_link() {
		//home.verifyCartEmpty();
	}

	@Then("Contact Us page opens")
	public void contact_us_page_opens() {
		home.verifyCartEmpty();
	}
}
