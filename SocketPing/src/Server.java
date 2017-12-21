import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	private static final int LISTEN_PORT = 9999;
	private static final String END_MARK = ".";
	
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
				socket = serverSocket.accept();
				bufferedReader = new BufferedReader( new InputStreamReader(socket.getInputStream()));
				printWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));
				
				str = "";
				while(!str.equals(END_MARK)) {
					str = bufferedReader.readLine();
					System.out.println(str);
					
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
