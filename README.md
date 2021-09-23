# esedra_corso_fullstack
Corso full stack dev

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
 
