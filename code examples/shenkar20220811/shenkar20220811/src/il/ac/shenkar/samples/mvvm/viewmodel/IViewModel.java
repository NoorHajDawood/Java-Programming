package il.ac.shenkar.samples.mvvm.viewmodel;

import il.ac.shenkar.samples.mvvm.model.IModel;
import il.ac.shenkar.samples.mvvm.view.IView;

public interface IViewModel {
    public void getFilesAndFoldersNames(String directory);
    public void setModel(IModel model);
    public void setView(IView view);
}
