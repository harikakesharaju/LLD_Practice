package com.practice.FileSystem;

public class File implements FileNode{
	private String name;
	private Directory parent;
	private String content;
	
	public File(String s) {
		this.name=s;
		this.content="";
	}
	
	public void write(String s) {
		this.content=s;
	}
	
	public void append(String s) {
		this.content+=s;
	}
	
	public String read() {
		return this.content;
	}
	
	public long getSize() {
		return this.content.length();
	}
	
	
	
	@Override
	public void ls(int depth) {
		for(int i=0;i<depth;i++)
		System.out.print(" - ");
		System.out.println(name);
		
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Directory getParent() {
		return parent;
	}

	@Override
	public void setParent(Directory d) {
		this.parent=d;		
	}

	
	
}
