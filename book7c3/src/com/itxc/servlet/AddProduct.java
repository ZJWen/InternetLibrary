package com.itxc.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.itxc.dao.ProductsDao;
import com.itxc.model.Products;

public class AddProduct extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("------AddProduct-------");
		response.setContentType("text/html;charset=utf-8");
		try { 
			// 创建工厂
			DiskFileItemFactory factory = new DiskFileItemFactory();
			File f=new File("e:\\Target");
			if(!f.exists()){
				f.mkdirs();
			}
			//设置文件的缓存路径
			factory.setRepository(f); 
			// 创建 fileupload 组件
			ServletFileUpload fileupload = new ServletFileUpload(factory);
			fileupload.setHeaderEncoding("utf-8");
			// 解析 request
			List<FileItem> fileitems = fileupload.parseRequest(request);
			PrintWriter writer=response.getWriter();
			Products prod = new Products();
			prod.setId(UUID.randomUUID().toString());
			// 遍历集合
			for (FileItem fileitem : fileitems) {
				// 判断是否为普通字段
				if (fileitem.isFormField()) {
					// 获得字段名和字段值
					String name = fileitem.getFieldName();
					switch (name) {
					case "name":
						prod.setName(fileitem.getString("utf-8"));
						break;
					case "price":
					    double price = Double.parseDouble(fileitem.getString("utf-8"));
						prod.setPrice(price);
						break;
					case "pnum":
						int pnum = Integer.parseInt(fileitem.getString("utf-8"));
						prod.setPnum(pnum);
						break;
					case "category":
						prod.setCategory(fileitem.getString("utf-8"));
						break;
					case "description":
						prod.setDescription(fileitem.getString("utf-8"));
						break;
					default:
						break;
					}
					String value = fileitem.getString("utf-8");
					writer.print("上传者："+value+"<br>");
				} else {
					// 上传的文件路径
					String filename = fileitem.getName();
					writer.print("文件来源："+filename+"<br>");
					// 截取出文件名
					filename = filename
							.substring(filename.lastIndexOf("\\") + 1);
					writer.print("成功上传的文件："+filename+"<br>");
					// 文件名需要唯一
					filename = UUID.randomUUID().toString() + "_" + 
                        		filename;
					// 在服务器创建同名文件
					String webPath = "/upload/";
					prod.setImgurl(webPath+filename);
					String filepath =
                       getServletContext().getRealPath(webPath+filename); 
					// 创建文件
					File file = new File(filepath);
					file.getParentFile().mkdirs();
					file.createNewFile();
					// 获得上传文件流
					InputStream in = fileitem.getInputStream();
					// 获得写入文件流
					OutputStream out = new FileOutputStream(file);
					// 流的对拷
					byte[] buffer = new byte[1024];
					int len;
					while ((len = in.read(buffer)) > 0)
						out.write(buffer, 0, len);
					// 关流
					in.close();
					out.close();
					// 删除临时文件
					fileitem.delete();
				}
			}
			//将产品对象 保存的数据库
			System.out.println(prod);
			ProductsDao dao = new ProductsDao();
			boolean isok=dao.addProd(prod);
			if(isok) {
				response.sendRedirect("ProdManager.jsp");
			}else {
				writer.print("<h3>添加商品失败！！！<h3>");
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
