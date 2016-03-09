package devops.hw1.core;

public class NumericData extends AbstractData<Long> {

	public NumericData(long id, Long value) {
		super(id, value);
	}

	@Override
	public int compareTo(IData<Long> other) {
		if(this.equals(other))
			return 0;
		
		Long myValue = this.getValue();
		Long otherValue = other.getValue();
		
		if(myValue > otherValue)
			return 1;
		
		return -1;
	}
}
