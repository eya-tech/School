
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



public class adminCalendar extends javax.swing.JFrame {
    String std_id;
ResultSet rs;
JTable table;
 Connection con=null;
    PreparedStatement ps=null,prs,pst;
String selected_date ;
String theo_date,theo_time,crs_date,crs_time,exam_date,exam_time;
   DateFormat  dateFormat;

    public adminCalendar() {
        
            initComponents();
                    this.setLocationRelativeTo(null);// center form in the screen

     
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
            int[] count =new int[]{0,0,0,0,0,0,0,0,0,0,0};
            String[][] names=new String[11][11];
            for(int i=0;i<11;i++){for(int j=0;j<11;j++){ names[i][j]=""; }  }
            
            int rows=1;
        String std_query = "SELECT  first_name , last_name,theory_exam_date,next_crs_date,practice_exam_date,practice_exam_location FROM students ";
        try {
            ps=Connect.ConnectDB().prepareStatement(std_query);
            ResultSet resltat =ps.executeQuery();
            while (resltat.next()){
                
                
           if(resltat.getString("theory_exam_date")!=null){
                String[] str = resltat.getString("theory_exam_date").split(" ");
            theo_date=str[0];
            theo_time=str[1];
            
            
            if(date.equals(theo_date)){
            names[count[index(theo_time)]][index(theo_time)]="theory exam of "+resltat.getString("first_name")+" "+resltat.getString("last_name");

                         if(count[index(theo_time)]==0 ){
                                model.setValueAt("Th exam",0,index(theo_time));
                                count[index(theo_time)]++;
                                
                }else{
                    if(count[index(theo_time)]<rows){
                     model.setValueAt("Th exam",count[index(theo_time)],index(theo_time));
                                count[index(theo_time)]++;
                    }else if(count[index(theo_time)]==rows){
                       model.addRow(new Object []{ "","","","","","","","","","",""});
                     model.setValueAt("Th exam",count[index(theo_time)],index(theo_time));
                                count[index(theo_time)]++;
                                rows++;
                    }               
                }    
           }

            }
                
                
                
                
                
            if(resltat.getString("next_crs_date")!=null){
                String[] parts = resltat.getString("next_crs_date").split(" ");
            crs_date=parts[0];
            crs_time=parts[1];
            
             if(date.equals(crs_date)){
                        names[count[index(crs_time)]][index(crs_time)]="practice course of "+resltat.getString("first_name")+" "+resltat.getString("last_name");

                if(count[index(crs_time)]==0 ){
                                model.setValueAt("course",0,index(crs_time));
                                count[index(crs_time)]++;
                                
                }else{
                    if(count[index(crs_time)]<rows){
                     model.setValueAt("P course",count[index(crs_time)],index(crs_time));
                                count[index(crs_time)]++;
                    }else if(count[index(crs_time)]==rows){
                       model.addRow(new Object []{ "","","","","","","","","","",""});
                     model.setValueAt("P course",count[index(crs_time)],index(crs_time));
                                count[index(crs_time)]++;
                                rows++;
                    }                }
            }
 
            }
            
            
            
        if(resltat.getString("practice_exam_date")!=null){

               String[] part = resltat.getString("practice_exam_date").split(" ");
            exam_date=part[0];
            exam_time=part[1];
            
            if(date.equals(exam_date)){
              names[count[index(exam_time)]][index(exam_time)]="practice exam of "+resltat.getString("first_name")+" "+resltat.getString("last_name")+"\n at "+resltat.getString("practice_exam_location");

                  if(count[index(exam_time)]==0 ){
                                model.setValueAt("P exam",0,index(exam_time));
                                count[index(exam_time)]++;
                                
                }else{
                    if(count[index(exam_time)]<rows){
                     model.setValueAt("P exam",count[index(exam_time)],index(exam_time));
                                count[index(exam_time)]++;
                    }else if(count[index(exam_time)]==rows){
                       model.addRow(new Object []{ "","","","","","","","","","",""});
                     model.setValueAt("P exam",count[index(exam_time)],index(exam_time));
                                count[index(exam_time)]++;
                                rows++;
                    }              
                }
                       
            }
                        }
           
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(adminCalendar.class.getName()).log(Level.SEVERE, null, ex);
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
                String Value = (String) table.getValueAt(table.getSelectedRow(), table.getSelectedColumn());
               if(!Value.equals("")){
 JOptionPane.showMessageDialog(null,names[table.getSelectedRow()][table.getSelectedColumn()]);
 }else{
 JOptionPane.showMessageDialog(null," calendar is free at this time ");

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
        user = new javax.swing.JLabel();
        jScrollPane = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        logout = new javax.swing.JLabel();
        reg_std_p = new javax.swing.JPanel();
        reg_std = new javax.swing.JLabel();
        reg_tch_p = new javax.swing.JPanel();
        reg_tch = new javax.swing.JLabel();
        std_p = new javax.swing.JPanel();
        std = new javax.swing.JLabel();
        tch_p = new javax.swing.JPanel();
        tch = new javax.swing.JLabel();
        calndr_p = new javax.swing.JPanel();
        calender = new javax.swing.JLabel();
        res_p = new javax.swing.JPanel();
        res = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        std_p1 = new javax.swing.JPanel();
        std1 = new javax.swing.JLabel();
        reg_std_p1 = new javax.swing.JPanel();
        reg_std1 = new javax.swing.JLabel();
        reg_tch_p1 = new javax.swing.JPanel();
        reg_tch1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tch_p1 = new javax.swing.JPanel();
        tch1 = new javax.swing.JLabel();
        res_p1 = new javax.swing.JPanel();
        res1 = new javax.swing.JLabel();
        cal_p = new javax.swing.JPanel();
        cal1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cal = new com.toedter.calendar.JCalendar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("admin");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(0, 128, 128));

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
                .addComponent(username, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(437, 437, 437)
                .addComponent(jLabel3)
                .addContainerGap(279, Short.MAX_VALUE))
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

        calndr_p.setBackground(new java.awt.Color(0, 102, 102));

        calender.setBackground(new java.awt.Color(0, 51, 102));
        calender.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        calender.setForeground(new java.awt.Color(204, 204, 204));
        calender.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/report.png"))); // NOI18N
        calender.setText("Calendar");

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

        res_p.setBackground(new java.awt.Color(0, 128, 128));

        res.setBackground(new java.awt.Color(0, 51, 102));
        res.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        res.setForeground(new java.awt.Color(204, 204, 204));
        res.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/report.png"))); // NOI18N
        res.setText("Results");
        res.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                resMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                resMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                resMouseExited(evt);
            }
        });

