# Usare il dockercompose per creare il container del db

Nella cartella configurations c'è un file .txt che, se messo come .java, serve per la configurazione della sicurezza sul web.
Non modificare quel file e non togliere ne eliminare le dipendenze commentate al pom.

# Per manifest

aggiungere android:usesCleartextTraffic="true" nel tag application
aggiungere "<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />"