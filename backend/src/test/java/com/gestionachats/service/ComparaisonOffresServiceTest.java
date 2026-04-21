package com.gestionachats.service;

import com.gestionachats.entity.Fournisseur;
import com.gestionachats.entity.CommandeAchat;
import com.gestionachats.entity.LigneCommandeAchat;
import com.gestionachats.entity.HistoriqueAchats;
import com.gestionachats.repository.LigneCommandeAchatRepository;
import com.gestionachats.repository.HistoriqueAchatsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ComparaisonOffresServiceTest {

    @Mock
    private LigneCommandeAchatRepository ligneCommandeAchatRepository;

    @Mock
    private HistoriqueAchatsRepository historiqueAchatsRepository;

    @InjectMocks
    private ComparaisonOffresService comparaisonOffresService;

    private Fournisseur fournisseur1, fournisseur2;
    private CommandeAchat commande1, commande2;
    private LigneCommandeAchat ligne1, ligne2, ligne3;
    private HistoriqueAchats historique1, historique2;

    @BeforeEach
    void setUp() {
        // Création des fournisseurs de test
        fournisseur1 = new Fournisseur("TechCorp", "contact@techcorp.com", 4, 8.5);
        fournisseur1.setId(1L);

        fournisseur2 = new Fournisseur("GlobalSupply", "info@globalsupply.com", 5, 9.2);
        fournisseur2.setId(2L);

        // Création des commandes de test
        commande1 = new CommandeAchat(fournisseur1, LocalDate.now(), "Confirmée", 10000.0);
        commande1.setId(1L);

        commande2 = new CommandeAchat(fournisseur2, LocalDate.now(), "Livrée", 15000.0);
        commande2.setId(2L);

        // Création des lignes de commande de test
        ligne1 = new LigneCommandeAchat();
        ligne1.setId(1L);
        ligne1.setCommande(commande1);
        ligne1.setProduit("Ordinateur portable");
        ligne1.setQuantite(10);
        ligne1.setPrixUnitaire(1200.0);

        ligne2 = new LigneCommandeAchat();
        ligne2.setId(2L);
        ligne2.setCommande(commande2);
        ligne2.setProduit("Ordinateur portable");
        ligne2.setQuantite(8);
        ligne2.setPrixUnitaire(1150.0);

        ligne3 = new LigneCommandeAchat();
        ligne3.setId(3L);
        ligne3.setCommande(commande1);
        ligne3.setProduit("Clavier mécanique");
        ligne3.setQuantite(20);
        ligne3.setPrixUnitaire(150.0);

        // Création de l'historique des achats de test
        historique1 = new HistoriqueAchats(fournisseur1, "Ordinateur portable", 50, 5, LocalDate.now().minusDays(10));
        historique1.setId(1L);

        historique2 = new HistoriqueAchats(fournisseur2, "Ordinateur portable", 40, 3, LocalDate.now().minusDays(8));
        historique2.setId(2L);
    }

    @Test
    void testComparerPrixProduit() {
        // Given
        String produit = "Ordinateur portable";
        List<LigneCommandeAchat> expectedOffres = Arrays.asList(ligne2, ligne1); // Trié par prix croissant

        when(ligneCommandeAchatRepository.findBestPricesForProduct(produit)).thenReturn(expectedOffres);

        // When
        List<LigneCommandeAchat> result = comparaisonOffresService.comparerPrixProduit(produit);

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Ordinateur portable", result.get(0).getProduit());
        assertEquals(1150.0, result.get(0).getPrixUnitaire());
    }

    @Test
    void testComparerDelaisLivraisonProduit() {
        // Given
        String produit = "Ordinateur portable";
        List<HistoriqueAchats> expectedDelais = Arrays.asList(historique2, historique1); // Trié par délai croissant

        when(historiqueAchatsRepository.findFastestDeliveryForProduct(produit)).thenReturn(expectedDelais);

        // When
        List<HistoriqueAchats> result = comparaisonOffresService.comparerDelaisLivraisonProduit(produit);

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Ordinateur portable", result.get(0).getProduit());
        assertEquals(3, result.get(0).getDelaiLivraison());
    }

    @Test
    void testComparaisonCompleteProduit() {
        // Given
        String produit = "Ordinateur portable";
        List<LigneCommandeAchat> offresPrix = Arrays.asList(ligne2, ligne1);
        List<HistoriqueAchats> delaisLivraison = Arrays.asList(historique2, historique1);

        when(ligneCommandeAchatRepository.findBestPricesForProduct(produit)).thenReturn(offresPrix);
        when(historiqueAchatsRepository.findFastestDeliveryForProduct(produit)).thenReturn(delaisLivraison);

        // When
        Map<String, Object> result = comparaisonOffresService.comparaisonCompleteProduit(produit);

        // Then
        assertNotNull(result);
        assertEquals(produit, result.get("produit"));
        assertNotNull(result.get("offresPrix"));
        assertNotNull(result.get("delaisLivraison"));
        assertNotNull(result.get("meilleureOffrePrix"));
        assertNotNull(result.get("meilleureLivraison"));
    }

    @Test
    void testComparerFournisseursPourProduit() {
        // Given
        String produit = "Ordinateur portable";
        List<LigneCommandeAchat> offres = Arrays.asList(ligne1, ligne2);

        when(ligneCommandeAchatRepository.findByProduitContainingIgnoreCase(produit)).thenReturn(offres);

        // When
        Map<String, Object> result = comparaisonOffresService.comparerFournisseursPourProduit(produit);

        // Then
        assertNotNull(result);
        assertEquals(produit, result.get("produit"));
        assertNotNull(result.get("offresParFournisseur"));
        assertTrue(result.containsKey("nombreFournisseurs"));
    }

    @Test
    void testGetProduitsDisponibles() {
        // Given
        List<String> expectedProduits = Arrays.asList("Ordinateur portable", "Clavier mécanique", "Souris gaming");

        when(ligneCommandeAchatRepository.findAllProducts()).thenReturn(expectedProduits);

        // When
        List<String> result = comparaisonOffresService.getProduitsDisponibles();

        // Then
        assertNotNull(result);
        assertEquals(3, result.size());
        assertTrue(result.contains("Ordinateur portable"));
    }
}