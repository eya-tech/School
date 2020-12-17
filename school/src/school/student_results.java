/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package school;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;


public class student_results extends javax.swing.JFrame {
    String id,std_id;
ResultSet rs;
DefaultTableModel model=new DefaultTableModel();
JTable table;
 Connection con=null;
    PreparedStatement ps=null,ps1=null,ps2=null,pst;
    Statement stm,st; 
String lic , select;
    
    public student_results(String id) {
           this.id=id;
            initComponents();
            String sql="select first_name,last_name from students where studentID='"+id+"'";
                            this.setLocationRelativeTo(null);// center form in the screen

        try {
         
            ps=Connect.ConnectDB().prepareStatement(sql);
            ResultSet reslt =ps.executeQuery();
            if (reslt.next()){
            username.setText(reslt.getString("first_name")+" "+reslt.getString("last_name"));
                   showResults();  
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(student_results.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void showResults(){
                        
    table= new JTable(){
            @Override
            public boolean isCellEditable(int row, int column) {                
                return false;               
        }
        };
    
      table.addMouseListener(new MouseAdapter() {
        @Override
        public void mousePressed(MouseEvent e) {
            if (e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() == 1) {
               if(table.getSelectedColumn()==2 && table.getSelectedRow()==0){
                 String Value = (String) table.getValueAt(0,1);
                 if(Value.equals("failed")){
                          theory_failer();
                          showResults();

            
                 }
                   
                   
 }else if(table.getSelectedColumn()==2 && table.getSelectedRow()==1){
                String Val = (String) table.getValueAt(1,1);
                          if(Val.equals("failed")){
                          practice_failer();
                          showResults();

                 }
                          else if(Val.equals("succeeded")){
                          practice_succ();
                          showResults();
                 }
           

                      }      
            }
        }

    });
    
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
                        lic=resultat.getString("licence");
                     if(resultat.getString("theory_exam")!=null){
                      model.addRow(new Object []{"theory exam  ",result(1,resultat.getString("theory_exam")),theory_reslt(result(1,resultat.getString("theory_exam")))});
                           }
                           if(resultat.getString("practice_exam")!=null){
                      model.addRow(new Object []{"practice exam  ",result(2,resultat.getString("practice_exam")),practice_reslt(result(2,resultat.getString("practice_exam")))});
                           }
                        
                        
                        
                        
  
                    }
	        }catch(SQLException ex){
			 JOptionPane.showMessageDialog(this,ex);
	        }
           
      table.setModel(model);
      
                         
                        
                      
         
    
}
     
    
    String result( int i, String str){
       
        if(i==1){
           int j=Integer.parseInt(str);
            
             if(j<=23 && j>0){
                   return "failed";
              }else if(j>23 && j<=30){
                  return "succeeded";
                       } 
               else if(j==-1){
                  return "repassing";
                       }
              else{return "not yet";}
            
        }
        else if (i==2){
           if(str.equals("invalid")){
                   return "failed";
              }else if(str.equals("valid")){
                  return "succeeded";
                       }  
            else if(str.equals("repass")){
                  return "repassing";
                       } 
              else{return "not yet";}
        
        }
        
        else return " ";
   
    }

    String theory_reslt(String str){
    if(str.equals("failed")){
    return"repass the theory exam";}
    return " ";
    }
    String practice_reslt(String str){
    if(str.equals("failed")){
    return"repass the practice exam";}
    else if(str.equals("succeeded")){
    return"add another licence";}
    return " ";
    }
    void theory_failer(){
     String update_query="UPDATE `students` SET `theory_exam_date`=?,`theory_exam`=? WHERE studentID=?";
         try {
             ps = Connect.ConnectDB().prepareStatement(update_query);
         ps.setString(1,null);
         ps.setInt(2,-1);
         ps.setString(3, id);
         if (ps.executeUpdate() != 0) {
             JOptionPane.showMessageDialog(null, "your request has been send. \n the exam date will be set in the exams section");
         }
             
         } catch (SQLException ex) {
        Logger.getLogger(updateStudent.class.getName()).log(Level.SEVERE, null, ex);
    }

    }
    
    
      void practice_failer(){
     String update_query="UPDATE `students` SET `practice_exam_date`=?,`practice_exam_location`=?"
        + ",`practice_exam`=? WHERE studentID=?";
       
         try {
             ps = Connect.ConnectDB().prepareStatement(update_query);
         ps.setString(1,null);
         ps.setString(2,null);
         ps.setString(3, "repass");
         ps.setString(4, id);
         if (ps.executeUpdate() != 0) {
             JOptionPane.showMessageDialog(null, "your request has been send. \n the exam date will be set in the exams section");
         } 
             
         } catch (SQLException ex) {
        Logger.getLogger(updateStudent.class.getName()).log(Level.SEVERE, null, ex);
    }

    }
           void practice_succ(){
  JComboBox box=new JComboBox();
  select=lic;
  box.addItem("select a category");
  box.addItem("A1");
  box.addItem("A");
  box.addItem("B+E");
  box.addItem("C");
  box.addItem("C+E");  
  box.addItem("D");
    box.addItem("D1");
  box.addItem("H");
    box.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
  if(!box.getSelectedItem().equals("select a category")){
      select=(String) box.getSelectedItem();
   }
            
            }
        });
 

  
String message ="Choose the licence category :\n";
Object[] params = {message,box};
JOptionPane.showConfirmDialog(null,params,"other licence", JOptionPane.PLAIN_MESSAGE);
                   
               
               
