/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package CsvFormatter_IV;
import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.TextAnchor;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.CategoryAxis;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Base64;
import java.util.Date;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import org.jfree.chart.axis.NumberAxis;
import org.joda.time.DateTime;

/**
 *
 * @author vrosinac
 */
public class BottomPanel extends JPanel {

    
    private class Inte{
       public int x=0;
}
     private JButton btnBugHitRatio = new JButton();
   
    private JButton btnSelectCSV = new JButton();
    private JButton btnTriageGraph = new JButton();
    private JButton btnExecute = new JButton();
    private JButton btnKPIGraph = new JButton();
    private JButton btnL2Graph = new JButton();
    
    private JButton btnBugHitRatioGraph = new JButton();
    public JLabel lblFileInfo;
     public CSVFormatter ptrEngine;
    
    public String legendProgressed = "progressed at end of period";
    public String legendInCS = "CS side at end of period";
    public String legendClientSide = "Client side at end of period";        
       
    public String legendTriageALL = "Triaged";
    public String legendTriagePcdDev = "Triaged processed to Bug";
    public String legendTriagePcdNoDev = "Triaged processed not to Bug";        
    public String legendTriageSelf_PcdNoDev = "Triaged progressed not to Bug"; 
    public String legendTriageSelf_PcdDev = "Triaged progressed to Bug";  
    public String legendSevDowngradingPct = "Downgraded sev"; 
    public String legendSevDowngradingPctBelow2 = "Downgraded below sev2"; 
   
    public String legendTimeInTriage = "time in triage"; 
    public String legendTimeInL2 = "time in L2"; 
    public String legendTimeInCustomer = "time at customer";  


    public JFormattedDateTextField formattedTf =  new JFormattedDateTextField();
      
      
    
