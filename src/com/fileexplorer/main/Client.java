package com.fileexplorer.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

	public static void main(String args[]) throws Exception {

		Socket socket = new Socket("localhost", 8085);
		System.out.println("Connection has been Established");
		BufferedReader inputVar = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintStream outVar = new PrintStream(socket.getOutputStream());
		BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
		String str;
		String choice;
		String Drive;
		int drcount;
		while (true) {

			str = inputVar.readLine();
			// Get print Statement
			System.out.println("Server :" + str);
			// Get the fileName
			System.out.print("Client : ");
			str = stdin.readLine();
			if (str.equalsIgnoreCase("exit")) {
				System.out.println("Connection Terminated.....");
				break;
			}
			outVar.println(str);
			str = inputVar.readLine();
			// Get Server Statement for Drive Type
			System.out.println("Server :" + str);
			// Get Drive Type
			System.out.print("Client : ");
			str = stdin.readLine();
			outVar.println(str);
			str = inputVar.readLine();
			// Print Drive Selection
			System.out.println("Server :" + str);

			str = inputVar.readLine();
			System.out.println("Server:" + str);
			System.out.print("Client :");

			str = stdin.readLine();
			outVar.println(str);
			drcount = Integer.parseInt(str);
			for (int i = 0; i < drcount; i++) {
				str = inputVar.readLine();
				System.out.println("Server :" + str);
				System.out.print("Client :");
				choice = stdin.readLine();
				outVar.println(choice);
			}
//			str=inputVar.readLine();
//			System.out.println("Server :"+str);
//			System.out.print("Client :");
//			choice=stdin.readLine();
//			outVar.println(choice);
			str = inputVar.readLine();
			System.out.println("Server :" + str);
//			System.out.print("Client:");
//			str=stdin.readLine();
//			outVar.println(str);
//			str = inputVar.readLine();
//			System.out.print("Server : " + str + "\n");

		}

		socket.close();
		inputVar.close();
		outVar.close();
		stdin.close();
	}

}