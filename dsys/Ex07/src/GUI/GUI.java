package GUI;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import p2p.RemoteFISI;

import p2p.Client;
import p2p.ClientI;
import p2p.ClientIImpl;
import p2p.GlobalFileInfo;

public class GUI extends javax.swing.JFrame {
    private static final long serialVersionUID = -1676972555985017191L;
	private DefaultListModel<GlobalFileInfo> listmodel; //model for JList
    private Vector<JComponent> componentsToEnable; //components which should be enabled when logged on
    private Vector<JComponent> componentsToDisable; //components which should be disabled when logged on
    private boolean loggedOn = false; //state of loggedOn
    private int noUsers = 0; //number of users online
    private int noFiles = 0; //number of files online
    private String DELIM;
    private Client client;
    
    /** Creates new form GUI */
    public GUI(Client client) {
        initComponents();
        
        this.client = client;
        
        componentsToEnable = new Vector<JComponent>();
        componentsToEnable.add(bLogout);
        componentsToEnable.add(bdownload);
        componentsToEnable.add(binfo);
        componentsToEnable.add(tquery);
        componentsToEnable.add(lquery);
        componentsToEnable.add(bgo);
        componentsToEnable.add(lresults);
        
        componentsToDisable = new Vector<JComponent>();
        componentsToDisable.add(tserver);
        componentsToDisable.add(tusername);
        componentsToDisable.add(tpassword);
        componentsToDisable.add(tshareddir);
        componentsToDisable.add(bchoose);
        componentsToDisable.add(bLogin);
        
        if (System.getProperty("os.name").equals("Linux")) {
            topenCommand.setText("firefox");
            DELIM = "/";
        }
        else if (System.getProperty("os.name").equals("Windows")) {
            topenCommand.setText("cmd /c start");
            DELIM = "\\";
        }   
    }
    
    public GUI(Client client, String username, String password, String filepath)
    {
    	this(client);
    	tusername.setText(username);
    	tpassword.setText(password);
    	tshareddir.setText(filepath);
    }
    
