package controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import model.User;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/users/*")
public class UserServlet extends AbstractController {
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

        String path = getTemplatePath(request.getServletPath()+request.getPathInfo());



        if ("/list".equalsIgnoreCase(request.getPathInfo())){

            UserDao userDao = UserDaoImpl.getInstance();

            List<User> users = userDao.findAll();

            request.setAttribute("users", users);

        }

        else if("/edit".equalsIgnoreCase(request.getPathInfo()))
        {
        	UserDao userDao = UserDaoImpl.getInstance();
        	Optional<User> user=userDao.find(Long.parseLong(request.getParameter("id")));
        	
        	request.setAttribute("user", user.get());
        }
        
        else if ("/delete".equalsIgnoreCase(request.getPathInfo()))
        {
        	UserDao userDao=UserDaoImpl.getInstance();
        	Long id=Long.parseLong(request.getParameter("id"));
        	boolean status=userDao.delete(id);
        	if(status==true)
       	 {
       		path="list";
       	 }
       	 else
       	 {
       		 path="form";
       	 }
        	
        }

        if ("/delete".equalsIgnoreCase(request.getPathInfo()))
        {
        	response.sendRedirect(path);
        }
        else 
        {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);

        requestDispatcher.forward(request, response);
        }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		 String path = getTemplatePath(request.getServletPath()+request.getPathInfo());



	        if ("/save".equalsIgnoreCase(request.getPathInfo())){
	        	 UserDao userDao = UserDaoImpl.getInstance();
	        	 String username=request.getParameter("username");
	        	 String password=request.getParameter("userpass");
	        	 User u=new User(0,username,password);
	        	 
	        	 
	        	boolean status=userDao.save(u);
	        	 if(status==true)
	        	 {
	        		path="list";
	        	 }
	        	 else
	        	 {
	        		 path="form";
	        	 }
	        	 response.sendRedirect(path);
	        }
	        
	        else if ("/update".equalsIgnoreCase(request.getPathInfo())) {
	        	UserDao userDao=UserDaoImpl.getInstance();
	        	Long id=Long.parseLong(request.getParameter("id"));
	        	String username=request.getParameter("username");
	        	String password=request.getParameter("userpass");
	        	User u=new User(id,username,password);
	        	
	        	boolean status=userDao.update(u);
	        	if(status==true)
	        	{
	        		path="list";
	        	}
	        	else
	        	{
	        		path="edit?id="+id;
	        	}
	        	 response.sendRedirect(path);
	        }
	        
		
		//super.doPost(request, response);
	}


}
