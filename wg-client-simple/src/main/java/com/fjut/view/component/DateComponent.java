package com.fjut.view.component;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.jdesktop.swingx.JXDatePicker;

import com.eltima.components.ui.DatePicker;

/**
 * 日期组件
 * @author LGX
 *
 */
public class DateComponent {

	private int x;
	private int y;
	private int width;
	private int height;
	
    public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}
	
	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public DateComponent() {
		super();
	}

	public DateComponent(int x, int y, int width, int height) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	/**
	 * 使用DatePicker创建日期选择器，缺点无法设置日期
	 * @return
	 */
	public DatePicker getDatePicker() {
        final DatePicker datepick;
        // 格式
        String DefaultFormat = "yyyy-MM-dd";
        // 字体
        Font font = new Font("Times New Roman", Font.BOLD, 14);
        Dimension dimension = new Dimension(177, 24);
        int[] hilightDays = { 1, 3, 5, 7 };
        int[] disabledDays = { 4, 6, 5, 9 };
        //构造方法（初始时间，时间显示格式，字体，控件大小）
        datepick = new DatePicker(new Date(), DefaultFormat, font, dimension);
        //也可用setBounds()直接设置大小与位置
        datepick.setBounds(this.x, this.y, this.width, this.height);
        // 设置一个月份中需要高亮显示的日子
        datepick.setHightlightdays(hilightDays, Color.red);
        // 设置一个月份中不需要的日子，呈灰色显示
        datepick.setDisableddays(disabledDays);
        // 设置国家
        datepick.setLocale(Locale.CANADA);
        // 设置时钟面板可见
        datepick.setTimePanleVisible(true);
        return datepick;
    }
	
	/**
	 * 使用JXDatePicker创建日期选择器
	 * @param date
	 * @return
	 */
	public JXDatePicker getJXDatePicker (Date date){
        final JXDatePicker datepick = new JXDatePicker();
        // 设置 date日期
        datepick.setDate(date);
        //设置日期格式
        String DefaultFormat = "yyyy-MM-dd";			
        datepick.setFormats(new SimpleDateFormat(DefaultFormat));
        //设置字体
        Font font = new Font("Times New Roman", Font.BOLD, 14);
        datepick.setFont(font);
      //用setBounds()直接设置大小与位置
        datepick.setBounds(this.x, this.y, this.width, this.height);
        return datepick;
	}
	
	
	public JXDatePicker getJXDatePicker(){
		return getJXDatePicker(new Date());
	}
	
}
