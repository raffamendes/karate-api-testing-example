Feature: Test Amqp Endpoint Message

  Background:
    Given url baseUrl
     

  @name=amqp
  Scenario: Send a simple amqp message
    * path '/amqp/hello'
    When method get
    Then status 200
