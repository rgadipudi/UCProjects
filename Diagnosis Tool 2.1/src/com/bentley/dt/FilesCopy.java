package com.bentley.dt;

import java.io.File;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class FilesCopy {

	public void moveFiles(String folderName, JPanel jPanel1) 
	{
		File MoveFile1 = new File("rxtxSerial.dll");
		File MoveFile2 = new File("rxtxParallel.dll");
		File MoveFile3 = new File("RXTXcomm.jar");
    	if(!MoveFile1.renameTo(new File( folderName+"/bin/"+MoveFile1.getName())))
    	{
    		JOptionPane.showMessageDialog(jPanel1, "Run as admin");
    		System.exit(-1);
    	}
    	if(!MoveFile2.renameTo(new File( folderName+"/bin/"+MoveFile2.getName())))
    	{
    		JOptionPane.showMessageDialog(jPanel1, "Run as admin");
    		System.exit(-1);
    	}
    	if(!MoveFile3.renameTo(new File( folderName+"/lib/ext/"+MoveFile3.getName())))
    	{
    		JOptionPane.showMessageDialog(jPanel1, "Run as admin");
    		System.exit(-1);
    	}
	}

}
