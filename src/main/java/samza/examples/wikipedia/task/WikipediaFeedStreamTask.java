/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package samza.examples.wikipedia.task;

import java.util.HashMap;
import java.util.Map;

import org.apache.samza.system.IncomingMessageEnvelope;
import org.apache.samza.system.OutgoingMessageEnvelope;
import org.apache.samza.system.SystemStream;
import org.apache.samza.task.MessageCollector;
import org.apache.samza.task.StreamTask;
import org.apache.samza.task.TaskCoordinator;
import org.apache.log4j.Logger;

/**
 * This task is very simple. All it does is take messages that it receives, and
 * sends them to a Kafka topic called wikipedia-raw.
 */
public class WikipediaFeedStreamTask implements StreamTask {
 // private static final SystemStream OUTPUT_STREAM = new SystemStream("kafka", "feed-test-output");
	final static Logger logger = Logger.getLogger(WikipediaFeedStreamTask.class);
  @SuppressWarnings("unchecked")
@Override
  public void process(IncomingMessageEnvelope envelope, MessageCollector collector, TaskCoordinator coordinator) {
	  Map<String, Object> jsonObject = (Map<String, Object>) envelope.getMessage();
	  try {
		  logger.info("p");
	    	/*String name=(String)jsonObject.get("name");
	    	String Company=(String)jsonObject.get("Company");
	    	String Target=(String)jsonObject.get("Target");
	    	String data=(String)jsonObject.get("data");
	    	String project=(String)jsonObject.get("project");
	    	String headq=(String)jsonObject.get("headq");
	    	//String parsedJsonObject =jsonObject;
	    	 logger.info("string msg:"+jsonObject.get("name"));
	    	 logger.info("output topic green-output");
	        Map<String, Object> parsedJsonObject =new HashMap<String, Object>();
	     	parsedJsonObject.put("name",name);
	     	parsedJsonObject.put("Company",Company);
	     	parsedJsonObject.put("Target",Target);
	     	parsedJsonObject.put("data",data);
	     	parsedJsonObject.put("project",project);
	     	parsedJsonObject.put("headq",headq);*/
	      collector.send(new OutgoingMessageEnvelope(new SystemStream("kafka", "red-output"), jsonObject));
	    } catch (Exception e) {
	      System.err.println("Unable to parse line: " + jsonObject);
	    }
  }
}
