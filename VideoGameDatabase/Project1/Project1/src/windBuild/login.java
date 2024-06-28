//Program launches a JFrame window which brings the user to a login screen from which they can create an account, recover their password/username, or login to the database.
//entering the database allows the user to search for information on video games within a database

package windBuild;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.CallableStatement;
import java.sql.Types;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

import java.awt.BorderLayout;
import java.util.*;
import java.util.concurrent.TimeUnit;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;

import java.awt.Color;

import java.awt.event.KeyEvent;
import java.awt.BorderLayout;
import java.util.*;
import java.util.logging.Logger;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.System.Logger.Level;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import net.proteanit.sql.DbUtils;

import javax.swing.JScrollBar;
import javax.swing.border.LineBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JRadioButton;
import javax.swing.JList;
import javax.swing.JEditorPane;

public class login {

	//information required to enter database
	static final String databasePrefix ="cs366-2231_syversonbl27";//database name
    static final String netID ="syversonbl27"; // Please enter your netId
    static final String hostName ="washington.uww.edu"; //140.146.23.39 or washington.uww.edu
    static final String databaseURL ="jdbc:mariadb://"+hostName+"/"+databasePrefix;
    static final String password="bs2719"; // please enter your own password
    
    private Connection connection = null;
	private Statement statement = null;
	private ResultSet resultSet = null;
	
