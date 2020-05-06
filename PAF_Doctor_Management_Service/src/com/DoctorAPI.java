package com;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Doctor;

/**
 * Servlet implementation class DoctorAPI
 */
@WebServlet("/DoctorAPI")
public class DoctorAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	Doctor doctor1 = new Doctor();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoctorAPI() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String output = doctor1.addDoctor(
				request.getParameter("firstname"),
				request.getParameter("lastname"),
				request.getParameter("email"),
				request.getParameter("password"),
				request.getParameter("phonenumber"),
				request.getParameter("specalization"),
				request.getParameter("charges"),
				request.getParameter("type"));
				response.getWriter().write(output); 
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map paras = getParasMap(request);
		
		 String output = doctor1.updateDoctor(paras.get("hidItemIDSave").toString(),
				 paras.get("firstname").toString().replace('+',' '),
				 paras.get("lastname").toString().replace('+',' '),
				 paras.get("email").toString().replaceAll("%40","@"),
				 paras.get("password").toString(),
				 paras.get("phonenumber").toString(),
				 paras.get("specalization").toString().replace('+',' '),
				 paras.get("charges").toString(),
				 paras.get("type").toString().replace('+',' '));
				 response.getWriter().write(output); 

	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map paras = getParasMap(request);
		System.out.println(paras.get("doctorid"));
		String output = doctor1.RemoveDoctor(paras.get("doctorid").toString());
		System.out.println("error point "+output);
		response.getWriter().write(output);	
	}
	
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

	
}
