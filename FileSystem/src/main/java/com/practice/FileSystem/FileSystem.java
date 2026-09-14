package com.practice.FileSystem;

public class FileSystem {

	private Directory root=new Directory("/");
	
	private Directory traverse(String path){

	    if(path.equals("/"))
	        return root;

	    String[] parts = path.split("/");

	    Directory curr = root;

	    for(String part : parts){

	        if(part.isEmpty())
	            continue;

	        FileNode node = curr.find(part);

	        if(node == null)
	            return null;

	        if(!(node instanceof Directory))
	            return null;

	        curr = (Directory) node;
	    }

	    return curr;
	}
	
	public void mkdir(String path){

	    int idx = path.lastIndexOf('/');
	    String parentPath = path.substring(0,idx);
	    String dirName = path.substring(idx+1);
	    Directory parent = traverse(parentPath);
	    if(parent==null)
	        return;
	    parent.add(new Directory(dirName));
	}
	
	public void touch(String path){

	    int idx = path.lastIndexOf('/');
	    String parentPath = path.substring(0,idx);
	    String fileName = path.substring(idx+1);
	    Directory parent = traverse(parentPath);
	    if(parent==null)
	        return;
	    parent.add(new File(fileName));
	}
	
	
}
