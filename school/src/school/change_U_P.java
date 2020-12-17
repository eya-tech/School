
package school;

import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class change_U_P extends javax.swing.JFrame {
String user;
    /**
     * Creates new form change_U_P
     * @param user
     */
    public change_U_P(String user) {
        this.user=user;
        initComponents();
                this.setLocationRelativeTo(null);// center form in the screen

    }

   
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        usernameF = new javax.swing.JTextField();
        passwordF = new javax.swing.JPasswordField();
        jLabel_username = new javax.swing.JLabel();
        jLabel_password = new javax.swing.JLabel();
        jCheckBoxShowPass = new javax.swing.JCheckBox();

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel3.setBackground(new java.awt.Color(0, 128, 128));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setText("OK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setText("Cancel");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        usernameF.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        usernameF.setForeground(new java.awt.Color(153, 153, 153));
        usernameF.setText("username");
        usernameF.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                usernameFFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                usernameFFocusLost(evt);
            }
        });
        usernameF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameFActionPerformed(evt);
            }
        });

        passwordF.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        passwordF.setForeground(new java.awt.Color(153, 153, 153));
        passwordF.setText("password");
        passwordF.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                passwordFFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                passwordFFocusLost(evt);
            }
        });
        passwordF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordFActionPerformed(evt);
            }
        });

        jLabel_username.setBackground(new java.awt.Color(255, 255, 255));
        jLabel_username.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel_username.setForeground(new java.awt.Color(0, 51, 51));
        jLabel_username.setText("Username");

        jLabel_password.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel_password.setForeground(new java.awt.Color(0, 51, 51));
        jLabel_password.setText("Password");

        jCheckBoxShowPass.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBoxShowPass.setForeground(new java.awt.Color(0, 0, 51));
        jCheckBoxShowPass.setText("Show Password");
        jCheckBoxShowPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxShowPassActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jCheckBoxShowPass)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel_username, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel_password, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(usernameF)
                            .addComponent(passwordF))))
                .addGap(82, 82, 82))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 178, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(55, 55, 55)
                .addComponent(jButton2)
                .addGap(115, 115, 115))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_username, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(usernameF, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_password, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(passwordF, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCheckBoxShowPass)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton1))
                .addContainerGap(47, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
                                   this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void usernameFFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_usernameFFocusGained

        //clear the textfield on focus if the text is "username"
        if(usernameF.getText().trim().toLowerCase().equals("username"))
        {
            usernameF.setText("");
            usernameF.setForeground(Color.black);

        }

    }//GEN-LAST:event_usernameFFocusGained

    private void usernameFFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_usernameFFocusLost

        //if the text is equal to username or empty
        //we will set the "username" test in the field
        //on focus lost event
        if (usernameF.getText().trim().equals("")
            || usernameF.getText().trim().toLowerCase().equals("username"))
        {
            usernameF.setText("username");
            usernameF.setForeground(new Color(153,153,153));
        }

    }//GEN-LAST:event_usernameFFocusLost

    private void usernameFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameFActionPerformed

    private void passwordFFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_passwordFFocusGained
        //clear the password on focus if the text is "password"
        //get the pass text
        String pass=String.valueOf(passwordF.getPassword());
        if(pass.trim().toLowerCase().equals("password"))
        {
            passwordF.setText("");
            passwordF.setForeground(Color.black);

        }

    }//GEN-LAST:event_passwordFFocusGained

    private void passwordFFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_passwordFFocusLost
        //if the password is equal to password or empty
        //we will set the "password" test in the field
        //on focus lost event

        //get the pass text
        String pass=String.valueOf(passwordF.getPassword());
        if (pass.trim().equals("")
            || pass.trim().toLowerCase().equals("password"))
        {
            passwordF.setText("password");
            passwordF.setForeground(new Color(153,153,153));
        }

    }//GEN-LAST:event_passwordFFocusLost

    private void passwordFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passwordFActionPerformed

    private void jCheckBoxShowPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxShowPassActionPerformed
        if(jCheckBoxShowPass.isSelected())
        {
            passwordF.setEchoChar((char)0);
        }else
        {
            passwordF.setEchoChar('*');
        }
    }//GEN-LAST:event_jCheckBoxShowPassActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
         Connection con=null;
    PreparedStatement ps=null;
  
    
        String value=usernameF.getText();
        String value1=String.valueOf(passwordF.getPassword());
        
        String sql= "update users set username=?,password=? WHERE userID='" + user + "'";
        
        con=Connect.ConnectDB();

        try {
            
            ps=con.prepareStatement(sql);
            ps.setString(1, value);
            ps.setString(2, value1);
         
            
            ps.executeUpdate();
            
           JOptionPane.showMessageDialog(rootPane, "Updated");
            

        }  catch (SQLException ex) {
            java.util.logging.Logger.getLogger(change_U_P.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
      
        
                                   this.dispose();
        
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JCheckBox jCheckBoxShowPass;
    private javax.swing.JLabel jLabel_password;
    private javax.swing.JLabel jLabel_username;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPasswordField passwordF;
    private javax.swing.JTextField usernameF;
    // End of variables declaration//GEN-END:variables
}
