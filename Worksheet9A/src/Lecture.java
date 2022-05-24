import java.io.*;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Lecture implements Serializable {

	private String name;
	private Student[] students;

	public Lecture(String name, Student[] students) {
		this.name = name;
		this.students = students;
	}

	public String getName() {
		return name;
	}
	public Student[] getStudents() {
		return students;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setStudents(Student[] students) {
		this.students = students;
	}

	public Lecture initLecture() {
		Student[] tmp = {new Student(1, "Bilgi", 100)};
		return new Lecture("CMPE261", tmp);
	}
}

//STUDENT CLASS
class Student {

	private int id;
	private String name;
	private int grade;

	public Student(int id, String name, int grade) {
		this.id = id;
		this.name = name;
		this.grade = grade;
	}

	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public int getGrade() {
		return grade;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
}


// =============================
//				API
// =============================
interface RMIInterface extends Remote {
	public void insertStudent(int id, String name, int grade) throws RemoteException;
	public double calculateAverageGrade() throws RemoteException;
	public void readLectureFromFile(String fileName) throws IOException, ClassNotFoundException;
	public void saveLectureToFile(String fileName) throws RemoteException;
}

class RMI implements RMIInterface {

	private Lecture lecture;

	@Override
	public void insertStudent(int id, String name, int grade) throws RemoteException {
		// *** FILL THIS METHOD FOR PART 1 ***
		Student student = new Student(id, name, grade);


	}

	@Override
	public double calculateAverageGrade() throws RemoteException {
		// *** FILL THIS METHOD FOR PART 1 ***
		return 0;
	}

	@Override
	public void readLectureFromFile(String fileName) throws IOException, ClassNotFoundException {
		// *** FILL THIS METHOD FOR PART 2 ***
		FileInputStream fileIn = new FileInputStream(file); // new file stream
		ObjectInputStream objIn = new ObjectInputStream(objIn); // new object input stream

		Lecture readObject= (Lecture)objIn.readObject(); // readObject of type lecture to store read lectures
		// closing the file
		fileIn.close();
		objIn.close();
	}

	@Override
	public void saveLectureToFile(String fileName) throws RemoteException {
		// *** FILL THIS METHOD FOR PART 2 ***
		// to save the lecture object in a file
		File file = new File("Lecture.ser"); // new file name
				try{
					FileOutputStream fileOut = new FileOutputStream(file); // new fileoutput stream
					ObjectOutputStream objOut = new ObjectOutputStream(fileOut); // new objectOutput stream

					objOut.writeObject(lecture); // write the lecture objecte into file
					// close the file
					objOut.flush();
					objOut.close();
					fileOut.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
	}

}

//=============================
//			SERVER-SIDE
//=============================
class UniversityServer {

	// rmi:			rmi object for providing api
	// bindAddress: binding address of the stub(registry point)
	private RMI rmi;
	private String bindAddress;

	public UniversityServer(RMI rmi, String bindAddress) {
		this.rmi = rmi;
		this.bindAddress = bindAddress;
	}

	// open the server and wait clients to make requests
	public void openServer() {
		// *** FILL THIS METHOD FOR PART 3 ***

	}

}

//=============================
//			CLIENT-SIDE
//=============================
class TeacherClient {

	// api:			provides the methods for clients
	// bindAddress: binding address of the stub(registry point)
	private RMIInterface api;
	private String bindAddress;

	public TeacherClient(String bindAddress) {
		this.bindAddress = bindAddress;
	}

	// open the client side of the registry and request for adding the student to lecture
	public void requestInsertStudent(int id, String name, int grade) {
		// *** FILL THIS METHOD FOR PART 4 ***
	}

	// open the client side of the registry and request for calculating the average grade of the students
	public double requestCalculateAverageGrade() {
		// *** FILL THIS METHOD FOR PART 4 ***
		return 0;
	}

	// open the client side of the registry and request for saving the lecture into a serial file
	public void requestSaveFile(String fileName) {
		// *** FILL THIS METHOD FOR PART 4 ***
	}
}
