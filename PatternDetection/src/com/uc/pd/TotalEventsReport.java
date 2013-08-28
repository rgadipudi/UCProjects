package com.uc.pd;

import java.util.ArrayList;
import java.util.List;

public class TotalEventsReport 
{
	// Created list to add number of events happened and patterns occurred
	static List<String[]> laCountData = new ArrayList<String[]>();
	static List<String[]> laTotalData = new ArrayList<String[]>();
    
	// Adds the events and total number to list
	void addEvents(String badgeId, int count, int total) 
	{
		String[] sCount = new String[2];
		sCount[0]=badgeId;
		sCount[1]=String.valueOf(count);
		laCountData.add(sCount);
		
		String[] sTotal = new String[2];
		sTotal[0]=badgeId;
		sTotal[1]=String.valueOf(total);
		laTotalData.add(sTotal);
	}
	
	// Prints Total Count
	int printEventsCount(String badgeId)
	{
		int iData = 0;
		for(String[] sTemp : laCountData)
		{
			if(sTemp[0].equalsIgnoreCase(badgeId))
			{
				iData += Integer.parseInt(sTemp[1]);
			}
		}
		return iData;
	}
	
	// Prints total events
	int printEventsTotal(String badgeId)
	{
		int iData = 0;
		for(String[] sTemp : laTotalData)
		{
			if(sTemp[0].equalsIgnoreCase(badgeId))
			{
				iData += Integer.parseInt(sTemp[1]);
			}
		}
		return iData;
	}
}
