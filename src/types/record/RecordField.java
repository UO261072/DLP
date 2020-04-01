package types.record;

import types.Type;

public class RecordField {

	private String name;
	private Type type;
	private int offset;
	
	public RecordField(String name, Type type, int offset) {
		super();
		this.name = name;
		this.type = type;
		this.offset = offset;
	}
	
	
}
