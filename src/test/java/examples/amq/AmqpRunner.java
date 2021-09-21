package examples.amq;

import com.intuit.karate.junit5.Karate;

public class AmqpRunner {


	@Karate.Test
	Karate testAmqp() {
		return Karate.run("amqp").relativeTo(getClass());	
		}
}
