/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package CsvFormatter_IV;


// mabuhay  
/**
 *
 * @author vrosinac
 */
public class GUITest1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      
        CSVFormatter csvF = new CSVFormatter();
        ApplicationFrame app = ApplicationFrame.getInsance();
        
        
        app.setVisible(true);
        app.setEngine(csvF);
        
    }
    
}
