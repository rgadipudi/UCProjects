package com.bentley.dt;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CSVSaveData 
{
	// File created with time in name
	static String fileName = new JaviTerm().fileName;
	static int i = 0;
	String folderName = new FileDirectory().sCreateSubFolderJaviTerm;
	
	CSVSaveData() throws IOException
	{	
			// creates a file with default name
			String newFileName =  fileName;
			File newFile = new File(newFileName);
			BufferedWriter writer = new BufferedWriter(new FileWriter(newFile, true));
	}
	
	// Save the incoming data in to CSV file
	public void sendtoCSV(String data) throws IOException
	{
		String dataInput = new TimeStamp().getDateTime()+","+data;
		
		parse(dataInput.replace(" ", ","));
		// Opens the file
		FileWriter writer = new FileWriter(fileName, true);
		// Send data to database
		// give radio messages count
	    // Appends the data with time stamp
		writer.append(dataInput+"\n");
		writer.flush();
	    writer.close();
	}
	
	 void parse(String strLine) throws IOException 
		{
			strLine=strLine.replaceAll("[^A-Za-z0-9,-:]", "");
			int count =countOccurrences(strLine, ",");
			int i = 0;
			String[] s= new String[count];
		
			if(count==1)
			{
				FileWriter writer = new FileWriter(folderName+"/1.txt", true);
				// write data in to report file
				writer.append(strLine+"\n");
				writer.flush();
			    writer.close();
			}
			else if(count>1 && count<5)
			{
				FileWriter writer = new FileWriter(folderName+"/2.txt", true);
				// write data in to report file
				writer.append(strLine+"\n");
				writer.flush();
			    writer.close();
			}
			
			else if(count>4 && count<8)
			{		
				FileWriter writer = new FileWriter(folderName+"/3.txt", true);
				// write data in to report file
				writer.append(strLine+"\n");
				writer.flush();
			    writer.close();
			}
			else if(count>8 && count<15)
			{
				FileWriter writer = new FileWriter(folderName+"/4.txt", true);
				// write data in to report file
				writer.append(strLine+"\n");
				writer.flush();
			    writer.close();
			}
			else if(count>15)
			{
				FileWriter writer = new FileWriter(folderName+"/5.txt", true);
				// write data in to report file
				writer.append(strLine+"\n");
				writer.flush();
			    writer.close();
			}
			
			else
			{
				FileWriter writer = new FileWriter(folderName+"/6.txt", true);
				// write data in to report file
				writer.append(strLine+"\n");
				writer.flush();
			    writer.close();
			}
		//	do
		//	{
		//		int ia = strLine.indexOf(",");
		//		s[i]=strLine.substring(0, ia);
		//		strLine = strLine.substring(ia+1, strLine.length());
		//		i++;	
		//		
		//	}
		//	while(cntOccurrences(strLine,","));
		}

		public static int countOccurrences(String str, String check)
		{
		    int count = 0;
		    for (int i=0; i < str.length(); i++)
		    {
		    	char c = check.charAt(0);
		        if (str.charAt(i) == c)
		        {
		             count++;
		        }
		    }
		    return count;
		}
		public static boolean cntOccurrences(String str, String check)
		{
		    for (int i=0; i < str.length(); i++)
		    {
		    	char c = check.charAt(0);
		        if (str.charAt(i) == c)
		        {
		             return true;
		        }
		    }
		    return false;
		}
}
