import java.net.*;
import java.io.*;

class Client {

	public static void main (String args[])throws Exception {

		String toIP;
		String siteAddr;

		Socket clientSocket = new Socket("localhost", 3033);

		BufferedReader inputURL = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter the website whose IP address you'd like to find: ");
		toIP = inputURL.readLine();

		DataOutputStream toServer = new DataOutputStream(clientSocket.getOutputStream());
		toServer.writeBytes(toIP + '\n');

		BufferedReader fromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		siteAddr = fromServer.readLine();

		System.out.println("This websites IP address is: " + siteAddr);

		clientSocket.close();

	}
}