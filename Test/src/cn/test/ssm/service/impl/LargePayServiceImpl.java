package cn.test.ssm.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import cn.test.ssm.pojo.LargePay;
import cn.test.ssm.service.LargePayService;

import com.alibaba.fastjson.JSON;

/**
 * Copyright © 2018 Talent Information Solutions Ltd. All rights reserved.
 * 
 * @ClassName: LargePayServiceImpl
 * @Description: TODO(大额支付接口实现类)
 * @author: GK
 * @version V1.0
 */
@Service("largePayService")
public class LargePayServiceImpl implements LargePayService {
	
	private static final String PATH = "C:\\pay";
	private static final String FILE_NAME = "largePay.txt";

	@Override
	public String packageMessage(LargePay largePay) {
		try {
			writeTxtFile(JSON.toJSONString(largePay), PATH, FILE_NAME);//生成文件
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "操作成功！报文文件路径：" + PATH + File.separator + FILE_NAME;
	}
	
	

	@Override
	public Map<String, Object> unpack() {
		Map<String, Object> retMap = new HashMap<String, Object>();
		String filePath = PATH + File.separator + FILE_NAME;
		if(!isExists(filePath)){//文件不存在
			retMap.put("largePay", new LargePay());
			retMap.put("retrunMsg", "报文文件不存在！");
			return retMap;
		}
		
		try {
			byte[] bytes = toByteArray(filePath);//读取报文文件内容
			LargePay bean = JSON.parseObject(bytes, LargePay.class);//JSON转化成对象
			retMap.put("largePay", bean);
			retMap.put("retrunMsg", "读取成功！");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return retMap;
	}



	/**
	 * 生成文件
	 * @param newStr
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	public static boolean writeTxtFile(String newStr, String filePath,
			String fileName) throws IOException {
		boolean flag = false;
		FileOutputStream fos = null;
		PrintWriter pw = null;
		try {
			createDir(filePath);// 创建目录，存在则不创建
			// 文件路径
			File file = new File(filePath + File.separator + fileName);
			fos = new FileOutputStream(file);
			pw = new PrintWriter(fos);
			pw.write(newStr.toCharArray());
			pw.flush();
			flag = true;
		} catch (IOException e1) {
			throw e1;
		} finally {
			if (pw != null) {
				pw.close();
			}
			if (fos != null) {
				fos.close();
			}
		}
		return flag;
	}
	
	/**
	 * 创建目录
	 * 
	 * @param destDirName
	 *            目标目录名
	 * @return 目录创建成功返回true，否则返回false
	 */
	public static boolean createDir(String destDirName) {
		File dir = new File(destDirName);
		if (dir.exists()) {
			return false;
		}
		if (!destDirName.endsWith(File.separator)) {
			destDirName = destDirName + File.separator;
		}
		// 创建单个目录
		if (dir.mkdirs()) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 读取到字节数组
	 * 
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	public static byte[] toByteArray(String filePath) throws IOException {
		File f = new File(filePath);
		if (!f.exists()) {
			throw new FileNotFoundException(filePath);
		}

		FileChannel channel = null;
		FileInputStream fs = null;
		try {
			fs = new FileInputStream(f);
			channel = fs.getChannel();
			ByteBuffer byteBuffer = ByteBuffer.allocate((int) channel.size());
			while ((channel.read(byteBuffer)) > 0) {
				// do nothing
				// System.out.println("reading");
			}
			return byteBuffer.array();
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				channel.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				fs.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 判断文件是否存在
	 * 
	 * @return
	 */
	public static boolean isExists(String filePath) {
		File file = new File(filePath);
		if (file.exists()) {// 存在
			return true;
		} else {
			return false;// 不存在
		}
	}
}
