import java.util.*;
import java.awt.Rectangle;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Player {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int surfaceN = in.nextInt(); // the number of points used to draw the surface of Mars.
        Path2D ground = new Path2D.Double();
        Rectangle world = new Rectangle(7000,3000);
        List<Line2D> landingSpots = new LinkedList<Line2D>();
        for (int i = 0; i < surfaceN; i++) {
            Double landX = Double.valueOf(in.nextInt()); // X coordinate of a surface point. (0 to 6999)
            Double landY = world.getHeight()- in.nextInt(); // Y coordinate of a surface point. By linking all the points together in a sequential fashion, you form the surface of Mars.
            final Point2D lastPoint = ground.getCurrentPoint();
			final Point2D.Double currentPoint = new Point2D.Double(landX, landY);
			if(lastPoint!= null && lastPoint.distance(currentPoint) >=1000 && lastPoint.getY() == currentPoint.getY()){
            	landingSpots.add(new Line2D.Double(lastPoint, currentPoint));	
            }
			if(i == 0){
				ground.moveTo(Double.valueOf(landX), landY);
			}else{
				ground.lineTo(Double.valueOf(landX),landY);	
			}
            
        }
        Line2D destination = null;
        // game loop
        while (true) {
            int X = in.nextInt();
            int Y = in.nextInt();
            Point2D location = new Point2D.Double(Double.valueOf(X), Double.valueOf(Y));
            if(destination == null){
            	final Optional<Line2D> min = landingSpots.parallelStream().min((left, right) -> {
            		final int lcompare = Double.compare(left.getP1().distance(location), left.getP2().distance(location));
            		Point2D leftPt =null;
            		if(lcompare < 0){
            			leftPt = left.getP1();
            		}else{
            			leftPt = left.getP2();
            		}
            		final int rcompare = Double.compare(right.getP1().distance(location), right.getP2().distance(location));
            		Point2D rightPt = null;
            		if(rcompare < 0){
            			rightPt = right.getP1();
            		}else{
            			rightPt = right.getP2();
            		}
            		final int compare = Double.compare(leftPt.distance(location), rightPt.distance(location));
            		
					return compare;
            	});
            	destination = min.get();
            	System.err.println(destination.getP1()+"**"+destination.getP2());
            }
            int hSpeed = in.nextInt(); // the horizontal speed (in m/s), can be negative.
            int vSpeed = in.nextInt(); // the vertical speed (in m/s), can be negative.
            int fuel = in.nextInt(); // the quantity of remaining fuel in liters.
            int rotate = in.nextInt(); // the rotation angle in degrees (-90 to 90).
            int power = in.nextInt(); // the thrust power (0 to 4).
            double radians = Math.toRadians(Double.valueOf(rotate));
            Point2D midPoint = new Point2D.Double(destination.getX1()+ (destination.getX2()-destination.getX1()/2), destination.getX1());
            
            final double distance = location.distance(midPoint);
            final double theta = Math.acos((location.getY()-midPoint.getY())/distance);
            final Double degrees = -(Math.toDegrees(theta)-90);
            if(vSpeed <= -35){
                power = 4;
            }else if(vSpeed <= -20){
                power = 3;
            }else {
                power = 0;
            }

            // Write an action using System.out.println()
            // To debug: System.err.println("Debug messages...");


            // rotate power. rotate is the desired rotation angle. power is the desired thrust power.
            System.out.println(degrees.intValue() +" "+power);
        }
    }
}
