/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package CsvFormatter_IV;
import javax.swing.*;
import java.awt.*;
import java.util.prefs.Preferences;


/**
 * 
 *
 * @author vrosinac
 */
public class RightAfterMiddlePanel extends JPanel {

     public ApplicationFrame ptrFrame;
    
    public javax.swing.JCheckBox jCheckBoxImplementation;
    public javax.swing.JCheckBox jCheckBoxLive;
    public javax.swing.JCheckBox jCheckBoxShowAllOthers; 
   
    public CSVFormatter ptrEngine;

    JSpinner spinner = new JSpinner();
    GridBagLayout gridBagLayout = new GridBagLayout();

    public RightAfterMiddlePanel() {
        setupGui();
       
    }
  
      public void setEngine(CSVFormatter  csvF){ ptrEngine = csvF;}
      
    private void setupGui()
    {
        this.setBorder(BorderFactory.createTitledBorder("CALC. OPT. system"));
        this.setLayout(gridBagLayout);
         
        
        jCheckBoxImplementation= new javax.swing.JCheckBox();
        jCheckBoxImplementation.setText("Live");
        
        jCheckBoxLive = new javax.swing.JCheckBox();
        jCheckBoxLive.setText("Implementation");
         
        jCheckBoxShowAllOthers= new javax.swing.JCheckBox();
        jCheckBoxShowAllOthers.setText("All others");
                
                
        int y=0;
        this.add(jCheckBoxLive ,            new GridBagConstraints(0, y++, 1, 1, 0, 0.1, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
        this.add(jCheckBoxImplementation ,  new GridBagConstraints(0, y++, 1, 1, 0, 0.1, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
        this.add(jCheckBoxShowAllOthers ,   new GridBagConstraints(0, y++, 1, 1, 0, 0.1, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
      
        
    }

    public void setFrame (ApplicationFrame f) {
        ptrFrame = f;
        
  
        setCheckBoxWithPreferences(jCheckBoxLive, "Live");
        setCheckBoxWithPreferences(jCheckBoxImplementation, "Implementation");
        setCheckBoxWithPreferences(jCheckBoxShowAllOthers, "All Others");
        
    }

    private void setCheckBoxWithPreferences(javax.swing.JCheckBox chkbx, String value)
    {
        
        
        if (ptrFrame.node.getInt(value,1) == 1){
            chkbx.setSelected(true);
        }
        else {
                chkbx.setSelected(false);
        }
        chkbx.setText(value);
        chkbx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if (chkbx.isSelected() == true)
                {ptrFrame.node.putInt(value,1);}
                else
                {ptrFrame.node.putInt(value,0);}
            }
        });
    }        
    
    
}

