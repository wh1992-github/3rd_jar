package com.example.a_test;

import java.io.File;

public class DeleteFileUtils {

	public static void deleteFile(File file) {
		if (file.isFile()) {
			// 如果是文件，可以直接删除。
			// 用.delete()实现。
			file.delete();
			return;
		}

		if (file.isDirectory()) {
			// 如果是文件夹目录的话，必须遍历他的每一个子项来逐个删除。
			// 原因在于.delete()只能删除文件和空文件夹。
			File[] childFiles = file.listFiles();
			if (childFiles == null || childFiles.length == 0) {
				file.delete();
				return;
			}
			// 执行到此处，说明childFiles.length>0。
			// 所以回调deleteFile()来继续完成删除任务。
			for (int i = 0; i < childFiles.length; i++) {
				deleteFile(childFiles[i]);
			}
			//此处是将空文件夹删除掉
			file.delete();
		}
	}
}
