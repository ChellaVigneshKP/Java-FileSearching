package com.fileexplorer.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.fileexplorer.drives.DriveFinderFactory;
import com.fileexplorer.drives.SystemDriverFinder;
import com.fileexplorer.search.*;

public class Server {

	int port;
	ServerSocket ss = null;
	Socket socket = null;
	ExecutorService es = null;
	int clientcount = 0;
	static List<String> drives = null;

	public static void main(String[] args) throws IOException {
		Server sObject = new Server(8085);
		sObject.startServer();

	}

	Server(int port) {
		this.port = port;
		es = Executors.newFixedThreadPool(5);

	}

	public void startServer() throws IOException {

		ss = new ServerSocket(port);
		System.out.println("Server is ON....");
		System.out.println("To quit connection type exit....");

		while (true) {

			socket = ss.accept();
			clientcount++;
			ServerThread st = new ServerThread(socket, clientcount, this);
			es.execute(st);
		}

	}

	private static class ServerThread implements Runnable {
		Server server = null;
		Socket client = null;
		BufferedReader in;
		PrintStream out;
		Scanner sc = new Scanner(System.in);
		int id;
		String input;
		String[] Drive1 = new String[10];
		String Choice;
		String drcount;
		int drcounti;
		private String outputString = "";

		ServerThread(Socket client, int count, Server server) throws IOException {

			this.client = client;
			this.server = server;
			this.id = count;
			System.out.println("Connection has been established with client " + id);

			in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			out = new PrintStream(client.getOutputStream());

		}

		@Override
		public void run() {
			int x = 1;
			try {
				while (true) {
					outputString = "Enter the File Name";
					out.println(outputString);
					input = in.readLine();
					System.out.print("Client(" + id + ") : " + input + "\n");

					if (input.equalsIgnoreCase("exit")) {
						out.println("BYE");
						x = 0;
						System.out.println("Connection Terminated....");
						break;
					} else if ((input.length() >= 6) && ((input.charAt(input.length() - 4) == '.')
							|| (input.charAt(input.length() - 5) == '.'))) {

						System.out.println("Client requested for searching the file " + input);
						outputString = "Choose the State of Drive:Active(or)ALL";
						out.println(outputString);
						Choice = in.readLine();
						drives = DriveFinderFactory.create(Choice).findDrives();
						outputString = "Drives present are" + drives;
						out.println(outputString);

						outputString = "Enter the Number of Drives to be Searched";
						out.println(outputString);
						drcount = in.readLine();
						drcounti = Integer.parseInt(drcount);
						System.out.println(drcounti);
						for (int i = 0; i < drcounti; i++) {
							outputString = "Choose the Drive" + drives;
							out.println(outputString);
							Drive1[i] = in.readLine();
							System.out.println("Drive Selction is:" + Drive1[i]);
						}

//						outputString="Choose the Drive"+drives;
//						out.println(outputString);
//						Drive1=in.readLine();
//						System.out.println("Drive Selction is:"+Drive1);
						SearchEngineMain se = new SearchEngineMain();
						List<String> filepaths = se.mainq(input, Drive1, drcounti, Choice);
						if (filepaths.isEmpty()) {
							outputString = "No Files Found";
							System.out.println(outputString);
							out.println("No Files Found");
							out.flush();
						} else {
							outputString = "Location: " + filepaths;
							System.out.println(outputString);
							out.println(outputString);
							out.flush();
						}
						// outputString = "";

					} else {
						System.out.print("Server: ");
						String output = sc.nextLine();
						out.println(output);
						out.flush();
					}
				}

				in.close();
				client.close();
				out.close();
				if (x == 0) {
					System.out.println("*****Terminating*****");
					System.exit(0);
				}
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}
}