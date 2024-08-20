package oasis.piloti.repository;

import oasis.piloti.entity.StudyCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudyCardRepository extends JpaRepository<StudyCard, Long> {
}
