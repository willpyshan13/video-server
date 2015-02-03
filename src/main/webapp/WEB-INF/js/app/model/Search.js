Ext.define('dy.model.Search', {
    extend: 'Ext.data.Model',
    idProperty: 'searchId',
    fields: ['searchId', 'searchKey', 'personId','searchCount', 'createTime'],
    proxy: {
        type: 'ajax',
        api: {
            create: 'ext/add/search',
            read: 'ext/load/search',
            update: 'ext/update/search',
            destroy: 'ext/remove/search'
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