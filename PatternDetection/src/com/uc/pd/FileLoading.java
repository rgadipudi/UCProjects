// UltraClenz LLC
// Project Quality Metrics
package com.uc.pd;

// Library Packages
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;

// This is the main class. 
public class FileLoading 
{
	
	static int iPatternCount = 1;  	// Count for invalid state sequences
	static int iTotal = 0;  		// Count for total invalid sequences
	
	static BufferedReader br;		
	static FileInputStream fStream;
	static DataInputStream in;
	
	static List<String[]> laLogData = new ArrayList<String[]>(); // List array to save the data read from CSV file
	static String sFolderName = "C:/Users/rgadipudi/Downloads/aug_2013/"; // Folder name to check the available files
	static HashSet<String> sUniqueSet; // HashSet for sorting the badge Id and keeps them as a reference
	
	public static void main(String args[]) throws IOException   
    {  
		
		String sLine = "";		// created to save line read from csv file
		sUniqueSet = new HashSet<String>();
		// Checks if there is argument passed or not  
		//if(args.length != 1) 
		//{
			// Throws error when no file is entered in command line
		//	System.err.println("Invalid command line, argument required");
		//	System.exit(1);
		//}
		
		// Reads all files from a folder.
		
		File folder = new File(sFolderName);		// Loads the folder 
		File[] listOfFiles = folder.listFiles();	// returns the list of available file in folder
		
		SaveToCSV stc = new SaveToCSV(sFolderName,1);	// SaveToCSV class object is initialized to created supported files and folders 
		SaveToCSV stc_2 = new SaveToCSV(sFolderName);	//  object is created to return the final report
		
		for (File file : listOfFiles) 				// for each loop for list of Files
		{
	    if (file.isFile()) 							// checks the input is file or not
		    {

		    	System.out.println("File: "+ file.getName()+" is Processing............");  // Prints the file name in to console output
		    	String sFileDate = file.getName(); 											// get file name to print the date 
		    	SaveToCSV stc_sub = new SaveToCSV(sFolderName,sFileDate.substring(0, 8));	// Creates folder and file with date
		    	
		    	// Displays processing file name
		    	stc_sub.sortCSV("Processing File Name: "+file.getName());  					// Prints the content in to file
		    	stc_sub.reportCSV("Processing File Name: "+file.getName());
		    	stc_sub.detailCSV("Processing File Name: "+file.getName());
		    	stc_sub.bucket1CSV("Processing File Name: "+file.getName());
		    	stc_sub.bucket2CSV("Processing File Name: "+file.getName());
		    	stc_sub.bucket3CSV("Processing File Name: "+file.getName());
		    	stc_sub.impossibleCSV("Processing File Name: "+file.getName());
		    	
		    	stc_sub.impossibleCSV("Captures impossible event states");
		    	stc_sub.bucket1CSV("Filter 1 : Same device and same states over and over");
		    	stc_sub.bucket2CSV("Filter 2 : Bad Request at device i.e, non dispensing events");
		    	stc_sub.bucket3CSV("Filter 3 : Different Devices and Events Missing");
		    	
		    	// Displays Date 
		    	stc_sub.sortCSV("Date : "+sFileDate.substring(0, 4)+"/"+sFileDate.substring(4, 6)+"/"+sFileDate.substring(6, 8));
		    	stc_sub.impossibleCSV("Date : "+sFileDate.substring(0, 4)+"/"+sFileDate.substring(4, 6)+"/"+sFileDate.substring(6, 8));
		    	stc_sub.reportCSV("Date : "+sFileDate.substring(0, 4)+"/"+sFileDate.substring(4, 6)+"/"+sFileDate.substring(6, 8));
		    	stc_sub.detailCSV("Date : "+sFileDate.substring(0, 4)+"/"+sFileDate.substring(4, 6)+"/"+sFileDate.substring(6, 8));
		    	stc_sub.bucket1CSV("Date : "+sFileDate.substring(0, 4)+"/"+sFileDate.substring(4, 6)+"/"+sFileDate.substring(6, 8));
		    	stc_sub.bucket2CSV("Date : "+sFileDate.substring(0, 4)+"/"+sFileDate.substring(4, 6)+"/"+sFileDate.substring(6, 8));
		    	stc_sub.bucket3CSV("Date : "+sFileDate.substring(0, 4)+"/"+sFileDate.substring(4, 6)+"/"+sFileDate.substring(6, 8));
		    	stc_sub.impossibleCSV("**********************************************");
		    	
		    	try
		        {  
					// command line parameter passes file name args[0]  
			        fStream = new FileInputStream(sFolderName+sFileDate);  					// loading each file from folder
			        // Get the object of DataInputStream  
			        in = new DataInputStream(fStream);  									
			        br = new BufferedReader(new InputStreamReader(in));  

		    	stc_sub.detailCSV("Detecting Impossible Events States");					// writes in to file

			    	//Read File Line By Line  
			        while ((sLine = br.readLine()) != null)   								// while loop to read all the lines in the file
		        	{  
			        	//Checks the number of colons
			        	if(countOccurrences(sLine,"|")==12)									// Checks for specific format of data. in this case our data contains 12 |		
			        	{
			        		// invokes Parse method sending sLine by replacing "," to Space which returns the badge address and saves in to laBadgeId list
			        		sUniqueSet.add(parse(sLine.replace(",", ""), stc_sub));   
			        	}
		        	}
		        }
		        
		        catch(Exception e)
		        {
		        	e.printStackTrace();
		        }
		        
		        finally
		        {
		        	//Returns the end count
		        	stc_sub.sortCSV("Total number of imposible events returned: "+iTotal);
		        	stc_sub.impossibleCSV("Total number of imposible events returned: "+iTotal);
		        	stc_sub.impossibleCSV("**********************************************");
		        }  
		        
		        // Returns the total  no of badges active on that day
		        stc_sub.sortCSV("Total Number of Badges Active:" + sUniqueSet.size());
				
				for (String sSetData : sUniqueSet)						// for each loop to read each value in hash set
				{
					if(sSetData.length() != 0)							// if the event is generated by badge id process array list			
					{
						// invokes the method pattern detection phase
						patternDetectionPhase(sSetData, stc_sub, file.getName().substring(4, 6)); 
					}
				}
				
				
				stc_sub.sortCSV("Total Number of imposible event patterns occured: "+iPatternCount);
				stc_sub.detailCSV("Total Number of imposible event patterns occured: "+iPatternCount);
				stc_sub.reportCSV("Total Number of imposible event patterns occured: "+iPatternCount);
				stc_sub.bucket1CSV("Total Number of imposible event patterns occured: "+iPatternCount);
				stc_sub.bucket2CSV("Total Number of imposible event patterns occured: "+iPatternCount);
				stc_sub.bucket3CSV("Total Number of imposible event patterns occured: "+iPatternCount);
				
				System.out.println("File: "+ file.getName()+" is Done.");
				
		    }
		    laLogData.clear();			// clear array list so it will delete the past data
		    iPatternCount = 0;			// clear 
		    System.gc();				// forcing JVM to clear unused resources after each files
		}
		
		new GenerateReport(stc_2).printReport();  // Prints the final report on processed files available in the folder
    }
	
