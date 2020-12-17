package school;




import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ButtonGroup;


import javax.swing.JOptionPane;
import javax.swing.JTextField;



public class repassUpdate extends javax.swing.JFrame {
String id;
PreparedStatement pst,ps,ps1;
ResultSet rs;
int j;


   
    public repassUpdate(String id)  {
 
        this.id=id;
        initComponents();
                this.setLocationRelativeTo(null);// center form in the screen
        ButtonGroup bg0 = new ButtonGroup();
        bg0.add(td1);
        bg0.add(td2);
        
         
        
          ButtonGroup bg2 = new ButtonGroup();
        bg2.add(pd1);
        bg2.add(pd2);
        
        ButtonGroup bg = new ButtonGroup();
        bg.add(valid);
        bg.add(invalid);
        
        
        theo_date.setDateFormatString("yyyy-MM-dd hh:mm:ss");
        pratic_date.setDateFormatString("yyyy-MM-dd hh:mm:ss");
        

        String query = "SELECT * FROM students where studentID='"+id+"'";
 
        try {
                    ps=Connect.ConnectDB().prepareStatement(query);
	            ResultSet resltat =ps.executeQuery();
                          if (resltat.next()){
     name.setText(resltat.getString("first_name")+" "+resltat.getString("last_name"));
     
     if(resltat.getInt("theory_exam")==-1){
         j=1;
                   theo_score.setText(Integer.toString(resltat.getInt("theory_exam")));
    if(resltat.getString("th_last_feed")==null){
               
        td2.setSelected(false);
        td1.setSelected(false);
        }
        else if(resltat.getString("th_last_feed").equals("unpaid") ){
                  td2.setSelected(true);
      }
      else if(resltat.getString("th_last_feed").equals("paid") ){
                  td1.setSelected(true);
      }
     java.util.Date date ;
      if(resltat.getString("theory_exam_date")==null ){
      date = null;}
      else{date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(resltat.getString("theory_exam_date")); }
theo_date.setDate(date);
         
         jLabel15.setVisible(true);          td1.setVisible(true);       td2.setVisible(true); 
         jLabel11.setVisible(true); theo_date.setVisible(true);
                 jLabel6.setVisible(true); theo_score.setVisible(true);
                 
                 jLabel9.setVisible(false);  pd1.setVisible(false);  pd2.setVisible(false); 
                         jLabel8.setVisible(false);  pratic_date.setVisible(false); 
                                 jLabel10.setVisible(false);   pratic_location.setVisible(false); 
                                         
                                         jLabel5.setVisible(false);  valid.setVisible(false);  invalid.setVisible(false); 
         
     }else if(resltat.getString("practice_exam").equals("repass"))
     {
            j=2;            
         pratic_location.setText(resltat.getString("practice_exam_location"));
         
        if(resltat.getString("practice_exam")==null){
               
        invalid.setSelected(false);
        valid.setSelected(false);
        }
        else if(resltat.getString("practice_exam").equals("invalid") ){
                  invalid.setSelected(true);
      }
      else if(resltat.getString("practice_exam").equals("valid") ){
                  valid.setSelected(true);
      }
        
      
          
           if(resltat.getString("pr_last_feed")==null){
               
        pd2.setSelected(false);
        pd1.setSelected(false);
        }
        else if(resltat.getString("pr_last_feed").equals("unpaid") ){
                  pd2.setSelected(true);
      }
      else if(resltat.getString("pr_last_feed").equals("paid") ){
                  pd1.setSelected(true);
      }
         
         java.util.Date date ,date1;
    
if(resltat.getString("practice_exam_date")==null){
      date1 = null;}
else {date1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(resltat.getString("practice_exam_date")); }
pratic_date.setDate(date1);

     
     
      jLabel9.setVisible(true);  pd1.setVisible(true);  pd2.setVisible(true); 
                         jLabel8.setVisible(true);  pratic_date.setVisible(true); 
                                 jLabel10.setVisible(true);   pratic_location.setVisible(true); 
                                         
                                         jLabel5.setVisible(true);  valid.setVisible(true);  invalid.setVisible(true); 
         
      jLabel15.setVisible(false);          td1.setVisible(false);       td2.setVisible(false); 
         jLabel11.setVisible(false); theo_date.setVisible(false);
                 jLabel6.setVisible(false); theo_score.setVisible(false);
     
     
     
     
     }
        }
        }catch(SQLException ex) {
            java.util.logging.Logger.getLogger(repassUpdate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
      
        } catch (ParseException ex) {
        Logger.getLogger(repassUpdate.class.getName()).log(Level.SEVERE, null, ex);
    }


    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        update = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        theo_date = new com.toedter.calendar.JDateChooser();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        theo_score = new javax.swing.JTextField();
        valid = new javax.swing.JRadioButton();
        invalid = new javax.swing.JRadioButton();
        pratic_location = new javax.swing.JTextField();
        pratic_date = new com.toedter.calendar.JDateChooser();
        jPanel1 = new javax.swing.JPanel();
        name = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        pd1 = new javax.swing.JRadioButton();
        pd2 = new javax.swing.JRadioButton();
        jLabel15 = new javax.swing.JLabel();
        td1 = new javax.swing.JRadioButton();
        td2 = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 51, 51));
        jLabel5.setText("Practice exam validation:");

