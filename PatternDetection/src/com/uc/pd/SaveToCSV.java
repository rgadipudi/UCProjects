package com.uc.pd;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SaveToCSV 
{
	String sFolderName;
	String sCreateFolder = "/Results";
	File fDir;
	String sFileNameFolder;
	
	// Counter
	int iImpossibleCount = 0;
	int iSortFileCount = 0;
	int iReportFileCount = 0;
	int iDetailFileCount = 0;
	int iTotalDetailFileCount = 0;
	int iFilter1Count = 0;
	int iFilter2Count = 0;
	int iFilter3Count = 0;
	
	// File names
	String sFileImpossible = "_ImpossibleEvents.csv";
	String sFileSort = "_DataSort.csv";
	String sFileReport = "_BriefReport.csv";
	String sFileDetail = "_DetailReport.csv";
	String sFileFilter1 = "_Bucket_HF.csv";
	String sFileFilter2 = "_Bucket_Non_Dispensing.csv";
	String sFileFilter3 = "_Bucket_MissingEvents.csv";
 	String sFileTotalDetail = "Report.csv";
	
	SaveToCSV(String folderName, int i)
	{
		sFolderName = folderName;
		makeFolders(sFolderName+sCreateFolder);
	}
	
	SaveToCSV(String folderName)
	{
		sFolderName = folderName;
	}
	
	SaveToCSV(String folderName, String fileName)
	{
		sFolderName = folderName+sCreateFolder+"/";
		sFileNameFolder = fileName;
		makeFolders(sFolderName+sFileNameFolder);
	}
	
	// Creates the folders
	void makeFolders(String sFileName) {
		fDir = new File(sFileName);
		// if the directory does not exist, create it
		if (!fDir.exists()) 
		{
			fDir.mkdir();  
	  	}
	}

	void impossibleCSV(String data) throws IOException 
	{
		// deletes the sort file if exists
		if(iImpossibleCount == 0)
		{
			File fileTemp = new File(sFolderName+sFileNameFolder+"/"+sFileNameFolder+sFileImpossible);
			if (fileTemp.exists()){
			    fileTemp.delete();
			}
			iImpossibleCount++;
		}
		
		// creates a new sort file
		FileWriter writer = new FileWriter(sFolderName+sFileNameFolder+"/"+sFileNameFolder+sFileImpossible, true);
		// write data in to sort file
		writer.append(data+"\n");
		writer.flush();
	    writer.close();
	}
	
	// Write in to sort file
	void sortCSV(String data) throws IOException 
	{
		// deletes the sort file if exists
		if(iSortFileCount == 0)
		{
			File fileTemp = new File(sFolderName+sFileNameFolder+"/"+sFileNameFolder+sFileSort);
			if (fileTemp.exists()){
			    fileTemp.delete();
			}
			iSortFileCount++;
		}
		
		// creates a new sort file
		FileWriter writer = new FileWriter(sFolderName+sFileNameFolder+"/"+sFileNameFolder+sFileSort, true);
		// write data in to sort file
		writer.append(data+"\n");
		writer.flush();
	    writer.close();
	}
	
	void bucket1CSV(String data) throws IOException 
	{
		// deletes the sort file if exists
		if(iFilter1Count == 0)
		{
			File fileTemp = new File(sFolderName+sFileNameFolder+"/"+sFileNameFolder+sFileFilter1);
			if (fileTemp.exists()){
			    fileTemp.delete();
			}
			iFilter1Count++;
		}
		
		// creates a new sort file
		FileWriter writer = new FileWriter(sFolderName+sFileNameFolder+"/"+sFileNameFolder+sFileFilter1, true);
		// write data in to sort file
		writer.append(data+"\n");
		writer.flush();
	    writer.close();
	}
	
	void bucket2CSV(String data) throws IOException 
	{
		// deletes the sort file if exists
		if(iFilter2Count == 0)
		{
			File fileTemp = new File(sFolderName+sFileNameFolder+"/"+sFileNameFolder+sFileFilter2);
			if (fileTemp.exists()){
			    fileTemp.delete();
			}
			iFilter2Count++;
		}
		
		// creates a new sort file
		FileWriter writer = new FileWriter(sFolderName+sFileNameFolder+"/"+sFileNameFolder+sFileFilter2, true);
		// write data in to sort file
		writer.append(data+"\n");
		writer.flush();
	    writer.close();
	}
	
	void bucket3CSV(String data) throws IOException 
	{
		// deletes the sort file if exists
		if(iFilter3Count == 0)
		{
			File fileTemp = new File(sFolderName+sFileNameFolder+"/"+sFileNameFolder+sFileFilter3);
			if (fileTemp.exists()){
			    fileTemp.delete();
			}
			iFilter3Count++;
		}
		
		// creates a new sort file
		FileWriter writer = new FileWriter(sFolderName+sFileNameFolder+"/"+sFileNameFolder+sFileFilter3, true);
		// write data in to sort file
		writer.append(data+"\n");
		writer.flush();
	    writer.close();
	}
	
	// Write in to report file
	void reportCSV(String data) throws IOException 
	{
		// deletes the report file if exists
		if(iReportFileCount == 0)
		{
			File fileTemp = new File(sFolderName+sFileNameFolder+"/"+sFileNameFolder+sFileReport);
			if (fileTemp.exists()){
			    fileTemp.delete();
			}
			iReportFileCount++;
		}
		
		// creates a new report file
		FileWriter writer = new FileWriter(sFolderName+sFileNameFolder+"/"+sFileNameFolder+sFileReport, true);
		// write data in to report file
		writer.append(data+"\n");
		writer.flush();
	    writer.close();
	}
	
	// Write in to detail file
	void detailCSV(String data) throws IOException 
	{
		// deletes the detail file if exists
		if(iDetailFileCount == 0)
		{
			File fileTemp = new File(sFolderName+sFileNameFolder+"/"+sFileNameFolder+sFileDetail);
			if (fileTemp.exists()){
			    fileTemp.delete();
			}
			iDetailFileCount++;
		}
		
		// creates a new detail file
		FileWriter writer = new FileWriter(sFolderName+sFileNameFolder+"/"+sFileNameFolder+sFileDetail, true);
		// write data in to detail file
		writer.append(data+"\n");
		writer.flush();
	    writer.close();
	}
	
	// Write in to end report file
	void totalReport(String data) throws IOException
	{
		// deletes the detail file if exists
				if(iTotalDetailFileCount == 0)
				{
					File fileTemp = new File(sFolderName+sFileTotalDetail);
					if (fileTemp.exists()){
					    fileTemp.delete();
					}
					iTotalDetailFileCount++;
				}
				
				// creates a new detail file
				FileWriter writer = new FileWriter(sFolderName+sFileTotalDetail, true);
				// write data in to detail file
				writer.append(data+"\n");
				writer.flush();
			    writer.close();
	}
}
