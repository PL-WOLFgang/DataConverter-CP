package Manipulation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Connection.ConnectF;
import Converter.StructureFile;

public class IOData {
	
	public int generateCreateTable(String nameTable, String descriptTable, ArrayList<StructureFile> listStructure) throws SQLException{
		int exec = 0 ;
		if(nameTable != null && !nameTable.isEmpty() && listStructure != null && !listStructure.isEmpty()){
			StringBuffer sql = new StringBuffer(" CREATE TABLE IF NOT EXISTS "+nameTable+" ("	);
			
			StructureFile structure = null;
			String comma = "";
			for (int i = 1; i < listStructure.size(); i++) {
				structure = listStructure.get(i);
				if(!structure.getDescriptionField().toLowerCase().contains("blank") && !structure.getDescriptionField().trim().toLowerCase().contains("redefines")){
					sql.append(comma+" "+structure.getNameField()+" "+structure.getTypeField().trim()+"("+structure.getLegthField().trim()+") COMMENT '"+structure.getDescriptionField()+"'  ");
					comma = ",";
				}
			}
			sql.append(
						 ", sql_rowid bigint(10) NOT NULL auto_increment,"
						+" PRIMARY KEY  (sql_rowid)"
						+") ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT = '"+descriptTable+"'; ");
			
			Connection con =  ConnectF.getConnection();			
			PreparedStatement pstmtInsert = con.prepareStatement(sql.toString());
			exec = pstmtInsert.executeUpdate();
			pstmtInsert.close();
			con.close();			
		}
		return exec;
	}

	public int insertData(String nameTable, ArrayList<StructureFile> listData) throws SQLException{
		int exec = 0 ;
		Connection con =  ConnectF.getConnection();
		
		if(nameTable != null && !nameTable.isEmpty() && listData != null && !listData.isEmpty()){
				
			StringBuffer sql = new StringBuffer(" INSERT INTO  "+nameTable+" ");
			StructureFile structure = null;
			
			StringBuffer values = new StringBuffer();
			
			String comma = "";
			
			for (int j = 0; j < listData.size(); j++) {
				structure = listData.get(j);			
				 if(!structure.getDescriptionField().toLowerCase().contains("blank") && !structure.getDescriptionField().toLowerCase().contains("redefines")){
					if(structure.getValueField() != null && structure.getTypeField().contains("CHAR")){
						values.append (comma+" '"+structure.getValueField().trim()+"' ");
					}else{
						values.append (comma+" "+structure.getValueField()+" ");
					}
					comma = ",";
				 }
			}
			int rowid = countRegister(nameTable, con)+1;
			
			sql.append(" VALUES ( "+values+", "+(rowid)+" ); ");	
			
			System.out.println("insert: "+sql.toString());
			PreparedStatement pstmtInsert = con.prepareStatement(sql.toString());
			exec = pstmtInsert.executeUpdate();
			
			if(exec > 0){
				exec++;
			}
			pstmtInsert.close();
			con.close();			
		}
		return exec;
	}
	
	public int countRegister(String nameTable, Connection conn) throws SQLException{
		int count = 0;
		PreparedStatement pstmtInsert = conn.prepareStatement("SELECT COUNT(sql_rowid) AS count FROM "+nameTable+" ");
		ResultSet rs = pstmtInsert.executeQuery();
		if (rs.next()) {
			count = rs.getInt("count");
		}
		pstmtInsert.close();
		rs.close();
		return count;
	}
	
	
}
