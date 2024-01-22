package com.jcgc.platzimarket.persistence;

import com.jcgc.platzimarket.domain.Product;
import com.jcgc.platzimarket.domain.repository.ProductRepository;
import com.jcgc.platzimarket.persistence.crud.ProductoCrudRepository;
import com.jcgc.platzimarket.persistence.entity.Producto;
import com.jcgc.platzimarket.persistence.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductoRepository implements ProductRepository {

    @Autowired
    private ProductoCrudRepository productoCrudRepository;

    @Autowired
    private ProductMapper mapper;

    @Override
    public List<Product> getAll(){
        List<Producto> productos = (List<Producto>) productoCrudRepository.findAll();
        return mapper.toProducts(productos);

    }

    @Override
    public Optional<List<Product>> getByCategory(int categoryId) {
        List<Producto> productos = productoCrudRepository.findByIdCategoriaOrderByNombreAsc(categoryId);
        return Optional.of(mapper.toProducts(productos));
    }

    @Override
    public Optional<List<Product>> getScarseProducts(int quantity) {
        Optional<List<Producto>> productos = productoCrudRepository.findByCantidadStockLessThanAndEstado(quantity, true);
        return productos.map(prods -> mapper.toProducts(prods));
    }

    @Override
    public Optional<Product> getProduct(int productId) {
        return productoCrudRepository.findById(productId).map(prods -> mapper.toProduct(prods));
    }

    public Product save(Product product) {
        Producto producto = mapper.toProducto(product);
        return mapper.toProduct(productoCrudRepository.save(producto));
    }

    @Override
    public void delete(int productId) {
        productoCrudRepository.deleteById(productId);
    }

    public Producto update(Producto newProducto, int idProducto) {
        return productoCrudRepository.findById(idProducto)
                .map(producto -> {
                            producto.setCantidadStock(newProducto.getCantidadStock());
                            producto.setIdCategoria(newProducto.getIdCategoria());
                            producto.setEstado(newProducto.getEstado());
                            producto.setNombre(newProducto.getNombre());
                            producto.setCodigoBarras(newProducto.getCodigoBarras());
                            producto.setPrecioVenta(newProducto.getPrecioVenta());
                            return productoCrudRepository.save(producto);
                        }
                ).get();
    }
}
