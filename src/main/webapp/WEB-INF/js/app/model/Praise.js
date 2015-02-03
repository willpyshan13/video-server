Ext.define('dy.model.Praise', {
    extend: 'Ext.data.Model',
    idProperty: 'praiseId',
    fields: ['praiseId', 'personId','userName', 'videoId','videoTitle', 'createTime'],
    proxy: {
        type: 'ajax',
        api: {
            create: 'ext/add/praise',
            read: 'ext/load/praise',
            update: 'ext/update/praise',
            destroy: 'ext/remove/praise'
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