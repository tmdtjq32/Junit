package me.tmdtjq32.myproject.study;

import me.tmdtjq32.myproject.domain.Study;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyRepository extends JpaRepository<Study,Long> {
}
