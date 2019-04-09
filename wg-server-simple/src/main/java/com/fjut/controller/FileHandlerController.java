package com.fjut.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fjut.pojo.ResultInfo;
import com.fjut.pojo.Version;

@RestController
public class FileHandlerController {
	Logger logger = LoggerFactory.getLogger(FileHandlerController.class);

	@Value("${wg-ip}")
	private String ip;
	
	/**
	 * 单文件上传 文件上传名为xxxxx-版本.jar
	 */
	@RequestMapping(value = "/upload")
	public ResultInfo upload(@RequestParam("file") MultipartFile file) {
		ResultInfo result = new ResultInfo();
		
        if (file.isEmpty()) {
            result.setCode(400);
            result.setMsg("文件为空，请重新上传");
            return result;
        }
        
		try {
			// 获取文件名
			String fileName = file.getOriginalFilename();
			logger.info("上传的文件名为：" + fileName);

			// 设置文件存储路径
			String filePath = "E://FileUpload//";
			String path = filePath + fileName;

			File dest = new File(path);
			// 检测是否存在目录
			if (!dest.getParentFile().exists()) {
				dest.getParentFile().mkdirs();// 新建文件夹
			}
			file.transferTo(dest);// 文件写入
			
			Version.version = Double.parseDouble(fileName.substring(fileName.lastIndexOf("-") + 1, fileName.lastIndexOf(".")));
			Version.filePath = "http://" + ip + ":8080/file/" + fileName;
			Version.fileSize = file.getSize();
			System.out.println(Version.version + ":" + Version.filePath);

            result.setCode(200);
            result.setMsg("文件上传成功");
            result.setInfo(Version.version + "");
            return result;
		} catch (Exception e) {
            result.setCode(500);
            result.setMsg("文件上传发生异常！");
            return result;
		}
	}
	
	/**
	 * 获取更新信息
	 */
	@GetMapping("/info")
	public Map<String, Object> getUpdateInfo() {
		Map<String, Object> data = new HashMap<>();
		data.put("version", Version.version);
		data.put("filePath", Version.filePath);
		data.put("fileSize", Version.fileSize);
		return data;
	}

}
