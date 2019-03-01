package com.fdmgroup.businessLogic;

public class IDGenerator {
	private static int id = 0;
	
	public static int getNext(){
		return ++id;
	}
}
