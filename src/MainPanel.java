/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package CsvFormatter_IV;
import javax.swing.*;
import java.awt.*;

/**
 *
 * @author vrosinac
 */
public class MainPanel extends JPanel {

    private LeftTopPanel leftTopPanel;

    private LeftBottomPanel leftBottomPanel;
    public MiddleTopPanel middleTopPanel;
    public RighTopPanel rightTopPanel;
    public RightMiddlePanel  rightMiddlePanel;
    public RightAfterMiddlePanel rightAfterMiddlePanel;
  
    public RightBottomPanel rightBottomPanel;
    private BottomPanel bottomPanel;
    private GridBagLayout gridBagLayoutMain;
    //private JPanel topMainPanel;
    public CSVFormatter ptrEngine;
    private ApplicationFrame ff;
    
    public MainPanel(ApplicationFrame f) {
        ff = f;
        setupGui();
    }

     public void setEngine(CSVFormatter  csvF)
    {
    
        
        ptrEngine = csvF;
        leftTopPanel.setEngine(csvF);
        leftBottomPanel.setEngine(csvF);
        rightTopPanel.setEngine(csvF);        
        rightMiddlePanel.setEngine(csvF);        
        rightAfterMiddlePanel.setEngine(csvF);        
        bottomPanel.setEngine(csvF); 
        rightTopPanel.setEngine(csvF);
        rightMiddlePanel.setFrame(ff);
        rightAfterMiddlePanel.setFrame(ff);
        rightTopPanel.setFrame(ff);
        rightBottomPanel.setFrame(ff);
        ptrEngine.setLeftTopPanel(leftTopPanel);
        ptrEngine.setLeftBottomPanel(leftBottomPanel);
         ptrEngine.setRightTopPanel(rightTopPanel);
        ptrEngine.setRightMiddlePanel(rightMiddlePanel);
        ptrEngine.setRightAfterMiddlePanel(rightAfterMiddlePanel);
        ptrEngine.setRightBottomPanel(rightBottomPanel);
        ptrEngine.setMiddleTopPanel(middleTopPanel);
        ptrEngine.setBottomPanel(bottomPanel);
    }
    
     
    private void setupGui() {

        //topMainPanel = new JPanel();
        gridBagLayoutMain = new GridBagLayout();
        leftTopPanel = new LeftTopPanel();
        
        
        leftBottomPanel = new LeftBottomPanel();
        
        
        
        rightTopPanel = new RighTopPanel();
        rightMiddlePanel = new RightMiddlePanel();
        rightAfterMiddlePanel = new RightAfterMiddlePanel();
       
        rightBottomPanel = new RightBottomPanel();
        middleTopPanel = new MiddleTopPanel();
        bottomPanel = new BottomPanel();

        this.setBorder(BorderFactory.createTitledBorder(""));
        this.setLayout(gridBagLayoutMain);

        this.add(leftTopPanel,         new GridBagConstraints(0, 0, 1,   2, 0.25, 1,    GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
        this.add(leftBottomPanel,      new GridBagConstraints(0, 2, 1,   2, 0.25, 0.2,  GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, new Insets(0, 5, 0, 5), 0, 0));
        this.add(middleTopPanel,       new GridBagConstraints(1, 0, 1,   2, 1,    1,    GridBagConstraints.NORTHEAST, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
        this.add(bottomPanel,          new GridBagConstraints(1, 2, 1,   1, 1,    0,    GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));         
        this.add(rightTopPanel,        new GridBagConstraints(2, 0, 1,   1, 0,    1,    GridBagConstraints.EAST,  GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
        this.add(rightMiddlePanel,     new GridBagConstraints(2, 1, 1,   1, 0,    0,    GridBagConstraints.EAST,  GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
        this.add(rightAfterMiddlePanel,new GridBagConstraints(2, 2, 1,   1, 0,    0,    GridBagConstraints.EAST,  GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
        this.add(rightBottomPanel,     new GridBagConstraints(2, 3, 1,   1, 0,    0,    GridBagConstraints.EAST,  GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));

       
    }

}
