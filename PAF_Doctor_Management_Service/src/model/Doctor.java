package model;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Doctor {

	public Connection connect() {
		Connection con = null;

		try {

			Class.forName("com.mysql.jdbc.Driver");
			con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/paf_db", "root", "");	
			System.out.print("Successfully connected");

		} catch (Exception e) {

			System.out.print("connection fail");
			e.printStackTrace();
			System.out.print(e);
		}

		return con;
	}
	
	
//----------------------------------------------view Doctor Details-------------------------------------------
	
	public String viewDoctorDetails() {

		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			
			output = "<table border='1'><tr>"
					+ "<th>First Name</th>" 
					+ "<th>Last Name</th>" 
					+ "<th>Email</th>"
					+ "<th>Password</th>"
					+ "<th>Phone No</th>"
					+ "<th>Specalization</th>"
					+ "<th>Charges</th>"
					+ "<th>type</th><th>Update</th><th>Delete</th></tr>";
			
			String query = "select * from registereddoctor";
			
			Statement statement = con.createStatement();
			ResultSet resultSet = statement.executeQuery(query);

			
			while (resultSet.next()) {
				int d_id =    resultSet.getInt("doctorid");
				String fname =  resultSet.getString("firstname");
				String lname =  resultSet.getString("lastname");
				String demail =  resultSet.getString("email");
				String dpass =  resultSet.getString("password");
				String dphone =  resultSet.getString("phonenumber");
				String dspec =  resultSet.getString("specalization");
				String dcharge = Double.toString(resultSet.getDouble("charges"));
				String dtype =  resultSet.getString("type");

				
				output += "<tr><td><input id='hidItemIDUpdate' name='hidItemIDUpdate' type='hidden' value='" +d_id+ "'>" +fname+"</td>";
				output += "<td>" + lname + "</td>";
				output += "<td>" + demail + "</td>";
				output += "<td>" + dpass + "</td>";
				output += "<td>" + dphone + "</td>";
				output += "<td>" + dspec + "</td>";
				output += "<td>" + dcharge + "</td>";
				output += "<td>" + dtype + "</td>";


				output += "<td><input name='btnUpdate' type='button'value='Update'class='btnUpdate btn btn-secondary'></td> "
						+ "<td><input name='btnRemove' type='button' value='Remove'class='btnRemove btn btn-danger' data-itemid='"+d_id+"'>" + "</td></tr>";
				
			}

			con.close();
		
			output += "</tr></table>";

		} catch (Exception e) {
			output = "Error while reading the Doctor Details.";
			System.err.println(e.getMessage());
		}

		return output;
	}
	
	
	//----------------------------------------------insert Doctor Details-------------------------------------------
	
		public String addDoctor(String first_name, String last_name, String D_email,String D_password, String phoneNo, String specali ,String dcharges, String Dtype) {

			String output = "";
			try {

				Connection con = connect();

				if (con == null) {
					return "Error while connecting to the database";
				}

			
				String query = " INSERT INTO registereddoctor (firstname, lastname,email,password,phonenumber,specalization,charges,type) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
				PreparedStatement preparedStatement = con.prepareStatement(query);

	
				preparedStatement.setString(1, first_name);
				preparedStatement.setString(2, last_name);
				preparedStatement.setString(3, D_email);
				preparedStatement.setString(4, D_password);
				preparedStatement.setString(5, phoneNo);
				preparedStatement.setString(6, specali);
				preparedStatement.setDouble(7, Double.parseDouble(dcharges));
				preparedStatement.setString(8, Dtype);


				preparedStatement.execute();
				con.close();
				
				String newDoctor = viewDoctorDetails();
				output = "{\"status\":\"success\", \"data\": \"" + newDoctor + "\"}";

			} catch (Exception e) {
				output = "{\"status\":\"error\", \"data\": \"Error while inserting the newDoctor details.\"}";
				System.err.println(e.getMessage());
			}

			System.out.print(output);
			return output;
		}
		
		
		
		
		
		//============================= Delete Doctor Details ==============================	
		
		public String RemoveDoctor(String DoC_id) {
			String output = "";
			try {

				Connection con = connect();
				if (con == null) {
					return "Error while connecting to the database for deleting.";
				}

				
				String query = "DELETE FROM registereddoctor WHERE doctorid=?";
				PreparedStatement preparedStatement = con.prepareStatement(query);

			
				//preparedStatement.setString(1, DoC_id);
				preparedStatement.setInt(1, Integer.parseInt(DoC_id));
				preparedStatement.execute();
				con.close();
		
				String newDoctor = viewDoctorDetails();
				output = "{\"status\":\"success\", \"data\": \"" + newDoctor + "\"}";
		 
			}
			catch (Exception e)
			{
				output = "{\"status\":\"error\", \"data\":\"Error while deleting the newDoctor.\"}";
				System.err.println(e.getMessage());
		 
			}
		 
			return output;
		 
		}
	
		
		
		//============================= Update doctor Details ==============================
		
				public String updateDoctor(String id ,String first_name, String last_name, String D_email,String D_password, String phoneNo, String specali ,String dcharges, String Dtype) {

					String output = "";

					try {
						Connection con = connect();
						if (con == null) {
							return "Error while connecting to the database for updating.";
						}
						// create a prepared statement
						String query = "UPDATE registereddoctor SET firstname =?,lastname =?,email =?,password=?,phonenumber =?,specalization =?,charges =?,type=? WHERE doctorid =?";
						PreparedStatement preparedStatement = con.prepareStatement(query);

						// binding values

						preparedStatement.setString(1, first_name);
						preparedStatement.setString(2, last_name);
						preparedStatement.setString(3, D_email);
						preparedStatement.setString(4, D_password);
						preparedStatement.setString(5, phoneNo);
						preparedStatement.setString(6, specali);
						preparedStatement.setDouble(7, Double.parseDouble(dcharges));
						preparedStatement.setString(8, Dtype);
						preparedStatement.setInt(9, Integer.parseInt(id));
						// execute the statement
						preparedStatement.execute();
						con.close();
						
						String newDoctor = viewDoctorDetails();
						output = "{\"status\":\"success\", \"data\": \"" +newDoctor + "\"}";
						 
					}
					catch (Exception e)
					{
						output = "{\"status\":\"error\", \"data\": \"Error while updating the newDoctor.\"}";
						 
						System.err.println(e.getMessage());
					}
						
					return output;
						 
				} 
				
				
			
		
	
}