	// Counts the occurrences of a string
	public static int countOccurrences(String str, String sCheck)
	{
	    int iCount = 0;			// create & initialize count
	    for (int i=0; i < str.length(); i++)	// for loop to check the whole line
	    {
	        if (str.charAt(i) == sCheck.charAt(0)) // check if the char matches
	        {
	             iCount++;  // if available increase count
	        }
	    }
	    return iCount;		// here we are looking for count #12
	}
	
	// Return the occurrences is true or not
	public static boolean returnOccurrences(String str, String sCheck)
	{
	    for (int i=0; i < str.length(); i++)  	// for loop to check the whole string
	    {
	        if (str.charAt(i) == sCheck.charAt(0))		// check if the char matches
	        {
	             return true;			// if available return true
	        }
	    }
	    return false;		// else false
	}
	
	// Method to parse the input string line
	private static String parse(String sLine, SaveToCSV stc_sub) throws IOException 
	{
		int i = 0;
		// Array For saving line data
		String[] sDataArray = new String[13];			// array to save parsed data fields
		
		do
		{
			// gets the index of |
			int iA = sLine.indexOf("|");	// return the index of |
			// Saves the sub string into array sDataArray
			sDataArray[i]=sLine.substring(0, iA);
			
			// Saves the remaining sub string in to string sLine
			sLine = sLine.substring(iA+1, sLine.length());
			i++;	// increase count
			
			if(i==11)	// for last occurrence of "|"
			{
			for (String sTemp : sLine.split("|"))   // for each loop split the line in to half if there is any "|" char 
        		{
					if(i!=0)				
					{
						sDataArray[12]=sTemp;		// save last data field in last array position
					}
					else
					{
						i++;
					}
        		}
			}
		}
		// Check the occurrences of |
		while(returnOccurrences(sLine,"|"));		// run again on occurrence
		
		// Invoke detection method
		detectionPhase(sDataArray[11]+sDataArray[12], sDataArray, stc_sub);
		
		// return the badge Id
		return sDataArray[5];
	}
	
