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


public class WikipediaParserStreamTask implements StreamTask {
  @SuppressWarnings("unchecked")
  @Override
  public void process(IncomingMessageEnvelope envelope, MessageCollector collector, TaskCoordinator coordinator) {
    Map<String, Object> jsonObject = (Map<String, Object>) envelope.getMessage();
  
    
    try {
         	
    	String name=(String)jsonObject.get("name");
    	String Company=(String)jsonObject.get("Company");
    	String Target=(String)jsonObject.get("Target");
    	Map<String, Object> parsedJsonObject =new HashMap<String, Object>();
    	parsedJsonObject.put("name",name);
    	parsedJsonObject.put("Company",Company);
    	parsedJsonObject.put("Target",Target);

      collector.send(new OutgoingMessageEnvelope(new SystemStream("kafka", "samza-benchmark-output"), parsedJsonObject));
    } catch (Exception e) {
      System.err.println("Unable to parse line: " + jsonObject);
    }
  }
}
 