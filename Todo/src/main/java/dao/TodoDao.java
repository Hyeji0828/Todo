package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dto.Todo;

public class TodoDao {
	
	private static String dburl = "jdbc:mysql://localhost:3306/exampledb";
	private static String dbuser = "dbuser";
	private static String dbpasswd = "db123!";
	
	public int addTodo(Todo todo) {
		int insertCount = 0;
		
		// Load JDBC Driver
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("JDBC Driver Loading Error");
		}
			
		String sql = "INSERT INTO todo (id, title, name, sequence, type) VALUES(?,?,?,?,?);";
		
		// Connect
		try (Connection connection = DriverManager.getConnection(dburl, dbuser, dbpasswd);
				PreparedStatement ps = connection.prepareStatement(sql);){
			ps.setLong(1, todo.getId());
			ps.setString(2, todo.getTitle());
			ps.setString(3, todo.getName());
			ps.setInt(4, todo.getSequence());
			ps.setString(5, todo.getType());
			
			try {
				insertCount = ps.executeUpdate();
			}catch (Exception e) {
				e.printStackTrace();
				System.out.println("SQL Update Executing Error");
			}
				
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("Insert Error");
		}		
		return insertCount;
	}
	
	public List<Todo>getTodos(){
		List<Todo> list = new ArrayList<>(); 
		
		// Load JDBC Driver
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("JDBC Driver Loading Error");
		}
		
		String sql = "SELECT id, title, name, sequence, type, regdate FROM todo";
		try(Connection connection = DriverManager.getConnection(dburl, dbuser, dbpasswd);
				PreparedStatement ps = connection.prepareStatement(sql)){
			
			try(ResultSet rs = ps.executeQuery()){
				
				// Retreive values
				while(rs.next()) {
					Long id = rs.getLong("id");
					String title = rs.getString("title");
					String name = rs.getString("name");
					int sequence = rs.getInt("sequence");
					String type = rs.getString("type");
					String regdate = rs.getString("regdate");
					
					Todo todo = new Todo(id, name, regdate, sequence, title, type);
					list.add(todo);
				}
			}catch (Exception e) {
				e.printStackTrace();
				System.out.println("SQL Query Executing Error");
			}
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("Query Error");
		}		
		
		return list;
	}
	
	public int updateTodo(Todo todo, String type) {
		int updateCount = 0;
		
		// Load Driver
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("JDBC Driver Loading Error");
		}
		
		String sql = "UPDATE todo SET type=? WHERE id=?";
		
		// Connect
		try(Connection connection = DriverManager.getConnection(dburl,dbuser,dbpasswd);
				PreparedStatement ps = connection.prepareStatement(sql)){
			ps.setString(1, type);
			ps.setLong(2, todo.getId());
			
			try {
				updateCount = ps.executeUpdate();
			}catch (Exception e) {
				e.printStackTrace();
				System.out.println("SQL Update Executing Error");
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("Update Error");
		}
		
		return updateCount;
	}

	public Todo getTodo(Long id) {
		
		Todo todo = null;
		
		// Load JDBC Driver
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("JDBC Driver Loading Error");
		}
		
		String sql = "SELECT title, name, sequence, type, regdate FROM todo WHERE id=?";
		try(Connection connection = DriverManager.getConnection(dburl, dbuser, dbpasswd);
				PreparedStatement ps = connection.prepareStatement(sql)){
			
			ps.setLong(1, id);
			try(ResultSet rs = ps.executeQuery()){
				
				while(rs.next()) {
					// Retreive values
					String title = rs.getString("title");
					String name = rs.getString("name");
					int sequence = rs.getInt("sequence");
					String type = rs.getString("type");
					String regdate = rs.getString("regdate");
					
					todo = new Todo(id, name, regdate, sequence, title, type);
				}
					
			}catch (Exception e) {
				e.printStackTrace();
				System.out.println("SQL Query Executing Error");
			}
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("Query Error");
		}		
		
		return todo;
	}
}
