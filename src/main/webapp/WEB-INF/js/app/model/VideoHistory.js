Ext.define('dy.model.VideoHistory', {
    extend: 'Ext.data.Model',
    idProperty: 'historyId',
    fields: ['historyId', 'personId','userName', 'videoId','videoTitle', 'createTime'],
    proxy: {
        type: 'ajax',
        api: {
            create: 'ext/add/videoHistory',
            read: 'ext/load/videoHistory',
            update: 'ext/update/videoHistory',
            destroy: 'ext/remove/videoHistory'
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