        javax.swing.GroupLayout res_pLayout = new javax.swing.GroupLayout(res_p);
        res_p.setLayout(res_pLayout);
        res_pLayout.setHorizontalGroup(
            res_pLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(res_pLayout.createSequentialGroup()
                .addComponent(res, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        res_pLayout.setVerticalGroup(
            res_pLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(res, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
        );

        jPanel5.setBackground(new java.awt.Color(0, 128, 128));
        jPanel5.setForeground(new java.awt.Color(204, 204, 204));

        std_p1.setBackground(new java.awt.Color(0, 128, 128));

        std1.setBackground(new java.awt.Color(153, 0, 51));
        std1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        std1.setForeground(new java.awt.Color(204, 204, 204));
        std1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/report.png"))); // NOI18N
        std1.setText("Students list ");
        std1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                std1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                std1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                std1MouseExited(evt);
            }
        });

        javax.swing.GroupLayout std_p1Layout = new javax.swing.GroupLayout(std_p1);
        std_p1.setLayout(std_p1Layout);
        std_p1Layout.setHorizontalGroup(
            std_p1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(std_p1Layout.createSequentialGroup()
                .addComponent(std1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        std_p1Layout.setVerticalGroup(
            std_p1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, std_p1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(std1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        reg_std_p1.setBackground(new java.awt.Color(0, 102, 102));

        reg_std1.setBackground(new java.awt.Color(153, 0, 51));
        reg_std1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        reg_std1.setForeground(new java.awt.Color(204, 204, 204));
        reg_std1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/report.png"))); // NOI18N
        reg_std1.setText("registred Students list");

        javax.swing.GroupLayout reg_std_p1Layout = new javax.swing.GroupLayout(reg_std_p1);
        reg_std_p1.setLayout(reg_std_p1Layout);
        reg_std_p1Layout.setHorizontalGroup(
            reg_std_p1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, reg_std_p1Layout.createSequentialGroup()
                .addComponent(reg_std1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        reg_std_p1Layout.setVerticalGroup(
            reg_std_p1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, reg_std_p1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(reg_std1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        reg_tch_p1.setBackground(new java.awt.Color(0, 128, 128));

        reg_tch1.setBackground(new java.awt.Color(0, 51, 102));
        reg_tch1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        reg_tch1.setForeground(new java.awt.Color(204, 204, 204));
        reg_tch1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/report.png"))); // NOI18N
        reg_tch1.setText("registred teachers list");
        reg_tch1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                reg_tch1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                reg_tch1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                reg_tch1MouseExited(evt);
            }
        });

        javax.swing.GroupLayout reg_tch_p1Layout = new javax.swing.GroupLayout(reg_tch_p1);
        reg_tch_p1.setLayout(reg_tch_p1Layout);
        reg_tch_p1Layout.setHorizontalGroup(
            reg_tch_p1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(reg_tch_p1Layout.createSequentialGroup()
                .addComponent(reg_tch1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        reg_tch_p1Layout.setVerticalGroup(
            reg_tch_p1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(reg_tch1, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
        );

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logout.png"))); // NOI18N
        jLabel4.setText("Log out");
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        tch_p1.setBackground(new java.awt.Color(0, 128, 128));

        tch1.setBackground(new java.awt.Color(0, 128, 128));
        tch1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tch1.setForeground(new java.awt.Color(204, 204, 204));
        tch1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/report.png"))); // NOI18N
        tch1.setText("Teachers list");
        tch1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tch1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tch1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tch1MouseExited(evt);
            }
        });

        javax.swing.GroupLayout tch_p1Layout = new javax.swing.GroupLayout(tch_p1);
        tch_p1.setLayout(tch_p1Layout);
        tch_p1Layout.setHorizontalGroup(
            tch_p1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tch_p1Layout.createSequentialGroup()
                .addComponent(tch1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        tch_p1Layout.setVerticalGroup(
            tch_p1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tch1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        res_p1.setBackground(new java.awt.Color(0, 128, 128));

        res1.setBackground(new java.awt.Color(0, 51, 102));
        res1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        res1.setForeground(new java.awt.Color(204, 204, 204));
        res1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/report.png"))); // NOI18N
        res1.setText("Results");
        res1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                res1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                res1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                res1MouseExited(evt);
            }
        });

        javax.swing.GroupLayout res_p1Layout = new javax.swing.GroupLayout(res_p1);
        res_p1.setLayout(res_p1Layout);
        res_p1Layout.setHorizontalGroup(
            res_p1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(res_p1Layout.createSequentialGroup()
                .addComponent(res1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        res_p1Layout.setVerticalGroup(
            res_p1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(res1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
        );

        cal_p.setBackground(new java.awt.Color(0, 128, 128));

        cal1.setBackground(new java.awt.Color(0, 51, 102));
        cal1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        cal1.setForeground(new java.awt.Color(204, 204, 204));
        cal1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/report.png"))); // NOI18N
        cal1.setText("Calendar");
        cal1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cal1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cal1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cal1MouseExited(evt);
            }
        });

        javax.swing.GroupLayout cal_pLayout = new javax.swing.GroupLayout(cal_p);
        cal_p.setLayout(cal_pLayout);
        cal_pLayout.setHorizontalGroup(
            cal_pLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cal_pLayout.createSequentialGroup()
                .addComponent(cal1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        cal_pLayout.setVerticalGroup(
            cal_pLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cal1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Dashboard");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(std_p1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(reg_tch_p1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(tch_p1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(res_p1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(cal_p, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(reg_std_p1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 30, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(reg_std_p1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(reg_tch_p1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(std_p1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(tch_p1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(cal_p, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(res_p1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(reg_std_p, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(reg_tch_p, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(std_p, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(tch_p, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(calndr_p, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(res_p, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(logout, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65)
                .addComponent(reg_std_p, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(reg_tch_p, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(std_p, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(tch_p, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(calndr_p, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(res_p, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(logout, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        cal.setPreferredSize(new java.awt.Dimension(212, 157));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cal, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 893, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
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
        reg_std_p.setBackground(new Color(0,128,128));
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

    private void resMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_resMouseClicked
        // TODO add your handling code here:
                                   this.dispose();
        new adminStdResults().setVisible(true);
    }//GEN-LAST:event_resMouseClicked

    private void resMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_resMouseEntered
        // TODO add your handling code here:
        res_p.setBackground(new Color(0,102,102));
    }//GEN-LAST:event_resMouseEntered

    private void resMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_resMouseExited
        // TODO add your handling code here:
        res_p.setBackground(new Color(0,128,128));
    }//GEN-LAST:event_resMouseExited

    private void std1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_std1MouseClicked
        // TODO add your handling code here:
                                   this.dispose();
        new studentList().setVisible(true);

    }//GEN-LAST:event_std1MouseClicked

    private void std1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_std1MouseEntered
        // TODO add your handling code here:
        std_p.setBackground(new Color(0,102,102));
    }//GEN-LAST:event_std1MouseEntered

    private void std1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_std1MouseExited
        // TODO add your handling code here:
        std_p.setBackground(new Color(0,128,128));
    }//GEN-LAST:event_std1MouseExited

    private void reg_tch1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reg_tch1MouseClicked
        // TODO add your handling code here:
                                   this.dispose();
        new registred_teacherList().setVisible(true);
    }//GEN-LAST:event_reg_tch1MouseClicked

    private void reg_tch1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reg_tch1MouseEntered
        // TODO add your handling code here:
        reg_tch_p.setBackground(new Color(0,102,102));
    }//GEN-LAST:event_reg_tch1MouseEntered

    private void reg_tch1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reg_tch1MouseExited
        // TODO add your handling code here:
        reg_tch_p.setBackground(new Color(0,128,128));
    }//GEN-LAST:event_reg_tch1MouseExited

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        // TODO add your handling code here:
                                   this.dispose();
        new LoginForm().setVisible(true);
    }//GEN-LAST:event_jLabel4MouseClicked

    private void tch1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tch1MouseClicked
        // TODO add your handling code here:
        this.setVisible(false);
        new teacherList().setVisible(true);
    }//GEN-LAST:event_tch1MouseClicked

    private void tch1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tch1MouseEntered
        // TODO add your handling code here:
        tch_p.setBackground(new Color(0,102,102));
    }//GEN-LAST:event_tch1MouseEntered

    private void tch1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tch1MouseExited
        // TODO add your handling code here:
        tch_p.setBackground(new Color(0,128,128));
    }//GEN-LAST:event_tch1MouseExited

    private void res1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_res1MouseClicked
        // TODO add your handling code here:
                                   this.dispose();
        new adminStdResults().setVisible(true);
    }//GEN-LAST:event_res1MouseClicked

    private void res1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_res1MouseEntered
        // TODO add your handling code here:
        res_p.setBackground(new Color(0,102,102));
    }//GEN-LAST:event_res1MouseEntered

    private void res1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_res1MouseExited
        // TODO add your handling code here:
        res_p.setBackground(new Color(0,128,128));
    }//GEN-LAST:event_res1MouseExited

    private void cal1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cal1MouseClicked
        // TODO add your handling code here:
                                   this.dispose();
        new adminCalendar().setVisible(true);
    }//GEN-LAST:event_cal1MouseClicked

    private void cal1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cal1MouseEntered
        // TODO add your handling code here:
        cal_p.setBackground(new Color(0,102,102));
    }//GEN-LAST:event_cal1MouseEntered

    private void cal1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cal1MouseExited
        // TODO add your handling code here:
        cal_p.setBackground(new Color(0,128,128));
    }//GEN-LAST:event_cal1MouseExited

   
    
   
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JCalendar cal;
    private javax.swing.JLabel cal1;
    private javax.swing.JPanel cal_p;
    private javax.swing.JLabel calender;
    private javax.swing.JPanel calndr_p;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JLabel logout;
    private javax.swing.JLabel reg_std;
    private javax.swing.JLabel reg_std1;
    private javax.swing.JPanel reg_std_p;
    private javax.swing.JPanel reg_std_p1;
    private javax.swing.JLabel reg_tch;
    private javax.swing.JLabel reg_tch1;
    private javax.swing.JPanel reg_tch_p;
    private javax.swing.JPanel reg_tch_p1;
    private javax.swing.JLabel res;
    private javax.swing.JLabel res1;
    private javax.swing.JPanel res_p;
    private javax.swing.JPanel res_p1;
    private javax.swing.JLabel std;
    private javax.swing.JLabel std1;
    private javax.swing.JPanel std_p;
    private javax.swing.JPanel std_p1;
    private javax.swing.JLabel tch;
    private javax.swing.JLabel tch1;
    private javax.swing.JPanel tch_p;
    private javax.swing.JPanel tch_p1;
    private javax.swing.JLabel user;
    private javax.swing.JLabel username;
    // End of variables declaration//GEN-END:variables
}
