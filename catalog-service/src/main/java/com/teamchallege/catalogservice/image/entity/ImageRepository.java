package com.teamchallege.catalogservice.image.entity;

import com.teamchallege.common.entities.catalog.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

}
