package UML_editor.menu_items;

public class ungroup_objects extends menu_item {
    
    public ungroup_objects() {
        super("ungroup objects");
    }

    @Override
    public void perform_action() {
        my_canvas.cancel_composite();
    }
}
