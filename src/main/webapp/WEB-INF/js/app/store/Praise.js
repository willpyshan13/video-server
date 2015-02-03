Ext.define('dy.store.Praise', {
	extend : 'Ext.data.Store',
	model : 'dy.model.Praise',
	remoteSort : true,
	remoteFilter : true,
	pageSize : 20,
	autoLoad : false
});