        update.setBackground(new java.awt.Color(255, 153, 51));
        update.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        update.setForeground(new java.awt.Color(0, 51, 51));
        update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/edit-icon.png"))); // NOI18N
        update.setText("Update");
        update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(0, 102, 102));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(0, 51, 51));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/close.png"))); // NOI18N
        jButton2.setText("Cancel");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 51, 51));
        jLabel6.setText("Theory exam score:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 51, 51));
        jLabel8.setText("Practice exam date:");

        theo_date.setBackground(new java.awt.Color(108, 122, 137));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 51, 51));
        jLabel10.setText("Practice exam location:");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 51, 51));
        jLabel11.setText("Theory exam date:");

        theo_score.setBackground(new java.awt.Color(108, 122, 137));
        theo_score.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        theo_score.setForeground(new java.awt.Color(228, 241, 254));
        theo_score.setText("0");

        valid.setBackground(new java.awt.Color(255, 255, 255));
        valid.setForeground(new java.awt.Color(0, 51, 51));
        valid.setText("valid");

        invalid.setBackground(new java.awt.Color(255, 255, 255));
        invalid.setForeground(new java.awt.Color(0, 51, 51));
        invalid.setText("invalid");
        invalid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                invalidActionPerformed(evt);
            }
        });

        pratic_location.setBackground(new java.awt.Color(108, 122, 137));
        pratic_location.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        pratic_location.setForeground(new java.awt.Color(228, 241, 254));
        pratic_location.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pratic_locationActionPerformed(evt);
            }
        });

        pratic_date.setBackground(new java.awt.Color(108, 122, 137));

        jPanel1.setBackground(new java.awt.Color(0, 128, 128));

        name.setFont(new java.awt.Font("Traditional Arabic", 1, 24)); // NOI18N
        name.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(119, 119, 119)
                .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(name, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 51, 51));
        jLabel9.setText("Practice demande :");

        pd1.setBackground(new java.awt.Color(255, 255, 255));
        pd1.setForeground(new java.awt.Color(0, 51, 51));
        pd1.setText("paid");

        pd2.setBackground(new java.awt.Color(255, 255, 255));
        pd2.setForeground(new java.awt.Color(0, 51, 51));
        pd2.setText("unpaid");
        pd2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pd2ActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 51, 51));
        jLabel15.setText("Theory demande :");

        td1.setBackground(new java.awt.Color(255, 255, 255));
        td1.setForeground(new java.awt.Color(0, 51, 51));
        td1.setText("paid");
        td1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                td1ActionPerformed(evt);
            }
        });

        td2.setBackground(new java.awt.Color(255, 255, 255));
        td2.setForeground(new java.awt.Color(0, 51, 51));
        td2.setText("unpaid");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(63, 63, 63)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(theo_date, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(td1)
                                        .addComponent(pd1))
                                    .addGap(71, 71, 71)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(pd2)
                                        .addComponent(td2))))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(valid)
                                        .addGap(68, 68, 68)
                                        .addComponent(invalid))
                                    .addComponent(theo_score, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(pratic_location, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(pratic_date, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(97, 97, 97))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addGap(101, 101, 101))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel8))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addComponent(update)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel15)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel11)
                        .addGap(27, 27, 27)
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel10)
                        .addGap(19, 19, 19)
                        .addComponent(jLabel6)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel5))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(td1)
                            .addComponent(td2))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(pd1)
                            .addComponent(pd2))
                        .addGap(18, 18, 18)
                        .addComponent(theo_date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(pratic_date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(pratic_location, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(theo_score, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(valid)
                            .addComponent(invalid))))
                .addGap(31, 31, 31)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(update, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(239, 239, 239))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 481, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 479, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

   
    private void updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateActionPerformed
        String th_date=((JTextField)theo_date.getDateEditor().getUiComponent()).getText();//p2
if(th_date.equals("")){th_date=null;}
int score;
        String thscore = theo_score.getText();if(thscore.equals("")){ score=0;}
        else{score=Integer.parseInt(thscore);}//p3

        
        
         String pr_date=((JTextField)pratic_date.getDateEditor().getUiComponent()).getText();//p5
if(pr_date.equals("")){pr_date=null;}

        String pr_location = pratic_location.getText();if(pr_location.equals("")){pr_location=null;}//p6
        
        
         String td_validation =null;//p8
if (td2.isSelected()) {
            td_validation = "unpaid";}
else if (td1.isSelected()) {
    td_validation = "paid";}


 String pd_validation =null;//p10
if (pd2.isSelected()) {
            pd_validation = "unpaid";}
else if (pd1.isSelected()) {
    pd_validation = "paid";}


        String validation =null;//p7
if (invalid.isSelected()) {
            validation = "invalid";}
else if (valid.isSelected()) {
    validation = "valid";
}
        
if(j==1){
    th_update(td_validation,th_date,score);
    
    
}
else if(j==2){
        pr_update(pd_validation,pr_date,pr_location,validation);

    
}      

    }//GEN-LAST:event_updateActionPerformed

    void th_update(String p1,String p2,int p3){
    
     String update_query="UPDATE `students` SET `th_last_feed`=?,`theory_exam_date`=?,`theory_exam`=?  WHERE studentID=?";
         try {
             
             ps = Connect.ConnectDB().prepareStatement(update_query);
       
         ps.setString(1,p1);
         ps.setString(2,p2);
         ps.setInt(3,p3);
         ps.setString(4, id);
         if (ps.executeUpdate() != 0) {
             JOptionPane.showMessageDialog(null, "the student has been updated");
                            this.setVisible(false);

         } else {
             JOptionPane.showMessageDialog(null, "Error:check your information");
         }
             
         } catch (SQLException ex) {
        Logger.getLogger(repassUpdate.class.getName()).log(Level.SEVERE, null, ex);
    }
         
    
    
    }
       void pr_update(String p1,String p2,String p3,String p4){
    
     String update_query="UPDATE `students` SET `pr_last_feed`=?,`practice_exam_date`=?,`practice_exam_location`=? ,`practice_exam`=?  WHERE studentID=?";
         try {
             
             ps = Connect.ConnectDB().prepareStatement(update_query);
       
         ps.setString(1,p1);
         ps.setString(2,p2);
         ps.setString(3,p3);
         ps.setString(4,p4);
         ps.setString(5, id);
         if (ps.executeUpdate() != 0) {
             JOptionPane.showMessageDialog(null, "the student has been updated");
                                   this.dispose();

         } else {
             JOptionPane.showMessageDialog(null, "Error:check your information");
         }
             
         } catch (SQLException ex) {
        Logger.getLogger(repassUpdate.class.getName()).log(Level.SEVERE, null, ex);
    }
         
    
    
    }
    
    
    
    
    
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
                                   this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void pratic_locationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pratic_locationActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pratic_locationActionPerformed

    private void invalidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_invalidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_invalidActionPerformed

    private void pd2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pd2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pd2ActionPerformed

    private void td1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_td1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_td1ActionPerformed

  
  
   


    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected javax.swing.JRadioButton invalid;
    protected javax.swing.JButton jButton2;
    protected javax.swing.JLabel jLabel10;
    protected javax.swing.JLabel jLabel11;
    protected javax.swing.JLabel jLabel15;
    protected javax.swing.JLabel jLabel5;
    protected javax.swing.JLabel jLabel6;
    protected javax.swing.JLabel jLabel8;
    protected javax.swing.JLabel jLabel9;
    protected javax.swing.JPanel jPanel1;
    protected javax.swing.JPanel jPanel2;
    protected javax.swing.JLabel name;
    protected javax.swing.JRadioButton pd1;
    protected javax.swing.JRadioButton pd2;
    protected com.toedter.calendar.JDateChooser pratic_date;
    protected javax.swing.JTextField pratic_location;
    protected javax.swing.JRadioButton td1;
    protected javax.swing.JRadioButton td2;
    protected com.toedter.calendar.JDateChooser theo_date;
    protected javax.swing.JTextField theo_score;
    protected javax.swing.JButton update;
    protected javax.swing.JRadioButton valid;
    // End of variables declaration//GEN-END:variables
}