	public void Connection(){//connect to database
		  
	      try {
	    	    Class.forName("org.mariadb.jdbc.Driver");
	    	  	System.out.println("databaseURL"+ databaseURL);
	            connection = DriverManager.getConnection(databaseURL, netID, password);
	            System.out.println("Successfully connected to the database");
	         }
	        catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }
	        catch (SQLException e) {
	            e.printStackTrace();
	        }
	    } // end of Connection
	
    public void simpleQuery(String sqlQuery) {//simpleQuery method
    	try {
    		statement = connection.createStatement();
    		resultSet = statement.executeQuery(sqlQuery);

    		ResultSetMetaData metaData = resultSet.getMetaData();
    		int columns = metaData.getColumnCount();

    		for (int i=1; i<= columns; i++) {
    			System.out.print(metaData.getColumnName(i)+"\t");
    		}

    		System.out.println();

    		while (resultSet.next()) {
       
    			for (int i=1; i<= columns; i++) {
    				System.out.print(resultSet.getObject(i)+"\t\t");
    			}
    			System.out.println();
    		}
    	}
    	catch (SQLException e) {
    		e.printStackTrace();
    	}
    } // end of simpleQuery method
	String st = "";
    public ArrayList < String > list = new ArrayList < String > ();
    public void simpleQuery2(String sqlQuery) {//simpleQuery2
    	try {
    		statement = connection.createStatement();
    		resultSet = statement.executeQuery(sqlQuery);
    		ResultSetMetaData metaData = resultSet.getMetaData();
    		int columns = metaData.getColumnCount();
    		for (int i=2; i<= columns; i++) {
    			System.out.print(metaData.getColumnName(i));
    		}
    		
    		System.out.println();

    		while (resultSet.next()) {
    			for (int i=1; i<= columns; i++) {
    				st = resultSet.getObject(i).toString();
    				list.add(resultSet.getString(1));
    			}
    			System.out.println();
    		}
    	}
    	catch (SQLException e) {
    		e.printStackTrace();
    	}
    } // end of simpleQuery2 method
	
	private JFrame frmCreateAccount;
	
	/**
	 * Launch the application.
	 */	
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login window = new login();
					window.frmCreateAccount.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public login() {
		initialize();//go to method which determines starting screen, loginScreen() by default
	}
	Connection connect;
	PreparedStatement pstate;
	private JTextField username2;
	private JTextField txtEe;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JTextField textField_1;
	private JLabel lblNewLabel_6;
	private JPasswordField password2;
	private JTextField usernameField;
	private JTextField faveGameField;
	
	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {//determines starting screen, loginScreen() by default
		frmCreateAccount = new JFrame();
		frmCreateAccount.setTitle("User");
		frmCreateAccount.setBounds(100, 100, 631, 433);
		frmCreateAccount.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCreateAccount.getContentPane().setLayout(null);
		
		//---SCREEN SELECTION--
		//Uncomment which screen to start at when the program runs, loginScreen() is default
		//each method below is a seperate window
		
		//createAccount();//start on create account screen
		loginScreen();//start on login screen
		//forgotPassword();//start on forgot password screen
		//forgotUsername();//start on forgot username screen
		//initialize2();//start on game database search screen
		//---END SCREEN SELECTION--
	}
	
	public void loginScreen() {//loginScreen()  this is the main and first screen the user will enter by default
		JLabel lblNewLabel_1 = new JLabel("Video Game Sales 2019");//title
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel_1.setBounds(57, 11, 355, 76);
		frmCreateAccount.getContentPane().add(lblNewLabel_1);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Login", TitledBorder.LEADING, TitledBorder.TOP, null, null));//border title
		panel.setBounds(138, 82, 355, 301);
		frmCreateAccount.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Username:");//enter username text field
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(31, 63, 86, 28);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("Password:");//enter password text field
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(35, 94, 78, 28);
		panel.add(lblNewLabel_2);
		
		username2 = new JTextField();
		username2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		username2.setBounds(116, 62, 139, 28);
		panel.add(username2);
		username2.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Create Account");//create account button, goes to createAccount()
		btnNewButton_1.setBounds(159, 165, 127, 23);
		panel.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	panel.setVisible(false);
		    	createAccount();
		    }
		});
		
		JButton btnNewButton_2 = new JButton("Forgot Username");//forgot username button, goes to forgotUsername()
		btnNewButton_2.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			panel.setVisible(false);
			forgotUsername();
		}
		});
		btnNewButton_2.setBounds(20, 220, 151, 23);
		panel.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Forgot Password");//forgot password button, goes to forgotPassword()
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(false);
				forgotPassword();
			}
		});
		btnNewButton_3.setBounds(20, 254, 151, 23);
		panel.add(btnNewButton_3);
		
		// -validation popups-
		JLabel lblNewLabel_13 = new JLabel("USERNAME REQUIRED*");
		lblNewLabel_13.setForeground(Color.RED);
		lblNewLabel_13.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblNewLabel_13.setBounds(142, 48, 123, 14);
		panel.add(lblNewLabel_13);
		lblNewLabel_13.setVisible(false);
		
		JLabel lblNewLabel_14 = new JLabel("PASSWORD REQUIRED*");
		lblNewLabel_14.setForeground(Color.RED);
		lblNewLabel_14.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblNewLabel_14.setBounds(136, 121, 151, 23);
		panel.add(lblNewLabel_14);
		lblNewLabel_14.setVisible(false);
		
		JLabel lblNewLabel_15 = new JLabel("INVALID USERNAME OR PASSWORD*");
		lblNewLabel_15.setForeground(Color.RED);
		lblNewLabel_15.setFont(new Font("Tahoma", Font.ITALIC, 14));
		lblNewLabel_15.setBounds(22, 140, 253, 14);
		panel.add(lblNewLabel_15);
		lblNewLabel_15.setVisible(false);
		// -end validation popups-
		
		password2 = new JPasswordField();
		password2.setBounds(116, 94, 139, 29);
		panel.add(password2);
		
		JButton btnNewButton = new JButton("Confirm");//attempt to login
		btnNewButton.setBounds(22, 165, 127, 23);
		panel.add(btnNewButton);	
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean success = false;
				String username = username2.getText();
				String password = password2.getText();
				
				//ensures all fields are filled out
				if(username.isEmpty()) {
					lblNewLabel_13.setVisible(true);
				}
					else if(!username.isEmpty()) {
						lblNewLabel_13.setVisible(false);
					}
				if(password.isEmpty()) {
					lblNewLabel_14.setVisible(true);
				}
					else if(!username.isEmpty()) {
						lblNewLabel_14.setVisible(false);
					}
				if(!username.isEmpty() && !password.isEmpty()) {//if username and pasword is filled out, search database for user, if successful then enter database
					Boolean x = false;
				
			    	login demoObj2 = new login();
			    	demoObj2.Connection();
			    	String sqlQuery = "SELECT CASE WHEN EXISTS(SELECT * FROM User WHERE username = '"+username+"' AND password = '"+password+"')THEN 'TRUE' ELSE 'FALSE' END;";
			    	demoObj2.simpleQuery2(sqlQuery);
			    		    	
					if(x == false) {//user found
						lblNewLabel_15.setVisible(false);
						System.out.println("USER FOUND");
						initialize2();
					
					}
					else {//user not found
						lblNewLabel_15.setVisible(true);
						System.out.println("USER NOT FOUND");
					}
					}
				}		
		});//end confirm listener
		
	}//end loginScreen();	
	
	public void forgotUsername() {//forgot username
		JLabel lblNewLabel_7 = new JLabel("Video Game Sales 2019");//title
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel_7.setBounds(57, 11, 355, 76);
		frmCreateAccount.getContentPane().add(lblNewLabel_7);
		
		JPanel panel4 = new JPanel();//create panel
		panel4.setBorder(new TitledBorder(null, "Username Recovery", TitledBorder.LEADING, TitledBorder.TOP, null, null));//border title
		panel4.setBounds(139, 82, 355, 301);
		frmCreateAccount.getContentPane().add(panel4);
		panel4.setLayout(null);
		
		usernameField = new JTextField();
		usernameField.setBounds(156, 36, 164, 27);
		panel4.add(usernameField);
		usernameField.setColumns(10);
		
		faveGameField = new JTextField();
		faveGameField.setBounds(156, 82, 164, 27);
		panel4.add(faveGameField);
		faveGameField.setColumns(10);
		
		JLabel lblNewLabel_16 = new JLabel("Password:");//enter password
		lblNewLabel_16.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_16.setBounds(70, 42, 109, 14);
		panel4.add(lblNewLabel_16);
		
		JLabel lblNewLabel_17 = new JLabel("Favorite Game:");//enter favorite game
		lblNewLabel_17.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_17.setBounds(48, 89, 109, 14);
		panel4.add(lblNewLabel_17);
		
		JLabel lblNewLabel_18 = new JLabel("PASSWORD REQUIRED*");//password validation
		lblNewLabel_18.setForeground(Color.RED);
		lblNewLabel_18.setBounds(156, 63, 164, 14);
		panel4.add(lblNewLabel_18);
		lblNewLabel_18.setVisible(false);
		
		JLabel lblNewLabel_19 = new JLabel("FAVORITE GAME REQUIRED*");//favorite game validation
		lblNewLabel_19.setForeground(Color.RED);
		lblNewLabel_19.setBounds(156, 110, 164, 14);
		panel4.add(lblNewLabel_19);
		lblNewLabel_19.setVisible(false);
		
		JLabel providedPassword = new JLabel("*username*");//shows username on panel
		providedPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		providedPassword.setBounds(28, 135, 142, 14);
		panel4.add(providedPassword);
		providedPassword.setVisible(false);
		
		JLabel lblNewLabel_20 = new JLabel("SUCCESS! SEE CONSOLE");//appears on successful search
		lblNewLabel_20.setForeground(Color.GREEN);
		lblNewLabel_20.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_20.setBounds(121, 148, 206, 14);
		panel4.add(lblNewLabel_20);
		lblNewLabel_20.setVisible(false);
		
		JButton btnNewButton_8 = new JButton("Cancel");//returns to login screen
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel4.setVisible(false);
				loginScreen();//return to login screen
			}
		});
		btnNewButton_8.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_8.setBounds(192, 215, 142, 58);
		panel4.add(btnNewButton_8);

		JButton btnNewButton_9 = new JButton("<-- Return");//pop-up return button
		btnNewButton_9.setVisible(false);
		btnNewButton_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel4.setVisible(false);
				loginScreen();//return to login screen
			}
		});
		btnNewButton_9.setBounds(28, 186, 142, 23);
		panel4.add(btnNewButton_9);
		
		JButton btnNewButton_7 = new JButton("Confirm");//submit button, searches for username and favorite game
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String password = usernameField.getText();
				String faveGame = faveGameField.getText();
					//ensures password and favorite game is not empty
				if(password.isEmpty()) {//pasword empty
					lblNewLabel_18.setVisible(true);//tell user password is empty
				}
				else if(!password.isEmpty()) {//password not empty
					lblNewLabel_18.setVisible(false);//dont tell user password is empty
				}
				if(faveGame.isEmpty()) {//favorite game empty
					lblNewLabel_19.setVisible(true);//tell user favorite game is empty
				}
				else if(!faveGame.isEmpty()) {//favorite game is empty
					lblNewLabel_19.setVisible(false);//tell the user favorite game is empty
				}				
				if(!password.isEmpty() && !faveGame.isEmpty()) {//password and favorite game is not empty
					System.out.println("SUCCESS");//console message
					btnNewButton_9.setVisible(true);
					lblNewLabel_20.setVisible(true);

			    	createUser demoObj = new createUser();//calls external class to create a user
			    	demoObj.Connection();
			    	System.out.println("\n----------");
			    	String sqlQuery ="select username from User WHERE password = '"+password+"' AND favoriteGame = '"+faveGame+"';";
			    	demoObj.simpleQuery(sqlQuery);//searches for username
			    				    				    	
			    	providedPassword.getText();
			    	providedPassword.setText("Username");//displays username in console if password and favorite game match and user exists
			    	System.out.println("----------\n");	
				}		
			}	
		});
		btnNewButton_7.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_7.setBounds(28, 215, 142, 58);
		panel4.add(btnNewButton_7);	
	}// end forgotUsername()
	
	public void forgotPassword() {//forgot password
		JLabel lblNewLabel_7 = new JLabel("Video Game Sales 2019");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel_7.setBounds(57, 11, 355, 76);
		frmCreateAccount.getContentPane().add(lblNewLabel_7);
		
		JPanel panel3 = new JPanel();
		panel3.setBorder(new TitledBorder(null, "Password Recovery", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel3.setBounds(139, 82, 355, 301);
		frmCreateAccount.getContentPane().add(panel3);
		panel3.setLayout(null);
		
		usernameField = new JTextField();
		usernameField.setBounds(156, 36, 164, 27);
		panel3.add(usernameField);
		usernameField.setColumns(10);
		
		faveGameField = new JTextField();
		faveGameField.setBounds(156, 82, 164, 27);
		panel3.add(faveGameField);
		faveGameField.setColumns(10);
		
		JLabel lblNewLabel_16 = new JLabel("Username:");
		lblNewLabel_16.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_16.setBounds(70, 42, 109, 14);
		panel3.add(lblNewLabel_16);
		
		JLabel lblNewLabel_17 = new JLabel("Favorite Game:");
		lblNewLabel_17.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_17.setBounds(48, 89, 109, 14);
		panel3.add(lblNewLabel_17);
		
		JLabel lblNewLabel_18 = new JLabel("USERNAME REQUIRED*");//username validation
		lblNewLabel_18.setForeground(Color.RED);
		lblNewLabel_18.setBounds(156, 63, 164, 14);
		panel3.add(lblNewLabel_18);
		lblNewLabel_18.setVisible(false);
		
		JLabel lblNewLabel_19 = new JLabel("FAVORITE GAME REQUIRED*");//favorite game validation
		lblNewLabel_19.setForeground(Color.RED);
		lblNewLabel_19.setBounds(156, 110, 164, 14);
		panel3.add(lblNewLabel_19);
		lblNewLabel_19.setVisible(false);
		
		JLabel providedPassword = new JLabel("*password*");//shows password on panel
		providedPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		providedPassword.setBounds(10, 120, 142, 14);
		panel3.add(providedPassword);
		providedPassword.setVisible(false);
		
		JLabel lblNewLabel_20 = new JLabel("SUCCESS! SEE CONSOLE");//shows after successful lookup
		lblNewLabel_20.setForeground(Color.GREEN);
		lblNewLabel_20.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_20.setBounds(136, 145, 184, 14);
		panel3.add(lblNewLabel_20);
		lblNewLabel_20.setVisible(false);
		
		JButton btnNewButton_8 = new JButton("Cancel");//button to return to login screen
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel3.setVisible(false);
				loginScreen();
			}
		});
		btnNewButton_8.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_8.setBounds(192, 215, 142, 58);
		panel3.add(btnNewButton_8);
		
		JButton btnNewButton_9 = new JButton("<-- Return");//return button, shows after successful lookup
		btnNewButton_9.setVisible(false);
		btnNewButton_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel3.setVisible(false);
				loginScreen();
			}
		});
		btnNewButton_9.setBounds(28, 186, 142, 23);
		panel3.add(btnNewButton_9);
		
		JButton btnNewButton_7 = new JButton("Confirm");//begins search
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				String username = usernameField.getText();
				String faveGame = faveGameField.getText();
				
				//ensures username and favorite game are not empty 
				if(username.isEmpty()) {
					lblNewLabel_18.setVisible(true);
				}
				else if(!username.isEmpty()) {
					lblNewLabel_18.setVisible(false);
				}
				if(faveGame.isEmpty()) {
					lblNewLabel_19.setVisible(true);
				}
				else if(!faveGame.isEmpty()) {
					lblNewLabel_19.setVisible(false);
				}
				if(!username.isEmpty() && !faveGame.isEmpty()) {//if username and password fields are not empty
					System.out.println("SUCCESS");
			
					btnNewButton_9.setVisible(true);
					lblNewLabel_20.setVisible(true);

			    	createUser demoObj = new createUser();
			    	demoObj.Connection();
			    	System.out.println("\n----------");
			    	String sqlQuery ="select password from User WHERE username = '"+username+"' AND favoriteGame = '"+faveGame+"';";//displays password in console if username and favorite game match up
			    	demoObj.simpleQuery(sqlQuery);
			    	providedPassword.setText("PASSWORD");//display password in window
			    	System.out.println("----------\n");
				}		
			}	
		});
		btnNewButton_7.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_7.setBounds(28, 215, 142, 58);
		panel3.add(btnNewButton_7);
	}// end forgotPassword()
	
	public void createAccount() {//create account
		JLabel lblNewLabel_7 = new JLabel("Create Account");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel_7.setBounds(57, 11, 355, 76);
		frmCreateAccount.getContentPane().add(lblNewLabel_7);
		
		JPanel panel2 = new JPanel();
		panel2.setBorder(new TitledBorder(null, "Account", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel2.setBounds(139, 82, 355, 301);
		frmCreateAccount.getContentPane().add(panel2);
		panel2.setLayout(null);
	
		JLabel lblNewLabel_3 = new JLabel("Enter Username:");
		lblNewLabel_3.setBounds(40, 53, 153, 14);
		panel2.add(lblNewLabel_3);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		lblNewLabel_6 = new JLabel("Enter Favorite Game:");
		lblNewLabel_6.setBounds(10, 95, 170, 14);
		panel2.add(lblNewLabel_6);
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel lblNewLabel_4 = new JLabel("Enter Password:");
		lblNewLabel_4.setBounds(14, 136, 145, 14);
		panel2.add(lblNewLabel_4);
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel lblNewLabel_5 = new JLabel("Confirm Password:");
		lblNewLabel_5.setBounds(13, 184, 153, 14);
		panel2.add(lblNewLabel_5);
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		txtEe = new JTextField();
		txtEe.setToolTipText("Username");
		txtEe.setBounds(179, 52, 158, 20);
		panel2.add(txtEe);
		txtEe.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setToolTipText("Favorite Game");
		textField_1.setBounds(179, 94, 158, 20);
		panel2.add(textField_1);
		textField_1.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setToolTipText("Password");
		passwordField.setBounds(179, 138, 158, 20);
		panel2.add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setToolTipText("Confirm Password");
		passwordField_1.setBounds(178, 181, 158, 20);
		panel2.add(passwordField_1);
		
	// -input validation-
	JLabel lblNewLabel_8 = new JLabel("USERNAME REQUIRED*");
	lblNewLabel_8.setEnabled(true);
	lblNewLabel_8.setForeground(Color.RED);
	lblNewLabel_8.setBackground(new Color(204, 0, 0));
	lblNewLabel_8.setFont(new Font("Tahoma", Font.ITALIC, 11));
	lblNewLabel_8.setBounds(183, 73, 155, 14);
	panel2.add(lblNewLabel_8);
	lblNewLabel_8.setVisible(false);
		
	JLabel lblNewLabel_9 = new JLabel("FAVORITE GAME REQUIRED*");
	lblNewLabel_9.setFont(new Font("Tahoma", Font.ITALIC, 11));
	lblNewLabel_9.setForeground(Color.RED);
	lblNewLabel_9.setBounds(182, 115, 158, 14);
	panel2.add(lblNewLabel_9);
	lblNewLabel_9.setVisible(false);
	
	JLabel lblNewLabel_10 = new JLabel("PASSWORD REQUIRED*");
	lblNewLabel_10.setFont(new Font("Tahoma", Font.ITALIC, 11));
	lblNewLabel_10.setForeground(Color.RED);
	lblNewLabel_10.setBounds(180, 159, 155, 14);
	panel2.add(lblNewLabel_10);
	lblNewLabel_10.setVisible(false);
	
	JLabel lblNewLabel_11 = new JLabel("PASSWORDS MUST MATCH*");
	lblNewLabel_11.setForeground(Color.RED);
	lblNewLabel_11.setFont(new Font("Tahoma", Font.ITALIC, 11));
	lblNewLabel_11.setBounds(179, 201, 158, 14);
	panel2.add(lblNewLabel_11);	
	lblNewLabel_11.setVisible(false);
	// -end input validation-
		
	JLabel lblNewLabel_12 = new JLabel("ACCOUNT SUCCESSFULLY CREATED!");//message popup if account created
	lblNewLabel_12.setForeground(new Color(0, 255, 153));
	lblNewLabel_12.setFont(new Font("Tahoma", Font.BOLD, 18));
	lblNewLabel_12.setBounds(10, 276, 335, 14);
	panel2.add(lblNewLabel_12);
	lblNewLabel_12.setVisible(false);
	
	JButton btnNewButton_6 = new JButton("<-- Return to Login");//return to login button that shows after account created
	btnNewButton_6.setFont(new Font("Tahoma", Font.ITALIC, 14));
	btnNewButton_6.setBounds(10, 209, 158, 23);
	panel2.add(btnNewButton_6);
	btnNewButton_6.setVisible(false);
			
		JButton btnNewButton_4 = new JButton("Confirm");//attempt to create account
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean success = false;
				String username = txtEe.getText();
				String faveGame = textField_1.getText();
				String password = passwordField.getText();
				String password2 = passwordField_1.getText();
				
				//checks if all fields are filled
				if(username.isEmpty()) {
					lblNewLabel_8.setVisible(true);
				}
				else if(!username.isEmpty()) {
					lblNewLabel_8.setVisible(false);
				}
				if(faveGame.isEmpty()) {
					lblNewLabel_9.setVisible(true);
				}
				else if(!faveGame.isEmpty()) {
					lblNewLabel_9.setVisible(false);
				}
				if(password.isEmpty()) {
					lblNewLabel_10.setVisible(true);
				}
				else if(!password.isEmpty()) {
					lblNewLabel_10.setVisible(false);
				}
				if(password2.isEmpty() || !password2.equals(password)) {
					lblNewLabel_11.setVisible(true);
				}
				else if(password2.equals(password)) {
					lblNewLabel_11.setVisible(false);
				}
				
				if(!username.isEmpty() && !faveGame.isEmpty() && !password.isEmpty() && password.equals(password2)) {//if all fields filled, user does not exist, and passwor/confirm password match, a new username, favorite game, and password are created in the database
					lblNewLabel_12.setVisible(true);
					success = true;
					System.out.println("SUCCESS");
					btnNewButton_6.setVisible(true);
					
			    	createUser demoObj = new createUser();
			    	demoObj.Connection();
			    	String sqlStr = "'" + username + "', '" + password + "', '" + faveGame + "'";
			    	String sqlQuery ="INSERT INTO User (username, password, favoriteGame) VALUES (" + sqlStr + ");";//insert new user into database
			    	demoObj.simpleQuery(sqlQuery);
				}		
			}
		});//end confirm listener
		
		btnNewButton_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_4.setBounds(40, 243, 123, 29);
		panel2.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("Cancel");
		btnNewButton_5.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_5.setBounds(184, 243, 123, 29);
		panel2.add(btnNewButton_5);
		
		btnNewButton_6.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	panel2.setVisible(false);
		    	loginScreen();
		    }
		});
		
		btnNewButton_5.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	panel2.setVisible(false);
		    	loginScreen();//return to login screen
		    }
		});	
	}//end createAccount()
	

	
