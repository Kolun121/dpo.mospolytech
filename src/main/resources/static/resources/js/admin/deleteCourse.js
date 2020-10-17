$(function() { 
    $('#deleteCourse').click(function(event) { 
        if(!confirm('Вы действительно хотите удалить данный курс и все связанные с ним данные?')) {
            event.preventDefault();
        }
    });
});