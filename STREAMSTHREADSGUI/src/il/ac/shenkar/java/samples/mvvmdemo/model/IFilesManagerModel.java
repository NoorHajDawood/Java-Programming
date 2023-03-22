package il.ac.shenkar.java.samples.mvvmdemo.model;

import il.ac.shenkar.java.samples.mvvmdemo.FilesManagerException;

public interface IFilesManagerModel {

    public String[] getFilesAndFoldersNames(String directory) throws FilesManagerException;
    public String getTextualFileContent(String file) throws FilesManagerException;

}
