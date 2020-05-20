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
 * @author vrosinac
 */
public class RighTopPanel extends JPanel {

     public ApplicationFrame ptrFrame;
    
    public javax.swing.JCheckBox jCheckBoxClientConfirmed;
    public javax.swing.JCheckBox jCheckBoxClientUnconfirmed;
  //  public javax.swing.JCheckBox jCheckBoxClosed;
    public javax.swing.JCheckBox jCheckBoxDevelopment;
    public javax.swing.JCheckBox jCheckBoxFixToFormalise;
    
    public javax.swing.JCheckBox jCheckBoxEngineering;
    public javax.swing.JCheckBox jCheckBoxEnhancementDelivered;
    public javax.swing.JCheckBox jCheckBoxFixAvailable;
    public javax.swing.JCheckBox jCheckBoxFixDelivered;
    public javax.swing.JCheckBox jCheckBoxInformationProvided;
    public javax.swing.JCheckBox jCheckBoxMSDC;
   public javax.swing.JCheckBox jCheckBoxProductManagement;
   
    public javax.swing.JCheckBox jCheckBoxPS;
    public javax.swing.JCheckBox jCheckBoxRequestEngineering;
    public javax.swing.JCheckBox jCheckBoxResolved;
    public javax.swing.JCheckBox jCheckBoxResolvedByPhone;
    public javax.swing.JCheckBox jCheckBoxResolvedNoPhone;
    public javax.swing.JCheckBox jCheckBoxSales;
    public javax.swing.JCheckBox jCheckBoxSolutionProvided;
  //  public javax.swing.JCheckBox jCheckBoxWeekStartTuesday;
    public javax.swing.JCheckBox jCheckBoxWorkaroundProvided;
    public CSVFormatter ptrEngine;
    //private Preferences prefs;
    //JLabel label02= new JLabel("Text Label Text");

    JSpinner spinner = new JSpinner();
    GridBagLayout gridBagLayout = new GridBagLayout();

    public RighTopPanel() {
        setupGui();
    }
  
      public void setEngine(CSVFormatter  csvF){ 
          ptrEngine = csvF;
      }
      