    private GridBagLayout gridBagLayout = new GridBagLayout();
    
        
    
    
      public void setEngine(CSVFormatter  csvF){ ptrEngine = csvF;}
      

private void btnSelectCSVActionPerformed(java.awt.event.ActionEvent evt) {

    ptrEngine.btnSelectCSVActionPerformed(evt);

}


private void btnBugHitRatioGraphActionPerformed(java.awt.event.ActionEvent evt) 
{
    
    
    
    
    
    
    
    // Open SSLSocket directly to gmail.com
    try{
        SocketFactory sf = SSLSocketFactory.getDefault();
        SSLSocket socket = (SSLSocket) sf.createSocket("gmail.com", 443);
        HostnameVerifier hv = HttpsURLConnection.getDefaultHostnameVerifier();
        SSLSession s = socket.getSession();

        // Verify that the certicate hostname is for mail.google.com
        // This is due to lack of SNI support in the current SSLSocket.
        if (!hv.verify("mail.google.com", s)) {
            throw new SSLHandshakeException("Expected mail.google.com, " +
                                            "found " + s.getPeerPrincipal());
        }

        
    }
    catch (Exception e)
    {
        int caughhtit = 0;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    //////////////////////// GOOD WITH GOOGLE ////////////
    
    //compose the URL
String exploreJSONCall =  "https://www.google.com/";
//  String exploreJSONCall =  "//https://almtools/jira/rest/api/2/search?jql=project%20%3D%20\"Trade%20Innovation%20Defects\"%20and%20\"Defect%20Origin\"%20%3D%20\"External%20Customer\"%20and%20type%20%3D%20Defect%20and%20created%20>%3D%20startOfMonth(-1)%20and%20created%20<%3D%20endOfMonth(-1)%20%20and%20resolution%20in%20(\"not%20a%20defect\")";
        


//String exploreJSONCall =  "https://almtools/jira/rest/api/2/search?jql=project%20%3D%20\"Trade%20Innovation%20Defects\"%20and%20\"Defect%20Origin\"%20%3D%20\"External%20Customer\"%20and%20type%20%3D%20Defect%20and%20created%20>%3D%20startOfMonth(-1)%20and%20created%20<%3D%20endOfMonth(-1)%20%20and%20resolution%20in%20(\"not%20a%20defect\")";

  
  //https://almtools/jira/rest/api/2/search?jql=project%20%3D%20"Trade%20Innovation%20Defects"%20and%20"Defect%20Origin"%20%3D%20"External%20Customer"%20and%20type%20%3D%20Defect%20and%20created%20>%3D%20startOfMonth(-1)%20and%20created%20<%3D%20endOfMonth(-1)%20%20and%20resolution%20in%20("not%20a%20defect")
//JSON_API_LIST.EXPLORE_API;
//log(exploreJSONCall);
    try{
        URL myURL = new URL(exploreJSONCall);
        try
        {
            HttpsURLConnection yc = (HttpsURLConnection) myURL.openConnection();
            yc.setRequestMethod("GET");
            yc.setRequestProperty("Accept", "application/json");
            //log("Content type expected:"+yc.getContentType());

            //a basic check on response code. 
            int respCode = yc.getResponseCode();
            //log("Response code received:"+Integer.toString(respCode));
            if (respCode != 200) {
                throw new RuntimeException("Failed : HTTP error code : "+respCode);
            }
            BufferedReader in1 = new BufferedReader(new InputStreamReader(
                                    yc.getInputStream()));
            String inputLine;
            while ((inputLine = in1.readLine()) != null){
                //log(inputLine);
                int doit=1;
            }
        }
        catch (IOException ex){
            int caughtit =0;
        }
    }
     catch(MalformedURLException ex){   
        //do exception handling here
        int caughtit =0;
    }   
    
    
    ///////////////////////////


    
        
        String username = "kanukala";
        String password = "Siva2345%";
        try
        {
    URL url = new URL("https://almtools/jira/secure/Dashboard.jspa");
   // URL url = new URL("http://www.google.com");
  // String encoding = new sun.misc.BASE64Encoder().encode(userPassword.getBytes());
   URLConnection uc = url.openConnection();
   uc.setRequestProperty(username, "kanukala");
   uc.setRequestProperty(password, "dddddddd");
   uc.connect();
    }
    catch (Exception e)
            {
            e.printStackTrace();
            
            }
    
    
    
    
    
    
    
    
    


    /*    {


 URL myURL = new URL("https://almtools/jira/secure/Dashboard.jspa");
HttpURLConnection myURLConnection = (HttpURLConnection)myURL.openConnection();
String userCredentials = "username:password";
String basicAuth = "Basic " + new String(new Base64().encode(userCredentials.getBytes()));
myURLConnection.setRequestProperty ("Authorization", basicAuth);
myURLConnection.setRequestMethod("POST");
myURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
myURLConnection.setRequestProperty("Content-Length", "" + postData.getBytes().length);
myURLConnection.setRequestProperty("Content-Language", "en-US");
myURLConnection.setUseCaches(false);
myURLConnection.setDoInput(true);
myURLConnection.setDoOutput(true);

}*/

//https://www.codota.com/code/java/classes/java.net.HttpURLConnection
    
    
    
    
    
    
    
    ///////////////////////////////
    try{
        URL url2 = new URL("https://almtools/jira/secure/Dashboard.jspa");
        URLConnection uc = url2.openConnection();

        String userpass = "vrosinac" + ":" + "xx";
        String basicAuth = "Basic " + javax.xml.bind.DatatypeConverter.printBase64Binary(userpass.getBytes());

        uc.setRequestProperty ("Authorization", basicAuth);
        InputStream in = uc.getInputStream();
    }
catch (Exception e)
{
    
    int here = 0;
}

    
    ////////////// try this first /////////////////////////////
 try
 {
            URL url1 = new URL("https://almtools/jira/secure/Dashboard.jspa");
      String userPass = "vincent.rosinach@misys.com:cccccccccc";
          String basicAuth1 = "Basic " + userPass.getBytes();//or
      //String basicAuth = "Basic " + new String(Base64.encode(userPass.getBytes(), Base64.No_WRAP));
      HttpURLConnection urlConnection = (HttpURLConnection)url1.openConnection();
      urlConnection.setRequestProperty("Authorization", basicAuth1);
      urlConnection.connect();
    }
catch (Exception e) {

int where = 1;
}
    
    
    
    
    
    /* 
    HttpURLConnection urlConnection;
    String url;
 //   String data = json;
    String result = null;
    try {
        String username ="vincent.rosinach@finastra.com";
        String password = "ccccccccc";

        String auth =new String(username + ":" + password);
        byte[] data1 = auth.getBytes(UTF_8);
        String base64 = Base64.encodeToString(data1, Base64.NO_WRAP);
        //Connect
        urlConnection = (HttpURLConnection) ((new URL("https://almtools/jira/secure/Dashboard.jspa").openConnection()));
        urlConnection.setDoOutput(true);
        urlConnection.setRequestProperty("Content-Type", "application/json");
        urlConnection.setRequestProperty("Authorization", "Basic "+base64);
        urlConnection.setRequestProperty("Accept", "application/json");
        urlConnection.setRequestMethod("GET");
        urlConnection.setConnectTimeout(10000);
        urlConnection.connect();
    }
      catch (Exception e) {
        e.printStackTrace();
    }
    
    */
    
    
    
    
    ///////////////////////////// TRY ANOTHER ///////////////////////////////
    
    //compose the URL
    https://stackoverflow.com/questions/496651/connecting-to-remote-url-which-requires-authentication-using-java
exploreJSONCall =  "https://almtools/jira/secure/Dashboard.jspa";
  
  //https://almtools/jira/rest/api/2/search?jql=project%20%3D%20"Trade%20Innovation%20Defects"%20and%20"Defect%20Origin"%20%3D%20"External%20Customer"%20and%20type%20%3D%20Defect%20and%20created%20>%3D%20startOfMonth(-1)%20and%20created%20<%3D%20endOfMonth(-1)%20%20and%20resolution%20in%20("not%20a%20defect")
    try{
        URL myURL = new URL(exploreJSONCall);
        try
        {
            HttpsURLConnection yc = (HttpsURLConnection) myURL.openConnection();
            yc.setRequestMethod("GET");
            yc.setRequestProperty("Accept", "application/json");

            //a basic check on response code. 
            int respCode = yc.getResponseCode();
            //log("Response code received:"+Integer.toString(respCode));
            if (respCode != 200) {
                throw new RuntimeException("Failed : HTTP error code : "+respCode);
            }
            BufferedReader in1 = new BufferedReader(new InputStreamReader(
                                    yc.getInputStream()));
            String inputLine;
            while ((inputLine = in1.readLine()) != null){
                int doit=1;
            }
        }
        catch (IOException ex){
            int caughtit =0;
        }
    }
     catch(MalformedURLException ex){
        int caughtit =0;
    }   
    
    
    
    
    
    /////////////////////////// BAD //////////////////////////////
    try{
        URL url = new URL("https://www.google.com/");
       try{
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("http://hybrid-web.global.blackspider.com:8082/proxy.pac?p=8rdwrz9p",80));
            URLConnection c = url.openConnection(proxy);
   
            BufferedReader in1 = new BufferedReader(new InputStreamReader(c.getInputStream()));  //stream to resource
            String str;
            String responsestring ="";
            while ((str = in1.readLine()) != null)   //reading data
                responsestring += str+"\n";//process the response and save it in some string or so
            in1.close();  //closing stream
        }
        catch (IOException ex){
            int caughtit =0;
        }
    }
    catch(MalformedURLException ex){
        //do exception handling here
        int caughtit =0;
    }
    
}



private void btnKPIActionPerformed(java.awt.event.ActionEvent evt) {
    DefaultTableModel  tbl_time_triage =null,tbl_time_L2 = null,tbl_time_customer=null;
   //defaults initialisation (for compilation only)
     tbl_time_triage = ptrEngine.tbl_time_triage_AD;
     tbl_time_L2 = ptrEngine.tbl_time_L2_AD;
     tbl_time_customer=ptrEngine.tbl_time_customer_AD;
             
     if (ptrEngine.leftTopPanel.rbAtDate.isSelected())
     {
         tbl_time_triage = ptrEngine.tbl_time_triage_AD;
        tbl_time_L2 = ptrEngine.tbl_time_L2_AD;
        tbl_time_customer=ptrEngine.tbl_time_customer_AD;
     }

     if (ptrEngine.leftTopPanel.rbEachProgress.isSelected())
     {
         tbl_time_triage = ptrEngine.tbl_time_triage_EP;
        tbl_time_L2 = ptrEngine.tbl_time_L2_EP;
        tbl_time_customer=ptrEngine.tbl_time_customer_EP;
     }

     if (ptrEngine.leftTopPanel.rbFirstRes.isSelected())
     {
         tbl_time_triage = ptrEngine.tbl_time_triage_FR;
        tbl_time_L2 = ptrEngine.tbl_time_L2_FR;
        tbl_time_customer= ptrEngine.tbl_time_customer_FR;
     }


     if (tbl_time_L2 !=null)
     {
        int sizetbl = tbl_time_L2.getRowCount();  

        DefaultCategoryDataset dataset_time_triage = new DefaultCategoryDataset();
        DefaultCategoryDataset dataset_time_L2 = new DefaultCategoryDataset();
        DefaultCategoryDataset dataset_time_customer = new DefaultCategoryDataset();


        createTimeInDatasets(tbl_time_triage, tbl_time_L2, tbl_time_customer, dataset_time_triage, dataset_time_L2, dataset_time_customer);

        if (dataset_time_triage != null && dataset_time_L2 != null && dataset_time_customer != null)
        {
            int size1 = dataset_time_triage.getRowCount();  
            int size2 = dataset_time_L2.getRowCount(); 
            int size3 = dataset_time_customer.getRowCount();

            if (size1 !=0 && size2!=0 && size3 !=0)
            {

               String activity_title = "time spent (days) per case (assigned and progressed in csv extract) " + ptrEngine.leftBottomPanel.activity_title;
               JFreeChart chart = ChartFactory.createLineChart(activity_title, "period", "count", dataset_time_L2, PlotOrientation.VERTICAL, true, true, false);

               CategoryPlot p = chart.getCategoryPlot();
               CategoryAxis xAxis = (CategoryAxis)p.getDomainAxis();
               xAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);

               p.setRangeAxis(0, new NumberAxis("legend for L2 and customer"));
              // p.setRangeAxis(1, new NumberAxis("legend for triage"));

               DecimalFormat decimalFormat = new DecimalFormat("####");


               LineAndShapeRenderer renderer1 = (LineAndShapeRenderer) p.getRenderer();
               renderer1 = new LineAndShapeRenderer();
               renderer1.setItemLabelGenerator(new StandardCategoryItemLabelGenerator("{2}",decimalFormat ));
               p.setRenderer(0,renderer1);
               renderer1.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.INSIDE10, TextAnchor.TOP_LEFT));
               p.getRendererForDataset(p.getDataset(0)).setSeriesPaint(0, Color.red); 
               renderer1.setItemLabelsVisible(true);



             LineAndShapeRenderer renderer2 = new LineAndShapeRenderer();
               renderer2.setItemLabelGenerator(new StandardCategoryItemLabelGenerator("{2}",decimalFormat ));
               p.setDataset(1,dataset_time_customer);
               p.setRenderer(1,renderer2);
               renderer2.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.INSIDE10, TextAnchor.TOP_LEFT));
               p.getRendererForDataset(p.getDataset(1)).setSeriesPaint(0, Color.green); 
               renderer2.setItemLabelsVisible(true);


     /*           LineAndShapeRenderer renderer3 = new LineAndShapeRenderer();
               renderer3.setItemLabelGenerator(new StandardCategoryItemLabelGenerator("{2}",decimalFormat ));
               p.setDataset(2,dataset_time_customer);
               p.setRenderer(2,renderer3);
               renderer3.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.INSIDE10, TextAnchor.TOP_LEFT));
               p.getRendererForDataset(p.getDataset(2)).setSeriesPaint(0, Color.blue); 
               renderer3.setItemLabelsVisible(true);
   */

             //Map the data to the appropriate axis

             p.mapDatasetToRangeAxis(0, 0);
               p.mapDatasetToRangeAxis(1, 0);
               p.mapDatasetToRangeAxis(2, 0);



               p.setRangeGridlinePaint(Color.black);
               ChartFrame frame = new ChartFrame("performance", chart);  
               frame.setVisible(true);
               frame.setSize(350,450);
            }   
        }
     }    

    
}

