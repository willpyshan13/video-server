Ext.define('dy.store.SystemConfig', {
	extend : 'Ext.data.Store',
	model : 'dy.model.SystemConfig',
	remoteSort : true,
	remoteFilter : true,
	autoLoad : false
});