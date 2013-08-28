package com.uc.gm;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class JaviTerm 
{
	static int writeReset = 0;
    public JaviTerm()
    {
        super();
    }
	public void StartJavaTerminal() 
	{
		// TODO Auto-generated method stub
		
	}
	// Prints the available list of ports for System
    static void listPorts()
    {
        java.util.Enumeration<CommPortIdentifier> portEnum = CommPortIdentifier.getPortIdentifiers();
        System.out.println("Available Ports: ");
        while ( portEnum.hasMoreElements() ) 
        {
        	// Identifies the available port number and its type
            CommPortIdentifier portIdentifier = portEnum.nextElement();
            
            System.out.println(portIdentifier.getName()  +  " - " +  getPortTypeName(portIdentifier.getPortType()) );
        }        
    }
    //Returns Port Type.
    static String getPortTypeName ( int portType )
    {
        switch ( portType )
        {
            case CommPortIdentifier.PORT_I2C:
                return "I2C";
            case CommPortIdentifier.PORT_PARALLEL:
                return "Parallel";
            case CommPortIdentifier.PORT_RAW:
                return "Raw";
            case CommPortIdentifier.PORT_RS485:
                return "RS485";
            // We need Serial Port
            case CommPortIdentifier.PORT_SERIAL:
                return "Serial";
            default:
                return "unknown type";
        }
    }
}
