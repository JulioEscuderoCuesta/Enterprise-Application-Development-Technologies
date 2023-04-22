package es.unican.empresariales.julio.polaflix.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import es.unican.empresariales.julio.polaflix.entities.Categorie;

public interface CategorieRepository extends JpaRepository<Categorie, Long> {
    
}
