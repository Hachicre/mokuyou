import java.io.BufferedReader;
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
	@SuppressWarnings("unchecked")
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
			ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
			ArrayList<shosekiDBSystem> list;
			sendString = "";
			while(! sendString.equals(END_MARK)) {
				System.out.println("送信メッセージを入力してください:");
				sendString = bufferedReaderStdIn.readLine();
	
				printWriter.println(sendString);
				printWriter.flush();
				list = (ArrayList<shosekiDBSystem>) inputStream.readObject();
				
				for(shosekiDBSystem db: list) {
					System.out.println("ISBN :\t"+ db. getISBN()+"\n"
										+"TITLE :\t"+ db.getBookname()+"\n"
										+"TITLE :\t"+ db.getBookname()+"\n"
										+"WRITER :\t"+ db.getWriter()+"\n"
										+"PUBLISHER :\t"+ db.getPublisher()+"\n");

				}
				receiveString = bufferdReader.readLine();
				System.out.println("Server:" +receiveString);
			}
			bufferedReaderStdIn.close();
			bufferdReader.close();
			printWriter.close();
			socket.close();
			
		}catch(IOException | ClassNotFoundException e) {
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
