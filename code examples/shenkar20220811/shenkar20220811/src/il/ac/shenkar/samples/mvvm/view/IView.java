package il.ac.shenkar.samples.mvvm.view;

import il.ac.shenkar.samples.mvvm.viewmodel.IViewModel;

public interface IView {
    public void setViewModel(IViewModel ob);
    public void setFileContentMessages(String message);
    public void setFilesAndDirectoriesMessages(String message);
    public void setListOfFilesAndDirectories(String[] list);
    public void start();
}
