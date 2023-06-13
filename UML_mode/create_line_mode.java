package UML_mode;

import java.util.ArrayList;
import java.awt.Point;
import java.awt.event.MouseEvent;
import UML_object.object;
import UML_object.port;

public class create_line_mode extends mode {
    private String line_type;
    private object_factory_baseclass factory = new line_factory();
    private Point start_point, end_point;
    private port start_port, end_port;
    private ArrayList<object> all_objects = new ArrayList<object>();
    
    public create_line_mode(String line_type) {
        this.line_type= line_type;
    }

    @Override
    public void mouse_pressed(MouseEvent e) {
        start_port = get_connection_port(e.getPoint());

        if (start_port != null) {
            // Set the start point
            start_point = new Point(start_port.get_center_point());
        }
    }

    @Override
    public void mouse_dragged(MouseEvent e) {
        if (start_point != null) {
            object line = factory.create_object(line_type, start_point, e.getPoint());
            my_canvas.drag_line = line;
            my_canvas.repaint();
        }
    }

    @Override
    public void mouse_released(MouseEvent e) {
        if (start_point != null) {
            end_port = get_connection_port(e.getPoint());

            if (end_port != null) {
                // Set the end point
                end_point = end_port.get_center_point();
                object line = factory.create_object(line_type, start_point, end_point);
                // Set the connected ports of the created line
                line.set_connected_ports(start_port, end_port);
                // Set the connected line of the corresponding ports
                start_port.add_line(line);
                end_port.add_line(line);
                // Add line to canvas
                my_canvas.create_object(line);
            }
            // Reset everything
            start_point = null;
            end_port = null;
            // Cancel the drag line
            my_canvas.drag_line = null;
            my_canvas.repaint();
        }
    }

    public port get_connection_port(Point p) {
        int port_index;
        port connected_port = null;
        all_objects = my_canvas.get_all_objects();

        for (int i = 0; i < all_objects.size(); i++) {
            object object = all_objects.get(i);
            String is_inside = object.inside(p);
            
            if (is_inside != null) {

                if (is_inside.equals("in_composite")) {
                    object = object.get_selected_object();
                    port_index = Integer.parseInt(object.inside(p));
                    connected_port = object.get_port(port_index);
                }
                else {
                    port_index = Integer.parseInt(is_inside);
                    connected_port = object.get_port(port_index);
                }
            }
        }
        return connected_port;
    }
}
