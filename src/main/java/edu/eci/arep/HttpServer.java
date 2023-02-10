package edu.eci.arep;

import java.net.*;
import java.io.*;

public class HttpServer {
    private static HttpServer instance;
    private static final int PORT = 35000;
    private static final String PATH = "src/main/resources/static";
    private static final String DEFAULT_FILE = "index.html";
    private static final String NOT_FOUND_FILE = "404.html";
    private static final String CONTENT_TYPE = "Content-Type";
    private static final String CONTENT_LENGTH = "Content-Length";
    private static final String CONTENT_TYPE_HTML = "text/html";
    private static final String CONTENT_TYPE_CSS = "text/css";
    private static final String CONTENT_TYPE_JS = "text/javascript";
    private static final String CONTENT_TYPE_JPG = "image/jpeg";
    private static final String CONTENT_TYPE_PNG = "image/png";
    private static final String CONTENT_TYPE_GIF = "image/gif";
    private static final String CONTENT_TYPE_ICO = "image/x-icon";

    /*
     * Main method
     */
    public static void main(String[] args) {
        HttpServer.getInstance().start();
    }

    /**
     * Constructor
     */
    public static HttpServer getInstance() {
        if (instance == null) {
            instance = new HttpServer();
        }
        return instance;
    }

    /**
     * Starts the server
     */
    public void start() {
        try {
            try (ServerSocket serverSocket = new ServerSocket(PORT)) {
                System.out.println("Server started on port " + PORT);
                while (true) {
                    Socket clientSocket = serverSocket.accept();
                    System.out.println("Client connected");
                    processRequest(clientSocket);
                }
            }
        } catch (IOException e) {
            System.err.println("Error starting server: " + e.getMessage());
        }
    }

    /**
     * Processes the request
     * 
     * @param clientSocket
     */
    private void processRequest(Socket clientSocket) {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            String requestLine = in.readLine();
            System.out.println("Request line: " + requestLine);
            String[] requestLineParts = requestLine.split(" ");
            String method = requestLineParts[0];
            String resource = requestLineParts[1];
            String httpVersion = requestLineParts[2];
            System.out.println("Method: " + method);
            System.out.println("Resource: " + resource);
            System.out.println("HTTP version: " + httpVersion);
            String headerLine = in.readLine();
            while (!headerLine.isEmpty()) {
                System.out.println("Header line: " + headerLine);
                headerLine = in.readLine();
            }
            if (method.equals("GET")) {
                if (httpVersion.equals("HTTP/1.1")) {
                    if (resource.equals("/")) {
                        resource = "/" + DEFAULT_FILE;
                    }
                    File file = new File(PATH + resource);

                    if (file.exists()) {
                        String contentType = getContentType(file);
                        String contentLength = String.valueOf(file.length());
                        out.println("HTTP/1.1 200 OK");
                        out.println(CONTENT_TYPE + ": " + contentType);
                        out.println(CONTENT_LENGTH + ": " + contentLength);
                        out.println();
                        out.flush();
                        sendFile(file, out);
                    } else {
                        File notFoundFile = new File(PATH + "/" + NOT_FOUND_FILE);
                        String contentType = getContentType(notFoundFile);
                        String contentLength = String.valueOf(notFoundFile.length());
                        out.println("HTTP/1.1 404 Not Found");
                        out.println(CONTENT_TYPE + ": " + contentType);
                        out.println(CONTENT_LENGTH + ": " + contentLength);
                        out.println();
                        out.flush();
                        sendFile(notFoundFile, out);
                    }
                }
            }
            clientSocket.close();
        } catch (IOException e) {
            System.err.println("Error processing request: " + e.getMessage());
        }
    }

    /**
     * Sends the file
     * 
     * @param file
     * @param out
     */
    private void sendFile(File file, PrintWriter out) {
        try {
            BufferedReader fileReader = new BufferedReader(new FileReader(file));
            String line = fileReader.readLine();
            while (line != null) {
                out.println(line);
                line = fileReader.readLine();
            }
            fileReader.close();
        } catch (IOException e) {
            System.err.println("Error sending file: " + e.getMessage());
        }
    }

    /**
     * Gets the content type
     * 
     * @param file
     * @return
     */
    private String getContentType(File file) {
        String contentType = "";
        String fileName = file.getName();
        String[] fileNameParts = fileName.split("\\.");
        String fileExtension = fileNameParts[fileNameParts.length - 1];
        switch (fileExtension) {
            case "html":
                contentType = CONTENT_TYPE_HTML;
                break;
            case "css":
                contentType = CONTENT_TYPE_CSS;
                break;
            case "js":
                contentType = CONTENT_TYPE_JS;
                break;
            case "jpg":
                contentType = CONTENT_TYPE_JPG;
                break;
            case "png":
                contentType = CONTENT_TYPE_PNG;
                break;
            case "gif":
                contentType = CONTENT_TYPE_GIF;
                break;
            case "ico":
                contentType = CONTENT_TYPE_ICO;
                break;
        }
        return contentType;

    }

}
