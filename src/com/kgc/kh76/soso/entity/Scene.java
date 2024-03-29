package com.kgc.kh76.soso.entity;

public class Scene {
	private String type;			// [场景类型]:String
	private int data;			// [场景消费数据]: int
	private String description;		//[场景描述]: String
	
	public Scene() {
	}

	public Scene(String type, int data, String description) {
		this.type = type;
		this.data = data;
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
