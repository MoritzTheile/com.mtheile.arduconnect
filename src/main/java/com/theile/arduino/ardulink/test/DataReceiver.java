/**
Copyright 2013 project Ardulink http://www.ardulink.org/

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

 */

package com.theile.arduino.ardulink.test;

import static java.lang.String.format;

import org.ardulink.core.Connection;
import org.ardulink.core.ConnectionBasedLink;
import org.ardulink.core.Link;
import org.ardulink.core.Pin.AnalogPin;
import org.ardulink.core.Pin.DigitalPin;
import org.ardulink.core.events.AnalogPinValueChangedEvent;
import org.ardulink.core.events.DigitalPinValueChangedEvent;
import org.ardulink.core.events.EventListener;
import org.ardulink.core.linkmanager.LinkManager;
import org.ardulink.util.URIs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * [ardulinktitle] [ardulinkversion]
 * 
 * project Ardulink http://www.ardulink.org/
 * 
 * [adsense]
 *
 */
public class DataReceiver {


	private String connection = "ardulink://serial?port=COM3&pingprobe=false";

	private int[] digitals = new int[] { 2 };

	private int[] analogs = new int[] { 5 };

	private String msgAnalog = "PIN state changed. Analog PIN: %s Value: %s";

	private String msgDigital = "PIN state changed. Digital PIN: %s Value: %s";

	private Link link;

	private static final Logger logger = LoggerFactory
			.getLogger(DataReceiver.class);

	public static void main(String[] args) throws Exception {
		new DataReceiver().work();
	}

	

	private void work() throws Exception {
		this.link = LinkManager.getInstance().getConfigurer(URIs.newURI(connection)).newLink();
		link.addListener(eventListener());

		for (int analog : analogs) {
			link.startListening(AnalogPin.analogPin(analog));
		}
		for (int digital : digitals) {
			link.startListening(DigitalPin.digitalPin(digital));
		}

		if (link instanceof ConnectionBasedLink) {
			Connection connection = ((ConnectionBasedLink) link)
					.getConnection();
			connection.addListener(rawDataListener());
		}
	}

	private EventListener eventListener() {
		return new EventListener() {
		
			
			public void stateChanged(AnalogPinValueChangedEvent event) {
				System.out.println(format(msgAnalog, event.getPin(), event.getValue()));
				
			}

			public void stateChanged(DigitalPinValueChangedEvent event) {
				System.out.println(format(msgDigital, event.getPin(), event.getValue()));
				
			}
			
		};
	}

	private Connection.Listener rawDataListener() {
		return new Connection.ListenerAdapter() {
			@Override
			public void received(byte[] bytes) {
				logger.info("Message from Arduino: %s", new String(bytes));
			}
		};
	}

}
