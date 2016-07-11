package Converter;

import java.util.Random;

public class StructureConverter {
	
	public static  String removeSpecialCaracter(String value){
		return value.replaceAll("[^a-zA-Z0-9]", "");
	}
	
	public static String getTypeColumn(String typeColumn){
		String type = "CHAR ";
		if(typeColumn.trim().toLowerCase().contains("decimal") && !typeColumn.trim().contains(".")){
			type = "INT";
		}else if(typeColumn.trim().toLowerCase().contains("decimal") && typeColumn.trim().contains(".")){
			type = "DOUBLE";
		}
		return type;
	}
	
	public static String getLength(String length, String typeColumn){
		String length2 = length;
		if(typeColumn.trim().toLowerCase().contains("decimal") && typeColumn.trim().contains(".")){
			String contain = typeColumn.substring(typeColumn.indexOf("(")+1, typeColumn.indexOf(")")).replace(".", ",").trim();
			int number1 = Integer.parseInt(contain.substring(0, contain.indexOf(",")));
			int number2 = Integer.parseInt(contain.substring(contain.indexOf(",")+1));
			length2 = number1+number2+","+number2;
		}
		return length2;
	}
			
	public static String getColumnName(String fieldName, boolean nameTable){
		fieldName = fieldName.trim()+" ";
		String columnName = "";
		int count = 0;
		while (count < 3 && (fieldName != null && !fieldName.isEmpty())) {
			String word = fieldName.substring(0, fieldName.indexOf(" ")).trim();
			if(word.length() > 4){
				if(count == 0){	
					if(!nameTable){
						columnName += removeSpecialCaracter(word).trim().toLowerCase().substring(0, 4)+new Random().nextInt(9);
					}else{
						columnName += removeSpecialCaracter(word).trim().toLowerCase();
					}
				}else{
					if(!nameTable){
						columnName += removeSpecialCaracter(word).trim().substring(0, 4)+new Random().nextInt(9);
					}else{
						columnName += removeSpecialCaracter(word).trim();
					}
				}
			}else{
				if(!removeSpecialCaracter(word).trim().isEmpty()){
					columnName += word;
				}
			}
			
			fieldName = fieldName.substring(fieldName.indexOf(word)+word.length()+1);			
			count++;
		}
		if(fieldName.trim().isEmpty() && !nameTable){
			columnName += new Random().nextInt(100)+new Random().nextInt(60);
		}
		return columnName;
	}
	
	public static  String getCommentField(String description){
		String commentField = "";
		if(description.length() > 50){
				commentField = description.substring(0, 50);
		}else{
			commentField = description;
		}
		return 	commentField;
	}
	
}
