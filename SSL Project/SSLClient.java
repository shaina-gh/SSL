import javax.net.ssl.*;
import java.io.*;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

public class SSLClient {
    public static void main(String[] args) throws Exception {
        String host = "localhost";
        int port = 4444;

        // Trust all certificates (for demo)
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, new TrustManager[] { new X509TrustManager() {
            public void checkClientTrusted(X509Certificate[] certs, String authType) {
            }

            public void checkServerTrusted(X509Certificate[] certs, String authType) {
            }

            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        } }, new SecureRandom());

        SSLSocketFactory sf = sslContext.getSocketFactory();
        SSLSocket socket = (SSLSocket) sf.createSocket(host, port);

        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        out.write("Hello from Client\n");
        out.flush();

        String serverMsg = in.readLine();
        System.out.println("Server: " + serverMsg);

        socket.close();
    }
}

/*
 * 1. Install Java Extension Pack
Make sure you have the Java Extension Pack installed in VS Code.

2. Create a Project Folder
Example folder structure:

SSLProject/
├── SSLServer.java
├── SSLClient.java
├── serverkeystore.jks

3. Generate the Keystore
Run this in terminal:

keytool -genkeypair -alias serverkey -keyalg RSA -keysize 2048 -keystore serverkeystore.jks -validity 365

When prompted:

Keystore password: password

Name, Org: anything (use defaults if unsure)

Confirm: yes

4. Compile the Code
In terminal inside your project folder:

javac SSLServer.java SSLClient.java

5. Run the Server
In terminal:

java SSLServer

6. Run the Client (in another terminal or tab)

java SSLClient

 */
