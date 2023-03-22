package il.ac.shenkar.java.samples.iostreams;

import java.io.*;
import java.net.URL;

public class SimpleFilesUtils implements IFilesUtils{
    @Override
    public void writeIntToFile(String file, int number) throws FilesUtilsException{
        FileOutputStream fos = null;
        DataOutputStream dos = null;

        try {
            fos = new FileOutputStream(file);
            dos = new DataOutputStream(fos);
            dos.writeInt(number);

        } catch (IOException e) {
            throw new FilesUtilsException("problem with writing int to file",e);
        } finally {
            if(dos!=null) {
                try {
                    dos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(fos!=null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /*
    @Override
    public int readIntFromFile(String file) throws FilesUtilsException {
        FileInputStream fis = null;
        DataInputStream dis = null;

        try {
            fis = new FileInputStream(file);
            dis = new DataInputStream(fis);
            return dis.readInt();

        } catch (IOException e) {
            throw new FilesUtilsException("problem with writing int to file",e);
        } finally {
            if(dis!=null) {
                try {
                    dis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(fis!=null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
     */
    @Override
    public int readIntFromFile(String file) throws FilesUtilsException {

        try(FileInputStream fis = new FileInputStream(file);
            DataInputStream dis = new DataInputStream(fis)) {
            return dis.readInt();
        }
        catch(IOException e) {
            throw new FilesUtilsException("problem with reading from file", e);
        }


    }

    @Override
    public void writeObjectToFile(String file, Object ob) throws FilesUtilsException {

        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {

            fos = new FileOutputStream(file);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(ob);


        }
        catch(IOException e) {
            throw new FilesUtilsException("problem writing object to file",e);
        }
        finally {
            if(oos!=null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    @Override
    public Object readObjectFromFile(String file) throws FilesUtilsException {

        FileInputStream fis = null;
        ObjectInputStream ois = null;

        try {

            fis = new FileInputStream(file);
            ois = new ObjectInputStream(fis);
            return ois.readObject();

        } catch(IOException | ClassNotFoundException e) {
            throw new FilesUtilsException("problem with reading object from file",e);
        } finally {
            if(ois!=null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void copyTextualFile(String from, String to) throws FilesUtilsException {
        FileReader fr = null;
        FileWriter fw = null;
        BufferedReader br = null;
        BufferedWriter bw = null;
        try {

            fr = new FileReader(from);
            fw = new FileWriter(to);
            br = new BufferedReader(fr);
            bw = new BufferedWriter(fw);

            String currentLine = br.readLine();
            while(currentLine!=null) {

                bw.write(currentLine,0,currentLine.length());
                bw.newLine();
                currentLine = br.readLine();
            }

            bw.flush();

        } catch(IOException e) {
            throw new FilesUtilsException("problem with copy one file to another",e);
        }
    }

    @Override
    public String readTextFromURL(String address) throws FilesUtilsException {

        InputStream is = null;
        try {
            URL url = new URL(address);
            is = url.openStream();
            StringBuilder sb = new StringBuilder();
            int data = is.read();
            while(data!=-1) {
                sb.append((char)data);
                data = is.read();
            }
            return sb.toString();

        } catch(IOException e) {
            throw new FilesUtilsException("problem reading from url address",e);
        } finally {
            if(is!=null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
