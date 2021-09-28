function getRandom(min, max) {
    return Math.random() * (max - min) + min;
}

function dividi(numeratore, denominatore) {
    if (denominatore == 0)
        throw Error("Non si può dividere per 0")
    return numeratore / denominatore
}


let casuale = getRandom(3, 6)
let numero = prompt("Inserisci un numero")

try {
    console.log(dividi(numero, casuale))
} catch (error) {
    console.log(error)
}
