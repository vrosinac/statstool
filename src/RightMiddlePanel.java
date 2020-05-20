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
public class RightMiddlePanel extends JPanel {

     public ApplicationFrame ptrFrame;
    
    public javax.swing.JCheckBox jCheckBoxCritical;
    public javax.swing.JCheckBox jCheckBoxShowstopper;
    public javax.swing.JCheckBox jCheckBoxShowBlocker; 
    public javax.swing.JCheckBox jCheckBoxShowHigh, jCheckBoxShowMedium, jCheckBoxShowLow;
   
    public CSVFormatter ptrEngine;

    JSpinner spinner = new JSpinner();
    GridBagLayout gridBagLayout = new GridBagLayout();

    public RightMiddlePanel() {
        setupGui();
       
    }
  
      public void setEngine(CSVFormatter  csvF){ ptrEngine = csvF;}
      
    private void setupGui()
    {
        this.setBorder(BorderFactory.createTitledBorder("CALC. OPT. sev."));
        this.setLayout(gridBagLayout);
         
        
        jCheckBoxCritical= new javax.swing.JCheckBox();
        jCheckBoxCritical.setText("1- Critical");
        
        jCheckBoxShowstopper = new javax.swing.JCheckBox();
        jCheckBoxShowstopper.setText("1- Showstopper");
         
        jCheckBoxShowBlocker= new javax.swing.JCheckBox();
        jCheckBoxShowBlocker.setText("2- Blocker");
                
        jCheckBoxShowHigh= new javax.swing.JCheckBox();
        jCheckBoxShowHigh.setText("2- High");
        
        jCheckBoxShowMedium= new javax.swing.JCheckBox();
        jCheckBoxShowMedium.setText("3- Medium");
        
        jCheckBoxShowLow= new javax.swing.JCheckBox();
        jCheckBoxShowLow.setText("4- Low");
                
                
        int y=0;
        this.add(jCheckBoxCritical ,     new GridBagConstraints(0, y++, 1, 1, 0, 1, GridBagConstraints.WEST, GridBagConstraints.WEST, new Insets(0, 0, 0, 0), 0, 0));
        this.add(jCheckBoxShowstopper ,  new GridBagConstraints(0, y++, 1, 1, 0, 1, GridBagConstraints.WEST, GridBagConstraints.WEST, new Insets(0, 0, 0, 0), 0, 0));
        this.add(jCheckBoxShowBlocker ,  new GridBagConstraints(0, y++, 1, 1, 0, 1, GridBagConstraints.WEST, GridBagConstraints.WEST, new Insets(0, 0, 0, 0), 0, 0));
        this.add(jCheckBoxShowHigh ,     new GridBagConstraints(0, y++, 1, 1, 0, 1, GridBagConstraints.WEST, GridBagConstraints.WEST, new Insets(0, 0, 0, 0), 0, 0));
        this.add(jCheckBoxShowMedium ,   new GridBagConstraints(0, y++, 1, 1, 0, 1, GridBagConstraints.WEST, GridBagConstraints.WEST, new Insets(0, 0, 0, 0), 0, 0));
        this.add(jCheckBoxShowLow ,      new GridBagConstraints(0, y++, 1, 1, 0, 1, GridBagConstraints.WEST, GridBagConstraints.WEST, new Insets(0, 0, 0, 0), 0, 0));
        
        
        
    }

    public void setFrame (ApplicationFrame f) {
        ptrFrame = f;
        
  
        setCheckBoxWithPreferences(jCheckBoxCritical, "1- Critical");
        setCheckBoxWithPreferences(jCheckBoxShowstopper, "1- Showstopper");
        setCheckBoxWithPreferences(jCheckBoxShowBlocker, "2- Blocker");
        setCheckBoxWithPreferences(jCheckBoxShowHigh, "2- High");
        setCheckBoxWithPreferences(jCheckBoxShowMedium, "3- Medium");
        setCheckBoxWithPreferences(jCheckBoxShowLow, "4- Low");

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