private void  createTimeInDatasets(DefaultTableModel tbl_time_triage, DefaultTableModel tbl_time_L2, DefaultTableModel tbl_time_customer, 
          DefaultCategoryDataset dataset_time_triage, DefaultCategoryDataset dataset_time_L2, DefaultCategoryDataset dataset_time_customer)
  {
        Inte  i1, i2, i3;
        i1 = new Inte();
        i2 = new Inte();
        i3 = new Inte();
        i1.x=0; i2.x=0; i3.x=0;
        int total1=0, total2=0, total3=0; 
        int week=0;
        int previousweek = 0;
        String weeklabel;
        Object o;
                                     
        if (tbl_time_triage  != null && tbl_time_L2 != null && tbl_time_customer != null)
        {
            
            int size_time_triage =  tbl_time_triage.getRowCount();
            int size_time_L2 =  tbl_time_L2.getRowCount();
            int size_time_customer =  tbl_time_customer.getRowCount();
            
            FillGraphDatasetRatio (tbl_time_triage, size_time_triage, dataset_time_triage,legendTimeInTriage,1);
            FillGraphDatasetRatio (tbl_time_L2, size_time_customer, dataset_time_L2,legendTimeInL2,1);
            FillGraphDatasetRatio (tbl_time_customer, size_time_customer, dataset_time_customer,legendTimeInCustomer,1);
            
            
        }
        /*
        int colsize = dataset_time_triage.getColumnCount();
        int j =0;
        double avg1 = 0;
        for (j=0; j < colsize; j++)
        {
             o = dataset_time_triage.getValue(0,j);
             avg1 = avg1 +  Double.valueOf((double)o);  // I THINK I DONT WANT THAT AVERGAE!!!
        }
            avg1 = avg1/colsize;
        for (j=0; j < colsize; j++)
        {
            datasetProgressedNoBug.addValue(avg1,"avg",datasetTotalTriaged.getColumnKey(j));
            //datasetDowngradingPct.addValue(50,"dwngd",datasetTotalTriaged.getColumnKey(j));
               // dataset.addValue(0,legend,weeklabel);
               // where do i get the count of all progressed cases for that period?
        }
        */
      
  
  }
  


