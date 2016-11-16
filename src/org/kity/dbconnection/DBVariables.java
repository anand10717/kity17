package org.kity.dbconnection;

public enum DBVariables {
	device_table("devices"),
	user_table("users");
	private final String name;

	private DBVariables(String string){
		this.name = string;
	}

	public  String getFileDir(){
		return name;
	}
}
