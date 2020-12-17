
package school;

import java.awt.Color;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.border.Border;


public class teacherCalendar extends javax.swing.JFrame {
    String id,std_id;
ResultSet rs;
JTable table;
 Connection con=null;
    PreparedStatement ps=null,prs,pst;
String selected_date ;
String crs_date,crs_time,exam_date,exam_time;
   DateFormat  dateFormat;

    public teacherCalendar(String id) {
        
            this.id=id;
            initComponents();
                    this.setLocationRelativeTo(null);// center form in the screen

            String sql="select firstname,lastname from teachers where teacherID='"+id+"'";
      
               try {
                    ps=Connect.ConnectDB().prepareStatement(sql);
                    ResultSet reslt =ps.executeQuery();
                        if (reslt.next()){
                    username.setText(reslt.getString("firstname")+" "+reslt.getString("lastname"));
                        }
            
                    } catch (SQLException ex) {
                        Logger.getLogger(teacherCalendar.class.getName()).log(Level.SEVERE, null, ex);
                    }
             
            
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            selected_date=dateFormat.format(cal.getDate());
                showCalendar(selected_date); 

           cal.addPropertyChangeListener(new PropertyChangeListener() {
                 @Override
                public void propertyChange(PropertyChangeEvent evt) {
                          selected_date=dateFormat.format(cal.getDate()); 
                             showCalendar(selected_date); 

}
            });
            

            
            
            
    }

