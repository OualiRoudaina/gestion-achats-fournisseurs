# Configuration MySQL

## Prérequis

1. **Installer MySQL** sur votre machine :
   - Windows : Télécharger depuis https://dev.mysql.com/downloads/installer/
   - Linux : `sudo apt-get install mysql-server` (Ubuntu/Debian) ou `sudo yum install mysql-server` (CentOS/RHEL)
   - macOS : `brew install mysql`

2. **Démarrer le service MySQL** :
   - Windows : Service MySQL dans les Services Windows
   - Linux : `sudo systemctl start mysql`
   - macOS : `brew services start mysql`

## Configuration

### 1. Créer la base de données (optionnel)

La base de données sera créée automatiquement grâce à `createDatabaseIfNotExist=true` dans l'URL de connexion.

Si vous voulez la créer manuellement :

```sql
CREATE DATABASE gestion_achats CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

### 2. Configurer les identifiants

Modifiez `application.properties` si nécessaire :

```properties
spring.datasource.username=root
spring.datasource.password=votre_mot_de_passe
```

### 3. Port MySQL par défaut

Le port par défaut est **3306**. Si votre MySQL utilise un autre port, modifiez l'URL :

```properties
spring.datasource.url=jdbc:mysql://localhost:VOTRE_PORT/gestion_achats?...
```

## Démarrage

1. **Vérifier que MySQL est démarré** :
   ```bash
   mysql --version
   ```

2. **Démarrer l'application Spring Boot** :
   ```bash
   cd backend
   mvn spring-boot:run
   ```

L'application va :
- Créer automatiquement la base de données `gestion_achats` si elle n'existe pas
- Créer automatiquement les tables via Hibernate (`ddl-auto=update`)
- Insérer les données initiales depuis `import.sql`

## Vérification

Vous pouvez vérifier la connexion et les données via :

1. **Ligne de commande MySQL** :
   ```bash
   mysql -u root -p
   USE gestion_achats;
   SHOW TABLES;
   SELECT * FROM fournisseurs;
   ```

2. **Outils graphiques** :
   - MySQL Workbench
   - phpMyAdmin
   - DBeaver
   - DataGrip

## Dépannage

### Erreur : "Access denied for user 'root'@'localhost'"

- Vérifiez le mot de passe dans `application.properties`
- Réinitialisez le mot de passe MySQL si nécessaire

### Erreur : "Unknown database 'gestion_achats'"

- Vérifiez que `createDatabaseIfNotExist=true` est dans l'URL
- Créez manuellement la base de données (voir ci-dessus)

### Erreur : "Communications link failure"

- Vérifiez que MySQL est démarré
- Vérifiez le port (3306 par défaut)
- Vérifiez les paramètres de firewall

### Erreur de connexion SSL

L'URL contient déjà `useSSL=false`, mais si vous avez encore des problèmes :
- Vérifiez que `allowPublicKeyRetrieval=true` est présent
- Vérifiez la configuration SSL de MySQL
