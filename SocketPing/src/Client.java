import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import request_model.RequestModel;

public class Client {
	private static final String END_MARK = ".";
	private static Boolean      isEnd     = false;

	@SuppressWarnings("unchecked")
	public static void main(String args[]) {
		Socket socket = null;
		BufferedReader bufferdReader;
		ObjectInputStream objectInputStream = null;
		ObjectOutputStream objectOutputStream = null;
		String receiveString;
		String id = "2222";
		String password = "bigfoot";
		Scanner scanner = new Scanner(System.in);
		System.out.print("debug:mainです");

		try {
			System.out.println("debug:tryです");
			socket 					= new Socket("localhost",9999);
			bufferdReader 			= new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			
			
			while(!isEnd) {
				System.out.println("debug:trueです");
				
				System.out.println("###################################");
				System.out.println("############### MENU ##############");
				System.out.println("###################################");
				System.out.println("# 1 : レンタル商品一覧取得");
				System.out.println("# 2 : 決済一覧取得");
				System.out.println("# 3 : レンタルするよ(決済)");
				System.out.println("# 4 : 返却");
				System.out.println("# 0 : 終了");
				System.out.println("###################################");
				System.out.print("===>");

				Integer menu_num = scanner.nextInt();

				RequestModel requestModel = new RequestModel(id,password);

				switch(menu_num) {
					case 1: 
						requestModel.method = "GET";
						requestModel.scope  = "product";
						break;		
					case 2: 
						requestModel.method = "GET";
						requestModel.scope  = "settlement";
						break;		
					case 3: 
						requestModel.method = "ADD";
						requestModel.scope  = "settlement";
						break;	
					case 4: 
						requestModel.method = "DELETE";
						requestModel.scope = "rental";
						break;
					case 0:
						isEnd = true;
						bufferdReader.close();
						socket.close();
						objectOutputStream.flush();
						objectInputStream.close();
						objectOutputStream.close();
						System.exit(0);
				}
				objectInputStream   	= new ObjectInputStream(socket.getInputStream());
				System.out.println("debug:in is accepted");
				objectOutputStream 		= new ObjectOutputStream(socket.getOutputStream());
				System.out.println("debug:out is accepted");
				
				//そうしん
				objectOutputStream.writeObject(requestModel);
				//じゅしん
				receiveString = bufferdReader.readLine();
				
				if(receiveString.equals("product")){
					ArrayList<dto.Product> resProduct = (ArrayList<dto.Product>) objectInputStream.readObject();
					for(dto.Product d : resProduct) {
	    				System.out.println("ISBN :\t" + d.getId());
	    				System.out.println("BOOKNAME :\t" + d.getName());
	    				System.out.println("WRITER :\t" + d.getStock());
	    				System.out.println("PUBLISHER :\t" + d.getPrice());
					}
				}else if(receiveString.equals("settlement")) {
					ArrayList<dto.Settlement> resSettlement = (ArrayList<dto.Settlement>) objectInputStream.readObject();
					for(dto.Settlement d : resSettlement) {
	    				System.out.println("ISBN :\t" + d.getId());
	    				System.out.println("BOOKNAME :\t" + d.getUserId());
	    				System.out.println("WRITER :\t" + d.getPrice());
	    				System.out.println("PUBLISHER :\t" + d.getBeReturned());
					}
				}
				
			}

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
