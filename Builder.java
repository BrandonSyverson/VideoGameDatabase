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
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import java.util.*;
import java.util.logging.Logger;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JScrollPane;
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

public class Builder {

	static final String databasePrefix ="cs366-2231_syversonbl27";
    static final String netID ="syversonbl27"; // Please enter your netId
    static final String hostName ="washington.uww.edu"; //140.146.23.39 or washington.uww.edu
    static final String databaseURL ="jdbc:mariadb://"+hostName+"/"+databasePrefix;
    static final String password="bs2719"; // please enter your own password
    private Connection connection = null;
	private Statement statement = null;
	private ResultSet resultSet = null;
	
	
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Builder window = new Builder();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Builder() {
		initialize();
		
		
	}
	

	ResultSet rs;
	Connection con;
	PreparedStatement pstate;
	
		
	
	public void Connection(){

		try {
			Class.forName("org.mariadb.jdbc.Driver");
		    System.out.println("databaseURL"+ databaseURL);
			Connection connect = DriverManager.getConnection(databaseURL, netID, password);
			Statement st = connect.createStatement();
			ResultSet rs = st.executeQuery("select g.rank, g.name from Game g where rank <= 100");
			
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
	
	public void Connection2(){

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
	
	public void Connection3(){

		try {
			Class.forName("org.mariadb.jdbc.Driver");
		    System.out.println("databaseURL"+ databaseURL);
			Connection connect = DriverManager.getConnection(databaseURL, netID, password);
			Statement st = connect.createStatement();
			ResultSet rs = st.executeQuery("");
			
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
	
	void setColor(JButton btnNewButton) {
		btnNewButton.setBackground(new Color(135, 112, 225));
	}
	void resetColor(JPanel panel) {
		panel.setBackground(new Color(76, 41, 211));
	}
		
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBackground(new Color(240, 255, 255));
		frame.getContentPane().setForeground(new Color(138, 43, 226));
		frame.setBounds(100, 100, 1117, 536);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(10, 10, 452, 468);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("Top 100 Games");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Connection();
				
			}
		});
		btnNewButton.setBounds(24, 22, 129, 35);
		panel.add(btnNewButton);
		
		JButton btnListOfGames = new JButton("List of Games");
		btnListOfGames.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Connection2();
			}
		});
		btnListOfGames.setBounds(24, 59, 129, 35);
		panel.add(btnListOfGames);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setBounds(24, 127, 45, 35);
		panel.add(lblNewLabel);
		
		JLabel lblGenre = new JLabel("Genre");
		lblGenre.setBounds(24, 162, 45, 35);
		panel.add(lblGenre);
		
		JLabel lblPlatform = new JLabel("Platform");
		lblPlatform.setBounds(24, 203, 45, 35);
		panel.add(lblPlatform);
		
		JLabel lblDeveloper = new JLabel("Developer");
		lblDeveloper.setBounds(24, 248, 45, 35);
		panel.add(lblDeveloper);
		
		tName = new JTextField();
		tName.setBounds(79, 135, 96, 19);
		panel.add(tName);
		tName.setColumns(10);
		
		tGenre = new JTextField();
		tGenre.setColumns(10);
		tGenre.setBounds(79, 170, 96, 19);
		panel.add(tGenre);
		
		tPlatform = new JTextField();
		tPlatform.setColumns(10);
		tPlatform.setBounds(79, 211, 96, 19);
		panel.add(tPlatform);
		
		tDeveloper = new JTextField();
		tDeveloper.setColumns(10);
		tDeveloper.setBounds(79, 256, 96, 19);
		panel.add(tDeveloper);
		
		JButton btnNewButton_1_1 = new JButton("Find");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String rank = tRank.getText();
					
					Class.forName("org.mariadb.jdbc.Driver");
				    System.out.println("databaseURL"+ databaseURL);
					Connection connect = DriverManager.getConnection(databaseURL, netID, password);
					Statement st = connect.createStatement();
					ResultSet rs = st.executeQuery("select g.name, g.genre, g.platform, g.developer from Game g where g.rank= '"+rank+"';");
					//int rank = Integer.parseInt(tRank.getText());
					//pstate.setInt(1, rank);
					if(rs.next() == false) {
					
					tGenre.setText("");
					tPlatform.setText("");
					tDeveloper.setText("");
					tRank.requestFocus();
				}
				else {
					tName.setText(rs.getString("Name"));
					tGenre.setText(rs.getString("Genre"));
					tPlatform.setText(rs.getString("Platform"));
					tDeveloper.setText(rs.getString("Developer"));
					
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
				}
				catch (ClassNotFoundException ex) {
					ex.printStackTrace();
				}
				catch (SQLException ex) {
					ex.printStackTrace();
				}
			} // end of Connection
				
//				try {
//					pstate = con.prepareStatement("select g.name, g.genre, g.platform, g.developer from Game g where g.rank =?");
//					int rank = Integer.parseInt(tRank.getText());
			
//					ResultSet rs = pstate.executeQuery();
//					if(rs.next() == false) {
//						JOptionPane.showMessageDialog(btnNewButton_1_1, this, "Record was not found", rank);
//						tName.setText("");
//						tGenre.setText("");
//						tPlatform.setText("");
//						tDeveloper.setText("");
//						tRank.requestFocus();
//					}
//					else {
//						tName.setText(rs.getString("Name"));
//						tGenre.setText(rs.getString("Genre"));
//						tPlatform.setText(rs.getString("Platform"));
//						tDeveloper.setText(rs.getString("Developer"));
//					}
//					
//				}
//				
//				catch (SQLException ex) {
//					ex.printStackTrace();
//				}
//			}
		});
		btnNewButton_1_1.setBounds(222, 437, 85, 21);
		panel.add(btnNewButton_1_1);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(10, 380, 191, 78);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblRank = new JLabel("Rank");
		lblRank.setBounds(10, 36, 27, 13);
		panel_3.add(lblRank);
		
		tRank = new JTextField();
		tRank.setBounds(61, 33, 96, 19);
		tRank.setColumns(10);
		panel_3.add(tRank);
		
		JButton btnNewButton_1 = new JButton("Rank(list)");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection3();
			}
		});
		btnNewButton_1.setBounds(155, 59, 129, 35);
		panel.add(btnNewButton_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(472, 10, 607, 468);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "JPanel title", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(-6, -15, 620, 489);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 15, 600, 468);
		panel_2.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setForeground(new Color(0, 0, 0));
		table.setBackground(new Color(32, 178, 170));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Rank", "Name", "Genre", "ESRB Rating", "Platform", "Developer", "Critic Score", "Total Shipped"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
	}
}
