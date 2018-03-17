package br.com.algarcsm.principal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import br.com.algarcsm.dao.StatusDAO;

public class Principal {

	public static void main(String[] args) throws IOException {
		
		
		//HASH STATUS ARQUIVO
		Map<String, Integer> mapStatusArq = new HashMap<String,Integer>();
		mapStatusArq.put("id_status_Arq", 5);
	
		//status anteriores do status 5  R======> 35,1
		ArrayList<Integer> statusAnt = new ArrayList<Integer>();
		statusAnt.add(35);
		statusAnt.add(1);
	
		Map<Integer, ArrayList<Integer> > mapFluxoStatus  = new HashMap<Integer, ArrayList<Integer> >();
		
		mapFluxoStatus.put(5, statusAnt);
//		statusto1.setIdStatus(7);
//		statusto1.setDs_status("REPASSADO");
//		statusto1.setDataStatus("17/03/2018");
//		
//		StatusDAO.inserirRegistro(statusto1);
		StatusDAO.manipulaRegistro(mapFluxoStatus);
		
		System.out.println("");
	}

}
