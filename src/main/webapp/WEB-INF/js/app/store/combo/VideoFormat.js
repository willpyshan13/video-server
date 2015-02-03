Ext.define('dy.store.combo.VideoFormat',{
	extend:'Ext.data.ArrayStore',
	fields: ["id", "text"],
    data: [['MP4', "MP4"], ['3GP', "3GP"],['M3U8',"M3U8"]]
});