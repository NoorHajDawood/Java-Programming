package il.ac.shenkar.java.samples.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CalculatorUI {

    private JTextField tf1, tf2, tf3;
    private JButton btPlus, btMinus;
    private JLabel label;
    private JFrame frame;
    private JPanel panel;


    public CalculatorUI() {
        tf1 = new JTextField(10);
        tf2 = new JTextField(10);
        tf3 = new JTextField(10);
        btPlus = new JButton("+");
        btMinus = new JButton("-");
        label = new JLabel("=");
        panel = new JPanel();
        frame = new JFrame();
    }

    public void start() {

        //building the panel
        panel.add(btPlus);
        panel.add(btMinus);
        panel.setBackground(Color.GREEN);
        panel.setLayout(new FlowLayout());

        //building the frame (window)
        frame.setLayout(new BorderLayout());
        frame.add(BorderLayout.WEST,tf1);
        frame.add(BorderLayout.NORTH,panel);
        frame.add(BorderLayout.EAST,tf2);
        frame.add(BorderLayout.CENTER,label);
        frame.add(BorderLayout.SOUTH, tf3);

        //handling the ui events
        ActionListener listener = new ButtonsListener();
        btPlus.addActionListener(listener);
        btMinus.addActionListener(listener);
        /*frame.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                //frame.dispose();
                //frame.setVisible(false);
                System.exit(0);
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        }); */
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                //super.windowClosing(e);
                System.exit(0);
            }
        });

        //displaying the frame
        frame.setSize(500,400);
        frame.setVisible(true);

    }

    class ButtonsListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==btPlus) {
                System.out.println("plus!");
            } else if(e.getSource()==btMinus) {
                System.out.println("minus!");
            }
        }
    }


}