    /** Set logon state */
    public void setLoggedOn(boolean val)
    {
        this.loggedOn = val;
        
        if (loggedOn) {
            for(JComponent c : componentsToEnable) c.setEnabled(true);
            for(JComponent c : componentsToDisable) c.setEnabled(false);
        } else {
            for(JComponent c : componentsToEnable) c.setEnabled(false);
            for(JComponent c : componentsToDisable) c.setEnabled(true);
        }
        
        tstate.setText(noUsers + " users and " + noFiles + " files online!");
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        tquery = new javax.swing.JTextField();
        lquery = new javax.swing.JLabel();
        bgo = new javax.swing.JButton();
        bdownload = new javax.swing.JButton();
        pdownloadprogress = new javax.swing.JProgressBar();
        jScrollPane1 = new javax.swing.JScrollPane();
        listmodel = new DefaultListModel<GlobalFileInfo>();
        lresults = new javax.swing.JList<GlobalFileInfo>(listmodel);
        binfo = new javax.swing.JButton();
        bLogin = new javax.swing.JButton();
        bLogout = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        tstate = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        topenCommand = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        tshareddir = new javax.swing.JTextField();
        tpassword = new javax.swing.JPasswordField();
        tusername = new javax.swing.JTextField();
        bchoose = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        tserver = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        tquery.setEnabled(false);

        lquery.setText("Query:");
        lquery.setEnabled(false);

        bgo.setText("Go");
        bgo.setEnabled(false);
        bgo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bgoActionPerformed(evt);
            }
        });

        bdownload.setText("Download and Open");
        bdownload.setEnabled(false);
        bdownload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bdownloadActionPerformed(evt);
            }
        });

        lresults.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        lresults.setEnabled(false);
        jScrollPane1.setViewportView(lresults);

        binfo.setText("Info");
        binfo.setEnabled(false);
        binfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                binfoActionPerformed(evt);
            }
        });

        bLogin.setText("Login");
        bLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bLoginActionPerformed(evt);
            }
        });

        bLogout.setText("Logout");
        bLogout.setEnabled(false);
        bLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bLogoutActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 24));
        jLabel2.setText("OSN P2P Client v 1.0");

        tstate.setBackground(new java.awt.Color(204, 204, 204));
        tstate.setEditable(false);

        jLabel1.setText("open with:");

        jLabel3.setText("Benutzername:");

        jLabel4.setText("Passwort:");

        jLabel5.setText("Geteiltes Verzeichnis:");

        bchoose.setText("...");
        bchoose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bchooseActionPerformed(evt);
            }
        });

        jLabel6.setText("Server:");

        tserver.setText("localhost");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(286, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(268, 268, 268))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 389, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(137, 137, 137)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(tusername, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(tserver, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                                        .addComponent(tpassword, javax.swing.GroupLayout.Alignment.LEADING))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(tshareddir, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(bchoose))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 316, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(bLogin, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bLogout, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 785, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(93, 93, 93)
                        .addComponent(lquery))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addComponent(jLabel1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(topenCommand)
                    .addComponent(jScrollPane1)
                    .addComponent(tquery, javax.swing.GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE))
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pdownloadprogress, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bgo)
                            .addComponent(binfo))
                        .addGap(110, 110, 110))
                    .addComponent(bdownload, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE))
                .addGap(51, 51, 51))
            .addComponent(tstate, javax.swing.GroupLayout.DEFAULT_SIZE, 809, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bLogin)
                    .addComponent(jLabel6)
                    .addComponent(tserver, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bLogout)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(tusername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(tpassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(tshareddir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bchoose))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lquery)
                            .addComponent(tquery, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(59, 59, 59)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(topenCommand, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bgo)
                        .addGap(18, 18, 18)
                        .addComponent(pdownloadprogress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(bdownload)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(binfo)))
                .addGap(24, 24, 24)
                .addComponent(tstate, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents


    /** Code performed when the File-Choose button has been pressed */
    private void bchooseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bchooseActionPerformed
        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            tshareddir.setText(file.getAbsolutePath());
        }
    }//GEN-LAST:event_bchooseActionPerformed

    /** Code performed when logout button has been pressed */
    private void bLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bLogoutActionPerformed

        if (client.logout()) {
        	this.setLoggedOn(false);
        } else {
        	JOptionPane.showMessageDialog(this, "Sorry, cannot log out! Please try again!");
        	this.setLoggedOn(true);
        }
        
    }//GEN-LAST:event_bLogoutActionPerformed

    /** Code performed when login button has been pressed */
    private void bLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bLoginActionPerformed

    	ClientIImpl clientiimpl;
		try {
			clientiimpl = new ClientIImpl(tshareddir.getText(), tusername.getText());			
			client.setClientIImpl(clientiimpl);
	    	
	        if (!client.login(tusername.getText(), new String(tpassword.getPassword()), tserver.getText())) {
	        	JOptionPane.showMessageDialog(this, "Sorry, cannot log on! Please try again!");
	        	this.setLoggedOn(false);
	        } else {
	        	this.setLoggedOn(true);
	        }
		} catch (RemoteException e) {
			e.printStackTrace();
		}
        
    }//GEN-LAST:event_bLoginActionPerformed

    /** Code performed when info button has been pressed */
    private void binfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_binfoActionPerformed

    	GlobalFileInfo gfi = (GlobalFileInfo)lresults.getSelectedValue();
        JOptionPane.showMessageDialog(this, "user: " + gfi.getUsername() + ",file: " + gfi.getFilename() + ", bytes: " + gfi.getLength());
    }//GEN-LAST:event_binfoActionPerformed

    /** Code performed when download button has been pressed */
    private void bdownloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bdownloadActionPerformed
         
        GlobalFileInfo gfi = (GlobalFileInfo)lresults.getSelectedValue();
        
        long len = gfi.getLength();
        ClientI peer = gfi.getClient();

        System.out.println("Starting file transfer with " + len + " bytes:");
        
        try {
	        RemoteFISI fis = peer.getFile(gfi.getAbsFilepath());
	        
	        if (fis != null) {	        
		        FileOutputStream fos = new FileOutputStream(tshareddir.getText() + DELIM + gfi.getFilename());
		        
		        pdownloadprogress.setMinimum(0);
		        pdownloadprogress.setMaximum((int)len);
		        
		        for (long i=0; i<len; i++) {
		            pdownloadprogress.setValue((int)i);
		        	fos.write(fis.readByte());
		        	System.out.print(".");
		        }
		        
		        fos.close();
		        System.out.println("finished!");
	        }
        }
        catch (Exception e) {
        	e.printStackTrace();
        }
        
        try {
            String command = topenCommand.getText() + " " + tshareddir.getText() + DELIM + lresults.getSelectedValue().getFilename();
            System.out.println(command);
            Runtime.getRuntime().exec(command);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        
    }//GEN-LAST:event_bdownloadActionPerformed

    /** Code performed when go button has been pressed */
    private void bgoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bgoActionPerformed

    	GlobalFileInfo[] arr = client.query(tquery.getText());
    	
    	listmodel.clear();
    	
        if (arr.length > 0) {
	        for (int i=0; i<arr.length; i++) {
	            listmodel.addElement(arr[i]);
	        }
        } else {
        	JOptionPane.showMessageDialog(this, "Sorry, cannot perform query!");
        }
        
    }//GEN-LAST:event_bgoActionPerformed
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bLogin;
    private javax.swing.JButton bLogout;
    private javax.swing.JButton bchoose;
    private javax.swing.JButton bdownload;
    private javax.swing.JButton bgo;
    private javax.swing.JButton binfo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lquery;
    private javax.swing.JList<GlobalFileInfo> lresults;
    private javax.swing.JProgressBar pdownloadprogress;
    private javax.swing.JTextField topenCommand;
    private javax.swing.JPasswordField tpassword;
    private javax.swing.JTextField tquery;
    private javax.swing.JTextField tserver;
    private javax.swing.JTextField tshareddir;
    private javax.swing.JTextField tstate;
    private javax.swing.JTextField tusername;
    // End of variables declaration//GEN-END:variables
    
}
