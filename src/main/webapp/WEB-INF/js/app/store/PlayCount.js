Ext.define('dy.store.PlayCount', {
	extend : 'Ext.data.Store',
	model : 'dy.model.PlayCount',
	remoteSort : true,
	remoteFilter : true,
	pageSize : 20,
	autoLoad : false
});