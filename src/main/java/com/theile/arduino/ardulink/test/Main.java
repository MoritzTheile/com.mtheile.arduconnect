package com.theile.arduino.ardulink.test;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.ardulink.core.Link;
import org.ardulink.core.Pin;
import org.ardulink.core.Pin.DigitalPin;
import org.ardulink.core.linkmanager.LinkManager;
import org.ardulink.util.URIs;

public class Main {

	private static Scanner scanner;

	public static void main(String[] args) throws Exception {

		// from: http://anshulkatta.blogspot.com/2017/01/java-program-on-arduino-over-serial.html
		//Link link = Links.getLink(URIs.newURI("ardulink://default"));
		Link link = LinkManager.getInstance().getConfigurer(URIs.newURI("ardulink://serial?port=COM3&pingprobe=false")).newLink();
		DigitalPin pin = Pin.digitalPin(13);

		boolean power = true;
		while (true) {
			String data = getInput();
			if(data.equalsIgnoreCase("on")) {
				power = true;
				link.switchDigitalPin(pin, power);


				TimeUnit.SECONDS.sleep(2);
			}else if(data.equalsIgnoreCase("off")){
				power = false;
				link.switchDigitalPin(pin, power);
				TimeUnit.SECONDS.sleep(2);
			}

		}
	}

	private static String getInput() {
		scanner = new Scanner(System.in);
		String data = scanner.next();
		// scanner.close();
		return data;
	}

}


//try {
//			Link link = LinkManager.getInstance().getConfigurer(URIs.newURI("ardulink://serial?port=COM3&pingprobe=false")).newLink();
//			 
//			  List<String> portList = link.getPortList(); // 2
//			  if(portList != null && portList.size() > 0) {
//			    String port = portList.get(0);
//			    System.out.println("Connecting on port: " + port);
//			    boolean connected = link.connect(port); // 3
//			    System.out.println("Connected:" + connected);
//			    Thread.sleep(2000); // 4
//			    int power = IProtocol.HIGH;
//			    while(true) {
//			      System.out.println("Send power:" + power);
//			      link.sendPowerPinSwitch(2, power); // 5
//			      if(power == IProtocol.HIGH) {
//			        power = IProtocol.LOW;
//			      } else {
//			        power = IProtocol.HIGH;
//			      }
//			      Thread.sleep(2000);
//			    }
//			  } else {
//			    System.out.println("No port found!");
//			  }
//
//			} catch(Exception e) {
//			  e.printStackTrace();
//			}
//	}
