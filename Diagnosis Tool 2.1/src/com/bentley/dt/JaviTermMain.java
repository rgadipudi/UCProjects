package com.bentley.dt;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import gnu.io.UnsupportedCommOperationException;

import java.awt.Color;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TooManyListenersException;

public class JaviTermMain 
{
	JaviTerm window = null;
	InputStream in;
	static SerialPort serialPort;
	static String sDataEvent = new TimeStamp().getFormatAMTime();
	final static int LOOP_DELAY = 1000; 
	final static int LOOP_DELAY_RESET = 1000*10;
	final static int iWaitTime = 1000*60*5;
	
	public JaviTermMain(JaviTerm window)
	{
		this.window=window;
	}
	
	JaviTermMain()
	{
		
	}
 
	void connect ( String portName) throws Exception
    {
		window.jTextArea1.append("Wait"+"\n");
    	// Connects to Specific port that specified below in Main method
        CommPortIdentifier portIdentifier;
		try {
			portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
			window.jTextArea1.append("Connected to Port: "+portName+"\n");
        
        // Exception is thrown if their is any other program is connect to it.
        if ( portIdentifier.isCurrentlyOwned() )
        {
        	window.jTextArea1.setForeground(Color.RED);
        	window.jTextArea1.append("Error: Port is currently in use"+"\n");
        }
        else
        {
        	//This tells the port which software is connected to.
            CommPort commPort = portIdentifier.open("Bentley Diagnosis Software",2000);
            
            // Process goes thru if the connected port is Serial Port
            if ( commPort instanceof SerialPort )
            {
            	window.jTextArea1.append("Time: "+new TimeStamp().getFormatTime()+"\n");
            	window.jTextArea1.append("Enter '?' for Help"+"\n");
            	//Serial Port Object Created for port settings
                serialPort = (SerialPort) commPort;
                
                //Port Settings Needs to be 57600,8 Bits, 1 stop bit, Parity None
                serialPort.setSerialPortParams(57600,SerialPort.DATABITS_8,SerialPort.STOPBITS_1,SerialPort.PARITY_NONE);

               // serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_OUT);
                // Stream objects for IO
                 in = serialPort.getInputStream();
                
                // Below Method handles the process of available data coming thru from the serial port. 
                
                serialPort.addEventListener(new SerialReader(in, window));
               
                writerCMD("m");
                
                try 
                { 
                	Thread.sleep(LOOP_DELAY); 
                }  
                catch ( InterruptedException e ) 
                {  
                }

                writerCMD("g");
                
                window.jButtonT4.setEnabled(true);
                serialPort.notifyOnDataAvailable(true);
                window.jButton1.setEnabled(false);
                window.jButtonT3.setEnabled(true);
                window.jButton4.setEnabled(true);
                window.jButton2.setEnabled(true);
            }
            else
            {
            	window.jTextArea1.setForeground(Color.RED);
            	window.jTextArea1.append("Error: Only serial ports are handled by this software."+"\n");
            	}
        } 
        } catch (NoSuchPortException e) {
        	window.jTextArea1.setForeground(Color.RED);
			window.jTextArea1.append("Please Check the port in driver manager. May requires to replug cable.\n");
				e.printStackTrace();
		} catch (PortInUseException e) {
			window.jTextArea1.setForeground(Color.RED);
			window.jTextArea1.append("Already Connected.\n");
			e.printStackTrace();
		} catch (UnsupportedCommOperationException e) {
			window.jTextArea1.setForeground(Color.RED);
			window.jTextArea1.append("Operation you trying to do is not allowed.\n");
			e.printStackTrace();
		} catch (IOException e) {
			window.jTextArea1.setForeground(Color.RED);
			window.jTextArea1.append("Input Error.\n");
			e.printStackTrace();
		} catch (TooManyListenersException e) {
			window.jTextArea1.setForeground(Color.RED);
			window.jTextArea1.setForeground(Color.BLACK);
			window.jTextArea1.append("Error! program already running.\n");
			e.printStackTrace();
		}
            
    }
	
	
	
	static OutputStream out;
	public void writer() throws IOException
	{
		
		try {
			out = serialPort.getOutputStream();
			(new Thread(new SerialWriter(out))).start();
		} catch (IOException e) {
			window.jTextArea1.setForeground(Color.RED);
			window.jTextArea1.append("Input Error.\n");
			e.printStackTrace();
		}
	}
	
	public void writerCMD(String cmd) throws IOException
	{
		try {
			out = serialPort.getOutputStream();
			(new Thread(new SerialWriterCMD(out,cmd))).start();
		} catch (IOException e) {
			window.jTextArea1.setForeground(Color.RED);
			window.jTextArea1.append("Input Error.\n");
			e.printStackTrace();
		}
		
	}
	static long start = System.currentTimeMillis();
    static long end = start + 1*1000; 
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
        
        @SuppressWarnings("deprecation")
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
                
                String incomingData = new String(buffer,0,len);
                
                sDataEvent = new TimeStamp().getFormatAMTime();
                
