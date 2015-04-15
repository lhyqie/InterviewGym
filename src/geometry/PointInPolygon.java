/**
 * Neighbourhood
Uber needs to connect a rider with a car as fast as possible. To achieve that, the system needs to accurately predict demand to make sure you get a car when you want one. Predicting demands requires knowledge about the locations from which most orders are likely to occur.

Given a neighbourhood represented as the coordinates of a convex polygon and a list of trip destinations
Your task is to
write a function that prints to the standard output (stdout) the number of trips that ended in the given neighbourhood
Note that your function will receive the following arguments:
neighbourhood
which is an array of strings representing the coordinates of the polygon
the convex polygon coordinates are presented in clockwise order
each string contains the x,y coordinates of one vertex, separated by comma
trips
which is an array of strings representing the coordinates of the trips destinations
each string contains the x,y coordinates of one destination, separated by comma
Data constraints
the length of the arrays above will not exceed 100,000 entries
all the coordinates values, for both the polygon and trip destinations, will be integer numbers in the [0, 10000] range
Efficiency constraints
your function is expected to print the requested result and return in less than 2 seconds
Example
Input	Output
neighbourhood: ["1,3","4,5","3,1"]
trips: ["1,1","2,3","3,3","4,2"]	2
Explanation:
The only trips that ended in the neighbourhood are (2,3) and (3,3).
 */

package geometry;

public class PointInPolygon {

	static class Point {
		public Point(float x, float y) {
			super();
			this.x = x;
			this.y = y;
		}

		float x;
		float y;
	}

	static class Polygon {
		public Polygon(Point[] points) {
			super();
			this.points = points;
		}

		private Point[] points; // Points making up the boundary

		/**
		 * Return true if the given point is contained inside the boundary. See:
		 * http
		 * ://www.ecse.rpi.edu/Homepages/wrf/Research/Short_Notes/pnpoly.html
		 * 
		 * @param test
		 *            The point to check
		 * @return true if the point is inside the boundary, false otherwise
		 *
		 */
		public boolean contains(Point test) {
			int i;
			int j;
			boolean result = false;
			for (i = 0, j = points.length - 1; i < points.length; j = i++) {
				if ((points[i].y > test.y) != (points[j].y > test.y)
						&& (test.x < (points[j].x - points[i].x)
								* (test.y - points[i].y)
								/ (points[j].y - points[i].y) + points[i].x)) {
					result = !result;
				}
			}
			return result;
		}
	}

	public static void count_trips(String[] neighbourhood, String[] trips) {
		Point[] points = new Point[neighbourhood.length];
		for (int i = 0; i < neighbourhood.length; i++) {
			String[] tokens = neighbourhood[i].split(",");
			points[i] = new Point(Float.parseFloat(tokens[0]),Float.parseFloat(tokens[1]));
		}
		
		int count = 0;
		Polygon polygon = new Polygon(points);
		for (String trip : trips) {
			String[] tokens = trip.split(",");
			Point p = new Point(Float.parseFloat(tokens[0]),Float.parseFloat(tokens[1]));
			if (polygon.contains(p)) count ++;
		}
		System.out.print(count);
	}

	public static void main(String[] args) {

		String[] neighbourhood = { "1,3", "4,5", "3,1" };
		String[] trips = { "1,1", "2,3", "3,3", "4,2" };
		count_trips(neighbourhood, trips);
	}
}
