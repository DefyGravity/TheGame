import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.image.AffineTransformOp;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse the standard input
 * according to the problem statement.
 **/
class Solution {

	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		int speed = in.nextInt();
		System.err.print(speed);
		int lightCount = in.nextInt();
		System.err.println("," + lightCount);
		Path2D path = new Path2D.Double();
		// initialize
		path.moveTo(0, 0);
		for (int i = 0; i < lightCount; i++) {
			int distance = in.nextInt();
			int duration = in.nextInt();
			path.lineTo(distance, duration);
			System.err.println(distance + ", " + new Double(duration) / 60 / 60);
		}
path.closePath();
		final AffineTransform y3x = new AffineTransform();
		y3x.scale(1, 3);
		AffineTransform transform = new AffineTransform();
		// convert to km and hours respectively
		transform.scale(0.001, 1.0 / 3600.0);
		path.transform(transform);
		int previousSpeed = 0;
		int mySpeed = Integer.MAX_VALUE;

		while (Integer.compare(mySpeed, speed) > 0) {

			PathIterator pathIterator = path.getPathIterator(null);
			pathIterator.next();
			Point2D lowestPoint = new Point2D.Double(Double.MAX_VALUE, Double.MAX_VALUE);
			while (!pathIterator.isDone()) {
				double[] coords = new double[6];
				int y = pathIterator.currentSegment(coords);
				if(Integer.compare(PathIterator.SEG_CLOSE,y)==0||Integer.compare(PathIterator.SEG_MOVETO, y)==0) {
					pathIterator.next();
					continue;
				}
				System.err.println(coords[0] + "," + coords[1]);
				if (Double.compare(coords[1], lowestPoint.getY()) <= 0) {
					lowestPoint.setLocation(coords[0], coords[1]);
				}
				pathIterator.next();
			}
			System.err.println(lowestPoint);
			Double test = lowestPoint.getX() / lowestPoint.getY();
			System.err.println("**\t" + test);
			mySpeed = test.intValue();

			path.transform(y3x);
			previousSpeed = mySpeed;
		}

		// Write an action using System.out.println()
		// To debug: System.err.println("Debug messages...");
		System.out.println((previousSpeed == 0) ? mySpeed : previousSpeed);
	}
}