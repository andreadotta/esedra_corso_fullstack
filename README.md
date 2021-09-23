# esedra_corso_fullstack

## Refactoring App ShoppingList

### Gestione uniqueCode e Id

Lo uniqueCode ha la funzione di mascherare l'id (chiave univoca che identifica User, ShoppingList e Product) per evitare di esporlo nel passaggio di dati tra il Fornt End e il Back End dell'applicazione.

In quest'ottica, viene generato una volta sola come anche l'id e in questa fase deve essere associato, in modo che il flusso logico all'interno del Back End sia poi gestito dall'Id. Può quindi essere scorporato dalle funzioni Dao
