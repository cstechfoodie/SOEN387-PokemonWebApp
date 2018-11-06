package data.rdg;

public abstract class RDG {
	public abstract int insert();
	public abstract int update();
	public abstract int delete();
	public abstract RDG find(long id);
}
