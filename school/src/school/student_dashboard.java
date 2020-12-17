
package school;

import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;


public class student_dashboard extends javax.swing.JFrame {
    String id,std_id;
ResultSet rs;
DefaultTableModel model=new DefaultTableModel();
JTable table;
 Connection con=null;
    PreparedStatement ps=null,ps1=null,ps2=null,pst;
    Statement stm,st; 

    
    public student_dashboard(String id) {
            this.id=id;
            initComponents();
            this.setLocationRelativeTo(null);// center form in the screen
            String sql="select first_name,last_name from students where studentID='"+id+"'";

        try {

            ps=Connect.ConnectDB().prepareStatement(sql);
            ResultSet reslt =ps.executeQuery();
            if (reslt.next()){
            username.setText(reslt.getString("first_name")+" "+reslt.getString("last_name"));
            }
            
            showCourse();  
        } catch (SQLException ex) {
            Logger.getLogger(student_dashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void showCourse(){
                        
    table= new JTable(){
            @Override
            public boolean isCellEditable(int row, int column) {                
                return false;               
        }
        };
        jScrollPane.setViewportView(table);
      DefaultTableModel model=new DefaultTableModel();
                model.addColumn("   ");                
                model.addColumn("   ");                
                model.addColumn("   ");                



      table.setModel(model);
        table.setPreferredScrollableViewportSize(table.getPreferredSize());
        table.getColumnModel().getColumn(0).setPreferredWidth(200);
String std_query="SELECT * from students where studentID='"+id+"'";
     
   try{
	            pst=Connect.ConnectDB().prepareStatement(std_query);
	            ResultSet resultat =pst.executeQuery();
	            while(resultat.next()){
                      model.addRow(new Object []{"theory course",resultat.getString("theory_course")});
                      model.addRow(new Object []{"total theory course ",resultat.getString("theory_course_hrs")});
                      model.addRow(new Object []{"completed theory course ",resultat.getString("completed_thCrs_hrs")});
                           if(resultat.getInt("theory_exam")>=24){
                      model.addRow(new Object []{"teacher",teacher(resultat.getString("teacherID"))});
                      model.addRow(new Object []{"total practice course ",resultat.getString("practice_course_hrs")});
                      model.addRow(new Object []{"completed practice course ",resultat.getString("completed_prCrs_hrs")});
                      if(resultat.getInt("completed_prCrs_hrs")<resultat.getInt("practice_course_hrs")){
                      model.addRow(new Object []{"book your next practice course ",resultat.getString("next_crs_date")});
                    }
  
                           }
                        
                        
                        
                        
  
                    }
	        }catch(SQLException ex){
			 JOptionPane.showMessageDialog(this,ex);
	        }
           
      table.setModel(model);
        table.addMouseListener(new MouseAdapter() {
        @Override
        public void mousePressed(MouseEvent e) {
            if (e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() == 1) {
               if(table.getSelectedColumn()==1 && table.getSelectedRow()==6){
                JDateChooser jd = new JDateChooser();
                jd.setDateFormatString("yyyy-MM-dd hh:mm:ss");
String message ="Choose a new course date:\n";
Object[] params = {message,jd};
JOptionPane.showConfirmDialog(null,params,"next course date", JOptionPane.PLAIN_MESSAGE);
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
String s=sdf.format(((JDateChooser)params[1]).getDate());   
                   if(!s.equals("")){
                   update(s);    }  
               }      
            }
        }

    });
                         
                        
                      
         
    
}
     
   private void update(String str){
                  String update_query="UPDATE `students` SET `next_crs_date`=? WHERE studentID=?";
         try {
             ps1 = Connect.ConnectDB().prepareStatement(update_query);
         ps1.setString(1,str);
         ps1.setString(2, id);
         if (ps1.executeUpdate() != 0) {
             JOptionPane.showMessageDialog(null, "next course is booked");
         }
             
         } catch (SQLException ex) {
        Logger.getLogger(updateStudent.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    showCourse();
    
    }
    String teacher(String tchID){
         String sql="select firstname,lastname from teachers where teacherID='"+tchID+"'";
         String name="";
            try {
            ps=Connect.ConnectDB().prepareStatement(sql);
            ResultSet reslt =ps.executeQuery();
            if (reslt.next()){
            name=reslt.getString("firstname")+" "+reslt.getString("lastname");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(student_dashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    return name;
    
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
        crs_p = new javax.swing.JPanel();
        crs = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        reslt_p = new javax.swing.JPanel();
        results = new javax.swing.JLabel();
        exam_p = new javax.swing.JPanel();
        exam = new javax.swing.JLabel();
        payment_p = new javax.swing.JPanel();
        payment = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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
                .addComponent(username, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(302, 302, 302)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(change, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(username, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel1.setBackground(new java.awt.Color(0, 128, 128));
        jPanel1.setForeground(new java.awt.Color(204, 204, 204));

        crs_p.setBackground(new java.awt.Color(0, 102, 102));

        crs.setBackground(new java.awt.Color(153, 0, 51));
        crs.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        crs.setForeground(new java.awt.Color(204, 204, 204));
        crs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/report.png"))); // NOI18N
        crs.setText("Course");

        javax.swing.GroupLayout crs_pLayout = new javax.swing.GroupLayout(crs_p);
        crs_p.setLayout(crs_pLayout);
        crs_pLayout.setHorizontalGroup(
            crs_pLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(crs_pLayout.createSequentialGroup()
                .addComponent(crs, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        crs_pLayout.setVerticalGroup(
            crs_pLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, crs_pLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(crs, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logout.png"))); // NOI18N
        jLabel4.setText("Log out");
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        reslt_p.setBackground(new java.awt.Color(0, 128, 128));

        results.setBackground(new java.awt.Color(0, 51, 102));
        results.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        results.setForeground(new java.awt.Color(204, 204, 204));
        results.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/report.png"))); // NOI18N
        results.setText("results");
        results.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                resultsMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                resultsMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                resultsMouseExited(evt);
            }
        });

        javax.swing.GroupLayout reslt_pLayout = new javax.swing.GroupLayout(reslt_p);
        reslt_p.setLayout(reslt_pLayout);
        reslt_pLayout.setHorizontalGroup(
            reslt_pLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(reslt_pLayout.createSequentialGroup()
                .addComponent(results, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        reslt_pLayout.setVerticalGroup(
            reslt_pLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(results, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        exam_p.setBackground(new java.awt.Color(0, 128, 128));

        exam.setBackground(new java.awt.Color(0, 51, 102));
        exam.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        exam.setForeground(new java.awt.Color(204, 204, 204));
        exam.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/report.png"))); // NOI18N
        exam.setText("Exams");
        exam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                examMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                examMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                examMouseExited(evt);
            }
        });

        javax.swing.GroupLayout exam_pLayout = new javax.swing.GroupLayout(exam_p);
        exam_p.setLayout(exam_pLayout);
        exam_pLayout.setHorizontalGroup(
            exam_pLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(exam_pLayout.createSequentialGroup()
                .addComponent(exam, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        exam_pLayout.setVerticalGroup(
            exam_pLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(exam_pLayout.createSequentialGroup()
                .addComponent(exam, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        payment_p.setBackground(new java.awt.Color(0, 128, 128));

        payment.setBackground(new java.awt.Color(0, 51, 102));
        payment.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        payment.setForeground(new java.awt.Color(204, 204, 204));
        payment.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/report.png"))); // NOI18N
        payment.setText("Payment");
        payment.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                paymentMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                paymentMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                paymentMouseExited(evt);
            }
        });

        javax.swing.GroupLayout payment_pLayout = new javax.swing.GroupLayout(payment_p);
        payment_p.setLayout(payment_pLayout);
        payment_pLayout.setHorizontalGroup(
            payment_pLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(payment_pLayout.createSequentialGroup()
                .addComponent(payment, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        payment_pLayout.setVerticalGroup(
            payment_pLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(payment, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Dashboard");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(crs_p, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(reslt_p, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(exam_p, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(payment_p, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65)
                .addComponent(crs_p, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(exam_p, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(reslt_p, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(payment_p, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 753, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 171, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

   
    
      
    
     
  
       
    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        // TODO add your handling code here:
                                   this.dispose();
        new LoginForm().setVisible(true);
    }//GEN-LAST:event_jLabel4MouseClicked

    private void resultsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_resultsMouseClicked
        // TODO add your handling code here:
                                   this.dispose();
        new student_results(id).setVisible(true);
    }//GEN-LAST:event_resultsMouseClicked

    private void resultsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_resultsMouseEntered
        // TODO add your handling code here:
        reslt_p.setBackground(new Color(0,102,102));
    }//GEN-LAST:event_resultsMouseEntered

    private void resultsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_resultsMouseExited
        // TODO add your handling code here:
        reslt_p.setBackground(new Color(0,128,128));
    }//GEN-LAST:event_resultsMouseExited

    private void examMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_examMouseClicked
        // TODO add your handling code here:
                                   this.dispose();
        new student_exams(id).setVisible(true);
    }//GEN-LAST:event_examMouseClicked

    private void examMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_examMouseEntered
        // TODO add your handling code here:
        exam_p.setBackground(new Color(0,102,102));
    }//GEN-LAST:event_examMouseEntered

    private void examMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_examMouseExited
        // TODO add your handling code here:
        exam_p.setBackground(new Color(0,128,128));
    }//GEN-LAST:event_examMouseExited

    private void paymentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_paymentMouseClicked
        // TODO add your handling code here:
                                   this.dispose();
        new student_payment(id).setVisible(true);
    }//GEN-LAST:event_paymentMouseClicked

    private void paymentMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_paymentMouseEntered
        // TODO add your handling code here:
         payment_p.setBackground(new Color(0,102,102));
    }//GEN-LAST:event_paymentMouseEntered

    private void paymentMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_paymentMouseExited
        // TODO add your handling code here:
          payment_p.setBackground(new Color(0,128,128));
    }//GEN-LAST:event_paymentMouseExited

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
    private javax.swing.JLabel change;
    private javax.swing.JLabel crs;
    private javax.swing.JPanel crs_p;
    private javax.swing.JLabel exam;
    private javax.swing.JPanel exam_p;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JLabel payment;
    private javax.swing.JPanel payment_p;
    private javax.swing.JPanel reslt_p;
    private javax.swing.JLabel results;
    private javax.swing.JLabel username;
    // End of variables declaration//GEN-END:variables
}
