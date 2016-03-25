package devops.hw1.core;

public abstract class AbstractData<T> implements IData<T> {
	private long id;
	private T value;
	
	/**
	 * Constructor for an AbstractData<T> object
	 * @param id the ID number of the object
	 * @param value the object that stores this object's value
	 */
	public AbstractData(long id, T value) {
		this.id = id;
		this.value = value;
	}

	
	public long getId() {
		return this.id;
	}

	public T getValue() {
		return this.value;
	}

	/**
	 * This method produces a hash code for the object from the value that it stores
	 * @return the object's hash code
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	/**
	 * This method determines whether this object and another are equivalent
	 * @param obj the other object that this is compared to
	 * @return whether the two objects are equivalent
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;

		@SuppressWarnings("unchecked")//!# needs to handle the cast failing
		AbstractData<T> other = (AbstractData<T>) obj;
		if (id != other.id)
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}	
	
	public abstract int compareTo(IData<T> o);
}
