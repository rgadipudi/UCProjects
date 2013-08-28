package com.bentley.dt;

import java.io.File;
import java.io.IOException;

public class FileDirectory 
{
	static final String sSystemName = System.getProperty("user.name");;
	static final String sCreateFolder = "C:/Users/"+sSystemName+"/Ultraclenz";
	static final String sCreateFolderJaviTerm  = sCreateFolder+"/JaviTerm";
	static final String sCreateSubFolderJaviTerm = sCreateFolderJaviTerm+"/"+new TimeStamp().getDate();
	static final String sCreateFolderLoadAndRun = sCreateFolder+"/LoadAndRun";
	static String sCreateSubFolderLoadAndRun = sCreateFolderLoadAndRun+"/"+new TimeStamp().getDate();;
	MainPage window = null;
	
	public FileDirectory()
	{
	}
	
	public FileDirectory(MainPage window)
	{
		this.window=window;
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
	
	public void createSubJaviDirectory()
	{
		File fDir;
		
		fDir = new File(sCreateSubFolderJaviTerm);
		// if the directory does not exist, create it
		if (!fDir.exists()) 
		{
			fDir.mkdir();  
	  	}
	}
	
	public void deleteDirectory() 
	{
		File directory = new File(sCreateFolder);
		if(directory.exists())
		{
			
			try
			{
				delete(directory);
				window.jButton4.setEnabled(false);
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}
  	}
	public static void delete(File file)
	    	throws IOException{
	 
	    	if(file.isDirectory()){
	 
	    		//directory is empty, then delete it
	    		if(file.list().length==0){
	 
	    		   file.delete();
	    		  
	    		}else{
	 
	    		   //list all the directory contents
	        	   String files[] = file.list();
	 
	        	   for (String temp : files) {
	        	      //construct the file structure
	        	      File fileDelete = new File(file, temp);
	 
	        	      //recursive delete
	        	     delete(fileDelete);
	        	   }
	 
	        	   //check the directory again, if empty then delete it
	        	   if(file.list().length==0){
	           	     file.delete();
	        	   }
	    		}
	 
	    	}else{
	    		//if file, then delete it
	    		file.delete();
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
	
	public void createSubLoadAndRunDirectory()
	{
		File fDir;
		
		fDir = new File(sCreateSubFolderLoadAndRun);
		// if the directory does not exist, create it
		if (!fDir.exists()) 
		{
			fDir.mkdir();  
	  	}
	}
}
