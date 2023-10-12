package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.TitledBorder;

import Classes.Bank;
import Classes.BankSys;

import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

public class BankFrame extends JFrame {

	private JPanel contentPane;
	private JTextField tfTransAcc;
	private JTextField tfTransDate;
	private JTextField tfTransTarget;
	private JTextField tfTransMoney;
	private JTextField tfDepositAcc;
	private JTextField tfDepositDate;
	private JTextField tfDepositTarget;
	private JTextField tfDepositMoney;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	private JComboBox cbBankName;
	
	private CustomerFrame customerFrame = new CustomerFrame(this);
	
	/**
	 * Create the frame.
	 */
	public BankFrame() {
		setTitle("Bank");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 780, 580);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 294, 744, 236);
		contentPane.add(scrollPane);
		
		JTextArea taResult = new JTextArea();
		scrollPane.setViewportView(taResult);
		
		JButton btnRead = new JButton("Read Bank information from File");
		btnRead.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (BankSys.readFromFile("bankInfo.txt")) {
					taResult.setText("File read successful.");
					fillBankNameComboBox();
				} else {
					taResult.setText("An exception occurred during file read!");
				}
			}
		});
		btnRead.setBounds(10, 11, 291, 23);
		contentPane.add(btnRead);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Display Bank", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(420, 168, 334, 115);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Type:");
		lblNewLabel.setBounds(10, 27, 144, 14);
		panel.add(lblNewLabel);
		
		JComboBox cbType = new JComboBox();
		cbType.setBounds(144, 23, 60, 22);
		panel.add(cbType);
		cbType.setModel(new DefaultComboBoxModel(new String[] {"A", "T", "D"}));
		
		JButton btnDisplay = new JButton("Display");
		btnDisplay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String result = BankSys.getBankList(cbType.getSelectedItem().toString());
				taResult.setText(result);
			}
		});
		btnDisplay.setBounds(214, 23, 110, 23);
		panel.add(btnDisplay);
		
		JLabel lblNewLabel_5 = new JLabel("(A: all, T: Transfer, D: Deposit)");
		lblNewLabel_5.setBounds(10, 52, 314, 14);
		panel.add(lblNewLabel_5);
		
		JButton btnWrite = new JButton("Write Bank Information to File");
		btnWrite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (BankSys.writeToFile("updatedBankInfo.txt")) {
					taResult.setText("File write successful.");
				} else {
					taResult.setText("An exception occurred during file write!");
				}
			}
		});
		btnWrite.setBounds(420, 11, 264, 23);
		contentPane.add(btnWrite);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_2.setBounds(10, 45, 400, 238);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("Transaction Type to Add:");
		lblNewLabel_3.setBounds(10, 11, 153, 14);
		panel_2.add(lblNewLabel_3);
		
		JPanel panelTransfer = new JPanel();
		panelTransfer.setBounds(10, 36, 375, 160);
		panel_2.add(panelTransfer);
		panelTransfer.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Add Transfer", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelTransfer.setLayout(null);
		
		JPanel panelDeposit = new JPanel();
		panelDeposit.setBounds(10, 36, 375, 160);
		panel_2.add(panelDeposit);
		panelDeposit.setLayout(null);
		panelDeposit.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Add Deposit", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		panelDeposit.setVisible(false);
		
		JRadioButton rdbtnTransfer = new JRadioButton("Transfer");
		rdbtnTransfer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnTransfer.isSelected()) {
					panelTransfer.setVisible(true);
					panelDeposit.setVisible(false);
				}
			}
		});
		buttonGroup.add(rdbtnTransfer);
		rdbtnTransfer.setSelected(true);
		rdbtnTransfer.setBounds(169, 7, 89, 23);
		panel_2.add(rdbtnTransfer);
		
		JRadioButton rdbtnDeposit = new JRadioButton("Deposit");
		rdbtnDeposit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnDeposit.isSelected()) {
					panelTransfer.setVisible(false);
					panelDeposit.setVisible(true);
				}
			}
		});
		buttonGroup.add(rdbtnDeposit);
		rdbtnDeposit.setBounds(260, 7, 70, 23);
		panel_2.add(rdbtnDeposit);
		
		JLabel lblNewLabel_1 = new JLabel("Account Number:");
		lblNewLabel_1.setBounds(10, 28, 185, 14);
		panelTransfer.add(lblNewLabel_1);
		
		tfTransAcc = new JTextField();
		tfTransAcc.setBounds(205, 25, 150, 20);
		panelTransfer.add(tfTransAcc);
		tfTransAcc.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Date:");
		lblNewLabel_1_1.setBounds(10, 57, 185, 14);
		panelTransfer.add(lblNewLabel_1_1);
		
		tfTransDate = new JTextField();
		tfTransDate.setBounds(205, 54, 150, 20);
		panelTransfer.add(tfTransDate);
		tfTransDate.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Reciever Account Number:");
		lblNewLabel_2.setBounds(10, 81, 185, 14);
		panelTransfer.add(lblNewLabel_2);
		
		tfTransTarget = new JTextField();
		tfTransTarget.setColumns(10);
		tfTransTarget.setBounds(205, 84, 150, 20);
		panelTransfer.add(tfTransTarget);
		
		JLabel lblNewLabel_2_1 = new JLabel("Amount of Transfer:");
		lblNewLabel_2_1.setBounds(10, 115, 185, 14);
		panelTransfer.add(lblNewLabel_2_1);
		
		tfTransMoney = new JTextField();
		tfTransMoney.setColumns(10);
		tfTransMoney.setBounds(205, 114, 150, 20);
		panelTransfer.add(tfTransMoney);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("");
		lblNewLabel_1_1_1.setBounds(10, 77, 185, 14);
		panelTransfer.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Account Number:");
		lblNewLabel_1_2.setBounds(10, 28, 185, 14);
		panelDeposit.add(lblNewLabel_1_2);
		
		tfDepositAcc = new JTextField();
		tfDepositAcc.setColumns(10);
		tfDepositAcc.setBounds(205, 25, 150, 20);
		panelDeposit.add(tfDepositAcc);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Date:");
		lblNewLabel_1_1_2.setBounds(10, 57, 185, 14);
		panelDeposit.add(lblNewLabel_1_1_2);
		
		tfDepositDate = new JTextField();
		tfDepositDate.setColumns(10);
		tfDepositDate.setBounds(205, 54, 150, 20);
		panelDeposit.add(tfDepositDate);
		
		
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Amount of Deposit:");
		lblNewLabel_2_1_1.setBounds(10, 88, 185, 14);
		panelDeposit.add(lblNewLabel_2_1_1);
		
		tfDepositTarget = new JTextField();
		tfDepositTarget.setColumns(10);
		tfDepositTarget.setBounds(205, 85, 150, 20);
		panelDeposit.add(tfDepositTarget);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("");
		lblNewLabel_1_1_1_1.setBounds(10, 77, 185, 14);
		panelDeposit.add(lblNewLabel_1_1_1_1);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(215, 204, 89, 23);
		panel_2.add(btnSave);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String accnum, date, target, money, type;
				
				if (rdbtnTransfer.isSelected()) {
					type = "T";
					accnum = tfTransAcc.getText();
					date = tfTransDate.getText();
					target = tfTransTarget.getText();
					money = tfTransMoney.getText();
					
					if (accnum.isEmpty() || date.isEmpty() || target.isEmpty() || money.isEmpty()) {
						taResult.setText("All fields are required for transfer!");
					} else {
						if (BankSys.addBank(type, accnum, date, target, money)) {
							taResult.setText("Transfer added successfully.");
							clear();
							fillBankNameComboBox();
						} else {
							taResult.setText("Transfer could not be added because bank with the same name exists!");
						}
					}
				} else {
					type = "D";
					accnum = tfDepositAcc.getText();
					date = tfDepositDate.getText();
					target = tfDepositTarget.getText();
					money = tfDepositTarget.getText();
					
					if (accnum.isEmpty() || date.isEmpty() || target.isEmpty() || money.isEmpty()) {
						taResult.setText("All fields are required for deposit!");
					} else {
						if (BankSys.addBank(type, accnum, date, target, money)) {
							taResult.setText("Deposit added successfully.");
							clear();
							fillBankNameComboBox();
						} else {
							taResult.setText("Deposit could not be added because bank with the same name exists!");
						}
					}
				}
			}
		});
		
		JPanel panelCustomer = new JPanel();
		panelCustomer.setBounds(420, 45, 334, 121);
		contentPane.add(panelCustomer);
		panelCustomer.setLayout(null);
		panelCustomer.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Update Customer", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		JLabel lblNewLabel_4 = new JLabel("Account Number:");
		lblNewLabel_4.setBounds(10, 29, 105, 13);
		panelCustomer.add(lblNewLabel_4);
		
		cbBankName = new JComboBox();
		cbBankName.setBounds(113, 25, 211, 22);
		panelCustomer.add(cbBankName);
		
		JButton btnUpdateCustomer = new JButton("Update");
		btnUpdateCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Bank bank = BankSys.findBank(cbBankName.getSelectedItem().toString());
				customerFrame.setBank(bank);
				customerFrame.setVisible(true);
			}
		});
		btnUpdateCustomer.setBounds(20, 57, 89, 23);
		panelCustomer.add(btnUpdateCustomer);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Bank bank = BankSys.findBank(cbBankName.getSelectedItem().toString());
				taResult.setText(bank.toString());
				
			}
		});
		btnSearch.setBounds(123, 57, 89, 23);
		panelCustomer.add(btnSearch);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BankSys.DeleteBank(cbBankName.getSelectedItem().toString());
				taResult.setText("Number of "+cbBankName.getSelectedItem()+" account is deleted");
				
				 int selectedIndex = cbBankName.getSelectedIndex();
			        if (selectedIndex != -1) {
			            cbBankName.removeItemAt(selectedIndex);
			        }
			}
		});
		btnDelete.setBounds(222, 57, 89, 23);
		panelCustomer.add(btnDelete);
	}
	
	public void clear() {
		tfTransAcc.setText("");
		tfTransDate.setText("");
		tfTransTarget.setText("");
		tfTransMoney.setText("");
		tfDepositAcc.setText("");
		tfDepositDate.setText("");
		tfDepositTarget.setText("");
		tfDepositTarget.setText("");
	}
	
	public void fillBankNameComboBox() {
        cbBankName.setModel(new DefaultComboBoxModel(BankSys.getaccountNums()));
    }
}
