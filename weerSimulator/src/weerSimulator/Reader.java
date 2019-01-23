package weerSimulator;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;

import javax.swing.JOptionPane;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class Reader {
	public static void main(String argv[]) {

	    try {

		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser saxParser = factory.newSAXParser();

		DefaultHandler handler = new DefaultHandler() {

		// Waardes van xml bestand 
		boolean bstn = false;
		boolean bdate = false;
		boolean btime = false;
		boolean bwind = false;
		boolean bcloud = false;
		
		
		public void startElement(String uri, String localName,String qName, 
	                Attributes attributes) throws SAXException {

			//Test om het begin van de elementen te zien 
			//System.out.println("Start Element :" + qName);

			if (qName.equalsIgnoreCase("STN")) {
				bstn = true;
			}

			if (qName.equalsIgnoreCase("DATE")) {
				bdate = true;
			}

			if (qName.equalsIgnoreCase("TIME")) {
				btime = true;
			}

			if (qName.equalsIgnoreCase("WDSP")) {
				bwind = true;
			}
			
			if (qName.equalsIgnoreCase("CLDC")) {
				bcloud = true;
			}


		}

		public void endElement(String uri, String localName,
			String qName) throws SAXException {

			//Test om het einde van de elementen te zien
			//System.out.println("End Element :" + qName);
			// stationnumber station number  cloud coverage wind speed 

		}

		// schrijven van de waardes
		public void characters(char ch[], int start, int length) throws SAXException {

			if (bstn) {
				System.out.println("Station number : " + new String(ch, start, length));
				bstn = false;
			}

			if (bdate) {
				System.out.println("Date : " + new String(ch, start, length));
				bdate = false;
			}

			if (btime) {
				System.out.println("Time : " + new String(ch, start, length));
				btime = false;
			}

			if (bwind) {
				System.out.println("Wind speed : " + new String(ch, start, length));
				bwind = false;
			}
			
			if (bcloud) {
				System.out.println("Cloud coverage : " + new String(ch, start, length));
				bcloud = false;
			}
			
			/*String wstn = new String(ch, start, length);
			String wdate = new String(ch, start, length);
			String wtime= new String(ch, start, length);
			String wwind = new String(ch, start, length);
			String wcloud = new String(ch, start, length);
			
			String filepath = "test.csv";
			
			saveRecord(wstn, wdate, wtime, wwind, wcloud, filepath);*/

		}

	     };

	       saxParser.parse("c:\\output.xml", handler);
	 
	     } catch (Exception e) {
	       e.printStackTrace();
	     }
	  }
	
	/*public static void saveRecord(String wstn, String wdate, String wtime, String wwind, String wcloud, String filepath)
	{
		try
		{
			FileWriter fw = new FileWriter(filepath,true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			
			pw.println(wstn+","+wdate+","+wtime+","+wwind+","+wcloud);
			pw.flush();
			pw.close();
			
			JOptionPane.showMessageDialog(null, "Record saved");
                 
           
		}
		catch(Exception E)
		{
			JOptionPane.showMessageDialog(null, "Record not saved");
            
		}
	}
	*/ 
}

