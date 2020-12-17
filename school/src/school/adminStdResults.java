
package school;

import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class adminStdResults extends javax.swing.JFrame {
    String id,std_id;
ResultSet rs;
JTable table;
 Connection con=null;
    PreparedStatement ps=null,prs,pst;


    public adminStdResults() {
        
            this.id=id;
            initComponents();  
                    this.setLocationRelativeTo(null);// center form in the screen
             showResults();
      
            

            
            
            
    }

    private void showResults(){
              DefaultTableModel model=new DefaultTableModel();
             model.addColumn("ID");
            model.addColumn("full name");
            model.addColumn("result");
            
        String std_query = "SELECT  studentID, first_name , last_name,practice_exam FROM students ";
            try {
            ps=Connect.ConnectDB().prepareStatement(std_query);
            ResultSet resltat =ps.executeQuery();
            while (resltat.next()){
                String name=resltat.getString("first_name")+" "+resltat.getString("last_name");
                if(check(resltat.getString("practice_exam"))){
                String result=result(resltat.getString("practice_exam"));
                 model.addRow(new Object []{ resltat.getString("studentID"),name,result});
                }
                }

  
        } catch (SQLException ex) {
            Logger.getLogger(adminStdResults.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            
            
            table= new JTable(model){
                @Override
                public boolean isCellEditable(int row, int column) {               
                    return false;
                }
               
            };
            
            jScrollPane.setViewportView(table);
            
            
             
    }
@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        username = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        user = new javax.swing.JLabel();
        jScrollPane = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        logout = new javax.swing.JLabel();
        rest_p = new javax.swing.JPanel();
        result = new javax.swing.JLabel();
        reg_std_p = new javax.swing.JPanel();
        reg_std = new javax.swing.JLabel();
        reg_tch_p = new javax.swing.JPanel();
        reg_tch = new javax.swing.JLabel();
        std_p = new javax.swing.JPanel();
        std = new javax.swing.JLabel();
        tch_p = new javax.swing.JPanel();
        tch = new javax.swing.JLabel();
        cal_p = new javax.swing.JPanel();
        cal = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("admin");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(0, 128, 128));
        jPanel3.setPreferredSize(new java.awt.Dimension(953, 59));

        username.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        username.setForeground(new java.awt.Color(255, 255, 255));
        username.setText("Admin");

        user.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/edit_profile.png"))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(user, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(username, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(299, 299, 299)
                .addComponent(jLabel3)
                .addContainerGap(439, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(username, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(user, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
        );

        jPanel1.setBackground(new java.awt.Color(0, 128, 128));
        jPanel1.setForeground(new java.awt.Color(204, 204, 204));

        logout.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        logout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logout.png"))); // NOI18N
        logout.setText("Log out");
        logout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logoutMouseClicked(evt);
            }
        });

        rest_p.setBackground(new java.awt.Color(0, 102, 102));

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

        reg_std_p.setBackground(new java.awt.Color(0, 128, 128));

        reg_std.setBackground(new java.awt.Color(153, 0, 51));
        reg_std.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        reg_std.setForeground(new java.awt.Color(204, 204, 204));
        reg_std.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/report.png"))); // NOI18N
        reg_std.setText("Registred Students list");
        reg_std.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                reg_stdMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                reg_std_mouseEntred(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                reg_std_mouseExited(evt);
            }
        });

        javax.swing.GroupLayout reg_std_pLayout = new javax.swing.GroupLayout(reg_std_p);
        reg_std_p.setLayout(reg_std_pLayout);
        reg_std_pLayout.setHorizontalGroup(
            reg_std_pLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(reg_std_pLayout.createSequentialGroup()
                .addComponent(reg_std, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        reg_std_pLayout.setVerticalGroup(
            reg_std_pLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, reg_std_pLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(reg_std, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        reg_tch_p.setBackground(new java.awt.Color(0, 128, 128));

        reg_tch.setBackground(new java.awt.Color(0, 51, 102));
        reg_tch.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        reg_tch.setForeground(new java.awt.Color(204, 204, 204));
        reg_tch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/report.png"))); // NOI18N
        reg_tch.setText("Registred teachers list");
        reg_tch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                reg_tchMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                reg_tchMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                reg_tchMouseExited(evt);
            }
        });

        javax.swing.GroupLayout reg_tch_pLayout = new javax.swing.GroupLayout(reg_tch_p);
        reg_tch_p.setLayout(reg_tch_pLayout);
        reg_tch_pLayout.setHorizontalGroup(
            reg_tch_pLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(reg_tch_pLayout.createSequentialGroup()
                .addComponent(reg_tch, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        reg_tch_pLayout.setVerticalGroup(
            reg_tch_pLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(reg_tch, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
        );

        std_p.setBackground(new java.awt.Color(0, 128, 128));

        std.setBackground(new java.awt.Color(153, 0, 51));
        std.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        std.setForeground(new java.awt.Color(204, 204, 204));
        std.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/report.png"))); // NOI18N
        std.setText("Students list ");
        std.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                stdMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                stdMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                stdMouseExited(evt);
            }
        });

        javax.swing.GroupLayout std_pLayout = new javax.swing.GroupLayout(std_p);
        std_p.setLayout(std_pLayout);
        std_pLayout.setHorizontalGroup(
            std_pLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(std_pLayout.createSequentialGroup()
                .addComponent(std, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        std_pLayout.setVerticalGroup(
            std_pLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, std_pLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(std, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        tch_p.setBackground(new java.awt.Color(0, 128, 128));

        tch.setBackground(new java.awt.Color(0, 51, 102));
        tch.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tch.setForeground(new java.awt.Color(204, 204, 204));
        tch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/report.png"))); // NOI18N
        tch.setText("Teachers list");
        tch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tchMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tchMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tchMouseExited(evt);
            }
        });

        javax.swing.GroupLayout tch_pLayout = new javax.swing.GroupLayout(tch_p);
        tch_p.setLayout(tch_pLayout);
        tch_pLayout.setHorizontalGroup(
            tch_pLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tch_pLayout.createSequentialGroup()
                .addComponent(tch, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        tch_pLayout.setVerticalGroup(
            tch_pLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tch, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        cal_p.setBackground(new java.awt.Color(0, 128, 128));

        cal.setBackground(new java.awt.Color(0, 51, 102));
        cal.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        cal.setForeground(new java.awt.Color(204, 204, 204));
        cal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/report.png"))); // NOI18N
        cal.setText("Calendar");
        cal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                calMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                calMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                calMouseExited(evt);
            }
        });

        javax.swing.GroupLayout cal_pLayout = new javax.swing.GroupLayout(cal_p);
        cal_p.setLayout(cal_pLayout);
        cal_pLayout.setHorizontalGroup(
            cal_pLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cal_pLayout.createSequentialGroup()
                .addComponent(cal, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 50, Short.MAX_VALUE))
        );
        cal_pLayout.setVerticalGroup(
            cal_pLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Dashboard");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(rest_p, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(tch_p, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(std_p, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(reg_tch_p, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(reg_std_p, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(cal_p, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(logout, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65)
                .addComponent(reg_std_p, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(reg_tch_p, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(std_p, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(tch_p, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(cal_p, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(rest_p, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(logout, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11))
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
                        .addComponent(jScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(1, 1, 1))
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

   
    
    @SuppressWarnings("null")
   String result(String exam){
       if(exam.equals("valid")){
      return "succeeded";
  }else {
      return "failed";
  }  
  
  

   }   
    boolean check(String exam){
    if(exam==null){return false;}
    else return true;
    }
  
       
    private void logoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutMouseClicked
        // TODO add your handling code here:
        this.setVisible(false);
        new LoginForm().setVisible(true);
    }//GEN-LAST:event_logoutMouseClicked

    private void resultMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_resultMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_resultMouseClicked

    private void resultMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_resultMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_resultMouseEntered

    private void resultMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_resultMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_resultMouseExited

    private void reg_stdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reg_stdMouseClicked
        // TODO add your handling code here:
                                   this.dispose();
        new admin_dashboard().setVisible(true);
    }//GEN-LAST:event_reg_stdMouseClicked

    private void reg_std_mouseEntred(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reg_std_mouseEntred
        // TODO add your handling code here:
        reg_std_p.setBackground(new Color(0,102,102));
    }//GEN-LAST:event_reg_std_mouseEntred

    private void reg_std_mouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reg_std_mouseExited
        // TODO add your handling code here:
        reg_std_p.setBackground(new Color(0,102,102));
    }//GEN-LAST:event_reg_std_mouseExited

    private void reg_tchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reg_tchMouseClicked
        // TODO add your handling code here:
                                   this.dispose();
        new registred_teacherList().setVisible(true);
    }//GEN-LAST:event_reg_tchMouseClicked

    private void reg_tchMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reg_tchMouseEntered
        // TODO add your handling code here:
        reg_tch_p.setBackground(new Color(0,102,102));
    }//GEN-LAST:event_reg_tchMouseEntered

    private void reg_tchMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reg_tchMouseExited
        // TODO add your handling code here:
        reg_tch_p.setBackground(new Color(0,128,128));
    }//GEN-LAST:event_reg_tchMouseExited

    private void stdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_stdMouseClicked
        // TODO add your handling code here:
                                   this.dispose();
        new studentList().setVisible(true);
    }//GEN-LAST:event_stdMouseClicked

    private void stdMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_stdMouseEntered
        // TODO add your handling code here:
        std_p.setBackground(new Color(0,102,102));
    }//GEN-LAST:event_stdMouseEntered

    private void stdMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_stdMouseExited
        // TODO add your handling code here:
        std_p.setBackground(new Color(0,128,128));
    }//GEN-LAST:event_stdMouseExited

    private void tchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tchMouseClicked
        // TODO add your handling code here:
                                   this.dispose();
        new teacherList().setVisible(true);
    }//GEN-LAST:event_tchMouseClicked

    private void tchMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tchMouseEntered
        // TODO add your handling code here:
        tch_p.setBackground(new Color(0,102,102));
    }//GEN-LAST:event_tchMouseEntered

    private void tchMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tchMouseExited
        // TODO add your handling code here:
        tch_p.setBackground(new Color(0,128,128));
    }//GEN-LAST:event_tchMouseExited

    private void calMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_calMouseClicked
        // TODO add your handling code here:
                                   this.dispose();
        new adminCalendar().setVisible(true);
    }//GEN-LAST:event_calMouseClicked

    private void calMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_calMouseEntered
        // TODO add your handling code here:
        cal_p.setBackground(new Color(0,102,102));
    }//GEN-LAST:event_calMouseEntered

    private void calMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_calMouseExited
        // TODO add your handling code here:
        cal_p.setBackground(new Color(0,128,128));
    }//GEN-LAST:event_calMouseExited

   
  
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel cal;
    private javax.swing.JPanel cal_p;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JLabel logout;
    private javax.swing.JLabel reg_std;
    private javax.swing.JPanel reg_std_p;
    private javax.swing.JLabel reg_tch;
    private javax.swing.JPanel reg_tch_p;
    private javax.swing.JPanel rest_p;
    private javax.swing.JLabel result;
    private javax.swing.JLabel std;
    private javax.swing.JPanel std_p;
    private javax.swing.JLabel tch;
    private javax.swing.JPanel tch_p;
    private javax.swing.JLabel user;
    private javax.swing.JLabel username;
    // End of variables declaration//GEN-END:variables
}
