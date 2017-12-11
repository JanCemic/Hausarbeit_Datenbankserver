import java.io.BufferedReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Test{
	
	public static void main(String ags[]) {
		
		try {
			ServerSocket server = new ServerSocket(5555);
				System.out.println("Gestartet");
			Socket client = server.accept();
			OutputStream out = client.getOutputStream();
			InputStream in = client.getInputStream();
			PrintWriter writer = new PrintWriter(out);
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			
			
			
			String s = null;
					
			while((s = reader.readLine()) != null) {
				writer.write(s);
				System.out.println("Client: " + s);
			}
			
			writer.close();
			reader.close();
					
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
}
