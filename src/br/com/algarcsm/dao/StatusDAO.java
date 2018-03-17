package br.com.algarcsm.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import br.com.algarcsm.arquivo.StatusTO;
import br.com.algarcsm.connection.ConnectionFactory;

import com.mysql.jdbc.PreparedStatement;

public class StatusDAO {
	
	public static boolean consultarRegistro(int idStatusPesq){
		
		String sql = "select id_status from cstb_registro_historico where id_status = ?";
		StatusTO statusTO = new StatusTO();
		
		boolean encontou = false;
		
		try {
			PreparedStatement stmt = (PreparedStatement) ConnectionFactory.getConnection().prepareStatement(sql);
			ResultSet rs = null;
			
			stmt.setInt(1, idStatusPesq);
			rs = stmt.executeQuery();
			
			if(rs != null){
				while(rs.next()){
					statusTO.setIdStatus(rs.getInt("ID_STATUS"));
				}
			}
			
			if(statusTO.getIdStatus() == idStatusPesq){
				encontou = true;
			}else{
				encontou = false;
			}

			stmt.close();
						
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return encontou;
		
	}
	
	public static void inserirRegistro(StatusTO statusTO){
		String sql = "insert into cstb_registro_historico values(?,?,?)";
		
		try {
			PreparedStatement stmt = (PreparedStatement) ConnectionFactory.getConnection().prepareStatement(sql);
			
			stmt.setInt(1, statusTO.getIdStatus());
			stmt.setString(2, statusTO.getDs_status());
			stmt.setString(3, statusTO.getDataStatus());
			
			stmt.execute();
			stmt.close();
			
			System.out.println("Contato adicionado com sucesso...!");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void manipulaRegistro(Map<Integer, ArrayList<Integer>> mapFluxoStatus) {
		 
		Set<Integer> keys = mapFluxoStatus.keySet();
		
		ArrayList<Integer> valoresAnt = new ArrayList<Integer>();
		
        for (Integer key : keys) {
            valoresAnt = mapFluxoStatus.get(key); 
        }
		
        for (Integer idStatusProc : valoresAnt) {
			boolean encontrado = consultarRegistro(idStatusProc);
			
			StatusTO statusTO = new StatusTO();
			statusTO.setIdStatus(idStatusProc);
			statusTO.setDs_status(consultaDadosStatus(idStatusProc));
			statusTO.setDataStatus("15/01/2017"); // criar para data do status funcao
			
			if(encontrado){
				atualizaStatus(statusTO);
			}else{
				inserirRegistro(statusTO);
			}
		}
	}

	
private static String consultaDadosStatus(Integer idStatusProc) {
	String sql = "select descricao from status where id_status = ?";
	String descricao = null;
	
	try {
		PreparedStatement stmt = (PreparedStatement) ConnectionFactory.getConnection().prepareStatement(sql);
		ResultSet rs = null;
		
		stmt.setInt(1, idStatusProc);
		rs = stmt.executeQuery();
		
		if(rs != null){
			while(rs.next()){
				descricao = rs.getString("descricao");
			}
		}
		

		stmt.close();
					
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return descricao;
}

public static void atualizaStatus(StatusTO statusTo){
		
		String sql = "update cstb_registro_historico set ds_status=?, data_registro=?  where id_status = ?";
		
		try {
			PreparedStatement stmt = (PreparedStatement) ConnectionFactory.getConnection().prepareStatement(sql);
			
			
			stmt.setString(1, statusTo.getDs_status());
			stmt.setString(2, statusTo.getDataStatus());
			stmt.setInt(3, statusTo.getIdStatus());
			
			stmt.execute();
			
			stmt.close();
						
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	
}
