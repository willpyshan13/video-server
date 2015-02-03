Ext.define('dy.model.VideoUrl', {
    extend: 'Ext.data.Model',
    idProperty: 'videoUrlId',
    fields: [ 'videoUrlId', 'videoId', 'videoUrlIndex', 'videoUrlDesc',
        'videoPlayUrl', 'videoWebUrl', 'videoFormat', 'status' ],
    proxy: {
        type: 'ajax',
        api: {
            create: 'ext/add/videoUrl',
            read: 'ext/load/videoUrl',
            update: 'ext/update/videoUrl',
            destroy: 'ext/remove/videoUrl'
        },
        extraParams: {
            videoId: -1
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