Feature: Test Amqp Endpoint Message

  Background:
    Given url baseUrl
     

  @name=amqp
  Scenario: Send a simple amqp message
    * def utils = call read('common.feature')
    Given path '/amqp/'
    And path 'Test'+utils.rnd()
    When method get
    Then status 200

  @name=openwire
  Scenario: Send a simple openwire message
    * def utils = call read('common.feature')
    Given path '/openwire/'
    And param message = 'Test'+utils.rnd()
    When method get
    Then status 200
 
  @name=openwireConsumer
  Scenario: Send a simple openwire message
    Given path '/openwire/read/last'
    When method get
    Then status 200
    * print response


  @name=amqpDeadLetter
  Scenario: Send a message and try to consume from a faulty consumer
    Given path '/amqp/hello'
    When method get
    Then status 200
    Given path '/amqp/broken/consumer'
    Then status 200
