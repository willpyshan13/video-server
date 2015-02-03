Ext.define('dy.model.Loading', {
    extend: 'Ext.data.Model',
    idProperty: 'loadingId',
    fields: ['loadingId', 'loadingImgUrl', 'createTime'],
    proxy: {
        type: 'ajax',
        api: {
            create: 'ext/add/loading',
            read: 'ext/loadone/loading',
            update: 'ext/update/loading',
            destroy: 'ext/remove/loading'
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