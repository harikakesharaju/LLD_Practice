package com.practice.FileSystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Directory implements FileNode{

	private Map<String,FileNode> children=new HashMap<>();
	private String name;
	private Directory parent;
	
	public Directory(String s) {
		this.name=s;
		this.parent=null;
	}
	
	@Override
	public void ls(int depth) {
		for(int i=0;i<depth;i++)
			System.out.print(" - ");
		System.out.println("Directory");
		for(FileNode f:children.values()) {
			f.ls(depth+1);
		}
	}
	
	public void add(FileNode node){
	    children.put(node.getName(),node);
	    node.setParent(this);
	}
	
	public void remove(String name){
	    children.remove(name);
	}
	
	public FileNode find(String name){
	    return children.get(name);
	}
	
	public boolean contains(String name){
	    return children.containsKey(name);
	}
	
	public List<FileNode> getChildren(){
	    return new ArrayList<>(children.values());
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
	public long getSize() {
		 long size=0;
		    for(FileNode node:children.values())
		        size+=node.getSize();
		 return size;
	}

	@Override
	public void setParent(Directory d) {
		this.parent=d;		
	}
}
