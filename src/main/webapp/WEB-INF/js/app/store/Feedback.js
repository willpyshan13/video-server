Ext.define('dy.store.Feedback', {
	extend : 'Ext.data.Store',
	model : 'dy.model.Feedback',
	remoteSort : true,
	remoteFilter : true,
	pageSize : 20,
	autoLoad : false
});