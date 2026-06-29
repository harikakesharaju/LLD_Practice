package com.practice.FileSystem;

public class File implements FileNode{
	public String name;
	
	public File(String s) {
		this.name=s;
	}
	
	@Override
	public void ls(int depth) {
		for(int i=0;i<depth;i++)
		System.out.print(" - ");
		System.out.println(name);
		
	}

	
	
}
