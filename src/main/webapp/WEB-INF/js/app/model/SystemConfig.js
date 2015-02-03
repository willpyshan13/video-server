Ext.define('dy.model.SystemConfig', {
    extend: 'Ext.data.Model',
    idProperty: 'configId',
    fields: ['systemEmail', 'systemUsername', 'systemPassword', 'emailSubject', 'serverSmtp', 'serverPort'],
    proxy: {
        type: 'ajax',
        api: {
            create: 'ext/add/config',
            read: 'ext/loadone/config',
            update: 'ext/update/config',
            destroy: 'ext/remove/config'
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