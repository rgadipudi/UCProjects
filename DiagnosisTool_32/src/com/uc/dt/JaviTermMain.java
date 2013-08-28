package com.uc.dt;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class JaviTermMain 
{
	JaviTerm window = null;
	SerialPort serialPort  = null;
	public JaviTermMain(JaviTerm window)
	{
		this.window=window;
	}
	
	JaviTermMain()
	{
		
	}
	
	void connect ( String portName) throws Exception
    {
		
    	// Connects to Specific port that specified below in Main method
        CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
        window.jTextArea1.append("Connected to Port: "+portName+"\n");
        
        // Exception is thrown if their is any other program is connect to it.
        if ( portIdentifier.isCurrentlyOwned() )
        {
        	window.jTextArea1.append("Error: Port is currently in use"+"\n");
        }
        else
        {
        	//This tells the port which software is connected to.
            CommPort commPort = portIdentifier.open("UltraClenz Gateway&Hub Diagnosis Software",2000);
            
            // Process goes thru if the connected port is Serial Port
            if ( commPort instanceof SerialPort )
            {
            	window.jTextArea1.append("Time: "+new TimeStamp().getFormatTime()+"\n");
            	window.jTextArea1.append("Enter '?' for Help"+"\n");
            	
            	//Serial Port Object Created for port settings
                serialPort = (SerialPort) commPort;
                
                //Port Settings Needs to be 57600,8 Bits, 1 stop bit, Parity None
                serialPort.setSerialPortParams(57600,SerialPort.DATABITS_8,SerialPort.STOPBITS_1,0);
               // serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_OUT);
                // Stream objects for IO
                InputStream in = serialPort.getInputStream();
                
                               
                // Below thread gives access to write to serial port
//(new Thread(new SerialWriter(out))).start();
                
                // Below Method handles the process of available data coming thru from the serial port. 
                serialPort.addEventListener(new SerialReader(in, window));
                
                serialPort.notifyOnDataAvailable(true);

            }
            else
            {
            	window.jTextArea1.append("Error: Only serial ports are handled by this software."+"\n");
            }
        }     
    }
	
	public void writer(String cmd) throws IOException
	{
		OutputStream out = serialPort.getOutputStream();
		(new Thread(new SerialWriter(out))).start();
	}
	
	public static class SerialReader implements SerialPortEventListener 
    {
        private InputStream in;
        private byte[] buffer = new byte[1024];
        JaviTerm window = null;
        public SerialReader ( InputStream in, JaviTerm window )
        {
        	this.window = window;
            this.in = in;
        }
        
        public void serialEvent(SerialPortEvent arg0) {
            int data;
          
            try
            {
                int len = 0;
                while ( ( data = in.read()) > -1 )
                {
                    if ( data == '\n' ) {
                        break;
                    }
                    buffer[len++] = (byte) data;
                }
                window.jTextArea1.append(new String(buffer,0,len)+"\n");
            }
            catch ( IOException e )
            {
                e.printStackTrace();
                System.exit(-1);
            }             
        }

    }

    /** */
    public class SerialWriter implements Runnable 
    {
        OutputStream out;
        
        public SerialWriter ( OutputStream out )
        {
            this.out = out;
        }
        
        public void run ()
        {
        	try
            {                
                int c = 0;
                for(byte b : window.jTextField6.getText().getBytes())
                {
                	c = (int) b;
                	if(c!=10)
                	{
                		this.out.write(c);
                	}
                }
                this.out.write(13);
            }
            catch ( IOException e )
            {
                e.printStackTrace();
            }            
        }
    }
	
   	final static int NEW_LINE_ASCII = 10;
    
   	// Inner Class: Reads data from COMM port
    public static class SerialReaderBit implements SerialPortEventListener 
    {
        private InputStream in;
        public SerialReaderBit ( InputStream in )
        {
            this.in = in;
        }
        String st="";
        public void serialEvent(SerialPortEvent arg0) 
        {
            
        	 byte singleData;
			try {
				singleData = (byte)in.read();
			
             if (singleData != NEW_LINE_ASCII)
             {
                 st = new String(new byte[] {singleData});
                 
             }
        	    System.out.print(st);
             } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    }
}
