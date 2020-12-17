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



public class updateStudent extends javax.swing.JFrame {
String id;
PreparedStatement pst,ps,ps1;
ResultSet rs;
String lic=null;
String Olic=null;

   
    public updateStudent(String id)  {
 
        this.id=id;
        initComponents();
                this.setLocationRelativeTo(null);// center form in the screen
        ButtonGroup bg0 = new ButtonGroup();
        bg0.add(td1);
        bg0.add(td2);
        
          ButtonGroup bg1 = new ButtonGroup();
        bg1.add(tc1);
        bg1.add(tc2);
        
          ButtonGroup bg2 = new ButtonGroup();
        bg2.add(pd1);
        bg2.add(pd2);
        
          ButtonGroup bg3 = new ButtonGroup();
        bg3.add(pc1);
        bg3.add(pc2);
        
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
                              lic=resltat.getString("licence");   
    //resltat.getString("theory_course"),
     name.setText(resltat.getString("first_name")+" "+resltat.getString("last_name"));
     if(resltat.getString("completed_thCrs_hrs")==null){
     CrsHrs.setValue(0);}
     else{      CrsHrs.setValue(Integer.parseInt(resltat.getString("completed_thCrs_hrs")));}
   
     
          theo_score.setText(Integer.toString(resltat.getInt("theory_exam")));
          tchID.setText(resltat.getString("teacherID"));
        if(resltat.getString("teacherID")!=null ){
            tchID.setEditable(false);    
        }
        teachers(resltat.getString("licence"));
        
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
        
          if(resltat.getString("theory_demande")==null){
               
        td2.setSelected(false);
        td1.setSelected(false);
        }
        else if(resltat.getString("theory_demande").equals("unpaid") ){
                  td2.setSelected(true);
      }
      else if(resltat.getString("theory_demande").equals("paid") ){
                  td1.setSelected(true);
      }
          
           if(resltat.getString("practice_demande")==null){
               
        pd2.setSelected(false);
        pd1.setSelected(false);
        }
        else if(resltat.getString("practice_demande").equals("unpaid") ){
                  pd2.setSelected(true);
      }
      else if(resltat.getString("practice_demande").equals("paid") ){
                  pd1.setSelected(true);
      }
          
           if(resltat.getString("th_course_payment")==null){
               
        tc2.setSelected(false);
        tc1.setSelected(false);
        }
        else if(resltat.getString("th_course_payment").equals("unpaid") ){
                  tc2.setSelected(true);
      }
      else if(resltat.getString("th_course_payment").equals("paid") ){
                  tc1.setSelected(true);
      }
           
            if(resltat.getString("pr_course_payment")==null){
               
        pc2.setSelected(false);
        pc1.setSelected(false);
        }
        else if(resltat.getString("pr_course_payment").equals("unpaid") ){
                  pc2.setSelected(true);
      }
      else if(resltat.getString("pr_course_payment").equals("paid") ){
                  pc1.setSelected(true);
      }
         
      java.util.Date date ,date1;
      if(resltat.getString("theory_exam_date")==null ){
      date = null;}
      else{date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(resltat.getString("theory_exam_date")); }
theo_date.setDate(date);
if(resltat.getString("practice_exam_date")==null){
      date1 = null;}
else {date1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(resltat.getString("practice_exam_date")); }
pratic_date.setDate(date1);
               }
        }catch(SQLException ex) {
            java.util.logging.Logger.getLogger(updateStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
      
        } catch (ParseException ex) {
        Logger.getLogger(updateStudent.class.getName()).log(Level.SEVERE, null, ex);
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
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        theo_date = new com.toedter.calendar.JDateChooser();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        theo_score = new javax.swing.JTextField();
        valid = new javax.swing.JRadioButton();
        invalid = new javax.swing.JRadioButton();
        pratic_location = new javax.swing.JTextField();
        teacherId = new javax.swing.JComboBox<String>();
        pratic_date = new com.toedter.calendar.JDateChooser();
        jPanel1 = new javax.swing.JPanel();
        name = new javax.swing.JLabel();
        tchID = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        CrsHrs = new javax.swing.JSpinner();
        jLabel9 = new javax.swing.JLabel();
        pd1 = new javax.swing.JRadioButton();
        pd2 = new javax.swing.JRadioButton();
        jLabel14 = new javax.swing.JLabel();
        tc1 = new javax.swing.JRadioButton();
        tc2 = new javax.swing.JRadioButton();
        jLabel15 = new javax.swing.JLabel();
        td1 = new javax.swing.JRadioButton();
        td2 = new javax.swing.JRadioButton();
        jLabel13 = new javax.swing.JLabel();
        pc1 = new javax.swing.JRadioButton();
        pc2 = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 51));
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
        jLabel6.setForeground(new java.awt.Color(0, 0, 51));
        jLabel6.setText("Theory exam score:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 51));
        jLabel7.setText("Teacher ID:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 51));
        jLabel8.setText("Practice exam date:");

        theo_date.setBackground(new java.awt.Color(108, 122, 137));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 51));
        jLabel10.setText("Practice exam location:");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 51));
        jLabel11.setText("Theory exam date:");

        theo_score.setBackground(new java.awt.Color(236, 239, 243));
        theo_score.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        theo_score.setForeground(new java.awt.Color(0, 0, 51));
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

        pratic_location.setBackground(new java.awt.Color(236, 239, 243));
        pratic_location.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        pratic_location.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pratic_locationActionPerformed(evt);
            }
        });

        teacherId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                teacherIdActionPerformed(evt);
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

        tchID.setBackground(new java.awt.Color(236, 239, 243));
        tchID.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 51));
        jLabel12.setText("Completed theory course hours:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 51));
        jLabel9.setText("Practice demande :");

        pd1.setBackground(new java.awt.Color(255, 255, 255));
        pd1.setForeground(new java.awt.Color(0, 51, 51));
        pd1.setText("paid");

        pd2.setBackground(new java.awt.Color(255, 255, 255));
        pd2.setForeground(new java.awt.Color(0, 51, 51));
        pd2.setText("unpaid");

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 0, 51));
        jLabel14.setText("Theory course payment :");

        tc1.setBackground(new java.awt.Color(255, 255, 255));
        tc1.setForeground(new java.awt.Color(0, 51, 51));
        tc1.setText("paid");

        tc2.setBackground(new java.awt.Color(255, 255, 255));
        tc2.setForeground(new java.awt.Color(0, 51, 51));
        tc2.setText("unpaid");

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 0, 51));
        jLabel15.setText("Theory demande :");

        td1.setBackground(new java.awt.Color(255, 255, 255));
        td1.setForeground(new java.awt.Color(0, 51, 51));
        td1.setText("paid");

        td2.setBackground(new java.awt.Color(255, 255, 255));
        td2.setForeground(new java.awt.Color(0, 51, 51));
        td2.setText("unpaid");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 51));
        jLabel13.setText("Practice course payment :");

        pc1.setBackground(new java.awt.Color(255, 255, 255));
        pc1.setForeground(new java.awt.Color(0, 51, 51));
        pc1.setText("paid");

        pc2.setBackground(new java.awt.Color(255, 255, 255));
        pc2.setForeground(new java.awt.Color(0, 51, 51));
        pc2.setText("unpaid");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(theo_date, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(45, 45, 45))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(jLabel12))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jLabel11))
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addGap(18, 18, 18))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(23, 23, 23)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(pc1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(pc2)
                                .addGap(29, 29, 29))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(td1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                                        .addComponent(td2))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(tc1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(tc2)))
                                .addGap(39, 39, 39))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(pd1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(pd2)
                                .addGap(29, 29, 29))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(theo_score, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(CrsHrs, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(update)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel10)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel8)
                                .addComponent(jLabel5)))
                        .addGap(18, 76, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(valid)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(invalid)
                                .addGap(29, 29, 29))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                            .addComponent(teacherId, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(tchID, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(pratic_date, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(pratic_location, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jButton2))
                                .addContainerGap())))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(td1)
                    .addComponent(td2))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(tc2)
                    .addComponent(tc1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(theo_date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CrsHrs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(theo_score, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(pd1)
                    .addComponent(pd2))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(pc1)
                    .addComponent(pc2))
                .addGap(32, 32, 32)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(teacherId, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(tchID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(pratic_date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pratic_location, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(invalid)
                    .addComponent(valid)
                    .addComponent(jLabel5))
                .addGap(41, 41, 41)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(update, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

   
    private void updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateActionPerformed
         int hrs=(Integer) CrsHrs.getValue();//p1
        String th_date=((JTextField)theo_date.getDateEditor().getUiComponent()).getText();//p2
if(th_date.equals("")){th_date=null;}
int score;
        String thscore = theo_score.getText();if(thscore.equals("")){ score=0;}
        else{score=Integer.parseInt(thscore);}//p3

        
        
        String tchId=tchID.getText();if(tchId.equals("")||tchId.equals("teachers list") ){tchId=null;} //p4
         String pr_date=((JTextField)pratic_date.getDateEditor().getUiComponent()).getText();//p5
if(pr_date.equals("")){pr_date=null;}

        String pr_location = pratic_location.getText();if(pr_location.equals("")){pr_location=null;}//p6
        
        
         String td_validation =null;//p8
if (td2.isSelected()) {
            td_validation = "unpaid";}
else if (td1.isSelected()) {
    td_validation = "paid";}

 String tc_validation =null;//p9
if (tc2.isSelected()) {
            tc_validation = "unpaid";}
else if (tc1.isSelected()) {
    tc_validation = "paid";}


 String pd_validation =null;//p10
if (pd2.isSelected()) {
            pd_validation = "unpaid";}
else if (pd1.isSelected()) {
    pd_validation = "paid";}

 String pc_validation =null;//p11
if (pc2.isSelected()) {
            pc_validation = "unpaid";}
else if (pc1.isSelected()) {
    pc_validation = "paid";}

 
        String validation =null;//p7
if (invalid.isSelected()) {
            validation = "invalid";}
else if (valid.isSelected()) {
    validation = "valid";
Olic=lic;}
        
         String update_query="UPDATE `students` SET `completed_thCrs_hrs`=?,`theory_exam_date`=?,`theory_exam`=?,`teacherID`=?,`practice_exam_date`=?,`practice_exam_location`=?"
        + ",`practice_exam`=?, `obtainedLicence`=?  ,`theory_demande`=?,  `th_course_payment`=?,  `practice_demande`=?,  `pr_course_payment`=?   WHERE studentID=?";
         try {
             if(verify(hrs,th_date,score,tchId,pr_date,pr_location,validation,td_validation,tc_validation,pd_validation,pc_validation)){
             ps = Connect.ConnectDB().prepareStatement(update_query);
         ps.setInt(1,hrs); 
         ps.setString(2,th_date);
         ps.setInt(3,score);
         ps.setString(4,tchId);
         ps.setString(5,pr_date);
         ps.setString(6,pr_location);
         ps.setString(7,validation );
         ps.setString(8,Olic);
         ps.setString(9,td_validation );
         ps.setString(10,tc_validation );
         ps.setString(11,pd_validation );
         ps.setString(12,pc_validation );

         ps.setString(13, id);
         if (ps.executeUpdate() != 0) {
             JOptionPane.showMessageDialog(null, "the student has been updated");
               this.setVisible(false);
         } else {
             JOptionPane.showMessageDialog(null, "Error:check your information");
         }
             }
         } catch (SQLException ex) {
        Logger.getLogger(updateStudent.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    }//GEN-LAST:event_updateActionPerformed

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

    private void teacherIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_teacherIdActionPerformed
        // TODO add your handling code here:
        
        if(!"teachers list".equals((String) teacherId.getSelectedItem())){
tchID.setText((String) teacherId.getSelectedItem());}
     
    }//GEN-LAST:event_teacherIdActionPerformed

  void teachers(String lic){
              String sql="select firstname , teacherID from teachers where licence='"+lic+"'";
        teacherId.addItem("teachers list");

    try {
        pst=Connect.ConnectDB().prepareStatement(sql);
        ResultSet res =pst.executeQuery();
        while(res.next()){
        teacherId.addItem(res.getString("teacherID"));
        }
        
    } catch (SQLException ex) {
        Logger.getLogger(updateStudent.class.getName()).log(Level.SEVERE, null, ex);
    }
  
  }  
  
        boolean verify(int p1, String p2,int p3 , String p4,String p5,String p6,String p7,String p8,String p9,String p10,String p11)    
        {
            
            
    try {
        String query = "SELECT * FROM students where studentID='"+id+"'";
        
        ps1=Connect.ConnectDB().prepareStatement(query);
        ResultSet reslt =ps.executeQuery();
        while (reslt.next()){
            if(p9!= null && !p8.equals("paid") ){
            JOptionPane.showMessageDialog(null,"the theory course can't be payed without paying the demande!",
      "Error!", JOptionPane.ERROR_MESSAGE);
            return false;}
        else if(p2!= null &&  !p8.equals("paid") ){
            JOptionPane.showMessageDialog(null,"the theory exam date can't be set without paying the demande!",
      "Error!", JOptionPane.ERROR_MESSAGE);
            return false;}
       else if(reslt.getString("theory_course").equals("alone") && p1!=0 ){
  JOptionPane.showMessageDialog(null, "you are not allowed to modify the theory hours , this student has a home schooling !",
      "Error!", JOptionPane.ERROR_MESSAGE);           
        return false;}
       
       else if(reslt.getString("payment").equals("contract") && p1!=0 ){
  JOptionPane.showMessageDialog(null, "you are not allowed to modify the theory hours , this student has a contract !",
      "Error!", JOptionPane.ERROR_MESSAGE);           
        return false;}
       
        else if(reslt.getString("theory_course").equals("school") &&reslt.getString("payment").equals("per hour")&& p1==0 && p3!=0 ){
            JOptionPane.showMessageDialog(null," this student didn't complet the theory course!",
      "Error!", JOptionPane.ERROR_MESSAGE);
            return false;}
 
        else if(p1!= 0 && reslt.getInt("theory_course_hrs")< p1 ){
            JOptionPane.showMessageDialog(null,"theory course hours mustn't be greater than "+reslt.getString("theory_course_hrs")+ "!",
      "Error!", JOptionPane.ERROR_MESSAGE);
            return false;}
        
     else if(p3!= 0 && ( p2==null ||  !p8.equals("paid") ||  !p9.equals("paid") ||Integer.parseInt(reslt.getString("theory_course_hrs"))!= p1 )){
            JOptionPane.showMessageDialog(null,"you are not allowed to set the score yet !",
      "Error!", JOptionPane.ERROR_MESSAGE);
            return false;}     
        else if(p3!= 0 && p3>30 ){
            JOptionPane.showMessageDialog(null,"the score out of range  !",
      "Error!", JOptionPane.ERROR_MESSAGE);
            return false;}
        
         else if(p3<24 &&(p10!= null || p4!= null || p11!= null || p5!= null ||p6!=null||p7!= null )){
            JOptionPane.showMessageDialog(null,"you're not allowed to set the practice part yet !",
      "Error!", JOptionPane.ERROR_MESSAGE);
            return false;}
                 if(p3>=24 && p11==null && p10==null && p5==null && p6==null&& p7==null && p4==null  ){
                     return true;
                 }
            
        if(p3>=24 && p2!=null &&  p8.equals("paid") && p9.equals("paid") && Integer.parseInt(reslt.getString("theory_course_hrs"))== p1){
      
           if(p11.equals("paid") && !p10.equals("paid")  ){
            JOptionPane.showMessageDialog(null,"the demande must be payed before the course!",
      "Error!", JOptionPane.ERROR_MESSAGE);
            return false;}
             else if(p4!= null && !p10.equals("paid")  ){
            JOptionPane.showMessageDialog(null,"the teacher can't be set without paying the demande !",
      "Error!", JOptionPane.ERROR_MESSAGE);
            return false;}
        else if(p5!= null && !p10.equals("paid")  ){
            JOptionPane.showMessageDialog(null,"the practice exam date can't be set without paying the demande !",
      "Error!", JOptionPane.ERROR_MESSAGE);
            return false;}
        
         else if(p7!= null && (p6== null || p5==null) ){
            JOptionPane.showMessageDialog(null," you are not allowed to set the validation yet!",
      "Error!", JOptionPane.ERROR_MESSAGE);
            return false;}
          else if(p7!= null && !p11.equals("paid") ){
            JOptionPane.showMessageDialog(null," you are not allowed to set the validation yet!",
      "Error!", JOptionPane.ERROR_MESSAGE);
            return false;}
       
        }
        
        }
        
       
    } catch (SQLException ex) {
        Logger.getLogger(updateStudent.class.getName()).log(Level.SEVERE, null, ex);
    }
                    return true;

        }    
   
 


    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected javax.swing.JSpinner CrsHrs;
    protected javax.swing.JRadioButton invalid;
    protected javax.swing.JButton jButton2;
    protected javax.swing.JLabel jLabel10;
    protected javax.swing.JLabel jLabel11;
    protected javax.swing.JLabel jLabel12;
    protected javax.swing.JLabel jLabel13;
    protected javax.swing.JLabel jLabel14;
    protected javax.swing.JLabel jLabel15;
    protected javax.swing.JLabel jLabel5;
    protected javax.swing.JLabel jLabel6;
    protected javax.swing.JLabel jLabel7;
    protected javax.swing.JLabel jLabel8;
    protected javax.swing.JLabel jLabel9;
    protected javax.swing.JPanel jPanel1;
    protected javax.swing.JPanel jPanel2;
    protected javax.swing.JLabel name;
    protected javax.swing.JRadioButton pc1;
    protected javax.swing.JRadioButton pc2;
    protected javax.swing.JRadioButton pd1;
    protected javax.swing.JRadioButton pd2;
    protected com.toedter.calendar.JDateChooser pratic_date;
    protected javax.swing.JTextField pratic_location;
    protected javax.swing.JRadioButton tc1;
    protected javax.swing.JRadioButton tc2;
    protected javax.swing.JTextField tchID;
    protected javax.swing.JRadioButton td1;
    protected javax.swing.JRadioButton td2;
    protected javax.swing.JComboBox<String> teacherId;
    protected com.toedter.calendar.JDateChooser theo_date;
    protected javax.swing.JTextField theo_score;
    protected javax.swing.JButton update;
    protected javax.swing.JRadioButton valid;
    // End of variables declaration//GEN-END:variables
}
