package examples.amq

import com.intuit.karate.gatling.PreDef._
import io.gatling.core.Predef._

import scala.concurrent.duration.DurationInt

class AmqpSimulation extends Simulation{
  
  val getTest = scenario("AMQP Scenario").exec(karateFeature("classpath:examples/amq/amqp.feature@name=amqp"))
  val openwireTest = scenario("Openwire Producer Failover Scenario").exec(karateFeature("classpath:examples/amq/amqp.feature@name=openwire"))
  val openwireConsumerTest = scenario("Openwire consumer Failover Scenario").exec(karateFeature("classpath:examples/amq/amqp.feature@name=openwireConsumer"))
//  val messageMigrationTest = scenario("Message Migration test").exec(karateFeature("classpath:examples/amq/amqp.feature@name=messageMigration"))
  val deadLetterTest = scenario("Dead Letter scenario").exec(karateFeature("classpath:examples/amq/amqp.feature@name=amqpDeadLetter"))

  val protocol = karateProtocol(
    "/" -> pauseFor("get" -> 2)
  )

  setUp(
   // getTest.inject(rampUsers(10) during (5 seconds)).protocols(protocol),
    //deadLetterTest.inject(constantConcurrentUsers(1).during(1.seconds)),
    openwireTest.inject(constantConcurrentUsers(1).during(1.minute))
     //.andThen(
      //  openwireConsumerTest.inject(rampUsers(20) during (10 seconds)).protocols(protocol)
      //)
  //  messageMigrationTest.inject(rampUsers(10) during (60 seconds)).protocols(protocol)

  )
  
}
