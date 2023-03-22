package il.ac.shenkar.samples.mvvm.view;

import il.ac.shenkar.samples.mvvm.viewmodel.IViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class View implements IView {

    private IViewModel vm;
    private JFrame frame;
    private JPanel panelLeft, panelRight, panelLeftTop, panelRightTop;
    private JButton btGetFileContent, btGetListOfFilesAndDirectories;
    private JTextField tfFileContent, tfListOfFilesAndDirectories, tfFileContentMessages, tfListOfFilesAndDirectoriesMessages;
    private JTextArea taFileContent, taListOfFilesAndDirectories;
    private JScrollPane spFileContent, spListOfFilesAndDirectories;

    public View() {
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
        tfFileContentMessages = new JTextField(10);
        tfListOfFilesAndDirectoriesMessages = new JTextField(10);
        spFileContent = new JScrollPane(taFileContent);
        spListOfFilesAndDirectories = new JScrollPane((taListOfFilesAndDirectories));

    }

    public void start() {
        //setting the layout managers
        frame.setLayout(new GridLayout(1,2));
        panelRight.setBackground(Color.GREEN);
        panelLeft.setBackground(Color.YELLOW);
        panelLeft.setLayout(new BorderLayout());
        panelRight.setLayout(new BorderLayout());

        //building the left panel top
        panelLeftTop.setBackground(Color.ORANGE);
        panelLeftTop.add(btGetListOfFilesAndDirectories);
        panelLeftTop.add(tfListOfFilesAndDirectories);

        //building the left panel
        frame.add(panelLeft);
        panelLeft.add(panelLeftTop,BorderLayout.NORTH);
        panelLeft.add(tfListOfFilesAndDirectoriesMessages, BorderLayout.SOUTH);
        panelLeft.add(spListOfFilesAndDirectories,BorderLayout.CENTER);

        //building the right panel top
        panelRightTop.setBackground(Color.DARK_GRAY);
        panelRightTop.add(btGetFileContent);
        panelRightTop.add(tfFileContent);

        //building the right panel
        frame.add(panelRight);
        panelRight.add(panelRightTop,BorderLayout.NORTH);
        panelRight.add(tfFileContentMessages, BorderLayout.SOUTH);
        panelRight.add(spFileContent,BorderLayout.CENTER);


        //handling btGetListOfFilesAndDirectories event
        btGetListOfFilesAndDirectories.addActionListener(action-> {
            String path = tfListOfFilesAndDirectories.getText();
            vm.getFilesAndFoldersNames(path);
        });


        //showing the frame
        frame.setSize(800,600);
        frame.setVisible(true);

        //handling frame events
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });


    }


    @Override
    public void setViewModel(IViewModel ob) {
        vm = ob;
    }

    @Override
    public void setFileContentMessages(String message) {
        this.tfFileContentMessages.setText(message);
    }

    @Override
    public void setFilesAndDirectoriesMessages(String message) {
        this.tfListOfFilesAndDirectoriesMessages.setText(message);
    }

    @Override
    public void setListOfFilesAndDirectories(String[] list) {

        if(SwingUtilities.isEventDispatchThread()) {

            StringBuffer sb = new StringBuffer();

            for(String str : list) {
                sb.append(str);
                sb.append(" ... ");
            }

            taListOfFilesAndDirectories.setText(sb.toString());



        } else {

            SwingUtilities.invokeLater(() -> {


                StringBuffer sb = new StringBuffer();

                for(String str : list) {
                    sb.append(str);
                    sb.append(" ... ");
                }

                taListOfFilesAndDirectories.setText(sb.toString());


            });


        }




    }
}
