
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;

import Converter.DataConverter;
import Manipulation.IOData;

public class Main {
	
	
	public static void main(String[] args) {	
		
		try {	
			String      line = null;  				
			FileReader  reader = new FileReader("C:\\Temp\\read.csv");  
			BufferedReader file = new BufferedReader(reader);	
			
			DataConverter converter = new DataConverter();
			int type = 0;
			while((line = file.readLine())!= null){
				int ret = converter.testLine(line);
				if(ret != -1){
					type = ret;
				}
				converter.getType(true, line, type);
			}			
			file.close();  
			reader.close();			
			
			if(converter.importedData_type() > 0){
				IOData ioData = new IOData();
				ioData.generateCreateTable(converter.getNameTable(0), converter.getDescriptionTable(0), converter.getListStructure_type_0_9());
				ioData.generateCreateTable(converter.getNameTable(8), converter.getDescriptionTable(8), converter.getListStructure_type_8());
				ioData.generateCreateTable(converter.getNameTable(2), converter.getDescriptionTable(2), converter.getListStructure_type_2());
				ioData.generateCreateTable(converter.getNameTable(1), converter.getDescriptionTable(1), converter.getListStructure_type_1());
			}
			
			if(converter.importedData_type() > 0){
				reader = new FileReader("C:\\Temp\\data1.txt");  
				file = new BufferedReader(reader);	
				
				while((line = file.readLine())!= null){
					type = Integer.parseInt(line.substring(0, 1));	
					converter.getType(false, line, type);
				}
			}
		
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Erro ao ler arquivo!");
			System.exit(1);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Falha ao tentar gerar tabela!");
			System.exit(1);
		}
				
		System.exit(0);		
	}	
}
