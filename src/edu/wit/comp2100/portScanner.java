package edu.wit.comp2100;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;

/*
 * TLDR: Main method runs calls checksHosts passing in subnet to check for the host on the network
 * checkHosts checks all IP connected then calls portisOpen to check is port 80 is open
 */

public class portScanner {

    private static final int BOOTSTRAPPERPORT = 1337;
    private static final String IP_BASIS = "192.168.1.";

    // Main (Nothing really to see here)
    public static ArrayList getBootstrapperIPs() throws IOException {

		//TODO fix hardcoded IP address (make it find IP address on its own)
        ArrayList<String> openAddresses = checkHosts(IP_BASIS);

        return openAddresses;

    }

	// Scans the network for connect IP Addresses
	private static ArrayList<String> checkHosts(String subnet) throws IOException {

		// ArrayList of Strings to store all the IP Address that have port 80 opened
		ArrayList<String> openIPAddress = new ArrayList<>();

		// Time out in (ms) 1000ms == 1sec
		int timeout = 500;

		for (int i = 1; i < 254; i++) {
			String host = subnet + i;

			// IF statement checks if host (IP Address) is connected
			if (InetAddress.getByName(host).isReachable(timeout)) {
				System.out.println(host + " is reachable");

				// These IF ELSE prints (Not action)
				boolean isitopen = portIsOpen(host);
				if (isitopen) {
					openIPAddress.add(host);
					System.out.println(host + " has port " + BOOTSTRAPPERPORT + " open");
					System.out.println("");
				} else {
					System.out.println(host + " does not have port " + BOOTSTRAPPERPORT + " open");
					System.out.println("");
				}
			}
		}

		return openIPAddress;
	}

	/*
	 * Takes the Connected IP Address and Checks if Port 80 is open on that IP
	 * Address
	 */
	private static boolean portIsOpen(String ip) {

		try {
			Socket socket = new Socket();
			socket.connect(new InetSocketAddress(ip, BOOTSTRAPPERPORT), 500);
			socket.close();
			return true;
		} catch (Exception ex) {
			return false;
		}
	}
}