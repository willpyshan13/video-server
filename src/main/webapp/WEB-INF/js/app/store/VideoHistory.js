Ext.define('dy.store.VideoHistory', {
	extend : 'Ext.data.Store',
	model : 'dy.model.VideoHistory',
	remoteSort : true,
	remoteFilter : true,
	pageSize : 20,
	autoLoad : false
});