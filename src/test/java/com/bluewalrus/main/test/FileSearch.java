package com.bluewalrus.main.test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileSearch {

	private String fileNameToSearch;
	private List<File> result = new ArrayList<File>();

	public String getFileNameToSearch() {
		return fileNameToSearch;
	}

	public void setFileNameToSearch(String fileNameToSearch) {
		this.fileNameToSearch = fileNameToSearch;
	}

	public List<File> getResult() {
		return result;
	}

	public static void main(String[] args) throws IOException {

		FileSearch fileSearch = new FileSearch();

		String current = new java.io.File(".").getCanonicalPath();

		System.out.println("Current dir:" + current);
		
		// try different directory and filename :)
		fileSearch.searchDirectory(new File("."),
				"TestDataXY_Boxplot.java");

		int count = fileSearch.getResult().size();
		if (count == 0) {
			System.out.println("\nNo result found!");
		} else {
			System.out.println("\nFound " + count + " result!\n");
			for (File matched : fileSearch.getResult()) {
				System.out.println("Found : " + matched);
			}
		}
	}

	public void searchDirectory(File directory, String fileNameToSearch) {

		setFileNameToSearch(fileNameToSearch);

		if (directory.isDirectory()) {
			search(directory);
		} else {
			System.out.println(directory.getAbsoluteFile()
					+ " is not a directory!");
		}
		
		System.out.println("FileNames : " + getResult());

	}

	private void search(File file) {

		if (file.isDirectory()) {
//			System.out.println("Searching directory ... "
//					+ file.getAbsoluteFile());

			// do you have permission to read this directory?
			if (file.canRead()) {
				for (File temp : file.listFiles()) {
					if (temp.isDirectory()) {
						search(temp);
					} else {
						
//						System.out.println(" file:  " + temp.getName() + " to search : " +getFileNameToSearch());
						
						if (getFileNameToSearch().equals(
								temp.getName())) {
							result.add(temp);
						}
					}
				}
			} else {
				System.out.println(file.getAbsoluteFile() + "Permission Denied");
			}
		}

	}

}
