var userFeedBack = {
    deleteUserFeedBackItem: function(event, id) {
        event.preventDefault;
        if(!confirm('Вы действительно хотите удалить данную заявку?')) {
            return false;
        }
        $.ajax({
            url: document.URL + '/' + id,
            type: "DELETE",
            beforeSend: function(){

            },
            success: function(){
                window.location = '/admin/userFeedBack';           
            },
            error: function (e) {
                console.log(e);
            }
        });
        
    }
}