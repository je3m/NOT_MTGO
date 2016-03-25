package devops.hw1.core;

public class NumericData extends AbstractData<Long> {

	public NumericData(long id, Long value) {//!#should throw IllegalArgumentException if id and value not equiv
		super(id, value);
	}

	/**
	 * This method compares the current object's value to the value of another object implementing the same interface
	 * @param other the other object being compared to
	 * @return 1 if this object is comparatively greater than the other, 0 if they are comparatively equivalent, and -1 if the other is comparatively greater than this object
	 */
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
