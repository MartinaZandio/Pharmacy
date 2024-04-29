package pharmacy.db.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import library.db.pojos.Author;
import library.db.pojos.Book;
import pharmacy.db.interfaces.MedicineManager;
import pharmacy.db.pojos.*;

public class JDBCMedicineManager implements MedicineManager {

	private ConnectionManager conMan;
	private Connection c;

	public JDBCMedicineManager(ConnectionManager conMan) {
		this.conMan = conMan; 
		this.c = conMan.getConnection();
	}
	@Override
	public void addMedicine(Medicine medicine) {
		// TODO Auto-generated method stub
		try {
			String template= "INSERT INTO medicine (id, name, prescription_id, laboratory_id)"
					+ "VALUES (?,?,?,?);";
			PreparedStatement insert= c.prepareStatement(template);		
			insert.setInt(1,medicine.getNumAsigned());
			insert.setString(2, medicine.getName());
			insert.setInt(3, medicine.getPrescription().getId());
			insert.setInt(4, medicine.getLaboratory().getId());
			insert.executeUpdate();	
			insert.close();
			}catch(SQLException sqlE) {
				System.out.println("Error in query");
				sqlE.printStackTrace();
			}

	}

	@Override
	public void searchMedicine() {
		
	}
	
	public List<Book> searchBookByTitle(String title) {
		List<Book> books = new ArrayList<Book>();
		try {
			String sql = "SELECT * FROM books WHERE title LIKE ?";
			PreparedStatement search = c.prepareStatement(sql);
			search.setString(1, "%" + title + "%");
			ResultSet rs = search.executeQuery();
			while(rs.next()) {
				Integer isbn = rs.getInt("isbn");
				String bookTitle = rs.getString("title");
				Date pubDate = rs.getDate("publicationDate");
				Integer authorId = rs.getInt("author_id");
				Author author = conMan.getAuthorMan().getAuthor(authorId);
				Book newBook = new Book(isbn, bookTitle, pubDate, author);
				books.add(newBook);
			}
			return books;
		} catch (SQLException e) {
			System.out.println("Error looking for a book");
			e.printStackTrace();
		}
		return books;
	}

	@Override
	public Medicine getMedicine(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void assignMedicine() {
		// TODO Auto-generated method stub
		
	}

}
