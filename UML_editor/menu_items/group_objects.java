package UML_editor.menu_items;

public class group_objects extends menu_item {
    
    public group_objects() {
        super("group objects");
    }

    @Override
    public void perform_action() {
        my_canvas.create_composite();
    }
}
