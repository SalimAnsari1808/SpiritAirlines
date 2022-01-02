package com.spirit.mobileMethods;

import io.appium.java_client.service.local.*;
import io.appium.java_client.service.local.flags.*;
import org.openqa.selenium.remote.*;

import java.io.*;
import java.net.*;

public class AppiumServer {
  private AppiumDriverLocalService service;
  private AppiumServiceBuilder builder;
  private DesiredCapabilities cap;


  public static void appiumServer() {
    AppiumServer appiumServer = new AppiumServer();


    int port = 4723;
    if(!appiumServer.checkIfServerIsRunning(port)) {
      appiumServer.startServer();
//      appiumServer.stopServer();
    } else {
      System.out.println("Appium Server already running on Port - " + port);
    }
  }



  public void startServer() {
    //Set Capabilities
    cap = new DesiredCapabilities();
    cap.setCapability("noReset", "false");

    //Build the Appium service
    builder = new AppiumServiceBuilder();
    builder.withIPAddress("127.0.0.1");
    builder.usingPort(4723);
    builder.withCapabilities(cap);
    builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
    builder.withArgument(GeneralServerFlag.LOG_LEVEL,"error");

    //Start the server with the builder
    service = AppiumDriverLocalService.buildService(builder);
    service.start();
    System.out.println("Appium Server has Started");
  }

  public void stopServer() {
    service.stop();
    System.out.println("Appium Server has been terminated");
  }

  public boolean checkIfServerIsRunning(int port) {

    boolean isServerRunning = false;
    ServerSocket serverSocket;
    try {
      serverSocket = new ServerSocket(port);
      serverSocket.close();
    } catch (IOException e) {
      //If control comes here, then it means that the port is in use
      isServerRunning = true;
    } finally {
      serverSocket = null;
    }
    return isServerRunning;
  }


}