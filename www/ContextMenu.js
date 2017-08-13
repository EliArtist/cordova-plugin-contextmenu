var ContextMenu = function(){};

ContextMenu.prototype.callNumber = function(success, failure, number, bypassAppChooser){
    cordova.exec(success, failure, "ContextMenu", "callNumber", [number, bypassAppChooser]);
};

ContextMenu.prototype.isCallSupported = function(success, failure){
    cordova.exec(success, failure, "ContextMenu", "isCallSupported");
}

//Plug in to Cordova
cordova.addConstructor(function() {

    if (!window.Cordova) {
        window.Cordova = cordova;
    };

    if(!window.plugins) window.plugins = {};
    window.plugins.ContextMenu = new ContextMenu();
});