package il.ac.shenkar.java.project.view;

import il.ac.shenkar.java.project.dao.Category;
import il.ac.shenkar.java.project.dao.Cost;
import il.ac.shenkar.java.project.viewmodel.IViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import com.toedter.calendar.JDateChooser;

public class View implements IView {
    /**
     * viewmodel reference
     */
    private IViewModel vm;
    /**
     * main frame reference
     */
    private JFrame mainFrame;
    /**
     * reference to each panel
     */
    private JPanel mainPanel, titlePanel, costPanel, categoryPanel, reportPanel, reportWrapper;

    /**
     * labels in the costs panel
     */
    private JLabel costSumLabel, costCategoryLabel, costDescriptionLabel, costDateLabel, costCurrencyLabel;
    /**
     * text fields in the costs panel
     */
    private JTextField costSumTF, costDescriptionTF;
    /**
     * combobox in the costs panel
     */
    private JComboBox<String> costCategoryCB, costCurrencyCB;
    /**
     * date chooser in the costs panel
     */
    private JDateChooser costDateDC;
    /**
     * button in the costs panel
     */
    private JButton costSubmitBtn;

    /**
     * labels in the category panel
     */
    private JLabel categoryNameLabel;
    /**
     * text field in the category panel
     */
    private JTextField categoryNameTF;
    /**
     * button in the category panel
     */
    private JButton categorySubmitBtn;
    /**
     * text area in the category panel
     */
    private JTextArea categoryTA;

    /**
     * labels in the report panel
     */
    private JLabel reportFromLabel, reportToLabel;
    /**
     * date choosers in the report panel
     */
    private JDateChooser reportFromDC, reportToDC;
    /**
     * button in the report panel
     */
    private JButton reportSubmitBtn;
    /**
     * text area in the report panel
     */
    private JTextArea reportTA;
    /**
     * scroll pane in the report panel
     */
    private JScrollPane reportTASP;

    /**
     * constructor to initialize all elements
     */
    public View() {
        // initalize the frames and panels
        mainFrame = new JFrame("Costs Manager");
        mainPanel = new JPanel();
        titlePanel = new JPanel();
        costPanel = new JPanel();
        categoryPanel = new JPanel();
        reportPanel = new JPanel();
        reportWrapper = new JPanel();

        // initialize cost panel elements
        costSumLabel = new JLabel("sum:");
        costCategoryLabel = new JLabel("category:");
        costDescriptionLabel = new JLabel("Description:");
        costDateLabel = new JLabel("Date:");
        costCurrencyLabel = new JLabel("Currency:");
        costSumTF = new JTextField(10);
        costDescriptionTF = new JTextField(10);
        costCategoryCB = new JComboBox<String>();
        costCurrencyCB = new JComboBox<String>();
        costDateDC = new JDateChooser();
        costSubmitBtn = new JButton("Add Cost");

        // initialize category panel elements
        categoryNameLabel = new JLabel("Name:");
        categoryNameTF = new JTextField(10);
        categorySubmitBtn = new JButton("Add Category");
        categoryTA = new JTextArea();

        // initalize report panel elements
        reportFromLabel = new JLabel("From:");
        reportToLabel = new JLabel("To");
        reportFromDC = new JDateChooser();
        reportToDC = new JDateChooser();
        reportSubmitBtn = new JButton("Show Costs");
        reportTA = new JTextArea(10,1);
        reportTASP = new JScrollPane(reportTA);
    }
    /**
     * set the viewmodel reference
     * @param vm
     */
    @Override
    public void setViewModel(IViewModel vm) {
        this.vm = vm;

    }
    /**
     * start the interface
     */
    public void start() {
        // setup the main frame and main panel
        BoxLayout boxlayout = new BoxLayout(mainPanel, BoxLayout.Y_AXIS);
        mainPanel.setLayout(boxlayout);
        JLabel titleLabel = new JLabel("<html><span style='color: teal;'>Costs Manager</span></html>", SwingConstants.CENTER);
        titleLabel.setFont(titleLabel.getFont().deriveFont(32.0F));
        titlePanel.add(titleLabel);
        mainPanel.add(titlePanel);
        mainPanel.add(Box.createVerticalStrut(10));

        // setup cost panel and its elements
        costPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        costPanel.setBorder(BorderFactory.createTitledBorder("New Cost"));
        costPanel.add(costSumLabel);
        costPanel.add(costSumTF);
        costPanel.add(costCurrencyLabel);
        costCurrencyCB.addItem("NIS");
        costCurrencyCB.addItem("USD");
        costCurrencyCB.addItem("EUR");
        costPanel.add(costCurrencyCB);
        costPanel.add(costCategoryLabel);
        costPanel.add(costCategoryCB);
        costCategoryCB.setPrototypeDisplayValue("Category Name");
        costPanel.add(costDescriptionLabel);
        costPanel.add(costDescriptionTF);
        costPanel.add(costDateLabel);
        costPanel.add(costDateDC);
        // add an action listener for adding a new cost
        costSubmitBtn.addActionListener(action -> {
            String categoryName = (String) costCategoryCB.getSelectedItem();
            Float sum = 0F;
            try {
                sum = Float.parseFloat(costSumTF.getText());
            } catch (NumberFormatException e) {
                e.printStackTrace();
                displayMSG("Sum must be a float number.","ERROR", JOptionPane.ERROR_MESSAGE);

                return;
            }
            if (categoryName == null) {
                displayMSG("Must select a category.","ERROR", JOptionPane.ERROR_MESSAGE);

                return;
            }
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(costDateDC.getDate());

            vm.addCost(sum, (String)costCurrencyCB.getSelectedItem(), categoryName, costDescriptionTF.getText(), new Date(calendar.getTimeInMillis()));
        });
        costPanel.add(costSubmitBtn);
        costPanel.add(Box.createHorizontalStrut(10));
        mainPanel.add(costPanel);
        // spacer
        mainPanel.add(Box.createVerticalStrut(25));

        // setup category panel and its elements
        categoryPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        categoryPanel.setBorder(BorderFactory.createTitledBorder("New Category"));
        categoryPanel.add(categoryNameLabel);
        categoryPanel.add(categoryNameTF);
        // add an action listener to add a new category
        categorySubmitBtn.addActionListener(action -> {
            String name = categoryNameTF.getText();
            if(name == null || name.equals("")) {
                displayMSG("Category name can't be empty.","ERROR", JOptionPane.ERROR_MESSAGE);
                return;
            }
            vm.addCategory(name);
        });
        categoryPanel.add(categorySubmitBtn);
        mainPanel.add(categoryPanel);
        // spacer
        mainPanel.add(Box.createVerticalStrut(25));

        // setup report panel and its elements
        BoxLayout reportboxlayout = new BoxLayout(reportWrapper, BoxLayout.Y_AXIS);
        reportWrapper.setLayout(reportboxlayout);
        reportPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        reportWrapper.setBorder(BorderFactory.createTitledBorder("Costs Report"));
        reportPanel.add(reportFromLabel);
        reportPanel.add(reportFromDC);
        reportPanel.add(reportToLabel);
        reportPanel.add(reportToDC);
        reportPanel.add(reportSubmitBtn);
        // add an action listener to list a costs report
        reportSubmitBtn.addActionListener(action -> {
            Calendar startCalendar = new GregorianCalendar();
            Calendar endCalendar = new GregorianCalendar();
            startCalendar.setTime(reportFromDC.getDate());
            endCalendar.setTime(reportToDC.getDate());
            vm.getCosts(new Date(startCalendar.getTimeInMillis()), new Date(endCalendar.getTimeInMillis()));
        });
        reportWrapper.add(reportPanel);
        reportWrapper.add(reportTASP);
        mainPanel.add(reportWrapper);

        // change behavior of close button
        mainFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        // display the main frame
        mainFrame.add(mainPanel);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }

