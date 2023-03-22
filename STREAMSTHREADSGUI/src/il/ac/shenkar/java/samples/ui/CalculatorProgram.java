package il.ac.shenkar.java.samples.ui;

import javax.swing.*;

public class CalculatorProgram {
    public static void main(String args[]) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                CalculatorUI ui = new CalculatorUI();
                ui.start();
            }
        });


    }
}
