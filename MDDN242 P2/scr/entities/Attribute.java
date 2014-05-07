package entities;

public abstract class Attribute<T> {
	public abstract String getKey();
	public abstract T getValue();
	public abstract void valueChange(String newValue);
}
