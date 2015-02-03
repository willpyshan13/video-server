Ext.define('dy.controller.team.Team', {
    extend: 'Ext.app.Controller',
    views:["team.Team","team.TeamEditWindow"],
    models:["Team"],
    stores:["Team"],
    init: function () {
        this.control({
        	
        });
    }
});