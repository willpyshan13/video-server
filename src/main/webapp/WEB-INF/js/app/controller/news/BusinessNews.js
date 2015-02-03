Ext.define('dy.controller.news.BusinessNews', {
    extend: 'Ext.app.Controller',
    views:["news.BusinessNews","news.BusinessNewsEditWindow"],
    models:["BusinessNews"],
    stores:["BusinessNews"],
    init: function () {
        this.control({
        	
        });
    }
});