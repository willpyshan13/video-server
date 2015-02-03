Ext.define('dy.store.Team', {
	extend : 'Ext.data.Store',
	model : 'dy.model.Team',
	remoteSort : true,
	remoteFilter : true,
	pageSize : 20,
	autoLoad : false
});