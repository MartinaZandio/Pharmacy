package pharmacy.db.interfaces;
import pharmacy.db.pojos.*;
import java.util.List;


public interface PatientProfile {

	//(Nete) Ejemplos de una interfaz
	public void addPatient(Patient p);
	public List<Patient> searchPatientByName(String name);
	public List<Patient> searchPatientById(int id);
	public void deletePatient(Patient p);
}
