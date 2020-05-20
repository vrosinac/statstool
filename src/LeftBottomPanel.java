/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package CsvFormatter_IV;


import javax.swing.*;
import java.awt.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author vrosinac
 */


      
public class LeftBottomPanel extends JPanel {
    private GridBagLayout gridBagLayoutLeftBottom;
    public JScrollPane jScrollPaneList, jScrollPaneListTriage, jScrollPaneListAccounts;
    private CSVFormatter ptrEngine;
    public javax.swing.JRadioButton rbOwner, rbTriage, rbAccount;
    private javax.swing.ButtonGroup buttonGroupFilter;
    public JList ownersList, triagersList, AccountsList;
    private static String[] initOwners = {"  ---  empty list  of case owners to select  ----        "};
    private static String[] initTriagers= {"  ---  empty list  of triagers to select  ----        "};
   private static String[] initAccounts= {"  ---  empty list  of accounts to select  ----        "};
   
    public  String activity_title ="";
               
    private int selectedCount =0;

    public LeftBottomPanel() {
        setupGui();
    }
    public void setEngine(CSVFormatter  csvF){ 
        
        ptrEngine = csvF;
    
    }
    
    
    private void rbOwnerActionPerformed(java.awt.event.ActionEvent evt) {
       
       ownersList.setListData(ptrEngine.ownersFinal);
       
    }  

    private void rbTriageActionPerformed(java.awt.event.ActionEvent evt) {
        ownersList.setListData(ptrEngine.triagersFinal);
      
    } 

    private void rbAccountActionPerformed(java.awt.event.ActionEvent evt) {
      ownersList.setListData(ptrEngine.AccountsFinal);
    } 
     
     
    private void setupGui() 
    {
        rbOwner = new javax.swing.JRadioButton();
        rbTriage = new javax.swing.JRadioButton();
        rbAccount = new javax.swing.JRadioButton();
         
        buttonGroupFilter = new javax.swing.ButtonGroup();
	buttonGroupFilter.add(rbOwner);
        rbOwner.setText("filter by owner");
        rbOwner.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbOwnerActionPerformed(evt);
            }
        });

	buttonGroupFilter.add(rbTriage);
        rbTriage.setText("filter by triager");
        rbTriage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbTriageActionPerformed(evt);
            }
        });


	buttonGroupFilter.add(rbAccount);
        rbAccount.setText("filter by account");
        rbAccount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbAccountActionPerformed(evt);
            }
        });

	rbOwner.setSelected(true);

        gridBagLayoutLeftBottom = new GridBagLayout();
      this.setBorder(BorderFactory.createTitledBorder(null,  "FILTER (use ctrl key for multiple selection) ",TitledBorder.LEFT,TitledBorder.DEFAULT_POSITION));
////           this.setBorder(BorderFactory.createTitledBorder(""));
        this.setLayout(gridBagLayoutLeftBottom);
        ownersList = new JList( initOwners);
        ownersList.setVisibleRowCount(5);
        ownersList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        ownersList.addMouseListener(new java.awt.event.MouseAdapter() 
        {
            public void mouseClicked(java.awt.event.MouseEvent evt) 
            {
                Cursor hourglassCursor = new Cursor(Cursor.WAIT_CURSOR);
                setCursor(hourglassCursor);
                String columnName = "";
                
                if (rbOwner.isSelected())
                {
                    columnName = "CASE_OWNER";
                }
                else 
                {
                    if (rbAccount.isSelected())
                    {
                         columnName = "ACCOUNT";
                    }
                    else 
                    {
                        if (rbTriage.isSelected())
                        {
                             columnName = "TRIAGE_BY";
                        }
                    }
                }
                
                String filter = "";
                 activity_title ="";
                ownersList.getSelectedValues();
                Object selectedValues[] = ownersList.getSelectedValues();
                int selectionsLength = selectedValues.length;
                for (int i = 0, n = selectionsLength; i < n; i++) 
                {
                    if (i == 0) 
                    {
                        System.out.println("  Selections: ");
                    }
                    System.out.println(i + "/" + selectedValues[i] + " ");
                    if (i ==0){
                        filter = filter + " AND ( " + columnName + " = " + "'" +  safeString((String)selectedValues[i]) + "' " ;
                    }
                    else{
                        if (i > 0)
                        {
                            filter = filter + " OR " + columnName + " = " + "'" +  safeString((String)selectedValues[i]) + "' " ;
                        }
                    }
                     activity_title = activity_title +  selectedValues[i] + ",";
                }
                    
                if (selectionsLength >0)
                {
                    filter = filter + " ) " ;
                     // if not  zero 
                     ptrEngine.regenerateBasedTableModels( filter);
                     ptrEngine.displayBaseTableModels();
                    ptrEngine.prepareDerivedTableModels(filter);
                    ptrEngine.displayDerivedTableModels();
                      // ptrEngine.DisplayMiddlePanel();
                     //ptrEngine.DisplayLeftPanel();
                
                    //  it has the length, which can nbe zero
                    setSelectedCount(selectionsLength);
                    Cursor normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);
                    setCursor(normalCursor);

                }
            } 
        }
        );
    
        JLabel labelOwners= new JLabel("case owners");         
        jScrollPaneList = new JScrollPane(ownersList);
         //   this.add(labelOwners, new GridBagConstraints(0, 0, 1, 2, 0.8, 1, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(1, 1, 1, 1), 0, 0));
        this.add(jScrollPaneList, new GridBagConstraints(0, 0, 1, 3, 1,    1, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        this.add(rbOwner,         new GridBagConstraints(1, 0, 1, 1, 0.5,  1, GridBagConstraints.NORTHEAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        this.add(rbTriage,        new GridBagConstraints(1, 1, 1, 1, 0.5,  1, GridBagConstraints.EAST, GridBagConstraints.CENTER, new Insets(0, 0, 0, 0), 0, 0));
	this.add(rbAccount,       new GridBagConstraints(1, 2, 1, 1, 0.5,  1, GridBagConstraints.SOUTHEAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
      
     
   
}
    
    
private String safeString (String str)
{

    String result;
    if (str.equals("Tina O'Kelly"))
    {
        result = "Tina O''Kelly";
        
    }
    else if (str.equals("Jerish dela Cruz"))
    {
        result = "Jerish Dela Cruz";
    }
    else
        result = str;
    
    return result;


}
 /*   
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
*/
    public int getSelectedCount()
    {
        return this.selectedCount;
    }
    
    
    
    private void setSelectedCount(int count)
    {
        this.selectedCount = count;
    }
    
}