package com.example.RemoteClassLoader;

public class Animal {

	private String name;
	
	private String brew;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrew() {
		return brew;
	}

	public void setBrew(String brew) {
		this.brew = brew;
	}

	@Override
	public String toString() {
		return "Animal [name=" + name + ", brew=" + brew + "]";
	}
	
}