private void btnExecuteActionPerformed(java.awt.event.ActionEvent evt) {


    Cursor hourglassCursor = new Cursor(Cursor.WAIT_CURSOR);
    setCursor(hourglassCursor);
    ptrEngine.btnExecuteActionPerformed(evt);
    Cursor normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);
    setCursor(normalCursor);

}
 
private void btnGraphActionPerformed(java.awt.event.ActionEvent evt) {
   
   DefaultTableModel  tbl1 =null,tbl2 = null,tbl3=null;
  //defaults initialisation (for compilation only)
    tbl1 = ptrEngine.tableModelAggrAtDate ;
    tbl2 = ptrEngine.tableModelCSAtDate; 
    tbl3 = ptrEngine.tableModelAwaitingClientAtDate;
   
   
    if (ptrEngine.leftTopPanel.rbAtDate.isSelected())
    {
        tbl1 = ptrEngine.tableModelAggrAtDate ;
        tbl2 = ptrEngine.tableModelCSAtDate; 
        tbl3 = ptrEngine.tableModelAwaitingClientAtDate;
    }
   
    
    if (ptrEngine.leftTopPanel.rbEachProgress.isSelected())
    {
        tbl1 = ptrEngine.tableModelAggrEachProgress ;
        tbl2 = ptrEngine.tableModelCSEachProgress; 
        tbl3 = ptrEngine.tableModelAwaitingClientEachProgress;
    }
     
    if (ptrEngine.leftTopPanel.rbFirstRes.isSelected())
    {
        tbl1 = ptrEngine.tableModelAggrFirstRes ;
        tbl2 = ptrEngine.tableModelCSFirstRes; 
        tbl3 = ptrEngine.tableModelAwaitingClientFirstRes;
    }
   
    
    int sizetbl = tbl1.getRowCount();  
   
   
    DefaultCategoryDataset dataset1 = new DefaultCategoryDataset();
    DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();
    DefaultCategoryDataset dataset3 = new DefaultCategoryDataset();
    

    DefaultCategoryDataset dataset1_avg = new DefaultCategoryDataset();
    
    DefaultCategoryDataset dataset_base_avg = new DefaultCategoryDataset();
    
    
    createL2Datasets(tbl1, tbl2, tbl3, dataset1, dataset2,  dataset3, dataset1_avg, /*dataset2_avg, dataset3_avg,*/ dataset_base_avg);
    if (dataset1 != null && dataset2 != null && dataset3 != null)
    {
        

        int size = dataset1.getRowCount();  
        int size2 = dataset2.getRowCount(); 
        int size3 = dataset3.getRowCount();
          
        if (size !=0 && size2!=0 && size3 !=0)
        {
          
            String activity_title = "activity"+ " " + ptrEngine.leftBottomPanel.activity_title;
            JFreeChart chart = ChartFactory.createLineChart(activity_title, "period", "count", dataset1, PlotOrientation.VERTICAL, true, true, false);

           CategoryPlot p = chart.getCategoryPlot();
           CategoryAxis xAxis = (CategoryAxis)p.getDomainAxis();
           xAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
           DecimalFormat decimalFormat = new DecimalFormat("####");
           
           
           LineAndShapeRenderer renderer1 = (LineAndShapeRenderer) p.getRenderer();
           renderer1 = new LineAndShapeRenderer();
           renderer1.setItemLabelGenerator(new StandardCategoryItemLabelGenerator("{2}",decimalFormat ));
           p.setRenderer(0,renderer1);
           renderer1.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.INSIDE10, TextAnchor.TOP_LEFT));
           p.getRendererForDataset(p.getDataset(0)).setSeriesPaint(0, Color.red); 
           renderer1.setItemLabelsVisible(true);

           
           
         LineAndShapeRenderer renderer2 = new LineAndShapeRenderer();
           renderer2.setItemLabelGenerator(new StandardCategoryItemLabelGenerator("{2}",decimalFormat ));
           p.setDataset(1,dataset2);
           p.setRenderer(1,renderer2);
           renderer2.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.INSIDE10, TextAnchor.TOP_LEFT));
           p.getRendererForDataset(p.getDataset(1)).setSeriesPaint(0, Color.green); 
           renderer2.setItemLabelsVisible(true);
           

            LineAndShapeRenderer renderer3 = new LineAndShapeRenderer();
           renderer3.setItemLabelGenerator(new StandardCategoryItemLabelGenerator("{2}",decimalFormat ));
           p.setDataset(2,dataset3);
           p.setRenderer(2,renderer3);
           renderer3.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.INSIDE10, TextAnchor.TOP_LEFT));
           p.getRendererForDataset(p.getDataset(2)).setSeriesPaint(0, Color.blue); 
           renderer3.setItemLabelsVisible(true);

           
            LineAndShapeRenderer renderer4 = new LineAndShapeRenderer();
           renderer4.setItemLabelGenerator(new StandardCategoryItemLabelGenerator("{2}",decimalFormat ));
           p.setDataset(3,dataset1_avg);
           p.setRenderer(3,renderer4);
           renderer4.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.INSIDE10, TextAnchor.TOP_LEFT));
           p.getRendererForDataset(p.getDataset(3)).setSeriesPaint(0, Color.red); 
           renderer4.setItemLabelsVisible(true);

         
            LineAndShapeRenderer renderer5 = new LineAndShapeRenderer();
           renderer5.setItemLabelGenerator(new StandardCategoryItemLabelGenerator("{2}",decimalFormat ));
           p.setDataset(4,dataset_base_avg);
           p.setRenderer(4,renderer5);
           renderer5.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.INSIDE10, TextAnchor.TOP_LEFT));
           p.getRendererForDataset(p.getDataset(4)).setSeriesPaint(0, Color.yellow); 
           renderer5.setItemLabelsVisible(true);
          
           
           p.setRangeGridlinePaint(Color.black);
           ChartFrame frame = new ChartFrame("performance", chart);  
           frame.setVisible(true);
           frame.setSize(350,450);
        }   
    }
    
    
    
}   
   
