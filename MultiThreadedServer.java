import java.net.*;
import java.io.*;

public class MultiThreadedServer implements Runnable {

	Socket newSock;

	MultiThreadedServer(Socket connectionSocket) {
		this.newSock = connectionSocket;
	}

	public void run() {

		try {

			String toURL;

			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(newSock.getInputStream()));
			toURL = inFromClient.readLine();

			InetAddress IPAddress = InetAddress.getByName(toURL);

			String siteIP = IPAddress.getHostAddress();
			String siteName = IPAddress.getHostName();

			System.out.println("Site name is: " + siteName + " and it's IP Address is: " + siteIP);

			DataOutputStream outToClient = new DataOutputStream(newSock.getOutputStream());
			outToClient.writeBytes(siteIP);

			System.out.println("Sent");

			newSock.close();
		}
			catch (Exception e) {
				System.out.println("Errror as: " + e );
			}
	}

	public static void main(String args[])throws Exception {

		ServerSocket welcomeSocket = new ServerSocket(3033);

		System.out.println("Listening...");

		while (true) {

			Socket newSock = welcomeSocket.accept();

			System.out.println("Connected...");

			new Thread(new MultiThreadedServer(newSock)).start();

		}
	}
}