package com.itxc.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.itxc.model.Products;
import com.itxc.utils.C3p0Utils;
import com.itxc.utils.JDBCUtils;

public class ProductsDao extends BaseDao{

	// 查询所有的User对象
	public List<Products> findAll() {
		// * 将数据库的表数据 按照 我们自己定义【数据结构】 存储在内存
		try {
			ArrayList<Products> list = new ArrayList<Products>();
			String sql = "SELECT * FROM Products";// sql脚本
			list = (ArrayList<Products>)this.query(sql, new BeanListHandler<>(Products.class));
			return list;
		} catch (SQLException e) {
			//e.printStackTrace();
			//记录异常日志 日志框架
			return null;
		}
	}
    
	public static void main(String[] args) {
		List<Products> findAll = new ProductsDao().findAll();
		System.out.println(findAll);
	}

	public boolean addProd(Products prod) {
		try {
			String sql="insert into products values(?,?,?,?,?,?,?)";
			QueryRunner runner = new QueryRunner(C3p0Utils.getDataSource());
			//调用方法
			int num =runner.update(sql, new Object[]{
					prod.getId(),
					prod.getName(),
					prod.getPrice(),
					prod.getCategory(),
					prod.getPnum(),
					prod.getImgurl(),
					prod.getDescription()
			} );
			if (num>=1)
				return true;
		} catch (SQLException e) {
			//e.printStackTrace();
		}
		return false;
	}
}
