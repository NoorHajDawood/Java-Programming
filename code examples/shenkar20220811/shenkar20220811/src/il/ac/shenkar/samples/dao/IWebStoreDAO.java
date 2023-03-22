package il.ac.shenkar.samples.dao;

import java.util.List;

public interface IWebStoreDAO {
    public boolean addProduct(Product product) throws WebStoreDBException;
    public List<Product> getProducts() throws WebStoreDBException;
}
