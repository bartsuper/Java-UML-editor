package UML_object;

import java.util.ArrayList;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class port {
    private ArrayList<object> lines = new ArrayList<object>();
    private int width, height;
    private Point port_position = new Point();

    public Point get_center_point() {
        Point center_point = new Point(port_position);
        center_point.x = center_point.x + (int) (width / 2);
        center_point.y = center_point.y + (int) (height / 2);
        return center_point;
    }

    public void set(Point port_center, int width) {
        int offset = width / 2;
        int x = port_center.x - offset;
        int y = port_center.y - offset;
        
        // Set port properties
        port_position.setLocation(x, y);
        this.width = width;
        this.height = width;
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(port_position.x, port_position.y, width, height);
    }

    public void add_line(object line) {
        lines.add(line);
    }

    public void move_lines() {
        for (int i = 0; i < lines.size(); i++) {
            object line = lines.get(i);
            line.move();
        }
    }
}
