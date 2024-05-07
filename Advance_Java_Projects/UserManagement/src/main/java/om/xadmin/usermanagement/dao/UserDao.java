package om.xadmin.usermanagement.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xadmin.usermanagement.bean.User;

public class UserDao {

	private String url = "jdbc:mysql://localhost:3306/users";
	private String username="root";
	private String password="root";
	private String jdbcDriver = "com.mysql.cj.jdbc.Driver";
	
	private static final String INSERT_USER_SQL = "insert into users" + "(name, email, country) values" +"(?,?,?);";
	
	private static final String SELECT_USER_BY_ID = "select id,name,email,country from users where id =?";
	private static final String SELECT_ALL_USERS = "select * from users";
	private static final String DELETE_USERS_SQL = "delete from users where id=?;";
	private static final String UPDATE_USERS_SQL = "update users set name=?,email=?,country=? where id = ?;";
	public UserDao() {
		
	}
	
	protected Connection getConnection() {
		Connection con = null;
		
		try {
			Class.forName("jdbcDriver");
			con = DriverManager.getConnection(url,username,password);
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
		
	}
	
	//insert user
	public void insertUser(User user) {
		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement(INSERT_USER_SQL);
				ps.setString(1, user.getName());
				ps.setString(2, user.getEmail());
				ps.setString(3, user.getCountry());
				
				System.out.println(ps);
				ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
			
		
	
	
	//select user by id
	public User selectUser(int id) throws SQLException {
		User user = null;
		
	
		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement(SELECT_USER_BY_ID);
				ps.setInt(1, id);
				
				ResultSet rs = ps.executeQuery();
				
				while(rs.next())
				{
					String name = rs.getString("name");
					String email = rs.getString("email");
					String country = rs.getString("country");
					user = new User(id,name,email,country);
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			return user;
	
	}


	//select all users
	public List<User> selectUsers() throws SQLException{
		List<User> users = new ArrayList<>();
		try {
			Connection con = getConnection();
			
			PreparedStatement ps = con.prepareStatement(SELECT_ALL_USERS);
			System.out.println(ps);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String country =rs.getString("country");
				users.add(new User(id,name,email,country));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
		return users;
	}
	
	
	//update user
	public boolean updateUser(User user) throws SQLException {
		boolean rowUpdated;
		
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement(UPDATE_USERS_SQL);
			System.out.println("updated user: "+ps);
			ps.setString(1, user.getName());
			ps.setString(1, user.getEmail());
			ps.setString(3, user.getCountry());
			ps.setInt(4, user.getId());
			
			rowUpdated = ps.executeUpdate() >0 ;
		
		
		return rowUpdated;
		
	}
	
	//delete user
	public boolean deleteUser(int id) throws SQLException
	{
		boolean rowDeleted;
		Connection con = getConnection();
		PreparedStatement ps = con.prepareStatement(DELETE_USERS_SQL);
		ps.setInt(1, id);
		rowDeleted = ps.executeUpdate() > 0;
		return rowDeleted;
	}
	
	}
	
	
	

