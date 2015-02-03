Ext.define('dy.store.PushMessage', {
	extend : 'Ext.data.Store',
	model : 'dy.model.PushMessage',
	remoteSort : true,
	remoteFilter : true,
	autoLoad : false
});