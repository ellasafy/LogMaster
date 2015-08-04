package org.master.LogMaster.producer;

import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

public class LogProducer extends Thread{
	 private final kafka.javaapi.producer.Producer<Integer, String> producer;
	  private final String topic;
	  private final Properties props = new Properties();

	  public LogProducer(String topic)
	  {
	    props.put("serializer.class", "kafka.serializer.StringEncoder");
	    props.put("metadata.broker.list", "10.11.29.30:9092");
	    // Use random partitioner. Don't need the key type. Just set it to Integer.
	    // The message is of type String.
	    producer = new kafka.javaapi.producer.Producer<Integer, String>(new ProducerConfig(props));
	    this.topic = topic;
	  }
	  
	  public void run() {
	    int messageNo = 1;
	    Random r = new Random();
	    while(messageNo<10)
	    {
	      String messageStr = new String(Thread.currentThread().getName() + " Message_" + messageNo);
	      producer.send(new KeyedMessage<Integer, String>(topic, messageStr));
	      messageNo++;
	      
	      try {
	    	  TimeUnit.SECONDS.sleep(r.nextInt(3));
	      } catch (Exception e) {
	    	  e.printStackTrace();
	      }
	    }
	  }
}
