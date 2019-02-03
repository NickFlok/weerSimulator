package weerSimulator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ServerTest {
    public static void main(String[] args) {

    	System.out.println("Running");
    	ServerSocket server = null;
        try {
            server = new ServerSocket(9999);
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
    	private ArrayList<String> stationListNic;
    	private ArrayList<String> stationListBol;
    	private ArrayList<String> stationListSur;
    	/*private LinkedList<?> windSpeedCheck;
    	private LinkedList<?> cloudCoverageCheck;
    	private String windSpeed;
    	private String cloudCoverage;*/

        public ClientHandler(Socket socket) {
        	
            this.clientSocket = socket;
            Date currentDate = new Date();
            new SimpleDateFormat("yyyy-MM-dd").format(currentDate);
            stationListNic = new ArrayList<String>();
            stationListBol = new ArrayList<String>();
            stationListSur = new ArrayList<String>();
//            Queue<String> windSpeedCheck = new LinkedList<String>();
//            Queue<String> cloudCoverageCheck = new LinkedList<String>();
//            windSpeedCheck.add("1");
//            cloudCoverageCheck.add("1");
            
        }
        
        public String split(String s){
        	
            int beginString = -1;
            int endString = -1;
            beginString = s.indexOf(">") + 1;
            endString = s.indexOf("</");
            s = s.substring(beginString,endString);
            return s;
            
        }
        
      /*  public String addWind(String windSpeed) {
        	if(windSpeed != null) {
        		windSpeedCheck.add(windSpeed);
        		windSpeed = ErrorCorrection.errorCheck(windSpeedCheck, windSpeed);
        		return windSpeed;
        	}
        	
        	float[] windspeedF = ErrorCorrection.naarArray(windSpeedCheck);
        	float som = ErrorCorrection.calcSom(windspeedF);
        	float gemiddelde = ErrorCorrection.gemiddelde(som, windSpeedCheck);
        	windSpeed = Float.toString(gemiddelde);
        	if (windSpeedCheck.size() >=30) {
        			windSpeedCheck.remove();
				}
        	return windSpeed;   	
        }
        
        
        public String addCoverage(String cloudCoverage) {
        	if (cloudCoverage != null) {
        		cloudCoverageCheck.add(cloudCoverage);
        		cloudCoverage = ErrorCorrection.errorCheck(cloudCoverageCheck, cloudCoverage);
        		return cloudCoverage;
        	}
        	
        	float[] cloudF = ErrorCorrection.naarArray(cloudCoverageCheck);
        	float som = ErrorCorrection.calcSom(cloudF);
        	float gemiddelde = ErrorCorrection.gemiddelde(som, cloudCoverageCheck);
        	cloudCoverage = Float.toString(gemiddelde);
        	if (cloudCoverageCheck.size() >=30) {
        			cloudCoverageCheck.remove();
			}
        	return cloudCoverage;
       }*/
        

        @Override
        public synchronized void run() {
            PrintWriter out = null;
            BufferedReader in = null;
            String station = " ";
            String date = " ";
            String time = " ";
            String windSpeed = " ";
            String cloudCoverage = " ";
            //String day = " ";
            stationListNic.add("298690");
            stationListBol.add("947260");
            stationListSur.add("749538");
      
            try {
                out = new PrintWriter("/home/pi/data/weather.xml");
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
                    	
                    	// doet errorhandeling
                        //windSpeed = addWind(windSpeed);
                       // cloudCoverage = addCoverage(cloudCoverage);
                    	
                    	System.out.println(station);
                        System.out.println(date);
                        System.out.println(time);
                        System.out.println(windSpeed);
                        System.out.println(cloudCoverage);
                        out.write("NN");
                        out.write(station);
                        out.write(">");
                        out.write("DN");
                        out.write(date);
                        out.write(">");
                        out.write("TN");
                        out.write(time);
                        out.write(">");
                        out.write("WN");
                        out.write("W");
                        out.write(windSpeed);
                        out.write(">");
                        out.write("CN");
                        out.write(cloudCoverage);
                        out.write(">");
                    }
                    if(stationListBol.contains(station)) {
                    	
                    	// doet errorhandeling
                      //  windSpeed = addWind(windSpeed);
                       // cloudCoverage = addCoverage(cloudCoverage);
                    	
                        System.out.println(station);
                        System.out.println(date);
                        System.out.println(time);
                        System.out.println(windSpeed);
                        System.out.println(cloudCoverage);
                        out.write("N");
                        out.write(station);
                        out.write(">");
                        out.write("DB");
                        out.write(date);
                        out.write(">");
                        out.write("TB");
                        out.write(time);
                        out.write(">");
                        out.write("WB");
                        out.write(windSpeed);
                        out.write(">");
                        out.write("CB");
                        out.write(cloudCoverage);
                        out.write(">");
                        
                    }
                    if(stationListSur.contains(station)) {
                    	
//                    	// doet errorhandeling
//                    	if(windSpeed != null && !windSpeed.isEmpty()){
//                    		windSpeedCheck.add(windSpeed);
//                    		windSpeed = ErrorCorrection.errorCheck(windSpeedCheck, windSpeed);	
//                    	}
//                    	float[] windspeedF = ErrorCorrection.naarArray(windSpeedCheck);
//                    	float som = ErrorCorrection.calcSom(windspeedF);
//                    	float gemiddelde = ErrorCorrection.gemiddelde(som, windSpeedCheck);
//                    	windSpeed = Float.toString(gemiddelde);
//                    	if (windSpeedCheck.size() >=30) {
//                    			windSpeedCheck.remove();
//            				}
//                     	
                        
                        //cloudCoverage = addCoverage(cloudCoverage);
                    	
                        System.out.println(station);
                        System.out.println(date);
                        System.out.println(time);
                        System.out.println(windSpeed);
                        System.out.println(cloudCoverage);
                        out.write("SS");
                        out.write(station);
                        out.write(">");
                        out.write("DS");
                        out.write(date);
                        out.write(">");
                        out.write("TS");
                        out.write(time);
                        out.write(">");
                        out.write("WS");
                        out.write(windSpeed);
                        out.write(">");
                        out.write("CS");
                        out.write(cloudCoverage);
                        out.write(">");
                     

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