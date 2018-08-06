package controller;

import javax.servlet.http.HttpServlet;

public abstract class AbstractController extends HttpServlet{

	
	public static final String VIEW_PREFIX="/WEB-INF/jsp";
	public static final String VIEW_SUFIX=".jsp";
	
	
	protected String getTemplatePath(String path)
	{
		if(path.equalsIgnoreCase("/"))
		{
			return VIEW_PREFIX + path + "index" + VIEW_SUFIX;
		}
		else
		{
			return VIEW_PREFIX + path + VIEW_SUFIX;
		}
	}
	
	
}
