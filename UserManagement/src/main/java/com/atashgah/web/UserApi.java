package com.atashgah.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import com.atashgah.dao.UserDAOImpl;
import com.atashgah.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserApi
 */
@WebServlet("/api/users/*")
public class UserApi extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserApi() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		PrintWriter out=response.getWriter();
		UserDAOImpl userDao=new UserDAOImpl();
		response.setHeader("Content-Type", "application/json");
		ObjectMapper objectMapper=new ObjectMapper();
		try 
		{
			if(request.getPathInfo()!=null &&request.getPathInfo().length()>1)
			{
				List<String>paths=Arrays.asList(request.getPathInfo().substring(1).split("/"));
				if(paths.size()>1) 
				{
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					out.print("Inlavid path");
				}
				else 
				{
					User user=userDao.selectUser(Integer.parseInt(paths.get(0)));
					out.print(objectMapper.writeValueAsString(user));
				}
			}
			else 
			{
				List<User>list=userDao.selectUsers();
				out.print(objectMapper.writeValueAsString(list));
			}
			
		} catch (Exception e) 
		{
			out.write(e.getMessage());
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		PrintWriter out=response.getWriter();
		StringBuffer jb=new StringBuffer();
		String line=null;
		try {
			BufferedReader reader=request.getReader();
			while((line=reader.readLine())!=null)
				jb.append(line);
			ObjectMapper objectMapper=new ObjectMapper();
			User user=objectMapper.readValue(jb.toString(), User.class);
			UserDAOImpl userDao=new UserDAOImpl();
			userDao.insertUser(user);
			out.write(objectMapper.writeValueAsString(user));
			
		} catch (Exception e) {
			out.write(e.getMessage());
		}
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		PrintWriter out=response.getWriter();
		StringBuffer jb=new StringBuffer();
		String line=null;
		try {
			BufferedReader reader=request.getReader();
			while((line=reader.readLine())!=null)
				jb.append(line);
			ObjectMapper objectMapper=new ObjectMapper();
			User user=objectMapper.readValue(jb.toString(), User.class);
			UserDAOImpl userDao=new UserDAOImpl();
			userDao.updateUser(user);
			out.write(objectMapper.writeValueAsString(user));
			
		} catch (Exception e) {
			out.write(e.getMessage());
		}
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		PrintWriter out=response.getWriter();
		UserDAOImpl userDao=new UserDAOImpl();
		response.setHeader("Content-Type", "application/json");
		ObjectMapper objectMapper=new ObjectMapper();
		try 
		{
			if(request.getPathInfo()!=null &&request.getPathInfo().length()>1)
			{
				List<String>paths=Arrays.asList(request.getPathInfo().substring(1).split("/"));
				if(paths.size()>1) 
				{
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					out.print("Inlavid path");
				}
				else 
				{
					userDao.deleteUser(Integer.parseInt(paths.get(0)));
					out.write("deleted");
				}
			}
			
			
		} catch (Exception e) 
		{
			out.write(e.getMessage());
		}
	}

}
