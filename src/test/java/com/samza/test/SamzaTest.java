package com.samza.test;
import java.io.IOException;

import org.testng.annotations.Test;

import samza.examples.kafka.KafkaMessageConsumer;
import samza.examples.kafka.KafkaMessageProducer;
import samza.examples.kafka.ScriptRunner;


public class SamzaTest {
@Test
public void SamzaTaskTest()
{
	ScriptRunner.runScript();
}
@Test
public void kafkaProducerTest() 
{
	try {
		KafkaMessageProducer.produce();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
@Test
public void kafkaConsumerTest()
{
	try {
		KafkaMessageConsumer.consume();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
