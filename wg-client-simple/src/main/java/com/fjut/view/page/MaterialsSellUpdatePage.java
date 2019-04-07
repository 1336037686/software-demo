package com.fjut.view.page;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

@SuppressWarnings("all")
public class MaterialsSellUpdatePage extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField nameField;
	private JTextField sumField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			MaterialsSellUpdatePage dialog = new MaterialsSellUpdatePage(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public MaterialsSellUpdatePage(Object[] target) {
		setBounds(100, 100, 362, 244);
		setModal(true);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel label = new JLabel("修改");
		label.setFont(new Font("宋体", Font.PLAIN, 20));
		label.setBounds(142, 10, 54, 29);
		contentPanel.add(label);
		
		JLabel label_1 = new JLabel("名称");
		label_1.setBounds(66, 62, 54, 15);
		contentPanel.add(label_1);
		
		nameField = new JTextField();
		nameField.setEnabled(false);
		nameField.setBounds(107, 59, 167, 21);
		contentPanel.add(nameField);
		nameField.setColumns(10);
		
		JLabel label_2 = new JLabel("数量");
		label_2.setBounds(66, 115, 54, 15);
		contentPanel.add(label_2);
		
		sumField = new JTextField();
		sumField.setColumns(10);
		sumField.setBounds(107, 112, 167, 21);
		contentPanel.add(sumField);
		
		JButton submitBtn = new JButton("修改");
		submitBtn.setBounds(133, 161, 93, 23);
		contentPanel.add(submitBtn);
		
		/*
		 * 		result[i][1] = list.get(i).getMaterialsName();
		 *		result[i][2] = list.get(i).getTotal();
		 *		result[i][3] = list.get(i).getId();
		 */
		
		nameField.setText(target[1].toString());
		sumField.setText(target[2].toString());

	}
}
