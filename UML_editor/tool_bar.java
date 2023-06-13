package UML_editor;

import javax.swing.ButtonGroup;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import UML_mode.mode;
import UML_mode.create_line_mode;
import UML_mode.create_basic_object_mode;
import UML_mode.select_mode;

public class tool_bar extends JToolBar {
    private final ButtonGroup button_group = new ButtonGroup();
    private final ImageIcon select_icon = new ImageIcon("img/selection.PNG");
    private final ImageIcon association_icon = new ImageIcon("img/association.PNG");
    private final ImageIcon generalization_icon = new ImageIcon("img/generalization.PNG");
    private final ImageIcon composition_icon = new ImageIcon("img/composition.PNG");
    private final ImageIcon class_icon = new ImageIcon("img/class.PNG");
    private final ImageIcon usecase_icon = new ImageIcon("img/usecase.PNG");
    private canvas my_canvas;
    // private Color button_pressed_color = new Color()

    public tool_bar() {
        my_canvas = canvas.get_canvas();
        
        // Set tool bar layout
        setLayout(new GridLayout(6, 1, 20, 5));
        setBackground(Color.WHITE);

        // Add buttons to the tool bar
        add_button(new tool_button("select", select_icon, new select_mode()));
        add_button(new tool_button("create association line", association_icon, new create_line_mode("association line")));
        add_button(new tool_button("create generalization line", generalization_icon, new create_line_mode("generalization line")));
        add_button(new tool_button("create composition line", composition_icon, new create_line_mode("composition line")));
        add_button(new tool_button("create class", class_icon, new create_basic_object_mode("class object")));
        add_button(new tool_button("create usecase", usecase_icon, new create_basic_object_mode("usecase object")));
    }

    private void add_button(tool_button button) {
        button_group.add(button);
        add(button);
    }

    private class tool_button extends JToggleButton {
        private mode mode;

        public tool_button(String name, ImageIcon icon, mode mode) {
            this.mode = mode;
            // Resize the image icon
            ImageIcon resized_icon = new ImageIcon(icon.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
            setToolTipText(name);
            setIcon(resized_icon);
            addActionListener(new tool_listener());
        }

        private class tool_listener implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                my_canvas.set_mode(mode);
            }
        }
    }
}
