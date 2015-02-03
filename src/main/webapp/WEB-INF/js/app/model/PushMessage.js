Ext.define('dy.model.PushMessage', {
    extend: 'Ext.data.Model',
    idProperty: 'pushMessageId',
    fields: ['pushMessageId', 'messageTitle','messageContent','pushTime'],
    proxy: {
        type: 'ajax',
        api: {
            create: 'ext/add/push',
            read: 'ext/load/push',
            update: 'ext/update/push',
            destroy: 'ext/remove/push',
            push:'ext/push'
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