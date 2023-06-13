package UML_object;

import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Point;

public abstract class object {
    protected Point point1 = new Point();
    protected Point point2 = new Point();
    protected String name = null;
    protected ArrayList<object> objects_in_composite = new ArrayList<object>();
    public int depth;

    public Point get_point1() {
        return point1;
    }

    public Point get_point2() {
        return point2;
    }

    public String get_name() {
        return name;
    }

    public abstract void draw(Graphics g);

    // For moving lines
    public void move() {};

    // For moving objects
    public void move(int move_x, int move_y) {};

    public String inside(Point p) {
        return null;
    }

    public void rename(String name) {};

    public void show_selected(Graphics g) {};

    // For basic object
    public port get_port(int port_index) {
        return null;
    }

    // For composite object
    public ArrayList<object> get_objects() {
        return objects_in_composite;
    }

    public object get_selected_object() {
        return null;
    }

    public void reset_selected_object() {}

    // For line object
    public void set_connected_ports(port start_port, port end_port) {}
}
