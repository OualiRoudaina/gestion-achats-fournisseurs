-- ===============================
-- Fournisseurs
-- ===============================
INSERT IGNORE INTO fournisseurs (id, nom, contact, qualite_service, note) VALUES
                                                                   (1, 'TechCorp SARL', 'contact@techcorp.ma', 4, 8.5),
                                                                   (2, 'GlobalSupply Ltd', 'info@globalsupply.com', 5, 9.2),
                                                                   (3, 'MediTech Solutions', 'sales@meditech.fr', 3, 7.8),
                                                                   (4, 'FastDelivery Co', 'orders@fastdelivery.es', 4, 8.1),
                                                                   (5, 'QualityParts GmbH', 'info@qualityparts.de', 5, 9.5);

-- ===============================
-- Commandes Achat
-- ===============================
INSERT IGNORE INTO commandes_achat (id, fournisseur_id, date_commande, statut, montant) VALUES
                                                                                 (1, 1, '2024-01-15', 'Livree', 15000.00),
                                                                                 (2, 2, '2024-01-20', 'Confirmee', 25000.00),
                                                                                 (3, 3, '2024-01-25', 'En attente', 12000.00),
                                                                                 (4, 1, '2024-02-01', 'Livree', 18000.00),
                                                                                 (5, 4, '2024-02-05', 'Confirmee', 22000.00),
                                                                                 (6, 2, '2024-02-10', 'En attente', 16000.00),
                                                                                 (7, 5, '2024-02-15', 'Livree', 30000.00),
                                                                                 (8, 3, '2024-02-20', 'Annulee', 0.00),
                                                                                 (9, 4, '2024-02-25', 'Confirmee', 19000.00),
                                                                                 (10, 1, '2024-03-01', 'En attente', 21000.00);

-- ===============================
-- Lignes commande achat
-- ===============================
INSERT IGNORE INTO lignes_commande_achat (commande_id, produit, quantite, prix_unitaire) VALUES

                                                                                      (1, 'Ordinateur portable', 10, 1200.00),
                                                                                      (1, 'Clavier mecanique', 25, 150.00),
                                                                                      (1, 'Souris gaming', 30, 80.00),

                                                                                      (2, 'Serveur Dell PowerEdge', 5, 3500.00),
                                                                                      (2, 'Switch reseau 48 ports', 8, 800.00),
                                                                                      (2, 'Routeur Cisco', 3, 1200.00),

                                                                                      (3, 'Equipement medical', 2, 4500.00),
                                                                                      (3, 'Consommables medicaux', 100, 75.00),

                                                                                      (4, 'Ordinateur portable', 15, 1150.00),
                                                                                      (4, "Ecran 27 pouces", 20, 350.00),
                                                                                      (4, 'Clavier mecanique', 40, 140.00),

                                                                                      (5, 'Imprimante laser', 12, 600.00),
                                                                                      (5, "Cartouches d'encre", 50, 45.00),
                                                                                      (5, 'Papier A4', 1000, 0.25),

                                                                                      (6, 'Serveur Dell PowerEdge', 3, 3400.00),
                                                                                      (6, 'Disque dur SSD 1TB', 15, 120.00),

                                                                                      (7, 'Composants electroniques', 200, 125.00),
                                                                                      (7, 'Capteurs IoT', 50, 85.00),
                                                                                      (7, 'Modules Bluetooth', 30, 45.00),

                                                                                      (9, 'Imprimante laser', 8, 580.00),
                                                                                      (9, 'Scanner haute resolution', 5, 450.00),

                                                                                      (10, 'Ordinateur portable', 12, 1180.00),
                                                                                      (10, 'Webcam HD', 25, 65.00);

-- ===============================
-- Historique achats
-- ===============================
INSERT IGNORE INTO historique_achats (fournisseur_id, produit, quantite, delai_livraison, date_achat) VALUES

                                                                                                   (1, 'Ordinateur portable', 50, 5, '2024-01-10'),
                                                                                                   (1, 'Clavier mecanique', 100, 3, '2024-01-08'),
                                                                                                   (1, 'Souris gaming', 150, 4, '2024-01-12'),
                                                                                                   (1, 'Ecran 27 pouces', 80, 6, '2024-02-05'),
                                                                                                   (1, 'Ordinateur portable', 60, 4, '2024-02-08'),

                                                                                                   (2, 'Serveur Dell PowerEdge', 20, 7, '2024-01-18'),
                                                                                                   (2, 'Switch reseau 48 ports', 35, 5, '2024-01-22'),
                                                                                                   (2, 'Routeur Cisco', 12, 8, '2024-01-25'),
                                                                                                   (2, 'Serveur Dell PowerEdge', 15, 6, '2024-02-12'),
                                                                                                   (2, 'Disque dur SSD 1TB', 60, 3, '2024-02-14'),

                                                                                                   (3, 'Equipement medical', 8, 10, '2024-01-28'),
                                                                                                   (3, 'Consommables medicaux', 400, 5, '2024-02-02'),

                                                                                                   (4, 'Imprimante laser', 40, 4, '2024-02-08'),
                                                                                                   (4, "Cartouches d'encre", 200, 2, '2024-02-06'),
                                                                                                   (4, 'Papier A4', 5000, 1, '2024-02-07'),
                                                                                                   (4, 'Imprimante laser', 25, 3, '2024-03-01'),

                                                                                                   (5, 'Composants electroniques', 800, 8, '2024-02-20'),
                                                                                                   (5, 'Capteurs IoT', 150, 6, '2024-02-18'),
                                                                                                   (5, 'Modules Bluetooth', 100, 4, '2024-02-22');