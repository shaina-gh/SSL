# Secure Client-Server Communication using SSL

This project demonstrates a simple **SSL (Secure Sockets Layer)** based client-server application in Java. It ensures secure communication between a server and a client by encrypting the transmitted data using Java’s built-in SSL capabilities.

## 🔒 Project Overview

This application sets up a server that listens over SSL and a client that connects securely to it. Both sides exchange messages over an encrypted channel, providing confidentiality and integrity.

---

## 🛠️ Setup & Execution

### Step 1: Prerequisites
- Java JDK (8 or later)
- VS Code with Java Extension Pack (recommended)
- Terminal access

### Step 2: Generate Keystore
The keystore is required by the server to authenticate itself.

```bash
keytool -genkeypair -alias serverkey -keyalg RSA -keysize 2048 -keystore serverkeystore.jks -validity 365
Keystore password: password

You can leave other prompts as default or fill in accordingly.
```

### Step 3: Compile the Code
Navigate to the project directory and run:

```bash
javac SSLServer.java SSLClient.java
```

### Step 4: Start the Server

```bash
java SSLServer
```
The server will listen for incoming SSL connections on port 4444.

### Step 5: Run the Client (in another terminal)
```bash
java SSLClient
```
Upon successful connection, messages will be exchanged securely.

---

## 🧠 Key Concepts
SSLContext: Sets up the secure socket layer configuration.

Keystore: Stores the server's private key and certificate.

TrustManager: Used on the client side to accept the server’s certificate (here, it accepts all for demo purposes).

Encrypted Communication: All data between client and server is encrypted using SSL.

## 📌 Important Notes
This project is for educational purposes. The client blindly trusts all certificates, which is not recommended for production use.

For real applications, validate server certificates properly using a TrustStore.

## 📷 Output (Sample)
Server terminal:

```arduino
SSL Server is running on port 4444
Client: Hello from Client
```

Client terminal:

```pgsql
Server: Hello from Server
```

---

