package UML_object;

import java.awt.Point;
import java.awt.Polygon;
import java.awt.Color;
import java.awt.Graphics;

public class composition_line extends line {
    
    public composition_line(Point point1, Point point2, int arrow_width, int arrow_height) {
        this.point1.setLocation(point1);
        this.point2.setLocation(point2);
        this.arrow_width = arrow_width;
        this.arrow_height = arrow_height;
    }

    @Override
    public void draw(Graphics g) {
        // Draw the line
        g.setColor(Color.BLACK);
        g.drawLine(point1.x, point1.y, point2.x, point2.y);

        // Draw the diamond shape.
        int dx = point2.x - point1.x;
        int dy = point2.y - point1.y;
        double point1_to_point2 = Math.sqrt((dx * dx) + (dy * dy));
        double cos = dx / point1_to_point2;
        double sin = dy / point1_to_point2;

        // We have 3 points A, B, and C. C is the point on the line.
        // We first get point A
        int pointA_x = (int) ((point1_to_point2 - arrow_width) * cos - arrow_height * sin + point1.x);
        int pointA_y = (int) ((point1_to_point2 - arrow_width) * sin + arrow_height * cos + point1.y);

        // We then get point B
        int pointB_x = (int) ((point1_to_point2 - arrow_width) * cos + arrow_height * sin + point1.x);
        int pointB_y = (int) ((point1_to_point2 - arrow_width) * sin - arrow_height * cos + point1.y);
        // Last but not least, we get point C
        int pointC_x = (int) ((point1_to_point2 - arrow_width * 2) * cos + point1.x);
        int pointC_y = (int) ((point1_to_point2 - arrow_width * 2) * sin + point1.y);

        // Draw the actual diamond polygon
        Polygon diamond = new Polygon();
        diamond.addPoint(pointA_x, pointA_y);
        diamond.addPoint(pointC_x, pointC_y);
        diamond.addPoint(pointB_x, pointB_y);
        diamond.addPoint(point2.x, point2.y);
        g.fillPolygon(diamond);
    }
}
