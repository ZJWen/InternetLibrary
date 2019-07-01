package com.itxc.utils;
import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;
/**
 * 获取c3p0连接池数据源类
 * @author Administrator
 */
public class C3p0Utils {
	private static DataSource ds;
	//静态代码块的执行时机:将本类加载到内存时(Class对象实例) 执行
	static {
		//在静态代码块 创建数据源 默认查找的配置文件是c3p0-config.xml[src根目录]
		ds = new ComboPooledDataSource();
	}
	public static DataSource getDataSource() {
		return ds;
	}
	//获取连接对象
	public static Connection getConnection() {
		try {
			return ds.getConnection();
		} catch (SQLException e) {
			return null;
		}
	}
}