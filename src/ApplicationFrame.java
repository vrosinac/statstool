/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package CsvFormatter_IV;
import javax.swing.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.awt.*;
import java.util.prefs.Preferences;
/**
 *
 * @author vrosinac
 */

public class ApplicationFrame extends JFrame
{
    private static ApplicationFrame instance = null;
    private MainPanel mainPanel;
    private GridBagLayout gridBagLayoutAppFrame;
    private CSVFormatter ptrEngine;
    //private JMenu menu;
    public static final int  DEFAULT_WIDTH=900;
    public static final int DEFAULT_HEIGHT=600;
    public Preferences node;
    
    private ApplicationFrame()
    {
        setupGUI();
    }
    public void setEngine(CSVFormatter  csvF)
    {
    
        ptrEngine = csvF;
        mainPanel.setEngine(csvF);
    
    }
    public static ApplicationFrame getInsance()
    {
        if(instance == null)
        {
            instance = new ApplicationFrame();
     
        }
        return  instance;
    }

    public void setupGUI()
    {
        this.setTitle("CSV Formatter  (vincent.rosinach@finastra.com)");
    
        Preferences root = Preferences.userRoot();
        node = root.node("/com/vrosinac/csvformatter");
        int left = node.getInt("left", 200);
        int top = node.getInt("top", 200);
        int width = node.getInt("width", DEFAULT_WIDTH);
        int height = node.getInt("height", DEFAULT_HEIGHT);

        //this.setMinimumSize(new Dimension(left,top));
        this.setBounds(left,top,width,height);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        gridBagLayoutAppFrame = new GridBagLayout();
        mainPanel = new MainPanel(this);
        this.setLayout(gridBagLayoutAppFrame);
        this.getContentPane().add(mainPanel,new GridBagConstraints(0,0,1,1,1,1,GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(10,10,10,10),0,0));
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu menu = new JMenu ("File");
        menuBar.add(menu);
        JMenuItem exitItem = new JMenuItem("Exit");
        menu.add(exitItem);
        exitItem.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent event){
        
        node.putInt("left", getX());
        node.putInt("top", getY());
        node.putInt("height", getHeight());
        node.putInt("width", getWidth());
        System.exit(0);
        }}
        );

        addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                System.out.println("Closed");
        
                node.putInt("left", getX());
                node.putInt("top", getY());
                node.putInt("height", getHeight());
                node.putInt("width", getWidth());
                e.getWindow().dispose();
            }
        });
        
    }
    

}

