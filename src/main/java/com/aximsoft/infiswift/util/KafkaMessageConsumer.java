package com.aximsoft.infiswift.util;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.io.*;


/**
* Simple Consumer
*/
public class KafkaMessageConsumer {
// public static void main(String[] args) {
public static void consume() throws IOException{
Properties config = new Properties();
File file=new File("src/main/resources/input.txt");
file.createNewFile();
//FileOutputStream op=new FileOutputStream(file);
FileWriter fw = new FileWriter(file);
BufferedWriter bw = new BufferedWriter(fw);
config.put("zookeeper.connect", "localhost:2181");
config.put("group.id", "default");
config.put("partition.assignment.strategy", "roundrobin");
config.put("bootstrap.servers", "localhost:9092");
config.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
config.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
kafka.consumer.ConsumerConfig consumerConfig = new kafka.consumer.ConsumerConfig(config);

ConsumerConnector consumerConnector = Consumer.createJavaConsumerConnector(consumerConfig);

Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
topicCountMap.put("orange-output", 1);

Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumerConnector.createMessageStreams(topicCountMap);

List<KafkaStream<byte[], byte[]>> streamList = consumerMap.get("orange-output");

KafkaStream<byte[], byte[]> stream = streamList.get(0);

ConsumerIterator<byte[], byte[]> iterator = stream.iterator();
while(iterator.hasNext()) {
//op.write(new String(iterator.next().message()).getBytes(), 0, new String(iterator.next().message()).length());
//bw.write(new String(iterator.next().message()));
System.out.println("message consumed: "+new String(iterator.next().message()));
//bw.close();

}

}

/* public static void processRecords(Map<String, ConsumerRecords<String, String>> records) {
List<ConsumerRecord<String, String>> messages = records.get("infiswift")
if(messages != null) {
for (ConsumerRecord<String, String> next : messages) {
try {
System.out.println(next.value());
} catch (Exception e) {
e.printStackTrace();
}
}
} else {
System.out.println("No messages");
}
}*/
}

