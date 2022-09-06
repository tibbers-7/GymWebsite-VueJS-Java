package beans;

public class Content{
	private int id;
	private String name;
	public Content(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public Content(String content) {
		super();
		String[] s= content.split("-");
		
		this.id = Integer.parseInt(s[0]);
		this.name = s[1];
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return id + "-" + name;
	}
	
	
}