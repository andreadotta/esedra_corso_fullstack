# esedra_corso_fullstack
Corso full stack dev

## Esedra corso full stack

Per compilare una classe spostarsi nella cartella src

```bash
javac -d ../bin it/esedra/corso/lezioni/Operatori.java
```

X Windows invece

```bash
javac -d ..\bin it\esedra\corso\lezioni\Operatori.java
```


Per eseguire spostarsi nella cartella bin e quindi

```bash
java it.esedra.corso.lezioni.Operatori
```


### Installazione di Github
Scaricare ed installare la versione di github per il proprio sistema operativo.

Per Windows 10 scarichiamo

https://git-scm.com/downloads

Installare la versione di default e modificare soltanto "Choosing the default editor used by Git" selezionando Notepad

### Installazione di Eclipse

https://www.eclipse.org/downloads/packages/

Scarichiamo ed installiamo la versione "Eclipse IDE for Enterprise Java and Web Developers" scompattando il contenuto in una cartella.
Lanciare quindi il file eseguibile eclipse dentro la cartella.

Una volta avviato Eclipse chiede un percorso per generare la cartella del workspace: [WORKSPACE_FOLDER]

Cliccando

### Configurazione del progetto

Tramite promp dei comandi andare su [WORKSPACE_FOLDER] e quindi digitare

```bash
git clone https://github.com/andreadotta/esedra_corso_fullstack.git
```
Creerà una cartella esedra_corso_fullstack dentro [WORKSPACE_FOLDER]

Su Eclipse si va su 
File->New->Java Project 
e quindi come "Project name" si scrive esedra_corso_fullstack.
Cliccare su "Finish", confermare tutti i passaggi e quindi cliccare su "Open Java perspective"

#### Compilazione e build
Cliccando con il pulsante destro sulla cartella del progetto si seleziona Proprietà e quindi nella finestra che si apre si imposta:

- Java Build Path
       Su libraries verificare che: tra i modulepath sia presente la JRE System Library
       Nel class path deve essere presente il file javax.json.1.1.4.jr che si trova nella cartella lib (eventualmente si carica cliccando su add library a destra)
- Project Natures
        Deve essere presente Java, altrimenti aggiungere premenedo su Add a destra

