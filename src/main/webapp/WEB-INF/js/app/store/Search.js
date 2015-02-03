Ext.define('dy.store.Search', {
	extend : 'Ext.data.Store',
	model : 'dy.model.Search',
	remoteSort : true,
	remoteFilter : true,
	pageSize : 20,
	autoLoad : false
});