package weerSimulator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;

import javax.swing.JOptionPane;


public class Writer {
	
	public static void main(String[] args) {
		
		String ID = "1234";
		String name = "Bob";
		String age = "22";
		String filepath = "cake.csv";
		
		saveRecord(ID, name, age, filepath);
		
	}
	
	public static void saveRecord(String ID, String name, String age, String filepath)
	{
		try
		{
			FileWriter fw = new FileWriter(filepath,true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			
			pw.println(ID+","+name+","+age);
			pw.flush();
			pw.close();
			
			JOptionPane.showMessageDialog(null, "Record saved");
                 
           
		}
		catch(Exception E)
		{
			JOptionPane.showMessageDialog(null, "Record not saved");
            
		}
	}
}
	
	