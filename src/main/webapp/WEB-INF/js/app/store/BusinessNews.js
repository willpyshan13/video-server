Ext.define('dy.store.BusinessNews', {
	extend : 'Ext.data.Store',
	model : 'dy.model.BusinessNews',
	remoteSort : true,
	remoteFilter : true,
	pageSize : 20,
	autoLoad : false
});