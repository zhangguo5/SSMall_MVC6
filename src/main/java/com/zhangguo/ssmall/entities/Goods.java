package com.zhangguo.ssmall.entities;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 商品实体
 *
 */
public class Goods implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*
	 * 编号
	 */
	private int id;
	/*
	 * 名称
	 */
	@Pattern(regexp="^[^><&#]{1,50}$",message="{pattern}")
	@NotNull(message="{notNull}")
	private String name;
	/*
	 * 价格
	 */
	@Min(value=1,message="必须大于或等于1")
	private double price;
	/*
	 * 图片
	 */
	private String picture;
	
	public Goods() {
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	@Override
	public String toString() {
		return "id："+getId()+"，name："+getName()+"，price："+getPrice()+"，picture："+getPicture();
	}
}