    private void showCalendar(String date){
              DefaultTableModel model=new DefaultTableModel();
             model.addColumn("08:00");
            model.addColumn("09:00");
            model.addColumn("10:00");
            model.addColumn("11:00");
            model.addColumn("12:00");
            model.addColumn("13:00");
            model.addColumn("14:00");
            model.addColumn("15:00");
            model.addColumn("16:00");
            model.addColumn("17:00");
            model.addColumn("18:00");
            model.addRow(new Object []{ "","","","","","","","","","",""});
            String[] names=new String [] {"","","","","","","","","","",""};
        String std_query = "SELECT  first_name , last_name,next_crs_date,practice_exam_date,practice_exam_location FROM students where teacherID='"+id+"'";
            try {
            ps=Connect.ConnectDB().prepareStatement(std_query);
            ResultSet resltat =ps.executeQuery();
            while (resltat.next()){
                if(resltat.getString("next_crs_date")!= null  ){
                String[] parts = resltat.getString("next_crs_date").split(" ");
            crs_date=parts[0];
            crs_time=parts[1];
            
             if(date.equals(crs_date)){
                names[index(crs_time)]=resltat.getString("first_name")+" "+resltat.getString("last_name");
                                model.setValueAt("course",0,index(crs_time));
            }
                }
                else if(resltat.getString("practice_exam_date")!=null ){
               String[] part = resltat.getString("practice_exam_date").split(" ");
            exam_date=part[0];
            exam_time=part[1];
            
            
           
            if(date.equals(exam_date)){
                names[index(exam_time)]=resltat.getString("first_name")+" "+resltat.getString("last_name")+" \n at "+resltat.getString("practice_exam_location");

                                model.setValueAt("exam",0,index(exam_time));

            }

            
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(teacherCalendar.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            
            
            table= new JTable(model){
                @Override
                public boolean isCellEditable(int row, int column) {               
                    return false;
                }
                
                
               
            };
              table.addMouseListener(new MouseAdapter() {
        @Override
        public void mousePressed(MouseEvent e) {
            if (e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() == 1) {
                String Value = (String) table.getValueAt(0, table.getSelectedColumn());
               if(!Value.equals("")){
 JOptionPane.showMessageDialog(null,Value +" of "+names[table.getSelectedColumn()]);
 }else{
 JOptionPane.showMessageDialog(null," you are free at this time ");

                      }

                
        
            }
        }

    });
       

            jScrollPane.setViewportView(table);
            
            
                         
     
     
     
     
     
    }
@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        username = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        change = new javax.swing.JLabel();
        jScrollPane = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        std_p = new javax.swing.JPanel();
        std_list = new javax.swing.JLabel();
        calndr_p = new javax.swing.JPanel();
        calender = new javax.swing.JLabel();
        logout = new javax.swing.JLabel();
        rest_p = new javax.swing.JPanel();
        result = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cal = new com.toedter.calendar.JCalendar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1150, 505));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(1150, 500));

        jPanel3.setBackground(new java.awt.Color(0, 128, 128));

        username.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        username.setForeground(new java.awt.Color(255, 255, 255));

        change.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        change.setForeground(new java.awt.Color(255, 51, 51));
        change.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/edit_profile.png"))); // NOI18N
        change.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                changeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                changeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                changeMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(change)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(username, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(206, 206, 206)
                .addComponent(jLabel3)
                .addContainerGap(512, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(change, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(username, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel1.setBackground(new java.awt.Color(0, 128, 128));
        jPanel1.setForeground(new java.awt.Color(204, 204, 204));
        jPanel1.setPreferredSize(new java.awt.Dimension(220, 505));

        std_p.setBackground(new java.awt.Color(0, 128, 128));

        std_list.setBackground(new java.awt.Color(0, 128, 128));
        std_list.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        std_list.setForeground(new java.awt.Color(204, 204, 204));
        std_list.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/report.png"))); // NOI18N
        std_list.setText("Students list");
        std_list.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                stdListMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                stdListMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                stdListMouseExited(evt);
            }
        });

        javax.swing.GroupLayout std_pLayout = new javax.swing.GroupLayout(std_p);
        std_p.setLayout(std_pLayout);
        std_pLayout.setHorizontalGroup(
            std_pLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(std_pLayout.createSequentialGroup()
                .addComponent(std_list, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        std_pLayout.setVerticalGroup(
            std_pLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, std_pLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(std_list, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        calndr_p.setBackground(new java.awt.Color(0, 102, 102));

        calender.setBackground(new java.awt.Color(0, 51, 102));
        calender.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        calender.setForeground(new java.awt.Color(204, 204, 204));
        calender.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/report.png"))); // NOI18N
        calender.setText("calendar");

        javax.swing.GroupLayout calndr_pLayout = new javax.swing.GroupLayout(calndr_p);
        calndr_p.setLayout(calndr_pLayout);
        calndr_pLayout.setHorizontalGroup(
            calndr_pLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(calndr_pLayout.createSequentialGroup()
                .addComponent(calender, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        calndr_pLayout.setVerticalGroup(
            calndr_pLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(calender, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
        );

        logout.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        logout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logout.png"))); // NOI18N
        logout.setText("Log out");
        logout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logoutMouseClicked(evt);
            }
        });

        rest_p.setBackground(new java.awt.Color(0, 128, 128));

        result.setBackground(new java.awt.Color(0, 51, 102));
        result.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        result.setForeground(new java.awt.Color(204, 204, 204));
        result.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/report.png"))); // NOI18N
        result.setText("results");
        result.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                resultMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                resultMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                resultMouseExited(evt);
            }
        });

        javax.swing.GroupLayout rest_pLayout = new javax.swing.GroupLayout(rest_p);
        rest_p.setLayout(rest_pLayout);
        rest_pLayout.setHorizontalGroup(
            rest_pLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rest_pLayout.createSequentialGroup()
                .addComponent(result, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        rest_pLayout.setVerticalGroup(
            rest_pLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(result, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Dashboard");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(std_p, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(calndr_p, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(rest_p, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(logout, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 30, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65)
                .addComponent(std_p, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(calndr_p, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(rest_p, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 103, Short.MAX_VALUE)
                .addComponent(logout, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
        );

        cal.setPreferredSize(new java.awt.Dimension(212, 157));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(cal, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addComponent(jScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 804, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 488, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 1236, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 488, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

   
    
   int index(String time){
   switch(time){
       case "08:00:00" : return 0;
       case "09:00:00" : return 1;
       case "10:00:00" : return 2;
       case "11:00:00" : return 3;
       case "12:00:00" : return 4;
       case "01:00:00" : return 5;
       case "02:00:00" : return 6;
       case "03:00:00" : return 7;
       case "04:00:00" : return 8;
       case "05:00:00" : return 9;
       case "06:00:00" : return 10;
   }
        return -1;

   }   
    
     
  
       
    private void logoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutMouseClicked
        // TODO add your handling code here:
       this.dispose();
        new LoginForm().setVisible(true);
    }//GEN-LAST:event_logoutMouseClicked

    private void stdListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_stdListMouseClicked
        // TODO add your handling code here:
       this.dispose();
         new teacher_dashboard(id).setVisible(true);

    }//GEN-LAST:event_stdListMouseClicked

    private void stdListMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_stdListMouseEntered
        // TODO add your handling code here:
                                        std_p.setBackground(new Color(0,102,102));

    }//GEN-LAST:event_stdListMouseEntered

    private void stdListMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_stdListMouseExited
        // TODO add your handling code here:
                        std_p.setBackground(new Color(0,128,128));

    }//GEN-LAST:event_stdListMouseExited

    private void resultMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_resultMouseClicked
        // TODO add your handling code here:
       this.dispose();
         new teacherStdResults(id).setVisible(true);
    }//GEN-LAST:event_resultMouseClicked

    private void resultMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_resultMouseEntered
        // TODO add your handling code here:
                                                rest_p.setBackground(new Color(0,102,102));

    }//GEN-LAST:event_resultMouseEntered

    private void resultMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_resultMouseExited
        // TODO add your handling code here:
                                rest_p.setBackground(new Color(0,128,128));

    }//GEN-LAST:event_resultMouseExited

    private void changeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_changeMouseClicked
        // TODO add your handling code here:
        change_U_P rf=new change_U_P(id);
        rf.setVisible(true);
        rf.pack();
        rf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }//GEN-LAST:event_changeMouseClicked

    private void changeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_changeMouseEntered
        // TODO add your handling code here:
        Border label_border=BorderFactory.createMatteBorder(0,0,1,0,Color.black);
        change.setBorder(label_border);
    }//GEN-LAST:event_changeMouseEntered

    private void changeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_changeMouseExited
        // TODO add your handling code here:
        change.setBorder(null);
    }//GEN-LAST:event_changeMouseExited

   
  
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JCalendar cal;
    private javax.swing.JLabel calender;
    private javax.swing.JPanel calndr_p;
    private javax.swing.JLabel change;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JLabel logout;
    private javax.swing.JPanel rest_p;
    private javax.swing.JLabel result;
    private javax.swing.JLabel std_list;
    private javax.swing.JPanel std_p;
    private javax.swing.JLabel username;
    // End of variables declaration//GEN-END:variables
}
