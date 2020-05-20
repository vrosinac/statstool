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
public class RightBottomPanel extends JPanel {

     public ApplicationFrame ptrFrame;
    
    public javax.swing.JCheckBox jCheckBoxWeekStartTuesday;
    public javax.swing.JCheckBox jCheckBoxMonthlyCount;
    public javax.swing.JCheckBox jCheckBoxShowAwaitingClientAfterProgress; 
//    , jCheckBoxRestrictToSev2,     jCheckBoxRestrictToProduction, jCheckBoxRestrictToImplementation;
   
    public CSVFormatter ptrEngine;

    JSpinner spinner = new JSpinner();
    GridBagLayout gridBagLayout = new GridBagLayout();

    public RightBottomPanel() {
        setupGui();
       
    }
  
      public void setEngine(CSVFormatter  csvF){ ptrEngine = csvF;}
      
    private void setupGui()
    {
        this.setBorder(BorderFactory.createTitledBorder("CALC. OPTIONS  II "));
        this.setLayout(gridBagLayout);
         
        
        jCheckBoxWeekStartTuesday= new javax.swing.JCheckBox();
        jCheckBoxWeekStartTuesday.setText("Week starts on Tuesday");
        
        
        jCheckBoxShowAwaitingClientAfterProgress = new javax.swing.JCheckBox();
        jCheckBoxShowAwaitingClientAfterProgress.setText("Show 'Awaitingn Client' after progress");
        
         
        jCheckBoxMonthlyCount= new javax.swing.JCheckBox();
        jCheckBoxMonthlyCount.setText("Monthly results");
                
                
                
        int y=0;
        this.add(jCheckBoxWeekStartTuesday ,                 new GridBagConstraints(0, y++, 1, 1, 0, 0.1, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
        this.add(jCheckBoxMonthlyCount ,                     new GridBagConstraints(0, y++, 1, 1, 0, 1, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
        this.add(jCheckBoxShowAwaitingClientAfterProgress ,  new GridBagConstraints(0, y++, 1, 1, 0, 1, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
        
        
        
    }

    public void setFrame (ApplicationFrame f) {
        ptrFrame = f;
        
  
        setCheckBoxWithPreferences(jCheckBoxWeekStartTuesday, "Week starts on Tuesday");
        setCheckBoxWithPreferences(jCheckBoxMonthlyCount, "Monthly results");
        setCheckBoxWithPreferences(jCheckBoxShowAwaitingClientAfterProgress, "Show 'Awaiting Client' after progress");
     
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

