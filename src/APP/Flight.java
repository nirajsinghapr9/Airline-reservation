package APP;

public class Flight {
	private int sino, price, seating;
	private String name, start,end, departure,arrival;
	
	public Flight(int sino, int price,int seating, String name, String start,String end, String departure,String arrival )
	{
		
		this.name=name;
		this.start=start;
		this.end=end;
		this.seating=seating;
		this.departure=departure;
		this.arrival=arrival;
		
		}
	public int getsino() {
		return sino;
		
	}
	public int getprice() {
		return price;
	}
	public String name() {
		return name;
	}
	public String start() {
		return start;
	}
	public String end() {
		return end;
	}
	public int seating() {
		return seating;
	}
	public String departure() {
		return departure;
	}
	public String arrival() {
		return arrival;
	}
	public static void main(String[] args) {
		
		

	}

}
