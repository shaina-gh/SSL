import javax.net.ssl.*;
import java.io.*;
import java.security.KeyStore;

public class SSLServer {
    public static void main(String[] args) throws Exception {
        int port = 4444;

        // Load server keystore
        KeyStore keyStore = KeyStore.getInstance("JKS");
        keyStore.load(new FileInputStream("serverkeystore.jks"), "password".toCharArray());

        // Set up key manager
        KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
        kmf.init(keyStore, "password".toCharArray());

        // Set up SSL context
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(kmf.getKeyManagers(), null, null);

        SSLServerSocketFactory ssf = sslContext.getServerSocketFactory();
        SSLServerSocket serverSocket = (SSLServerSocket) ssf.createServerSocket(port);
        System.out.println("SSL Server is running on port " + port);

        SSLSocket clientSocket = (SSLSocket) serverSocket.accept();
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

        String clientMsg = in.readLine();
        System.out.println("Client: " + clientMsg);
        out.write("Hello from Server\n");
        out.flush();

        clientSocket.close();
        serverSocket.close();
    }
}
