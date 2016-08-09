package com.bluewalrus.main.test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.bluewalrus.main.GenerateShowcase;

public class FileUtils {

	public static void writeFile(StringBuilder sbCodeSnippet, String path, String fileName){
		BufferedWriter writer = null;
		try {
			String s = GenerateShowcase.path + fileName;
			
			System.out.println("create new file " + s);
			
			
			File file = new File(s);
			file.createNewFile();
			
			System.out.println("file.getCanonicalFile " + file.getCanonicalFile());
			System.out.println("sbCodeSnippet length " + sbCodeSnippet.length());
			
			writer = new BufferedWriter(new FileWriter(file)); 
			writer.write(sbCodeSnippet.toString());
		} catch (IOException e1) {
			e1.printStackTrace();
		}finally{
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
