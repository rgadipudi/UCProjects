package com.bentley.dt;

import java.io.File;

import javax.swing.JOptionPane;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rgadipudi
 */
public class MainPage extends javax.swing.JFrame {

    /**
     * Creates new form MainPage
     */
    public MainPage() {
    	super("Bentley Diagnosis Tool V2.0");
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setPreferredSize(new java.awt.Dimension(625, 575));
        setResizable(false);
        
        String javaFolder = "C:/Program Files (x86)/Java/";
        
        File folder = new File(javaFolder);		// Loads the folder 
		File[] listOfFiles = folder.listFiles();
        int i = 0;
		for(File file : listOfFiles)
		{
			if(file.isDirectory())
			{
				
				if(file.getName().contains("jre"))
				{
					i++;
					//new FilesCopy().moveFiles(javaFolder+file.getName(), jPanel1);					// Commented out for admin error
				}
				if(file.getName().contains("jdk"))
				{
					i++;
					//new FilesCopy().moveFiles(javaFolder+file.getName()+"/jre", jPanel1);				// commented out for admin error
				}
			}
		}
        if(i==0)
        {
        	JOptionPane.showMessageDialog(jPanel1, "System Requires a 32-bit JRE 1.6+");
        	System.exit(-1);
        }

        jButton1.setText("RUN JAVI TERM");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("LOAD & RUN CSV");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Search");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Clear Previous Data");
        File folderExisting = new File(sCreateFolder);  
        if (!folderExisting.exists()){  
        	jButton4.setEnabled(false);
        }
        else
        {
        	jButton4.addActionListener(new java.awt.event.ActionListener() {
        		public void actionPerformed(java.awt.event.ActionEvent evt) {
        			jButton4ActionPerformed(evt);
        		}
        	});
        }
        
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(120, 120, 120)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(121, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(69, 69, 69)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(85, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Bookman Old Style", 2, 36)); // NOI18N
        jLabel1.setText("Bentley Diagnosis Tool");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(40, 40, 40)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>                        

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) 
    {                                         
        dispose();
        new FileDirectory().createDirectory();
        new JaviTerm().setVisible(true);
    }                                        

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) 
    {                                         
        
    }                                        

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        
    }                                        

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) 
    {                                         
    	new FileDirectory().deleteDirectory();
    }                                        

    /**
     * @param args the command line arguments
     * @throws Exception 
     */
    public static void main(String args[]) throws Exception {
    	sSystemName = System.getProperty("user.name");
		sCreateFolder = "C:/Users/"+sSystemName+"/Ultraclenz";
    	
		/* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            	
                new MainPage().setVisible(true);
            }
        });
    }

	// Variables declaration - do not modify                     
    javax.swing.JButton jButton1;
    javax.swing.JButton jButton2;
    javax.swing.JButton jButton3;
    static javax.swing.JButton jButton4;
    javax.swing.JLabel jLabel1;
    javax.swing.JPanel jPanel1;
    static String sSystemName;
	static String sCreateFolder;
    // End of variables declaration                   
}