    /**
     * helper function for setCosts
     * @param costs
     */
    private void updateReport(List<Cost> costs) {
        // print costs report where each line is: [date] [category] cost currency => description.
        StringBuffer report = new StringBuffer();
        for (Cost cost : costs) {
            report.append("[")
                    .append(cost.getDate())
                    .append("] [")
                    .append(cost.getCategoryName())
                    .append("] ")
                    .append(cost.getSum())
                    .append(" ")
                    .append(cost.getCurrency())
                    .append(" => ")
                    .append(cost.getDescription())
                    .append("\n");
        }
        reportTA.setText(report.toString());
    }
    /**
     * set the costs in the view
     * @param costs
     */
    @Override
    public void setCosts(List<Cost> costs) {
        // make sure to only execute in the dispatch thread
        if (SwingUtilities.isEventDispatchThread()) {
            updateReport(costs);
        } else {
            SwingUtilities.invokeLater(() -> {
                updateReport(costs);
            });
        }
    }

    /**
     * helper function for setCategories
     * @param categories
     */
    private void updateCategories(List<Category> categories) {
        // update categories in combobox
        costCategoryCB.removeAllItems();
        StringBuffer sb = new StringBuffer();
        for (Category category : categories) {
            costCategoryCB.addItem(category.getName());
            sb.append(category.getName()).append("\n");
        }
        categoryTA.setText(sb.toString());
    }
    /**
     * set the categories in the view
     * @param categories
     */
    @Override
    public void setCategories(List<Category> categories) {
        // make sure to only execute in the dispatch thread
        if (SwingUtilities.isEventDispatchThread()) {
            updateCategories(categories);
        } else {
            SwingUtilities.invokeLater(() -> {
                updateCategories(categories);
            });
        }
    }
    /**
     * display a message to the user
     * @param msg
     * @param type
     * @param msgType
     */
    public void displayMSG(String msg , String type ,int msgType){
        // make sure to only execute in the dispatch thread
        if (SwingUtilities.isEventDispatchThread()) {
            JOptionPane.showMessageDialog(mainFrame, msg,
                    type, msgType);
        } else {
            SwingUtilities.invokeLater(() -> {
                JOptionPane.showMessageDialog(mainFrame, msg,
                        type, msgType);
            });
        }

    }
    /**
     * sync the categories in the view with the database
     */
    @Override
    public void syncCategories() {
        vm.getCategories();
    }
}
