Ext.define('dy.store.Collection', {
	extend : 'Ext.data.Store',
	model : 'dy.model.Collection',
	remoteSort : true,
	remoteFilter : true,
	pageSize : 20,
	autoLoad : false
});