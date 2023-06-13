package UML_object;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Font;

public abstract class basic_object extends object {
    private int port_width = 10;
    protected int width, height, min_margin;
    protected port[] ports = new port[4];
    protected Font font = new Font(Font.DIALOG, Font.BOLD, 14);

    public abstract void draw(Graphics g);

    @Override
    public void show_selected(Graphics g) {
        for (int i = 0; i < ports.length; i++) {
            ports[i].draw(g);
        }
    }

    @Override
    public String inside(Point p) {
        Point center = new Point();
        center.x = (point1.x + point2.x) / 2;
        center.y = (point1.y + point2.y) / 2;

        // Use the 4 corners and a center point to divide an oject into 4 parts, to decide which port the mouse cursor is currently closest to
        Point[] corners = { 
            new Point(point1.x, point1.y), 
            new Point(point2.x, point1.y), 
            new Point(point2.x, point2.y), 
            new Point(point1.x, point2.y)
        };

        for (int i = 0; i < corners.length; i++) {
            Polygon check_area = new Polygon();
            int first_point_idx = i;
            int seconed_point_idx = (i + 1) % 4;

            // A divide part is a triangle consisting 3 points : center, first point and second point
            check_area.addPoint(center.x, center.y);
            check_area.addPoint(corners[first_point_idx].x, corners[first_point_idx].y);
            check_area.addPoint(corners[seconed_point_idx].x, corners[seconed_point_idx].y);

            if (check_area.contains(p)) {
                // if the mouse cursor is between point 0 and point 1, then it is closets to port 0
                return Integer.toString(first_point_idx);
            }

        }

        return null;
    }

    @Override
    public void move(int move_x, int move_y) {
        // Calculate new position
        Point new_point1 = new Point(point1.x + move_x, point1.y + move_y);
        Point new_point2 = new Point(point2.x + move_x, point2.y + move_y);
        // Point new_point2 = new Point(point1.x + width, point1.y + height);

        // Set new position
        point1.setLocation(new_point1);
        point2.setLocation(new_point2);

        Point[] ports_position = {
            new Point(((point1.x + point2.x) / 2), point1.y),
            new Point((point2.x), ((point1.y + point2.y) / 2)),
            new Point(((point1.x + point2.x) / 2), point2.y),
            new Point((point1.x), ((point1.y + point2.y) / 2))
        };

        for (int i = 0; i < ports.length; i++) {
            ports[i].set(ports_position[i], port_width);
            ports[i].move_lines();
        }

    }

    @Override
    public void rename(String name) {
        this.name = name;
    }

    @Override
    public port get_port(int port_index) {
        return ports[port_index];
    }

    protected void create_ports() {
        Point[] ports_position = {
            new Point(((point1.x + point2.x) / 2), point1.y),
            new Point((point2.x), ((point1.y + point2.y) / 2)),
            new Point(((point1.x + point2.x) / 2), point2.y),
            new Point((point1.x), ((point1.y + point2.y) / 2))
        };
        for (int i = 0; i < ports.length; i++) {
            port port = new port();
            port.set(ports_position[i], port_width);
            ports[i] = port;
        }
    }

}
