$image_crop = $('#image_demo').croppie({
    enableExif: true,
    viewport: {
      width:500,
      height:500,
      type:'square' //circle
    },
    boundary:{
      width:550,
      height:550
    }
});

function toggle(objName) {
 var obj = $(objName);
 
 if (obj.css("display") != "none") {
    obj.animate({ height: 'hide' }, 500);
    $('#show_teacher_courses_btn').html("Показать программу");
    $('html,body').stop().animate({ scrollTop: $('#teacherCourses').offset().top - 150 }, 500);
 } else {
     obj.animate({ height: 'show' }, 500);
     $('#show_teacher_courses_btn').html("Скрыть курсы");
 }
 
};

var teacher = {
    cropTeacherMainImage: function() {
        const files = document.getElementById('files').files;
        
        var reader = new FileReader();
        reader.onload = function (event) {
        $image_crop.croppie('bind', {
            url: event.target.result
          }).then(function(){
            console.log('jQuery bind complete');
          });
        }

        reader.readAsDataURL(files[0]);
        $('#uploadimageModal').modal('show');
    },
    addTeacherMainImage: function(event) {   
        event.preventDefault();
        $image_crop.croppie('result', {
            type: 'blob',
            size: 'viewport'
          }).then(function(response){
            const formData = new FormData();
            formData.append('file', response);
            
            $.ajax({
                url: document.URL + "/image/new",
                type: 'POST',
                data: formData,
                processData: false,
                contentType: false,
                beforeSend: function(){

                },
                success: function(result){
                    $('#uploadimageModal').modal('hide');
                    document.getElementById('btn_del_photo').style.display = "block";
                    document.getElementById('file_upload_block').style.display = "none";
                    document.getElementById('teacherMainImage').src = result;            
                }
            });
          });
    },
    deleteTeacherMainImage: function(evnt) {
        evnt.preventDefault();
        evnt.target.style.display = 'none';
        $.ajax({
            url: document.URL + "/image",
            type: "DELETE",
            success: function(){
                document.getElementById('file_upload_block').style.display = "block";
                document.getElementById('teacherMainImage').src = "";            
            }
        });
        
    },
    deleteTeacher: function(event) {
        event.preventDefault;
        if(!confirm('Вы действительно хотите удалить данного преподавателя и все связанные с ним данные?')) {
            return false;
        }
        $.ajax({
            url: document.URL,
            type: "DELETE",
            beforeSend: function(){

            },
            success: function(){
                window.location = '/admin/teachers';           
            },
            error: function (e) {
                console.log(e);
            }
        });
        
    }
}