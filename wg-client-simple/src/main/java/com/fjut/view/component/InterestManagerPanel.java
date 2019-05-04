package com.fjut.view.component;

import javax.swing.JPanel;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 其他项目面板
 * @author LGX
 *
 */
@Lazy
@Scope("prototype")
@Component("InterestManagerPanel")
@SuppressWarnings("all")
public class InterestManagerPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public InterestManagerPanel() {

	}

}
