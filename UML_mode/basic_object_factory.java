package UML_mode;

import java.awt.Point;

import UML_object.class_object;
import UML_object.object;
import UML_object.usecase_object;

public class basic_object_factory extends object_factory_baseclass {
    private String name;
    private int min_margin = 10;
    private int class_width = 100;
    private int class_height = 150;
    private int usecase_width = 200;
    private int usecase_height = 100;

    @Override
    public object create_object(String object_type, Point p, int depth) {
        name = "depth : " + Integer.toString(depth);

        if (object_type.equals("class object")) {
            return new class_object(name, p, class_width, class_height, min_margin, depth);
        }
        if (object_type.equals("usecase object")) {
            return new usecase_object(name, p, usecase_width, usecase_height, min_margin, depth);
        }
        return null;
    }
    
}
