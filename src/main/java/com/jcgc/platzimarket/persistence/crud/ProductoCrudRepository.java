package com.jcgc.platzimarket.persistence.crud;

import com.jcgc.platzimarket.persistence.entity.Producto;
import org.springframework.data.repository.CrudRepository;

public interface ProductoCrudRepository extends CrudRepository<Producto, Integer> {
}
