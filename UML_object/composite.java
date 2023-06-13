package UML_object;

import java.util.ArrayList;
import java.util.Comparator;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Color;

public class composite extends object {
    private Rectangle composite_area = new Rectangle();
    private object selected_object;

    public void sort_depth() {
        objects_in_composite.sort(new Comparator<object>() {
            public int compare(object o1, object o2) {
                if (o1.depth == o2.depth) {
                    return 0;
                }
                return o1.depth < o2.depth ? -1 : 1;
            }
        });
    }

    @Override
    public void draw(Graphics g) {
        sort_depth();

        for (int i = 0; i < objects_in_composite.size(); i++) {
            object object = objects_in_composite.get(i);
            object.draw(g);
            if (object == selected_object) {
                object.show_selected(g);
            }
        }
    }

    @Override
    public void move(int move_x, int move_y) {
        // Move all objects inside the composite
        for (int i = 0; i < objects_in_composite.size(); i++) {
            object object = objects_in_composite.get(i);
            object.move(move_x, move_y);
        }
        
        // Also move the composite area
        move_composite_area(move_x, move_y);
    }

    @Override
    public void show_selected(Graphics g) {
        int alpha = 64; // ~25% transparency

        // Draw outline of the composite area
        g.setColor(new Color(134, 134, 227));
        g.drawRect(point1.x, point1.y, (point2.x - point1.x), (point2.y - point1.y));
        // Fill the inner part of the composite area
        g.setColor(new Color(224, 224, 255, alpha));
        g.fillRect(point1.x, point1.y, (point2.x - point1.x), (point2.y - point1.y));
        // Reset color
        g.setColor(Color.WHITE);
        // if (selected_object != null) {
        //     selected_object.show_selected(g);
        // }
    }
    
    @Override
    public String inside(Point p) {
        for (int i = objects_in_composite.size() - 1; i >= 0; i--) {
            object object = objects_in_composite.get(i);
            String is_inside = object.inside(p);
            if (is_inside != null) {
                selected_object = object;
                return "in_composite";
            }
        }
        return null;
    }

    @Override
    public void rename(String name) {
        selected_object.rename(name);
    }

    public void add_object(object object) {
        objects_in_composite.add(object);
    }

    public ArrayList<object> get_objects() {
        return objects_in_composite;
    }

    public object get_selected_object() {
        return selected_object;
    }

    public void reset_selected_object() {
        selected_object = null;
    }

    public void set_composite_area() {
        // We need to find the upper-left-most (x, y) and the lower-right-most (x, y)
        Point upper_left_point = new Point(Integer.MAX_VALUE, Integer.MAX_VALUE);
        Point lower_right_point = new Point(Integer.MIN_VALUE, Integer.MIN_VALUE);

        System.out.println("objects in composite : " + Integer.toString(objects_in_composite.size()));

        for (int i = 0; i < objects_in_composite.size(); i++) {
            object object = objects_in_composite.get(i);
            
            if (object.get_point1().x < upper_left_point.x) {
                upper_left_point.x = object.get_point1().x;
            }
            if (object.get_point1().y < upper_left_point.y) {
                upper_left_point.y = object.get_point1().y;
            }
            if (object.get_point2().x > lower_right_point.x) {
                lower_right_point.x = object.get_point2().x;
            }
            if (object.get_point2().y > lower_right_point.y) {
                lower_right_point.y = object.get_point2().y;
            }
        }

        composite_area.setBounds(upper_left_point.x, upper_left_point.y, (lower_right_point.x - upper_left_point.x), (lower_right_point.y - upper_left_point.y));

        // Set composite object position
        point1.setLocation(upper_left_point);
        point2.setLocation(lower_right_point);
    }

    public void move_composite_area(int move_x, int move_y) {
        Point new_point1 = new Point(point1.x + move_x, point1.y + move_y);
        Point new_point2 = new Point(point2.x + move_x, point2.y + move_y);

        point1.setLocation(new_point1);
        point2.setLocation(new_point2);
    }
}
