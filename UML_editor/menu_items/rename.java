package UML_editor.menu_items;

import javax.swing.JOptionPane;

public class rename extends menu_item {
    
    public rename() {
        super("rename");
    }

    @Override
    public void perform_action() {
        String name = JOptionPane.showInputDialog(my_canvas, "new name : ");
        if (name != null) {
            my_canvas.rename(name);
        }
    }
}
