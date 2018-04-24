package com.talent.forex.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletException;

import org.apache.log4j.Logger;

import com.opensymphony.webwork.ServletActionContext;
import com.talent.tools.StringUtil;
@SuppressWarnings("unchecked")
public class FileUtil {
	private static Logger logger = Logger.getLogger(FileUtil.class);

	public static void exec(String path, String filename) {

		try {
			String command = "cmd.exe /c start /min " + path + filename;
			Process proc = Runtime.getRuntime().exec(command);

			// BufferedReader br=null;
			// br=new BufferedReader(new
			// InputStreamReader(proc.getInputStream()));
			// String line=null;
			// logger.warn("打印所有正在运行的进程信息--");
			// while((line=br.readLine())!=null){
			// logger.warn(br.readLine());
			// }

		} catch (IOException e) {
			logger.error(e);
		}

	}

	/**
	 * 
	 * @param path
	 * @param filename
	 * @param sql
	 * @throws IOException
	 */
	public static boolean writeBatFile(String path, String filename, String cmd)
			throws IOException {
		boolean flag = false;
		File dir = new File(path);
		if (!dir.exists())
			dir.mkdirs();
		File file = new File(path + filename);
		try {
			if (file.exists())
				file.delete();
			file.createNewFile();
			FileWriter fw = new FileWriter(file, true);
			fw.write(cmd);
			fw.write("\r\n");
			fw.write("exit");
			logger.warn("创建bat文件[" + path + filename + "]执行命令：" + cmd);
			fw.close();
			flag = true;
		} catch (IOException e) {
			logger.error("bat文件创建失败！");
			logger.error(e);

		}
		return flag;

	}


	/**
	 * 判断dmp文件是否存在
	 * 
	 * @param path
	 * @param fileName
	 */
	public static boolean checkFileExit(String path, String fileName) {
		boolean flag = false;
		try {
			File dir = new File(path);

			if (!dir.exists()) {
				// 判断是否有该路径,没有,返回 报错
				logger.error("没有目录路径存在：" + path);

			} else {
				// 如果目录存在 ，判断是否有文件存在
				String[] uploadFileNames = dir.list(); // 获取上传文件夹里面的所有文件
				for (int i = 0; i < uploadFileNames.length; i++) {
					if (uploadFileNames[i].trim().equals(fileName.trim())) {
						flag = true;// 文件存在
					}
				}

			}
		} catch (Exception e) {
		}

		return flag;
	}


	/**
	 * @param fileName
	 * @param fileFullName
	 * @throws ServletException
	 * @throws IOException
	 */
	public static void fileDownload(String fileName, String fileFullName)
			throws ServletException, IOException {

		ServletActionContext.getResponse().setContentType(
				"text/html;charset=gbk");
		ServletActionContext.getResponse().setHeader(
				"Content-disposition",
				"attachment; filename=\"" + StringUtil.gbkToIso(fileName)
						+ "\"");
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			
			bis = new BufferedInputStream(new FileInputStream(fileFullName));
			bos = new BufferedOutputStream(ServletActionContext.getResponse()
					.getOutputStream());

			byte[] buff = new byte[2048];
			int bytesRead;

			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}

		} catch (final IOException e) {
			e.printStackTrace();
			
		} finally {
			if (bis != null)
				bis.close();
			if (bos != null)
				bos.close();
		}
	}
	
//	压缩文件
	public  void zipFile(String inputFileName,String outputFileName) throws Exception { 
			ZipOutputStream out = new ZipOutputStream(new FileOutputStream(outputFileName));
			zip(out, new File(inputFileName), "");
			System.out.println("zip done");
			out.close();
		}
	
	private void zip(ZipOutputStream out, File f, String base) throws Exception {
			if (f.isDirectory()) {
				File[] fl = f.listFiles();
				if (System.getProperty("os.name").startsWith("Windows")){
					out.putNextEntry(new ZipEntry(base + "/"));
					base = base.length() == 0 ? "" : base + "/";
				}
				else{
					out.putNextEntry(new ZipEntry(base + "/"));
					base = base.length() == 0 ? "" : base + "/";
				}
				for (int i = 0; i < fl.length; i++) {
					zip(out, fl[i], base + fl[i].getName());
				}
			}
			else {

				out.putNextEntry(new ZipEntry(base));
				FileInputStream in = new FileInputStream(f);
				int b;
//				System.out.println(base);
				while ( (b = in.read()) != -1) {
					out.write(b);
				}
				in.close();
			}
	}
}
