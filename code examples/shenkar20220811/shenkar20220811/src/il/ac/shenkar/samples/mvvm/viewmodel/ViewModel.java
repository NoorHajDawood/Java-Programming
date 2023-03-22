package il.ac.shenkar.samples.mvvm.viewmodel;

import il.ac.shenkar.samples.mvvm.FilesManagerException;
import il.ac.shenkar.samples.mvvm.model.IModel;
import il.ac.shenkar.samples.mvvm.view.IView;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ViewModel implements IViewModel {

    private ExecutorService service;
    private IView view;
    private IModel model;

    public ViewModel() {
        service = Executors.newFixedThreadPool(8);
    }

    @Override
    public void getFilesAndFoldersNames(String directory) {
        service.submit(()->{
            try {
                String[] vec = model.getFilesAndFoldersNames(directory);
                view.setListOfFilesAndDirectories(vec);

            }
            catch(FilesManagerException e) {

                e.printStackTrace();
                view.setFilesAndDirectoriesMessages("problem with getting list of files and folders");

            }
        });
    }

    public IView getView() {
        return view;
    }

    public void setView(IView view) {
        this.view = view;
    }

    public IModel getModel() {
        return model;
    }

    public void setModel(IModel model) {
        this.model = model;
    }
}
