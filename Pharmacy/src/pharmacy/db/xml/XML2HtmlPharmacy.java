package pharmacy.db.xml;

import java.io.File;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class XML2HtmlPharmacy {
	
	public static void simpleTransfrom(String sourcePath, String xsltPath, String resultDir) {
		TransformerFactory tfactory= TransformerFactory.newInstance();
		try {
			Transformer transformer = tfactory.newTransformer(new StreamSource(new File (xsltPath)));
			transformer.transform(new StreamSource(new File(sourcePath)), new StreamResult(new File(resultDir)));	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
