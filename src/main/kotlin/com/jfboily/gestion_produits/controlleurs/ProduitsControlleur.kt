package com.jfboily.gestion_produits.controlleurs

import com.jfboily.gestion_produits.data.Produit
import com.jfboily.gestion_produits.data.ProduitsReposistory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import java.util.logging.Level
import java.util.logging.Logger

@RestController
class ProduitsControlleur(val produitsReposistory: ProduitsReposistory) {

    val logger: Logger = Logger.getLogger("ProduitsControlleur")

    @GetMapping("/produits")
    fun obtenirTous(): List<Produit> {
        return produitsReposistory.findAll()
    }

    @GetMapping("/produits/{id}")
    fun obtenirUnique(@PathVariable("id") id: Long): Produit {
        try {
            return produitsReposistory.getById(id);
        } catch (e: Exception) {
            logger.log(Level.SEVERE, "Exception du repository ${e.message}")
            throw ResponseStatusException(HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping("/produits")
    fun creerProduit(@RequestBody produit: Produit): Produit {
        return produitsReposistory.save(produit)
    }

    @DeleteMapping("/produits/{id}")
    fun supprimerProduits(@PathVariable("id") id: Long) {
        try {
            return produitsReposistory.deleteById(id)
        } catch (e: Exception) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND)
        }
    }

    @PutMapping("/produits/{id}/prix/{prix}")
    fun modifierPrix(@PathVariable("id") id: Long, @PathVariable("prix") prix: Double): Produit {
        if (prix < 0) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST)
        }

        try {
            val produit = produitsReposistory.getById(id)
            produit.prix = prix
            return produitsReposistory.save(produit)
        } catch (e: Exception) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND)
        }
    }

    @PutMapping("/produits/{id}/inventaire/{qte}")
    fun modifierInventaire(@PathVariable("id") id: Long, @PathVariable("qte") qte: Int): Produit {
        if (qte < 0) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST)
        }

        try {
            val produit = produitsReposistory.getById(id)
            produit.inventaire = qte
            return produitsReposistory.save(produit)
        } catch (e: Exception) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND)
        }
    }

}
