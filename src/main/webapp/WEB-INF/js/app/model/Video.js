Ext.define('dy.model.Video', {
    extend: 'Ext.data.Model',
    idProperty: 'videoId',
    fields: [
        {name: 'videoId'},
        'videoTitle',
        'videoWheelPicUrl',
        'videoPreviewPicUrl',
        'videoBrief',
        'videoDesc',
        'videoDirector',
        'videoScriptwriter',
        'videoActor',
        {
            name: 'videoRegion'
        },
        {name: 'videoYear'},
        'status',
        'createTime',
        {name: 'hot', type: 'boolean', persist: false},
        {name: 'wheel', type: 'boolean', persist: false},
        {name: 'labels', type: 'auto'}
    ],
    proxy: {
        type: 'ajax',
        api: {
            create: 'ext/add/video',
            read: 'ext/load/video',
            update: 'ext/update/video',
            destroy: 'ext/remove/video'
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