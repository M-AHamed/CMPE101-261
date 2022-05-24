import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class CaesarCipher {
	
	// Do not change this method
	public boolean testCaesarCipher() {
		ServerCipher sTest = new ServerCipher(5000, 3);
		ClientDecipher cTest = new ClientDecipher(5000, 3);
		
		return sTest.cipher("hello").equals("khoor") && cTest.decipher("khoor").equals("hello");
	}

}

// SERVER-SIDE
class ServerCipher {
	
	private int port;  // port number to be allocated 
	private int shift; // shift value for CaesarCipher 
	private String cypheredMessage; // store the cyphered message
	public ServerCipher(int port, int shift) {
		this.port = port;
		this.shift = shift;
	}
	
	// cipher the given string wrt this.shift
	public String cipher(String msg) {
		char[] array = msg.toCharArray(); // store the characters in array of type char
		// ***  FILL THIS METHOD FOR PART 1 *** //
		for (int i = 0; i < msg.length(); i++){
			array[i] = (char) ((array[i] + shift) % 128);
			// for each character in array, shift its ascii value by shift
		}
		cypheredMessage = String.valueOf(array); // save vale of array into cypheredMessage
		return cypheredMessage;

	}

	// open your server and publish encryption of the given message 
	public void sendCipheredMessage(String msg) {

		// *** FILL THIS METHOD FOR PART 2 *** //
		try {
			ServerSocket server = new ServerSocket(port);
			Socket socket = server.accept();

			// sending the message to the client
			OutputStream output = socket.getOutputStream(); // writes the output stream
			DataOutputStream dataOut = new DataOutputStream(output);
			dataOut.writeUTF(cypheredMessage); // saving the cyphered message into the output stream

			// closing the connection
			output.close();
			dataOut.close();
			socket.close();
			server.close();
		}catch (IOException e) {
			e.printStackTrace();
		}

	}
	
}

// CLIENT-SIDE
class ClientDecipher {

	private int port;  // port number to be allocated 
	private int shift; // shift value for CaesarCipher
	private String decipheredMessage;

	public ClientDecipher(int port, int shift) {
		this.port = port;
		this.shift = shift;
	}

	// decipher the given string wrt this.shift
	public String decipher(String msg) {

		char[] array = msg.toCharArray(); // saving string into char array
		// ***  FILL THIS METHOD FOR PART 1 *** //
		for (int i = 0; i < msg.length(); i++) {
			array[i] = (char) ((array[i] + 128 - shift % 128) % 128);
			// for each char in the array, decipher it
		}
		decipheredMessage = String.valueOf(array);
		return decipheredMessage;
	}

	// listen your server and return decipher of the encrypted message 
	public String listenMessage() {
		String	decipheredtxt = "";
		// *** FILL THIS METHOD FOR PART 3 *** //
		try {
			// initializing the client side
			Socket clientDecipherSocket = new Socket("127.0.0.1", port);
			InputStream in = clientDecipherSocket.getInputStream(); // new input stream
			DataInputStream dataInput = new DataInputStream(in); // new data input stream

			decipheredtxt =  decipher(dataInput.readUTF()); // save the string value from the input
			System.out.println(dataInput.readUTF());
			in.close();
			dataInput.close();
			clientDecipherSocket.close();

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();

		}
	return decipheredtxt;

	}
}


