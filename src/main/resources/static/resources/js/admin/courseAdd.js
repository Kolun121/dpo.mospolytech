var course = {
    createCourse: function(evnt) {
        evnt.preventDefault();
        $.ajax({
            url: document.URL + '/new',
            type: "POST",
            success: function(result){
                console.log(result);
                window.location = '/admin/courses/' + result;
            }
        });
    }
}