### NODE e NPM
Nella cartella front-end eseguire il comando
```bash
npm install
```
[NPM JS](https://www.npmjs.com/) home page di npm js

poi per lanciare il server
```bash
nom run dev 
```
per raggiungere il server 
http://localhost:3000

### MAVEN
#### Installazione Maven:
Scaricare la versione più recente del binary di Maven: [DOVNLOAD](https://maven.apache.org/download.cgi) 

selezionare la versione compessa .zip o .tar.gz

Scompattarla in un percorso predefinito nel proprio sistema operativo.

Aggiungere la subdirectory /bin della cartella appena scaricata alla propria variabile di sistema $PATH.

Per info sull'installazione consultare la guida: [INSTALL](https://maven.apache.org/install.html)

Anche la variabile di ambiente $JAVA_HOME deve essere configurata correttamente perchè Maven funzioni.

Verificare che l'installazione sia andata a buon fine:
```bash
mvn -v
```
Deve restituire le informazioni su Maven e relative informazioni

#### Test sulla configurazione del progetto dopo la conversione in Progetto Maven

Convertire il progetto Java in Eclipse in progetto Maven: con il tasto destro sulla cartella del progetto, selezionare 
- Configure -> Convert to Maven Project
- Nella finestra di dialogo, lasciare invariati i campi precompilati e avviare la configurazione.

La procedura crea automaticamente un file pom.xml con le istruzioni di base per il progetto. Queste andranno poi integrate con i plugins e le dependencies del progetto.

Da notare nel file pom.xml l'indicazione del parent project a "spring-boot-starter-parent" e le dependencies "spring-boot-starter" e "spring-boot-starter.test":

Saranno queste impostazioni che scaricheranno le librerie che integrano SpringBoot nel progetto.

Per verificare il funzionamento, con il bottone destro dalla root del progetto selezionare:
- Run As: Maven-clean
- Run As: Maven-install

Con il primo comando viene rimosso il contenuto della cartella target e con la seconda si dovrebbe creare nella cartella target il file .jar del progetto, se non ci sono errori che ne impediscono il build.

## Refactoring App ShoppingList

### Gestione uniqueCode e Id

Lo uniqueCode ha la funzione di mascherare l'id (chiave univoca che identifica User, ShoppingList e Product) per evitare di esporlo nel passaggio di dati tra il Fornt End e il Back End dell'applicazione.

In quest'ottica, viene generato una volta sola come anche l'id e in questa fase deve essere associato, in modo che il flusso logico all'interno del Back End sia poi gestito dall'Id. Può quindi essere scorporato dalle funzioni Dao

Il metodo generateUniqueKey viene quindi spostato nella classe AESHelper come metodo statico.

WIP - Era stata modificata la classe SequenceManager e relativi references rimuovendo l'incremento di un nuovo id: il metodo newUserId richiama SequenceManager.getCurrentUserId() che non opera nessun incremento.

### Revisione del flusso logico dell'applicazione

È stata implementata la struttura di Product aggiungendo ProductBuilder e ProductDao, allineando la logica a quella delle altre entities.

Per creare una struttura dati che abbia le correlazioni necessarie, è stato creato un file "list.csv" e un file "shoppingList.csv" che rappresentano le rispettive basi dati. Deve essere configurata la correlazione come in una struttura tabellare con un campo chiave primaria e un campo chiave secondaria per collegare in una relazione uno-a-molti un'entità "User" con le sue entità "ShoppingLists" e una entità "ShoppingList" con le sue entità "Products" (lista di prodotti).

La struttura così creata anche per Product consente di gestire la lista di prodotti con le sue operazioni di "get, save, getAll, find e delete" relative alla lista di prodotti contenuta in una shopping list.

La correlazione non è stata ancora completamente implementata (WIP).

### Pulizia del codice delle classi User e ShoppingList

Il codice è stato reso omogeneo tra le due serie di classi, per quello che riguarda i metodi che son stati completati.

WIP - Rivedere la struttura del fieldsMap in modo da accedere alla collection chiave/valore in modo più compatto.
WIP - Creare un metodo che metta in relazione le stringhe del file csv con la struttura chiave/valore necessaria per la creazione di una nuova entità.
      Per il momento c'è uno stream che separa le stringhe di una riga di testo del file e un'altro stream che costruisce una shoppingList creandola e creando la       lista di prodotti contestualmente. La logica corretta sarebbe quella di passare una lista di prodotti dopo aver creato la lista, attraverso il file               lista.csv (che deve essere creato nel save di ProductDao)

WIP - Utilizzare un sistema univoco per il save di ShoppingList e User: ora sono impiegate le classi PrinterWriter e BufferedWriter.
      BufferedWriter gestisce uno stream buffered mentre PrnterWriter no. L'utilità di avere uno stream buffered è nella gestione degli accessi al file della           base dati  (Il capitolo su input/output stream non è ancora stato affrontato)
 
### Gestione dell'errore

Abbiamo anche provato a ragionare sul controllo del flusso di gestione delle eccezioni, queste le considerazioni:

Le eccezioni possono essere gestite anche senza essere rilanciate, per esempio utilizzando un logger che salvi uno Stack Trace per poterlo valutare in fase di sviluppo dell'applicazione. 
L'analisi del flusso logico dell'applicazione è necessario per valutare dove l'eccezione debba essere rilanciata, dove debba essere intercettata da un try/catch e dove sia sufficiente gestirla con un blocco try/catch senza rilanciarla.
Rilanciare un'eccezione consente di evitare il surrounding con il try/catch, a meno che si debba ricevere un errore di tipo diverso da quello da rilanciare. Tuttavia, per utilizzare un metodo all'interno di uno stream, per esempio con un forEach(), le eccezioni non possono essere rilanciate. Per questo si può gestire l'errore con un logger all'interno del metodo senza rilanciare l'eccezione e utilizzare il metodo in uno stream. In alternativa bisogna includere nello stream un blocco try/catch, rendendo il codice meno sintetico, oltre a non rispettare il corretto utilizzo dello stream che non prevede la gestione dell'errore.

