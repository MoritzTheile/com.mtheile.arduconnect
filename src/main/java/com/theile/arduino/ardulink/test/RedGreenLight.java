package com.theile.arduino.ardulink.test;

import org.ardulink.core.Link;
import org.ardulink.core.Pin;
import org.ardulink.core.Pin.DigitalPin;
import org.ardulink.core.linkmanager.LinkManager;
import org.ardulink.util.URIs;

public class RedGreenLight {

	public static void main(String[] args) throws Exception {
		
		// final Link link = LinkManager.getInstance().getConfigurer(URIs.newURI("ardulink://serial/?port=COM3&pingprobe=false")).newLink();
		final Link link = LinkManager.getInstance().getConfigurer(URIs.newURI("ardulink://serial-jssc/?port=COM6&pingprobe=false")).newLink();
		
		//System.setProperty("java.library.path", ""C:\\dev\\git\\com.mtheile.arduconnect\\lib"");
		
		DigitalPin pin3 = Pin.digitalPin(3);
		DigitalPin pin2 = Pin.digitalPin(2);

		boolean switchIsOn = false;

		while(true) {
			
			switchIsOn = !switchIsOn;
			link.switchDigitalPin(pin3, switchIsOn);
			link.switchDigitalPin(pin2, !switchIsOn);
			Thread.sleep(3000);
			
		}
		
		
		
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
