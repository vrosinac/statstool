//package csvformatter;

//package CsvFormatter_IV;
import java.awt.Cursor;
import java.text.*;
import java.util.Date;
import org.joda.time.Days;
import java.util.Calendar;
import java.time.LocalTime;
import javax.swing.JOptionPane;
import com.opencsv.CSVReader;
import java.awt.Rectangle;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

/**
 *
 * @author vr
 */
/*
class SwingProgressBar extends JPanel {

  JProgressBar pbar;

  static final int MY_MINIMUM = 0;

  static final int MY_MAXIMUM = 100;

  public SwingProgressBar() {
    // initialize Progress Bar
    pbar = new JProgressBar();
    pbar.setMinimum(MY_MINIMUM);
    pbar.setMaximum(MY_MAXIMUM);
    // add to JPanel
    add(pbar);
  }
}

*/

public class CSVFormatter extends javax.swing.JFrame {
    
    
    //static final String USERNAME     = "username@salesforce.com";
    static final String USERNAME     = "vincent.rosinach@misys.com";
    //static final String PASSWORD     = "passwordSecurityToken";
    static final String PASSWORD     = "Misys890814189";
    //static final String LOGINURL     = "https://login.salesforce.com";
    
    static final String LOGINURL     = "https://misys.my.salesforce.com";
    
    static final String GRANTSERVICE = "/services/oauth2/token?grant_type=password";
    static final String CLIENTID     = "00D20000000098n";
    static final String CLIENTSECRET = "SecretKey";
    static String accessToken  =  "";
    public static String[] ownersTemporary;
    public static String[] ownersFinal;
    public static String[] triagersTemporary;
    public static String[] triagersFinal;
    public static String[] AccountsTemporary;
    public static String[] AccountsFinal;
    private static int[] calendarTemporary;
    private static int[] calendarFinal;
    public static int[] PeriodCalendar;
    private static         Object[] columnnamessolved;

    public static Vector<Vector<Object>> VectorFromResultSet(ResultSet rs)
            throws SQLException {
        
        ResultSetMetaData metaData = rs.getMetaData();
        
        // names of columns
        Vector<String> columnNames = new Vector<String>();
        int columnCount = metaData.getColumnCount();
        for (int column = 1; column <= columnCount; column++) {
            columnNames.add(metaData.getColumnName(column));
        }
        
        // data of the table
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        while (rs.next()) {
            Vector<Object> vector = new Vector<Object>();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                vector.add(rs.getObject(columnIndex));
            }
            data.add(vector);
        }
        
