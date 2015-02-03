Ext.define('dy.store.Comment', {
	extend : 'Ext.data.Store',
	model : 'dy.model.Comment',
	remoteSort : true,
	remoteFilter : true,
	pageSize : 20,
	autoLoad : false
});