package fcsrtt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jesus Ortiz
 */
public class fcsrttGUI extends javax.swing.JFrame {
    
    private File inputFolder;   //!< Input folder
    private File outputFile;    //!< Output file
    
    private List<Experiment> experiments = new LinkedList<>();

    /**
     * Creates new form fcsrttGUI
     */
    public fcsrttGUI() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        b_inputFolder = new javax.swing.JButton();
        b_outputFile = new javax.swing.JButton();
        b_process = new javax.swing.JButton();
        l_inputFolder = new javax.swing.JLabel();
        l_outputFile = new javax.swing.JLabel();
        cb_verbose = new javax.swing.JCheckBox();
        b_exit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Ping's Parser");
        setResizable(false);

        b_inputFolder.setText("Input Folder");
        b_inputFolder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_inputFolderActionPerformed(evt);
            }
        });

        b_outputFile.setText("Output File");
        b_outputFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_outputFileActionPerformed(evt);
            }
        });

        b_process.setText("Process");
        b_process.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_processActionPerformed(evt);
            }
        });

        l_inputFolder.setText("Select Input Folder");

        l_outputFile.setText("Select Output File");

        cb_verbose.setText("Verbose Output");

        b_exit.setText("Exit");
        b_exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_exitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(b_inputFolder, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(l_inputFolder, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(b_outputFile, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(l_outputFile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cb_verbose)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(b_process, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(b_exit, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(b_inputFolder, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(l_inputFolder))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(b_outputFile, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(l_outputFile))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cb_verbose)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 211, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(b_process, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b_exit, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /** Select input folder action */
    private void b_inputFolderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_inputFolderActionPerformed
        // Create a file chooser
        final JFileChooser fc = new JFileChooser();
        
        // Set the default folder
        if (inputFolder != null) {
            fc.setCurrentDirectory(inputFolder);
        }
        else if (outputFile != null) {
            fc.setCurrentDirectory(outputFile.getParentFile());
        }
        else {
            fc.setCurrentDirectory(new File("."));
        }
        
        // Set directories only
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        
        // Set dialog title
        fc.setDialogTitle("Select input folder");

        // Get folder name
        int returnVal = fc.showOpenDialog(fcsrttGUI.this);
        
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            inputFolder = fc.getSelectedFile();
            l_inputFolder.setText(inputFolder.getName());
        }
    }//GEN-LAST:event_b_inputFolderActionPerformed

    /** Select output file action */
    private void b_outputFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_outputFileActionPerformed
        // Create a file chooser
        final JFileChooser fc = new JFileChooser() {
            @Override
            public void approveSelection() {
                File f = getSelectedFile();
                if (f.exists() && getDialogType() == SAVE_DIALOG) {
                    int result = JOptionPane.showConfirmDialog(
                        this, "The file exists, overwrite?",
                        "Existing file", JOptionPane.YES_NO_CANCEL_OPTION);
                    
                    switch(result) {
                        case JOptionPane.YES_OPTION:
                            super.approveSelection();
                            return;
                        case JOptionPane.NO_OPTION:
                            return;
                        case JOptionPane.CLOSED_OPTION:
                            return;
                        case JOptionPane.CANCEL_OPTION:
                            cancelSelection();
                            return;
                    }
                }
                else {
                    super.approveSelection();
                }
            }
        };
        
        // Set the default folder
        if (outputFile != null) {
            fc.setCurrentDirectory(outputFile);
        }
        else if (inputFolder != null) {
            fc.setCurrentDirectory(inputFolder.getParentFile());
        }
        else {
            fc.setCurrentDirectory(new File("."));
        }
        
        // Set files only
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        
        // Set dialog title
        fc.setDialogTitle("Select output file");
        
        // Get file name
        int returnVal = fc.showSaveDialog(fcsrttGUI.this);
        
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            outputFile = fc.getSelectedFile();
            l_outputFile.setText(outputFile.getName());
        }
    }//GEN-LAST:event_b_outputFileActionPerformed

    /** Process action */
    private void b_processActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_processActionPerformed
        if (outputFile == null || inputFolder == null) {
            JOptionPane.showMessageDialog(
                fcsrttGUI.this,
                "You must select the input folder and the output file first",
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Get the list of files in the directory
        File listOfFiles[] = inputFolder.listFiles();
        
        if (listOfFiles == null) {
            JOptionPane.showMessageDialog(
                fcsrttGUI.this,
                "The selected folder is empty or it couldn't been read",
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Sort files by name
        Arrays.sort(listOfFiles);
        
        // List of errors
        String errorFiles = new String();
        
        // Process one file by one
        for (File file : listOfFiles) {
            if (!processFile(file)) {
                errorFiles += "\n" + file.getName();
            }
        }
        
        // Show error message if necessary
        if (errorFiles.length() > 0) {
            JOptionPane.showMessageDialog(
                fcsrttGUI.this,
                "There was an error while processing the next files:" + errorFiles,
                "Warning", JOptionPane.WARNING_MESSAGE);
        }
        
        // Check data
        if (!checkData()) {
            JOptionPane.showMessageDialog(
                fcsrttGUI.this,
                "The data is probably incorrect",
                "Warning", JOptionPane.WARNING_MESSAGE);
        }
        
        // Save data in the file
        // TODO
        
        // Show success message
        JOptionPane.showMessageDialog(
            fcsrttGUI.this,
            "The data was succesfully exported",
            "Finished", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_b_processActionPerformed

    /** Exit action */
    private void b_exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_exitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_b_exitActionPerformed

    /** Main function
     * @param args the command line arguments
     */
    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(fcsrttGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(fcsrttGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(fcsrttGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(fcsrttGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new fcsrttGUI().setVisible(true);
            }
        });
    }
    
    
    /** Function to process one single file
     * @param [in] file     File to process
     * @retrun              true if sucess, false otherwise
     */
    private boolean processFile(File file) {
        try {
            // Variables
            FileInputStream inFile = new FileInputStream(file.getAbsolutePath());
            InputStreamReader inReader = new InputStreamReader(inFile);
            BufferedReader inBuffer = new BufferedReader(inReader);
            String line;
            int posColon;
            String key;
            String value;
            int session = -1;
            char lastVector = '\0';
            int number;
            TestDay testDay = new TestDay();
            Mouse mouse = new Mouse();
            int nValues;
            float values[] = new float[11];
            Experiment experiment = null;
            
            while (inBuffer.ready()) {
                // Read line
                line = inBuffer.readLine();
                
                // Check line size
		if (line.length() < 2) {
                    continue;
                }

		// Extract key
		posColon = line.indexOf(':');

		// Colon not found
		if (posColon == -1) {
                    continue;
                }
                
                // Get key and value
                key = line.substring(0, posColon);
                
                if (posColon + 2 < line.length()) {
                    value = line.substring(posColon + 2);
                }
                else {
                    value = "empty";
                }
                
                if (key.equals("Start Date")) {
                    // Move to next session
                    session++;

                    // Move to next experiment
                    if (session == 3)
                    {
                        session = 0;

                        if (experiment == null)
                        {
                            System.out.println("Something went wrong, the experiment shouldn't be NULL");
                            inBuffer.close();
                            inReader.close();
                            inFile.close();
                            return false;
                        }
                        else
                        {
                            testDay.daySeq = experiment.tests.size() + 1;
                            experiment.tests.add(testDay);
                        }
                    }

                    //System.out.println("Start Date: " + value);
                    testDay.dateStart = value;
                    lastVector = '\0';
		}
		else if (key.equals("End Date")) {
                    //System.out.println("End Date: " + value);
                    testDay.dateEnd = value;
                    lastVector = '\0';
		}
		else if (key.equals("Subject")) {
                    //System.out.println("Subject: " + value);
                    mouse.id = Integer.parseInt(value);
                    lastVector = '\0';

                    // Look for the mouse
                    ListIterator<Experiment> exIt = experiments.listIterator();
                    Experiment ex;
                    experiment = null;
                    
                    while (exIt.hasNext()) {
                        ex = exIt.next();
                        
                        if (ex.mouse.id == mouse.id) {
                            experiment = ex;
                            break;
                        }
                    }

                    if (experiment == null) {
                        //System.out.println( "Mouse not found, adding " + mouse.id);
                        experiment = new Experiment();
                        experiment.mouse = mouse;
                        experiment.tests.clear();
                        experiments.add(experiment);
                    }
		}
		else if (key.equals("Start Time")) {
                    //System.out.println("Start Time: " + value);
                    testDay.session[session].timeStart = value;
                    lastVector = '\0';
		}
		else if (key.equals("End Time")) {
                    //System.out.println("End Time: " + value);
                    testDay.session[session].timeEnd = value;
                    lastVector = '\0';
		}
		else if (key.equals("C")) {
                    lastVector = 'C';
		}
		else if (key.equals("D")) {
                    lastVector = 'D';
		}
		else if ((number = getNumber(value)) != -1) {
                    if (lastVector == 'C')
                    {
                        // Save data in the vector
                        nValues = extractValues(value, values, 11);

                        if (number + nValues > Session.SIZE_C)
                        {
                            System.out.println("Wrong number of values");
                            inBuffer.close();
                            inReader.close();
                            inFile.close();
                            return false;
                        }

                        System.arraycopy(values, 0, testDay.session[session].paramsC, number, nValues);
                    }
                    else if (lastVector == 'D')
                    {
                        // Save data in the vector
                        nValues = extractValues(value, values, 11);

                        if (number + nValues > Session.SIZE_D)
                        {
                            System.out.println("Wrong number of values");
                            inBuffer.close();
                            inReader.close();
                            inFile.close();
                            return false;
                        }

                        System.arraycopy(values, 0, testDay.session[session].paramsD, number, nValues);
                    }
		}
                else {
                    // Discard line
                    lastVector = '\0';
		}
            }
            
            inBuffer.close();
            inReader.close();
            inFile.close();
        } catch(IOException e) {
            System.out.println("Error reading the file");
            return false;
        }
        
        return true;
    }
    
    /** Converts a string to a number, but returns -1 if it's not a number */
    private int getNumber(String value) {
        try {
            int number = Integer.parseInt(value);
            return number;
        }
        catch (NumberFormatException e) {
            return -1;
        }
    }
    
    /** Extract an array of values from a string */
    private int extractValues(String line, float values[], int size)
    {
        int nValues = 0;
        boolean insideNumber = false;
        int posLastNumber = -1;

        for (int i = 0; i < line.length(); i++)
        {
            if (insideNumber)
            {
                if (line.charAt(i) == ' ')
                {
                    values[nValues] = Float.parseFloat(line.substring(posLastNumber));
                    nValues++;
                    insideNumber = false;
                }
            }
            else
            {
                if (line.charAt(i) != ' ')
                {
                    posLastNumber = i;
                    insideNumber = true;
                }
            }
        }

        if (insideNumber)
        {
            values[nValues] = Float.parseFloat(line.substring(posLastNumber));
            nValues++;
        }

        return nValues;
    }
    
    private boolean checkData() {
	boolean dataCorrect = true;

        ListIterator<Experiment> exIt = experiments.listIterator();
        Experiment ex;
        TestDay td;

        while (exIt.hasNext()) {
            ex = exIt.next();

            ListIterator<TestDay> tdIt = ex.tests.listIterator();
            
            while (tdIt.hasNext()) {
                td = tdIt.next();
                
                if (!td.session[0].timeStart.equals(td.session[1].timeStart) ||
                    !td.session[0].timeStart.equals(td.session[2].timeStart) ||
                    !td.session[1].timeStart.equals(td.session[2].timeStart)) {
                    dataCorrect = false;
                }
            }
        }

	return dataCorrect;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b_exit;
    private javax.swing.JButton b_inputFolder;
    private javax.swing.JButton b_outputFile;
    private javax.swing.JButton b_process;
    private javax.swing.JCheckBox cb_verbose;
    private javax.swing.JLabel l_inputFolder;
    private javax.swing.JLabel l_outputFile;
    // End of variables declaration//GEN-END:variables
}