private void btnTriageGraphActionPerformed(java.awt.event.ActionEvent evt) {

    DefaultTableModel  tbl_TotalTriaged =null,tbl_ProcessedBug = null,tbl_ProcessedNoBug=null, tbl_ProgressedBug = null, tbl_ProgressedNoBug=null, 
            tbl_Sev = null;
   //defaults initialisation (for compilation only)
     tbl_TotalTriaged = ptrEngine.tbl_M_AD_Trg_All ;
     tbl_ProcessedBug = ptrEngine.tbl_M_AD_Trg_Pced_Dev; 
     tbl_ProcessedNoBug = ptrEngine.tbl_M_AD_Trg_Pcd_NoDev;
     tbl_ProgressedBug=  ptrEngine.tbl_M_AD_Trg_Self_Pcd_Dev;
     tbl_ProgressedNoBug=  ptrEngine.tbl_M_AD_Trg_Self_Pcd_NoDev;
     tbl_Sev =  ptrEngine.tbl_DowngradeSev_AD ;
    


     if (ptrEngine.leftTopPanel.rbAtDate.isSelected())
     {
        tbl_TotalTriaged = ptrEngine.tbl_M_AD_Trg_All ;
        tbl_ProcessedBug = ptrEngine.tbl_M_AD_Trg_Pced_Dev; 
        tbl_ProcessedNoBug = ptrEngine.tbl_M_AD_Trg_Pcd_NoDev;
        tbl_ProgressedBug=  ptrEngine.tbl_M_AD_Trg_Self_Pcd_Dev;
        tbl_ProgressedNoBug=  ptrEngine.tbl_M_AD_Trg_Self_Pcd_NoDev;
        tbl_Sev =  ptrEngine.tbl_DowngradeSevBelow2_AD ;
     }


     if (ptrEngine.leftTopPanel.rbEachProgress.isSelected())
     {
        tbl_TotalTriaged = ptrEngine.tbl_M_EP_Trg_All ;
        tbl_ProcessedBug = ptrEngine.tbl_M_EP_Trg_Pced_Dev; 
        tbl_ProcessedNoBug = ptrEngine.tbl_M_EP_Trg_Pcd_NoDev;
        tbl_ProgressedBug=  ptrEngine.tbl_M_EP_Trg_Self_Pcd_Dev;
        tbl_ProgressedNoBug=  ptrEngine.tbl_M_EP_Trg_Self_Pcd_NoDev;
        tbl_Sev =  ptrEngine.tbl_DowngradeSevBelow2_AD ;
     }

     if (ptrEngine.leftTopPanel.rbFirstRes.isSelected())
     {
        tbl_TotalTriaged = ptrEngine.tbl_M_FR_Trg_All ;
        tbl_ProcessedBug = ptrEngine.tbl_M_FR_Trg_Pced_Dev; 
        tbl_ProcessedNoBug = ptrEngine.tbl_M_FR_Trg_Pcd_NoDev;
        tbl_ProgressedBug=  ptrEngine.tbl_M_FR_Trg_Self_Pcd_Dev;
        tbl_ProgressedNoBug=  ptrEngine.tbl_M_FR_Trg_Self_Pcd_NoDev;
        tbl_Sev =  ptrEngine.tbl_DowngradeSevBelow2_AD ;
     }


     int sizetbl = tbl_TotalTriaged.getRowCount();  

     DefaultCategoryDataset datasetTotalTriaged = new DefaultCategoryDataset();
     DefaultCategoryDataset datasetProcessedNoBug = new DefaultCategoryDataset();
     DefaultCategoryDataset datasetProcessedBug = new DefaultCategoryDataset();
     DefaultCategoryDataset datasetProgressedNoBug = new DefaultCategoryDataset();
     DefaultCategoryDataset datasetProgressedBug = new DefaultCategoryDataset();
     DefaultCategoryDataset datasetSevDowngrade = new DefaultCategoryDataset();
     DefaultCategoryDataset datasetDowngradingPctBelow2 = new DefaultCategoryDataset();
     DefaultCategoryDataset datasetDowngradingPct = new DefaultCategoryDataset();


     createTriageDatasets(tbl_TotalTriaged, tbl_ProcessedBug, tbl_ProcessedNoBug, tbl_ProgressedBug, tbl_ProgressedNoBug, tbl_Sev, 
             datasetTotalTriaged, datasetProcessedBug,  datasetProcessedNoBug, datasetProgressedBug,  datasetProgressedNoBug, 
             datasetDowngradingPct, datasetDowngradingPctBelow2);
     
     if (datasetTotalTriaged != null && datasetProcessedNoBug != null && datasetProcessedBug != null && datasetProgressedNoBug != null 
             && datasetProgressedBug != null)
     {
         int size1 = datasetTotalTriaged.getRowCount();  
         int size2 = datasetProcessedNoBug.getRowCount(); 
         int size3 = datasetProcessedBug.getRowCount();
         int size4 = datasetProgressedNoBug.getRowCount(); 
         int size5 = datasetProgressedBug.getRowCount();

         if (size1 !=0 && size2!=0 && size3 !=0)
         {

             String activity_title = "triage activity " + ptrEngine.leftBottomPanel.activity_title;
             JFreeChart chart = ChartFactory.createLineChart(activity_title, "period", "count", datasetTotalTriaged, PlotOrientation.VERTICAL, true, true, false);

            CategoryPlot p = chart.getCategoryPlot();
            CategoryAxis xAxis = (CategoryAxis)p.getDomainAxis();
            xAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
            
            p.setRangeAxis(0, new NumberAxis("Series 1"));
            p.setRangeAxis(1, new NumberAxis("Series 2"));
            
            DecimalFormat decimalFormat = new DecimalFormat("####");


            LineAndShapeRenderer renderer1 = (LineAndShapeRenderer) p.getRenderer();
            renderer1 = new LineAndShapeRenderer();
            renderer1.setItemLabelGenerator(new StandardCategoryItemLabelGenerator("{2}",decimalFormat ));
            p.setRenderer(0,renderer1);
            renderer1.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.INSIDE10, TextAnchor.TOP_LEFT));
            p.getRendererForDataset(p.getDataset(0)).setSeriesPaint(0, Color.red); 
            renderer1.setItemLabelsVisible(true);



          LineAndShapeRenderer renderer2 = new LineAndShapeRenderer();
            renderer2.setItemLabelGenerator(new StandardCategoryItemLabelGenerator("{2}",decimalFormat ));
            p.setDataset(1,datasetProcessedNoBug);
            p.setRenderer(1,renderer2);
            renderer2.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.INSIDE10, TextAnchor.TOP_LEFT));
            p.getRendererForDataset(p.getDataset(1)).setSeriesPaint(0, Color.red); 
            renderer2.setItemLabelsVisible(true);


             LineAndShapeRenderer renderer3 = new LineAndShapeRenderer();
            renderer3.setItemLabelGenerator(new StandardCategoryItemLabelGenerator("{2}",decimalFormat ));
            p.setDataset(2,datasetProcessedBug);
            p.setRenderer(2,renderer3);
            renderer3.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.INSIDE10, TextAnchor.TOP_LEFT));
            p.getRendererForDataset(p.getDataset(2)).setSeriesPaint(0, Color.green); 
            renderer3.setItemLabelsVisible(true);


             LineAndShapeRenderer renderer4 = new LineAndShapeRenderer();
            renderer4.setItemLabelGenerator(new StandardCategoryItemLabelGenerator("{2}",decimalFormat ));
            p.setDataset(3,datasetProgressedNoBug);
            p.setRenderer(3,renderer4);
            renderer4.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.INSIDE10, TextAnchor.TOP_LEFT));
            p.getRendererForDataset(p.getDataset(3)).setSeriesPaint(0, Color.yellow); 
            renderer4.setItemLabelsVisible(true);


             LineAndShapeRenderer renderer5 = new LineAndShapeRenderer();
            renderer5.setItemLabelGenerator(new StandardCategoryItemLabelGenerator("{2}",decimalFormat ));
            p.setDataset(4,datasetProgressedBug);
            p.setRenderer(4,renderer5);
            renderer5.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.INSIDE10, TextAnchor.TOP_LEFT));
            p.getRendererForDataset(p.getDataset(4)).setSeriesPaint(0, Color.green); 
          renderer5.setItemLabelsVisible(true);

          
             LineAndShapeRenderer renderer6 = new LineAndShapeRenderer();
            renderer6.setItemLabelGenerator(new StandardCategoryItemLabelGenerator("{2}",decimalFormat ));
            p.setDataset(5,datasetDowngradingPct);
            p.setRenderer(5,renderer6);
            renderer6.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.INSIDE10, TextAnchor.TOP_LEFT));
            p.getRendererForDataset(p.getDataset(5)).setSeriesPaint(0, Color.blue); 
          renderer6.setItemLabelsVisible(true);
          //Map the data to the appropriate axis
            p.mapDatasetToRangeAxis(0, 0);
            p.mapDatasetToRangeAxis(1, 0);
            p.mapDatasetToRangeAxis(2, 0);
            p.mapDatasetToRangeAxis(3, 0);
            p.mapDatasetToRangeAxis(4, 0);
            p.mapDatasetToRangeAxis(5, 1);   

        

            p.setRangeGridlinePaint(Color.black);
            ChartFrame frame = new ChartFrame("performance", chart);  
            frame.setVisible(true);
            frame.setSize(350,450);
         }   
     }

    }



    public BottomPanel() {
        setupGUI();
    }

    private void setupGUI() {
        int width = this.getWidth();
        ////this.setBorder(BorderFactory.createTitledBorder(""));
        this.setPreferredSize(new Dimension(width,80));
        this.setMinimumSize(new Dimension(width,80));
        this.setMaximumSize(new Dimension(width,80));
        this.setLayout(gridBagLayout);
        
        formattedTf.setValue(new Date());
        formattedTf.setEditable(false);
        
        btnSelectCSV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelectCSVActionPerformed(evt);
            }
        });

        
          btnExecute.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExecuteActionPerformed(evt);
            }
        });
        
 
             btnKPIGraph.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKPIActionPerformed(evt);
            }
        }); 
          
         btnBugHitRatioGraph.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
               btnBugHitRatioGraphActionPerformed(evt);
            }
        });      
             
          
        btnL2Graph.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGraphActionPerformed(evt);
            }
        });
 
        
           btnTriageGraph.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTriageGraphActionPerformed(evt);
            }
        });
        
 
        
        Dimension btnDimension = new Dimension(120,25);
        btnSelectCSV.setMaximumSize(btnDimension);
        btnTriageGraph.setMaximumSize(btnDimension);
        btnExecute.setMaximumSize(btnDimension);
        btnKPIGraph.setMaximumSize(btnDimension);
        btnBugHitRatioGraph.setMaximumSize(btnDimension);
        btnL2Graph.setMaximumSize(btnDimension);
        
        
        btnSelectCSV.setMinimumSize(btnDimension);
        btnTriageGraph.setMinimumSize(btnDimension);
        btnExecute.setMinimumSize(btnDimension);
        btnKPIGraph.setMinimumSize(btnDimension);
        btnBugHitRatioGraph.setMinimumSize(btnDimension);
        btnL2Graph.setMinimumSize(btnDimension);
        
        btnSelectCSV.setPreferredSize(btnDimension);
        btnTriageGraph.setPreferredSize(btnDimension);
        btnExecute.setPreferredSize(btnDimension);
        btnKPIGraph.setPreferredSize(btnDimension);
        btnBugHitRatioGraph.setPreferredSize(btnDimension);
        btnL2Graph.setPreferredSize(btnDimension);
        
        
        
        btnSelectCSV.setText("Select csv file");
        btnTriageGraph.setText("Triage graph");
        btnExecute.setText("Execute");
        btnKPIGraph.setText("KPI Graph");
        btnBugHitRatioGraph.setText("Test only (work in progress)");
        btnL2Graph.setText("L2 Graph");
        
        lblFileInfo= new JLabel("No file selected");
        //lblFileInfo.setFont(new Font(Font.DIALOG, Font.PLAIN),12);
        //lblFileInfo
        
        
        DateFormat format = new SimpleDateFormat("yyyy-mm-dd");
        JFormattedTextField dateTextField = new JFormattedTextField(format);
        DateTime dateNow = DateTime.now();   
       // dateTextField.setValue(dateNow.toString("yyyymmdd"));
