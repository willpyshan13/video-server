Ext.define('dy.controller.video.VideoWheel', {
    extend: 'Ext.app.Controller',
    views:["video.VideoWheel",'video.VideoEditWindow'],
    models:[],
    stores:["VideoWheel","combo.Status","combo.VideoYear","combo.VideoRegion","combo.VideoLabel"],
    init: function () {
        this.control({
        	
        });
    }
});