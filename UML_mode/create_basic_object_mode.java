package UML_mode;

import java.awt.event.MouseEvent;
import UML_object.object;

public class create_basic_object_mode extends mode {
    private String object_type;
    // private object_factory factory = new object_factory();
    private object_factory_baseclass factory = new basic_object_factory();

    public create_basic_object_mode(String object_type) {
        this.object_type = object_type;
    }

    @Override
    public void mouse_pressed(MouseEvent e) {
        int current_depth = my_canvas.get_current_depth();
        object object = factory.create_object(object_type, e.getPoint(), current_depth);
        my_canvas.add_depth();
        my_canvas.create_object(object);
        my_canvas.repaint();
    }
}
