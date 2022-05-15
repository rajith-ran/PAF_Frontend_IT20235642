package com.UserManage;
import java.sql.*;

public class User {
	
	public Connection connect()
	{
			Connection con = null;
			try
			{
			
				Class.forName("com.mysql.jdbc.Driver");
				con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pafelectricity", "root", "tharaka4");
			
				//For testing
				System.out.print("Successfully connected");
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		return con;
	}
	
public String insertUser(String name, String age, String address, String email)
		{
			String output = "";
			try
		{
			Connection con = connect();
			if (con == null)
			{
				return "Error while connecting to the database for inserting.";
			}

			// create a prepared statement
			String query = " insert into user1 (`id`,`name`,`age`,`address`,`email`)"
					+ " values (?, ?, ?, ?, ? )";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, name);
			preparedStmt.setString(3, age);
			preparedStmt.setString(4, address);
			preparedStmt.setString(5, email);


			// execute the statement
			preparedStmt.execute();
			con.close();
			String newUser = readUser();
			output = "{\"status\":\"success\", \"data\": \"" +newUser + "\"}";
		}
		catch (Exception e)
		{
			output = "{\"status\":\"error\", \"data\":\"Error while inserting the user1.\"}";
			System.err.println(e.getMessage());
		}
			
		return output;
	}

public String readUser()
{
		String output = "";
		try
		{
				Connection con = connect();
					if (con == null)
					{
						return "Error while connecting to the database for reading.";
					}

					//Prepare the HTML table to be displayed
					output = "<table border='3'>"
							+ "<tr><th>User Name</th>"
							+"<th>User Age</th>"
							+ "<th>User Address</th>"
							+ "<th>User Email</th>"
							+ "<th>Update</th><th>Remove</th></tr>";
					
					String query = "select * from user1";
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(query);

					// iterate through the rows in the result set
					while (rs.next())
					{
						String id  = Integer.toString(rs.getInt("id"));
						String name  = rs.getString("name");
						String age  = rs.getString("age");
						String address =rs.getString("address");
						String email  = rs.getString("email");
						


						// Add a row into the html table
						output += "<tr><td><input id='hidUserIDUpdate'name='hidUserIDUpdate'type='hidden' value='" + id  + "'>"+ name  + "</td>";
						output += "<td>" + age + "</td>";
						output += "<td>" + address + "</td>";
						output += "<td>" + email + "</td>";


						// buttons
						output += "<td><input name='btnUpdate' type='button' value='Update' "
								+ "class='btnUpdate btn btn-secondary' data-userid='" + id + "'></td>"
								+"<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-userid='" + id + "'></td></tr>"; 
					}
					con.close();


					// Complete the html table
					output += "</table>";
			}
			catch (Exception e)
			{
				output = "Error while reading the user1.";
				System.err.println(e.getMessage());
			}
			return output;
		}
	public String deleteUser(String id)
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{
				return "Error while connecting to the database for deleting.";
			}


			// create a prepared statement
			String query = "delete from user1 where id=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, Integer.parseInt(id));

			// execute the statement
			preparedStmt.execute();
			con.close();

			//output = "Deleted successfully";
			String newUser = readUser();
			output = "{\"status\":\"success\", \"data\": \"" +newUser + "\"}";
			}
		catch (Exception e)
		{
			//output = "Error while deleting the user.";
			output = "{\"status\":\"error\", \"data\":\"Error while deleting the user1.\"}";
			System.err.println(e.getMessage());
		}
		
		return output;
	}

	//method to update bill details in DB
	public String updateUser(String id, String name, String age,String address,String email)
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{
				return "Error while connecting to the database for updating.";
			}
				
			// create a prepared statement
			String query = "UPDATE user1 SET name=?,age=?,address=?,email=? WHERE id=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setString(1, name);
			preparedStmt.setString(2, age);
			preparedStmt.setString(3, address);
			preparedStmt.setString(4, email);
			preparedStmt.setInt(5, Integer.parseInt(id));

			// execute the statement
			preparedStmt.execute();
			con.close();
			//output = "Updated User details successfully";
			String newUser = readUser();
			output = "{\"status\":\"success\", \"data\": \"" +newUser + "\"}"; }
		catch (Exception e)
		{
			//output = "Error while updating the User Details.";
			output = "{\"status\":\"error\", \"data\":\"Error while updating the user1.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}


}

