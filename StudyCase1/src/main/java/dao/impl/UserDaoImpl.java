package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import dao.DataSourceFactory;
import dao.UserDao;
import model.User;

public class UserDaoImpl implements UserDao  {

	public Optional<User> find(Long id) {
		// TODO Auto-generated method stub
		
		try(Connection conn=DataSourceFactory.getConnection())
		{
			PreparedStatement stmt=conn.prepareStatement("select * from user where id=?");
			stmt.setLong(1,id);
			ResultSet rs=stmt.executeQuery();
			if(rs.next())
			{
				User user=new User(rs.getLong("id"),rs.getString("username"),rs.getString("password"));
				return Optional.of(user);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return Optional.empty();
	}

	public List<User> findAll() {
		// TODO Auto-generated method stub
		List<User> result=new ArrayList<>();
		
		try(Connection conn =DataSourceFactory.getConnection())
		{
			Statement stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery("select * from user");
			while(rs.next())
			{
				User user=new User(rs.getLong("id"),rs.getString("username"),rs.getString("password"));
				result.add(user);
			}
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return result;
	}

	public boolean save(User user) {
		// TODO Auto-generated method stub
		
		try(Connection conn=DataSourceFactory.getConnection())
		{
			PreparedStatement stmt=conn.prepareStatement("insert into user values (NULL,?,?)");
			stmt.setString(1, user.getUsername());
			stmt.setString(2, user.getPassword());
			int result=stmt.executeUpdate();
			if(result==1)
				return true;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return false;
	}

	public boolean update(User user) {
		// TODO Auto-generated method stub
		
		try(Connection conn=DataSourceFactory.getConnection())
		{
			PreparedStatement stmt=conn.prepareStatement("update user set username=?, password=? where id=?");
			stmt.setString(1,user.getUsername());
			stmt.setString(2, user.getPassword());
			stmt.setLong(3, user.getId());
			int result=stmt.executeUpdate();
			if(result==1)
			{
				return true;
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return false;
	}

	public boolean delete(Long id) {
		// TODO Auto-generated method stub
		
		try(Connection conn=DataSourceFactory.getConnection())
		{
			PreparedStatement stmt=conn.prepareStatement("delete from user where id=?");
			stmt.setLong(1, id);
			int result=stmt.executeUpdate();
			if(result==1)
				return true;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		
		return false;
	}

	public Optional<User> findByUserName(String username) {
		// TODO Auto-generated method stub
		try(Connection conn=DataSourceFactory.getConnection())
		{
			PreparedStatement stmt=conn.prepareStatement("select * from user where username=?");
			stmt.setString(1,username);
			ResultSet rs=stmt.executeQuery();
			if(rs.next())
			{
				User user=new User(rs.getLong("id"),rs.getString("username"),rs.getString("password"));
				return Optional.of(user);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return Optional.empty();
	}
	
	private static class SingletonHelper
	{
		private static final UserDaoImpl INSTANCE=new UserDaoImpl();
	}

	public static UserDao getInstance()
	{
		return SingletonHelper.INSTANCE;
	}
	
	
}
