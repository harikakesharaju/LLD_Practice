package com.practice.FileSystem;

public interface FileNode {	
	
	public void ls(int depth);
	
	public String getName();
	
	public Directory getParent();
	
	public long getSize();
	
	public void setParent(Directory d);
	
	
}
