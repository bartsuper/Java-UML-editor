package UML_object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class usecase_object extends basic_object {
    
    public usecase_object(String name, Point p, int width, int height, int min_margin, int depth) {
        this.name = name;
        point1.setLocation(p);
        point2.setLocation(p.x + width, p.y + height);
        this.width = width;
        this.height = height;
        this.min_margin = min_margin;
        this.depth = depth;
        create_ports();
    }

    @Override
    public void draw(Graphics g) {
        // Draw the oval shape of a usecase object, but the width should be greater than that of its name
        int name_width = g.getFontMetrics(font).stringWidth(name);
        if (name_width >= (width - (min_margin * 2))) {
            width = name_width + (min_margin * 2);
            point2.x = point1.x + width;
        }
        g.setColor(Color.BLACK);
        g.drawOval(point1.x, point1.y, width, height);
        g.setColor(Color.WHITE);
        g.fillOval(point1.x, point1.y, width, height);

        // Display the object name, and display it in the middle
        int empty_space_x = (int)(((point2.x - point1.x) - name_width) / 2);
        int empty_space_y = (int)((point2.y - point1.y) / 2);
        g.setColor(Color.BLACK);
        g.setFont(font);
        g.drawString(name, (point1.x + empty_space_x), (point1.y + empty_space_y));
    }
}
