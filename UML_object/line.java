package UML_object;

import java.awt.Graphics;

public abstract class line extends object {
    protected port[] connected_ports = new port[2];
    protected int arrow_width, arrow_height;

    public abstract void draw(Graphics g);

    @Override
    public void set_connected_ports(port start_port, port end_port) {
        connected_ports[0] = start_port;
        connected_ports[1] = end_port;
    }

    @Override
    public void move() {
        point1.setLocation(connected_ports[0].get_center_point());
        point2.setLocation(connected_ports[1].get_center_point());
    }
}
