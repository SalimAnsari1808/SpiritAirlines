package com.spirit.runner;

import com.spirit.utility.WaitUtil;
import org.testng.TestNG;
import java.util.ArrayList;
import java.util.List;

public class HeartBeatPROD {

    public  static  void main(String s[]){

        for(int heartBeatCounter=0;heartBeatCounter<100;heartBeatCounter++)
        {
            //create a list of String
            List<String> suites = new ArrayList<String>();

            //add current suite under test
            suites.add(System.getProperty("user.dir")+ "/src/test/java/com/spirit/runner/" + "HeartBeatSuite.xml"); //path of .xml file to be run-provide complete path

            //create testng object reference
            TestNG testng  = new TestNG();

            //add suite want to execute
            testng.setTestSuites(suites);

            //run test suite
            testng.run(); //run test suite

            //wait for 3 mins before next execution
            WaitUtil.untilTimeCompleted(3000);
        }
    }
}
