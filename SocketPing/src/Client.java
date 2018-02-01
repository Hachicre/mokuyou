import java.io.BufferedReader;

import java.util.Scanner;

import request_model.RequestModel;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class Client {
	private static final String END_MARK = ".";
	private Boolean             isEnd    = false;
	
	@SuppressWarnings("unchecked")
	public static void main(String args[]) {
		Socket socket = null;
		BufferedReader bufferdReader, bufferedReaderStdIn;
		PrintWriter printWriter;
		String sendString,receiveString;
		Scanner scanner = new Scanner();
		
		try {
			socket 							= new Socket("localhost",9999);
			bufferdReader 					= new BufferedReader(new InputStreamReader(socket.getInputStream()));
			printWriter 						= new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));
			bufferedReaderStdIn 				= new BufferedReader(new InputStreamReader(System.in));
			ObjectInputStream objectInputStream   = new ObjectInputStream(socket.getInputStream());
			ObjectOutputStream objectOutputStream = new ObjectInputStream(socket.getOutputStream());
			
			sendString = "";
			
			// ID取得
			System.out.println("IDいれて");
			String id = scanner.readLine();
			System.out.println("passwordもいれて");
			String password = scanner.readLine();

			// authentication instance 作成
			Authentication authentication = new Authentication(id, password);
			
			while(!isEnd) {
				System.out.println(
					"###################################"
				  + "############### MENU ##############"
				  + "###################################"
				  + "# 1 : レンタル商品一覧取得"
				  + "# 2 : 決済一覧取得"
				  + "# 3 : レンタルするよ(決済)"
				  + "# 4 : 返却"
				  + "###################################"
				);
				
				Integer menu_num = null;
			
				while (menu_num == null) {
					try {
						menu_num = Integer.parseInt(scanner.nextInt());
					} catch(e) {
						System.out.println("数値を入力してください");
					}
				}
				
				RequestModel requestModel = new RequestModel();
				requestModel.authentication = authentication;
				
				switch(menu_num) {
					case 1: {
						requestModel.method = "GET";
						requestModel.scope  = "product";
					}
					case 2: {
						requestModel.method = "GET";
						requestModel.scope  = "settlement";
					}
					case 3: {
						requestModel.method = "ADD";
						requestModel.scope  = "settlement";
						SettlementContent settlementContent;
						// 下はダミーです
						settlementContent.productIds = [1, 2, 3];
						requestModel.settlementContent = settlementContent;
					}
				}
	
				objectOutputStream.writeObject(requestModel);
				
				RequestModel response = (RequestModel) inputStream.readObject();
				
				
				receiveString = bufferdReader.readLine();
				System.out.println("Server:" +receiveString);
				
			}
			bufferedReaderStdIn.close();
			bufferdReader.close();
			printWriter.close();
			socket.close();
			
		} catch(IOException | ClassNotFoundException e) {
			System.out.println("エラーが発生しました");
			e.printStackTrace();
		} finally {
			try {
				if(socket != null)socket.close();
			} catch(IOException e) {
				System.out.println("エラーが発生しました");
				e.printStackTrace();
			}
		}
	}

}
