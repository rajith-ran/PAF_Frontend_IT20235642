package com.UserManage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


	@WebServlet("/UserAPI")
	public class UserAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;

	User usrObj = new User();

	public UserAPI() {
	super();
	}

	// Convert request parameters to a Map
		private static Map getParasMap(HttpServletRequest request)
		{
		 Map<String, String> map = new HashMap<String, String>();
		try
		 {
		 Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
		 String queryString = scanner.hasNext() ?
		 scanner.useDelimiter("\\A").next() : "";
		 scanner.close();
		 String[] params = queryString.split("&");
		 for (String param : params)
		 { 
			 String[] p = param.split("=");
			 map.put(p[0], p[1]);
			 }
			 }
			catch (Exception e)
			 {
			 }
			return map;
			}

		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			String output = usrObj.insertUser(
					request.getParameter("name"),
					request.getParameter("age"),
					request.getParameter("address"),
			        request.getParameter("email"));
					response.getWriter().write(output); 

		}

		protected void doPut(HttpServletRequest request, HttpServletResponse response)
				 throws ServletException, IOException
				{
				 Map paras = getParasMap(request);
				 String output = usrObj.updateUser(paras.get("hidUserIDSave").toString(),
				 paras.get("name").toString(),
				 paras.get("age").toString(),
				 paras.get("address").toString(),
				 paras.get("email").toString());
				 response.getWriter().write(output);
				} 

		protected void doDelete(HttpServletRequest request, HttpServletResponse response)
				 throws ServletException, IOException
				{
				 Map paras = getParasMap(request);
				 String output = usrObj.deleteUser(paras.get("id").toString());
				response.getWriter().write(output);
				}

	}
