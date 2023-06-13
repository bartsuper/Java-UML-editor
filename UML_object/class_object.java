package UML_object;

import java.awt.Point;
import java.awt.Color;
import java.awt.Graphics;

public class class_object extends basic_object {

    public class_object(String name, Point p, int width, int height, int min_margin, int depth) {
        this.name = name;
        point1.setLocation(p);
        point2.setLocation((p.x + width), (p.y + height));
        this.width = width;
        this.height = height;
        this.min_margin = min_margin;
        this.depth = depth;
        create_ports();
    }

    @Override
    public void draw(Graphics g) {
        // Draw outline of class object but the width should be greater than that of its name
        int name_width = g.getFontMetrics(font).stringWidth(name);
        if (name_width >= (width - (min_margin * 2))) {
            width = name_width + (min_margin * 2);
            point2.x = point1.x + width;
        }
        g.setColor(Color.BLACK);
        g.drawRect(point1.x, point1.y, width, height);
        g.setColor(Color.WHITE);
        g.fillRect(point1.x, point1.y, width, height);

        // Draw the middle lines
        int line1_offset = (int)(height * 0.2);
        int line2_offset = (int)(height * 0.6);
        g.setColor(Color.BLACK);
        g.drawLine(point1.x, (point1.y + line1_offset), point2.x, (point1.y + line1_offset));
        g.drawLine(point1.x, (point1.y + line2_offset), point2.x, (point1.y + line2_offset));
        
        // Display the object name, and display in the middle
        int empty_space_x = (int)(((point2.x - point1.x) - name_width) / 2);
        int empty_space_y = (int)(line1_offset / 1.5);
        g.setFont(font);
        g.drawString(name, (point1.x + empty_space_x), (point1.y + empty_space_y));
    }
}
