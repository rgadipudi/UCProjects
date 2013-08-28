package com.uc.gm;

import java.util.Scanner;

public class SelectOptions {

	public int makeSelection() 
	{
		int iSelection = 0;
		System.out.println("1. Run Using Javi Term");
		System.out.println("2. Run Using RealTerm");
		System.out.println("3. Load a File");
		System.out.println("Please Enter your selection below.");
		Scanner scanner = new Scanner(System.in);
	    String sSelect = scanner.nextLine();
	    
	    switch(sSelect)
	    {
	    case "1":
	    	System.out.println("Your Selection is 1");
	    	return 1;
	    case "2":
	    	System.out.println("your Selection is 2");
	    	return 2;
	    case "3":
	    	System.out.println("Your Selection is 3");
	    	return 3;
	    default:
	    	System.err.println("Error! Please Make you again");
	    	makeSelection();
	    }
	    return iSelection;
	}

}
