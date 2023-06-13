package UML_editor;

import UML_editor.menu_items.rename;
import UML_editor.menu_items.group_objects;
import UML_editor.menu_items.ungroup_objects;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

public class menu_bar extends JMenuBar {
    private canvas my_canvas;
    private final JMenu file_menu, edit_menu;

    public menu_bar() {
        my_canvas = canvas.get_canvas();

        // Add file menu
        file_menu = new JMenu("file");
        add(file_menu);

        // Add edit menu and its menu items
        edit_menu = new JMenu("edit");
        // Add rename option
        edit_menu.add(new rename());
        // Add group objects option
        edit_menu.add(new group_objects());
        // Add ungroup objects option
        edit_menu.add(new ungroup_objects());
        add(edit_menu);
    }
}