//-----------------------------------------------------------------BEGIN GAME SEARCH-------------------------------------------------------------------------
	//after successful login, the database game searching tool pops up
	
	/**
	 * Create the application.
	 */

	private JFrame frame;
	ResultSet rs; // set type result set
	Connection con; // set type connection
	PreparedStatement pstate2; // set type SQL statement
	
	public void Connection11(){ // connection method designed to check SQL statement on button command top left hand button top 100 games

		try {
			Class.forName("org.mariadb.jdbc.Driver");
		    System.out.println("databaseURL"+ databaseURL); // database call
			Connection connect = DriverManager.getConnection(databaseURL, netID, password);
			Statement st = connect.createStatement();
			ResultSet rs = st.executeQuery("select g.rank, g.name from Game g where rank <= 100"); // SQL statement
			
			while(table.getRowCount() > 0) {
				((DefaultTableModel)table.getModel()).removeRow(0); // checking table model count for row expansin with while loop
			}
			int col = rs.getMetaData().getColumnCount();
			while(rs.next()) {
				Object [] rows = new Object [col];
				for(int i = 1; i <= col; i++) {
					rows[i-1] = rs.getObject(i); // insert column based off new object being added(entity set) type
				}
				((DefaultTableModel)table.getModel()).insertRow(rs.getRow() -1, rows);
			}
			rs.close();
			st.close();
		
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch (SQLException e) {
			e.printStackTrace();
			
		}
	} // end of Connection
	
	public void Connection2(){ // button List of Games

		try {
			Class.forName("org.mariadb.jdbc.Driver");
		    System.out.println("databaseURL"+ databaseURL);
			Connection connect = DriverManager.getConnection(databaseURL, netID, password);
			Statement st = connect.createStatement();
			ResultSet rs = st.executeQuery("select g.name, g.platform, Sales.globalShipped, Score.criticScore from Game g, Sales, Score Where g.genre = Genre AND g.rank = Sales.salesRank AND g.rank = Score.scoreRank");
			
			while(table.getRowCount() > 0) {
				((DefaultTableModel)table.getModel()).removeRow(0);
			}
			int col = rs.getMetaData().getColumnCount();
			while(rs.next()) {
				Object [] rows = new Object [col];
				for(int i = 1; i <= col; i++) {
					rows[i-1] = rs.getObject(i);
				}
				((DefaultTableModel)table.getModel()).insertRow(rs.getRow() -1, rows);
			}
			rs.close();
			st.close();
		
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	} // end of Connection
	
	public void Connection3(){ // button Critics

		try {
			Class.forName("org.mariadb.jdbc.Driver");
		    System.out.println("databaseURL"+ databaseURL);
			Connection connect = DriverManager.getConnection(databaseURL, netID, password);
			Statement st = connect.createStatement();
			ResultSet rs = st.executeQuery("Select g.platform, SUM(s.globalShipped) As total_sales\r\n"
					+ "From Game g, Sales s\r\n"
					+ "Where g.rank=s.salesRank\r\n"
					+ "Group By g.platform\r\n"
					+ "Having total_sales>0\r\n"
					+ "");
			
			while(table.getRowCount() > 0) {
				((DefaultTableModel)table.getModel()).removeRow(0);
			}
			int col = rs.getMetaData().getColumnCount();
			while(rs.next()) {
				Object [] rows = new Object [col];
				for(int i = 1; i <= col; i++) {
					rows[i-1] = rs.getObject(i);
				}
				((DefaultTableModel)table.getModel()).insertRow(rs.getRow() -1, rows);
			}
			rs.close();
			st.close();
		
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	} // end of Connection
	public void Connection4(){ // button Total Sales

		try {
			Class.forName("org.mariadb.jdbc.Driver");
		    System.out.println("databaseURL"+ databaseURL);
			Connection connect = DriverManager.getConnection(databaseURL, netID, password);
			Statement st = connect.createStatement();
			ResultSet rs = st.executeQuery("select g.name, sc.criticScore from Game g, Score sc where g.rank = sc.scoreRank ORDER BY sc.criticScore DESC limit 10");
			
			while(table.getRowCount() > 0) {
				((DefaultTableModel)table.getModel()).removeRow(0);
			}
			int col = rs.getMetaData().getColumnCount();
			while(rs.next()) {
				Object [] rows = new Object [col];
				for(int i = 1; i <= col; i++) {
					rows[i-1] = rs.getObject(i);
				}
				((DefaultTableModel)table.getModel()).insertRow(rs.getRow() -1, rows);
			}
			rs.close();
			st.close();
		
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	} // end of Connection
	
	public void Connection5(){ // Button Rank

		try {
			Class.forName("org.mariadb.jdbc.Driver");
		    System.out.println("databaseURL"+ databaseURL);
			Connection connect = DriverManager.getConnection(databaseURL, netID, password);
			Statement st = connect.createStatement();
			ResultSet rs = st.executeQuery("Select g.name,g.platform, s.globalShipped,sc.criticScore, sc.userScore,sc.esrbRating, g.developer\r\n"
					+ "From Game g, Sales s, Score sc\r\n"
					+ "Where g.rank=s.salesRank And sc.scoreRank=g.rank limit 100;\r\n"
					+ "");
			
			while(table.getRowCount() > 0) {
				((DefaultTableModel)table.getModel()).removeRow(0);
			}
			int col = rs.getMetaData().getColumnCount();
			while(rs.next()) {
				Object [] rows = new Object [col];
				for(int i = 1; i <= col; i++) {
					rows[i-1] = rs.getObject(i);
				}
				((DefaultTableModel)table.getModel()).insertRow(rs.getRow() -1, rows);
			}
			rs.close();
			st.close();
		
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	} // end of Connection
	
	public void Connection6(){ // Button Sales range

		try {
			Class.forName("org.mariadb.jdbc.Driver");
		    System.out.println("databaseURL"+ databaseURL);
			Connection connect = DriverManager.getConnection(databaseURL, netID, password);
			Statement st = connect.createStatement();
			ResultSet rs = st.executeQuery("Select * \r\n"
					+ "From Sales \r\n"
					+ "Where globalShipped Between 10 And 35\r\n"
					+ "Order By globalShipped ASC\r\n"
					+ "");
			
			while(table.getRowCount() > 0) {
				((DefaultTableModel)table.getModel()).removeRow(0);
			}
			int col = rs.getMetaData().getColumnCount();
			while(rs.next()) {
				Object [] rows = new Object [col];
				for(int i = 1; i <= col; i++) {
					rows[i-1] = rs.getObject(i);
				}
				((DefaultTableModel)table.getModel()).insertRow(rs.getRow() -1, rows);
			}
			rs.close();
			st.close();
		
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	} // end of Connection
	
	public void Connection7(){ // radio Button top-selling games of 2018

		try {
			Class.forName("org.mariadb.jdbc.Driver");
		    System.out.println("databaseURL"+ databaseURL);
			Connection connect = DriverManager.getConnection(databaseURL, netID, password);
			Statement st = connect.createStatement();
			ResultSet rs = st.executeQuery("Select g.name,MAX(Sales.globalShipped)\r\n"
					+ "From Game g, Sales \r\n"
					+ "Where g.rank =Sales.salesRank And g.year= 2018\r\n"
					+ "");
			
			while(table.getRowCount() > 0) {
				((DefaultTableModel)table.getModel()).removeRow(0);
			}
			int col = rs.getMetaData().getColumnCount();
			while(rs.next()) {
				Object [] rows = new Object [col];
				for(int i = 1; i <= col; i++) {
					rows[i-1] = rs.getObject(i);
				}
				((DefaultTableModel)table.getModel()).insertRow(rs.getRow() -1, rows);
			}
			rs.close();
			st.close();
		
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	} // end of Connection
	
	public void Connection8(){ // radio Button Top developer highest rating

		try {
			Class.forName("org.mariadb.jdbc.Driver");
		    System.out.println("databaseURL"+ databaseURL);
			Connection connect = DriverManager.getConnection(databaseURL, netID, password);
			Statement st = connect.createStatement();
			ResultSet rs = st.executeQuery("    SELECT g.developer, AVG(sc.criticScore) AS avg_critic_score, AVG(sc.userScore) AS avg_user_score\r\n"
					+ "    FROM Game g\r\n"
					+ "    JOIN Score sc ON g.rank = sc.scoreRank\r\n"
					+ "    GROUP BY g.developer\r\n"
					+ "    ORDER BY (AVG(sc.criticScore) + AVG(sc.userScore)) DESC\r\n"
					+ "    LIMIT 1\r\n"
					+ "");
			
			while(table.getRowCount() > 0) {
				((DefaultTableModel)table.getModel()).removeRow(0);
			}
			int col = rs.getMetaData().getColumnCount();
			while(rs.next()) {
				Object [] rows = new Object [col];
				for(int i = 1; i <= col; i++) {
					rows[i-1] = rs.getObject(i);
				}
				((DefaultTableModel)table.getModel()).insertRow(rs.getRow() -1, rows);
			}
			rs.close();
			st.close();
		
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	} // end of Connection
	
	public void Connection9(){ // radio button top selling game on platform list

		try {
			Class.forName("org.mariadb.jdbc.Driver");
		    System.out.println("databaseURL"+ databaseURL);
			Connection connect = DriverManager.getConnection(databaseURL, netID, password);
			Statement st = connect.createStatement();
			ResultSet rs = st.executeQuery("Select g.platform,g.name, Max(s.globalShipped) As max_sales From  Game g, Sales s\r\n"
					+ "Where g.rank = s.salesRank\r\n"
					+ "Group By g.platform\r\n"
					+ "Having max_sales>0\r\n"
					+ "");
			
			while(table.getRowCount() > 0) {
				((DefaultTableModel)table.getModel()).removeRow(0);
			}
			int col = rs.getMetaData().getColumnCount();
			while(rs.next()) {
				Object [] rows = new Object [col];
				for(int i = 1; i <= col; i++) {
					rows[i-1] = rs.getObject(i);
				}
				((DefaultTableModel)table.getModel()).insertRow(rs.getRow() -1, rows);
			}
			rs.close();
			st.close();
		
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	} // end of Connection
	
	public void Connection10(){ // radio button top selling by ESRB

		try {
			Class.forName("org.mariadb.jdbc.Driver");
		    System.out.println("databaseURL"+ databaseURL);
			Connection connect = DriverManager.getConnection(databaseURL, netID, password);
			Statement st = connect.createStatement();
			ResultSet rs = st.executeQuery("Select g.name, s.globalShipped, sc.esrbRating\r\n"
					+ "From Game g, Sales s, Score sc\r\n"
					+ "Where g.rank = s.salesRank and sc.scoreRank = g.rank\r\n"
					+ "Order By sc.esrbRating DESC\r\n"
					+ "Limit 10\r\n"
					+ "");
			
			while(table.getRowCount() > 0) {
				((DefaultTableModel)table.getModel()).removeRow(0);
			}
			int col = rs.getMetaData().getColumnCount();
			while(rs.next()) {
				Object [] rows = new Object [col];
				for(int i = 1; i <= col; i++) {
					rows[i-1] = rs.getObject(i);
				}
				((DefaultTableModel)table.getModel()).insertRow(rs.getRow() -1, rows);
			}
			rs.close();
			st.close();
		
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	} // end of Connection
	
	
	private JTable table;
	private JTextField tName;
	private JTextField tGenre;
	private JTextField tPlatform;
	private JTextField tDeveloper;
	private JTextField tRank;
	private JTextField tYear;
	
	void setColor(JButton btnNewButton) {
		btnNewButton.setBackground(new Color(135, 112, 225));
	}
	void resetColor(JPanel panel) {
		panel.setBackground(new Color(76, 41, 211));
	}
		
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize2() {
		System.out.println("Game Search run...");
		frame = new JFrame();
		frame.setForeground(new Color(204, 204, 204));
		frame.setBackground(new Color(240, 255, 255));
		frame.getContentPane().setForeground(new Color(204, 204, 204));
		frame.setBounds(100, 100, 1136, 662);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Game Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(10, 10, 452, 600);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		panel.setVisible(true);
		frame.setVisible(true);
		System.out.println("Game Search successful!");
		
		JButton btnNewButton = new JButton("Top 100 Games");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Connection11(); // method call button
				
			}
		});
		btnNewButton.setBounds(24, 22, 129, 35);
		panel.add(btnNewButton);
		
		JButton btnListOfGames = new JButton("List of Games");
		btnListOfGames.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Connection2(); // method call button
			}
		});
		btnListOfGames.setBounds(24, 59, 129, 35);
		panel.add(btnListOfGames);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setBounds(14, 127, 100, 35);
		panel.add(lblNewLabel);
		
		JLabel lblGenre = new JLabel("Genre");
		lblGenre.setBounds(14, 166, 54, 35);
		panel.add(lblGenre);
		
		JLabel lblPlatform = new JLabel("Platform");
		lblPlatform.setBounds(14, 203, 110, 35);
		panel.add(lblPlatform);
		
		JLabel lblDeveloper = new JLabel("Developer");
		lblDeveloper.setBounds(10, 240, 104, 28);
		panel.add(lblDeveloper);
		
		tName = new JTextField();
		tName.setBounds(70, 135, 118, 19);
		panel.add(tName);
		tName.setColumns(10);
		
		tGenre = new JTextField();
		tGenre.setColumns(10);
		tGenre.setBounds(70, 174, 118, 19);
		panel.add(tGenre);
		
		tPlatform = new JTextField();
		tPlatform.setColumns(10);
		tPlatform.setBounds(70, 211, 118, 19);
		panel.add(tPlatform);
		
		tDeveloper = new JTextField();
		tDeveloper.setColumns(10);
		tDeveloper.setBounds(70, 248, 118, 19);
		panel.add(tDeveloper);
		
		JButton btnNewButton_1_1 = new JButton("Find");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String rank = tRank.getText(); // get text from Game set primary key 'rank'
					
					Class.forName("org.mariadb.jdbc.Driver");
				    System.out.println("databaseURL"+ databaseURL); // establish connection 
					Connection connect = DriverManager.getConnection(databaseURL, netID, password);
					Statement st = connect.createStatement();
					ResultSet rs = st.executeQuery("select g.name, g.genre, g.platform, g.developer, g.year from Game g where g.rank= '"+rank+"';"); // SQl statement
					//int rank = Integer.parseInt(tRank.getText());
					//pstate.setInt(1, rank);
					if(rs.next() == false) {
					tName.setText(""); // setText point to check if statement has given request from rank key
					tGenre.setText("");
					tPlatform.setText("");
					tDeveloper.setText("");
					tYear.setText("");
			
					tRank.requestFocus();
				}
				else {
					tName.setText(rs.getString("Name")); // then print out based on string result from table 
					tGenre.setText(rs.getString("Genre"));
					tPlatform.setText(rs.getString("Platform"));
					tDeveloper.setText(rs.getString("Developer"));
					tYear.setText(rs.getString("Year"));
					
					while(table.getRowCount() > 0) { // not needed for statement to work 
						((DefaultTableModel)table.getModel()).removeRow(0);
					}
					int col = rs.getMetaData().getColumnCount();
					while(rs.next()) {
						Object [] rows = new Object [col]; 
						for(int i = 1; i <= col; i++) {
							rows[i-1] = rs.getObject(i);
						}
						((DefaultTableModel)table.getModel()).insertRow(rs.getRow() -1, rows);
					}
					rs.close();
					st.close();
				
				}
				}
				catch (ClassNotFoundException ex) {
					ex.printStackTrace();
				}
				catch (SQLException ex) {
					ex.printStackTrace();
				}
			} // end of Connection
			
			
				
		});
		btnNewButton_1_1.setBounds(222, 437, 85, 21);
		panel.add(btnNewButton_1_1);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(10, 380, 191, 78);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblRank = new JLabel("Rank");
		lblRank.setBounds(10, 33, 41, 16);
		panel_3.add(lblRank);
		
		tRank = new JTextField();
		tRank.setBounds(61, 33, 96, 19);
		tRank.setColumns(10);
		panel_3.add(tRank);
		
		JButton btnNewButton_1 = new JButton("Total Sales");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection3(); // method call
			}
		});
		btnNewButton_1.setBounds(155, 59, 129, 35);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Critics");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection4();// method call
			}
		});
		btnNewButton_2.setBounds(155, 22, 129, 35);
		panel.add(btnNewButton_2);
		
		JLabel lblYear = new JLabel("Year");
		lblYear.setBounds(10, 273, 100, 28);
		panel.add(lblYear);
		
		tYear = new JTextField();
		tYear.setColumns(10);
		tYear.setBounds(70, 278, 118, 19);
		panel.add(tYear);
		
		JButton btnNewButton_2_1 = new JButton("Rank");
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection5(); // method call
			}
		});
		btnNewButton_2_1.setBounds(283, 22, 129, 35);
		panel.add(btnNewButton_2_1);
		
		JButton btnNewButton_2_2 = new JButton("Sales Range");
		btnNewButton_2_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection6(); // method call
			}
		});
		btnNewButton_2_2.setBounds(283, 59, 129, 35);
		panel.add(btnNewButton_2_2);
		
		JRadioButton t2018 = new JRadioButton("Top-Selling game of 2018");
		t2018.setForeground(new Color(255, 255, 255));
		t2018.setBackground(new Color(0, 0, 255));
		t2018.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection7(); // method call
			}
		});
		t2018.setBounds(206, 134, 240, 21);
		panel.add(t2018);
		
		JRadioButton rdbtnTopdevelopeGlobalShipped = new JRadioButton("Top-Developer Highest Rating");
		rdbtnTopdevelopeGlobalShipped.setForeground(new Color(255, 255, 255));
		rdbtnTopdevelopeGlobalShipped.setBackground(new Color(0, 0, 255));
		rdbtnTopdevelopeGlobalShipped.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection8(); // method call
			}
		});
		rdbtnTopdevelopeGlobalShipped.setBounds(206, 179, 240, 21);
		panel.add(rdbtnTopdevelopeGlobalShipped);
		
		JRadioButton t2018_2 = new JRadioButton("Top-Selling Game on Platform");
		t2018_2.setBackground(new Color(0, 0, 255));
		t2018_2.setForeground(new Color(255, 255, 255));
		t2018_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection9(); // method call
			}
		});
		t2018_2.setBounds(206, 217, 240, 21);
		panel.add(t2018_2);
		
		JRadioButton t2018_3 = new JRadioButton("Top-Selling Games by ESRB");
		t2018_3.setBackground(new Color(0, 0, 255));
		t2018_3.setForeground(new Color(255, 255, 255));
		t2018_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection10(); // method call
			}
		});
		t2018_3.setBounds(206, 256, 240, 21);
		panel.add(t2018_3);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(472, 10, 607, 468);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Data Set", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2.setBounds(-6, 0, 620, 599);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 15, 600, 456);
		panel_2.add(scrollPane);
		
		table = new JTable(); // table build 
		
		table.setFillsViewportHeight(true);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane.setViewportView(table);
		table.setForeground(new Color(255, 255, 255));
		table.setBackground(new Color(0, 0, 128));
		table.setRowHeight(30);
		table.setRowHeight(100, 100);
		
		
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, "", null, null, null, null, null, null},
			},
			new String[] {"1", "2", "3", "4", "5", "6", "7", "8" // set title column
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(200); // lengths 
		table.getColumnModel().getColumn(1).setMinWidth(10);
		table.getColumnModel().getColumn(2).setMinWidth(10);
		table.getColumnModel().getColumn(3).setMinWidth(10);
		table.getColumnModel().getColumn(4).setMinWidth(10);
		table.getColumnModel().getColumn(5).setMinWidth(10);
		
		JLabel label = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/52761-video-game-icon.png")).getImage();
		label.setIcon(new ImageIcon(img));
		label.setBounds(641, 478, 310, 132);
		frame.getContentPane().add(label);
		
		JButton btnNewButton_3 = new JButton("Exit");
		btnNewButton_3.setBounds(958, 530, 145, 80);
		frame.getContentPane().add(btnNewButton_3);
		btnNewButton_3.setBackground(new Color(255, 0, 0));
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 22));
		btnNewButton_3.setForeground(new Color(0, 0, 0));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0); // exit button 
			}
		});	
	}
}