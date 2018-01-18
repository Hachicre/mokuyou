
public class shosekiDBSystem implements java.io.Serializable {

		private int isbn;
		private String bookname;
		private String writer;
		private String publisher;
		private int year;
		private int month;
		private int day;

		public shosekiDBSystem(int isbn,
								String bookname,
								String writer,
								String publisher,
								int year,
								int month,
								int day) {
			
			this.isbn = isbn;
			this.bookname = bookname;
			this.writer = writer;
			this.publisher = publisher;
			this.year = year;
			this.month = month;
			this.day = day;
		}
			public int getISBN() {
					return isbn;
			}
			public void setISBN(int isbn) {
					this.isbn = isbn;
			}
			public String getBookname() {
					return bookname;
			}
			public void setBookname(String bookname) {
					this.bookname = bookname;
			}
			public String getWriter() {
					return writer;
			}
			public void setWriter(String writer) {
					this.writer = writer;
			}
			public String getPublisher() {
					return publisher;
			}
			public void setPublisher(String publisher) {
					this.publisher = publisher;
			}
			public int getYear() {
					return year;
			}
			public void setYear(int year) {
					this.year = year;
			}
			public int getMonth() {
					return month;
			}
			public void setMonth(int month) {
				this.month = month;
			}
			public int getDay() {
					return day;
			}
			public void setDay(int day) {
					this.day = day;
			}

}