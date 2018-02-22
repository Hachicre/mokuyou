import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import request_model.RequestModel;

public class Server {
	
	private static final int LISTEN_PORT = 9999;
	private static final String END_MARK = ".";
	static dao.Product daoProduct = new dao.Product();
	static dao.Settlement daoSettlement = new dao.Settlement();
	
	public static void main(String args[]) {
		
		ServerSocket serverSocket = null;
		Socket socket = null;
		BufferedReader bufferedReaderStdIn;
		String str;
		
		
		
		try {
			serverSocket = new ServerSocket(LISTEN_PORT);
			
			while(true) {
				System.out.println("新しい接続を待っています。");
				socket 								= serverSocket.accept();
				System.out.println("debug:Connection is accepted");
				bufferedReaderStdIn = new BufferedReader(new InputStreamReader(System.in));
				ObjectInputStream  inputStream	= new ObjectInputStream(socket.getInputStream());
				System.out.println("debug:Input is ready");
				ObjectOutputStream outputStream 	= new ObjectOutputStream(socket.getOutputStream());
				
				RequestModel res = (RequestModel)inputStream.readObject();

				
				if(res.method.equals("get")) {
					if(res.method.equals("product")) {
						ArrayList<dto.Product> product;
						product    = daoProduct.findAll();
						outputStream.writeObject(product);
						outputStream.flush();
						str = "product";
					}else if(res.method.equals("settlement")) {
						ArrayList<dto.Settlement> settlement;
						settlement = daoSettlement.findAll();
						outputStream.writeObject(settlement);
						outputStream.flush();
						str = "settlement";
					}
				}
				str = bufferedReaderStdIn.readLine();
				socket.close();
				inputStream.close();
				outputStream.close();
			}
			
		}catch(IOException | ClassNotFoundException e) {
			System.out.println("エラーが発生しました。");
			e.printStackTrace();
		}finally {
			try {
				if(serverSocket != null) {
					serverSocket.close();
				}
				if(socket != null) {
					socket.close();
				}
			}catch(IOException e) {
				System.out.println("エラーが発生しました。");
				e.printStackTrace();
			}
		}
		
	}
	

}
