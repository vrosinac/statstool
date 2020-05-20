/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package CsvFormatter_IV;
import javax.swing.*;
import java.awt.*;
import static javax.swing.JTable.AUTO_RESIZE_OFF;


/**
 *
 * @author vrosinac
 */
public class MiddleTopPanel extends JPanel {

    //JTextField textField = new JTextField();
   // JButton addButton = new JButton();
    
    
    public  JTable jTableDetail;
    private JScrollPane jScrollPaneDetail;
     public CSVFormatter ptrEngine;
    
    
    
      public void setEngine(CSVFormatter  csvF){ ptrEngine = csvF;}
    
    
    
    
    GridBagLayout gridBagLayout = new GridBagLayout();

    public MiddleTopPanel() {
        setupGui();
    }

    private void setupGui() {
        this.setBorder(BorderFactory.createTitledBorder("DETAIL"));
        this.setLayout(gridBagLayout);

        String columnNames[] = {"Column 1", "Column 2", "Column 3", "Column 4"};
        String dataValues[][] =
                {
                };
        
        jTableDetail = new JTable(dataValues, columnNames);
        jTableDetail.setAutoResizeMode(AUTO_RESIZE_OFF);
        jScrollPaneDetail = new JScrollPane(jTableDetail);
        jScrollPaneDetail.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jTableDetail.setAutoCreateRowSorter(true);
        jTableDetail.getTableHeader().setReorderingAllowed(false);
        this.add(jScrollPaneDetail, new GridBagConstraints(0, 0, 1, 1, 0.2, 1.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(2, 2, 2, 2), 0, 0));

    }
}