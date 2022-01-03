import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableModel;
import java.awt.Font;
import java.awt.SystemColor;
import java.sql.ResultSet;
import net.proteanit.sql.DbUtils;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.awt.Color;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

public class Accounts {

	private static JFrame frame;
	private static JTextField txt1,txt2;
	private static JPasswordField txt3,txt4;
	private static JButton btn1,btn2,btn3,btn4,btn5;
	private static JTable table_1;
	private static JScrollPane scrollPane;
	private static String Account_Id;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Accounts window = new Accounts();
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
	public Accounts() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("SignUp(CRUD)");
		frame.getContentPane().setBackground(SystemColor.window);
		frame.setBounds(100, 100, 457, 378);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lbl1 = new JLabel("Full name:");
		lbl1.setBounds(10, 30, 72, 14);
		lbl1.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 13));
		frame.getContentPane().add(lbl1);
		
		JLabel lbl2 = new JLabel("Username:");
		lbl2.setBounds(10, 55, 72, 14);
		lbl2.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 13));
		frame.getContentPane().add(lbl2);
		
		JLabel lbl3 = new JLabel("Password:");
		lbl3.setBounds(10, 81, 72, 14);
		lbl3.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 13));
		frame.getContentPane().add(lbl3);
		
		JLabel lbl4 = new JLabel("Re-enter Password:");
		lbl4.setBounds(10, 106, 118, 14);
		lbl4.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 13));
		frame.getContentPane().add(lbl4);
		
		txt1 = new JTextField();
		txt1.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
			    String a = txt1.getText().trim();
			    if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			    	if(a.equals("")) {
			    		JOptionPane.showMessageDialog(frame, "fill-up fullname first!!", "Message", JOptionPane.ERROR_MESSAGE);
			    		txt2.setEnabled(false);
			    		txt1.requestFocus();
			    	}
			    	else {
			    		txt2.setEnabled(true);
			    		txt2.requestFocus();		
			    	}
			    }
			}
		});
		txt1.setBounds(85, 28, 340, 20);
		txt1.setColumns(10);
		frame.getContentPane().add(txt1);
		
		txt2 = new JTextField();
		txt2.setEnabled(false);
		txt2.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
			    String a = txt2.getText().trim();
			    if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			    	if(btn4.isEnabled() == true) {
				    	if(a.equals("")) {
				    		JOptionPane.showMessageDialog(frame, "fill-up username first!!", "Message", JOptionPane.ERROR_MESSAGE);
				    		txt2.requestFocus();
				    	}
				    	else {
				    		btn4.requestFocus();		
				    	}
			    	}
			    	else {
				    	if(a.equals("")) {
				    		JOptionPane.showMessageDialog(frame, "fill-up username first!!", "Message", JOptionPane.ERROR_MESSAGE);
				    		txt3.setEnabled(false);
				    		txt2.requestFocus();
				    	}
				    	else {
				    		txt3.setEnabled(true);
				    		txt3.requestFocus();		
				    	}
			    	}
			    }
			}
		});
		txt2.setBounds(85, 51, 340, 20);
		txt2.setColumns(10);
		frame.getContentPane().add(txt2);
		
		txt3 = new JPasswordField();
		txt3.setEnabled(false);
		txt3.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
			    String a = txt3.getText().trim();
			    if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			    	if(a.equals("")) {
			    		JOptionPane.showMessageDialog(frame, "fill-up password first!", "Message", JOptionPane.ERROR_MESSAGE);
			    		txt4.setEnabled(false);
			    		txt3.requestFocus();
			    	}
			    	else {
			    		txt4.setEnabled(true);
			    		txt4.requestFocus();		
			    	}
			    }	
			}
		});
		txt3.setBounds(85, 75, 340, 20);
		frame.getContentPane().add(txt3);
		
		txt4 = new JPasswordField();
		txt4.setEnabled(false);
		txt4.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				String password = txt3.getText().trim();
				String reEnterPassword = txt4.getText().trim();
			    if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			    	if(reEnterPassword.equals("")) {
			    		JOptionPane.showMessageDialog(frame, "fill-up re-enter password first!", "Message", JOptionPane.ERROR_MESSAGE);
			    		btn1.setEnabled(false);
			    		txt4.requestFocus();
			    	}
					else if (!(password.equals(reEnterPassword))) {
						JOptionPane.showMessageDialog(frame, "password & re-enter password do not match!", "Message", JOptionPane.ERROR_MESSAGE);
						txt4.setText(null);
						txt4.requestFocus();
					}
			    	else {
			    		btn1.setEnabled(true);
			    		btn1.requestFocus();		
			    	}
			    }
			}
		});
		txt4.setBounds(135, 102, 290, 20);
		frame.getContentPane().add(txt4);
		
		btn1 = new JButton("ADD");
		btn1.setEnabled(false);
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addAccount();
			}
		});
		btn1.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
			    if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			    	addAccount();
			    }
			}
		});
		btn1.setBounds(14, 133, 80, 28);
		btn1.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		frame.getContentPane().add(btn1);
		
		btn2 = new JButton("EDIT");
		btn2.setEnabled(false);
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				executeEdit();
			}
		});
		btn2.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
			    if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			    	executeEdit();
			    }
			}
		});
		btn2.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btn2.setBounds(96, 133, 80, 28);
		frame.getContentPane().add(btn2);
		
		btn3 = new JButton("DELETE");
		btn3.setEnabled(false);
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteAccount();
			}
		});
		btn3.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
			    if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			    	deleteAccount();
			    }
			}
		});
		btn3.setBounds(178, 133, 80, 28);
		btn3.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		frame.getContentPane().add(btn3);
		
		btn4 = new JButton("UPDATE");
		btn4.setEnabled(false);
		btn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateAccount();
			}
		});
		btn4.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
			    if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			    	updateAccount();
			    }
			}
		});
		btn4.setBounds(260, 133, 80, 28);
		btn4.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		frame.getContentPane().add(btn4);
		
		btn5 = new JButton("CLEAR");
		btn5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				executeClear();
			}
		});
		btn5.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
			    if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			    	executeClear();
			    }
			}
		});
		btn5.setBounds(342, 133, 80, 28);
		btn5.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		frame.getContentPane().add(btn5);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 173, 420, 150);
		frame.getContentPane().add(scrollPane);
		table_1 = new JTable();
		table_1.setBackground(Color.WHITE);
		scrollPane.setViewportView(table_1);
		table_1.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		
		// load data/s to JTable
		selectAllContent(1);
		table_1.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent arg0) {
				if(btn4.isEnabled() == true) {
				}
				else {
					int i = table_1.getSelectedRow();
					TableModel model=table_1.getModel();
					Account_Id = model.getValueAt(i, 0).toString();
					txt1.setText(model.getValueAt(i, 1).toString());
					txt2.setText(model.getValueAt(i, 2).toString());
					txt3.setText(model.getValueAt(i, 3).toString());
					txt4.setText(model.getValueAt(i, 3).toString());
					
					txt1.setEnabled(false);
					txt2.setEnabled(false);
					txt3.setEnabled(false);
					txt4.setEnabled(false);
					btn1.setEnabled(false);
					btn2.setEnabled(true);
					btn3.setEnabled(true);
					btn4.setEnabled(false);
					btn5.setEnabled(true);
				}
			}
		});
	}
	// load data/s to table
	private static void selectAllContent(int control) {
		try {
			Connection connection = DriverManager.getConnection
			("jdbc:mysql://localhost:3306/crud_db","root","");
			String selectall = "select Id, fullname, username, password from account_tbl";
			PreparedStatement stmt=connection.prepareStatement(selectall); 
			ResultSet rs = stmt.executeQuery();
			
			if(control==1) {
				table_1.setModel(DbUtils.resultSetToTableModel(rs));
			}
			connection.close();
		} catch(SQLException ex) {
			JOptionPane.showMessageDialog(frame, "Database Error: "+ex, "Message", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	// put action to button "Add"
	private static void addAccount() {
		String fullname = txt1.getText().trim();
		String username = txt2.getText().trim();
		String password = txt3.getText().trim();
		String reEnterPassword = txt4.getText().trim();
		
		if(fullname.equals("")) {
			JOptionPane.showMessageDialog(frame, "fill-up fullname first!", "Message", JOptionPane.ERROR_MESSAGE);
			txt1.requestFocus();
		}
		else if(username.equals("")) {
			JOptionPane.showMessageDialog(frame, "fill-up username first!", "Message", JOptionPane.ERROR_MESSAGE);
			txt2.requestFocus();
		}
		else if(password.equals("")) {
			JOptionPane.showMessageDialog(frame, "fill-up password first!", "Message", JOptionPane.ERROR_MESSAGE);
			txt3.requestFocus();
		}
		else if(reEnterPassword.equals("")) {
			JOptionPane.showMessageDialog(frame, "fill-up re-enter password first!", "Message", JOptionPane.ERROR_MESSAGE);
			txt4.requestFocus();
		}
		else if (!(password.equals(reEnterPassword))) {
			JOptionPane.showMessageDialog(frame, "password & re-enter password do not match!", "Message", JOptionPane.ERROR_MESSAGE);
			txt4.setText(null);
			txt4.requestFocus();
		}
		else {
			password = (MD5(password));
			try {
				Connection connection = DriverManager.getConnection
				("jdbc:mysql://localhost:3306/crud_db","root","");
				int n=JOptionPane.showConfirmDialog(null,"Are you sure you want to create this account?","Create Account",JOptionPane.YES_NO_OPTION);
					
				if(n == JOptionPane.YES_OPTION) {
					String add = "insert into account_tbl(fullname,username,password)values(?,?,?)";
					PreparedStatement stmt=connection.prepareStatement(add);
					stmt.setString(1, fullname);
					stmt.setString(2, username);
					stmt.setString(3, password);
					stmt.executeUpdate();
					JOptionPane.showMessageDialog(frame, "Account has been successfully created", "Message", JOptionPane.INFORMATION_MESSAGE);
					executeClear();
				}
				connection.close();
			} catch(SQLException ex) {
				JOptionPane.showMessageDialog(frame, "Database Error: "+ex, "Message", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
	
	// put action to button "Edit"
	private static void executeEdit() {
		txt1.setEnabled(true);
		btn1.setEnabled(false);
		btn2.setEnabled(false);
		btn3.setEnabled(false);
		btn4.setEnabled(true);
		btn5.setEnabled(false);
		table_1.setEnabled(false);
		txt1.requestFocus();
	}
	
	// put action to button "Delete"
	private static void deleteAccount() {
		try
		{
			Connection connection = DriverManager.getConnection
			("jdbc:mysql://localhost:3306/crud_db","root","");
			int n=JOptionPane.showConfirmDialog(null,"Are you sure you want to delete this account?","Remove Account",JOptionPane.YES_NO_OPTION);
				
			if(n == JOptionPane.YES_OPTION) {
				String delete = "delete from account_tbl where Id=?";
				PreparedStatement stmt=connection.prepareStatement(delete);
				stmt.setString(1, Account_Id);
				stmt.executeUpdate();
				JOptionPane.showMessageDialog(frame, "Account has been succesfully removed", "Message", JOptionPane.INFORMATION_MESSAGE);
				executeClear();
			}
			connection.close();
		} catch(SQLException ex) {
			JOptionPane.showMessageDialog(frame, "Database Error: "+ex, "Message", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	// put action to button "Update"
	private static void updateAccount() {
		String fullname = txt1.getText().trim();
		String username = txt2.getText().trim();
		
		if(fullname.equals("")) {
			JOptionPane.showMessageDialog(frame, "fill-up fullname first!", "Message", JOptionPane.ERROR_MESSAGE);
			txt1.requestFocus();
		}
		else if(username.equals("")) {
			JOptionPane.showMessageDialog(frame, "fill-up username first!", "Message", JOptionPane.ERROR_MESSAGE);
			txt2.requestFocus();
		}
		else {
			try
			{
				Connection connection = DriverManager.getConnection
				("jdbc:mysql://localhost:3306/crud_db","root","");
				int n=JOptionPane.showConfirmDialog(null,"Are you sure you want to save changes?","Update Account?",JOptionPane.YES_NO_OPTION);
					
				if(n == JOptionPane.YES_OPTION) {
					String edit = "update account_tbl set fullname=?, username=? where Id=?";
					PreparedStatement stmt=connection.prepareStatement(edit);
					stmt.setString(1, fullname);
					stmt.setString(2, username);
					stmt.setString(3, Account_Id);
					stmt.executeUpdate();
					JOptionPane.showMessageDialog(frame, "Account info. has been succesfully updated", "Message", JOptionPane.INFORMATION_MESSAGE);
					executeClear();
				}
				connection.close();
			} catch(SQLException ex) {
				JOptionPane.showMessageDialog(frame, "Database Error: "+ex, "Message", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
	
	// put action to button "Clear"
	private static void executeClear(){
		txt1.setText(null);
		txt2.setText(null);
		txt3.setText(null);
		txt4.setText(null);
		
		txt1.setEnabled(true);
		txt2.setEnabled(false);
		txt3.setEnabled(false);
		txt4.setEnabled(false);
		btn1.setEnabled(false);
		btn2.setEnabled(false);
		btn3.setEnabled(false);
		btn4.setEnabled(false);
		btn5.setEnabled(true);
		selectAllContent(1);
		table_1.setEnabled(true);
		txt1.requestFocus();
	}
	
	// hashing
	protected static String MD5 (String password) {
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.update(password.getBytes());
			byte[] resultByteArray =  messageDigest.digest();
			StringBuilder sb = new StringBuilder();
			for(byte b : resultByteArray) {
				sb.append(String.format("%02x", b));
			}
			
			return sb.toString();
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}
	
}