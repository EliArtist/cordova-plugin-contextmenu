var ContextMenuPlugin = function(){};

ContextMenuPlugin.prototype.callNumber = function(success, failure, number, bypassAppChooser){
    cordova.exec(success, failure, "ContextMenuPlugin", "callNumber", [number, bypassAppChooser]);
};

ContextMenuPlugin.prototype.isCallSupported = function(success, failure){
    cordova.exec(success, failure, "ContextMenuPlugin", "isCallSupported");
}

//Plug in to Cordova
cordova.addConstructor(function() {

    if (!window.Cordova) {
        window.Cordova = cordova;
    };

    if(!window.plugins) window.plugins = {};
    window.plugins.ContextMenuPlugin = new ContextMenuPlugin();
});