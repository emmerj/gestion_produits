package com.jfboily.gestion_produits.data

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id


@Entity
data class Produit (
    @Id
    @GeneratedValue
    val id: Long?,
    val nom: String,
    val fabriquant: String,
    val type: String,
    var prix: Double,
    var inventaire: Int
        );
