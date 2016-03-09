package devops.hw1.core;

public interface IData<T> extends Comparable<IData<T>> {
	public long getId();
	public T getValue();
}
