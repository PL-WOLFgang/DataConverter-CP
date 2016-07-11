package Converter;


public class StructureFile {
	private String nameTable;
	private String descriptionTable;
	
	private String nameField;
	private String valueField;
	private String descriptionField;
	private String legthField;
	private int lengthField2;
	private String typeField;
	private int startWord;
	private int endWord;
		
	public String getNameField() {
		return nameField;
	}
	public void setNameField(String nameField) {
		this.nameField = StructureConverter.getColumnName(nameField, false);
	}
	public void setNameField2(String nameField) {
		this.nameField = nameField;
	}
	public String getDescriptionField() {
		return descriptionField;
	}
	public void setDescriptionField(String descriptionField) {
		this.descriptionField = StructureConverter.getCommentField((descriptionField));
	}
	public String getLegthField() {
		return legthField;
	}
	public void setLegthField(String legthField) {
		this.legthField = StructureConverter.getLength(legthField, typeField);
	}
	public void setLegthField(String legthField, String typeField) {
		this.legthField = StructureConverter.getLength(legthField, typeField);
	}
	public String getTypeField() {
		return typeField;
	}
	public void setTypeField(String typeField) {
		this.typeField = StructureConverter.getTypeColumn(typeField);
	}
	public int getStartWord() {
		return startWord;
	}
	public void setStartWord(int startWord) {
		this.startWord = startWord;
	}
	public int getEndWord() {
		return endWord;
	}
	public void setEndWord(int endWord) {
		this.endWord = endWord;
	}
	public String getValueField() {
		return valueField;
	}
	public void setValueField(String valueField) {
		this.valueField = valueField;
	}
	public String getDescriptionTable() {
		return descriptionTable;
	}
	public void setDescriptionTable(String descriptionTable) {
		this.descriptionTable = StructureConverter.getCommentField(descriptionTable);
	}
	public String getNameTable() {
		return nameTable;
	}
	public void setNameTable(String nameTable) {
		this.nameTable = StructureConverter.getColumnName(nameTable, true);
	}
	public int getLengthField2() {
		return lengthField2;
	}
	public void setLengthField2(int lengthField2) {
		this.lengthField2 = lengthField2;
	}	
	
}
