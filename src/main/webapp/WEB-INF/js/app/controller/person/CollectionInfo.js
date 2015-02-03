Ext.define('dy.controller.person.CollectionInfo', {
    extend: 'Ext.app.Controller',
    views:["person.CollectionInfo","person.CollectionEditWindow"],
    models:["Collection"],
    stores:["Collection"],
    init: function () {
        this.control({
        	
        });
    }
});