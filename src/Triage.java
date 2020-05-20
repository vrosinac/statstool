
import java.util.logging.Logger;
import org.joda.time.DateTime;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author vrosinac
 */
public class Triage {
    private long duration;
    private long caseNumber;
    private DateTime dateSeverityChange;
    private  DateTime enterTriage;

    
    private  DateTime exitTriage;
    private  String oldSeverity;
    private  String newSeverity;
    private  String caseSeverity;
    private  String initialSeverity;
    private int severityDrop;

    public DateTime getEnterTriage() {
        return enterTriage;
    }
    public String getInitialSeverity() {
        if (initialSeverity != null)
        {
            return initialSeverity;
        }
        return caseSeverity;
     }
    
    
    public String getCaseSeverity() {
        return caseSeverity;
    }
    
    public long getDuration() {
        return duration;
    }
    
    
    public int getSeverityDrop() {
        
        //only if we have dates
        if (exitTriage != null && dateSeverityChange != null )
        {
            // ONLY IF date change severity <  date exit triage for reporting
            if (  exitTriage.isAfter(dateSeverityChange.getMillis()) ||   (enterTriage != null && enterTriage.isAfter(dateSeverityChange.getMillis())))
            {   
                return severityDrop;
            }
            else
                return 0;
        }
        return 0;
    }
    
    private void computeDuration()
    {
        // to calculate severityDrop
        // to calculate duration (in "days":  it should be in hours really as the days are not full becuasae we remove nights
        if (enterTriage != null && !enterTriage.equals(new DateTime(1970, 01, 01, 00, 00)))
        {
            if (enterTriage.getMillis() == exitTriage.getMillis())
            {
                int stophere = 1;
            }
            duration = duration +  CalculateTimeInTriage(enterTriage, exitTriage);
            System.out.println("TRIAGE compute duration: " + duration);
        
            // reset
            // TODO always reset?
            enterTriage = null; //new DateTime(1970, 01, 01, 00, 00);// we zero it to be able to count multiple triage re-entries
            exitTriage = null; //new DateTime(1970, 01, 01, 00, 00);
        }
        else
        {//and if we can't count, discar the calculation for that case 
        //(maybe we don't have the start fo the data in the SFDC extract)
            System.out.println("TRIAGE could not compute duration");
        }

        trace();
        
    }

    public Triage(long caseNumber, String caseSeverity) {
        this.caseNumber = caseNumber;
        this.caseSeverity = caseSeverity;
    }

    
    private void computeSeverityDrop()
    {
        // if we can't count, discar the calculation for that case 
        //(maybe we don't have the start fo the data in the SFDC extract)
        // but log it
        if (oldSeverity.equals("") || newSeverity.equals("")) 
        {
            //  Maybe it's ok, many cases don't have a severity change    system.out.println(" severity")
            System.out.println("TRIAGE computeSeverityDrop, nothing to do");
        }


        // to calculate severityDrop
        if (!newSeverity.equals(oldSeverity))
        {// did we sev drop in triage?
            if(  newSeverity.compareTo(oldSeverity)  >0)
            {/// we dropped
                severityDrop = severityDrop + 1;
               /* if(  (newSeverity.compareTo("2 - a") <0 ) && ( oldSeverity.compareTo("2 - a")  >0) )
                {/// we dropped below sev2 if the old was above "2 - a"  and teh new below "2 - a"  we are comparing String using alpahbetical orders
                    severityDropBeow2 = 1;
                }*/
            }
            if(  newSeverity.compareTo(oldSeverity)  <0)
            {   // we increased the severirty 
                severityDrop = severityDrop -1;
            }
        
        }
        System.out.println("TRIAGE computeSeverityDrop:" + severityDrop);
        
        trace();
    }



    public void setSeverityChangeData(String oldSeverity, String newSeverity, DateTime dateSeverityChange ) {
       
        if ((oldSeverity != null) && (newSeverity != null) )
        {
            if (this.initialSeverity != null)
            {
                this.initialSeverity = oldSeverity;
            }
            this.oldSeverity = oldSeverity;
            this.newSeverity = newSeverity;
            this.dateSeverityChange = dateSeverityChange;
           
        }
        System.out.println("TRIAGE oldSeverity:" + oldSeverity + ", newSeverity:" + newSeverity + ", date:" + dateSeverityChange );
        
        computeSeverityDrop();
        
    } 

    public void setEnterTriage(DateTime enterTriage) {
        this.enterTriage = enterTriage;
        System.out.println("TRIAGE enterTriage:" + enterTriage);
    }

    public void setExitTriage(DateTime exitTriage) {
        this.exitTriage = exitTriage;
        System.out.println("TRIAGE exitTriage:" + exitTriage);
        computeDuration();
    }

    public void setOldSeverity(String oldSeverity) {
        this.oldSeverity = oldSeverity;
        System.out.println("TRIAGE oldSeverity:" + oldSeverity);
    }

    public void setNewSeverity(String newSeverity) {
        this.newSeverity = newSeverity;
        System.out.println("TRIAGE newSeverity:" + newSeverity);
        
        
    }
    public void trace(){
        System.out.println("TRIAGE TRACE for " + caseNumber + ", enterTriage:" + enterTriage + ", exitTriage:" + exitTriage + ", oldSeverity:" + oldSeverity +
                ", newSeverity:" + newSeverity + ", duration:" + duration + ", drop:" + severityDrop);
    
    }
    
    
    
    
    private long CalculateTimeInTriage (DateTime dateEnteringTriage, DateTime dateExitingTriage)
    {
        //dateExitingTriage - dateEnteringTriage  - 15 hours if we span accorss 2 days  and -48 if we have a week end
        // but i still need to know the timezone.
        //no, if we have a change of day and more than 10 hours of tine differemce then for sure the traiger went to bed
        /// so we substract 14 hours to the time difference if it was 2 consecutve days.
        
        //if it was on more than 2 consecutive dataes
                //if week edn we substract 48 hours
                //if  not wee ekd we substract 14 hours per calendar day    
                 
        
        long rawDuration = (((dateExitingTriage.getMillis() - dateEnteringTriage.getMillis())/ 1000)/60)/60; // hours between enter and exit
        DateTime increment = dateEnteringTriage;
        int discount = 0;
        int count=0;
        while ( 1>=0)
        {
            
            increment = increment.plusDays(1); // be carefull of midnighht.
            
            if (increment.isAfter(dateExitingTriage.getMillis()) ) 
            {
                break;
            }
            
            // 6 = Saturday   // 7 = Sundday
            int day = increment.getDayOfWeek();
            if (day ==7 || day == 8)
            {
                discount = discount+1;
            }
            else
            {
                count = count +1;
            }
        }
        
        //we remove 14 hours per day if there are many calendar days
        // but if  there is juts count =1 we need to check if there are more than 10 hours.
        // if tere is more than 10 hours ten we know there wasa one night in betweenn the days and so we do -14
        // we remove 24 hours per week end day
        
        rawDuration = rawDuration - 24*discount;
        
        if (count >1)
        {
            rawDuration = rawDuration - count*14;
        }
        else 
        {
            if (count <=1)
            {
                if (dateExitingTriage.getMillis() - dateEnteringTriage.getMillis() > 1000*60*60*14)
                {
                    // remove 14 hours
                    rawDuration = rawDuration - 14;
                }
            }
            
        }
        
        
        return rawDuration  ;
    }        
   
    
    
}
