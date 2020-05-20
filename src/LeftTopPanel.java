/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package CsvFormatter_IV;


import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author vrosinac
 */


      
public class LeftTopPanel extends JPanel {

    public JTable jTableCS, jTableProgressed, jTableClient;
    private GridBagLayout gridBagLayoutLeftTop;
    public JScrollPane jScrollPaneClient, jScrollPaneCS,jScrollPaneProgressed, jScrollPaneList;
    public javax.swing.JRadioButton rbAtDate;
    public javax.swing.JRadioButton rbFirstRes;
    public javax.swing.JRadioButton rbEachProgress;
    private javax.swing.ButtonGroup buttonGroup1;
    public JCheckBox checkBoxCombined;
    private CSVFormatter ptrEngine;
    private JList leftList;
    private static String[] owners = {"Byron","Kathy","Ivan", "Winkleman Montevirgen","Tin"};
    
    

    public LeftTopPanel() {
        setupGui();
    }
    public void setEngine(CSVFormatter  csvF){ ptrEngine = csvF;}
    private void setupGui() {
//        jTable1 = new JTable();




        gridBagLayoutLeftTop = new GridBagLayout();
        checkBoxCombined = new JCheckBox();
        checkBoxCombined.setText("Combined for Detail");
        this.setBorder(BorderFactory.createTitledBorder("TOTAL (top table = Customer side, middle = in CS, bottom = Progressed)"));
        this.setLayout(gridBagLayoutLeftTop);

        String columnNames[] = {"Column 1", "Column 2", "Column 3", "Column 4"};
        String dataValues[][] =   {         };

        jTableCS = new JTable(dataValues, columnNames);
        jTableProgressed = new JTable(dataValues, columnNames);
        jTableProgressed.getTableHeader().setReorderingAllowed(false);
        jTableProgressed.setAutoCreateRowSorter(true);
         jScrollPaneProgressed = new JScrollPane(jTableProgressed);
       
        
        jTableClient = new JTable(dataValues, columnNames);
        jTableClient.setAutoCreateRowSorter(true);
        jTableClient.getTableHeader().setReorderingAllowed(false);
        jScrollPaneClient = new JScrollPane(jTableClient);
                
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);
        
        jTableCS.setAutoCreateRowSorter(true);
        jTableCS.getTableHeader().setReorderingAllowed(false);
        jScrollPaneCS = new JScrollPane(jTableCS);
        jTableCS.getColumnModel().getColumn(2).setCellRenderer(renderer);
        jTableCS.getColumnModel().getColumn(3).setCellRenderer(renderer);
        
    
        
        // radio buttons
      	rbAtDate = new javax.swing.JRadioButton();
        rbEachProgress = new javax.swing.JRadioButton();
        rbFirstRes = new javax.swing.JRadioButton();
    

   	buttonGroup1 = new javax.swing.ButtonGroup();
 	buttonGroup1.add(rbAtDate);
        rbAtDate.setText("AtDate");
        rbAtDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbAtDateActionPerformed(evt);
            }
        });

	buttonGroup1.add(rbEachProgress);
        rbEachProgress.setText("EachProgress");
        rbEachProgress.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbEachProgressActionPerformed(evt);
            }
        });


	buttonGroup1.add(rbFirstRes);
        rbFirstRes.setText("FirstRes");
        rbFirstRes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbFirstResActionPerformed(evt);
            }
        });

        rbAtDate.setSelected(true);

           
        
        
        jTableClient.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblClientMouseClicked(evt);
            }
        });

        jTableCS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCSMouseClicked(evt);
            }
        });
        
        jTableProgressed.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProgressedMouseClicked(evt);
            }
        });
    
        
        
      //  add(new JScrollPane(leftList));
         leftList = new JList(owners);
         leftList.setVisibleRowCount(4);
         leftList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
       
        jScrollPaneList = new JScrollPane(leftList);
      
        
        
        this.add(jScrollPaneClient, new GridBagConstraints(0, 0, 2, 1, 0.2, 1.0, GridBagConstraints.SOUTH, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
        this.add(jScrollPaneCS, new GridBagConstraints(0, 1, 2, 1, 0.2, 1.0, GridBagConstraints.SOUTH, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
        this.add(jScrollPaneProgressed, new GridBagConstraints(0, 2, 2, 1, 0.2, 1.0, GridBagConstraints.SOUTH, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
        this.add(rbFirstRes, new GridBagConstraints(0, 3, 1, 1, 0.2, 0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        this.add(rbEachProgress, new GridBagConstraints(0, 3, 1, 1, 0.2, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        this.add(rbAtDate, new GridBagConstraints(0, 3, 1, 1, 0.2, 0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        this.add(checkBoxCombined, new GridBagConstraints(1, 3, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
       // this.add(jScrollPaneList, new GridBagConstraints(0, 4, 5, 1, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));

    //  this.add(jScrollPaneList, new GridBagConstraints(0, 0, 5, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));

}
    
    
    
    
    private void rbAtDateActionPerformed(java.awt.event.ActionEvent evt) {
        ptrEngine.rbAtDateActionPerformed(evt);
    }
    
    private void rbEachProgressActionPerformed(java.awt.event.ActionEvent evt) {
        ptrEngine.rbEachProgressActionPerformed(evt);
    }
    
    private void rbFirstResActionPerformed(java.awt.event.ActionEvent evt) {
        ptrEngine.rbFirstResActionPerformed(evt);
    }

 
    private void tblClientMouseClicked(java.awt.event.MouseEvent evt){

           ptrEngine.tblClientSideMouseClicked(evt);

   }
    private void tblCSMouseClicked(java.awt.event.MouseEvent evt){

           ptrEngine.tblCSSideMouseClicked(evt);

   }

    
    private void tblProgressedMouseClicked(java.awt.event.MouseEvent evt){

           ptrEngine.tblProgressedMouseClicked(evt);

   }

    
    
    
}