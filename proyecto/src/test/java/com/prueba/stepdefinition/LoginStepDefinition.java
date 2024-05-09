package com.prueba.stepdefinition;

import com.ibm.icu.impl.Assert;
import com.prueba.page.HomePage;
import com.prueba.page.LoginPage;

import cucumber.api.java.en.*;

public class LoginStepDefinition {
	
	HomePage home=new HomePage();
	LoginPage Loginpage = new LoginPage();
	
	
	@Given("^ingreso al sistema parabank$")
	public void ingreso_al_sistema_parabank() {
		home.inicializar();
	}


	@When("^ingreso datos personales al formulario \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\"$")
	public void ingreso_datos_personales_al_formulario(String FirstName, String LastName, String Address, String City, String State, String ZipCode, String Phone, String SSN) throws InterruptedException {
		Loginpage.ingresoDatosPersonales(FirstName,LastName,Address,City,State,ZipCode,Phone,SSN);
	}

	@When("^ingreso datos cuenta al formulario  \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\"$")
	public void ingreso_datos_cuenta_al_formulario(String Username, String Password, String Confirm) throws InterruptedException {
		Loginpage.ingresoDatosCuenta(Username,Password,Confirm);
	}

	@When("^clic en el boton registrar$")
	public void clic_en_el_boton_registrar()throws InterruptedException {
		Loginpage.ClicBotonRegistrar();
	}

	@Then("^Valida que se despliega el mensaje de éxito$")
	public void valida_que_se_despliega_el_mensaje_de_éxito() {
	   
	}

	@When("^ingreso la session con la cuenta creada \"([^\"]*)\",\"([^\"]*)\"$")
	public void ingreso_la_session_con_la_cuenta_creada(String Username, String Password) throws InterruptedException  {
		Loginpage.ingresoSession(Username,Password);
	}



}
