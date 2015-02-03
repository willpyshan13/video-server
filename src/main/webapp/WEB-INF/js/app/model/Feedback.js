Ext.define('dy.model.Feedback', {
    extend: 'Ext.data.Model',
    idProperty: 'feedbackId',
    fields: ['feedbackId', 'feedbackContent','username', 'personId', 'createTime'],
    proxy: {
        type: 'ajax',
        api: {
            create: 'ext/add/feedback',
            read: 'ext/load/feedback',
            update: 'ext/update/feedback',
            destroy: 'ext/remove/feedback'
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