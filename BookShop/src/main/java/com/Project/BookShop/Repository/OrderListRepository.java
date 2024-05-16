package com.Project.BookShop.Repository;

import com.Project.BookShop.Entity.OrderListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderListRepository extends JpaRepository<OrderListEntity, Long> {
}
