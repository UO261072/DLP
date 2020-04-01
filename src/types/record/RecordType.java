package types.record;

import java.util.List;

import types.AbstractType;
import types.Type;

public class RecordType extends AbstractType implements Type {

	private List<RecordField> components;

	public RecordType(List<RecordField> components) {
		super();
		this.components = components;
	}
	
	
}
