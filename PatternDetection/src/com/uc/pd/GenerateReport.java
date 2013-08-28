package com.uc.pd;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

// Generates Report
public class GenerateReport 
{
	SaveToCSV stc;
	TotalEventsReport ter = new TotalEventsReport();
	
	static List<String[]> laReportData = new ArrayList<String[]>();
	static HashSet<String> sUniqueSet = new HashSet<String>();
	// Constructor to get object for other class
	
	public GenerateReport(SaveToCSV stc) 
	{
		this.stc=stc;
	}
	
	// Constructor to start an xml file 
	GenerateReport()
	{
		
	}

	void report(String badgeId, String value, int iDate, int Count, int Total) throws IOException
	{
		// Adds total Event count and impossible compliance pattern count
		ter.addEvents(badgeId, Count, Total);
		
		String[] data = new String[3];
		data[0]=badgeId;
		data[1]=String.valueOf(iDate);
		data[2]=value;
		// Added Data to both lists
	
		laReportData.add(data);
		sUniqueSet.add(data[0]);
			
	}
	
	void printReport() throws IOException
	{
		// Prints Top line of Reports CSV File
        stc.totalReport("BadgeId,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31, Total Count, Total Events, Percent");
        
        // For loop to print the data
    	for(String sData : sUniqueSet)
    	{
    		// Full month calendar array first index with badge id
    		String[] sReport = new String[35];
    		sReport[0] = sData;
    		
    		// Initially Everything will saves with zero
    		for(int i = 1; i<=31; i++)
    		{
    			sReport[i] = "0";
    		}
    		
    		// Break downs the laReportData array
    		for(String[] sTemp : laReportData)
    		{
    			if(sData.equalsIgnoreCase(sTemp[0]))
    			{
    				int iDate = Integer.parseInt(sTemp[1]);
    				sReport[iDate]=sTemp[2];
    			}
    		}
    	
    		long count = ter.printEventsCount(sData);
    		long total = ter.printEventsTotal(sData);
    		
    		sReport[32]=String.valueOf(count);
    		sReport[33]=String.valueOf(total);
    		
    		// Calculating percentage
    		double percent = ((count)*100.0)/total;
    		DecimalFormat newFormat = new DecimalFormat("#.##");
    		double twoDecimalPercent =  Double.valueOf(newFormat.format(percent));
    		sReport[34] = String.valueOf(twoDecimalPercent)+"%";
    		

    		// Changes the array in to CSV comma friendly format
    		String sSaveTemp="";
    		for(String s : sReport)
    		{
    			if(s.equalsIgnoreCase("0"))
    			{
    				s="";
    			}
    			sSaveTemp += s + "," ;
    		}
    		// Prints in to Report file
    		stc.totalReport(sSaveTemp);
    	}
	}
}
