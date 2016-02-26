package com.aximsoft.infiswift.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;

public class ScriptRunner {
    int iExitValue;
    String sCommandString;

    public void runScript(List<String> list){
    	try {
        for(int i=0;i<list.size();i++)
        {
    	sCommandString = list.get(i);
        CommandLine oCmdLine = CommandLine.parse(sCommandString);
        
        DefaultExecutor oDefaultExecutor = new DefaultExecutor();
        oDefaultExecutor.setExitValue(0);
        
            iExitValue = oDefaultExecutor.execute(oCmdLine);
          
        }
        } catch (ExecuteException e) {
            System.err.println("Execution failed.");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("permission denied.");
            e.printStackTrace();
        }
    }

    public static void runScript(){
    	
    	ScriptRunner scriptRunner = new ScriptRunner();
        List<String>list=new ArrayList<String>();
        //list.add("ls");
        //list.add("pwd");
        //list.add("cd Downloads/hello-samza");
        list.add("deploy/samza/bin/run-job.sh --config-factory=org.apache.samza.config.factories.PropertiesConfigFactory --config-path=file:///home/hadoop/Downloads/backup/hello-samza/deploy/samza/config/wikipedia-feed.properties");
       // testScript.runScript("sh /root/Desktop/testScript.sh");
        scriptRunner.runScript(list);
    }
}
