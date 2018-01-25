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
			socket 								= new Socket("localhost",9999);
			bufferdReader 						= new BufferedReader(new InputStreamReader(socket.getInputStream()));
			printWriter 						= new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));
			bufferedReaderStdIn 				= new BufferedReader(new InputStreamReader(System.in));
			ObjectOutputStream outputStream 	= new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream  inputStream 	= new ObjectInputStream(socket.getInputStream());
			
			ArrayList<dto.Plan> 			plan;	
			ArrayList<dto.Product> 		product;
			ArrayList<dto.Rental> 			rental;
			ArrayList<dto.Settlement>		settlement;
			ArrayList<dto.User> 			user;
			
			
			sendString = "";
			while(! sendString.equals(END_MARK)) {
				System.out.println("送信メッセージを入力してください:");
				sendString = bufferedReaderStdIn.readLine();
	
				printWriter.println(sendString);
				printWriter.flush();
				product = (ArrayList<dto.Product>) inputStream.readObject();
				
				for(dto.Product db: product) {
					System.out.println("ID :\t"+ db. getId()+"\n"
										+"Name :\t"+ db.getName()+"\n"
										+"Stock :\t"+ db.getStock()+"\n"
										+"Price :\t"+ db.getPrice()+"\n");

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
