Ext.define('dy.model.ChangePassword', {
    extend: 'Ext.data.Model',
    idProperty: 'adminId',
    fields: ['adminName', 'adminPass', 'role', 'status', 'createTime'],
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