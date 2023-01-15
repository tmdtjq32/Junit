package me.tmdtjq32.myproject.src.repository;

import me.tmdtjq32.myproject.src.model.entity.Study;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudyRepository extends JpaRepository<Study,Long> {
}
