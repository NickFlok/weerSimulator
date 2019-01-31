package weerSimulator;


import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;

public class ServerTest {
    public static void main(String[] args) {

        ServerSocket server = null;
        try {
            server = new ServerSocket(7789);
            server.setReuseAddress(true);
            // The main thread is just accepting new connections
            while (true) {
                Socket client = server.accept();
                System.out.println("New client connected " + client.getInetAddress().getHostAddress());
                ClientHandler clientSock = new ClientHandler(client);

                // The background thread will handle each client separately
                new Thread(clientSock).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (server != null) {
                try {
                    server.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static class ClientHandler implements Runnable {

        private final Socket clientSocket;
    	private String date;
    	private ArrayList stationListNic;
    	private ArrayList stationListBol;
    	private ArrayList stationListSur;
    	private LinkedList windSpeedCheck;
    	private LinkedList cloudCoverageCheck;
    	private String windSpeed;
    	private String cloudCoverage;

        public ClientHandler(Socket socket) {
        	
            this.clientSocket = socket;
            Date currentDate = new Date();
            this.date = new SimpleDateFormat("yyyy-MM-dd").format(currentDate);
            stationListNic = new ArrayList();
            stationListBol = new ArrayList();
            stationListSur = new ArrayList();
            Queue<String> windSpeedCheck = new LinkedList<String>();
            Queue<String> cloudCoverageCheck = new LinkedList<String>();
            
        }
        
        public String split(String s){
        	
            int beginString = -1;
            int endString = -1;
            beginString = s.indexOf(">") + 1;
            endString = s.indexOf("</");
            s = s.substring(beginString,endString);
            return s;
            
        }
        
        public void adder() {
        	windSpeedCheck.add(windSpeed);
        	cloudCoverageCheck.add(cloudCoverage);
        }

        @Override
        public synchronized void run() {
            PrintWriter out = null;
            BufferedReader in = null;
            String station = " ";
            String date = " ";
            String time = " ";
            String windSpeed = " ";
            String cloudCoverage = " ";
            String day = " ";
            stationListNic.add("298690");
            stationListBol.add("947260");
            stationListSur.add("749538");
      
            try {
                out = new PrintWriter("file1.csv");
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                
                String line;
                while ((line = in.readLine()) != null) {
                    if(line.contains("<STN>")) {
                    	station = split(line);
                    }
                    if(line.contains("<DATE>")) {
                    	date = split(line);
                    }
                    if(line.contains("<TIME>")) {
                    	time = split(line);
                    }
                    if(line.contains("<WDSP>")) {
                    	windSpeed = split(line);
                    }
                    if(line.contains("<CLDC>")) {
                    	cloudCoverage = split(line);
                    }
                    if(stationListNic.contains(station)) {
                    	
                        System.out.println(station);
                        System.out.println(date);
                        System.out.println(time);
                        System.out.println(windSpeed);
                        System.out.println(cloudCoverage);
                        adder();
                        out.write("N");
                        out.write(station);
                        out.write("D");
                        out.write(date);
                        out.write("T");
                        out.write(time);
                        out.write("W");
                        out.write(windSpeed);
                        out.write("C");
                        out.write(cloudCoverage);

                    }
                    if(stationListBol.contains(station)) {
                    	
                        System.out.println(station);
                        System.out.println(date);
                        System.out.println(time);
                        System.out.println(windSpeed);
                        System.out.println(cloudCoverage);
                        adder();
                        out.write("N");
                        out.write(station);
                        out.write("D");
                        out.write(date);
                        out.write("T");
                        out.write(time);
                        out.write("W");
                        out.write(windSpeed);
                        out.write("C");
                        out.write(cloudCoverage);

                        
                    }
                    if(stationListSur.contains(station)) {
                    	
                        System.out.println(station);
                        System.out.println(date);
                        System.out.println(time);
                        System.out.println(windSpeed);
                        System.out.println(cloudCoverage);
                        adder();
                        out.write("N");
                        out.write(station);
                        out.write("D");
                        out.write(date);
                        out.write("T");
                        out.write(time);
                        out.write("W");
                        out.write(windSpeed);
                        out.write("C");
                        out.write(cloudCoverage);
                     

                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (out != null) {
                        out.close();
                    }
                    if (in != null)
                        in.close();
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}