package com.fjut.view.page;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.eltima.components.ui.DatePicker;
import com.fjut.pojo.Materials;
import com.fjut.pojo.MaterialsSell;
import com.fjut.pojo.MaterialsSellDetail;
import com.fjut.pojo.User;
import com.fjut.pojo.vo.ComboxVo;
import com.fjut.service.MaterialsSellService;
import com.fjut.service.MaterialsService;
import com.fjut.service.UserService;
import com.fjut.util.MD5Util;
import com.fjut.util.SpringContextUtils;
import com.fjut.view.component.DateComponent;
import com.fjut.view.component.MaterialInDetailPanel;

/**
 * 添加进仓界面
 * @author LGX
 *
 */
@Lazy
@Scope("prototype")
@Component("MaterialInPage")
@SuppressWarnings("all")
public class MaterialInPage extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField materialShellIdField;
	private JTextField inOutTypeField;
	private JTextField textField;
	private JTextField textField_1;
	 private DatePicker datepick;
	
	private List<Materials> materialsList;
	private MaterialsService materialsService = SpringContextUtils.getBean(MaterialsService.class);
	private MaterialsSellService materialsSellService = SpringContextUtils.getBean(MaterialsSellService.class);
	private UserService userService = SpringContextUtils.getBean(UserService.class);
	
	private int detailNum = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			MaterialInPage dialog = new MaterialInPage();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public MaterialInPage() {
		setModal(true);
		setBounds(100, 100, 683, 618);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		String md5Id = MD5Util.getMD5();
		materialsList = materialsService.getAllMaterialList();
		List<User> userList = userService.getAllUserList();
		
		JLabel label = new JLabel("单号");
		label.setBounds(10, 10, 54, 15);
		contentPanel.add(label);
		
		materialShellIdField = new JTextField();
		materialShellIdField.setEnabled(false);
		materialShellIdField.setBounds(74, 7, 583, 21);
		contentPanel.add(materialShellIdField);
		materialShellIdField.setColumns(10);
		//添加id
		materialShellIdField.setText(md5Id);
		
		JLabel label_1 = new JLabel("操作人员");
		label_1.setBounds(10, 38, 54, 15);
		contentPanel.add(label_1);
		
		JComboBox handlerUserField = new JComboBox();
		handlerUserField.setBounds(74, 35, 155, 21);
		
		if(userList != null) {
			for (User user : userList) {
				handlerUserField.addItem(new ComboxVo(user.getId(), user.getUserName()));
			}
		}
		contentPanel.add(handlerUserField);
		
		JLabel label_2 = new JLabel("进出仓类型");
		label_2.setBounds(239, 38, 68, 15);
		contentPanel.add(label_2);
		
		inOutTypeField = new JTextField();
		inOutTypeField.setEnabled(false);
		inOutTypeField.setBounds(317, 35, 120, 21);
		inOutTypeField.setText("进仓");
		contentPanel.add(inOutTypeField);
		inOutTypeField.setColumns(10);
		
		JLabel label_4 = new JLabel("进仓备注");
		label_4.setBounds(10, 423, 54, 15);
		contentPanel.add(label_4);
		
		JTextArea remarksField = new JTextArea();
		JScrollPane remarkJScrollPane = new JScrollPane(remarksField);
		remarkJScrollPane.setBounds(10, 448, 647, 88);
		contentPanel.add(remarkJScrollPane);
		
		JPanel inOutListPanel = new JPanel();
		inOutListPanel.setPreferredSize(new Dimension(647, 381));
		inOutListPanel.setBorder(BorderFactory.createTitledBorder("进出仓详情"));
		inOutListPanel.setLayout(null);
		
		JScrollPane inOutListJScrollPane = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
		        ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		contentPanel.add(inOutListJScrollPane);
		inOutListJScrollPane.setBounds(10, 66, 650, 347);

		JButton button = new JButton("添加");
		List<JPanel> detailPanelList = new ArrayList<>();																 //详情列表
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inOutListJScrollPane.setViewportView(inOutListPanel);
				inOutListPanel.setPreferredSize(new Dimension(647, 15 + 45 * detailNum));
				JPanel detailPanel = new MaterialInDetailPanel(10, 15 + 45 * detailNum, detailNum, md5Id, materialsList);
				detailPanel.setVisible(true);
				inOutListPanel.add(detailPanel);
				inOutListPanel.repaint();
				detailPanelList.add(detailPanel);
				detailNum++;
			}
		});
		contentPanel.add(button);
		button.setBounds(358, 546, 93, 23);
		
		JButton button_1 = new JButton("删除");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(detailPanelList.size() > 0) {					
					JPanel panel = detailPanelList.get(detailPanelList.size() - 1);
					inOutListPanel.remove(panel);
					detailPanelList.remove(panel);
					detailNum--;
					inOutListPanel.setPreferredSize(new Dimension(647, 15 + 45 * detailNum));
					inOutListPanel.repaint();
				}
			}
		});
		contentPanel.add(button_1);
		button_1.setBounds(461, 546, 93, 23);
		
		JLabel label_3 = new JLabel("操作日期");
		contentPanel.add(label_3);
		label_3.setBounds(447, 38, 54, 15);
		
		datepick = new DateComponent(511, 35, 149, 21).getDatePicker();
		contentPanel.add(datepick);
		
		JButton submitBtn = new JButton("提交");
		submitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<MaterialsSellDetail> materialsSellDetailsList = new ArrayList<>();
				if(detailPanelList != null) {
					for (JPanel jPanel : detailPanelList) {
						MaterialInDetailPanel m = (MaterialInDetailPanel) jPanel;
						if(m.isOK() == false) {
							JOptionPane.showMessageDialog(null, "请全部确认后提交", "提示", JOptionPane.ERROR_MESSAGE);
							return;
						}
						materialsSellDetailsList.add(m.getMaterialsSellDetailData());
						System.out.println(m.isOK() +  ":" + m.getMaterialsSellDetailData());
					}
				}
				
				if(materialsSellDetailsList.size() == 0) {
					JOptionPane.showMessageDialog(null, "请添加一个物料", "提示", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				ComboxVo userVo = (ComboxVo) handlerUserField.getSelectedItem();
				MaterialsSell materialsSell = new MaterialsSell(md5Id, (Date)datepick.getValue(), userVo.getKey(), remarksField.getText(), 1);
				boolean result = materialsSellService.addMaterialsSellIn(materialsSell, materialsSellDetailsList);
				if(result) {
					JOptionPane.showMessageDialog(null, "进仓成功", "提示", JOptionPane.INFORMATION_MESSAGE);					
					setVisible(false);
					dispose();
				}else {
					JOptionPane.showMessageDialog(null, "进仓失败", "提示", JOptionPane.ERROR_MESSAGE);		
				}
			}
		});
		submitBtn.setBounds(564, 546, 93, 23);
		contentPanel.add(submitBtn);
	}
}
