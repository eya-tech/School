
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
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class admin_dashboard extends javax.swing.JFrame {
ResultSet rs;
String reg_std_query = "SELECT  * FROM registred_students order by IDnumber asc";
DefaultTableModel model=new DefaultTableModel();
JTable table;
 Connection con=null;
    PreparedStatement ps=null,ps1=null,ps2=null,pst;
    Statement stm,st; 

    
    public admin_dashboard() {
        initComponents();
                this.setLocationRelativeTo(null);// center form in the screen

        showRegStudent();  
    }

    void showRegStudent(){
                        
    table= new JTable(){
            @Override
            public boolean isCellEditable(int row, int column) {                
                return false;               
        }
        };
        jScrollPane.setViewportView(table);
      DefaultTableModel model=new DefaultTableModel();
                model.addColumn("ID");                
                model.addColumn("first name");                
                model.addColumn("last name"); 
                 model.addColumn("birthday");
                model.addColumn("gender");
                model.addColumn("adress");
                model.addColumn("mail");
                model.addColumn("phone");
                model.addColumn("licence");
                model.addColumn("payment");
                model.addColumn("theory crs");
                model.addColumn("theory crs hours");
                model.addColumn("practice crs hours");
               
      table.setModel(model);
        table.setPreferredScrollableViewportSize(table.getPreferredSize());
       for(int i=0;i<13;i++){
                   table.getColumnModel().getColumn(i).setPreferredWidth(200);

       }
                         


     table.addMouseListener(new MouseAdapter() {
        @Override
        public void mousePressed(MouseEvent e) {
            if (e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() == 2) {
                String idValue = (String) table.getValueAt(table.getSelectedRow(), 0);
                String mail=(String) table.getValueAt(table.getSelectedRow(), 6);
                Object[] options = {"add", "remove","cancel"};
               
int n = JOptionPane.showOptionDialog(null,"Would you like to save the student","Settings",
    JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,
    null,options,options[0]);
	if (n == JOptionPane.YES_OPTION) {
		addStudent(idValue);
                removeStudent(idValue);}

        else if(n == JOptionPane.NO_OPTION) {    
               removeStudent(idValue);
               String msg=" Sorry you have been refused .";

            mailer.send(mail, msg);
 JOptionPane.showMessageDialog(null," removed successfully from registred students");

	} else {
		System.exit(0);
	}

                
                     showRegStudent();   
        
            }
        }

    });


         try{
	    pst=Connect.ConnectDB().prepareStatement(reg_std_query);
	    ResultSet resultat =pst.executeQuery();
	    while(resultat.next()){
	      model.addRow(new Object []{ resultat.getString("IDnumber"),resultat.getString("first_name")
,resultat.getString("last_name"),resultat.getString("birthday"),resultat.getString("gender"),
resultat.getString("adress"),resultat.getString("email"),resultat.getString("cellphone"),
resultat.getString("licence"),resultat.getString("payment"),resultat.getString("theory_course"),
resultat.getString("theory_course_hrs"),resultat.getString("practice_course_hrs")});
                                                                              
                    }
	        }catch(SQLException ex){
			 JOptionPane.showMessageDialog(this,ex);
                }   
    
                          
                        
                      
         
    
}
     
       void addStudent(String id){
        
        String sql= "SELECT * FROM `registred_students` WHERE `IDnumber`='"+id+"'";

             con=Connect.ConnectDB();
              
   try {
                    ps=con.prepareStatement(sql);
	            ResultSet resultat =ps.executeQuery();
                          if (resultat.next()){
                                 
                                 String usrname=resultat.getString("first_name")+"_"+resultat.getString("last_name");
                                 String password=id;
                                 
String insert_student_sql="INSERT INTO `students`(`studentID`, `first_name`, `last_name`, `gender`, `birthday`, `adress`, `email`, `cellphone`,`picture`, `licence`, `payment`, `theory_course`, `theory_course_hrs`, `practice_course_hrs`,`obtainedLicence`) "
        + " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      
          ps1=con.prepareStatement(insert_student_sql);
          ps1.setString(1,resultat.getString("IDnumber"));                  
          ps1.setString(2,resultat.getString("first_name"));                  
          ps1.setString(3,resultat.getString("last_name"));                  
          ps1.setString(4,resultat.getString("birthday"));                  
          ps1.setString(5,resultat.getString("gender"));                  
          ps1.setString(6,resultat.getString("adress"));                  
          ps1.setString(7,resultat.getString("email"));                  
          ps1.setString(8,resultat.getString("cellphone"));
          ps1.setBlob(9,resultat.getBlob("picture"));
          ps1.setString(10,resultat.getString("licence"));                  
          ps1.setString(11,resultat.getString("payment"));                  
          ps1.setString(12,resultat.getString("theory_course"));                  
          ps1.setString(13,resultat.getString("theory_course_hrs"));                  
          ps1.setString(14,resultat.getString("practice_course_hrs")); 
           ps1.setString(15,resultat.getString("obtainedLicence")); 
                  
   String insert_usr_sql="INSERT INTO `users`(`userID`,`username`, `password`,`user_type`) VALUES ('"+id+"','"+usrname+"','"+id+"','"+"student"+"')";
      stm=Connect.ConnectDB().createStatement();
      
                      
             
            if(ps1.executeUpdate()!=0 && stm.executeUpdate(insert_usr_sql)!=0){
                        JOptionPane.showMessageDialog(null,resultat.getString("first_name")+" "+resultat.getString("last_name")+" added successfully");
            //send email with data 
                                  String msg="you have been accepted \n usename : "+usrname+"\n password :"+password+"\n welcome to our school.";

            mailer.send(resultat.getString("email"), msg);
            
            
            }

                               }

		}  catch (SQLException ex) {
            java.util.logging.Logger.getLogger(admin_dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } 
       }       

            
  void removeStudent(String id){
    try {
        String delete_sql= "DELETE  FROM `registred_students` WHERE `IDnumber`='"+id+"'"; 
        st=Connect.ConnectDB().createStatement();
        st.executeUpdate(delete_sql);
         
    } catch (SQLException ex) {
        Logger.getLogger(admin_dashboard.class.getName()).log(Level.SEVERE, null, ex);
    }

   }  
   
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        std_p = new javax.swing.JPanel();
        std = new javax.swing.JLabel();
        reg_std_p = new javax.swing.JPanel();
        reg_std = new javax.swing.JLabel();
        reg_tch_p = new javax.swing.JPanel();
        reg_tch = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tch_p = new javax.swing.JPanel();
        tch = new javax.swing.JLabel();
        res_p = new javax.swing.JPanel();
        res = new javax.swing.JLabel();
        cal_p = new javax.swing.JPanel();
        cal = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("admin");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(0, 128, 128));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Admin");

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/edit_profile.png"))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(332, 332, 332)
                .addComponent(jLabel3)
                .addContainerGap(534, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
        );

        jScrollPane.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(0, 128, 128));
        jPanel1.setForeground(new java.awt.Color(204, 204, 204));

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

        reg_std_p.setBackground(new java.awt.Color(0, 102, 102));

        reg_std.setBackground(new java.awt.Color(153, 0, 51));
        reg_std.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        reg_std.setForeground(new java.awt.Color(204, 204, 204));
        reg_std.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/report.png"))); // NOI18N
        reg_std.setText("registred Students list");

        javax.swing.GroupLayout reg_std_pLayout = new javax.swing.GroupLayout(reg_std_p);
        reg_std_p.setLayout(reg_std_pLayout);
        reg_std_pLayout.setHorizontalGroup(
            reg_std_pLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, reg_std_pLayout.createSequentialGroup()
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
        reg_tch.setText("registred teachers list");
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

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logout.png"))); // NOI18N
        jLabel4.setText("Log out");
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        tch_p.setBackground(new java.awt.Color(0, 128, 128));

        tch.setBackground(new java.awt.Color(0, 128, 128));
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
                .addGap(0, 0, Short.MAX_VALUE))
        );
        cal_pLayout.setVerticalGroup(
            cal_pLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cal, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Dashboard");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(std_p, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(reg_tch_p, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(tch_p, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(res_p, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(cal_p, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(reg_std_p, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 30, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                .addComponent(reg_std_p, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(reg_tch_p, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(std_p, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(tch_p, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(cal_p, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(res_p, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 914, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane)
                .addContainerGap())
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1150, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

   
    
      
    
    //***************************************************
    
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

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        // TODO add your handling code here:
                                   this.dispose();
        new LoginForm().setVisible(true);
    }//GEN-LAST:event_jLabel4MouseClicked

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

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel cal;
    private javax.swing.JPanel cal_p;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JLabel reg_std;
    private javax.swing.JPanel reg_std_p;
    private javax.swing.JLabel reg_tch;
    private javax.swing.JPanel reg_tch_p;
    private javax.swing.JLabel res;
    private javax.swing.JPanel res_p;
    private javax.swing.JLabel std;
    private javax.swing.JPanel std_p;
    private javax.swing.JLabel tch;
    private javax.swing.JPanel tch_p;
    // End of variables declaration//GEN-END:variables
}