     String update_query="UPDATE `students` SET `practice_exam_date`=?,`practice_exam_location`=?"
        + ",`practice_exam`=?,`licence`=? WHERE studentID=?";
       
         try {
             ps = Connect.ConnectDB().prepareStatement(update_query);
         ps.setString(1,null);
         ps.setString(2,null);
         ps.setString(3, null); 
         ps.setString(4, select);
         ps.setString(5, id);
         if (ps.executeUpdate() != 0) {
             JOptionPane.showMessageDialog(null, "your new licence has been added. \n ");
         } 
             
         } catch (SQLException ex) {
        Logger.getLogger(updateStudent.class.getName()).log(Level.SEVERE, null, ex);
    }

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
        jLabel4 = new javax.swing.JLabel();
        reslt_p = new javax.swing.JPanel();
        rslt = new javax.swing.JLabel();
        exam_p = new javax.swing.JPanel();
        exam = new javax.swing.JLabel();
        crs_p = new javax.swing.JPanel();
        crs = new javax.swing.JLabel();
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
                .addGap(299, 299, 299)
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

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logout.png"))); // NOI18N
        jLabel4.setText("Log out");
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        reslt_p.setBackground(new java.awt.Color(0, 102, 102));

        rslt.setBackground(new java.awt.Color(153, 0, 51));
        rslt.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rslt.setForeground(new java.awt.Color(204, 204, 204));
        rslt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/report.png"))); // NOI18N
        rslt.setText("Results");

        javax.swing.GroupLayout reslt_pLayout = new javax.swing.GroupLayout(reslt_p);
        reslt_p.setLayout(reslt_pLayout);
        reslt_pLayout.setHorizontalGroup(
            reslt_pLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(reslt_pLayout.createSequentialGroup()
                .addComponent(rslt, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        reslt_pLayout.setVerticalGroup(
            reslt_pLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, reslt_pLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(rslt, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addComponent(exam, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        exam_pLayout.setVerticalGroup(
            exam_pLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(exam, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        crs_p.setBackground(new java.awt.Color(0, 128, 128));

        crs.setBackground(new java.awt.Color(0, 51, 102));
        crs.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        crs.setForeground(new java.awt.Color(204, 204, 204));
        crs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/report.png"))); // NOI18N
        crs.setText("Course");
        crs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                crsMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                crsMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                crsMouseExited(evt);
            }
        });

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
            .addComponent(crs, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
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
                .addComponent(payment, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
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
            .addComponent(reslt_p, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(exam_p, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(crs_p, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 574, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(350, Short.MAX_VALUE))))
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

    private void crsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_crsMouseClicked
        // TODO add your handling code here:
                                   this.dispose();
        new student_dashboard(id).setVisible(true);
    }//GEN-LAST:event_crsMouseClicked

    private void crsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_crsMouseEntered
        // TODO add your handling code here:
        crs_p.setBackground(new Color(0,102,102));
    }//GEN-LAST:event_crsMouseEntered

    private void crsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_crsMouseExited
        // TODO add your handling code here:
        crs_p.setBackground(new Color(0,128,128));
    }//GEN-LAST:event_crsMouseExited

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
    private javax.swing.JLabel rslt;
    private javax.swing.JLabel username;
    // End of variables declaration//GEN-END:variables
}
