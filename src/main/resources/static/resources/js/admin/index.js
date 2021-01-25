var index = {
    ajaxMethod: 'POST',
    listIndexItems: $('#listIndexItems'),
    addIndexItem: function() {
        var _this = this;
        $.ajax({
            url: document.URL + "/index/new",
            type: "POST",
            data: "",
            processData: false,
            contentType: false,
            beforeSend: function(){

            },
            success: function(result){
                if (result) {
                    _this.listIndexItems.append(result);
                }
            }
        });
        
    },
    deleteIndexItem: function(event, item_id) {
        event.preventDefault;
        if(!confirm('Вы действительно хотите удалить данный блок?')) {
            return false;
        }


        $.ajax({
            url: document.URL + '/index/' + item_id,
            type: "DELETE",
            beforeSend: function(){
            },
            success: function(){
                $('.index-item-' + item_id).remove(); 
            },
            error: function (e) {
                console.log(e);
            }
        });
        
    },
    onChangeIndexItemUrlOrText: function(evnt, index_item_id) {
        evnt.preventDefault();
        var data = {};
        data["url"] = $("#index-item-url-" + index_item_id).val();
        data["text"] = $("#index-item-text-" + index_item_id).val();
        $.ajax({
            url: document.URL + '/index/' + index_item_id,
            type: "POST",
            data: data,
            dataType: 'json'
        });     
    },
    uploadIndexItemImage: function(event, index_item_id) {
        const files = event.target.files;
      
        const file = files[0];

        const formData = new FormData();

        formData.append('file', file);

        $.ajax({
            url: document.URL + "/index/" + index_item_id + "/image/new",
            type: 'POST',
            data: formData,
            processData: false,
            contentType: false,
            beforeSend: function(){

            },
            success: function(result){
                console.log(result);
                document.getElementById('index-item-image-' + index_item_id).src = result;            
            }
        });
        
    },
    deleteIndexItemImage: function(evnt, index_item_id) {
        evnt.preventDefault();
        var id = index_item_id;
        evnt.target.style.display = 'none';
        
        $.ajax({
            url: document.URL + "/index/" + index_item_id +"/image",
            type: "DELETE",
            success: function(){
                document.getElementById('indexImage').style.display = "block";
                document.getElementById('index-item-image-' + id).src = "";            
            }
        });
        
    }
};
