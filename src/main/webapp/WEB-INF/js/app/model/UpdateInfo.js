Ext.define('dy.model.UpdateInfo', {
    extend: 'Ext.data.Model',
    idProperty: 'updateId',
    fields: ['updateId', 'androidVersion', 'iosVersion', 'androidUpdateUrl', 'iosUpdateUrl', 'androidVersionDesc','iosVersionDesc','count'],
    proxy: {
        type: 'ajax',
        api: {
            create: 'ext/add/update',
            read: 'ext/loadone/update',
            update: 'ext/update/update',
            destroy: 'ext/remove/update'
        },
        headers: {
            'Content-Type': 'application/json; charset=UTF-8'
        },
        reader: {
            type: 'json',
            root: 'data'
        },
        writer: {
            encode: false,
            writeAllFields: true
        }
    }
});