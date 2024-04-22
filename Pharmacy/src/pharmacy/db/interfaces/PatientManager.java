package pharmacy.db.interfaces;
import pharmacy.db.pojos.*;
import java.util.List;


public interface PatientManager {

	public void takeMedicine(int patientId, int medicineId);
	public List<Medicine> getTakenMedicines(int patientId);
	
}