//        dateTextField.setValue("2017-01-01");
       this.add(btnSelectCSV,           new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(15, 15, 15, 5), 0, 0));
        this.add(lblFileInfo,           new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(2, 2, 2, 2), 0, 0));
        this.add(btnTriageGraph,        new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(15, 15, 15, 15), 0, 0));
        this.add(btnL2Graph,            new GridBagConstraints(2, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(15, 15, 15, 15), 0, 0));
        this.add(btnKPIGraph,           new GridBagConstraints(3, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(15, 15, 15, 15), 0, 0));
        this.add(btnBugHitRatioGraph,   new GridBagConstraints(3, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(2, 2, 2, 2), 0, 0));
        this.add(btnExecute,            new GridBagConstraints(4, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(15, 15, 15, 15), 0, 0));
        this.add(formattedTf,           new GridBagConstraints(4, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(2, 2, 2, 2), 0, 0));
      
    
    }

    private void FillGraphDataset (DefaultTableModel tbl, int size, DefaultCategoryDataset dataset, String label)
    {
        int    delayMe=0, week=0;
        Object o;
        Inte i1;
       i1 = new Inte();
       int j =0;  
       i1.x=0;
       while (j <ptrEngine.periodCalendarLength) 
       {
            if (i1.x-delayMe < size)
            {    
                o = tbl.getValueAt(i1.x-delayMe,0);
                week =  Integer.valueOf((String)o);
                    if (week == ptrEngine.PeriodCalendar[j])
                {
                   // i1.x = j -delayMe;
                    fillinGrpahData(week,i1, delayMe, size, tbl, dataset, label);

                }
                else
                {

                     EmptyGrpahData( ptrEngine.PeriodCalendar[j], i1, tbl, dataset, label);
                     delayMe=delayMe+1;
                }
            }
            else
            {
                     EmptyGrpahData( ptrEngine.PeriodCalendar[j], i1, tbl, dataset, label);
                     delayMe=delayMe+1;

            }
            j=j+1;
        }

    }
            
   
    
     private void FillGraphDatasetRatio (DefaultTableModel tbl, int size, DefaultCategoryDataset dataset, String label, int multiplicator)
    {
        int    delayMe=0, week=0;
        Object o;
        Inte i1;
       i1 = new Inte();
       int j =0;  
       i1.x=0;
       while (j <ptrEngine.periodCalendarLength) 
       {
            if (i1.x-delayMe < size)
            {    
                o = tbl.getValueAt(i1.x-delayMe,0);
                week =  Integer.valueOf((String)o);
                    if (week == ptrEngine.PeriodCalendar[j])
                {
                   // i1.x = j -delayMe;
                    fillinGrpahDataRatio(week,i1, delayMe, size, tbl, dataset, label, multiplicator);
                    
                }
                else
                {

                     EmptyGrpahData( ptrEngine.PeriodCalendar[j], i1, tbl, dataset, label);
                     delayMe=delayMe+1;
                }
            }
            else
            {
                     EmptyGrpahData( ptrEngine.PeriodCalendar[j], i1, tbl, dataset, label);
                     delayMe=delayMe+1;

            }
            j=j+1;
        }

    }
   
    
    
    
   private void createTriageDatasets(DefaultTableModel tbl_TotalTriaged, DefaultTableModel tbl_ProcessedBug, DefaultTableModel tbl_ProcessedNoBug,
           DefaultTableModel tbl_ProgressedBug, DefaultTableModel tbl_ProgressedNoBug,  DefaultTableModel tbl_Aggr, 
           DefaultCategoryDataset datasetTotalTriaged, DefaultCategoryDataset datasetProcessedBug, DefaultCategoryDataset datasetProcessedNoBug,
           DefaultCategoryDataset datasetProgressedBug, DefaultCategoryDataset datasetProgressedNoBug, DefaultCategoryDataset datasetDowngradingPct,
           DefaultCategoryDataset datasetDowngradingPctBelow2) 
   {
        Inte  i1, i2, i3;
        i1 = new Inte();
        i2 = new Inte();
        i3 = new Inte();
        i1.x=0; i2.x=0; i3.x=0;
        int total1=0, total2=0, total3=0; 
        int week=0;
        int previousweek = 0;
        String weeklabel;
        Object o;
                                     
        if (tbl_TotalTriaged  != null && tbl_ProcessedBug != null && tbl_ProcessedNoBug != null)
        {
            
            int size_TotalTriaged =  tbl_TotalTriaged.getRowCount();
            int size_ProcessedBug =  tbl_ProcessedBug.getRowCount();
            int size_ProcessedNoBug =  tbl_ProcessedNoBug.getRowCount();
            int size_ProgressedBug =  tbl_ProgressedBug.getRowCount();
            int size_ProgressedNoBug =  tbl_ProgressedNoBug.getRowCount();
            int size_Aggr =  tbl_Aggr.getRowCount();
            FillGraphDataset (tbl_TotalTriaged , size_TotalTriaged, datasetTotalTriaged, legendTriageALL);
            FillGraphDataset (tbl_ProcessedBug, size_ProcessedBug, datasetProcessedBug, legendTriagePcdDev);
            FillGraphDataset (tbl_ProcessedNoBug, size_ProcessedNoBug, datasetProcessedNoBug,legendTriagePcdNoDev);
            FillGraphDataset (tbl_ProgressedBug, size_ProgressedBug, datasetProgressedBug,legendTriageSelf_PcdDev);
            FillGraphDataset (tbl_ProgressedNoBug, size_ProgressedNoBug, datasetProgressedNoBug,legendTriageSelf_PcdNoDev);
            FillGraphDatasetRatio (tbl_Aggr, size_Aggr, datasetDowngradingPct,legendSevDowngradingPct, 100);
            FillGraphDatasetRatio (tbl_Aggr, size_Aggr, datasetDowngradingPctBelow2,legendSevDowngradingPctBelow2, 100);
            
        }
        
        int colsize = datasetTotalTriaged.getColumnCount();
        int j =0;
        double avg1 = 0;
        for (j=0; j < colsize; j++)
        {
             o = datasetTotalTriaged.getValue(0,j);
             avg1 = avg1 +  Double.valueOf((double)o);  // I THINK I DONT WANT THAT AVERGAE!!!
        }
            avg1 = avg1/colsize;
        for (j=0; j < colsize; j++)
        {
            datasetProgressedNoBug.addValue(avg1,"avg",datasetTotalTriaged.getColumnKey(j));
            //datasetDowngradingPct.addValue(50,"dwngd",datasetTotalTriaged.getColumnKey(j));
               // dataset.addValue(0,legend,weeklabel);
               // where do i get the count of all progressed cases for that period?
        }
        
    }
   
 
   
      
   private void createL2Datasets(DefaultTableModel tbl1, DefaultTableModel tbl2, DefaultTableModel tbl3, DefaultCategoryDataset dataset1, DefaultCategoryDataset dataset2, DefaultCategoryDataset dataset3, DefaultCategoryDataset dataset1_avg,/* DefaultCategoryDataset dataset2_avg, DefaultCategoryDataset dataset3_avg, */ DefaultCategoryDataset dataset_base_avg) 
   {
        Inte  i1, i2, i3;
        i1 = new Inte();
        i2 = new Inte();
        i3 = new Inte();
        i1.x=0; i2.x=0; i3.x=0;
        int total1=0, total2=0, total3=0; 
        int week=0;
        int previousweek = 0;
        String weeklabel;
        Object o;
                                     
        if (tbl1 != null && tbl2 != null && tbl3 != null)
        {
            
            int size1 =  tbl1.getRowCount();
            int size2 =  tbl2.getRowCount();
            int size3 =  tbl3.getRowCount();
            FillGraphDataset (tbl1, size1, dataset1, legendProgressed);
            FillGraphDataset (tbl2, size2, dataset2, legendInCS);
            FillGraphDataset (tbl3, size3, dataset3,legendClientSide);
        }
        
        int colsize = dataset1.getColumnCount();
        int j =0;
        double avg1 = 0;
        for (j=0; j < colsize; j++)
        {
             o = dataset1.getValue(0,j);
             avg1 = avg1 +  Double.valueOf((double)o);  // I THINK I DONT WANT THAT AVERGAE!!!
        }
            avg1 = avg1/colsize;
       double relativeAvergae =0;
         int number_owners =  ptrEngine.getTeamSize();
        int numberofpeopleselected = ptrEngine.leftBottomPanel.getSelectedCount();
        double teamAverage = ptrEngine.getTeamAverage();
        if (numberofpeopleselected != 0 )
        {
            relativeAvergae = ptrEngine.getTeamAverage()*numberofpeopleselected;
        }
        else
        {
            relativeAvergae = ptrEngine.getTeamAverage()* ptrEngine.getTeamSize();
        }
         for (j=0; j < colsize; j++)
        {
            dataset1_avg.addValue(avg1,"progressed average",dataset1.getColumnKey(j));
            dataset_base_avg.addValue(relativeAvergae,"relative team average",dataset1.getColumnKey(j));
        }
        
    }
 
   
   
    private void fillinGrpahData(int previousweek, Inte i, int delayMe,  int size, DefaultTableModel tbl, DefaultCategoryDataset dataset, String legend)
    {
         String weeklabel;
        int total =0;
        Object o;
        o = tbl.getValueAt(i.x - delayMe,0);
        int week;
        
        week =  Integer.valueOf((String)o);
        while (week == previousweek &&  i.x -delayMe< size)
        {
            o = tbl.getValueAt(i.x -delayMe,2);
            total = total+ (int)o;
            i.x = i.x +1 ;
            if ( i.x -delayMe >= size)
                break;
            
            o = tbl.getValueAt(i.x -delayMe ,0);
            week =  Integer.valueOf((String)o);
        
        }
        
        weeklabel = " " + previousweek;
        dataset.addValue(total,legend,weeklabel);
    }
    
   
    private void fillinGrpahDataRatio(int previousweek, Inte i, int delayMe,  int size, DefaultTableModel tbl, DefaultCategoryDataset dataset, String legend, int multiplicator)
    {
         String weeklabel;
        long total =0;
        int count=0;
        Object o;
        o = tbl.getValueAt(i.x - delayMe,0);
        int week;
        
        week =  Integer.valueOf((String)o);
        while (week == previousweek &&  i.x -delayMe< size)
        {
            // count of Y instead 0f total
            // and count all to prepare average too
            
            o = tbl.getValueAt(i.x -delayMe,2);
            total = total+ /* Integer.valueOf((String)o); */(long)o;
    
            o = tbl.getValueAt(i.x -delayMe,3);
            count = count+  /* Integer.valueOf((String)o); */ (int)o;
    
            i.x = i.x +1 ;
            if ( i.x -delayMe >= size)
                break;
            
            o = tbl.getValueAt(i.x -delayMe ,0);
            week =  Integer.valueOf((String)o);
        
        }
        
        weeklabel = " " + previousweek;
        dataset.addValue(multiplicator*total/count,legend,weeklabel);
       // System.out.println(100*total/count);
    }
   
    
    private void EmptyGrpahData(int previousweek, Inte i1, DefaultTableModel tbl, DefaultCategoryDataset dataset, String legend)
    {
        
        
        // TODO TODO IT DOES NOT RECOVER MORE THAN 1 WEEK
        String weeklabel;
        weeklabel = " " + previousweek;
        dataset.addValue(0,legend,weeklabel);
        i1.x = i1.x+1;
    }
   
    
}