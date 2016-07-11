package Manipulation;

import java.sql.SQLException;
import java.util.ArrayList;

import Converter.StructureFile;

public class ReadStructure {	
	
	
	public  StructureFile readLineStructure(String line){
		String column = line.substring(0, line.indexOf(";"));
		if(column.contains("Record Type ") && column.contains(":")){
			StructureFile structureLine = new StructureFile();
			structureLine.setNameTable(line.substring(line.indexOf(":")+1, line.indexOf(";")));
			structureLine.setDescriptionTable(line.substring(line.indexOf(":")+1, line.indexOf(";")));

			return structureLine;
		}else if(!column.isEmpty()){
			StructureFile structureLine = new StructureFile();
			String lineCp = line;			
		
			structureLine.setNameField(lineCp.substring(0, lineCp.indexOf(";")));
			lineCp = lineCp.substring(lineCp.indexOf(";")+1);
			
			structureLine.setDescriptionField(lineCp.substring(0, lineCp.indexOf(";")));
			lineCp = lineCp.substring(lineCp.indexOf(";")+1);
			
			String legthField = lineCp.substring(0, lineCp.indexOf(";"));	
			structureLine.setLengthField2(Integer.parseInt(legthField));
			
			lineCp = lineCp.substring(lineCp.indexOf(";")+1);
			
			structureLine.setStartWord(Integer.valueOf(lineCp.substring(0, lineCp.indexOf(";"))));
			lineCp = lineCp.substring(lineCp.indexOf(";")+1);
			
			structureLine.setEndWord(Integer.valueOf(lineCp.substring(0, lineCp.indexOf(";"))));
			lineCp = lineCp.substring(lineCp.indexOf(";")+1);
			
			String typeField = "";
			if(lineCp.contains(";")){
				typeField = lineCp.substring(0, lineCp.indexOf(";"));
			}else{
				typeField = lineCp;
			}			
			structureLine.setLegthField(legthField, typeField);
			structureLine.setTypeField(typeField);
			return structureLine;
		}else{
			return null;
		}		
	}
	
	public  int readLineData(String line, ArrayList<StructureFile> listStruct) throws SQLException{		
		int insert = 0;
		if(listStruct  != null && !listStruct.isEmpty()){
			ArrayList<StructureFile> lineData = null;
			
			lineData = new ArrayList<>();
			
			StructureFile struture = null; 
			for (int i = 1; i < listStruct.size(); i++) {				
				
				struture = listStruct.get(i);
				
				String 	value = null;
				if(!line.isEmpty()){					
					if(i == 1){
						value = line.substring(0, struture.getLengthField2());	
					}else{
						if(!struture.getDescriptionField().toLowerCase().contains("blank") &&  (!struture.getDescriptionField().toLowerCase().contains("redefines")))
							value = line.substring(0, struture.getLengthField2());
					}	
					line = line.substring(struture.getLengthField2());
				}
				struture.setValueField(value);				
				lineData.add(struture);
				
				
			}
			IOData ioD = new IOData();
			insert = ioD.insertData(listStruct.get(0).getNameTable(), lineData);
		}
		return insert;
	}
	
	public  int readLineData_type1(String line, ArrayList<StructureFile> listStruct) throws SQLException{		
		int insert = 0;
		if(listStruct  != null && !listStruct.isEmpty()){
			ArrayList<StructureFile> lineData = null;
			
			lineData = new ArrayList<>();
			
			StructureFile struture = null; int previous = 0;	
			for (int i = 1; i < listStruct.size(); i++) {				
				
				struture = listStruct.get(i);
				
				struture.setNameField2(listStruct.get(i).getNameField());
				
				String 	value = null;
				if(!line.isEmpty()){					
					if(i == 1){
						value = line.substring(previous, struture.getEndWord());	
						previous = struture.getEndWord();
						previous = previous +1;
					}else if(i == 2){
						if(!struture.getDescriptionField().toLowerCase().contains("blank") &&  (!struture.getDescriptionField().toLowerCase().contains("redefines")))
							value = line.substring(previous-1, struture.getEndWord());
						previous = struture.getEndWord();
					}
					else if(struture.getStartWord() < 209 ){
						if(!struture.getDescriptionField().toLowerCase().contains("blank") &&  (!struture.getDescriptionField().toLowerCase().contains("redefines")))
							value = line.substring(previous-1, struture.getEndWord()-1);
						previous = struture.getEndWord();
					}else{
						if(!struture.getDescriptionField().toLowerCase().contains("blank") &&  (!struture.getDescriptionField().toLowerCase().contains("redefines")))
							value = line.substring(previous-2, struture.getEndWord()-2);
						previous = struture.getEndWord();
					}					
				}
				struture.setValueField(value);				
				lineData.add(struture);				
				
			}
			IOData ioD = new IOData();
			insert = ioD.insertData(listStruct.get(0).getNameTable(), lineData);
		}
		return insert;
	}	
}
