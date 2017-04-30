package ex3g;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class PayrollForm extends JFrame {

	private JPanel contentPane;
	private JTextField addHoursText;
	private JLabel totalHoursLabel;
	private JLabel grossPayLabel;
	private DefaultListModel employeeListModel;
	private JList employeeList;
	private double totalHours = 0.0;
	private JTextField empIDTextField;
	private JTextField empNameTextField;
	private JTextField payRateTextField;
	private JButton addHoursButton;
	private JButton clearHoursButton;
	private JButton updateButton;
	private PayrollObjMapper payrollObjMapper;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PayrollForm frame = new PayrollForm();
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
	public PayrollForm() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				do_this_windowClosing(arg0);
			}
		});
		setTitle("SJILK_Ex3G1 Payroll");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 481, 536);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSelectEmployee = new JLabel("Select Employee:");
		lblSelectEmployee.setBounds(12, 13, 118, 16);
		contentPane.add(lblSelectEmployee);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(22, 42, 423, 146);
		contentPane.add(scrollPane);
		
//		JList employeeList = new JList();
//		employeeListModel = new DefaultListModel();
//		employeeListModel.addElement(new Payroll(101, "Sarah Jilk", 10.0));
//		employeeListModel.addElement(new Payroll(102, "Patti Weigand", 20.0));
//		employeeListModel.addElement(new Payroll(103, "Lyle Stelter", 30.0));
//		employeeListModel.addElement(new Payroll(104, "Neva Burdick", 40.0));
//		employeeListModel.addElement(new Payroll(105, "Lisa Laing", 50.0));
		payrollObjMapper = new PayrollObjMapper("Exercise3G.txt");
		employeeListModel = payrollObjMapper.getAllPayroll();
		
		employeeList = new JList(employeeListModel);
		employeeList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				do_employeeList_mouseClicked(arg0);
			}
		});
		employeeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(employeeList);
		
		JLabel lblNewLabel = new JLabel("Employee ID (>100):");
		lblNewLabel.setBounds(22, 251, 120, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblEmployeeName = new JLabel("Employee Name:");
		lblEmployeeName.setBounds(22, 280, 108, 16);
		contentPane.add(lblEmployeeName);
		
		JLabel lblPayRate = new JLabel("Pay Rate (7.25 - 100):");
		lblPayRate.setBounds(22, 309, 142, 16);
		contentPane.add(lblPayRate);
		
		JLabel lblEnterHours = new JLabel("Enter Hours (0.1 - 20.0):");
		lblEnterHours.setBounds(22, 338, 142, 16);
		contentPane.add(lblEnterHours);
		
		JLabel lblTotalHours = new JLabel("Total Hours:");
		lblTotalHours.setBounds(22, 367, 91, 16);
		contentPane.add(lblTotalHours);
		
		JLabel lblGrossPay = new JLabel("Gross Pay:");
		lblGrossPay.setBounds(22, 392, 91, 16);
		contentPane.add(lblGrossPay);
		
		grossPayLabel = new JLabel("$0.00");
		grossPayLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		grossPayLabel.setBounds(250, 392, 56, 16);
		contentPane.add(grossPayLabel);
		
		totalHoursLabel = new JLabel("0.00");
		totalHoursLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		totalHoursLabel.setBounds(250, 367, 56, 16);
		contentPane.add(totalHoursLabel);
		
		addHoursText = new JTextField();
		addHoursText.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				do_addHoursText_focusGained(arg0);
			}
		});
		addHoursText.setHorizontalAlignment(SwingConstants.RIGHT);
		addHoursText.setText("0.10");
		addHoursText.setBounds(236, 335, 70, 22);
		contentPane.add(addHoursText);
		addHoursText.setColumns(10);
		
		JButton clearFormButton = new JButton("Clear Form");
		clearFormButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_clearFormButton_actionPerformed(e);
			}
		});
		clearFormButton.setBounds(249, 444, 97, 25);
		contentPane.add(clearFormButton);
		
		addHoursButton = new JButton("+");
		addHoursButton.setEnabled(false);
		addHoursButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				do_addHoursButton_actionPerformed(arg0);
			}
		});
		addHoursButton.setBounds(316, 334, 41, 25);
		contentPane.add(addHoursButton);
		
		clearHoursButton = new JButton("Clear");
		clearHoursButton.setEnabled(false);
		clearHoursButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				do_clearHoursButton_actionPerformed(arg0);
			}
		});
		clearHoursButton.setBounds(360, 334, 85, 25);
		contentPane.add(clearHoursButton);
		
		empIDTextField = new JTextField();
		empIDTextField.setText("000");
		empIDTextField.setBounds(236, 248, 70, 22);
		contentPane.add(empIDTextField);
		empIDTextField.setColumns(10);
		
		empNameTextField = new JTextField();
		empNameTextField.setBounds(164, 277, 142, 22);
		contentPane.add(empNameTextField);
		empNameTextField.setColumns(10);
		
		payRateTextField = new JTextField();
		payRateTextField.setText("7.25");
		payRateTextField.setBounds(236, 306, 70, 22);
		contentPane.add(payRateTextField);
		payRateTextField.setColumns(10);
		
		updateButton = new JButton("Update");
		updateButton.setEnabled(false);
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				do_updateButton_actionPerformed(arg0);
			}
		});
		updateButton.setBounds(86, 444, 97, 25);
		contentPane.add(updateButton);
	}
	protected void do_employeeList_mouseClicked(MouseEvent arg0) {
		Payroll item = (Payroll) employeeList.getSelectedValue();
		this.empIDTextField.setText(Integer.toString(item.getId()));
		this.empNameTextField.setText(item.getName());
		this.payRateTextField.setText(Double.toString(item.getPayRate()));
		
		DecimalFormat grossPayFmt = new DecimalFormat ("$#,###.00");
		this.grossPayLabel.setText(grossPayFmt.format(item.calcGrossPay()));
		DecimalFormat hoursFmt = new DecimalFormat("####.00");
		this.totalHoursLabel.setText(hoursFmt.format(item.getHours()));
		this.addHoursButton.setEnabled(true);
		this.clearHoursButton.setEnabled(true);
		this.updateButton.setEnabled(true);
		
	}
	
	protected void do_addHoursButton_actionPerformed(ActionEvent arg0) {
		Payroll payroll = (Payroll) employeeList.getSelectedValue();
		double hours = Double.parseDouble(addHoursText.getText());
		if (!payroll.addHours(hours)) {
			JOptionPane.showMessageDialog(null, "Invalid hours. \nMust be between 0.1 and 20");
			addHoursText.setText("0.10");
			addHoursText.requestFocus();
		}
		else {
		
			DecimalFormat hoursFmt = new DecimalFormat("####.00");
			payroll.addHours(hours);
			this.totalHoursLabel.setText(hoursFmt.format(payroll.getHours()));
			
			DecimalFormat grossPayFmt = new DecimalFormat ("$#,###.00");
			payroll.calcGrossPay();
			this.grossPayLabel.setText(grossPayFmt.format(payroll.calcGrossPay()));
			this.addHoursText.setText("0.00");
			this.addHoursText.requestFocus();
			this.addHoursText.selectAll();
		}
	
	}
	

	protected void do_clearHoursButton_actionPerformed(ActionEvent arg0) {
		Payroll payroll = (Payroll) employeeList.getSelectedValue();
		double totalHours = 0.00;
		
		payroll.setHours(0.00);
			
		this.totalHoursLabel.setText("0.00");
		this.grossPayLabel.setText("$0.00");
		this.addHoursText.setText("0.00");
		this.addHoursText.requestFocus();	
	
	}
	
	protected void do_clearFormButton_actionPerformed(ActionEvent e) {
		
		this.employeeList.clearSelection();
		this.empIDTextField.setText("0");
		this.empNameTextField.setText("");
		this.payRateTextField.setText("0.00");
		
		this.totalHoursLabel.setText("0.00");
		this.grossPayLabel.setText("$0.00");
		this.addHoursText.setText("0.00");
		
		this.addHoursText.requestFocus();
		this.addHoursButton.setEnabled(false);
		this.clearHoursButton.setEnabled(false);
		this.updateButton.setEnabled(false);
			
	}
	protected void do_addHoursText_focusGained(FocusEvent arg0) {
		this.addHoursText.selectAll();
	}
	
	protected void do_updateButton_actionPerformed(ActionEvent arg0) {
		int id = Integer.parseInt(empIDTextField.getText());
		double payRate = Double.parseDouble(payRateTextField.getText());
		String name = empNameTextField.getText();
		Payroll payroll = (Payroll) employeeList.getSelectedValue();
		if (!payroll.setId(id)) {
			JOptionPane.showMessageDialog(null, "Invalid employee ID. \nMust be > 100");
			empIDTextField.setText("101");
			empIDTextField.requestFocus();
		}
		else if (payroll.setPayRate(payRate) == false) {
			JOptionPane.showMessageDialog(null, "Invalid Pay Rate. \nMust be >= 7.25 and <= 100.00");
			payRateTextField.setText("7.25");
			payRateTextField.requestFocus();
		}
		else if (!payroll.setName(name)) {
			JOptionPane.showMessageDialog(null, "Invalid Name. \nName is required");
			empNameTextField.requestFocus();
		}
	
		employeeList.repaint();
	
	}


	protected void do_this_windowClosing(WindowEvent arg0) {
		if (payrollObjMapper != null) 
			payrollObjMapper.writeAllPayroll(employeeListModel);
	}
}
