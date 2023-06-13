package UML_mode;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import UML_object.object;

public class select_mode extends mode {
    private ArrayList<object> all_objects = new ArrayList<object>();
    private Point clicked_point;
    private boolean start_on_object = false;

    @Override
    public void mouse_pressed(MouseEvent e) {
        all_objects = my_canvas.get_all_objects();
        // First reset all selections
        my_canvas.reset_selection();
        start_on_object = false;

        clicked_point = e.getPoint();

        for (int i = all_objects.size() - 1; i >= 0; i--) {
            object object = all_objects.get(i);
            String is_inside = object.inside(clicked_point);

            if (is_inside != null) {
                my_canvas.selected_objects.add(object);
                start_on_object = true;
                break;
            }
        }

        my_canvas.repaint();
    }

    @Override
    public void mouse_dragged(MouseEvent e) {
        // Point current_mouse = new Point(e.getPoint());
        int move_x = e.getX() - clicked_point.x;
        int move_y = e.getY() - clicked_point.y;

        // System.out.println(String.valueOf(start_on_object));

        if (!my_canvas.selected_objects.isEmpty() && start_on_object == true) {

            // Move selected objects
            for (int i = 0; i < my_canvas.selected_objects.size(); i++) {
                object object = my_canvas.selected_objects.get(i);
                object.move(move_x, move_y);
            }

            // Set new clicked point
            clicked_point.setLocation(e.getPoint());
        }
        else {
            // Group select
            if (e.getX() > clicked_point.x) {
                if (e.getY() > clicked_point.y) {
                    my_canvas.selected_area.setBounds(clicked_point.x, clicked_point.y, Math.abs(move_x), Math.abs(move_y));
                }
                else {
                    my_canvas.selected_area.setBounds(clicked_point.x, e.getY(), Math.abs(move_x), Math.abs(move_y));
                }
            }
            else {
                if (e.getY() < clicked_point.y) {
                    my_canvas.selected_area.setBounds(e.getX(), e.getY(), Math.abs(move_x), Math.abs(move_y));
                }
                else {
                    my_canvas.selected_area.setBounds(e.getX(), clicked_point.y, Math.abs(move_x), Math.abs(move_y));
                }
            }

            for (int i = 0; i < all_objects.size(); i++) {
                object object = all_objects.get(i);

                if (my_canvas.check_selected_area(object) == true && !my_canvas.selected_objects.contains(object)) {
                    my_canvas.selected_objects.add(object);
                }
                else if (my_canvas.check_selected_area(object) == false && my_canvas.selected_objects.contains(object)) {
                    my_canvas.selected_objects.remove(object);
                }
            } 
        }

        my_canvas.repaint();
    }

    @Override
    public void mouse_released(MouseEvent e) {
        // Set group select area
        System.out.println(Integer.toString(my_canvas.selected_objects.size()));
        my_canvas.selected_area.setBounds(0, 0, 0, 0);
        my_canvas.repaint();
    }
}
