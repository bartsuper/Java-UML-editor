package UML_mode;
import java.awt.Point;

import UML_object.association_line;
import UML_object.composition_line;
import UML_object.generalization_line;
import UML_object.object;

public class line_factory extends object_factory_baseclass {
    private int arrow_width = 10;
    private int arrow_height = 10;

    public object create_object(String line_type, Point start_point, Point end_point) {
        if (line_type.equals("association line")) {
            return new association_line(start_point, end_point, arrow_width, arrow_height);
        }
        if (line_type.equals("generalization line")) {
            return new generalization_line(start_point, end_point, arrow_width, arrow_height);
        }
        if (line_type.equals("composition line")) {
            return new composition_line(start_point, end_point, arrow_width, arrow_height);
        }
        return null;
    }
}
