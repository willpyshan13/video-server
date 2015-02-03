Ext.define('dy.controller.person.PersonInfo', {
    extend: 'Ext.app.Controller',
    views:["person.PersonInfo","person.PersonEditWindow"],
    stores:["Person"],
    models:["Person"],
    init: function () {
        this.control({
        	
        });
    }
});