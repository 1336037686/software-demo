package com.fjut.view.page;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextArea;

/**
 * 备注显示界面
 * @author LGX
 *
 */
public class RemarksPage extends JDialog {
	
	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RemarksPage dialog = new RemarksPage("xxxxxx\n\n\n\n\n\n\nsdsdsd\n\n\n\n\n\n\nfsdfsdfsd");
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RemarksPage(String remarks) {
		setBounds(100, 100, 450, 300);
		setModal(true);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel label = new JLabel("备注");
		label.setFont(new Font("宋体", Font.PLAIN, 18));
		label.setBounds(193, 10, 54, 15);
		contentPanel.add(label);
		
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 17));
		textArea.setEditable(false);
		textArea.setEnabled(false);
		textArea.setText(remarks);
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(10, 37, 414, 214);
		contentPanel.add(scrollPane);
	}
}
