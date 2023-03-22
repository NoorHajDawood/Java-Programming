package il.ac.shenkar.java.samples.mvvmdemo.view;

import javax.swing.*;
import java.awt.*;

public class SimpleFilesManagerView implements IFilesManageView{

    private JFrame frame;
    private JPanel panelLeft, panelRight, panelLeftTop, panelRightTop;
    private JButton btGetFileContent, btGetListOfFilesAndDirectories;
    private JTextField tfFileContent, tfListOfFilesAndDirectories;
    private JTextArea taFileContent, taListOfFilesAndDirectories;

    public SimpleFilesManagerView() {
        frame = new JFrame("Simple Files Manager");
        panelLeft = new JPanel();
        panelRight = new JPanel();
        panelLeftTop = new JPanel();
        panelRightTop = new JPanel();
        btGetFileContent = new JButton("Get File Content");
        btGetListOfFilesAndDirectories = new JButton("Get List of Files & Directories");
        tfFileContent = new JTextField(10);
        tfListOfFilesAndDirectories = new JTextField(10);
        taFileContent = new JTextArea();
        taListOfFilesAndDirectories = new JTextArea();
    }

    public void start () {

        // setting the layout managers
        frame.setLayout(new GridLayout(1, 2));
        panelLeft.setLayout(new BorderLayout());
        panelRight.setLayout(new BorderLayout());

        //building the left panel
        frame.add(panelLeft);

        //building the right panel
        frame.add(panelRight);

        frame.add(panelLeft);
        frame.add(panelRight);

    }
}
