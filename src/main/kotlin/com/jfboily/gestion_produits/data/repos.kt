package com.jfboily.gestion_produits.data

import org.springframework.data.jpa.repository.JpaRepository

interface ProduitsReposistory: JpaRepository<Produit, Long>
