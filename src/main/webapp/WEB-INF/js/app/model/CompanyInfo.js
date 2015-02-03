Ext.define('dy.model.CompanyInfo', {
    extend: 'Ext.data.Model',
    idProperty: 'infoId',
    fields: ['infoId', 'infoTitle', 'infoLogoUrl', 'infoContent','companyWebSiteUrl', 'createTime'],
    proxy: {
        type: 'ajax',
        api: {
            create: 'ext/add/companyinfo',
            read: 'ext/loadone/companyinfo',
            update: 'ext/update/companyinfo',
            destroy: 'ext/remove/companyinfo'
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