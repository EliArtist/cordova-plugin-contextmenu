var exec = require('cordova/exec');

var ContextMenu = {
    __test: "Hallo Welt",

    getTest: function() {
        return ContextMenu.__test;
    },

    /**
    * Hide context menu of Android phones
    *
    * @param (Function) resultCallback  function to call when context menu is hidden
    * @param (Function) errorCallback   function to call when there are any errors
    */
    hideContextMenu: function(resultCallback, errorCallback) {
        cordova.exec(resultCallback, errorCallback, 'Contextmenu', 'hideContextMenu');
    }
}

module.exports = ContextMenu;