# Installation du Driver MySQL

## ✅ Solution : Reconstruire le projet avec Maven

Vous **n'avez PAS besoin** de télécharger manuellement le driver MySQL. Maven le fera automatiquement !

## Étapes à suivre :

### 1. Ouvrir un terminal dans le dossier `backend`

```bash
cd backend
```

### 2. Nettoyer et reconstruire le projet

```bash
mvn clean install
```

Cette commande va :
- Nettoyer les anciens fichiers compilés
- Télécharger automatiquement le driver MySQL depuis Maven Central
- Compiler le projet avec la nouvelle dépendance

### 3. Vérifier que le driver est téléchargé

Le driver sera automatiquement téléchargé dans :
```
backend/target/classes/
```

Et dans le cache Maven local :
```
~/.m2/repository/com/mysql/mysql-connector-j/
```

### 4. Redémarrer l'application

```bash
mvn spring-boot:run
```

## Alternative : Si vous utilisez un IDE

### IntelliJ IDEA / Eclipse
1. Clic droit sur le projet
2. **Maven** → **Reload Project** (ou **Update Project**)
3. Attendez que Maven télécharge les dépendances
4. Redémarrez l'application

### VS Code
1. Ouvrez la palette de commandes (Ctrl+Shift+P)
2. Tapez "Java: Clean Java Language Server Workspace"
3. Redémarrez VS Code ou rechargez la fenêtre

## Vérification

Pour vérifier que le driver est bien téléchargé, vous pouvez exécuter :

```bash
mvn dependency:tree | grep mysql
```

Vous devriez voir :
```
[INFO] +- com.mysql:mysql-connector-j:jar:8.x.x:runtime
```

## Si ça ne fonctionne toujours pas

1. Vérifiez votre connexion Internet
2. Vérifiez que Maven est bien installé : `mvn --version`
3. Essayez de forcer le téléchargement : `mvn dependency:resolve -U`
4. Vérifiez les paramètres proxy si vous êtes derrière un firewall
