function ContextMenu() {
    this._test_ = "Hallo Welt!";
}

ContextMenu.prototype.getTest = function() {
    return this._test_;
}

module.exports = ContextMenu;