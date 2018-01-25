import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
	
	private static final int LISTEN_PORT = 9999;
	private static final String END_MARK = ".";
	static dao.Product dao = new dao.Product();
	
	public static void main(String args[]) {
		
		ServerSocket serverSocket = null;
		Socket socket = null;
		BufferedReader bufferedReader;
		PrintWriter printWriter;
		String str;
		
		
		
		try {
			serverSocket = new ServerSocket(LISTEN_PORT);
			
			while(true) {
				System.out.println("新しい接続を待っています。");
				socket 								= serverSocket.accept();
				bufferedReader 						= new BufferedReader( new InputStreamReader(socket.getInputStream()));
				printWriter						  	= new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));
				ObjectInputStream  inputStream	= new ObjectInputStream(socket.getInputStream());
				ObjectOutputStream outputStream 	= new ObjectOutputStream(socket.getOutputStream());
				
				ArrayList<dto.Product> list;
				
				str = "";
				while(!str.equals(END_MARK)) {
					list = dao.findAll();
					str = bufferedReader.readLine();
					printWriter.println(str);
					
					String resultMessage = "";
					
	            	
	            	outputStream.writeObject(list);
            		//printWriter.println(resultMessage);
					//printWriter.flush();

					printWriter.println("メッセージを受信しました");
					printWriter.flush();
				}
				
				bufferedReader.close();
				printWriter.close();
				socket.close();
			}
		}catch(IOException e) {
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
