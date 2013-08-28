package com.uc.dt;

import java.io.File;

public class FileDirectory 
{
	String sSystemName;
	String sCreateFolder;
	String sCreateFolderJaviTerm;
	String sCreateSubFolderJaviTerm;
	String sCreateFolderLoadAndRun;
	String sCreateSubFolderLoadAndRun;
	String sCreateFolderRealTerm;
	String sCreateSubFolderRealTerm;
	
	public FileDirectory()
	{
		sSystemName = System.getProperty("user.name");
		sCreateFolder = "C:/Users/"+sSystemName+"/Ultraclenz";
		sCreateFolderJaviTerm = sCreateFolder+"/JaviTerm";
		sCreateSubFolderJaviTerm = sCreateFolderJaviTerm+"/"+new TimeStamp().getDate();
		
		sCreateFolderLoadAndRun = sCreateFolder+"/LoadAndRun";
		sCreateSubFolderLoadAndRun = sCreateFolderLoadAndRun+"/"+new TimeStamp().getDate();
		
		sCreateFolderRealTerm = sCreateFolder+"/RealTerm";
		sCreateSubFolderRealTerm = sCreateFolderRealTerm+"/"+new TimeStamp().getDate();
	}
	
	public void createDirectory() 
	{
		File fDir;
		
		fDir = new File(sCreateFolder);
		// if the directory does not exist, create it
		if (!fDir.exists()) 
		{
			fDir.mkdir();  
	  	}
	}
	
	public void createJaviDirectory()
	{
		File fDir;
		
		fDir = new File(sCreateFolderJaviTerm);
		// if the directory does not exist, create it
		if (!fDir.exists()) 
		{
			fDir.mkdir();  
	  	}
	}
	
	public void createLoadAndRunDirectory()
	{
		File fDir;
		
		fDir = new File(sCreateFolderLoadAndRun);
		// if the directory does not exist, create it
		if (!fDir.exists()) 
		{
			fDir.mkdir();  
	  	}
	}
	
	public void createRealDirectory()
	{
		File fDir;
		
		fDir = new File(sCreateFolderRealTerm);
		// if the directory does not exist, create it
		if (!fDir.exists()) 
		{
			fDir.mkdir();  
	  	}
	}
}
