package examples.amq

import com.intuit.karate.gatling.PreDef._
import io.gatling.core.Predef.{Simulation, openInjectionProfileFactory, rampUsers, scenario}

import scala.concurrent.duration.DurationInt

class AmqpSimulation extends Simulation{
  
  val getTest = scenario("get the feature").exec(karateFeature("classpath:examples/amq/amqp.feature@name=amqp"))

  val protocol = karateProtocol(
    "/" -> pauseFor("get" -> 2)
  )

  setUp(
    getTest.inject(rampUsers(10) during (5 seconds)).protocols(protocol)

  )
  
}
