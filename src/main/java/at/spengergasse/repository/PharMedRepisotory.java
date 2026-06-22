package at.spengergasse.repository;

import at.spengergasse.domain.PharMed;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PharMedRepisotory extends JpaRepository<PharMed,Long>
{
}
