package com.xadmin.medicalManagemet.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xadmin.medicalManagemet.bean.Medicine;

public class MedicineDao {

	private String jdbcURL ="jdbc:mysql://localhost:3306/MedicineDB?useSSl=false";
	private String jdbcUserName = "root";
	private String jdbcPasswd = "W@2915djkq#";
	private String jdbcDriver = "com.mysql.cj.jdbc.Driver";
	
	private static final String INSERT_MEDICINES_SQL = "INSERT INTO medicines" + "(MediName,MediDetails,ForDisease) VALUE " + "(?,?,?);";
	
	private static final String SELECT_MEDICINE_BY_ID ="select id,MediName,MediDetails,ForDisease from medicines where id =?";
	private static final String SELECT_ALL_MEDICINE= "select * from medicines";
	private static final String DELETE_MEDICINES_SQL = "delete from medicines where id =?";
	private static final String UPDATE_MEDICINES_SQL = "update medicines set MediName=?,MediDetails=?,ForDisease=? where id =?;";
	
	public MedicineDao() {
		
	}
	
	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName(jdbcDriver);
			connection = DriverManager.getConnection(jdbcURL,jdbcUserName,jdbcPasswd);
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	//CRUD  operation method -------------->>>>>>>>>
	
	
	//insert medicine -------+++++++++----
	public void insertMedicines(Medicine medi) {
		System.out.println(INSERT_MEDICINES_SQL);
		try(Connection connection = getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(INSERT_MEDICINES_SQL)){
			preparedStatement.setString(1, medi.getMediName());	
			preparedStatement.setString(2, medi.getMediDetails());	
			preparedStatement.setString(3, medi.getForDisease());	
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		}catch(SQLException e) {
			printSQLException(e);
		}
	}
	
	
	//select medicines by id---------++++++++------------
	public Medicine selectmedicines(int id) {
		Medicine medi=null;
		//Step 1: Establishing a connection 
		try(Connection connection = getConnection();
				//step 2: create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MEDICINE_BY_ID);){
			preparedStatement.setInt(1,id);
			System.out.println(preparedStatement);
			//step 3: Execute the query or update query 
			ResultSet rs = preparedStatement.executeQuery();
			
			//step 4: process the ResultSet object.
			while(rs.next()) {
				String MediName = rs.getString("MediName");
				String MediDetails = rs.getString("MediDetails");
				String ForDisease = rs.getString("ForDisease");
				medi = new Medicine(id,MediName,MediDetails,ForDisease);
			}
		}catch(SQLException e) {
			printSQLException(e);
		}
		return medi;
	}
	
	
	//select all medicines .------------+++++++++++-----------
	public List<Medicine> selectAllMedicines(){
		List<Medicine> medi= new ArrayList<>();
		try(Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_MEDICINE);){
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			
			//step 4: process the ResultSet object.
			while(rs.next()) {
				int id = rs.getInt("id");
				String MediName = rs.getString("MediName");
				String MediDetails = rs.getString("MediDetails");
				String ForDisease = rs.getString("ForDisease");
				medi.add(new Medicine(id,MediName,MediDetails,ForDisease));
			}
		}catch(SQLException e) {
			printSQLException(e);
		}
		return medi;
	}
	
	//Update medicines ----------++++++--------
	
	public boolean updateMedicines(Medicine medi) throws SQLException {
		boolean rowUpdated;
		try(Connection connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(UPDATE_MEDICINES_SQL);){
			System.out.println("updateMedicines"+statement);
			statement.setString(1,medi.getMediName());
			statement.setString(2, medi.getMediDetails());
			statement.setString(3, medi.getForDisease());
			statement.setInt(4, medi.getId());
			
			rowUpdated = statement.executeUpdate()>0;
		}
		return rowUpdated;
				
	}
	
	//delete medicines----------------+++++++++++-------------
	public boolean deleteMedicines(int id) throws SQLException {
		boolean rowDeleted;
		try(Connection connection = getConnection();
		PreparedStatement statement = connection.prepareStatement(DELETE_MEDICINES_SQL);){
			statement.setInt(1, id);
			rowDeleted=statement.executeUpdate()>0;
		}
		return rowDeleted;
	}
	
	
	private void printSQLException(SQLException ex) {
			
			for(Throwable e : ex) {
				if(e instanceof SQLException) {
					e.printStackTrace(System.err);
					System.out.println("SQLState: "+((SQLException) e).getSQLState());
					System.out.println("Error Code:"+((SQLException)e).getErrorCode());
					System.out.println("Message: "+e.getMessage());
					Throwable t = ex.getCause();
					while(t!=null) {
						System.out.println("Cause: "+t);
						t=t.getCause();
					}
				}
			}
		}
	
	
}
