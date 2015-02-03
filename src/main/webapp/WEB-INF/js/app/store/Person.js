Ext.define('dy.store.Person', {
	extend : 'Ext.data.Store',
	model : 'dy.model.Person',
	remoteSort : true,
	remoteFilter : true,
	pageSize : 20,
	autoLoad : false
});