package oasis.piloti.Dictionary.Repository;


import oasis.piloti.Dictionary.Domain.Dictionary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DictionaryRepository extends JpaRepository<Dictionary.Word, Long> {
}
