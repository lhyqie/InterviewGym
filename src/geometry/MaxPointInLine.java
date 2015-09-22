package geometry;

import java.util.*;

public class MaxPointInLine {
	public static void main(String[] args) {
		int x[] = {84,  0, 1,  0,    0,  1, 21, 42, -42};
		int y[] = {250, 0, 0, -70, -70, -1, 10, 90, -230 };
		Point[] points = new Point[x.length];
		for (int i = 0; i < x.length; i++) {
			points[i] = new Point(x[i], y[i]);
		}
		int ret = maxPoints(points);
		System.out.println(ret);
	} 
	
	public static int maxPoints(Point[] points) {
		if(points == null || points.length == 0) return 0;
		int res = 0;
		for(int i = 0; i < points.length; i++){
			HashMap<Line,Integer> lineCnt = new HashMap<Line,Integer>();
			int nSame = 0;
			for(int j = i+1; j < points.length; j++ ){
            	if(points[i].x == points[j].x && points[i].y == points[j].y) {
            		nSame ++;
            	}
            	else{
            		Line line = new Line(points[i], points[j]);
                    //System.out.println(line);
                    lineCnt.put(line, lineCnt.get(line) == null ? 1 : lineCnt.get(line) + 1);
            	}
            }
			int maxValue = 0;
			for(int e : lineCnt.values()) maxValue = Math.max(maxValue, e);
			System.out.println(maxValue + nSame + 1);
			res = Math.max(res, maxValue + nSame + 1);
		}
		return res;
	}
	/*  works if no overlapped points
	public static int maxPoints(Point[] points) {
		if(points == null || points.length == 0) return 0;
		if(points.length == 1) return 1;
        HashMap<Line,Integer> lineCnt = new HashMap<Line,Integer>();
        for(int i = 0; i < points.length; i++){
            for(int j = 0; j < points.length; j++ ){
            	if(i == j) continue;
            	Line line = new Line(points[i], points[j]);
                System.out.println(line);
                lineCnt.put(line, lineCnt.get(line) == null ? 1 : lineCnt.get(line) + 1);
            }
        }
        int max = 0;
        for(int cnt : lineCnt.values()) max = Math.max(max, cnt);
        System.out.println("max = " + max);
        return  (int)((1 + Math.sqrt(1 + 4 * max))/ 2) ;
	 }*/
}


class Point {
	int x;
    int y;
    Point() { x = 0; y = 0; }
    Point(int a, int b) { x = a; y = b; }
}
 
class Line {
    public static double eps = .00001;
    public double slope;
    public double intersect;
    public boolean infinite_slope = false;
    
    public Line(Point p1, Point p2){
        if(Math.abs(p1.x-p2.x) < eps){
            infinite_slope = true;
            intersect = p1.x;     //x -intersect
        }else{
            slope = (p2.y-p1.y ) * 1.0 /(p2.x-p1.x);
            intersect = p1.y - slope * p1.x;     //y - kx = b
        }
    }
    
    @Override
    public boolean equals(Object o){
    	Line other = (Line)o;
        if(infinite_slope && other.infinite_slope){
            if(doubleEqual(intersect, other.intersect)) return true;
            else return false;
        }else if(!infinite_slope && !other.infinite_slope){
            return doubleEqual(slope, other.slope) && doubleEqual(intersect, other.intersect); 
        }else{
            return false;
        }
    }
    
    @Override
    public int hashCode(){
        return (int)(slope * 123 + intersect * 789);
    }
    
    @Override
    public String toString(){
    	return "slope =" + slope +" intersect ="+intersect;
    }
    public static boolean doubleEqual(double x, double y){
        return Math.abs(x - y) < eps; 
    }
    
}
