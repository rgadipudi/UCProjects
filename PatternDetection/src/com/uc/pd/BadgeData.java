package com.uc.pd;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class BadgeData 
{
	// Saves the impossible compliance events in array list
	static List<String[]> laProcessedData = new ArrayList<String[]>();
	
	// Two array to compare each
	String[] sPEvent = new String[13]; 
	String[] sPrEvent = new String[13];
	DataFilter dt = new DataFilter();
	int iTotal = 0;
	int iTickCount = 0;
	int iCount = 0; 
	
	public int detectPattern(List<String[]> laSortData, SaveToCSV stc_sub, String sBadgeId) throws IOException 
	{
		stc_sub.sortCSV("All Events generated from Badge: "+sBadgeId);
		iTotal = 0;
		// Breaking down the array list and saving in arrays
		
		for(String[] sSortData : laSortData)
		{
			iTotal++;
			stc_sub.sortCSV(sSortData[0]+","+"_"+sSortData[1]+","+sSortData[2]+","+sSortData[3]+","+sSortData[4]+","+sSortData[5]+","+sSortData[6]+","+sSortData[7]+","+sSortData[8]+","+sSortData[9]+","+sSortData[10]+","+sSortData[11]+","+sSortData[12]);
			// Only the first event will passes this if stmt 
			if(iTickCount==0)
			{
				sPEvent=sSortData;
			}
			else
			{
				sPrEvent=sSortData;
			}
			
			// If its the first entry it waits until there is some data in available in second array
			if(iTickCount!=0)
			{
				// Check impossible event between two event data. If it returns true it increases the iCount
				if(detectPatternBTWTwoEvents(sPEvent,sPrEvent))
				{
					
					iCount++;
				}
				// Dump the 2nd array data into first like Fibonacci series 
				sPEvent = sPrEvent;	
			}
			iTickCount++;
		}
		
		stc_sub.sortCSV("Total Events: "+iTotal);
		new DataFilter().iTotal=iTotal;
		//Returns Count
		return iCount;
	}

	// Check for impossible compliance events
	private boolean detectPatternBTWTwoEvents(String[] sPEvent, String[] sPrEvent) 
	{
		switch(sPEvent[12]+sPrEvent[11])
		{
			case "00":
				return false;
			case "04":
				return false;
			case "11":
				return false;
			case "15":
				return false;
			case "22":
				return false;
			case "33":
				return false;
			case "44":
				return false;
			case "55":
				return false;
			default:
				if(sPEvent[2].equalsIgnoreCase(sPrEvent[2]))
				{
					switch(sPrEvent[12]+sPEvent[11])
					{
						case "00":
							return false;
						case "04":
							return false;
						case "11":
							return false;
						case "15":
							return false;
						case "22":
							return false;
						case "33":
							return false;
						case "44":
							return false;
						case "55":
							return false;
						default:
							laProcessedData.add(sPEvent);
							laProcessedData.add(sPrEvent);
							dt.filterDataIntoPockets(sPEvent,sPrEvent);
							return true;
					}
				}
				else
				{
					// If it is impossible save the data in to array list
					laProcessedData.add(sPEvent);
					laProcessedData.add(sPrEvent);
					dt.filterDataIntoPockets(sPEvent,sPrEvent);
					return true;
				}
		}
	}

	// For printing the data set 
	public void printData(SaveToCSV stc_sub, String sBadgeId, int iDate) throws IOException
	{
		
			int iLineCount = 0;
			
			for(String[] sPrintData : laProcessedData)
			{
				// if statement for printing line between each data set
				if(iLineCount%2 == 0)
				{
					stc_sub.detailCSV("----------------------------------------------------------------");
				}
				// Prints Needed Data
				stc_sub.detailCSV(sPrintData[1]+"|"+sPrintData[2]+"|"+sPrintData[11]+"|"+sPrintData[12]);
				iLineCount++;
			}
			
			stc_sub.detailCSV("----------------------------------------------------------------");
			
			// Prints the total impossible compliance for a badge
			double percent = ((laProcessedData.size()/2)*100.0f)/iTotal;
			DecimalFormat newFormat = new DecimalFormat("#.##");
			
			double twoDecimalPercent =  Double.valueOf(newFormat.format(percent));
			String per = String.valueOf(twoDecimalPercent);
			
			stc_sub.detailCSV("Impossible Compliance Count: "+laProcessedData.size()/2);
			stc_sub.detailCSV("Percentage: "+percent+"%");
			stc_sub.detailCSV("Total Events: "+iTotal);
			
			stc_sub.reportCSV("Impossible Compliance Count: "+laProcessedData.size()/2);
			stc_sub.reportCSV("Percentage: "+percent+"%");
			stc_sub.reportCSV("Total Events: "+iTotal);
			
			new GenerateReport().report(sBadgeId, per+"%/"+laProcessedData.size()/2+"-"+iTotal+new DataFilter().sPercent, iDate, laProcessedData.size()/2, iTotal);
			// Deletes all saved data from array list 
			laProcessedData.clear();
	}
	
	public void printNonData(SaveToCSV stc_sub, String sBadgeId, int iDate) throws IOException 
	{
		stc_sub.reportCSV("Impossible Compliance Count: 0");
		stc_sub.reportCSV("Percentage: 0% ");
		stc_sub.reportCSV("Total Events: "+iTotal);
		new GenerateReport().report(sBadgeId, "0%/0-"+iTotal, iDate, 0, iTotal);
		laProcessedData.clear();
	}
}