                if(incomingData.contains("Ultraclenz Gateway"))
                {
                	try { Thread.sleep(LOOP_DELAY_RESET); }  
                    catch ( InterruptedException e ) 
                    {  
                    }
                	new JaviTermMain().writerCMD("m");
                	
                	try { Thread.sleep(LOOP_DELAY); }  
                    catch ( InterruptedException e ) 
                    {  
                    }
                	new JaviTermMain().writerCMD("g");
                }
                
                if(incomingData.contains("Ultraclenz Hub"))
                {
                	try { Thread.sleep(LOOP_DELAY_RESET); }  
                    catch ( InterruptedException e ) 
                    {  
                    }
                	new JaviTermMain().writerCMD("m");
                	
                	try { Thread.sleep(LOOP_DELAY); }  
                    catch ( InterruptedException e ) 
                    {  
                    }
                	new JaviTermMain().writerCMD("g");
                }
                if(incomingData.contains("Connecting to backend..."))
                {
                	
                	window.jLabel16.setText("Connecting");
                }
                else if(incomingData.contains("Retrying..."))
                {
                	window.jLabel16.setText("Connecting");
                }
                else if(incomingData.contains("Connection failed."))
                {
                	window.jLabel16.setText("      Error!");
                }
                else if(incomingData.contains("Resetting backend connection."))
                {
                	window.jLabel16.setText("      Error!");
                }
                else if(incomingData.contains("Connected!"))
                {
                	window.jLabel16.setText("  Connected");
                }
                
                if(incomingData.contains("Monitor ON"))
                {
                	window.jButtonT3.setText("ON");
                }
                else if(incomingData.contains("Monitor OFF"))
                {
                	window.jButtonT3.setText("OFF");
                	new JaviTermMain().writerCMD("m");
                }
                if(incomingData.contains("Events ON"))
                {
                	window.jButtonT4.setText("ON");
                }
                else if(incomingData.contains("Events OFF"))
                {
                	window.jButtonT4.setText("OFF");
                	new JaviTermMain().writerCMD("g");
                }
                if(incomingData.contains("Invalid command.  Enter ? for more help."))
                {
                	try { Thread.sleep(LOOP_DELAY_RESET); }  
                    catch ( InterruptedException e ) 
                    {  
                    }
                	new JaviTermMain().writerCMD("m");
                	
                	try { Thread.sleep(LOOP_DELAY); }  
                    catch ( InterruptedException e ) 
                    {  
                    }
                	new JaviTermMain().writerCMD("g");
                }
                
                window.jTextArea1.setForeground(Color.BLACK);
                window.jTextArea1.append(incomingData+"\n");
                new CSVSaveData().sendtoCSV(incomingData);
                // CSV
            }
            catch ( IOException e )
            {
            	window.jTextArea1.setForeground(Color.RED);
    			window.jTextArea1.append("Input Error.\n");
                e.printStackTrace();
              
            }             
        }

    }
	
	public class SerialWriterCMD implements Runnable 
    {
        OutputStream outGateway;
        String sCMD = "";
        
        public SerialWriterCMD ( OutputStream out , String cmd) throws IOException
        {
        	sCMD = cmd;
            this.outGateway = out;
        }
        
        public void run ()
        {
        	try
            {                
                int c = 0;
                for(byte b : sCMD.getBytes())
                {
                	c = (int) b;
                	if(c!=10)
                	{
                		this.outGateway.write(c);
                	}
                	
                }
                this.outGateway.write(13);
            }
            catch ( IOException e )
            {
            	window.jTextArea1.setForeground(Color.RED);
    			window.jTextArea1.append("Input Error.\n");
    			
    			e.printStackTrace();
            }            
        }
    }
	
    /** */
    public class SerialWriter implements Runnable 
    {
        OutputStream outMain;
        
        public SerialWriter ( OutputStream out )
        {
            this.outMain = out;
        }
        
        public void run ()
        {
        	try
            {                
                int c = 0;
                
                for(byte b : window.jTextField7.getText().getBytes())
                {
                	c = (int) b;
                	
                	if(c!=10)
                	{
                		this.outMain.write(c);
                	}
                }
                this.outMain.write(13);
            }
            catch ( IOException e )
            {
            	window.jTextArea1.setForeground(Color.RED);
    			window.jTextArea1.append("Input Error.\n");
    			
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
             } catch (IOException e) 
             {
            	
				e.printStackTrace();
			}
		}
    }
    
    public void disconnect() throws IOException, InterruptedException {
    	if (serialPort != null) 
        {
    		serialPort.removeEventListener();
    		serialPort.getInputStream().close();
    		serialPort.getOutputStream().close();

    		serialPort.close();
    		
    		window.jButtonT4.setEnabled(false);
            window.jButton1.setEnabled(true);
            window.jButtonT3.setEnabled(false);
            window.jButton4.setEnabled(false);
            window.jButton2.setEnabled(false);
            
            window.jTextArea1.append("COMM port Closed.\n");
            
        }
    }
}
