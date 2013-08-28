package com.uc.pd;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class DataFilter 
{
	static String sPercent = "";
	static List<String[]> laFilter1Data = new ArrayList<String[]>();
	static List<String[]> laFilter2Data = new ArrayList<String[]>();
	static List<String[]> laFilter3Data = new ArrayList<String[]>();
	static int iFilter1Count = 0;
	static int iFilter2Count = 0;
	static int iFilter3Count = 0;
	static int iTotal = 0;
	
	public void filterDataIntoPockets(String[] sPEvent, String[] sPrEvent) 
	{
		if(sPEvent[1].equalsIgnoreCase(sPrEvent[1]))
		{
			if(sPEvent[11].equalsIgnoreCase(sPrEvent[11]) && sPEvent[12].equalsIgnoreCase(sPrEvent[12]))
			{
				iFilter1Count++;
				laFilter1Data.add(sPEvent);
				laFilter1Data.add(sPrEvent);
			}
		}
		else if(sPEvent[11].equalsIgnoreCase(sPrEvent[11]))
		{
			if(!sPEvent[1].equalsIgnoreCase(sPrEvent[1]) && !"0".equalsIgnoreCase(sPrEvent[12]))
			{
				iFilter2Count++;
				laFilter2Data.add(sPEvent);
				laFilter2Data.add(sPrEvent);
			}
			else
			{
				iFilter3Count++;
				laFilter3Data.add(sPEvent);
				laFilter3Data.add(sPrEvent);
			}
		}
		else
		{
			iFilter3Count++;
			laFilter3Data.add(sPEvent);
			laFilter3Data.add(sPrEvent);
		}
		
		
	}

	public void printData(SaveToCSV stc_sub, String sBadgeId, int iDate) throws IOException 
	{
		int iLine1Count = 0;
		
		for(String[] sPrintData : laFilter1Data)
		{
			// if statement for printing line between each data set
			if(iLine1Count%2 == 0)
			{
				stc_sub.bucket1CSV("----------------------------------------------------------------");
			}
			// Prints Needed Data
			stc_sub.bucket1CSV(sPrintData[1]+"|"+sPrintData[2]+"|"+sPrintData[11]+"|"+sPrintData[12]);
			iLine1Count++;
		}
		
		stc_sub.bucket1CSV("----------------------------------------------------------------");
		
		int iLine2Count = 0;
		
		for(String[] sPrintData : laFilter2Data)
		{
			// if statement for printing line between each data set
			if(iLine2Count%2 == 0)
			{
				stc_sub.bucket2CSV("----------------------------------------------------------------");
			}
			// Prints Needed Data
			stc_sub.bucket2CSV(sPrintData[1]+"|"+sPrintData[2]+"|"+sPrintData[11]+"|"+sPrintData[12]);
			iLine2Count++;
		}
		
		stc_sub.bucket2CSV("----------------------------------------------------------------");
		
		int iLine3Count = 0;
		
		for(String[] sPrintData : laFilter3Data)
		{
			// if statement for printing line between each data set
			if(iLine3Count%2 == 0)
			{
				stc_sub.bucket3CSV("----------------------------------------------------------------");
			}
			// Prints Needed Data
			stc_sub.bucket3CSV(sPrintData[1]+"|"+sPrintData[2]+"|"+sPrintData[11]+"|"+sPrintData[12]);
			iLine3Count++;
		}
		
		stc_sub.bucket3CSV("----------------------------------------------------------------");
		
		
		String sFilter1Per = "0";
		String sFilter2Per = "0";
		String sFilter3Per = "0";
		
		if(laFilter1Data.size()!=0)
		{
			stc_sub.bucket1CSV("Impossible Compliance Count Filter 1: "+laFilter1Data.size()/2);
			stc_sub.bucket1CSV("Percentage: "+percentCaculate(laFilter1Data.size())+"%");
			sFilter1Per = percentCaculate(laFilter1Data.size());
			stc_sub.bucket1CSV("Total Events: "+iTotal);
		}
		else
		{
			stc_sub.bucket1CSV("Impossible Compliance Count Filter 1: "+laFilter1Data.size()/2);
			stc_sub.bucket1CSV("Percentage: 0%");
			stc_sub.bucket1CSV("Total Events: "+iTotal);
		}
		
		if(laFilter2Data.size()!=0)
		{
			stc_sub.bucket2CSV("Impossible Compliance Count Filter 2: "+laFilter2Data.size()/2);
			stc_sub.bucket2CSV("Percentage: "+percentCaculate(laFilter2Data.size())+"%");
			sFilter2Per = percentCaculate(laFilter2Data.size());
			stc_sub.bucket2CSV("Total Events: "+iTotal);
		}
		else
		{
			stc_sub.bucket2CSV("Impossible Compliance Count Filter 2: "+laFilter2Data.size()/2);
			stc_sub.bucket2CSV("Percentage: 0%");
			stc_sub.bucket2CSV("Total Events: "+iTotal);
		}
		
		if(laFilter3Data.size()!=0)
		{
			stc_sub.bucket3CSV("Impossible Compliance Count Filter 3: "+laFilter3Data.size()/2);
			stc_sub.bucket3CSV("Percentage: "+percentCaculate(laFilter3Data.size())+"%");
			sFilter3Per = percentCaculate(laFilter3Data.size());
			stc_sub.bucket3CSV("Total Events: "+iTotal);
		}
		else
		{
			stc_sub.bucket3CSV("Impossible Compliance Count Filter 3: "+laFilter3Data.size()/2);
			stc_sub.bucket3CSV("Percentage: 0%");
			stc_sub.bucket3CSV("Total Events: "+iTotal);
		}
		
		sPercent = "|"+sFilter1Per+"%|"+sFilter2Per+"%|"+sFilter3Per+"%";
		
	}
	
	public String percentCaculate(int size)
	{
		double percent = ((size/2)*100.0f)/iTotal;
		DecimalFormat newFormat = new DecimalFormat("#.##");
		
		double twoDecimalPercent =  Double.valueOf(newFormat.format(percent));
		String per = String.valueOf(twoDecimalPercent);
		
		return per;
	}
	
	public void clearArrayBuff()
	{
		laFilter1Data.clear();
		laFilter2Data.clear();
		laFilter3Data.clear();
	}
}
