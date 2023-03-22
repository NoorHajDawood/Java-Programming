package il.ac.shenkar.samples.dao;

import java.util.*;

public class IWebStoreDAODemo {
    public static void main(String args[]) throws WebStoreDBException, ClassNotFoundException {

        IWebStoreDAO dao = new MySQLWebStoreDAO();
        Iterator<Product> iterator = dao.getProducts().iterator();
        while(iterator.hasNext()) {
            System.out.println(iterator.next());
        }

    }
}