        return data;
    }
   
    
    
    public LeftTopPanel  leftTopPanel;
    public LeftBottomPanel leftBottomPanel;
    private MiddleTopPanel middleTopPanel;
    private BottomPanel bottomPanel;
    private RighTopPanel rightTopPanel;
    private RightMiddlePanel rightMiddlePanel;
    private RightAfterMiddlePanel rightAfterMiddlePanel;
    public  RightBottomPanel rightBottomPanel;
    public int periodCalendarLength=0;
    
    private final JFileChooser fileChooser;
    private File csvFile;
    public DefaultTableModel tableModelEachProgress, tableModelFirstRes, tableModelAtDate,tableModelCSAtDate;
    public DefaultTableModel  tableModelAggrEachProgress, tableModelAggrFirstRes, tableModelAggrAtDate , tableModelAwaitingClientAtDate,
            tableModelCSEachProgress, tableModelAwaitingClientEachProgress, tableModelCSFirstRes, tableModelAwaitingClientFirstRes,
            tbl_M_EP_Trg_All,tbl_M_EP_Trg_Pced_Dev,tbl_M_EP_Trg_Pcd_NoDev, tbl_M_EP_Trg_Self_Pcd_Dev,tbl_M_EP_Trg_Self_Pcd_NoDev, 
             tbl_M_AD_Trg_All,tbl_M_AD_Trg_Pced_Dev,tbl_M_AD_Trg_Pcd_NoDev, tbl_M_AD_Trg_Self_Pcd_Dev,tbl_M_AD_Trg_Self_Pcd_NoDev,
             tbl_M_FR_Trg_All,tbl_M_FR_Trg_Pced_Dev,tbl_M_FR_Trg_Pcd_NoDev, tbl_M_FR_Trg_Self_Pcd_Dev,tbl_M_FR_Trg_Self_Pcd_NoDev,
            tbl_DowngradeSev_AD,tbl_DowngradeSev_EP,tbl_DowngradeSev_FR,
            tbl_DowngradeSevBelow2_AD,tbl_DowngradeSevBelow2_EP,tbl_DowngradeSevBelow2_FR,
            tbl_time_triage_AD, tbl_time_L2_AD, tbl_time_customer_AD,
            tbl_time_triage_EP, tbl_time_L2_EP, tbl_time_customer_EP,
            tbl_time_triage_FR, tbl_time_L2_FR, tbl_time_customer_FR
            ;
    
    private int incrementAtDate, incrementFirstRes, incrementEachProgress;
    private /* static final*/ int CASE_NUMBER = 0,
            PRODUCT = 1,
            EDITED_BY = 2,
            EDIT_DATE = 3,
            FIELD_EVENT = 4,
            OLD_VALUE = 5,
            NEW_VALUE = 6,
            CASE_OWNER = 7,
            SEVERITY = 8,
            
            PRODUCT_SET_VERSION = 9,
            TYPE = 10,
            STORY_POINTS = 11,
            YOUR_ACCOUNT_NAME=12,
            SYSTEM_STATUS=13,
            MODULE = 14,
            ORIGINAL_PRIO=15,
            NUM_COLUMNS = 16;
    private String Severity ="";
    
    private String OriginalPrio ="";
    private String system = "";

    private  int  earliestInterval =0;  ///20170601 > 20170501
    private  int  latestInterval =0;  ///20170601 > 20170501
    
   // private long time_triage =0, time_L2 =0, time_L3 = 0, time_cust = 0;
    private int time_L2_start=0, time_L3_start=0, time_cust_start=0;

    public void setDateEndReport(DateTime dateEndReport) {
        this.dateEndReport = dateEndReport;
    }
    private DateTime dateEndReport; 
        
    private double teamAverage =0;
    
    private int teamSize =0;
   
    public CSVFormatter() {
        fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("CSV files (*.csv)", "csv"));
      
        try {
            // Load the driver.
            Class.forName("org.relique.jdbc.csv.CsvDriver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CSVFormatter.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public File getCsvFile()
    {
        return csvFile;
    }
    public double getTeamAverage()
    {
        return this.teamAverage;
    }
    public int getTeamSize()
    {
        return this.teamSize;
    }
    
    private double calculateTeamAverage()
    {
       double result =0; 
       int total_cases =0;
       int size =0;
       //  scroll through the leftTopPanel.jTableProgressed to add together the numbers of cases solved of everybody
       JTable  tbl =   leftTopPanel.jTableProgressed; 
       
       int i = 0;
       int period = 0, last_period=0;
       int periodcount = 0;
       size = tbl.getRowCount();
       while (i < size)
       {
           total_cases = total_cases + (int) tbl.getValueAt(i,2);
           period = Integer.parseInt( (String)tbl.getValueAt(i,0));
           if (period != last_period)
           {
               periodcount = periodcount+1;
           }
           last_period= period;
           
           i = i+1;
       }
       
       ///  decde to get the average
       int own_length= getOwnersLength();
       this.teamSize = own_length;
       result = (double) total_cases / ((double) own_length * (double) /*periodcount*/  getTemporaryCalendarLength());
    
        return result;
    }
   
    public void btnSelectCSVActionPerformed(java.awt.event.ActionEvent evt) {
        //System.out.println(FileSystemView.getFileSystemView().getDefaultDirectory().toString());
        // fileChooser.setCurrentDirectory(FileSystemView.getFileSystemView().getDefaultDirectory());
        //FileSystemView.getFileSystemView().getDefaultDirectory()
        //  rightTopPanel.ptrFrame.node.get("path", );
        //fileChooser.setCurrentDirectory(new File("C:\\"));
        //  fileChooser.setCurrentDirectory(   rightTopPanel.ptrFrame.node.get("path",   FileSystemView.getFileSystemView().getDefaultDirectory()));
        // fileChooser.setCurrentDirectory(   rightTopPanel.ptrFrame.node.get("path",   ;
         
        String directory = rightTopPanel.ptrFrame.node.get("path","C:\\")   ;
        fileChooser.setCurrentDirectory(new File(directory));
        int retVal = fileChooser.showOpenDialog(this);
        if (retVal == JFileChooser.APPROVE_OPTION) {
            String preferedDirectory = fileChooser.getCurrentDirectory().getAbsolutePath();
            rightTopPanel.ptrFrame.node.put("path", preferedDirectory);
            csvFile = fileChooser.getSelectedFile();
            bottomPanel.lblFileInfo.setText(csvFile.getName());
        } 
        else 
        {
            csvFile = null;
        }
    }
      
    public void ValidateFileStructure(/*List csvRows*/ )
    {
          List csvRows = null;
        try {
            CSVReader CSVFileReader = new CSVReader(new FileReader(this.getCsvFile()));
            csvRows = CSVFileReader.readAll();
        } catch (IOException ex) {
            Logger.getLogger(CSVFormatter.class.getName()).log(Level.SEVERE, null, ex);
        }
        int columnnumber = 0;
        boolean existsColumn_CASE_NUMBER=false, existsColumn_EDITED_BY=false, existsColumn_EDIT_DATE=false, 
          existsColumn_FIELD_EVENT=false, existsColumn_OLD_VALUE=false, existsColumn_NEW_VALUE=false, existsColumn_CASE_OWNER=false,
          existsColumn_SEVERITY =false,existsColumn_ORIGINAL_PRIO =false,  existsColumn_PRODUCT_SET_VERSION =false, existsColumn_STORY_POINTS=false, 
          existsColumn_YOUR_ACCOUNT_NAME = false, existsColumn_SYSTEM_STATUS = false, existsColumn_MODULE = false;
        
        for (String thiscollvalue : (String[]) csvRows.get(0)) 
        {
            if (thiscollvalue.equals("Case Number") == true)
            {
                    existsColumn_CASE_NUMBER=true;
                    CASE_NUMBER = columnnumber;

            }
            if (thiscollvalue.equals("Edited By") == true)
            {
                    existsColumn_EDITED_BY=true;
                    EDITED_BY = columnnumber;

            }
            if (thiscollvalue.equals("Edit Date") == true)
            {
                    existsColumn_EDIT_DATE=true;
                    EDIT_DATE = columnnumber;

            }
            
            if (thiscollvalue.equals("Product Set Version") == true)
            {
                    existsColumn_PRODUCT_SET_VERSION=true;
                    PRODUCT_SET_VERSION = columnnumber;
            }
            
            if (thiscollvalue.equals("Severity") == true)
            {
                    existsColumn_SEVERITY=true;
                    SEVERITY = columnnumber;
            }
            
            if (thiscollvalue.equals("Field / Event") == true)
            {
                    existsColumn_FIELD_EVENT=true;
                    FIELD_EVENT = columnnumber;

            }
            if (thiscollvalue.equals("Old Value") == true)
            {
                    existsColumn_OLD_VALUE=true;
                    OLD_VALUE = columnnumber;

            }
            if (thiscollvalue.equals("New Value") == true)
            {
                    existsColumn_NEW_VALUE=true;
                    NEW_VALUE = columnnumber;

            }
            if (thiscollvalue.equals("Case Owner") == true)
            {
                    existsColumn_CASE_OWNER=true;
                    CASE_OWNER = columnnumber;

            }
            if (thiscollvalue.equals("Remarks") == true)
            {
                    existsColumn_STORY_POINTS=true;
                    STORY_POINTS = columnnumber;

            }
            
            
            if (thiscollvalue.equals("Your Account Name") == true)
            {
                    existsColumn_YOUR_ACCOUNT_NAME=true;
                    YOUR_ACCOUNT_NAME = columnnumber;
            }
            

            if (thiscollvalue.equals("System Status") == true)
            {
                    existsColumn_SYSTEM_STATUS=true;
                    SYSTEM_STATUS = columnnumber;
            }
            
            
            if (thiscollvalue.equals("Product Module") == true)
            {
                    existsColumn_MODULE=true;
                    MODULE = columnnumber;
            }
            
            
            if (thiscollvalue.equals("Original Priority") == true)
            {
                    existsColumn_ORIGINAL_PRIO=true;
                    ORIGINAL_PRIO = columnnumber;
            }
            
            
            columnnumber = columnnumber + 1;
        }


        String Title = "column misisng in iput csv file";
        if (existsColumn_CASE_NUMBER == false)
        {

            JOptionPane.showMessageDialog(null, "Case Number", "InfoBox: " + Title, JOptionPane.INFORMATION_MESSAGE);
        return;
        }

        if (existsColumn_EDITED_BY == false)
        {
            JOptionPane.showMessageDialog(null, "Edited By", "InfoBox: " + Title, JOptionPane.INFORMATION_MESSAGE);
        return;
        }
        if (existsColumn_EDIT_DATE == false)
        {
            JOptionPane.showMessageDialog(null, "Edit Date", "InfoBox: " + Title, JOptionPane.INFORMATION_MESSAGE);
        return;
        }
        if (existsColumn_PRODUCT_SET_VERSION == false)
        {
            JOptionPane.showMessageDialog(null, "Product Set Version", "InfoBox: " + Title, JOptionPane.INFORMATION_MESSAGE);
        return;
        }
        if (existsColumn_SEVERITY == false)
        {
            JOptionPane.showMessageDialog(null, "Severity", "InfoBox: " + Title, JOptionPane.INFORMATION_MESSAGE);
        return;
        }
        
        if (existsColumn_FIELD_EVENT == false)
        {
            JOptionPane.showMessageDialog(null, "Field / Event", "InfoBox: " + Title, JOptionPane.INFORMATION_MESSAGE);
        return;
        }
        if (existsColumn_OLD_VALUE == false)
        {
            JOptionPane.showMessageDialog(null, "Old Value", "InfoBox: " + Title, JOptionPane.INFORMATION_MESSAGE);
        return;
        }
        if ( existsColumn_NEW_VALUE == false)
        {
            JOptionPane.showMessageDialog(null, "New Value", "InfoBox: " + Title, JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if (existsColumn_CASE_OWNER == false)
        {
            JOptionPane.showMessageDialog(null, "Case Owner", "InfoBox: " + Title, JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if (existsColumn_STORY_POINTS == false)
        {
            JOptionPane.showMessageDialog(null, "Remarks", "InfoBox: " + Title, JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
         if (existsColumn_YOUR_ACCOUNT_NAME == false)
        {
            JOptionPane.showMessageDialog(null, "Your Account Name", "InfoBox: " + Title, JOptionPane.INFORMATION_MESSAGE);
            return;
        }
         if (existsColumn_SYSTEM_STATUS == false)
        {
            JOptionPane.showMessageDialog(null, "System Status", "InfoBox: " + Title, JOptionPane.INFORMATION_MESSAGE);
            return;
        }
         
          if (existsColumn_MODULE == false)
        {
            JOptionPane.showMessageDialog(null, "Module", "InfoBox: " + Title, JOptionPane.INFORMATION_MESSAGE);
            //   maybe that one is not 100% compulasry return;
        }
        
        if (existsColumn_ORIGINAL_PRIO == false)
        {
            JOptionPane.showMessageDialog(null, "Original priority", "InfoBox: " + Title, JOptionPane.INFORMATION_MESSAGE);
        return;
        }
        
        
        
    }
    public void btnExecuteActionPerformed(java.awt.event.ActionEvent evt) 
    {
        //////////////////////////////////////////
        // we imput csv data, check file validity, sort and save, then call process then save to csv and display
        //////////////////////////////////////
        
        earliestInterval =0; latestInterval =0;
       DefaultTableModel initialInputTbl;
        Cursor hourglassCursor = new Cursor(Cursor.WAIT_CURSOR);
        setCursor(hourglassCursor);

        
        Object[] columnnames;
        List csvRows = null;
        try {
            CSVReader CSVFileReader = new CSVReader(new FileReader(csvFile));
            csvRows = CSVFileReader.readAll();
        } catch (IOException ex) {
            Logger.getLogger(CSVFormatter.class.getName()).log(Level.SEVERE, null, ex);
        }
        
            columnnames = (String[]) csvRows.get(0);// this one is the input one (csv file we open)
            initialInputTbl = new DefaultTableModel(columnnames,  csvRows.size() - 1) {
                Class[] types = {Long.class, String.class, String.class,   
                                     DateTime.class, String.class, String.class, String.class, String.class, String.class, 
                                 String.class, String.class, String.class,  String.class,  String.class, String.class, String.class, String.class};

                @Override
                public Class getColumnClass(int columnIndex) {
                    
                    if  (columnIndex >=NUM_COLUMNS)
                    {
                        JOptionPane.showMessageDialog(null,"incorrect number of columns in csv file, expecting " + NUM_COLUMNS , "InfoBox: " + "file structure",  JOptionPane.INFORMATION_MESSAGE);
                        
                            columnIndex = NUM_COLUMNS -1;
                       
                    }
                    
                    return this.types[columnIndex];
                }
            };
            
        prepareInternalDataModels(csvRows.size());    
            
            
        DateTime EditDate, nonResBeginDate;
      
        System.out.print("size = ");    
        System.out.print(2* csvRows.size() - 1);
        
       String oldresolution;
        
        String SubStatus;
        int rowcount = 0;
        
        rowcount = csvRows.size() -1;
        LocalTime dateNow3 = LocalTime.now();
        
        System.out.println("start reading " + dateNow3 );

        boolean end = false;
        
        dateEndReport = new DateTime(1970, 01, 01, 00, 00);
        for (int x = 0; x < rowcount + 1 && !end; x++) 
        {
          // System.out.println("read x: " + x + " /" + rowcount );
          // if x = 0 this is the first row...skip it... data used for columnnames. 
          //Here we choudl check column titles and change th eenum
            if (x ==0)
            {   
                ValidateFileStructure( );
            }
            
            int columnnumber = 0;
            
            if (x > 0) 
            {
                for (String thiscellvalue : (String[]) csvRows.get(x)) 
                {
                    if (x == 55448)
                    {
                    int stophere = 1;
                    }
                     if (((String[]) csvRows.get(x)).length <3 || end 
                             || (thiscellvalue.equals("") && columnnumber ==CASE_NUMBER)
                             || (thiscellvalue.equals("") && columnnumber ==EDITED_BY )
                                || (thiscellvalue.equals("") && columnnumber ==EDIT_DATE)
                             || (thiscellvalue.equals("") && columnnumber ==FIELD_EVENT)
                            // || (thiscellvalue.equals("") && columnnumber ==OLD_VALUE)
                            // || (thiscellvalue.equals("") && columnnumber ==NEW_VALUE)
                            // || (thiscellvalue.equals("") && columnnumber ==STORY_POINTS)
                             ) // we get funny lines at teh end, copyright etc 
                    {
                            break; // i think thi si when reading teh bottom lines of teh csv file with SFDC advertisments
                    }        
                 
                    /*
                    if (((String[]) csvRows.get(x)).length != NUM_COLUMNS || end) 
                    {
                            break;
                    }
                    */
                    
                    EditDate = new DateTime();
                    if (columnnumber ==  CASE_NUMBER)
                    {
                            try {
                                Long.parseLong(thiscellvalue);
                                
                            } catch (NumberFormatException ex) {
                                end = true;
                                break;
                            }
                            initialInputTbl.setValueAt(Long.parseLong(thiscellvalue), x - 1, columnnumber);
                    }
                    else if (columnnumber == EDITED_BY)
                    {
                            initialInputTbl.setValueAt(thiscellvalue, x - 1, columnnumber);
                    }
                    else if (columnnumber == EDIT_DATE)
                    {
                            
                            if (thiscellvalue.indexOf('/') != -1)
                            {
                            EditDate = DateTime.parse(thiscellvalue,
                                 //  DateTimeFormat.forPattern("MM/dd/yy h:mm a"));//KAI
                                   DateTimeFormat.forPattern("dd/MM/yy HH:mm"));
                            }
                            else
                            {
                                EditDate = DateTime.parse(thiscellvalue,
                                    DateTimeFormat.forPattern("dd-MM-yy HH:mm"));
                            }
                            initialInputTbl.setValueAt(EditDate, x - 1, columnnumber);
                            
                            if (EditDate.isAfter(dateEndReport.getMillis()))
                            {
                              dateEndReport = EditDate;  
                            }
                            
                    }
                    else if (columnnumber == FIELD_EVENT)
                    {
                            initialInputTbl.setValueAt(thiscellvalue, x - 1, columnnumber);
                    }
                    else if (columnnumber == PRODUCT_SET_VERSION)
                    {
                            initialInputTbl.setValueAt(thiscellvalue, x - 1, columnnumber);
                    }
                    else if (columnnumber == SEVERITY)
                    {
                            initialInputTbl.setValueAt(thiscellvalue, x - 1, columnnumber);
                    }
                    
                    else if (columnnumber == OLD_VALUE)
                    {
                            initialInputTbl.setValueAt(thiscellvalue, x - 1, columnnumber);
                    }
                    else if (columnnumber ==  NEW_VALUE)
                    {
                            initialInputTbl.setValueAt(thiscellvalue, x - 1, columnnumber);
                    }
                    else if (columnnumber == CASE_OWNER)
                    {
                            initialInputTbl.setValueAt(thiscellvalue, x - 1, columnnumber);
                    }
                    else if (columnnumber ==  STORY_POINTS )
                    {
                            initialInputTbl.setValueAt(thiscellvalue, x - 1, columnnumber);
                    }
                    else if (columnnumber == YOUR_ACCOUNT_NAME)
                    {
                            initialInputTbl.setValueAt(thiscellvalue, x - 1, columnnumber);
                    }
                    else if (columnnumber == SYSTEM_STATUS)
                    {
                            initialInputTbl.setValueAt(thiscellvalue, x - 1, columnnumber);
                    }
                
                    else if (columnnumber == MODULE)
                    {
                            initialInputTbl.setValueAt(thiscellvalue, x - 1, columnnumber);
                    }
                
                    else if (columnnumber == ORIGINAL_PRIO)
                    {
                            initialInputTbl.setValueAt(thiscellvalue, x - 1, columnnumber);
                    }
                    columnnumber++;
                    int a = 0;
                    a = a+1;
                }
                    
            }
               
        }
        bottomPanel.formattedTf.setValue(new Date(dateEndReport.getMillis()));
        LocalTime dateNow4 = LocalTime.now();
           
        System.out.println("finish reading: " + dateNow4 );

        
       
        
        
        // #################### SOLVED CASES TABLE ##########################
        // we still rely on 3 private tbls to be ready to write to and the columns variable to be available
        Process( initialInputTbl);
        
        
        // ####################  WRITE TO CSV ##########################   
        WriteToCsv();
      
               
        
        // ####################   PREPARE THE LEFT PANNEL DATA ##########################
        createFinalOwnersList();
        createFinalTriagersList();
        createFinalAccountsList();
        

        // ######everity####################  PREPARE THE MIDDLE PANNEL DATA ##########################
        displayBaseTableModels();
        prepareDerivedTableModels("");
        displayDerivedTableModels();
        if (leftBottomPanel.rbOwner.isSelected())
        {
            leftBottomPanel.ownersList.setListData(ownersFinal);
        }
        else
        {
             if (leftBottomPanel.rbTriage.isSelected())
             {
                   leftBottomPanel.ownersList.setListData(triagersFinal);
             }
             else
             {
               if (leftBottomPanel.rbAccount.isSelected())
               {
                     leftBottomPanel.ownersList.setListData(AccountsFinal);
                   
               }   
             }
       
        }
         
        
            leftBottomPanel.activity_title ="";
        teamAverage = calculateTeamAverage();
        
        // teh list of periods should be ready for the preparation of teh graphs dataset from the 3 left Jtables
        //should we get id of this one
        finalizeCalendar();
        
        // what do we do in there?
        getDateFromInterval(20170601);
        buildPeriodCalendar();
        
        
        Cursor normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);
        setCursor(normalCursor);
        
    }// event_btnExecuteActionPerformed ##############################


    
    public void prepareInternalDataModels(int rowCount)
    {
        // these are for internal ones
            columnnamessolved = new String[]{"CASE_NUMBER", "CASE_OWNER","ACCOUNT", "SYSTEM_STATUS", "MODULE", "PRODUCT_SET_VERSION", "SEVERITY",  "CREATION_DATE",
               "ASSIGN_DATE", "STATUS_DATE",  "BEGIN_PERIOD", "STATUS", "FROM_STATUS", "TRIAGE_BY", "TRIAGE_STATUS", "T_TR", "T_L2", "T_L3", "T_CUST",
                "N_RESOLUTION",  "STORY_POINTS", "TIME_IN_STATE",  "SEV_DROP", "SEV_DROP_BELOW_2","ORIGINAL_PRIO"};
            tableModelFirstRes = new DefaultTableModel(columnnamessolved, 2*  rowCount) {
                Class[] types = {Long.class,String.class,String.class, String.class, String.class,String.class, DateTime.class, 
                DateTime.class, DateTime.class,Integer.class,  String.class, String.class,  String.class, String.class,
                Integer.class,Integer.class,Integer.class,Integer.class,Integer.class, Integer.class, Integer.class,  Integer.class, Integer.class, String.class};

                @Override
                public Class getColumnClass(int columnIndex) {
                    return this.types[columnIndex];
                }
            };
            //this is an intrnal one
            tableModelEachProgress = new DefaultTableModel(columnnamessolved, 2 * rowCount - 1) {  // FIX LATER MAKE TEH SIZE "DYNAMIC"  thanks to   "setSize() when we go overboad
                Class[] types = {Long.class, String.class,String.class,String.class, String.class,String.class,String.class, DateTime.class,
                DateTime.class, DateTime.class, Integer.class,  String.class,   String.class,  String.class, String.class,   
                Integer.class, Integer.class, Integer.class, Integer.class, Integer.class, Integer.class, Integer.class,  Integer.class, Integer.class,String.class};
                @Override
                public Class getColumnClass(int columnIndex) {
                    return this.types[columnIndex];
                }
            };
            tableModelAtDate = new DefaultTableModel(columnnamessolved, 2* rowCount - 1) {
                Class[] types = {Long.class, String.class, String.class,String.class,String.class, String.class,String.class,DateTime.class,
                DateTime.class, DateTime.class, Integer.class,  String.class,  String.class, String.class, String.class, 
                Integer.class, Integer.class, Integer.class, Integer.class,  Integer.class, Integer.class, Integer.class,  Integer.class, Integer.class,String.class};
                @Override
                public Class getColumnClass(int columnIndex) {
                    return this.types[columnIndex];
                }
            };

    }
    
    
    public void Process(DefaultTableModel initialInputTbl)
    {  
        long time_L2 =0, time_L3 = 0, time_cust = 0;
        String Account;
        String SystemStatus = "";
        String Module = "";
        long casenumber;
        int storypoints;
        Pattern pattern;
        Matcher matcher;
        //story points variabes
        String numberOnly;    
        String storyPointsBuffer;
        String foundPoints;
        String FieldEvent;
        String caseOwner;
        DateTime resolutiondate;
        DateTime  dateStartL3;
        int resolutioninterval;
        int resolutionyear;
        String resolution;
        String greyarearesolution;
        String lastgoodresolution;
        String nonResState;
        int nonResStateInterval, previousNonResStateInterval;
        DateTime EditDate, nonResBeginDate;
        int nonResStateBeginInterval;
        boolean finishcase = false;
        int Nresolution;
        String awaitingclient_headsup;
        String triagestatus;
        DateTime creationDate;
        String triageby;
        int SeverityDrop, SeverityDropBelow2;
        boolean bCaseAssigned = false;
        DateTime assignDate;
        boolean bPureCSSubStatus = false;
        boolean wasProgressedOnce= false;
        String ProductSetVersion;

        
        //some more initializations
        
        boolean bCheckBoxResolved= true, 
            bCheckBoxSolutionProvided = true, 
            bCheckBoxInformationProvided= true, 
            bCheckBoxDevelopment = true,
            bCheckBoxFixToFormalise= true, 
            bCheckBoxClientConfirmed = true,
            bCheckBoxClientUnconfirmed= true, 
            bCheckBoxFixDelivered = true,
            bCheckBoxFixAvailable = true,
            bCheckBoxEnhancementDelivered = true,
            bCheckBoxResolvedNoPhone = true,
            bCheckBoxResolvedByPhone = true,
            bCheckBoxMSDC  = true,
            bCheckBoxProductManagement= true,
            bCheckBoxPS = true,
            bCheckBoxSales= true, 
            bCheckBoxEngineering= true,  
            bCheckBoxRequestEngineering = true,
            bCheckBoxWorkaroundProvided = true;

        if (rightTopPanel != null)
        {
           if  ( !rightTopPanel.jCheckBoxResolved.isSelected() )
                bCheckBoxResolved= false;
           
                   
           if( rightTopPanel.jCheckBoxSolutionProvided.isSelected())
              bCheckBoxSolutionProvided =  true;
           else
               bCheckBoxSolutionProvided = false;
               
            if  ( rightTopPanel.jCheckBoxInformationProvided.isSelected())
                bCheckBoxInformationProvided = true;
            else
                bCheckBoxInformationProvided =false;
            
            if( rightTopPanel.jCheckBoxDevelopment.isSelected()) 
                bCheckBoxDevelopment = true;
            else
                bCheckBoxDevelopment = false;
                    
            if( rightTopPanel.jCheckBoxFixToFormalise.isSelected())
                bCheckBoxFixToFormalise = true;
            else
                bCheckBoxFixToFormalise = false;
            
            if( rightTopPanel.jCheckBoxClientConfirmed.isSelected())
                bCheckBoxClientConfirmed = true;
            else
                bCheckBoxClientConfirmed = false;
            
            if(! rightTopPanel.jCheckBoxClientUnconfirmed.isSelected())
                bCheckBoxClientUnconfirmed = false;
            
            if( !rightTopPanel.jCheckBoxFixDelivered.isSelected())
                bCheckBoxFixDelivered = false;
            
            if(!rightTopPanel.jCheckBoxFixAvailable.isSelected())
                bCheckBoxFixAvailable = false;
             
            if( !rightTopPanel.jCheckBoxEnhancementDelivered.isSelected())
                bCheckBoxEnhancementDelivered = false;
            
            if(! rightTopPanel.jCheckBoxResolvedNoPhone.isSelected())
                bCheckBoxResolvedNoPhone = false;
            
            if( !rightTopPanel.jCheckBoxResolvedByPhone.isSelected())
                bCheckBoxResolvedByPhone = false;
            
            if( !rightTopPanel.jCheckBoxMSDC.isSelected())
                bCheckBoxMSDC = false;
            
            if( !rightTopPanel.jCheckBoxProductManagement.isSelected())
                bCheckBoxProductManagement = false;
            
            if( !rightTopPanel.jCheckBoxPS.isSelected())
                bCheckBoxPS = false;
            
            if( !rightTopPanel.jCheckBoxSales.isSelected())
                bCheckBoxSales = false;
            
            if ( !rightTopPanel.jCheckBoxEngineering.isSelected())
                bCheckBoxEngineering  = false;
            
            if( !rightTopPanel.jCheckBoxRequestEngineering.isSelected() )
                bCheckBoxRequestEngineering = false;
            
            if( !rightTopPanel.jCheckBoxWorkaroundProvided.isSelected())
                bCheckBoxWorkaroundProvided = false;
        }
        
        
        
        ///// fisrt sort /////////////
         TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(initialInputTbl);
        List<RowSorter.SortKey> sortKeys = new ArrayList<>(3);
        sortKeys.add(new RowSorter.SortKey(CASE_NUMBER, SortOrder.ASCENDING));
        sortKeys.add(new RowSorter.SortKey(EDIT_DATE, SortOrder.ASCENDING));
        sortKeys.add(new RowSorter.SortKey(NEW_VALUE, SortOrder.ASCENDING));
        JTable sortedInputTbl  = new JTable();
        sortedInputTbl.setModel(initialInputTbl);
        sorter.setSortKeys(sortKeys);//setSortKeys seem to crash if we dont have teh expecte
        sortedInputTbl.setRowSorter(sorter);
        int rowcount =initialInputTbl.getRowCount() - 1;
        int x = rowcount;
        while (  ( initialInputTbl.getValueAt(x, 0) == null || (initialInputTbl.getValueAt(x, 0).toString()).trim().length() == 0)  && x>0  ) {
            initialInputTbl.removeRow(x);
            x--;
            
           System.out.println("remove X: " + x + " /" + rowcount );
          
        } ///  now it is sorted --------


        DateTime dateNow = DateTime.now();        
        ownersTemporary = new String[1000];
        triagersTemporary = new String[1000];
        AccountsTemporary = new String[1000];
        calendarTemporary = new int [100000];

        initializeCalendar();
        int intervalNow = getIntervalFromDate(dateEndReport);  
         int i = 0;
        setIncrementFirstRes(1);
        setIncrementAtDate(1);
        setIncrementEachProgress(1);
        // time_triage = 0;
         time_L2 =0; 
         time_L3 = 0;
         time_cust = 0;
         time_L2_start = 0;
         time_L3_start = 0;
         time_cust_start = 0;
         SystemStatus = "";                      
         Module = ""; 
         Account = ""; 



         
         
        while (i < sortedInputTbl.getRowCount()) 
        {
            SystemStatus = (String) sortedInputTbl.getValueAt(i, SYSTEM_STATUS);
            Module = (String) sortedInputTbl.getValueAt(i, MODULE);
            storypoints = 0; //timeInCS =0;
            casenumber = (Long) sortedInputTbl.getValueAt(i, CASE_NUMBER);
            Severity = sortedInputTbl.getValueAt(i, SEVERITY).toString();
            OriginalPrio = sortedInputTbl.getValueAt(i, ORIGINAL_PRIO).toString();
            // NO LOG System.out.println("i: " + i + " case: " + casenumber + "  IncrementFirstRes: " + getIncrementFirstRes() + "  getIncrementEachProgress(): " + getIncrementEachProgress() + "  incrementAtDate: " + getIncrementAtDate() );
            storyPointsBuffer = (String) sortedInputTbl.getValueAt(i, STORY_POINTS);
            storyPointsBuffer = storyPointsBuffer.replaceAll("\\s+", "");
            if (storyPointsBuffer.toLowerCase().contains("storypoints"))
            {
                pattern = Pattern.compile("(storypoints=.)");
                matcher = pattern.matcher(storyPointsBuffer.toLowerCase());
                if (matcher.find())
                {
                    foundPoints =  matcher.group(1);
                    numberOnly= foundPoints.replaceAll("[^0-9]", "");
                  // NOLOG  System.out.println("storry points for casenumber: "  + casenumber+ " : "  + numberOnly );
                  //  System.out.println("--- SP : "  + numberOnly ); 
                    storypoints = Integer.parseInt(numberOnly);
                }
            }
            if (casenumber == 1966511	       )  //  1910468  1916383 
            {
                int stophere;
                        stophere =1;
            }
            System.out.println("CASE: " + casenumber);
            FieldEvent = "";
            String newValue = "";
            String oldValue = "";
            caseOwner = "";
            resolutiondate = new DateTime(1970, 01, 01, 00, 00);
            nonResBeginDate = new DateTime(2016, 06, 01, 00, 00);
            dateStartL3 = new DateTime(1970, 01, 01, 00, 00);
            resolutioninterval = 1;
            resolutionyear = 1970;
            resolution = "";
            greyarearesolution = "";
            lastgoodresolution = "";
            nonResState = "";
            nonResStateInterval =previousNonResStateInterval =0;
            nonResStateBeginInterval = 0;  
            finishcase = false;
            Nresolution = 0;
            awaitingclient_headsup = "";
            //timeInCS = 0; timeAtCustomer=0; timeInL3=0;
            triageby = "";
            triagestatus="";
            system = "";
            creationDate = new DateTime(1970, 01, 01, 00, 00);
            Account = "";
            SeverityDrop = 0;
            SeverityDropBelow2 = 0;
            bCaseAssigned = false;
            assignDate = new DateTime(1970, 01, 01, 00, 00);
            bPureCSSubStatus = false;
            wasProgressedOnce= false;
            Triage triageTracker;
            triageTracker=  new Triage(casenumber, Severity);


            do //////////////////////////////////////    LOOPING ONE ONE SINGLE CASE  //////////////////////////////////////
            {
                try {
                    FieldEvent = sortedInputTbl.getValueAt(i, FIELD_EVENT).toString();
                } catch (Exception ex) {
                    finishcase = true;
                    break;
                }
                system = sortedInputTbl.getValueAt(i, SYSTEM_STATUS).toString();
                ProductSetVersion = sortedInputTbl.getValueAt(i, PRODUCT_SET_VERSION).toString();
                EditDate = (DateTime) sortedInputTbl.getValueAt(i, EDIT_DATE);
                newValue = sortedInputTbl.getValueAt(i, NEW_VALUE).toString();
                caseOwner = sortedInputTbl.getValueAt(i, CASE_OWNER).toString();
                Account = sortedInputTbl.getValueAt(i, YOUR_ACCOUNT_NAME).toString();
                if (isOwner(caseOwner)== false)
                {   
                    addOwner(caseOwner);
                }


                 ////////////////////// get accout data ////////////////////////
                if (isAccount(Account)== false)
                {   
                   addAccount(Account);
                }


                ///////////////////////// assigned?  start counting L2 L3 and customer time /////////////
                if(  FieldEvent.equals("Case Owner"))
                {
                    time_L2_start  = getIntervalFromDate(EditDate);
                    ///time_L3_start = getIntervalFromDate(EditDate);
                    time_cust_start= getIntervalFromDate(EditDate);
                    bCaseAssigned = true;
                    time_L2 = 0;
                    assignDate = EditDate;

                }


                ////////////////////// get severity downgrades //////////////////////
                if ( FieldEvent.equals("Severity"))  
                {

                    oldValue = sortedInputTbl.getValueAt(i, OLD_VALUE).toString();
                    if (!oldValue.equals(""))
                    {
                        triageTracker.setSeverityChangeData(oldValue, newValue, EditDate);

                    }
                }


                ////////////////////// get triage data /////////////////////////////////////
                if ( FieldEvent.equals("Triage Status"))  
                {
                    if (!newValue.equals(""))
                    {
                        triagestatus= newValue;
                        if(  (newValue.equals("Resolved No Phone")) ||   
                             (newValue.equals("Processed No Phone") )|| 
                             (newValue.equals("Resolved By Phone")) || 
                             (newValue.equals("Processed By Phone")) ||
                             (newValue.equals("Triage Not Required")) 
                               // || newValue.equals("In Level 1")) 
                                )    
                        {/// we exit triage, time to count how much time we spent there
                            triageTracker.setExitTriage(EditDate);
                        }
                    }
                } 
                if ( FieldEvent.equals("Triage By"))  
                {
                    if (!newValue.equals(""))
                    {
                        triageby= newValue;
                         if (isTriager(triageby)== false)
                        {   
                            addTriager(triageby);
                        }
                     }
                }

                if ( FieldEvent.equals("Created.") )
                {    
                    creationDate =    EditDate; //DateTime.parse(newValue);
                }    

                ////////////////////////////////   do L3 time measures ///////////////////////////////////////
                 // firstly we take care of timeL3 stats  (dev + product management)
                if (  newValue.equals("Development") ||   newValue.equals("Product Management") 
                        || newValue.equals("Product Management Review") || newValue.equals("Engineering")
                        || newValue.equals("Request Engineering")
                        )
                {
                    if(dateStartL3.equals(new DateTime(1970, 01, 01, 00, 00)) )
                        {// we are entering L3, we start counting from dateStartL3
                            dateStartL3 = EditDate;
                        }// otherwise we do noting as we can move for instance from product management to dev 
                        // and this is continuous L3 time for us.    
                }
                else
                {
                    // calculate the L3 duration --- if we have a datestartL3 of course
                    // and if we have a substatus change only  
                    //(for now we consider that anyt substatus apart from thr dev. amd productManagement ones mean that we exited dev.)
                    if(!dateStartL3.equals(new DateTime(1970, 01, 01, 00, 00)) &&  FieldEvent.equals("Sub-Status") )
                    {
                        long diff1 = DateDifference(dateStartL3, EditDate);
                        if (   time_L3_start != 0)
                        {
                            time_L3 = time_L3 + diff1;
                        }    
                        // reset the L3 start to zero ... but we do no reinitialize time_L3 as we can go oto dev. severa ties on one case
                        dateStartL3 = new DateTime(1970, 01, 01, 00, 00);
                    }
                }




                ////////////////////////////////// return the case to work /////////////////////////////////////
                if ( FieldEvent.equals("Sub-Status") && (    newValue.equals("CS") || newValue.equals("") 
                        || newValue.equals("Assigned for Analysis") || newValue.equals("Awaiting Client") 
                        || newValue.equals("In Triage")   || newValue.equals("Awaiting Triage")   )) 
                {
                    if (newValue.equals("CS"))
                    {
                        bPureCSSubStatus = true;
                        if ( (nonResState == "") && (wasProgressedOnce== true))
                        {// this is only for reopens
                            oldValue = sortedInputTbl.getValueAt(i, OLD_VALUE).toString();
                        }
                    }
                    //start clocking triage time
                    if (newValue.equals("Awaiting Triage"))
                    {
                         bPureCSSubStatus = false;
                    }
                    else if ( (newValue.equals("In Triage")) /*||   (newValue.equals("In Level 1"))*/ )
                    {
                        bPureCSSubStatus = false;
                            triageTracker.setEnterTriage(EditDate);
                    }// clocking triage time has started


                      //maybe it had started before and triage can end if we go to CS with a non empty starttriagedate
                    else if (triageTracker.getEnterTriage() != null   )
                    {  
                        triageTracker.setExitTriage(EditDate);
                    }

                    if (newValue.equals(""))
                    {
                        bPureCSSubStatus = false;
                       // newValue= "Awaiting Client"; !! mistake - > this is the initial status before entering triage
                         newValue= "CS";
                    }


                    if (  newValue.equals("Assigned for Analysis")    ||    (    newValue.equals("In Triage")     || newValue.equals("Awaiting Triage")  ) )
                    { 
                        bPureCSSubStatus = false;
                        newValue = "CS";  // those  sub-statuses mean the same to us in terms of "CS side vs customer side ")
                          //still we use that in our calculation of time in triage
                        // be careful that the CS time includes the triage time!!
                    }
                    nonResStateInterval = getIntervalFromDate(EditDate);

                    if ( (   !nonResState.equals(newValue)  && nonResState != ""  )  
                           || (   (nonResStateInterval > previousNonResStateInterval) && previousNonResStateInterval !=0  && nonResState.equals(newValue)   )   )    
                    {        /// we go from one non res state to another.
                            // so we count the number of intervals of the previous one

                        //calculating time in L2 and time awaiting customer 
                        long diff1 = DateDifference(nonResBeginDate, EditDate);
                        if (nonResState.equals("CS") &&       bPureCSSubStatus == true)
                        {
                           time_L2 = time_L2 + diff1;
                        }
                        else
                        {
                            if (nonResState.equals("Awaiting Client") )
                            {
                                    time_cust = time_cust + diff1;
                                    oldValue = ""; //only "reopens" are noted
                            }

                            if (nonResState.equals(""))
                            {
                                oldValue = ""; //only "reopens" are noted
                            }
                        }

                            if (  ( !newValue.equals("Awaiting Client") )  
                                || 
                                  (  newValue.equals("Awaiting Client") 
                                    && ( rightBottomPanel == null || !rightBottomPanel.jCheckBoxShowAwaitingClientAfterProgress.isSelected()  )
                                    && !resolution.equals("")
                                  )
                                ||
                                    (    newValue.equals("Awaiting Client") 
                                       && ( rightBottomPanel == null || rightBottomPanel.jCheckBoxShowAwaitingClientAfterProgress.isSelected()  )
                                    )
                               )
                            {   /// with tis option the awaiting client after dev we decide did not happem    
                                WriteNonResIntervals(nonResStateBeginInterval,nonResStateInterval-1,  casenumber, caseOwner,ProductSetVersion, Severity, OriginalPrio,
                                    creationDate,assignDate, nonResBeginDate,  nonResState, oldValue,
                                     triageTracker.getDuration(), time_L2 , time_L3 , time_cust,
                                    triageby, triagestatus, storypoints, 
                                    Account, triageTracker.getSeverityDrop(), SeverityDropBelow2 , SystemStatus, Module,
                                    triageTracker);
                            }
                        }

                    //reaady for next loops
                    if (  ( !newValue.equals("Awaiting Client") )  
                        || 
                          (  newValue.equals("Awaiting Client") 
                            && (rightBottomPanel == null || !rightBottomPanel.jCheckBoxShowAwaitingClientAfterProgress.isSelected() )
                            && !resolution.equals("")
                          )
                        ||
                            (    newValue.equals("Awaiting Client") 
                               && (rightBottomPanel == null || rightBottomPanel.jCheckBoxShowAwaitingClientAfterProgress.isSelected()  )
                            )
                       )
                    {   /// with tis option the awaiting client after dev we decide did not happem  
                                            //we cancel any resolution we had, but save it as a lastgoodresolution first
                        lastgoodresolution = resolution;
                        resolution = "";
                        resolutiondate = new DateTime(1970, 01, 01, 00, 00);
                        resolutionyear = resolutiondate.getYear();
                        nonResState = newValue;
                        previousNonResStateInterval = nonResStateInterval;
                        nonResStateBeginInterval =  nonResStateInterval;
                        nonResBeginDate = EditDate;
                        //nonResStateYear = EditDate.getYear();
                        if (newValue.equals("Awaiting Client")) {
                            awaitingclient_headsup = "Awaiting Client flagged";
                        }
                    }                   

                }


                else   ////////////////// if the record is not a non-resolution, then maybe it tis a resolution
                {       ////////////////////////////  DO WE HAVE A RESOLUTION ////////////////////////////////////////////////////////////////

                       if (  FieldEvent.equals("Sub-Status")  && (
                            ( bCheckBoxResolved && newValue.equals("Resolved") ) || 
                            ( bCheckBoxSolutionProvided && newValue.equals("Solution Provided") ) || 
                            ( bCheckBoxInformationProvided && newValue.equals("Information Provided") )|| 
                            ( bCheckBoxDevelopment && newValue.equals("Development")) || 
                            ( bCheckBoxFixToFormalise && newValue.equals("Fix to formalise")) || 
                            ( bCheckBoxClientConfirmed && newValue.equals("Client Confirmed")) || 
                            ( bCheckBoxClientUnconfirmed && newValue.equals("Client Unconfirmed") )||
                            ( bCheckBoxFixDelivered && newValue.equals("Fix Delivered") )|| 
                            ( bCheckBoxFixAvailable && newValue.equals("Fix Available")) ||
                            ( bCheckBoxEnhancementDelivered && newValue.equals("Enhancement Delivered")) ||
                            ( bCheckBoxResolvedNoPhone && newValue.equals("Resolved No Phone") )||
                            ( bCheckBoxResolvedByPhone && newValue.equals("Resolved By Phone")) ||
                            ( bCheckBoxMSDC && newValue.equals("MSDC")) ||
                            ( bCheckBoxProductManagement && (newValue.equals("Product Management") || newValue.equals("Product Management Review")) ) ||
                            ( bCheckBoxPS && newValue.equals("PS") )||
                            ( bCheckBoxSales && newValue.equals("Sales")) ||
                            ( bCheckBoxEngineering && newValue.equals("Engineering") )||
                            ( bCheckBoxRequestEngineering &&  newValue.equals("Request Engineering") )||
                            ( bCheckBoxWorkaroundProvided && newValue.equals("Workaround Provided") )
                        ))
                    {
                        wasProgressedOnce= true; // for "reopens" reproting
                        if (resolutiondate.equals(new DateTime(1970, 01, 01, 00, 00))) 
                        {  // if we dont have a progress yet
                            resolutiondate = EditDate;
                            resolutionyear = resolutiondate.getYear();
                            resolutioninterval = getIntervalFromDate(resolutiondate);

                             // first we write down what was befor (non resolution state if there was one)
                             if (nonResStateBeginInterval != 0)
                            {
                                long diff1 = DateDifference(nonResBeginDate, EditDate);
                                if (nonResState.equals("CS")  &&       bPureCSSubStatus == true)
                                {
                                    time_L2 = time_L2 + diff1;
                                     if (casenumber == 1669575	       )  //  1910468  1916383 
                                     {
                                            long report = time_L2/24;
                                             System.out.println("case 1669575  time_l2: " + report);
                                     }


                                }
                                else
                                {
                                    if (nonResState.equals("Awaiting Client") )
                                    {
                                         time_cust = time_cust + diff1;
                                         oldValue = ""; //only "reopens" are noted
                                    }
                                    // TODO NOW else if  in triage
                                }

                                if (nonResState.equals(""))
                                {
                                    oldValue = ""; //only "reopens" are noted
                                }
                                WriteNonResIntervals(nonResStateBeginInterval,resolutioninterval-1,  casenumber, caseOwner, ProductSetVersion, 
                                        Severity, OriginalPrio,creationDate,assignDate, nonResBeginDate,  nonResState,oldValue,  
                                         triageTracker.getDuration(), time_L2, time_L3, time_cust,
                                        triageby, triagestatus,storypoints, Account,   triageTracker.getSeverityDrop(), SeverityDropBelow2 , SystemStatus, Module,
                                        triageTracker);

                                // and if we cam from triage ...
                                triageTracker.setExitTriage(EditDate);

                            }    
                            resolution = newValue;
                            Nresolution = Nresolution + 1;
                            if (newValue.equals("PS")) {
                                greyarearesolution = "PS";
                            }
                            if (newValue.equals("Sales")) {
                                greyarearesolution = "SALES";
                            }

                            String weirdpath1 = weirdpath(resolution, lastgoodresolution);

                            if (Nresolution == 1) {
                                AddRecord("FirstRes", getIncrementFirstRes(), casenumber,caseOwner,ProductSetVersion, Severity,OriginalPrio,
                                        creationDate,assignDate,resolutiondate,resolutioninterval, resolution, "", 
                                        triageTracker.getDuration(), time_L2, time_L3 , time_cust,
                                        triageby, triagestatus,
                                        Nresolution, greyarearesolution, awaitingclient_headsup, weirdpath1, storypoints,0, 
                                        Account,  triageTracker.getSeverityDrop(), SeverityDropBelow2, SystemStatus, Module, triageTracker); 
                                //bumpIncrementFirstRes();
                            }
                            AddRecord("EachProgress", getIncrementEachProgress(), casenumber,caseOwner,ProductSetVersion, Severity,OriginalPrio,
                                    creationDate, assignDate, resolutiondate , resolutioninterval, resolution,"",
                                     triageTracker.getDuration(), time_L2 , time_L3 , time_cust, triageby, 
                                    triagestatus, Nresolution,greyarearesolution,awaitingclient_headsup,weirdpath1,storypoints,0, 
                                    Account,  triageTracker.getSeverityDrop(), SeverityDropBelow2, SystemStatus, Module, triageTracker); 
                            //bumpIncrementEachProgress();
                        }

                        //then we cancel any potential previous "non resoluton" as we are now past a resolution
                        nonResState = "";
                        nonResStateBeginInterval=0;

                    }
                } 

                if (i + 1 < sortedInputTbl.getRowCount() && casenumber == (Long) sortedInputTbl.getValueAt(i + 1, CASE_NUMBER)) {
                    i++;
                } else 
                {
                    finishcase = true;
                    i++;
                    if (resolutionyear != 1970) 
                    {
                        String weirdpath1 = weirdpath(resolution, lastgoodresolution);
                        AddRecord("AtDate", getIncrementAtDate(), casenumber,caseOwner,ProductSetVersion, Severity,OriginalPrio,
                                creationDate,assignDate, resolutiondate,resolutioninterval, 
                                resolution, "",  triageTracker.getDuration(), time_L2 , time_L3 , time_cust,
                                triageby, triagestatus, Nresolution,
                                greyarearesolution,awaitingclient_headsup,weirdpath1,storypoints,0, 
                                Account,   triageTracker.getSeverityDrop(), SeverityDropBelow2, SystemStatus, Module, triageTracker);
                        //bumpIncrementAtDate(); //? do we need to add one more line when reaching teh end of the file read

                         // calculate the L3 duration (till end of report) if we have a datestartL3  
                         // which means that something went to dev and has not come back yet
                        if(!dateStartL3.equals(new DateTime(1970, 01, 01, 00, 00)) )
                        {
                            long diff1 = DateDifference(dateStartL3, dateEndReport);
                            if(time_L3_start != 0)
                            {
                                time_L3 = time_L3 + diff1;
                            }
                            // reset the L3 start to zero ... but we do no reinitialize time_L3 as we can go oto dev. severa ties on one case
                            dateStartL3 = new DateTime(1970, 01, 01, 00, 00);
                        }
                    }  
                    else
                    {
                        if (nonResStateBeginInterval !=0)// 0 means that nothing relevant was found, no 'non-resolution' state mentionned, no need to report that case
                        {
                            long diff1 = DateDifference(nonResBeginDate, dateEndReport);//  (calculate duratioon till end of report) 
                            if (nonResState.equals("CS") &&       bPureCSSubStatus == true)
                            {
                               time_L2 = time_L2 + diff1;
                               if (casenumber == 1669575	       )  //  1910468  1916383 
                               {
                                   long report = time_L2/24;
                                    System.out.println("case 1669575  time_l2: " + report);
                               }
                            }
                            else
                            {
                                if (nonResState.equals("Awaiting Client") )
                                {
                                    time_cust = time_cust + diff1;
                                    oldValue = ""; //only "reopens" are noted
                                }
                            }
                            if (nonResState.equals(""))
                            {
                                oldValue = ""; //only "reopens" are noted
                            }
                            WriteNonResIntervals(nonResStateBeginInterval,intervalNow+1,  casenumber, caseOwner, ProductSetVersion, Severity,OriginalPrio,
                            creationDate, assignDate, nonResBeginDate, nonResState, oldValue,
                             triageTracker.getDuration(), time_L2, time_L3 , time_cust,
                            triageby, triagestatus,storypoints, Account ,  triageTracker.getSeverityDrop(), SeverityDropBelow2, SystemStatus, Module,
                            triageTracker);
                        } 
                     }
                    // time_triage = 0;
                     time_L2 =0; 
                     time_L3 = 0;
                     time_cust = 0;
                     time_L2_start = 0;
                     time_cust_start = 0;
                     time_L3_start = 0;

                }
            } while (finishcase == false); // end looping on one case

        } // end looping on all  cases


        // trim the TableModels
        LocalTime dateNow1 = LocalTime.now();
        int nremoved = 0;
        for (int r = tableModelFirstRes.getRowCount() - 1; r >= getIncrementFirstRes(); r--) {
            tableModelFirstRes.removeRow(r);
            nremoved++;
        }
        LocalTime dateNow2 = LocalTime.now();
        int time_ms = dateNow2.compareTo(dateNow1);
        System.out.println("remove from FirstRes: " + nremoved + " down to " + getIncrementFirstRes() + "in: " +  time_ms  );
        dateNow1 = LocalTime.now();

        for (int r = tableModelEachProgress.getRowCount() - 1; r >= getIncrementEachProgress(); r--) {
            tableModelEachProgress.removeRow(r);
        }
        dateNow2 = LocalTime.now();
        time_ms = dateNow2.compareTo(dateNow1);
        System.out.println("remove from EachProgress: " +  nremoved + " down to " + getIncrementEachProgress() + "in: " +  time_ms  );

        dateNow1 = LocalTime.now();
        for (int r = tableModelAtDate.getRowCount() -1 ; r >= getIncrementAtDate() ; r--) {
            tableModelAtDate.removeRow(r);
        }

        dateNow2 = LocalTime.now();
        time_ms = dateNow2.compareTo(dateNow1);
        System.out.println("remove from AtDate : " +  nremoved + " down to " + getIncrementAtDate() + "in: " +  time_ms  );


       
    }

    
    private void WriteToCsv()
    {
        try {
            exportJTableToCSV(tableModelEachProgress, (String[]) columnnamessolved, "EachProgress");
            exportJTableToCSV(tableModelFirstRes, (String[]) columnnamessolved, "FirstRes");
            exportJTableToCSV(tableModelAtDate, (String[]) columnnamessolved, "AtDate");
        } catch (IOException ex) {
            Logger.getLogger(CSVFormatter.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    
    
    private void exportJTableToCSV(TableModel tm, String[] headers, String filename) throws IOException {
        BufferedWriter writer = Files.newBufferedWriter(Paths.get(filename + ".csv"));
        ArrayList<String> columnNames = new ArrayList<String>();  // YTODO NEED THIS?
     
        /*
        for (int i = 0; i < tm.getColumnCount(); i++) {
            columnNames.add(tm.getColumnName(i));
        }
        */
        
        
        CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                .withHeader(headers));
        for (int i = 0; i < tm.getRowCount(); i++) {
            ArrayList<Object> values = new ArrayList<Object>();
            for (int j = 0; j < tm.getColumnCount(); j++) {
                values.add(tm.getValueAt(i, j));
            }
            csvPrinter.printRecord(values);
        }

        csvPrinter.flush();
        csvPrinter.close();;
    }

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
       
        
        /*
             HttpClient httpclient = HttpClientBuilder.create().build();
 
        // Assemble the login request URL
        String loginURL = LOGINURL +
                          GRANTSERVICE +
                          "&amp;client_id=" + CLIENTID +
                          "&amp;client_secret=" + CLIENTSECRET +
                          "&amp;username=" + USERNAME +
                          ";password=" + PASSWORD;
 
        // Login requests must be POSTs
        //HttpPost httpPost = new HttpPost(loginURL);
        
        
        //HttpPost httpPost = new HttpPost("https://login.salesforce.com/?un=vincent.rosinach@misys.com&pw=Misys890");
        
        //HttpPost httpPost = new HttpPost("https://login.salesforce.com/login.jsp?un=vincent.rosinach@misys.com&pw=Misys890398474&startURL=/00O0J000007PjCT?csv=1");
        
        
        //HttpPost httpPost = new HttpPost("https://misys.my.salesforce.com/00O0J000007PjCT?csv=1");
        
        
        //https://login.salesforce.com/login.jsp?un=INPUTEMAIL&pw=INPUTPASSWORD&startURL=/00O0J000007PjCT?csv=1
        
        
        //HttpPost httpPost = new HttpPost("https://login.salesforce.com/login.jsp?un=vincent.rosinach@misys.com&pw=Misys890398474&startURL=/00O0J000007PjCT?csv=1");
            HttpPost httpPost = new HttpPost("https://margo.convertio.me/p/4f9N84k8hc1iRBBcllRopQ/96e6fd25c877041eb792f0cd1cd3498f/zarcik1.png");
        
        
        HttpResponse response = null;
 
        try {
            // Execute the login POST request
            response = httpclient.execute(httpPost);
        } catch (ClientProtocolException cpException) {
            cpException.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
 
        // verify response is HTTP OK
        final int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode != HttpStatus.SC_OK) {
            System.out.println("Error authenticating to Force.com: "+statusCode);
            // Error is in EntityUtils.toString(response.getEntity())
            return;
        }
 
        String getResult = null;
        try {
            getResult = EntityUtils.toString(response.getEntity());
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        JSONObject jsonObject = null;
        String loginAccessToken = null;
        String loginInstanceUrl = null;
        try {
            jsonObject = (JSONObject) new JSONTokener(getResult).nextValue();
            loginAccessToken = jsonObject.getString("access_token");
            loginInstanceUrl = jsonObject.getString("instance_url");
        } catch (JSONException jsonException) {
            jsonException.printStackTrace();
        }
        System.out.println(response.getStatusLine());
        System.out.println("Successful login");
        System.out.println("  instance URL: "+loginInstanceUrl);
        System.out.println("  access token/session ID: "+loginAccessToken);
        accessToken = loginAccessToken;
        //use this accesstoken along with Get request to get reports
        // release connection
        httpPost.releaseConnection();
        String reportsName = "VRTRY1";
        String reportsURL = "yourInstance.salesforce.com/services/data/v35.0/analytics/reports/"+reportsName+"?includeDetails=true";
        
        HttpGet httpGet = new HttpGet(reportsURL);
        try {
			httpGet.setHeader("Authorization", "Bearer "+accessToken);
			response = httpclient.execute(httpGet);
			//This is a synchronous call, response is in json format

			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        		
      
        
        */
        
        
        csvFile = null;
       // lblFileInfo.setText("No file selected.");
        tableModelAtDate.setRowCount(0);
        tableModelEachProgress.setRowCount(0);
        tableModelFirstRes.setRowCount(0);
        tableModelAggrAtDate.setRowCount(0);
        tableModelAggrEachProgress.setRowCount(0);
        tableModelAggrFirstRes.setRowCount(0);
    }//GEN-LAST:event_btnClearActionPerformed

    
    
    
    public void rbEachProgressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbEachProgressActionPerformed
        if (leftTopPanel.rbEachProgress.isSelected() && tableModelEachProgress != null) {
            middleTopPanel.jTableDetail.setModel(tableModelEachProgress);
            leftTopPanel.jTableProgressed.setModel(tableModelAggrEachProgress);
            leftTopPanel.jTableCS.setModel(tableModelCSEachProgress);
            leftTopPanel.jTableClient.setModel(tableModelAwaitingClientEachProgress);    
            
            DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
            renderer.setHorizontalAlignment(JLabel.CENTER);
            leftTopPanel.jTableProgressed.getColumnModel().getColumn(2).setCellRenderer(renderer);
            renderer = new DefaultTableCellRenderer();
            renderer.setHorizontalAlignment(JLabel.CENTER);
            leftTopPanel.jTableProgressed.getColumnModel().getColumn(0).setCellRenderer(renderer);
            leftTopPanel.jTableProgressed.setAutoCreateRowSorter(true);

            middleTopPanel.jTableDetail.scrollRectToVisible(new Rectangle(middleTopPanel.jTableDetail.getCellRect(0, 0, true)));
            leftTopPanel.jTableProgressed.scrollRectToVisible(new Rectangle( leftTopPanel.jTableProgressed.getCellRect(0, 0, true)));
            
        }
    }//GEN-LAST:event_rbEachProgressActionPerformed

    public void rbFirstResActionPerformed(java.awt.event.ActionEvent evt) {
        if (leftTopPanel.rbFirstRes.isSelected() && tableModelFirstRes != null) {
            middleTopPanel.jTableDetail.setModel(tableModelFirstRes);
            leftTopPanel.jTableProgressed.setModel(tableModelAggrFirstRes);
            leftTopPanel.jTableCS.setModel(tableModelCSFirstRes); 
            leftTopPanel.jTableClient.setModel(tableModelAwaitingClientFirstRes);    
           
            
            
            DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
            renderer.setHorizontalAlignment(JLabel.CENTER);
            leftTopPanel.jTableProgressed.getColumnModel().getColumn(2).setCellRenderer(renderer);
            renderer = new DefaultTableCellRenderer();
            renderer.setHorizontalAlignment(JLabel.CENTER);
            leftTopPanel.jTableProgressed.getColumnModel().getColumn(0).setCellRenderer(renderer);
            leftTopPanel.jTableProgressed.setAutoCreateRowSorter(true);

            middleTopPanel.jTableDetail.scrollRectToVisible(new Rectangle(middleTopPanel.jTableDetail.getCellRect(0, 0, true)));
            leftTopPanel.jTableProgressed.scrollRectToVisible(new Rectangle(leftTopPanel.jTableProgressed.getCellRect(0, 0, true)));
           
        }
    }

    public void rbAtDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbAtDateActionPerformed
        if (leftTopPanel.rbAtDate.isSelected() && tableModelAtDate != null) {
            middleTopPanel.jTableDetail.setModel(tableModelAtDate);
            leftTopPanel.jTableProgressed.setModel(tableModelAggrAtDate);
            leftTopPanel.jTableClient.setModel(tableModelAwaitingClientAtDate);
            leftTopPanel.jTableCS.setModel(tableModelCSAtDate);

            DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
            renderer.setHorizontalAlignment(JLabel.CENTER);
            leftTopPanel.jTableProgressed.getColumnModel().getColumn(2).setCellRenderer(renderer);
            renderer = new DefaultTableCellRenderer();
            renderer.setHorizontalAlignment(JLabel.CENTER);
            leftTopPanel.jTableProgressed.getColumnModel().getColumn(0).setCellRenderer(renderer);
            leftTopPanel.jTableProgressed.setAutoCreateRowSorter(true);

            middleTopPanel.jTableDetail.scrollRectToVisible(new Rectangle(middleTopPanel.jTableDetail.getCellRect(0, 0, true)));
            leftTopPanel.jTableProgressed.scrollRectToVisible(new Rectangle(leftTopPanel.jTableProgressed.getCellRect(0, 0, true)));
        }
    }//GEN-LAST:event_rbAtDateActionPerformed

    public void tblProgressedMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProgressedMouseClicked
        int index = leftTopPanel.jTableProgressed.getSelectedRow();
        leftTopPanel.jTableCS.clearSelection();
        leftTopPanel.jTableClient.clearSelection();
        
        
        // get the interval and the owner from that libe
       String interval = (String)leftTopPanel.jTableProgressed.getValueAt(index, 0);
       String owner = (String)leftTopPanel.jTableProgressed.getValueAt(index, 1);
       String WhereFrom = "";
       
       ShowDetail( interval,  owner, WhereFrom);
       
       
    }

    
  
    
    public void tblCSSideMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCSSideMouseClicked
       int index = leftTopPanel.jTableCS.getSelectedRow();
        leftTopPanel.jTableProgressed.clearSelection();
        leftTopPanel.jTableClient.clearSelection();
        

        // get the interval and the owner from that libe
       String interval = (String)leftTopPanel.jTableCS.getValueAt(index, 0);
       String owner = (String)leftTopPanel.jTableCS.getValueAt(index, 1);
       String WhereFrom = "CS";
       
       ShowDetail( interval,  owner, WhereFrom);
       
    }//GEN-LAST:event_tblCSSideMouseClicked

    public void tblClientSideMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblClientSideMouseClicked
        int index = leftTopPanel.jTableClient.getSelectedRow();
        leftTopPanel.jTableProgressed.clearSelection();
        leftTopPanel.jTableCS.clearSelection();
        // get the interval and the owner from that libe
       String interval = (String)leftTopPanel.jTableClient.getValueAt(index, 0);
       String owner = (String)leftTopPanel.jTableClient.getValueAt(index, 1);
       String WhereFrom = "Awaiting Client";
       
       ShowDetail( interval,  owner, WhereFrom);
    }//GEN-LAST:event_tblClientSideMouseClicked
    
    
    
    private boolean filterOutSeverityOrSystem(Triage triageTracker)
    {       
        if (rightMiddlePanel == null  /* JUnit testing */)
            return false;
        
        if(      (   triageTracker.getInitialSeverity().equals("1 - Critical")   )  
                &&
                (!rightMiddlePanel.jCheckBoxCritical.isSelected()) )
        {
            return true;
        }
        
        if(  (  triageTracker.getInitialSeverity().equals("1 - Showstopper")   )
                &&
                (!rightMiddlePanel.jCheckBoxShowstopper.isSelected())   )
        {
            return true;
        }
        
        if( (   triageTracker.getInitialSeverity().equals("2 - Blocker")   )
                 && 
                (!rightMiddlePanel.jCheckBoxShowBlocker.isSelected()) )
        {
            return true;
        }
        
        
        if( (  triageTracker.getInitialSeverity().equals("2 - High")   )
                
                && 
                (!rightMiddlePanel.jCheckBoxShowHigh.isSelected()) )
        {
            return true;
        }
        
        
        if( (  triageTracker.getInitialSeverity().equals("3 - Medium")  )
                && 
                (!rightMiddlePanel.jCheckBoxShowMedium.isSelected()) )
        {
            return true;
        }
        
        
        if(   triageTracker.getInitialSeverity().equals("4 - Low")   
                &&
                (!rightMiddlePanel.jCheckBoxShowLow.isSelected()) )
        {
            return true;
        }
        
        
        if( (system.equals("Live")) &&  (!rightAfterMiddlePanel.jCheckBoxLive.isSelected()) )
        {
            return true;
        }
        
        
        if( (system.equals("Implementation")) &&  (!rightAfterMiddlePanel.jCheckBoxImplementation.isSelected()) )
        {
            return true;
        }
        
        
        if(  (!system.equals("Live")) && (!system.equals("Implementation")) && (!rightAfterMiddlePanel.jCheckBoxShowAllOthers.isSelected()) )
        {
            return true;
        }
        
        return false;
    }   
    
    
    private void WriteNonResIntervals(int beginInterval,int endInterval, long casenumber,String caseOwner, String ProductSetVersion,String Severity,String OriginalPrio,
            DateTime creationDate, DateTime assignDate, DateTime EditDate, String nonResState, String oldValue, 
            long time_triage, long time_L2, long time_L3 ,  long time_cust,
            String triageby, String triagestatus,
            int storypoints, String Account,  int SeverityDrop, int SeverityDropBelow2, String SystemStatus, String Module,
            Triage triageTracker)
    {
        
            // only if we are on the selected severity and system status
         if (filterOutSeverityOrSystem(triageTracker) == true)
         {
             return;
         }

        
        String s, p;  /// these are for manipulations around interval  (from yyyyww to ww)
        int interval = beginInterval;
        int previousinterval = EditDate.getYear() * 10000 + EditDate.getMonthOfYear() * 100 + EditDate.getDayOfMonth();
        int time_IN_STATE =0;
        
        if (oldValue.equals("Awaiting Triage"))
                {
                    int stophere =1;
                }
        /*
        if (casenumber == 1900238	)
       {
           int stophere =1;
       }
        */
        
        
         
         
        /// WE MUST CALCULATE THE NEXT INTERVAL BEFORE DOINGTHE DIFF
        // WE MISS THE NEXT RES/NON-RES DATE ALSO TO CALCULATE THE LAST DIFF
        // WE UST DIFFERENTIATE THE CS / CUST  sub-status here to decide in which column to writwe? or do we jsut chnage th etitle of the column i basta?
        while (interval < endInterval)
         {
             if( previousinterval < interval)
             {
                 time_IN_STATE = (int)  intervalDiff(previousinterval, interval);
             }
             
             
            AddRecord("FirstRes", getIncrementFirstRes(), casenumber,caseOwner,ProductSetVersion, Severity, OriginalPrio,
                    creationDate,assignDate, EditDate,
                    interval, nonResState,oldValue, 
                    time_triage, time_L2, time_L3 , time_cust,
                    triageby, triagestatus, 0,"","","",storypoints, time_IN_STATE, 
                    Account,  SeverityDrop, SeverityDropBelow2, SystemStatus, Module, triageTracker); 
            //bumpIncrementFirstRes(); 
            AddRecord("EachProgress", getIncrementEachProgress(), casenumber,caseOwner,ProductSetVersion, Severity,OriginalPrio,
                    creationDate,assignDate, EditDate,
                     interval, nonResState, oldValue, 
                    time_triage, time_L2, time_L3 , time_cust,
                    triageby, triagestatus, 0,"","","",storypoints,  time_IN_STATE,
                    Account, SeverityDrop, SeverityDropBelow2, SystemStatus, Module, triageTracker); 
            //bumpIncrementEachProgress();
            AddRecord("AtDate", getIncrementAtDate(), casenumber,caseOwner,ProductSetVersion, Severity,OriginalPrio,
                    creationDate,assignDate, EditDate,
                    interval, nonResState, oldValue,
                    time_triage, time_L2, time_L3, time_cust,
                    triageby, triagestatus, 0,"","","",storypoints, time_IN_STATE, 
                    Account,  SeverityDrop, SeverityDropBelow2, SystemStatus, Module, triageTracker); 
           // bumpIncrementAtDate();
            oldValue = ""; /// only the fist of the series can have a reopen return    
             if( previousinterval < interval)
             {
                previousinterval = interval;
             }
             
             
            if( (rightBottomPanel == null /* JUnit testing */) || rightBottomPanel.jCheckBoxMonthlyCount.isSelected())
            {
                //do +1 month 
                //convert to string and pick 2 digits, then convert to int ,and check if already 12
                int yearmonth = interval/100;
                int year = yearmonth/100;
                int monthonly = yearmonth-(year*100);
                // TODO check if we run past dateEndreport, and stop if we do
                /*if (dateEndreport.getMonthOfYear() < monthonly)
                {
                    break;
                }*/
                        
                        
                if (monthonly==12)
                {
                    interval = (year+1)*10000 + 100 + 1;
                }
                else
                {
                     interval = year*10000 + 100*(monthonly +1) +1;
                }
            }
            else
            {    // convert interval to date
                SimpleDateFormat originalFormat = new SimpleDateFormat("yyyyMMdd");
                String myInterval =    Integer.toString(interval);
                try
                {
                    Date date = originalFormat.parse(myInterval);
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(date);
                    cal.add(Calendar.DATE, 7); // add 7 days
                    date = cal.getTime();

                    
                    //  check if we run past dateEndreport, and stop if we do
                    if (date.getTime() > dateEndReport.getMillis())
                    {
                        break;
                    }
                    
                    DateTime datetime1 = new DateTime(date);
                    interval = getIntervalFromDate(datetime1);
                    if( interval >= endInterval)
                    {
                        break;
                    }


                }
                catch(Exception e) {
                    System.out.println("Error occurred"+ e.getMessage());
                }
            }    
        }
    }                       
      
    private long intervalDiff(int int1, int int2)
    {
        SimpleDateFormat originalFormat = new SimpleDateFormat("yyyyMMdd");
        String myInterval1 =    Integer.toString(int1);
        String myInterval2 =    Integer.toString(int2);
        long difference =0;
        try
        {
            Date date1 = originalFormat.parse(myInterval1);
            Date date2 = originalFormat.parse(myInterval2);

            Calendar cal = Calendar.getInstance();
            difference = date2.getTime() - date1.getTime();
            
        }
        catch(Exception e) {
            System.out.println("Error occurred"+ e.getMessage());
        }
        return difference /(1000 * 60 * 60 *24);
    
    }
            
     private long DateDifference(DateTime start, DateTime finish)
    {
       /*LocalDateTime dtt= start.toLocalDateTime();// - 
       LocalDateTime dtt1= finish.toLocalDateTime();
       Period period = new Period(dtt, dtt1);
       long dur1 = period.getHours();
       long dur2 = period.getDays();
       long dur4 = period.getMonths();
       */
       long duration  = finish.getMillis() - start.getMillis();
       
       // teh duration in hours
       return( ( ((duration/ 1000)) / 60)/60  )  ;
    }
    
     
    private int getIncrementAtDate()   {                    
        return incrementAtDate;
    }

    
    private void bumpIncrementAtDate()   {                    
         incrementAtDate++;
    }

    
    private void setIncrementAtDate(int i)   {                    

         incrementAtDate = i;
    }




private int getIncrementFirstRes()   {                    

        return incrementFirstRes;
    }

    
    private void bumpIncrementFirstRes()   {                    

         incrementFirstRes++;
    }

    
    private void setIncrementFirstRes(int i)   {                    

         incrementFirstRes = i;
    }



private int getIncrementEachProgress()   {                    

        return incrementEachProgress;
    }

    
    private void bumpIncrementEachProgress()   {                    

         incrementEachProgress++;
    }

    
    private void setIncrementEachProgress(int i)   {                    

         incrementEachProgress = i;
    }

    public void buildPeriodCalendar()
    {   //start from the begining
    
       DateTime startDate;
       DateTime nextDate;
       if (rightBottomPanel.jCheckBoxMonthlyCount.isSelected()== true)
       {
            
            int yearstart = earliestInterval / 10000;
            int yearend = latestInterval / 10000;

            int monthstart = (earliestInterval - 10000 *yearstart ) / 100;
            int monthend = (latestInterval - 10000 *yearend ) / 100;

           int numberOfIntervals = 12 - monthstart;
           numberOfIntervals = numberOfIntervals + (yearend -yearstart -1) *12;
           numberOfIntervals =numberOfIntervals + monthend;

           periodCalendarLength = numberOfIntervals +1;
           PeriodCalendar =new int [periodCalendarLength];
           PeriodCalendar[0]= earliestInterval; 
           if (periodCalendarLength >1)
           {
                int i=1;
                startDate = getDateFromInterval(earliestInterval);
                nextDate = startDate;
                int nextInterval=0;
                while( nextInterval <latestInterval)
                {
                     nextDate = nextDate.plusDays(31);
                     nextInterval = getIntervalFromDate(nextDate);
                     nextDate = getDateFromInterval(nextInterval);
                    PeriodCalendar[i]= nextInterval; 
                     i=i+1;
                }
           }    
           
       }
       else
       {   // we are in weekly mode
           if (earliestInterval == 0)
           {
               earliestInterval = 19700101;
           }
           if (latestInterval == 0)
           {
               latestInterval = 19700101;
           }
            DateTime datestart =  getDateFromInterval(earliestInterval);
            DateTime dateend =  getDateFromInterval(latestInterval);
            int ndays = Days.daysBetween(datestart.withTimeAtStartOfDay() , dateend.withTimeAtStartOfDay() ).getDays();
            int numberOfIntervals = ndays/7; 
            
            PeriodCalendar =new int [numberOfIntervals +1];
            PeriodCalendar[0]= earliestInterval; 
            nextDate = datestart;
            int nextInterval=0;
            int i =1;
            while( nextInterval <latestInterval)
            {
                nextDate = nextDate.plusDays(8);
                nextInterval = getIntervalFromDate(nextDate);
                nextDate = getDateFromInterval(nextInterval);
                if (i <=numberOfIntervals )
                {
                    PeriodCalendar[i]= nextInterval;
                }
                
                i=i+1;
            }
           periodCalendarLength = numberOfIntervals +1;
       }
    }
    
    private DateTime  getDateFromInterval(int interval)   { 
        
        
        //     20170601
        int year = interval / 10000;
        int month = (interval - 10000 *year ) / 100;
        int day =   interval - 10000 * year - 100*month;
                
                
      DateTime myDate = new DateTime(year, month, day,0,0);        
      return myDate ;
      
        
    }                   

    
    private int getIntervalFromDate(DateTime resolutiondate)   {                    

        int result = 0;
        if ((rightBottomPanel == null  /* Junit test */) || (rightBottomPanel.jCheckBoxMonthlyCount.isSelected()== false ) )
        {
            int startdayofinterval = 0;    
            int resolutionday = resolutiondate.getDayOfWeek();
            int daystodeduct =0;
            DateTime dateatbeginofinterval;
            if ( (rightBottomPanel == null  /* Junit test */) || (rightBottomPanel.jCheckBoxWeekStartTuesday.isSelected()))
            {
                startdayofinterval =2;
            }
            else
            {
                startdayofinterval= 1;
            }


            if (startdayofinterval != resolutionday)
            {
                daystodeduct = resolutionday - startdayofinterval;
                if (daystodeduct < 0)
                {
                    daystodeduct = 7+ daystodeduct;
                }
                 dateatbeginofinterval = resolutiondate.minusDays(daystodeduct);
            }

            else
            {
                
                
                dateatbeginofinterval = resolutiondate;
            }
            result = dateatbeginofinterval.getYear()*10000 + dateatbeginofinterval.getMonthOfYear()*100 + dateatbeginofinterval.getDayOfMonth();
        
        }
        else
        {
            result = resolutiondate.getYear()*10000 + resolutiondate.getMonthOfYear()*100 + 1;
        }
        
        // 
        tentativeAddIntervalToMasterCalendar(result);
        return result;
             
    }                    
        
    private int getTemporaryCalendarLength()
    {
        int counter = 0;
        
        for (int i = 0; i < 10000; i ++)
        if (calendarTemporary[i] == 0)
        {
            break;
        }
        else
            counter ++;
        return counter;
    
    }
   
    
     private void tentativeAddIntervalToMasterCalendar(int period)
    {
        int counter = 0;
        int length = getTemporaryCalendarLength();
        /*
        if (length == 0)
        {
            calendarTemporary[0] == period;
        }
        */
        for (int i = 0; i < length; i ++)
        {
            if (calendarTemporary[i] == period)
            {
                return;
            }

        }
        calendarTemporary[length] = period;
    }
    
    private void initializeCalendar()
    {
        
        for (int i = 0; i < 10000; i ++)
        {
            calendarTemporary[i] =0;
           
        }
        
    }
    
    private void finalizeCalendar()
    {
    
        int calendarLength = getTemporaryCalendarLength();
        calendarFinal = new int[calendarLength];
        int counter = 0;
        for (int i = 0; i < calendarLength; i ++)
        {
            calendarFinal[i] = calendarTemporary[i];
        }
    }
    
    
    
    
    private String weirdpath(String resolution, String lastgoodresolution) {
        String weirdpath = "";
        if (resolution.equals("Information Provided") && lastgoodresolution.equals("Development")) {
            weirdpath = "DOUBLE-CHECK REOPEN?";
        }

        if (resolution.equals("Information Provided") && lastgoodresolution.equals("Information Provided")) {
            weirdpath = "DOUBLE-CHECK THAT ONE!";
        }
        return weirdpath;
    }
    
    

    private void AddRecord(String mode,int rowNumber, long casenumber, String owner, String ProductSetVersion, String Severity, String  OriginalPrio,
            DateTime creationDate, DateTime assignDate, DateTime EditDate, int period, String state, String oldValue,
            long time_triage, long time_L2 , long time_L3 , long time_cust,
            String triageby, String triagestatus, int nRes, 
            String greyArea, String waiting, String weirdPath, int storypoints, int time_IN_STATE, String Account,  int SeverityDrop, int SeverityDropBelow2, String SystemStatus, String Module
            , Triage triageTracker)
   {
       
        DefaultTableModel table = tableModelAtDate;
        switch(mode)
        {
            case "AtDate":
                table = tableModelAtDate;
                break;
            case "EachProgress":
                table = tableModelEachProgress;
                break;
                
            case "FirstRes":
                table = tableModelFirstRes;
                break;
        }
        // only if we are on the selected severity and system status
         if (filterOutSeverityOrSystem(triageTracker) == true)
         {
             return;
         }


       if (casenumber == 1900238	)
       { //DEBUG HERE
           int stophere =1;
       }
           
       //check if  the date is earliesr that the known earliestInterval
       if (earliestInterval == 0)
       {   // update the earliestdate
           earliestInterval = period;
       }
        if (earliestInterval > period)
       {   // update the earliestdate
           earliestInterval = period;
       }
       if (latestInterval < period)
       {   // update the earliestdate
           latestInterval = period;
       }
       
       // System.out.println( "rownumber: " + rowNumber);
       table.setValueAt(casenumber, rowNumber - 1, 0);
       table.setValueAt(owner, rowNumber - 1, 1);
       table.setValueAt(Account, rowNumber - 1, 2);
       table.setValueAt(SystemStatus, rowNumber - 1, 3);
       table.setValueAt(Module, rowNumber - 1, 4);


       table.setValueAt(ProductSetVersion, rowNumber - 1, 5);
       table.setValueAt(Severity, rowNumber - 1, 6);
       table.setValueAt(creationDate, rowNumber - 1, 7);
       table.setValueAt(assignDate, rowNumber - 1, 8);
       table.setValueAt(EditDate, rowNumber - 1, 9);
       table.setValueAt(period, rowNumber - 1, 10);
       table.setValueAt(state, rowNumber - 1, 11);
       table.setValueAt(oldValue, rowNumber - 1, 12);
       
       
       table.setValueAt(triageby, rowNumber - 1, 13);
       table.setValueAt(triagestatus, rowNumber - 1, 14);
       
      /* // DEBUG NOW 
       table.setValueAt(11111 , rowNumber - 1, 10);
       table.setValueAt(2222 , rowNumber - 1, 11);
       table.setValueAt(3333 , rowNumber - 1, 12);
       table.setValueAt(9999 , rowNumber - 1, 13);
       */
       
      // the times atre in hours so far, ,we turn them into days at the last minute, (!!but maybe not for triage?)
       table.setValueAt(time_triage , rowNumber - 1, 15); // TRAIGE MEASURED IN HOURS
       table.setValueAt(time_L2/24 , rowNumber - 1, 16);
       table.setValueAt(time_L3/24 , rowNumber - 1, 17);
       table.setValueAt(time_cust/24 , rowNumber - 1, 18);
       table.setValueAt(nRes, rowNumber - 1, 19);
       table.setValueAt(storypoints, rowNumber - 1, 20);
       if (time_IN_STATE != 0)
       {
           table.setValueAt(time_IN_STATE, rowNumber - 1, 21);
       }
       table.setValueAt(SeverityDrop, rowNumber - 1, 22);
       table.setValueAt(SeverityDropBelow2, rowNumber - 1, 23);
       table.setValueAt(OriginalPrio, rowNumber - 1, 24);
       
       
        //add     the period to the list of peiods
       tentativeAddIntervalToMasterCalendar(period);
       
        
        /*  DEBUG TIME  // DEBUG NOW
       if (casenumber == 1919369)
       {
            long res =  (long)table.getValueAt(rowNumber - 1, 0);
            String res1 = (String)table.getValueAt(rowNumber - 1, 1);
            DateTime res2 = (DateTime)table.getValueAt(rowNumber - 1, 2);
            int res3 = (int)table.getValueAt(rowNumber - 1, 3);
            int stophere =1;
       }      */
        
        switch(mode)
        {
            case "AtDate":
                bumpIncrementAtDate();
                break;
            case "EachProgress":
                bumpIncrementEachProgress();
                break;
                
            case "FirstRes":
                bumpIncrementFirstRes();
                break;
        }
        
        
        
        
   }
    
    
    
    private void ShowDetail(String interval, String owner, String WhereFrom)
    {   

        String sourcetable ="";  // this comes from the radio buttons
        String WhereClause = "";
        TableModel model = leftTopPanel.jTableProgressed.getModel();
        DefaultTableModel tableRes;
        tableRes =tableModelAggrEachProgress; //initialization
        
        if (leftTopPanel.checkBoxCombined.isSelected() == false)  //if selected we combine the 3 outputs, if no  then we filter
        {

            if (WhereFrom == "CS")
            {
                WhereClause = " AND Status = 'CS' ";
            }

            if (WhereFrom == "Awaiting Client")
            {
                WhereClause = " AND Status = 'Awaiting Client' ";
            }

            if (WhereFrom == "")
            {
                WhereClause = " AND Status <> 'CS' AND Status <> 'Awaiting Client' ";
            }

         
  
        }
        if (leftTopPanel.rbAtDate.isSelected() == true)
        {
            sourcetable = "AtDate";
            tableRes =tableModelAggrAtDate; 
        }
        if ( leftTopPanel.rbEachProgress.isSelected() == true)
        {
            sourcetable = "EachProgress";
            tableRes =tableModelAggrEachProgress;
        }
        if (leftTopPanel.rbFirstRes.isSelected() == true)
        {
            sourcetable = "FirstRes";
            tableRes =tableModelAggrFirstRes;
        }

        String filter = " AND CASE_OWNER = '" + owner + "' AND BEGIN_PERIOD =  '" + interval + "' " + WhereClause;
        DefaultTableModel tableFilteredRes = regenerateBaseTableModel(tableRes,sourcetable,  filter);

        middleTopPanel.jTableDetail.setModel(tableFilteredRes);
        middleTopPanel.jTableDetail.setAutoCreateRowSorter(true);   

        //not sure i use that?????
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);
    }
    
    public void setLeftTopPanel(LeftTopPanel p)
    {
        leftTopPanel =p;
    }
    
    
    public void setLeftBottomPanel(LeftBottomPanel p)
    {
        leftBottomPanel =p;
    }
    
    public void setRightTopPanel(RighTopPanel p)
    {
        rightTopPanel =p;
    }
    public void setRightMiddlePanel(RightMiddlePanel p)
    {
        rightMiddlePanel =p;
    }
    public void setRightAfterMiddlePanel(RightAfterMiddlePanel p)
    {
        rightAfterMiddlePanel =p;
    }
    public void setRightBottomPanel(RightBottomPanel p)
    {
        rightBottomPanel =p;
    }
    public void setMiddleTopPanel(MiddleTopPanel p)
    {
        middleTopPanel =p;
    }
    
    
    public void  setBottomPanel(BottomPanel p)
    {
        bottomPanel =p;
    }
    
    
    public void addOwner(String o)
    {
        int counter = 0;
        for (int i = 0; i < ownersTemporary.length; i ++)
        if (ownersTemporary[i] == null)
        {
            ownersTemporary[i] = o;
            break;
        }
        else
            counter ++;
    }
    
    public void addTriager(String o)
    {
        int counter = 0;
        for (int i = 0; i < triagersTemporary.length; i ++)
        if (triagersTemporary[i] == null)
        {
            triagersTemporary[i] = o;
            break;
        }
        else
            counter ++;
    }
    
    
    public void addAccount(String o)
    {
        int counter = 0;
        for (int i = 0; i < AccountsTemporary.length; i ++)
        if (AccountsTemporary[i] == null)
        {
            AccountsTemporary[i] = o;
            break;
        }
        else
            counter ++;
    }
    
    
    private int getFilterLength(String[] fltr)
    {
        int counter = 0;
        for (int i = 0; i < fltr.length; i ++)
        if (fltr[i] == null)
        {
            break;
        }
        else
            counter ++;
        return counter;
    }
    private int getAccountsLength()
    {
        return getFilterLength(AccountsTemporary); 
    }
    
    
    private int getTriagersLength()
    {
        return getFilterLength(triagersTemporary); 
    }
    
    private int getOwnersLength()
    {
        return getFilterLength(ownersTemporary);
    }
    
    boolean isAccount(String o)
    {   
        for (String s: AccountsTemporary)
        {
          if (o.equals(s))
                  return true;
        }
        return false;
    }
    
    boolean isTriager(String o)
    {   
        for (String s: triagersTemporary)
        {
          if (o.equals(s))
                  return true;
        }
        return false;
    }
    
    
    boolean isOwner(String o)
    {   
        for (String s: ownersTemporary)
        {
          if (o.equals(s))
                  return true;
        }
        return false;
    }
    
    
    private void createFinalOwnersList()
    {
        int filterLength = getFilterLength(ownersTemporary);
        ownersFinal = new String[filterLength];
        createFinalFilter(ownersTemporary, ownersFinal,filterLength ); 
        sortFilter(ownersFinal);
    }

    private void createFinalFilter(String[] temporaryFilter, String [] finalFilter, int filterLength)
    {
        int counter = 0;
        for (int i = 0; i < filterLength; i ++)
        {
            finalFilter[i] = temporaryFilter[i];
        }
    }


      private void sortFilter( String [] finalFilter)
    {
        int filterLength = getFilterLength(finalFilter);
        int counter = 0;
        String temp ="";
        for (int i = 0; i < filterLength; i ++)
        {
            for (int j = i; j < filterLength; j ++)
            {
                if (finalFilter[i].compareTo(finalFilter[j]) >0)
                {
                    temp = finalFilter[i];
                    finalFilter[i] = finalFilter[j];
                    finalFilter[j] = temp;
                }
            }
       }
    }


    private void createFinalTriagersList()
    {
        int filterLength = getFilterLength(triagersTemporary);
        triagersFinal = new String[filterLength];
        createFinalFilter(triagersTemporary, triagersFinal,filterLength ); 
        sortFilter(triagersFinal);
        
        
    }

    private void createFinalAccountsList()
    {
        int filterLength = getFilterLength(AccountsTemporary);
        AccountsFinal = new String[filterLength];
        createFinalFilter(AccountsTemporary, AccountsFinal,filterLength ); 
        sortFilter(AccountsFinal);
    }

    
    
    
    
    void prepareDerivedTableModels(String filter)
    {
        Vector<String> columnnamesaggregated = new Vector<String>();
         columnnamesaggregated.add("BEGIN_PERIOD");
         columnnamesaggregated.add("CASE_OWNER");
         columnnamesaggregated.add("TOTAL_PROGRESSED");
         columnnamesaggregated.add("TOT_STORY_POINTS");
         
         Vector<String> columnnamesCS = new Vector<String>();
         columnnamesCS.add("BEGIN_PERIOD");
         columnnamesCS.add("CASE_OWNER");
         columnnamesCS.add("TOTAL_IN_'CS'");
         columnnamesCS.add("UNUSED");
         
         Vector<String> columnnamesAwaitingClient = new Vector<String>();
         columnnamesAwaitingClient.add("BEGIN_PERIOD");
         columnnamesAwaitingClient.add("CASE_OWNER");
         columnnamesAwaitingClient.add("TOTAL_Awaiting_Client");     
         columnnamesAwaitingClient.add("UNUSED");  

         Vector<String> columnnamesTriage = new Vector<String>();
         columnnamesTriage.add("BEGIN_PERIOD");
         columnnamesTriage.add("TRIAGE_BY");
         columnnamesTriage.add("TOTAL_CASES");            
  
         Vector<String> columnnamesDowngradeSev = new Vector<String>();
         columnnamesDowngradeSev.add("BEGIN_PERIOD");
         columnnamesDowngradeSev.add("TRIAGE_BY");
         columnnamesDowngradeSev.add("SUM_SEV_DROP");          
         columnnamesDowngradeSev.add("COUNT");          
        
         Vector<String> columnnamesTimeIn = new Vector<String>();
         columnnamesTimeIn.add("BEGIN_PERIOD");
         columnnamesTimeIn.add("CASE_OWNER");
         columnnamesTimeIn.add("SUM_TIME");          
         columnnamesTimeIn.add("COUNT_CASES");          
        
         
         tableModelAggrFirstRes= prepareDerivedTableModel(tableModelAggrFirstRes,columnnamesaggregated,"FirstRes","STATUS <> 'CS' AND STATUS <> 'Awaiting Client'" , filter );
         tableModelCSFirstRes =  prepareDerivedTableModel(tableModelCSFirstRes,columnnamesCS,"FirstRes","STATUS = 'CS'" , filter );
         tableModelAwaitingClientFirstRes= prepareDerivedTableModel(tableModelAwaitingClientFirstRes,columnnamesAwaitingClient,"FirstRes","STATUS = 'Awaiting Client'"  , filter );
         tableModelAggrAtDate=  prepareDerivedTableModel(tableModelAggrAtDate,columnnamesaggregated,"AtDate","STATUS <> 'Awaiting Client'   AND STATUS <> 'CS'"  , filter );
         tableModelCSAtDate = prepareDerivedTableModel(tableModelCSAtDate,columnnamesCS,"AtDate","STATUS = 'CS'"   , filter);
         tableModelAwaitingClientAtDate=  prepareDerivedTableModel(tableModelAwaitingClientAtDate,columnnamesAwaitingClient,"AtDate","STATUS = 'Awaiting Client'"  , filter );
         tableModelAggrEachProgress=  prepareDerivedTableModel(tableModelAggrEachProgress,columnnamesaggregated,"EachProgress","STATUS <> 'Awaiting Client'   AND STATUS <> 'CS'"  , filter );
         tableModelCSEachProgress=  prepareDerivedTableModel(tableModelCSEachProgress,columnnamesCS,"EachProgress","STATUS = 'CS'"  , filter );
         tableModelAwaitingClientEachProgress =  prepareDerivedTableModel(tableModelAwaitingClientEachProgress,columnnamesAwaitingClient,"EachProgress","STATUS = 'Awaiting Client'"  , filter );
         
           
         tbl_M_AD_Trg_All= prepareDerivedTableModelTriage(tbl_M_AD_Trg_All,columnnamesTriage,"AtDate","STATUS <> 'WETAKEITALL'" ,  filter );
         tbl_M_AD_Trg_Pced_Dev = prepareDerivedTableModelTriage(tbl_M_AD_Trg_Pced_Dev,columnnamesTriage,"AtDate","STATUS <> 'CS' AND STATUS <> 'Awaiting Client'" , "AND CASE_OWNER <> TRIAGE_BY"+ filter );
         tbl_M_AD_Trg_Pcd_NoDev = prepareDerivedTableModelTriage(tbl_M_AD_Trg_Pcd_NoDev,columnnamesTriage,"AtDate","(STATUS = 'CS' OR STATUS = 'Awaiting Client') " , "AND CASE_OWNER <> TRIAGE_BY" + filter);
         tbl_M_AD_Trg_Self_Pcd_Dev = prepareDerivedTableModelTriage(tbl_M_AD_Trg_Self_Pcd_Dev,columnnamesTriage,"AtDate","STATUS <> 'CS' AND STATUS <> 'Awaiting Client'" , "AND CASE_OWNER = TRIAGE_BY" + filter);
         tbl_M_AD_Trg_Self_Pcd_NoDev = prepareDerivedTableModelTriage(tbl_M_AD_Trg_Self_Pcd_NoDev,columnnamesTriage,"AtDate","(STATUS = 'CS' OR STATUS = 'Awaiting Client') " , "AND CASE_OWNER = TRIAGE_BY" + filter);
         
         tbl_M_EP_Trg_All= prepareDerivedTableModelTriage(tbl_M_AD_Trg_All,columnnamesTriage,"EachProgress","STATUS <> 'WETAKEITALL'" ,  filter );
         tbl_M_EP_Trg_Pced_Dev = prepareDerivedTableModelTriage(tbl_M_AD_Trg_Pced_Dev,columnnamesTriage,"EachProgress","STATUS <> 'CS' AND STATUS <> 'Awaiting Client'" , "AND CASE_OWNER <> TRIAGE_BY"+ filter );
         tbl_M_EP_Trg_Pcd_NoDev = prepareDerivedTableModelTriage(tbl_M_AD_Trg_Pcd_NoDev,columnnamesTriage,"EachProgress","(STATUS = 'CS' OR STATUS = 'Awaiting Client') " , "AND CASE_OWNER <> TRIAGE_BY" + filter);
         tbl_M_EP_Trg_Self_Pcd_Dev = prepareDerivedTableModelTriage(tbl_M_AD_Trg_Self_Pcd_Dev,columnnamesTriage,"EachProgress","STATUS <> 'CS' AND STATUS <> 'Awaiting Client'" , "AND CASE_OWNER = TRIAGE_BY" + filter);
         tbl_M_EP_Trg_Self_Pcd_NoDev = prepareDerivedTableModelTriage(tbl_M_AD_Trg_Self_Pcd_NoDev,columnnamesTriage,"EachProgress","(STATUS = 'CS' OR STATUS = 'Awaiting Client') " , "AND CASE_OWNER = TRIAGE_BY" + filter);
        
         
         tbl_M_FR_Trg_All= prepareDerivedTableModelTriage(tbl_M_AD_Trg_All,columnnamesTriage,"AtDate","STATUS <> 'WETAKEITALL'" ,  filter );
         tbl_M_FR_Trg_Pced_Dev = prepareDerivedTableModelTriage(tbl_M_AD_Trg_Pced_Dev,columnnamesTriage,"AtDate","STATUS <> 'CS' AND STATUS <> 'Awaiting Client'" , "AND CASE_OWNER <> TRIAGE_BY"+ filter );
         tbl_M_FR_Trg_Pcd_NoDev = prepareDerivedTableModelTriage(tbl_M_AD_Trg_Pcd_NoDev,columnnamesTriage,"AtDate","(STATUS = 'CS' OR STATUS = 'Awaiting Client') " , "AND CASE_OWNER <> TRIAGE_BY" + filter);
         tbl_M_FR_Trg_Self_Pcd_Dev = prepareDerivedTableModelTriage(tbl_M_AD_Trg_Self_Pcd_Dev,columnnamesTriage,"AtDate","STATUS <> 'CS' AND STATUS <> 'Awaiting Client'" , "AND CASE_OWNER = TRIAGE_BY" + filter);
         tbl_M_FR_Trg_Self_Pcd_NoDev = prepareDerivedTableModelTriage(tbl_M_AD_Trg_Self_Pcd_NoDev,columnnamesTriage,"AtDate","(STATUS = 'CS' OR STATUS = 'Awaiting Client') " , "AND CASE_OWNER = TRIAGE_BY" + filter);
        
                 
                
        tbl_DowngradeSevBelow2_AD  = prepareDerivedTableModelSevDrop(tbl_DowngradeSevBelow2_AD,columnnamesDowngradeSev,"AtDate"," STATUS <> 'CS' AND STATUS <> 'Awaiting Client' " , filter);
        tbl_DowngradeSevBelow2_EP  = prepareDerivedTableModelSevDrop(tbl_DowngradeSevBelow2_AD,columnnamesDowngradeSev,"EachProgress"," STATUS <> 'CS' AND STATUS <> 'Awaiting Client' " , filter);
        tbl_DowngradeSevBelow2_FR  = prepareDerivedTableModelSevDrop(tbl_DowngradeSevBelow2_AD,columnnamesDowngradeSev,"FirstRes"," STATUS <> 'CS' AND STATUS <> 'Awaiting Client' " , filter);
        
        
        tbl_time_triage_AD  = prepareDerivedTableModelTimeIn(tbl_time_triage_AD,columnnamesTimeIn,"AtDate","T_TR"," STATUS <> 'CS' AND STATUS <> 'Awaiting Client' " , filter);
        tbl_time_L2_AD  = prepareDerivedTableModelTimeIn(tbl_time_L2_AD,columnnamesTimeIn,"AtDate","T_L2", "STATUS <> 'CS' AND STATUS <> 'Awaiting Client' " , filter);
        tbl_time_customer_AD  = prepareDerivedTableModelTimeIn(tbl_time_customer_AD,columnnamesTimeIn,"AtDate","T_CUST"," STATUS <> 'CS' AND STATUS <> 'Awaiting Client' " , filter);
        
        tbl_time_triage_EP  = prepareDerivedTableModelTimeIn(tbl_time_triage_EP,columnnamesTimeIn,"EachProgress","T_TR"," STATUS <> 'CS' AND STATUS <> 'Awaiting Client' " , filter);
        tbl_time_L2_EP  = prepareDerivedTableModelTimeIn(tbl_time_L2_EP,columnnamesTimeIn,"EachProgress","T_L2"," STATUS <> 'CS' AND STATUS <> 'Awaiting Client' " , filter);
        tbl_time_customer_EP  = prepareDerivedTableModelTimeIn(tbl_time_customer_EP,columnnamesTimeIn,"EachProgress","T_CUST"," STATUS <> 'CS' AND STATUS <> 'Awaiting Client' " , filter);
        
        tbl_time_triage_FR  = prepareDerivedTableModelTimeIn(tbl_time_triage_FR,columnnamesTimeIn,"FirstRes","T_TR", " STATUS <> 'CS' AND STATUS <> 'Awaiting Client' " , filter);
        tbl_time_L2_FR  = prepareDerivedTableModelTimeIn(tbl_time_L2_FR,columnnamesTimeIn,"FirstRes","T_L2", " STATUS <> 'CS' AND STATUS <> 'Awaiting Client' " , filter);
        tbl_time_customer_FR  = prepareDerivedTableModelTimeIn(tbl_time_customer_FR,columnnamesTimeIn,"FirstRes","T_CUST", " STATUS <> 'CS' AND STATUS <> 'Awaiting Client' " , filter);
        
            
           
      }
    
    
    
    private DefaultTableModel prepareDerivedTableModelTimeIn(DefaultTableModel tbl,Vector<String>  columnnames,String src , String whichTime, String statusRes , String filter )
    {
        ResultSet rs = null;
        try 
        {    
            Connection conn = DriverManager.getConnection("jdbc:relique:csv:.");

            // Create a Statement object to execute the query with.
            Statement stmt = conn.createStatement();
            
         ////  HEJKA    String query = "SELECT BEGIN_PERIOD, CASE_OWNER, SUM("+ whichTime + ") AS SUM_TIME, COUNT(CASE_NUMBER) AS COUNT_CASES FROM " + src + " WHERE LENGTH(TRIM(CASE_OWNER))>0  AND " + statusRes + " " + filter  + " AND ASSIGN_DATE >  '1971-01-01T00:00:00.000+07:30' " + " GROUP BY BEGIN_PERIOD, CASE_OWNER ORDER BY ABS(BEGIN_PERIOD), CASE_OWNER";
           //   String query = "SELECT BEGIN_PERIOD, CASE_OWNER, "+ whichTime + " , CASE_NUMBER FROM " + src + " WHERE LENGTH(TRIM(CASE_OWNER))>0  AND " + statusRes + " " + filter  + " AND ASSIGN_DATE >  '1971-01-01T00:00:00.000+07:30' " + "  ORDER BY ABS(BEGIN_PERIOD), CASE_OWNER";
           
           
            String query = "SELECT BEGIN_PERIOD, CASE_OWNER, SUM("+ whichTime + ") AS SUM_TIME, COUNT(CASE_NUMBER) AS COUNT_CASES FROM " + src + " WHERE LENGTH(TRIM(CASE_OWNER))>0  AND " + statusRes + " " + filter  + " AND ASSIGN_DATE >  '1971-01-01T00:00:00.000+07:30' " + " GROUP BY BEGIN_PERIOD, CASE_OWNER, CASE_NUMBER ORDER BY BEGIN_PERIOD, CASE_OWNER";
           
           rs = stmt.executeQuery(query);
            Vector<Vector<Object>> data = VectorFromResultSet(rs);
            tbl = new DefaultTableModel(data, columnnames) {
                Class[] types = {Integer.class, String.class, Integer.class};
                @Override
                public Class getColumnClass(int columnIndex) {
                    return this.types[columnIndex];
                }
            };
       
            // Clean up
            conn.close();
        }
        catch (SQLException ex) {
            Logger.getLogger(CSVFormatter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tbl;
     
    }
    
    
    
    
    private DefaultTableModel prepareDerivedTableModelSevDrop(DefaultTableModel tbl,Vector<String>  columnnames,String src ,String statusRes , String filter )
    {
        ResultSet rs = null;
        try 
        {    
            Connection conn = DriverManager.getConnection("jdbc:relique:csv:.");

            // Create a Statement object to execute the query with.
            Statement stmt = conn.createStatement();
            
            String query = "SELECT BEGIN_PERIOD, CASE_OWNER, SUM(SEV_DROP) AS SEV_DROP, COUNT(SEV_DROP) AS TOTAL FROM " + src + " WHERE LENGTH(TRIM(CASE_OWNER))>0  AND " + statusRes + " " + filter  + " GROUP BY BEGIN_PERIOD, CASE_OWNER ORDER BY ABS(BEGIN_PERIOD), CASE_OWNER";
            rs = stmt.executeQuery(query);
            Vector<Vector<Object>> data = VectorFromResultSet(rs);
            tbl = new DefaultTableModel(data, columnnames) {
                Class[] types = {Integer.class, String.class, Integer.class,Integer.class};
                @Override
                public Class getColumnClass(int columnIndex) {
                    return this.types[columnIndex];
                }
            };
       
            // Clean up
            conn.close();
        }
        catch (SQLException ex) {
            Logger.getLogger(CSVFormatter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tbl;
     
    }
    
    
    
    private DefaultTableModel prepareDerivedTableModel(DefaultTableModel tbl,Vector<String>  columnnames,String src ,String statusRes , String filter )
    {
        ResultSet rs = null;
        try 
        {    
            Connection conn = DriverManager.getConnection("jdbc:relique:csv:.");

            // Create a Statement object to execute the query with.
            Statement stmt = conn.createStatement();
            
            String query = "SELECT BEGIN_PERIOD, CASE_OWNER, COUNT(DISTINCT CASE_NUMBER) AS TOTAL_CASES, SUM(STORY_POINTS) AS TOTAL_STORY_POINTS  FROM " + src + " WHERE LENGTH(TRIM(CASE_OWNER))>0  AND " + statusRes + " " + filter  + " GROUP BY BEGIN_PERIOD, CASE_OWNER ORDER BY ABS(BEGIN_PERIOD), CASE_OWNER";
            rs = stmt.executeQuery(query);
            Vector<Vector<Object>> data = VectorFromResultSet(rs);
            tbl = new DefaultTableModel(data, columnnames) {
                Class[] types = {Integer.class, String.class, Integer.class, Integer.class};
                @Override
                public Class getColumnClass(int columnIndex) {
                    return this.types[columnIndex];
                }
            };
       
            // Clean up
            conn.close();
        }
        catch (SQLException ex) {
            Logger.getLogger(CSVFormatter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tbl;
     
    }
    
    private DefaultTableModel prepareDerivedTableModelTriage(DefaultTableModel tbl,Vector<String>  columnnames,String src ,String statusRes , String filter )
    {
        ResultSet rs = null;
        try 
        {    
            Connection conn = DriverManager.getConnection("jdbc:relique:csv:.");

            // Create a Statement object to execute the query with.
            Statement stmt = conn.createStatement();
            
            String query = "SELECT BEGIN_PERIOD, TRIAGE_BY, COUNT(DISTINCT CASE_NUMBER) AS TOTAL_CASES, SUM(STORY_POINTS) AS TOTAL_STORY_POINTS  FROM " + src + " WHERE LENGTH(TRIM(TRIAGE_BY))>0  AND " + statusRes + " " + filter  + " GROUP BY BEGIN_PERIOD, TRIAGE_BY ORDER BY ABS(BEGIN_PERIOD), TRIAGE_BY";
            rs = stmt.executeQuery(query);
            Vector<Vector<Object>> data = VectorFromResultSet(rs);
            tbl = new DefaultTableModel(data, columnnames) {
                Class[] types = {Integer.class, String.class, Integer.class, Integer.class};
                @Override
                public Class getColumnClass(int columnIndex) {
                    return this.types[columnIndex];
                }
            };
       
            // Clean up
            conn.close();
        }
        catch (SQLException ex) {
            Logger.getLogger(CSVFormatter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tbl;
     
    }
    
    
    public void regenerateBasedTableModels(String fltr)
    {
    
        
        tableModelAtDate = regenerateBaseTableModel(tableModelAtDate, "AtDate", fltr);
        tableModelFirstRes = regenerateBaseTableModel(tableModelFirstRes, "FirstRes", fltr);
        tableModelEachProgress = regenerateBaseTableModel(tableModelEachProgress, "EachProgress", fltr);
        
        
        
    }
    
    private DefaultTableModel regenerateBaseTableModel(DefaultTableModel tbl,String src, String fltr)
    {
         Vector<String> columnnames = new Vector<String>();
        columnnames.add("CASE_NUMBER");
        columnnames.add("CASE_OWNER");
        columnnames.add( "ACCOUNT");
        columnnames.add( "SYSTEM_STATUS");
        columnnames.add( "MODULE");
        columnnames.add("PRODUCT_SET_VERSION");
        columnnames.add("SEVERITY");
        
        columnnames.add("CREATION_DATE");
        columnnames.add("ASSIGN_DATE");
        columnnames.add("STATUS_DATE");
        columnnames.add("BEGIN_PERIOD");
        columnnames.add("STATUS");
        columnnames.add("FROM_STATUS");
        columnnames.add("TRIAGED_BY");
        columnnames.add("TRIAGE_STATUS");
        columnnames.add("T_TR");
        columnnames.add("T_L2");
        columnnames.add("T_L3");
        columnnames.add("T_CUST");
        columnnames.add("N_RESOLUTION");
        columnnames.add( "STORY_POINTS");
        columnnames.add( "TIME_IN_STATE");
        columnnames.add( "SEV_DROP");
        columnnames.add( "SEV_DROP_BELOW_2");
         columnnames.add("ORIGINAL_PRIO");
        ResultSet rs = null;
        try 
        {    
            Connection conn = DriverManager.getConnection("jdbc:relique:csv:.");
           
            // Create a Statement object to execute the query with.
            Statement stmt = conn.createStatement();
            
            String query = "SELECT CASE_NUMBER,CASE_OWNER, ACCOUNT, SYSTEM_STATUS, MODULE, PRODUCT_SET_VERSION, SEVERITY,  CREATION_DATE, ASSIGN_DATE, STATUS_DATE,BEGIN_PERIOD,STATUS,FROM_STATUS, TRIAGE_BY, TRIAGE_STATUS, T_TR, T_L2, T_L3, T_CUST, N_RESOLUTION,STORY_POINTS,TIME_IN_STATE, SEV_DROP, SEV_DROP_BELOW_2, ORIGINAL_PRIO FROM " + src + " WHERE LENGTH(TRIM(CASE_OWNER))>0  " + fltr + " ";
              rs = stmt.executeQuery(query);
           
            Vector<Vector<Object>> data = VectorFromResultSet(rs);
            tbl = new DefaultTableModel(data, columnnames) {
                   Class[] types = {Long.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, 
                                     DateTime.class,DateTime.class, String.class, String.class, String.class, String.class, String.class, String.class,
                                 String.class, String.class, String.class, Integer.class, String.class, String.class, 
                                 String.class, String.class, Integer.class, Integer.class};
                   
                   
             @Override
                public Class getColumnClass(int columnIndex) {
                    return this.types[columnIndex];
                }
            };
       
            // Clean up
            conn.close();
        }
        catch (SQLException ex) {
            Logger.getLogger(CSVFormatter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tbl;
      
    }
    
    public void displayDerivedTableModels()
    {
        if (leftTopPanel.rbAtDate.isSelected()) {
            leftTopPanel.jTableProgressed.setModel(tableModelAggrAtDate);
            leftTopPanel.jTableCS.setModel(tableModelCSAtDate);
            leftTopPanel.jTableClient.setModel(tableModelAwaitingClientAtDate);

        } else if (leftTopPanel.rbEachProgress.isSelected()) {
            leftTopPanel.jTableProgressed.setModel(tableModelAggrEachProgress);
            leftTopPanel.jTableCS.setModel(tableModelCSAtDate);
            leftTopPanel.jTableClient.setModel(tableModelAwaitingClientAtDate);

        } else if (leftTopPanel.rbFirstRes.isSelected()) {
            leftTopPanel.jTableProgressed.setModel(tableModelAggrFirstRes);
            leftTopPanel.jTableCS.setModel(tableModelCSAtDate);
            leftTopPanel.jTableClient.setModel(tableModelAwaitingClientAtDate);

        }
        
        
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);
        leftTopPanel.jTableProgressed.getColumnModel().getColumn(2).setCellRenderer(renderer);
        renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);
        leftTopPanel.jTableProgressed.getColumnModel().getColumn(0).setCellRenderer(renderer);
        leftTopPanel.jTableProgressed.setAutoCreateRowSorter(true);

       leftTopPanel.jTableCS.setModel(tableModelCSAtDate);
        renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);
        leftTopPanel.jTableCS.getColumnModel().getColumn(2).setCellRenderer(renderer);
        renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);
        leftTopPanel.jTableCS.getColumnModel().getColumn(0).setCellRenderer(renderer);
        leftTopPanel.jTableCS.setAutoCreateRowSorter(true);


        leftTopPanel.jTableClient.setModel(tableModelAwaitingClientAtDate);
        renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);
        leftTopPanel.jTableClient.getColumnModel().getColumn(2).setCellRenderer(renderer);
        renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);
        leftTopPanel.jTableClient.getColumnModel().getColumn(0).setCellRenderer(renderer);
        leftTopPanel.jTableClient.setAutoCreateRowSorter(true);

    }

    public void displayBaseTableModels(){
        if (leftTopPanel.rbAtDate.isSelected()) {
            middleTopPanel.jTableDetail.setModel(tableModelAtDate);
        } else if (leftTopPanel.rbEachProgress.isSelected()) {
            middleTopPanel.jTableDetail.setModel(tableModelEachProgress);
        } else if (leftTopPanel.rbFirstRes.isSelected()) {
            middleTopPanel.jTableDetail.setModel(tableModelFirstRes);
        }
        middleTopPanel.jTableDetail.setAutoscrolls(true);
        middleTopPanel.jTableDetail.setAutoCreateRowSorter(true);
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);
        middleTopPanel.jTableDetail.getColumnModel().getColumn(2).setCellRenderer(renderer);
            
    
    }
                            
    
}

