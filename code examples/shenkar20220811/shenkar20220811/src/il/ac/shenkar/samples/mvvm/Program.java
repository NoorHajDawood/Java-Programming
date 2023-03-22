package il.ac.shenkar.samples.mvvm;

import il.ac.shenkar.samples.mvvm.model.*;
import il.ac.shenkar.samples.mvvm.view.*;
import il.ac.shenkar.samples.mvvm.viewmodel.IViewModel;
import il.ac.shenkar.samples.mvvm.viewmodel.ViewModel;

import javax.swing.*;

public class Program {
    public static void main(String args[]) {

        IModel model = new Model();
        IViewModel vm = new ViewModel();

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                IView view = new View();
                view.start();
                view.setViewModel(vm);
                vm.setModel(model);
                vm.setView(view);
            }
        });
    }
}
