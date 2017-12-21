import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
	private static final String END_MARK = ".";
	public static void main(String args[]) {
		Socket socket = null;
		BufferedReader bufferdReader, bufferedReaderStdIn;
		PrintWriter printWriter;
		String sendString,receiveString;
		
		try {
			socket = new Socket("localhost",9999);
			bufferdReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			printWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));
			bufferedReaderStdIn = new BufferedReader(new InputStreamReader(System.in));
			sendString = "";
			while(! sendString.equals(END_MARK)) {
				System.out.println("送信メッセージを入力してください:");
				sendString = bufferedReaderStdIn.readLine();
				//送信
				printWriter.println(sendString);
				printWriter.flush();
				
				receiveString = bufferdReader.readLine();
				System.out.println("Server:" +receiveString);
			}
			bufferedReaderStdIn.close();
			bufferdReader.close();
			printWriter.close();
			socket.close();
			
		}catch(IOException e) {
			System.out.println("エラーが発生しました");
			e.printStackTrace();
		}finally {
			try {
				if(socket != null)socket.close();
			}catch(IOException e) {
				System.out.println("エラーが発生しました");
				e.printStackTrace();
			}
		}
	}

}
