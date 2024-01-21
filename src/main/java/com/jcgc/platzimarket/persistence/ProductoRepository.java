package com.jcgc.platzimarket.persistence;

import com.jcgc.platzimarket.persistence.crud.ProductoCrudRepository;
import com.jcgc.platzimarket.persistence.entity.Producto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductoRepository {

    private ProductoCrudRepository productoCrudRepository;

    public List<Producto> getAll(){
        return (List<Producto>) productoCrudRepository.findAll();
    }

    public List<Producto> getByCategoria(int idCategoria) {
        return productoCrudRepository.findByIdCategoriaOrderByNombreAsc(idCategoria);
    }

    public Optional<List<Producto>> getEscasos(int cantidad) {
        return productoCrudRepository.findByCantidadStockLessThanAndEstado(cantidad, true);
    }

    public Optional<Producto> getProducto(int idProducto) {
        return productoCrudRepository.findById(idProducto);
    }

    public Producto save(Producto producto) {
        return productoCrudRepository.save(producto);
    }

    public void delete(int idProducto) {
        productoCrudRepository.deleteById(idProducto);
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
