import javax.swing.text.*;
import java.awt.event.*;
import javax.swing.*;
import java.text.*;
import java.awt.*;
import java.util.*;


class JFormattedDateTextField extends JFormattedTextField {
   Format format = new SimpleDateFormat("MM/dd/yyyy");
  
   public JFormattedDateTextField() {
      super();
      MaskFormatter maskFormatter = null;
      try {
         maskFormatter = new MaskFormatter("##/##/####");
      } catch (ParseException e) {
         e.printStackTrace();
      }
  
      maskFormatter.setPlaceholderCharacter('_');
      setFormatterFactory(new DefaultFormatterFactory(maskFormatter));
      this.addFocusListener(new FocusAdapter() {
         public void focusGained(FocusEvent e) {
            if (getFocusLostBehavior() == JFormattedTextField.PERSIST)
               setFocusLostBehavior(JFormattedTextField.COMMIT_OR_REVERT);
            }
   
            public void focusLost(FocusEvent e) {
               try {
                  Date date = (Date) format.parseObject(getText());
                  setValue(format.format(date));
               } catch (ParseException pe) {
                  setFocusLostBehavior(JFormattedTextField.PERSIST);
                  setText("");
                  setValue(null);
               }
            }
      });
   }
  
   public void setValue(Date date) {
      super.setValue(toString(date));
   }
  
   private Date toDate(String sDate) {
      Date date = null;
      if (sDate == null) return null;
      try {
         date = (Date) format.parseObject(sDate);
      }
      catch (ParseException pe) {
         // ignore
      }
  
      return date;
   }
  
   private String toString(Date date) {
      try {
         return format.format(date);
      } catch (Exception e) {
         return "";
      }
   }
}