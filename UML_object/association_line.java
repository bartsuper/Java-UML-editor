package UML_object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class association_line extends line {
    
    public association_line(Point point1, Point point2, int arrow_width, int arrow_height) {
        this.point1.setLocation(point1);
        this.point2.setLocation(point2);
        this.arrow_width = arrow_width;
        this.arrow_height = arrow_height;
    }

    @ Override
    public void draw(Graphics g) {
        // Draw the line
        g.setColor(Color.BLACK);
        g.drawLine(point1.x, point1.y, point2.x, point2.y);

        // Draw the arrow
        int dx = point2.x - point1.x;
        int dy = point2.y - point1.y;
        double point1_to_point2 = Math.sqrt((dx * dx) + (dy * dy));
        double cos = dx / point1_to_point2;
        double sin = dy / point1_to_point2;

        // We have a point A and a point B in the arrow
        // We first get point A
        int pointA_x = (int) ((point1_to_point2 - arrow_width) * cos - arrow_height * sin + point1.x);
        int pointA_y = (int) ((point1_to_point2 - arrow_width) * sin + arrow_height * cos + point1.y);

        // We then get point B
        int pointB_x = (int) ((point1_to_point2 - arrow_width) * cos + arrow_height * sin + point1.x);
        int pointB_y = (int) ((point1_to_point2 - arrow_width) * sin - arrow_height * cos + point1.y);

        // Draw the arrow lines
        g.drawLine(pointA_x, pointA_y, point2.x, point2.y);
        g.drawLine(pointB_x, pointB_y, point2.x, point2.y);
    }
}