    private void setupGui()
    {
        this.setBorder(BorderFactory.createTitledBorder("CALC. OPTIONS (progress)"));
        this.setLayout(gridBagLayout);
         
        jCheckBoxClientConfirmed = new javax.swing.JCheckBox();
        jCheckBoxClientUnconfirmed= new javax.swing.JCheckBox();
     //   jCheckBoxClosed= new javax.swing.JCheckBox();
        jCheckBoxDevelopment= new javax.swing.JCheckBox();
        jCheckBoxFixToFormalise= new javax.swing.JCheckBox();
        jCheckBoxEngineering= new javax.swing.JCheckBox();
        jCheckBoxRequestEngineering= new javax.swing.JCheckBox();
        jCheckBoxEnhancementDelivered= new javax.swing.JCheckBox();
        jCheckBoxFixAvailable= new javax.swing.JCheckBox();
        jCheckBoxFixDelivered= new javax.swing.JCheckBox();
        jCheckBoxInformationProvided= new javax.swing.JCheckBox();
        jCheckBoxProductManagement= new javax.swing.JCheckBox();
        jCheckBoxMSDC= new javax.swing.JCheckBox();
        jCheckBoxPS= new javax.swing.JCheckBox();
        jCheckBoxResolved= new javax.swing.JCheckBox();
        jCheckBoxResolvedByPhone= new javax.swing.JCheckBox();
        jCheckBoxResolvedNoPhone= new javax.swing.JCheckBox();
        jCheckBoxSales= new javax.swing.JCheckBox();
        jCheckBoxSolutionProvided= new javax.swing.JCheckBox();
        jCheckBoxWorkaroundProvided= new javax.swing.JCheckBox(); 
       // jCheckBoxWeekStartTuesday= new javax.swing.JCheckBox();
      
    
        jCheckBoxResolved.setSelected(true);
        jCheckBoxResolved.setText("Resolved");
        jCheckBoxDevelopment.setSelected(true);
        jCheckBoxDevelopment.setText("Development");

        jCheckBoxFixToFormalise.setSelected(true);
        jCheckBoxFixToFormalise.setText("Development");

        jCheckBoxClientConfirmed.setSelected(true);
        jCheckBoxClientConfirmed.setText("Client Confirmed");

        jCheckBoxInformationProvided.setSelected(true);
        jCheckBoxInformationProvided.setText("Information Provided");

        jCheckBoxSolutionProvided.setSelected(true);
        jCheckBoxSolutionProvided.setText("Solution Provided");

        jCheckBoxClientUnconfirmed.setSelected(true);
        jCheckBoxClientUnconfirmed.setText("Client Unconfirmed");

        jCheckBoxFixDelivered.setSelected(true);
        jCheckBoxFixDelivered.setText("Fix Delivered");

        jCheckBoxFixAvailable.setSelected(true);
        jCheckBoxFixAvailable.setText("Fix Available");

        jCheckBoxResolvedByPhone.setSelected(true);
        jCheckBoxResolvedByPhone.setText("Resolved By Phone");

        jCheckBoxResolvedNoPhone.setSelected(true);
        jCheckBoxResolvedNoPhone.setText("Resolved No Phone");

        jCheckBoxEnhancementDelivered.setSelected(true);
        jCheckBoxEnhancementDelivered.setText("Enhancement Delivered");

        jCheckBoxMSDC.setSelected(true);
        jCheckBoxMSDC.setText("MSDC");

        jCheckBoxProductManagement.setSelected(true);
        jCheckBoxProductManagement.setText("MSDC");

        
        jCheckBoxPS.setSelected(true);
        jCheckBoxPS.setText("PS");
        jCheckBoxSales.setSelected(true);
        jCheckBoxSales.setText("Sales");

        jCheckBoxRequestEngineering.setSelected(true);
        jCheckBoxRequestEngineering.setText("Request Engineering");

        jCheckBoxWorkaroundProvided.setSelected(true);
        jCheckBoxWorkaroundProvided.setText("Workaround Provided");

        jCheckBoxEngineering.setSelected(true);
        jCheckBoxEngineering.setText("Engineering");

        
        
        int y=0;
        this.add(jCheckBoxClientConfirmed ,  new GridBagConstraints(0, y++, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
        this.add(jCheckBoxClientUnconfirmed ,  new GridBagConstraints(0, y++, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
     //   this.add(jCheckBoxClosed ,  new GridBagConstraints(0, y++, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
        this.add(jCheckBoxDevelopment ,  new GridBagConstraints(0, y++, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
        this.add(jCheckBoxFixToFormalise ,  new GridBagConstraints(0, y++, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
     
        this.add(jCheckBoxEngineering ,  new GridBagConstraints(0, y++, 1,1 , 1, 1, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
        this.add(jCheckBoxRequestEngineering ,  new GridBagConstraints(0, y++, 1, 1, 1.0, 1.0, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
        this.add(jCheckBoxEnhancementDelivered ,  new GridBagConstraints(0, y++, 1, 1, 1.0, 1.0, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
        this.add(jCheckBoxFixAvailable ,  new GridBagConstraints(0, y++, 1, 1, 1.0, 1.0, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
        this.add(jCheckBoxFixDelivered ,  new GridBagConstraints(0, y++, 1, 1, 1.0, 1.0, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
        this.add(jCheckBoxInformationProvided ,  new GridBagConstraints(0, y++, 1, 1, 1.0, 1.0, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
        this.add(jCheckBoxMSDC ,  new GridBagConstraints(0, y++, 1, 1, 1.0, 1.0, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
      this.add(jCheckBoxProductManagement ,  new GridBagConstraints(0, y++, 1, 1, 1.0, 1.0, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
      
        this.add(jCheckBoxPS ,  new GridBagConstraints(0, y++, 1, 1, 1.0, 1.0, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
        this.add(jCheckBoxResolved ,  new GridBagConstraints(0, y++, 1, 1, 1.0, 1.0, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
        this.add(jCheckBoxResolvedByPhone ,  new GridBagConstraints(0, y++, 1, 1, 1.0, 1.0, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
        this.add(jCheckBoxResolvedNoPhone ,  new GridBagConstraints(0, y++, 1, 1, 1.0, 1.0, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
        this.add(jCheckBoxSales ,  new GridBagConstraints(0, y++, 1, 1, 1.0, 1.0, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
        this.add(jCheckBoxSolutionProvided ,  new GridBagConstraints(0, y++, 1, 1, 1.0, 1.0, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
        this.add(jCheckBoxWorkaroundProvided ,  new GridBagConstraints(0, y++,1, 1, 1.0, 1.0, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
  //      this.add(jCheckBoxWeekStartTuesday ,  new GridBagConstraints(0, y++, 1, 1, 1.0, 1.0, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
        
    }

    public void setFrame (ApplicationFrame f) {
        ptrFrame = f;
        setCheckBoxWithPreferences(jCheckBoxResolved,"Resolved");
        setCheckBoxWithPreferences(jCheckBoxDevelopment,"Development");
        setCheckBoxWithPreferences(jCheckBoxFixToFormalise,"Fix to formalise");
       
        setCheckBoxWithPreferences(jCheckBoxClientConfirmed,"Client Confirmed");
        setCheckBoxWithPreferences(jCheckBoxInformationProvided,"Information Provided");
        setCheckBoxWithPreferences(jCheckBoxSolutionProvided,"Solution Provided");
        setCheckBoxWithPreferences(jCheckBoxClientUnconfirmed,"Client Unconfirmed");
        setCheckBoxWithPreferences(jCheckBoxFixDelivered, "Fix Delivered");
        setCheckBoxWithPreferences(jCheckBoxFixAvailable,"Fix Available");
        setCheckBoxWithPreferences(jCheckBoxResolvedByPhone,"Resolved By Phone");
        setCheckBoxWithPreferences(jCheckBoxResolvedNoPhone,"Resolved No Phone");
        setCheckBoxWithPreferences(jCheckBoxEnhancementDelivered,"Enhancement Delivered");
        setCheckBoxWithPreferences(jCheckBoxMSDC,"MSDC");
        setCheckBoxWithPreferences(jCheckBoxProductManagement,"ProductManagement");
        setCheckBoxWithPreferences(jCheckBoxPS, "PS");
        setCheckBoxWithPreferences(jCheckBoxSales, "Sales");
        setCheckBoxWithPreferences(jCheckBoxRequestEngineering, "Request Engineering");
        setCheckBoxWithPreferences(jCheckBoxWorkaroundProvided, "Workaround Provided");
        setCheckBoxWithPreferences(jCheckBoxEngineering, "Engineering");
      //  setCheckBoxWithPreferences(jCheckBoxWeekStartTuesday, "Week starts on Tuesday");
    //    setCheckBoxWithPreferences(jCheckBoxClosed, "Closed");
        
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

