import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.io.OutputStream;



public class TestClient {

	
	public static void main(String ags[]) {
		
		try {
			
			Socket client = new Socket("localhost", 5555);
			System.out.println("Gestartet");
			
			OutputStream out = client.getOutputStream();
			PrintWriter writer =new PrintWriter(out);
			
			InputStream in = client.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			
			writer.write("Hallo Server");
			writer.flush();
			
			reader.close();
			writer.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
