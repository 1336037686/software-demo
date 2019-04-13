package com.fjut.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.fjut.pojo.Version;

/**
 * 更新工具类
 * @author LGX
 *
 */
@Component
public class UpdateUtil {
	
	@Value("${update-url-ip}")
	private String urlPath;
	
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
		}
	    return null;
	}
}
