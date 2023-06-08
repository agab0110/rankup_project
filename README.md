# ISTRUZIONI

 - Nel file "application.yml" sostituire localhost con il nome dell'immagine di mariadb
 - Buildare dockerfile: docker build --pull --rm -f "dockerfile" -t backend "."
 - Fare il compose del docker-compose: docker compose -f "docker-compose.yml" up -d --build 
 - Andare nel container del backend da docker e aspettare che il backend è avviato
 - Dal firewall esporre la porta 8080 in ingresso ed in uscita
