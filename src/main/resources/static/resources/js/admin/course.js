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
    $('#show_course_teachers_btn').html("Показать преподавателей");
    $('html,body').stop().animate({ scrollTop: $('#checkboxList').offset().top - 150 }, 500);
 } else {
     obj.animate({ height: 'show' }, 500);
     $('#show_course_teachers_btn').html("Скрыть преподавателей");
 }
 
};
var course = {
    listStageItems: $('#listStageItems'),
    addStageItem: function(event) {
        event.preventDefault();

        var _this = this;
        $.ajax({
            url: document.URL + '/stages/new',
            type: "POST",
            processData: false,
            contentType: false,
            beforeSend: function(){

            },
            success: function(result){
                if (result) {
                    _this.listStageItems.append(result);
                }
            }
        });      
    },
    onChangeCourseStageTitleorDescription: function(evnt, stageId) {
        evnt.preventDefault();
        var data = {};
        data["title"] = $("#stage-title-" + stageId).val();
        data["description"] = $("#stage-description-" + stageId).val();
        $.ajax({
            url: document.URL + '/stages/' + stageId,
            type: "POST",
            data: data,
            dataType: 'json'
        });     
    },
    deleteStageItem: function(event, stageId) {
        event.preventDefault();
        $.ajax({
            url: document.URL + '/stages/' + stageId,
            type: "DELETE",
            processData: false,
            contentType: false,
            beforeSend: function(){

            },
            success: function(){
                $('.stage-item-' + stageId).remove();           
            }
        });
        
    },
    cropCourseMainImage: function() {
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
    addCourseMainImage: function(event) {   
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
                    document.getElementById('courseMainImage').src = '/img/courses/main_image/' + result;            
                }
            });
          });
    },
    deleteCourseMainImage: function(evnt) {
        evnt.preventDefault();
        evnt.target.style.display = 'none';
        $.ajax({
            url: document.URL + "/image",
            type: "DELETE",
            success: function(){
                document.getElementById('file_upload_block').style.display = "block";
                document.getElementById('courseMainImage').src = "";            
            }
        });
        
    },
    listGalleryItems: $('#listGalleryItems'),
    uploadCourseGalleryImage: function(evnt) {
        evnt.preventDefault();
        
        const file = document.getElementById('galleryImageFile').files[0];
        var formData = new FormData();
        formData.append('file', file);
        var _this = this;
        $.ajax({
            url: document.URL + "/gallery/new",
            type: "POST",
            data: formData,
            processData: false,
            contentType: false,
            success: function(result){
                if (result) {
                    _this.listGalleryItems.append(result);
                }
            }
        });
        
    },
    deleteCourseGalleryImage: function(evnt, image_id) {
        evnt.preventDefault();        
        const formData = new FormData();
        formData.append('image_id', image_id);
        
        $.ajax({
            url: document.URL + "/gallery/" + image_id,
            type: "DELETE",
            success: function(){
                $('#gallery-image-' + image_id).remove();           
            }
        });
        
    },
//    deleteComment: function(comment_id) {
//        const formData = new FormData();
//        formData.append('comment_id', comment_id);
//        
//        $.ajax({
//            url: '/admin/courses/ajaxRemoveCourseComment/',
//            type: this.ajaxMethod,
//            data: formData,
//            processData: false,
//            contentType: false,
//            beforeSend: function(){
//
//            },
//            success: function(){
//                $('.comment-item-' + comment_id).remove();           
//            }
//        });
//        
//    },
    deleteCourse: function(event) {
        event.preventDefault;
        if(!confirm('Вы действительно хотите удалить данный курс и все связанные с ним данные?')) {
            return false;
        }
        $.ajax({
            url: document.URL,
            type: "DELETE",
            beforeSend: function(){

            },
            success: function(){
                window.location = '/admin/courses';           
            },
            error: function (e) {
                console.log(e);
            }
        });
        
    }
}