	// Detects the impossible compliance for consecutive entry's for a badge
	static void patternDetectionPhase(String sBadgeId, SaveToCSV stc_sub, String date) throws IOException 
	{
		DataFilter df = new DataFilter();
		BadgeData bd = new BadgeData();								// Object for class BadgeData
		List<String[]> laSortData = new ArrayList<String[]>();		// Array list is created to save the sorted data
		
		int iDate = Integer.parseInt(date);							// data is taken
		
		for (String[] sSortData : laLogData)						// for each loop for laLogData
		{
			if (sSortData[5].equalsIgnoreCase(sBadgeId))			// if badge id is same as selected id
			{
				laSortData.add(sSortData);							// save it in to array list
			}	
		}
		
		// This sorts the data set
		laSortData = sortTime(laSortData);							
		
		if(bd.detectPattern(laSortData, stc_sub, sBadgeId)>0)				// if condition to check if there are an pattern found
		{
			stc_sub.reportCSV("**********************************************");		// write in to output files
			stc_sub.reportCSV("Impossible Events Patterns Found for : " + sBadgeId);
			stc_sub.detailCSV("**********************************************");
			stc_sub.detailCSV("Impossible Events Patterns Found for : " + sBadgeId);
			
			stc_sub.bucket1CSV("**********************************************");
			stc_sub.bucket1CSV("Impossible Events Patterns Filter 1 : " + sBadgeId);
			stc_sub.bucket2CSV("**********************************************");
			stc_sub.bucket2CSV("Impossible Events Patterns Filter 2 : " + sBadgeId);
			stc_sub.bucket3CSV("**********************************************");
			stc_sub.bucket3CSV("Impossible Events Patterns Filter 3 : " + sBadgeId);
			
			df.printData(stc_sub,sBadgeId, iDate);
			bd.printData(stc_sub,sBadgeId, iDate);										// prints the data out
			df.clearArrayBuff();
			
			iPatternCount++;																				
		}
		
		else
		{
			stc_sub.reportCSV("**********************************************");
			stc_sub.reportCSV("Impossible Events Patterns Found for : " + sBadgeId);
			bd.printNonData(stc_sub,sBadgeId, iDate);
		}
	}

	private static List<String[]> sortTime(List<String[]> laSortData) 
	{ 
		// String array lists to Support Sorting
		List<String[]> laSortTimeData = new ArrayList<String[]>();
		List<String> laTime = new ArrayList<String>();
		
		// Saving All time stamps to list 
		for(String[] sTime : laSortData)
		{
			laTime.add(sTime[2]);
		}
		
		// By help of tree set which automatically sorts the list. i am sorting time stamp and also returns distinct numbers
		
		TreeSet<String> sTimeUniqueSet = new TreeSet<String>(laTime);
		
		// Comparing each time stamp with laSortData and saving back as sorted list in laSortTimeData 
		for (String sSetTimeData : sTimeUniqueSet)
		{
			if(sSetTimeData.length() != 0)
			{
				for (String[] sTimeSortData : laSortData)
				{
					if (sTimeSortData[2].equalsIgnoreCase(sSetTimeData))
					{
						laSortTimeData.add(sTimeSortData);
					}	
				}
				 
			}
		}
		return laSortTimeData;
	}

	// Detects the Impossible compliance for one entry. Print them in to a file for filter
	static void detectionPhase(String sCompData, String[] s_DataArray, SaveToCSV stc) throws IOException 
	{
		laLogData.add(s_DataArray);
		String sSavedData = "";
		for (String s_Temp : s_DataArray)
		{
			sSavedData += s_Temp + ",";
		}
		
		switch(sCompData)
		{
			case "02":
				stc.impossibleCSV(sSavedData);	
				iTotal++;
				break;
			
			case "03":
				stc.impossibleCSV(sSavedData);		
				iTotal++;
				break;
				
			case "05":
				stc.impossibleCSV(sSavedData);	
				iTotal++;
				break;
			
			case "14":
				stc.impossibleCSV(sSavedData);	
				iTotal++;
				break;
				
			case "21":
				stc.impossibleCSV(sSavedData);	
				iTotal++;
				iTotal++;
				break;
			
			case "23":
				stc.impossibleCSV(sSavedData);	
				iTotal++;
				break;
				
			case "24":
				stc.impossibleCSV(sSavedData);	
				iTotal++;
				break;
				
			case "25":
				stc.impossibleCSV(sSavedData);	
				iTotal++;
				break;
				
			case "31":
				stc.impossibleCSV(sSavedData);	
				iTotal++;
				break;
				
			case "34":
				stc.impossibleCSV(sSavedData);	
				iTotal++;
				break;
				
			case "35":
				stc.impossibleCSV(sSavedData);	
				iTotal++;
				break;
				
			case "41":
				stc.impossibleCSV(sSavedData);	
				iTotal++;
				break;
				
			case "43":
				stc.impossibleCSV(sSavedData);	
				iTotal++;
				break;
				
			case "45":
				stc.impossibleCSV(sSavedData);		
				iTotal++;
				break;
				
			case "51":
				stc.impossibleCSV(sSavedData);		
				iTotal++;
				break;
				
			case "54":
				stc.impossibleCSV(sSavedData);	
				iTotal++;
				break;
		}
	}
}
