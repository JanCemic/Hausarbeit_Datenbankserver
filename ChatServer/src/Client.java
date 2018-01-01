import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

private String name;
private Socket client;
private BufferedReader reader;
private PrintWriter writer;
private OutputStream out;
private InputStream in;

public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public Socket getClient() {
	return client;
}
public void setClient(Socket client) {
	this.client = client;
}
public BufferedReader getReader() {
	return reader;
}
public void setReader(BufferedReader reader) {
	this.reader = reader;
}
public PrintWriter getWriter() {
	return writer;
}
public void setWriter(PrintWriter writer) {
	this.writer = writer;
}

public Client(String name, Socket client) {
	this.name = name;
	this.client = client;
	prepare();
	}

public void prepare() {
	try {
		out = client.getOutputStream();
		in = client.getInputStream();
		writer = new PrintWriter(out);
		reader = new BufferedReader(new InputStreamReader(in));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}