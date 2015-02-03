Ext.define('dy.store.VideoTypeRecord', {
	extend : 'Ext.data.Store',
	model : 'dy.model.VideoTypeRecord',
	remoteSort : true,
	remoteFilter : true,
	pageSize : 20,
	autoLoad : false
});