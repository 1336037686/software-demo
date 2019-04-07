package com.fjut.handler;

import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.EmptyBorder;

import com.alibaba.fastjson.JSON;
import com.fjut.pojo.Version;

@SuppressWarnings("all")
public class Update extends JFrame {
	private JProgressBar progressBar;
	
	String urlPath = "http://127.0.0.1:8080/info";

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public Update() {
		//关闭进程

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 394, 124);
		setVisible(true);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("进度");
		label.setBounds(10, 31, 54, 15);
		contentPane.add(label);
		
		progressBar = new JProgressBar();
		progressBar.setBounds(49, 32, 319, 14);
		contentPane.add(progressBar);
		
		JLabel statusLabel = new JLabel("正在更新...");
		statusLabel.setBounds(161, 56, 113, 19);
		contentPane.add(statusLabel);
		
		Version version = getInfoData();
		if(version!=null) {
			String path = new File(this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath()).getParentFile().getParentFile().getParentFile().getPath(); //获取应用文件夹路径
			System.out.println(path);
			path = path.substring(path.indexOf("\\") + 1);
			System.out.println(path);
			//path = "C:\\Users\\LGX\\Desktop\\新建文件夹";
			File file = new File(path + "/wg-client-simple.jar");
			System.out.println(file.getPath());
			
			//多线程更新
			new Thread(() ->  {
				boolean result = getStream(version, file);
				if(result) {
					JOptionPane.showMessageDialog(null, "更新成功!", "提示", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "更新失败!", "提示", JOptionPane.ERROR_MESSAGE);
				}
				//退出系统
				setVisible(false);
				dispose();
			}).start();
		}
	}
	
	/**
	 * 获取更新信息
	 */
	public Version getInfoData() {
	    try {
	    	System.out.println(urlPath);
			URL url=new URL(urlPath);//使用java.net.URL
			URLConnection connection=url.openConnection();//打开链接
			InputStream in = connection.getInputStream();//获取输入流
			InputStreamReader isr=new InputStreamReader(in);//流的包装
			BufferedReader br = new BufferedReader(isr);
			StringBuffer result = new StringBuffer();
			String str = "";
			while ((str = br.readLine()) != null) {
				result.append(str);
			}
			br.close();
			isr.close();
			in.close();
			return JSON.parseObject(result.toString(), Version.class);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "获取更新失败", "提示", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	    return null;
	}
	
	/**
	 * 下载文件
	 */
	public boolean getStream(Version version, File target){
		 try {
			long currentSize = 0;
			 URL url=new URL(version.getFilePath());//使用java.net.URL
			 InputStream inputStream = url.openStream();
			 FileOutputStream fos = new FileOutputStream(target);//文件输出流
			 byte[] buff = new byte[1024];
			 int length = 0;
			 while((length = inputStream.read(buff)) != -1) {
				 fos.write(buff, 0, length);
				 currentSize += length;
				 progressBar.setValue((int) (currentSize * 100 / version.getFileSize()));
			 }
			 fos.close();
			 inputStream.close();
			 return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
