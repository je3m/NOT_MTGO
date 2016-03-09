package devops.hw1.core;

public abstract class AbstractData<T> implements IData<T> {
	private long id;
	private T value;
	
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;

		@SuppressWarnings("unchecked")
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
