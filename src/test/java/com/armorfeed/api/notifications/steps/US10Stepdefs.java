package com.armorfeed.api.notifications.steps;

import com.armorfeed.api.notifications.resources.response.CreateNotificationRequest;
import com.armorfeed.api.notifications.resources.response.NotificationResponse;
import io.cucumber.java.en.And;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;


@CucumberContextConfiguration
public class US10Stepdefs {

    @Autowired
    protected TestRestTemplate testRestTemplate;

    private CreateNotificationRequest request;

    boolean RegisterButtonhasbeenPressed=false;

    @Given("el usuario se encuentre en la sección de envíos")
    public void userRegistryShipmentSuccesfully(){}

    @When("realice un nuevo envío llenando de manera correcta los datos")
    public void userFillOutRegisterShipment(){}

    @And("presione el botón de Registrar")
    public void userPressRegisterShipmentButton(){ RegisterButtonhasbeenPressed=true;}

    @Then("se creará una notificación que indique que hay un nuevo envío")
    public void userRegisterNotification() { request= new CreateNotificationRequest(); }




}
