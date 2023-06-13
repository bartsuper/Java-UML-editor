package UML_editor;

import java.util.ArrayList;
import UML_object.object;
import UML_object.composite;
import UML_mode.mode;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class canvas extends JPanel {
    public ArrayList<object> all_objects = new ArrayList<object>();
    public ArrayList<object> selected_objects = new ArrayList<object>();
    public mode current_mode;
    public object drag_line;
    public Rectangle selected_area = new Rectangle();
    private static canvas my_canvas;
    private int current_depth = 0;

    private canvas() {
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        addMouseListener(new mouse_listener());
        addMouseMotionListener(new mouse_motion_listener());
    };

    public static canvas get_canvas() {
        if (my_canvas == null) {
            my_canvas = new canvas();
        }
        return my_canvas;
    }

    public ArrayList<object> get_all_objects() {
        return all_objects;
    }

    public ArrayList<object> get_selected_objects() {
        return selected_objects;
    }

    public int get_current_depth() {
        return current_depth;
    }

    public void add_depth() {
        current_depth++;
    }

    public void create_object(object object) {
        all_objects.add(object);
    }

    public void create_composite() {
        composite composite = new composite();
        System.out.println("selected object size = " + Integer.toString(selected_objects.size()));
        for (int i = 0; i < selected_objects.size(); i++) {
            object object = selected_objects.get(i);
            System.out.println(Integer.toString(i));
            composite.add_object(object);
            all_objects.remove(object);
        }
        selected_objects.clear();
        composite.set_composite_area();
        all_objects.add(composite);
    }

    public void cancel_composite() {
        for (int i = 0; i < selected_objects.size(); i++) {
            object composite = selected_objects.get(i);
            ArrayList<object> objects_in_composite = composite.get_objects();
            for (int j = 0; j < objects_in_composite.size(); j++) {
                object object = objects_in_composite.get(j);
                all_objects.add(object);
            }
            all_objects.remove(composite);
        }
        // object composite = selected_objects.get(0);
        // ArrayList<object> objects_in_composite = composite.get_objects();
        // all_objects.remove(composite);
        // for (int i = 0; i < objects_in_composite.size(); i++) {
        //     object object = objects_in_composite.get(i);
        //     all_objects.add(object);
        // }
        // all_objects.remove(composite);
        repaint();
    }

    public void rename(String name) {
        if (selected_objects.size() != 1) {
            return;
        }
        if (selected_objects.get(0).get_name() != null) {
            selected_objects.get(0).rename(name);
            repaint();
        }
    }

    public void set_mode(mode mode) {
        current_mode = mode;
        // reset_selection();
        repaint();
    }

    public boolean check_selected_area(object object) {
        Point upper_left = new Point(object.get_point1());
        Point lower_right = new Point(object.get_point2());

        if (selected_area.contains(upper_left) && selected_area.contains(lower_right)) {
            return true;
        }
        return false;
    }

    public void reset_selection() {
        for (int i = 0; i < selected_objects.size(); i++) {
            object selected_object = selected_objects.get(i);
            selected_object.reset_selected_object();
        }
        selected_objects.clear();
        selected_area.setBounds(0, 0, 0, 0);
    }

    public void paint(Graphics g) {
        // Set canvas
        Dimension dimension = getSize();
        // Set canvas color
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, dimension.width, dimension.height);
        // Set drawing color
        g.setColor(Color.BLACK);
        // Set stroke wdith
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(2));

        // Draw all objects
        for (int i = 0; i < all_objects.size(); i++) {
            object object = all_objects.get(i);
            object.draw(g);
            // Check if selected
            if (selected_objects.contains(object)) {
                object.show_selected(g);
            }
            
        }

        // Draw drag line
        if (drag_line != null) {
            drag_line.draw(g);
        }

        // Draw selected area
        if (!selected_area.isEmpty()) {
            int alpha = 64; // ~25% transparency
            // Draw the outline of the selected area
            g.setColor(new Color(79, 179, 134));
            g.drawRect(selected_area.x, selected_area.y, selected_area.width, selected_area.height);
            // Fill the inner part of the selected area
            g.setColor(new Color(224, 255, 241, alpha));
            g.fillRect(selected_area.x, selected_area.y, selected_area.width, selected_area.height);
        }
    }

    private class mouse_listener extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent e) {
            current_mode.mouse_pressed(e);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            current_mode.mouse_released(e);
        }
    }

    private class mouse_motion_listener extends MouseAdapter {
        
        @Override
        public void mouseDragged(MouseEvent e) {
            current_mode.mouse_dragged(e);
        }
    }
}
