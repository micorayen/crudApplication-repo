import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login extends JFrame {

	private static JPanel contentPane;
	private static JTextField txt1;
	private static JPasswordField txt2;
	private static JButton btn1, btn2;
	private static Login frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 294, 171);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.window);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("USERNAME");
		lblNewLabel.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 11));
		lblNewLabel.setBounds(10, 11, 67, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblPassword = new JLabel("PASSWORD");
		lblPassword.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 11));
		lblPassword.setBounds(10, 52, 67, 14);
		contentPane.add(lblPassword);
		
		txt1 = new JTextField();
		txt1.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
			    String a = txt1.getText().trim();
			    if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			    	if(a.equals("")) {
			    		JOptionPane.showMessageDialog(contentPane, "fill-up username first!", "Message", JOptionPane.ERROR_MESSAGE);
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
		txt1.setBounds(9, 26, 257, 21);
		contentPane.add(txt1);
		txt1.setColumns(10);
		
		txt2 = new JPasswordField();
		txt2.setEnabled(false);
		txt2.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
			    String a = txt2.getText().trim();
			    if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			    	if(a.equals("")) {
			    		JOptionPane.showMessageDialog(contentPane, "fill-up password first!", "Message", JOptionPane.ERROR_MESSAGE);
			    		btn1.setEnabled(false);
			    		txt2.requestFocus();
			    	}
			    	else {
			    		btn1.setEnabled(true);
			    		btn1.requestFocus();		
			    	}
			    }
			}
		});
		txt2.setBounds(9, 67, 257, 21);
		contentPane.add(txt2);
		
		btn1 = new JButton("LOGIN");
		btn1.setEnabled(false);
		btn1.setBackground(SystemColor.controlHighlight);
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login();
			}
		});
		btn1.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				login();
			}
		});
		btn1.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btn1.setBounds(9, 94, 150, 28);
		contentPane.add(btn1);
		
		btn2 = new JButton("CANCEL");
		btn2.setBackground(SystemColor.controlHighlight);
		btn2.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btn2.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				dispose();
			}
		});
		btn2.setBounds(161, 94, 106, 28);
		contentPane.add(btn2);
	}
	
	// put action to button "Login"
	private void login() {
		String username = txt1.getText().trim();
		String password = txt2.getText().trim();
		
		if(username.equals("")) {
			JOptionPane.showMessageDialog(contentPane, "fill-up username first!", "Message", JOptionPane.ERROR_MESSAGE);
			txt1.requestFocus();
		}
		else if(password.equals("")) {
			JOptionPane.showMessageDialog(contentPane, "fill-up password first!", "Message", JOptionPane.ERROR_MESSAGE);
			txt2.requestFocus();
		} else {
			password = (Accounts.MD5(password));
			try {
				Connection connect = DriverManager.getConnection
				("jdbc:mysql://localhost:3306/crud_db","root","");
				String query = "select * from account_tbl where username=? && password=?";
				PreparedStatement rayen=connect.prepareStatement(query); 
				rayen.setString(1, username);
				rayen.setString(2, password);
				
				ResultSet rs = rayen.executeQuery();
				rs.last();
				
				int check = rs.getRow();
				if(check>0) {
						Accounts.main(null);
						frame.dispose();
				}
				else {
					JOptionPane.showMessageDialog(contentPane, "username & password did not match", "Message", JOptionPane.INFORMATION_MESSAGE);
				}
				connect.close();		
			} catch(SQLException ex) {
				JOptionPane.showMessageDialog(contentPane, "Database Error: "+ex, "Message", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
}
