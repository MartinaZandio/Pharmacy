package pharmacy.db.xml;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.sql.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class SQLDateAdapter extends XmlAdapter<String, Date> { //from String to date and viceversa
	
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	@Override //comes from the interface
	public String marshal(Date sqlDate) throws Exception {
		return sqlDate.toLocalDate().format(formatter); //returns a string from a java sqlDate
	}

	@Override
	public Date unmarshal(String string) throws Exception {
		LocalDate localDate = LocalDate.parse(string, formatter);
		return Date.valueOf(localDate); 
	}

} //to create our own adapter we have to create the marshal() and unmarshal()