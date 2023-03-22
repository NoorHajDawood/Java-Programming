package il.ac.shenkar.samples.mvvm.model;

import il.ac.shenkar.samples.mvvm.FilesManagerException;

public interface IModel {

    public String[] getFilesAndFoldersNames(String directory) throws FilesManagerException;
    public String getTextualFileContent(String file) throws FilesManagerException;

}
