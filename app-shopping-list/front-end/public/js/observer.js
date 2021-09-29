var centraleMessaggi = function () {
  this.listaObserver = [];
};
centraleMessaggi.prototype = {
  subscribe: function (callback) {
    this.listaObserver.push(callback);
  },
  unsubscribe: function (callback) {
    for (var i = 0; i < this.listaObserver.length; i++) {
      if (this.listaObserver[i] === callback) {
        this.listaObserver.splice(i, 1);
        return;
      }
    }
  },
  nuovoMessaggio: function (msg) {
    for (var i = 0; i < this.listaObserver.length; i++) {
      this.listaObserver[i](msg);
    }
  },
};

var cm = new centraleMessaggi();
cm.subscribe(function (msg) {
  console.log(msg);
});
cm.nuovoMessaggio("nuovo messaggio");

cm.nuovoMessaggio("nuovo messaggio 1");
