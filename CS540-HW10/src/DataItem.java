public class DataItem {
	private double x1,x2,y;
	
	DataItem(double x1, double x2, double y) {
		this.x1 = x1;
		this.x2 = x2;
		this.y = y;
	}
	
	public double get_x1() {
		return this.x1;
	}
	
	public double get_x2() {
		return this.x2;
	}
	
	public double get_y() {
		return this.y;
	}
}