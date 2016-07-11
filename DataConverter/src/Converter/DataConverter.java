package Converter;


import java.sql.SQLException;
import java.util.ArrayList;

import Manipulation.ReadStructure;

public class DataConverter {	
	
	private  ArrayList<StructureFile> listStructure_type_0_9 ;	
	private  ArrayList<StructureFile> listStructure_type_8 ;	
	private  ArrayList<StructureFile> listStructure_type_1 ;
	private  ArrayList<StructureFile> listStructure_type_2 ;
	
	private  ArrayList<ArrayList<StructureFile>>  listData_type_0_9 ;	
	private  ArrayList<ArrayList<StructureFile>>  listData_type_8 ;	
	private  ArrayList<ArrayList<StructureFile>>  listData_type_1 ;
	private  ArrayList<ArrayList<StructureFile>>  listData_type_2 ;
	
	private ReadStructure readStruct;
		
	public DataConverter() {
		readStruct = new ReadStructure();
		listStructure_type_0_9 = new  ArrayList<StructureFile>() ;	
		listStructure_type_8 = new  ArrayList<StructureFile>() ;		
		listStructure_type_1 = new  ArrayList<StructureFile>() ;	
		listStructure_type_2 = new  ArrayList<StructureFile>() ;	
		
		listData_type_0_9 = new  ArrayList<ArrayList<StructureFile>>() ;	
		listData_type_8 =   new  ArrayList<ArrayList<StructureFile>>() ;		
		listData_type_1 =   new  ArrayList<ArrayList<StructureFile>>() ;	
		listData_type_2 =   new  ArrayList<ArrayList<StructureFile>>() ;	
	}
	
	public int testLine(String line){
		String column = line.substring(0, line.indexOf(";"));
		if(column.contains("Record Type ") && column.contains(":")){
			String value = column.substring(column.indexOf("Record Type ")+12, column.indexOf("Record Type ")+13);
			return Integer.parseInt(value);			
		}else{
			return -1;
		}
	}
	
	public void getType(boolean read, String line, int type) throws SQLException{
		if(read){
			StructureFile structureLine = readStruct.readLineStructure(line);
			if(structureLine != null){
				if(type == 0 || type == 9){
					listStructure_type_0_9.add(structureLine);
				}else if(type == 8){
					listStructure_type_8.add(structureLine);
				}else if(type == 2){
					listStructure_type_2.add(structureLine);
				}else if(type == 1){
					listStructure_type_1.add(structureLine);
				}
			}
		}else{
			if(type == 0 || type == 9){
				readStruct.readLineData(line, listStructure_type_0_9);
			}else if(type == 8){
				readStruct.readLineData(line, listStructure_type_8);
			}else if(type == 2){				
				readStruct.readLineData(line, listStructure_type_2);
			}else if(type == 1){
				readStruct.readLineData(line, listStructure_type_1);

			}
		}
	}
	
	
	public int importedData_type(){
		int imported = 0;
		if(!listStructure_type_0_9.isEmpty()){
			imported++;
		}
		if(!listStructure_type_8.isEmpty()){
			imported++;
		}
		if(!listStructure_type_1.isEmpty()){
			imported++;
		}
		if(!listStructure_type_2.isEmpty()){
			imported++;
		}
		return imported;		
	}
	
	public int importedData_data(){
		int imported = 0;
		if(!listData_type_0_9.isEmpty()){
			imported++;
		}
		if(!listData_type_8.isEmpty()){
			imported++;
		}
		if(!listData_type_1.isEmpty()){
			imported++;
		}
		if(!listData_type_2.isEmpty()){
			imported++;
		}
		return imported;		
	}
	
	public String getNameTable(int type){
		String name = "";
		if(type == 0 || type == 9 && (!listStructure_type_0_9.isEmpty())){
			StructureFile structure = listStructure_type_0_9.get(0);
			name = structure.getNameTable();
		}else if(type == 8){
			StructureFile structure = listStructure_type_8.get(0);
			name = structure.getNameTable();
		}else if(type == 2){
			StructureFile structure = listStructure_type_2.get(0);
			name = structure.getNameTable();
		}else if(type == 1){
			StructureFile structure = listStructure_type_1.get(0);
			name = structure.getNameTable();
		}
		return name;
	}
	
	public String getDescriptionTable(int type){
		String description = "";
		if(type == 0 || type == 9 && (!listStructure_type_0_9.isEmpty())){
			StructureFile structure = listStructure_type_0_9.get(0);
			description = structure.getDescriptionTable();
		}else if(type == 8){
			StructureFile structure = listStructure_type_8.get(0);
			description = structure.getDescriptionTable();
		}else if(type == 2){
			StructureFile structure = listStructure_type_2.get(0);
			description = structure.getDescriptionTable();
		}else if(type == 1){
			StructureFile structure = listStructure_type_1.get(0);
			description = structure.getDescriptionTable();
		}
		return description;
	}

	public  ArrayList<StructureFile> getListStructure_type_0_9() {
		return listStructure_type_0_9;
	}

	public  ArrayList<StructureFile> getListStructure_type_8() {
		return listStructure_type_8;
	}

	public  ArrayList<StructureFile> getListStructure_type_1() {
		return listStructure_type_1;
	}

	public  ArrayList<StructureFile> getListStructure_type_2() {
		return listStructure_type_2;
	}

	public  ArrayList<ArrayList<StructureFile>> getListData_type_0_9() {
		return listData_type_0_9;
	}

	public  ArrayList<ArrayList<StructureFile>> getListData_type_8() {
		return listData_type_8;
	}

	public  ArrayList<ArrayList<StructureFile>> getListData_type_1() {
		return listData_type_1;
	}

	public  ArrayList<ArrayList<StructureFile>> getListData_type_2() {
		return listData_type_2;
	}
	
}
