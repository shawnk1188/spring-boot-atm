# spring-boot-atm
 
Assessment:

ATM

This is a spring boot application simulating ATM machine's logic.

Pre-Requisites:
    1. User Has Already Inserted his debit card into the machine.
    2. Always absolute value is taken into consideration (ideal world user would not be having negative(-) option).

Technologies:
  maven
  java
  spring boot

  Containerization: 
     Docker, Docker-Compose


BUILD & RUN:

    Maven:
       JAR BUILD: 
            mvn clean build
       RUN APP AS REST API:
            mvn spring-boot:run --interfaceOption=rest
    Docker Compose:
        Running Rest Interface: 
            docker-compose --up build
    
    Local Running From Command Line: 
        Starting Application:
            1. mvn spring-boot:run
            2. From Main Class AutomaticTellerMachineApplication.java Run as java application.
        DashBoard Screen Entering PIN:

        Screen:

             Please Enter Your PIN:

            Invalid PIN: User has the Option to try pin for 4(Configurable) times

            Screen:
            ATM DashBoard
            **************************
            Please Enter Your PIN:
            123$
            Please Enter A Valid 4 Digit PIN:

        Select Account Screen:

        ***********************
        Select An Account:
        1.Savings
        2.Checking
        ***********************

        SuccessFul Account Selection:
        Please Select An Option From Below:


        ********************************
        1. View Balance
        2. Deposit Amount
        3. WithDraw Amount
        4. Exit
        ********************************

        Additional Option To Further Proceed Or Exit App:


        *******************
        If Would You Like to Make An Another Transaction Choose An Option Below:
        1. YES
        2. NO
        *******************

REST API Tests:
   Added postman collection to resource location. 
   Collection needs to be imported to postman to run the api's.

Improvements:
    1. Integrating with a data store.
