package com.qianfeng.day13_externalstorage_fileexplorer;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import android.os.Environment;
import android.os.StatFs;

public class SDCardUtil {

	/**
	 * 获得外部存储的状态
	 */
	public static String getSDCardState() {
		String state = Environment.getExternalStorageState();
		return state;
	}
	
	/**
	 * 获得外部存储是否正确挂载，并可读可写
	 */
	public static boolean isSDCardMounted() {
		String state = Environment.getExternalStorageState();
		return state.equals(Environment.MEDIA_MOUNTED);
	}
	
	/**
	 * 获取外部存储路径
	 */
	public static String getSDCardPath() {
		if (isSDCardMounted()) {
			return Environment.getExternalStorageDirectory().getAbsolutePath();
		}
		return null;
	}
	
	/**
	 * 获取外部存储的总大小，单位为M
	 */
	public static long getSDCardSize() {
		if (isSDCardMounted()) {
			StatFs statFs = new StatFs(getSDCardPath());
			long blockSize = statFs.getBlockSize();
			long blockCount = statFs.getBlockCount();
			return blockSize * blockCount / 1024 / 1024;
		}
		return 0;
	}
	
	/**
	 * 获取外部存储的可用大小，单位为M
	 */
	public static long getSDCardAvailableSize() {
		if (isSDCardMounted()) {
			StatFs statFs = new StatFs(getSDCardPath());
			long blockSize = statFs.getBlockSize();
			long blockCount = statFs.getAvailableBlocks();
			return blockSize * blockCount / 1024 / 1024;
		}
		return 0;
	}
	
	/**
	 * 将指定数据保存到指定位置、指定文件名的文件中
	 * @param data 要保存的数据
	 * @param dir 子文件夹名称，不要以“/”开头
	 * @param name 文件名
	 * @return 文件是否保存成功
	 */
	public static boolean write2File(byte[] data, String dir, String name) {
		if (isSDCardMounted()) {
			
			File dirFile = new File(getSDCardPath(), dir);
			if (!dirFile.exists()) {
				dirFile.mkdirs();
			}
			
			BufferedOutputStream bufOutStream = null;
			try {
				File file = new File(dirFile, name);
				bufOutStream = new BufferedOutputStream(new FileOutputStream(file));
				bufOutStream.write(data);
				bufOutStream.flush();
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (bufOutStream != null) {
					try {
						bufOutStream.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * 读取指定文件
	 * @param path 文件路径
	 * @return 文件的字节数组
	 */
	public static byte[] readFromFile(String path) {
		if (isSDCardMounted()) {
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			
			BufferedInputStream inStream = null;
			try {
				inStream = new BufferedInputStream(new FileInputStream(path));
				byte[] temp = new byte[1024];
				int len = 0;
				while ((len = inStream.read(temp)) != -1) {
					outStream.write(temp, 0, len);
					outStream.flush();
				}
				return outStream.toByteArray();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (inStream != null) {
					try {
						inStream.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return null;
	}
}
