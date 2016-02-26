package com.aximsoft.infiswift.test;
import java.io.IOException;

import org.testng.annotations.Test;

import com.aximsoft.infiswift.util.KafkaMessageConsumer;
import com.aximsoft.infiswift.util.KafkaMessageProducer;
import com.aximsoft.infiswift.util.ScriptRunner;


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
