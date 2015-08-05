package org.master.LogMaster;

import org.master.LogMaster.consumer.LogConsumer;
import org.master.LogMaster.producer.LogProducer;

public class Main {
	
	public static void main(String[] args) {
		Thread c1 = new Thread(new LogConsumer(KafkaProperties.topic,"group1"), "c1");
		
		Thread c2 = new Thread(new LogConsumer(KafkaProperties.topic, "group2"), "c2");
		Thread c3 = new Thread(new LogConsumer(KafkaProperties.topic, "group3"), "c3");
		
		Thread p1 = new Thread(new LogProducer(KafkaProperties.topic), "p1");
		
		Thread p2 = new Thread(new LogProducer(KafkaProperties.topic), "p2");
		
		c1.start();
		c2.start();
		p1.start();
		p2.start();
		c3.start();
	}

}
