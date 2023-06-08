# ISTRUZIONI

1. Buildare dockerfile: docker build --pull --rm -f "dockerfile" -t backend "."
2. Fare il compose del docker-compose: docker compose -f "docker-compose.yml" up -d --build
3. Andare nel container "backend" da docker e nei log aspettare e accertarsi del corretto avvio
4. Importare in phpMyAdmin il file "RankUpDB.sql" presente nel progetto per popolare il database
5. Dal firewall esporre la porta 8080 in ingresso ed in uscita
