package UML_editor;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import UML_mode.select_mode;

public class editor extends JFrame {
    private menu_bar menu_bar;
    private tool_bar tool_bar;
    private canvas my_canvas;

    public editor(int window_width, int window_height) {
        tool_bar = new tool_bar();
        System.out.println("new tool bar");
        menu_bar = new menu_bar();
        my_canvas = canvas.get_canvas();

        // Initialize the editor
        setLayout(new BorderLayout());
        setTitle("UML editor");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(window_width, window_height);
        my_canvas.set_mode(new select_mode());

        // Add the menu bar, tool bar and canvas
        add(tool_bar, BorderLayout.WEST);
        add(menu_bar, BorderLayout.NORTH);
        add(my_canvas, BorderLayout.CENTER);

        // Show the editor
        setVisible(true);
    }
}
