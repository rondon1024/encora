@ParaBank
Feature: Registro de formulario

  @Login
  Scenario Outline: caso de prueba creacion de cuenta 
    Given ingreso al sistema parabank
    When ingreso datos personales al formulario "<FirstName>","<LastName>","<Address>","<City>","<State>","<ZipCode>","<Phone>","<SSN>"
    And ingreso datos cuenta al formulario  "<Username>","<Password>","<Confirm>"
    And clic en el boton registrar
    Then Valida que se despliega el mensaje de éxito	
    When ingreso la session con la cuenta creada "<Username>","<Password>"
    
    Examples:
  	| FirstName  | LastName  | Address    | City | State      | ZipCode  | Phone     	 | SSN 		| Username  		| Password     |   Confirm   |
  	| Carlos     | Rondon    |  Mz G lote | lima | los olivos | 0001     | 934507835	 |  025		| probando1223    | probando     | probando    |
  	
  	 
  	

  	
  	
  	
  	
  	
  	
  	
  	
  	
  
