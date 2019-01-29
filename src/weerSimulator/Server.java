package weerSimulator;

import java.net.*;
import java.io.*;

public class Server {
	
	public static void main(String[] args) {
        BufferedInputStream bis;
        BufferedOutputStream bos;
        int num;

     try {
        int i = 1;
        ServerSocket s = new ServerSocket(7789);
        Socket incoming = s.accept();
        System.out.println("Spawning " + i);

        try {
              try{

            InputStream inStream = incoming.getInputStream();
            OutputStream outStream = incoming.getOutputStream();

            BufferedReader inm = new BufferedReader(new InputStreamReader(inStream));
            PrintWriter out = new PrintWriter(outStream, true /* autoFlush */);



            out.println("File received ");

            //RECIEVE and WRITE FILE
            byte[] receivedData = new byte[8192];
            bis = new BufferedInputStream(incoming.getInputStream());
            bos = new BufferedOutputStream(new FileOutputStream("file.xml"));
            //length = bis.read(receivedData);
            while ((num = bis.read(receivedData)) != -1){
            bos.write(receivedData,0,num);
        }
        bos.close();
        bis.close();

        File receivedFile = new File("file.xml");
        long receivedLen = receivedFile.length();
        out.println("ACK: Length of received file = " + receivedLen);
        System.out.println("Length of received file = " + receivedLen);

              } finally {
            incoming.close();
           }
        } catch (IOException e){
    e.printStackTrace();
    }
        } catch (IOException e1){
    e1.printStackTrace();
    }
	}


}