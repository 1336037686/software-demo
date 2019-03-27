package com.fjut.view.component;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import org.springframework.stereotype.Component;

import java.awt.Color;

/**
 * 主页面板
 * @author LGX
 *
 */
public class IndexPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public IndexPanel() {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBounds(0, 0, 1165, 725);
		

	}

}
