package com.itxc.model;

import java.io.Serializable;

//对应数据表创建类  【实体类】【贫血模型】
public class Products implements Serializable  {
	//可序列化 ==> 将java类和对象 转为二进制数据 --->通过网络进行传输
	//在接收端  拿到二进制数据 通过反序列化操作 将二进制===>类和对象
	private static final long serialVersionUID = 1L;
	private String id="100"; 
	private String name="测试";
	private double price=100;
    private String category="测试";
    private int pnum;
    private String imgurl;
    private String description;
    //框架  通过反射拿到实体类 属性和方法
	public String getId() {
		return id;
	}
	          //set属性名首字母大写(对应属性类型 参数名)
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getPnum() {
		return pnum;
	}
	public void setPnum(int pnum) {
		this.pnum = pnum;
	}
	public String getImgurl() {
		return imgurl;
	}
	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "Products [id=" + id + ", name=" + name + ", price=" + price + ", category=" + category + ", pnum="
				+ pnum + ", imgurl=" + imgurl + ", description=" + description + "]\n";
	}
}
