package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Classes.Bank;
import Classes.Customer;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

public class CustomerFrame extends JFrame {

	private JPanel contentPane;
	private JTextField tfId;
	private JTextField tfSSN;
	private JTextField tfName;
	private JTextField tfAddress;
	
	JLabel lblBankName;
	JLabel lblResult;
	
	Bank bank = null;

	/**
	 * Create the frame.
	 */
	public CustomerFrame(BankFrame bankFrame) {
		setTitle("Update Customer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 375, 264);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1_3 = new JLabel("Id:");
		lblNewLabel_1_3.setBounds(10, 45, 80, 14);
		contentPane.add(lblNewLabel_1_3);
		
		tfId = new JTextField();
		tfId.setColumns(10);
		tfId.setBounds(146, 43, 144, 20);
		contentPane.add(tfId);
		
		JLabel lblNewLabel_1_1_3 = new JLabel("SSN:");
		lblNewLabel_1_1_3.setBounds(10, 76, 80, 14);
		contentPane.add(lblNewLabel_1_1_3);
		
		tfSSN = new JTextField();
		tfSSN.setColumns(10);
		tfSSN.setBounds(146, 74, 144, 20);
		contentPane.add(tfSSN);
		
		tfName = new JTextField();
		tfName.setColumns(10);
		tfName.setBounds(146, 104, 144, 20);
		contentPane.add(tfName);
		
		JLabel lblNewLabel_2_3 = new JLabel("Name and Surname:");
		lblNewLabel_2_3.setBounds(10, 107, 126, 14);
		contentPane.add(lblNewLabel_2_3);
		
		JButton btnSaveCustomer = new JButton("Save");
		btnSaveCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (bank == null) {
					lblResult.setText("BankAccount could not be found!");
				} else {
					Customer customer = new Customer(Integer.parseInt(tfId.getText()), Integer.parseInt(tfSSN.getText()), tfName.getText(),tfAddress.getText());
					bank.setCustomer(customer);
					lblResult.setText("Customer updated successfully.");
				}
			}
		});
		btnSaveCustomer.setBounds(85, 194, 89, 23);
		contentPane.add(btnSaveCustomer);
		
		JLabel lblNewLabel_1_3_1 = new JLabel("Bank Account Number");
		lblNewLabel_1_3_1.setBounds(10, 11, 126, 14);
		contentPane.add(lblNewLabel_1_3_1);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(176, 224, 230));
		panel.setBounds(146, 11, 203, 20);
		contentPane.add(panel);
		panel.setLayout(null);
		
		lblBankName = new JLabel("");
		lblBankName.setBounds(30, 0, 219, 20);
		panel.add(lblBankName);
		lblBankName.setBackground(new Color(255, 255, 225));
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnClose.setBounds(201, 194, 89, 23);
		contentPane.add(btnClose);
		
		lblResult = new JLabel("");
		lblResult.setForeground(new Color(65, 105, 225));
		lblResult.setBounds(10, 165, 339, 20);
		contentPane.add(lblResult);
		lblResult.setBackground(SystemColor.info);
		
		tfAddress = new JTextField();
		tfAddress.setColumns(10);
		tfAddress.setBounds(146, 135, 144, 20);
		contentPane.add(tfAddress);
		
		JLabel lblNewLabel_2_3_1 = new JLabel("Address");
		lblNewLabel_2_3_1.setBounds(10, 137, 80, 14);
		contentPane.add(lblNewLabel_2_3_1);
	}
	
	public void setBank(Bank bank) {
		this.bank = bank;
		lblBankName.setText(bank.getaccountNum());
		tfId.setText("" + bank.getCustomer().getId());
		tfSSN.setText("" + bank.getCustomer().getSSN());
		tfName.setText("" + bank.getCustomer().getName());
		tfAddress.setText(""+bank.getCustomer().getAddress());
		lblResult.setText("");
	}
}
