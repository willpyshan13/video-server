Ext.define('dy.store.Video', {
	extend : 'Ext.data.Store',
	model : 'dy.model.Video',
	remoteSort : true,
	remoteFilter : true,
	pageSize : 20,
	autoLoad : false
});