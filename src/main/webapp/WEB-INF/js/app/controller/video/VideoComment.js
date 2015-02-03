Ext.define('dy.controller.video.VideoComment', {
    extend: 'Ext.app.Controller',
    views:["video.VideoComment","video.VideoCommentEditWindow"],
    stores:["Comment","combo.Status"],
    init: function () {
        this.control({
        	
        });
    }
});