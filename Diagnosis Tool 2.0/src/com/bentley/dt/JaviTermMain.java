package com.bentley.dt;

import java.awt.Color;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

public class JaviTermMain 
{
	JaviTerm window = null;
	SerialPort serialPort;
	final static int LOOP_DELAY = 1000; 
	final static int LOOP_DELAY_RESET = 1000*10*1;
	final static int iWaitTime = 1000*60*5;
	
	public JaviTermMain(JaviTerm window)
	{
		this.window=window;
	}
	
	JaviTermMain()
	{
	}
	
	void connect ( String portName, int bits, int dataBits, int stopBits, int parity ) throws Exception
    {
    	CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
    	window.jTextArea1.append("Connected to Port: "+portName+"\n");
        
        if ( portIdentifier.isCurrentlyOwned() )
        {
        	window.jTextArea1.setForeground(Color.RED);
        	window.jTextArea1.append("Error: Port is currently in use"+"\n");
        }
        else
        {
            CommPort commPort = portIdentifier.open("UltraClenz Gateway&Hub Diagnosis Software",2000);
            if ( commPort instanceof SerialPort )
            {
            	window.jTextArea1.append("Time: "+new TimeStamp().getFormatTime()+"\n");
            	window.jTextArea1.append("Enter '?' for Help"+"\n");
            	
                serialPort = (SerialPort) commPort;
                serialPort.setSerialPortParams(bits,dataBits,stopBits,parity);
               
                
                InputStream in = serialPort.getInputStream();
                
                serialPort.addEventListener(new SerialReader(in, window));
                
                window.jButtonT4.setEnabled(true);
                serialPort.notifyOnDataAvailable(true);
                window.jButton1.setEnabled(false);
                window.jButtonT3.setEnabled(true);
                window.jButton4.setEnabled(true);
                window.jButton2.setEnabled(true);
            }
            else
            {
                System.out.println("Error: Only serial ports are handled by this software.");
            }
        }     
    }
	
	public void writer() throws IOException
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

 		public void serialEvent(SerialPortEvent arg0) 
 		{
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
 				window.jTextArea1.append(incomingData+"\n");
 				System.out.println(incomingData);
 				
 				
 				
 				if(incomingData.contains("Ultraclenz Gateway"))
                {
                	new JaviTerm().Reset();
                }
                
                if(incomingData.contains("Ultraclenz Hub"))
                {
                	new JaviTerm().Reset();
                }
 				
 				
                if(incomingData.contains("Monitor ON"))
                {
                	window.jButtonT3.setText("ON");
                }
                else if(incomingData.contains("Monitor OFF"))
                {
                	window.jButtonT3.setText("OFF");
                }
                if(incomingData.contains("Events ON"))
                {
                	window.jButtonT4.setText("ON");
                }
                else if(incomingData.contains("Events OFF"))
                {
                	window.jButtonT4.setText("OFF");
                }
 				
 				
 				
 				
 				
 				
 				
 				
 			}
 			catch ( IOException e )
 			{
 				e.printStackTrace();
 				System.exit(-1);
 			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}             
 		}
 	}
 	
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
                for(byte b : window.jTextField7.getText().getBytes())
                {
                	int c = (int) b;
                	if(c!=10)
                	{
                		this.outMain.write(c);
                	}
                }
                this.outMain.write(13);
                this.outMain.flush();
            }
            catch ( IOException e )
            {
            	window.jTextArea1.setForeground(Color.RED);
    			window.jTextArea1.append("Input Error.\n");
    			try {
					new CSVSaveData().sendtoCSV("Input Error.");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
                e.printStackTrace();
            }            
        }
    }

 	public void disconnect() throws IOException 
 	{
 		if (serialPort != null) 
 		{
 			serialPort.close();
 			window.jTextArea1.append("COMM port Closed.\n");
 			window.jButton1.setEnabled(true);
 			window.jButtonT3.setEnabled(false);
 			window.jButtonT4.setEnabled(false);
			window.jButton4.setEnabled(false);
     	}
 	}
}
