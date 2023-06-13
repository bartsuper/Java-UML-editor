package UML_editor.menu_items;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;

import UML_editor.canvas;

public abstract class menu_item extends JMenuItem {
    protected canvas my_canvas = canvas.get_canvas();

    public menu_item(String name) {
        super(name);
        addActionListener(new action_listener());
    }

    public abstract void perform_action();

    private class action_listener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            perform_action();
        }
    }
}
