package br.com.algarcsm.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	static String url = "jdbc:mysql://localhost:3306/algarcsm";
	static String username = "root";
	static String password = "";

	
	public static Connection getConnection(){
		try{
			System.out.println("Conectado!!");
			return DriverManager.getConnection(url,username,password);
		}catch(SQLException e){
			throw new RuntimeException();
		}
	}

}
