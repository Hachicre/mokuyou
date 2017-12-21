import java.util.ArrayList;
import java.util.Scanner;
public class shosekiDBManagement {
	private static final Scanner  scanner = new Scanner(System.in);
	 static DBAccess dao = new DBAccess();
	public static void main(String[] args) {
			shosekiDBSystem data;
			int ret;
			
	        while(true){
	        	System.out.println("");
	            System.out.print(
	                "  hello! This is books_database. \n"
	              + "Please choose and pless one of these. \n"
	              + "    --- menu --- \n"
	              + "    1 : register \n"
	              + "    2 : search \n"
	              + "    9 : exit \n"
	              +"==>"
	            );


	            switch(scanner.nextInt()){
	                case 1:
	                    System.out.print("ISBN      > ");
	                    int ISBN         = scanner.nextInt();
	                    scanner.nextLine();
	                    System.out.print("bookName  > ");
	                    String bookName  = scanner.nextLine();
	                    System.out.print("writer    > ");
	                    String writer    = scanner.nextLine();
	                    System.out.print("publisher > ");
	                    String publisher = scanner.nextLine();
	                    System.out.println("release");
	                    System.out.print("year      > ");
	                    int year  = scanner.nextInt();
	                    System.out.print("month     > ");
	                    int month = scanner.nextInt();
	                    System.out.print("date      > ");
	                    int date  = scanner.nextInt();
	                    data = new shosekiDBSystem(ISBN,bookName,writer,publisher,year,month,date);
	        			ret = dao.registration(data);
	        			System.out.println("挿入レコード件数" + ret);
	                    break;
	                case 2:
	                	ArrayList<shosekiDBSystem> list = dao.findAll();
	            		for(shosekiDBSystem d : list) {
	            				System.out.println("ISBN :\t" + d.getISBN());
	            				System.out.println("BOOKNAME :\t" + d.getBookname());
	            				System.out.println("WRITER :\t" + d.getWriter());
	            				System.out.println("PUBLISHER :\t" + d.getPublisher());
	            				System.out.println("YEAR :\t" + d.getYear());
	            				System.out.println("MONTH :\t" + d.getMonth());
	            				System.out.println("DAY :\t" + d.getDay());
	            		}
	                    break;
	                case 9:
	                    System.exit(0);
	                    break;
	            }
	        }
		}
}
