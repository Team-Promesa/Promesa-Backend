package com.promesa.promesa.domain.item.dao;

import com.promesa.promesa.domain.item.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
