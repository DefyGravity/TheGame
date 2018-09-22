package martian2;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * Auto-generated code below aims at helping you parse the standard input
 * according to the problem statement.
 **/
class Player {

	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		int surfaceN = in.nextInt(); // the number of points used to draw the surface of Mars.
		Path2D ground = new Path2D.Double();
		Rectangle world = new Rectangle(7000, 3000);
		List<Line2D> landingSpots = new LinkedList<Line2D>();
		for (int i = 0; i < surfaceN; i++) {
			Double landX = Double.valueOf(in.nextInt()); // X coordinate of a surface point. (0 to 6999)
			Double landY = Double.valueOf(in.nextInt()); // Y coordinate of a surface point. By linking all the points
															// together in a sequential fashion, you form the surface of
															// Mars.
			final Point2D lastPoint = ground.getCurrentPoint();
			final Point2D.Double currentPoint = new Point2D.Double(landX, landY);
			if (lastPoint != null && lastPoint.distance(currentPoint) >= 1000
					&& lastPoint.getY() == currentPoint.getY()) {
				landingSpots.add(new Line2D.Double(lastPoint, currentPoint));
			}
			if (i == 0) {
				ground.moveTo(landX, landY);
			} else {
				ground.lineTo(landX, landY);
			}

		}
		Line2D destination = null;
		Point2D landingZone = new Point2D.Double();
		Line2D.Double route = new Line2D.Double();
		Point2D previousLocation = null;
		Double previousError = null;
		int turn = 1;
		// game loop
		while (true) {
			int X = in.nextInt();
			int Y = in.nextInt();

			Point2D location = new Point2D.Double(Double.valueOf(X), Double.valueOf(Y));
			int hSpeed = in.nextInt(); // the horizontal speed (in m/s), can be negative.
			int vSpeed = in.nextInt(); // the vertical speed (in m/s), can be negative.
			int fuel = in.nextInt(); // the quantity of remaining fuel in liters.
			int rotate = in.nextInt(); // the rotation angle in degrees (-90 to 90).
			int power = in.nextInt(); // the thrust power (0 to 4).
			Double dPower = new Double(power);
			if (destination == null) {

				final Optional<Line2D> min = landingSpots.parallelStream().min((left, right) -> {
					final int lcompare = Double.compare(left.getP1().distance(location),
							left.getP2().distance(location));
					Point2D leftPt = null;
					if (lcompare < 0) {
						leftPt = left.getP1();
					} else {
						leftPt = left.getP2();
					}
					final int rcompare = Double.compare(right.getP1().distance(location),
							right.getP2().distance(location));
					Point2D rightPt = null;
					if (rcompare < 0) {
						rightPt = right.getP1();
					} else {
						rightPt = right.getP2();
					}
					final int compare = Double.compare(leftPt.distance(location), rightPt.distance(location));

					return compare;
				});
				destination = min.get();

				landingZone = new Point2D.Double(destination.getX1() + (destination.getX2() - destination.getX1()) / 2,
						destination.getY1());
				route.setLine(location, landingZone);
			}

			double error = route.ptLineDist(location);
			
			if (error > 0) {
				double length = error / Math.sqrt(2);

				double radians = Math.sin((location.getY() + length) / error);
				Double degrees = new Double(Math.toDegrees(radians));
				rotate = degrees.intValue();
				power = 3;
				if (error - 3.18 > 0) {
					power = 4;
				}
			}
            if(landingZone.distance(location)< Math.abs(rotate / 15)*(Math.sqrt(Math.pow(hSpeed, 2)+Math.pow(vSpeed, 2)))){
            	rotate = 0;
            }
			System.out.println(rotate + " " + power);
			previousLocation = location;
			previousError = error;
			turn +=1;
		}
	}
}
