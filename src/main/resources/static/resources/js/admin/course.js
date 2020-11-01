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
    onChangeCourseSegment: function(event) {
        var segment = event.target.value;
//        var course_id = $('#formCourseId').val();
//        if(event.target.value === ""){
//            event.target.value = course_id;
//            return;
//        }else if(!isNaN(event.target.value) && event.target.value !== course_id){
//            alert('Можно использовать только ID курса как цифру ' + course_id + ' как URL');
//            event.target.value = course_id;
//            return;
//        }
        
        const formData = new FormData();
        formData.append('urlSegment', segment);

        $.ajax({
            url: document.URL + "/segment",
            type: "GET",
            data: formData,
            processData: false,
            contentType: false,
            beforeSend: function(){

            },
            success: function(result){
                if(result === 'false'){
                    alert('Такой URL уже существуйте. Попробуйте другие варианты.');
                    document.getElementById('segment').value = course_id;
                }else{
                    document.getElementById('segment').value = result;
                }
            }
        });
    },
//    update: function() {
//        var isValid = true;
//        var formData = new FormData();
//
//        formData.append('course_id', $('#formCourseId').val());
//        formData.append('segment', $('#segment').val());
//
//
//        if($('#formTitle').val() === ""){ 
//            $('#formTitle').addClass('is-invalid');
//            if(document.getElementById('formTitle').classList.contains('is-valid')){
//                document.getElementById('formTitle').classList.remove('is-valid'); 
//            }
//            isValid = false;
//        }else{
//            $('#formTitle').addClass('is-valid'); 
//            if(document.getElementById('formTitle').classList.contains('is-invalid')){
//                document.getElementById('formTitle').classList.remove('is-invalid'); 
//            }
//        }
//        
//        if($('#course_price').val() === ""){
//            $('#course_price').addClass('is-invalid');
//            if(document.getElementById('course_price').classList.contains('is-valid')){
//                document.getElementById('course_price').classList.remove('is-valid'); 
//            }
//            isValid = false;
//        }else{
//            $('#course_price').addClass('is-valid'); 
//            if(document.getElementById('course_price').classList.contains('is-invalid')){
//                document.getElementById('course_price').classList.remove('is-invalid'); 
//            }
//        }
//        
//        if($('#course_time').val() === ""){ 
//            $('#course_time').addClass('is-invalid');
//            if(document.getElementById('course_time').classList.contains('is-valid')){
//                document.getElementById('course_time').classList.remove('is-valid'); 
//            }
//            isValid = false;
//        }else{
//            $('#course_time').addClass('is-valid');  
//            if(document.getElementById('course_time').classList.contains('is-invalid')){
//                document.getElementById('course_time').classList.remove('is-invalid'); 
//            }
//        }
//        
//        if($('#course_document').val() === ""){ 
//            $('#course_document').addClass('is-invalid');
//            if(document.getElementById('course_document').classList.contains('is-valid')){
//                document.getElementById('course_document').classList.remove('is-valid'); 
//            }
//            isValid = false;
//        }else{
//            $('#course_document').addClass('is-valid'); 
//            if(document.getElementById('course_document').classList.contains('is-invalid')){
//                document.getElementById('course_document').classList.remove('is-invalid'); 
//            }
//        }
//        
//        if($('#content_goal').val() === ""){ 
//            $('#content_goal').addClass('is-invalid');
//            if(document.getElementById('content_goal').classList.contains('is-valid')){
//                document.getElementById('content_goal').classList.remove('is-valid'); 
//            }
//            isValid = false;
//        }else{
//            $('#content_goal').addClass('is-valid'); 
//            if(document.getElementById('content_goal').classList.contains('is-invalid')){
//                document.getElementById('content_goal').classList.remove('is-invalid'); 
//            }
//        }
//        
//        if($('#content_audience').val() === ""){ 
//            $('#content_audience').addClass('is-invalid');
//            if(document.getElementById('content_audience').classList.contains('is-valid')){
//                document.getElementById('content_audience').classList.remove('is-valid'); 
//            }
//            isValid = false;
//        }else{
//            $('#content_audience').addClass('is-valid'); 
//            if(document.getElementById('content_audience').classList.contains('is-invalid')){
//                document.getElementById('content_audience').classList.remove('is-invalid'); 
//            }
//        }
//        
//        if($('#content_subject').val() === ""){ 
//            $('#content_subject').addClass('is-invalid');
//            if(document.getElementById('content_subject').classList.contains('is-valid')){
//                document.getElementById('content_subject').classList.remove('is-valid'); 
//            }
//            isValid = false;
//        }else{
//            $('#content_subject').addClass('is-valid'); 
//            if(document.getElementById('content_subject').classList.contains('is-invalid')){
//                document.getElementById('content_subject').classList.remove('is-invalid'); 
//            }
//        }
//        
//        if($('#study_field').val() === ""){ 
//            $('#study_field').addClass('is-invalid');
//            if(document.getElementById('study_field').classList.contains('is-valid')){
//                document.getElementById('study_field').classList.remove('is-valid'); 
//            }
//            isValid = false;
//        }else{
//            $('#study_field').addClass('is-valid'); 
//            if(document.getElementById('study_field').classList.contains('is-invalid')){
//                document.getElementById('study_field').classList.remove('is-invalid'); 
//            }
//        }
//        
//        if($('#study_type').val() === ""){ 
//            $('#study_type').addClass('is-invalid');
//            if(document.getElementById('study_type').classList.contains('is-valid')){
//                document.getElementById('study_type').classList.remove('is-valid'); 
//            }
//            isValid = false;
//        }else{
//            $('#study_type').addClass('is-valid'); 
//            if(document.getElementById('study_type').classList.contains('is-invalid')){
//                document.getElementById('study_type').classList.remove('is-invalid'); 
//            }
//        }
//        
//        if($('#study_competency').val() === ""){ 
//            $('#study_competency').addClass('is-invalid');
//            if(document.getElementById('study_competency').classList.contains('is-valid')){
//                document.getElementById('study_competency').classList.remove('is-valid'); 
//            }
//            isValid = false;
//        }else{
//            $('#study_competency').addClass('is-valid'); 
//            if(document.getElementById('study_competency').classList.contains('is-invalid')){
//                document.getElementById('study_competency').classList.remove('is-invalid'); 
//            }
//        }
//        
//        if($('#study_form').val() === ""){ 
//            $('#study_form').addClass('is-invalid');
//            if(document.getElementById('study_form').classList.contains('is-valid')){
//                document.getElementById('study_form').classList.remove('is-valid'); 
//            }
//            isValid = false;
//        }else{
//            $('#study_form').addClass('is-valid'); 
//            if(document.getElementById('study_form').classList.contains('is-invalid')){
//                document.getElementById('study_form').classList.remove('is-invalid'); 
//            }
//        }
//        
//        if($('#study_location').val() === ""){ 
//            $('#study_location').addClass('is-invalid');
//            if(document.getElementById('study_location').classList.contains('is-valid')){
//                document.getElementById('study_location').classList.remove('is-valid'); 
//            }
//            isValid = false;
//        }else{
//            $('#study_location').addClass('is-valid'); 
//            if(document.getElementById('study_location').classList.contains('is-invalid')){
//                document.getElementById('study_location').classList.remove('is-invalid'); 
//            }
//        }
//        
//        if($('#course_form').val() === ""){ 
//            $('#course_form').addClass('is-invalid');
//            if(document.getElementById('course_form').classList.contains('is-valid')){
//                document.getElementById('course_form').classList.remove('is-valid'); 
//            }
//            isValid = false;
//        }else{
//            $('#course_form').addClass('is-valid'); 
//            if(document.getElementById('course_form').classList.contains('is-invalid')){
//                document.getElementById('course_form').classList.remove('is-invalid'); 
//            }
//        }
//        
//        if($('#segment').val() === ""){ 
//            $('#segment').addClass('is-invalid');
//            if(document.getElementById('segment').classList.contains('is-valid')){
//                document.getElementById('segment').classList.remove('is-valid'); 
//            }
//            isValid = false;
//        }else{
//            $('#segment').addClass('is-valid'); 
//            if(document.getElementById('segment').classList.contains('is-invalid')){
//                document.getElementById('segment').classList.remove('is-invalid'); 
//            }
//        }
//        
//        if(!isValid){
//            alert('Заполните необходимые поля!');
//            return;
//        }
//        
//        if($('#teachers').val() !== ""){ formData.append('teachers', $('#teachers').val()); }
//        
//        if(!!$('#date').val()){formData.append('date', $('#date').val());}
//        else{formData.append('date', null);}
//        
//        formData.append('title', $('#formTitle').val());
//        formData.append('course_price', $('#course_price').val());
//        formData.append('course_time', $('#course_time').val());
//        formData.append('course_document', $('#course_document').val());
//        formData.append('content_goal', $('#content_goal').val());
//        formData.append('content_audience', $('#content_audience').val());
//        formData.append('content_subject', $('#content_subject').val());
//        formData.append('study_field', $('#study_field').val());
//        formData.append('study_type', $('#study_type').val());
//        formData.append('study_competency', $('#study_competency').val());
//        formData.append('study_form', $('#study_form').val());
//        formData.append('study_location', $('#study_location').val());
//        formData.append('course_form', $('#course_form').val());
//      
//        formData.append('status', $('#status').val());
//        
//        $.ajax({
//            url: '/admin/courses/update/',
//            type: this.ajaxMethod,
//            data: formData,
//            cache: false,
//            processData: false,
//            contentType: false,
//            beforeSend: function(){
//            },
//            success: function(result){
//                console.log(result);
//                window.location.reload();
//            }
//        });
//    },
//    upload: function(button) {
//     
//        button.preventDefault();
//	var formData = new FormData();
//        var file = document.getElementById('file').files[0];
//	formData.append('file', file);
//        console.log(formData);
//
//        $.ajax({
//            url: '/admin/courses/upload/',
//            type: this.ajaxMethod,
//            data: formData,
//            cache: false,
//            processData: false,
//            contentType: false,
//            beforeSend: function(){
//
//            },
//            success: function(result){
//                console.log(result);
//            }
//        });
//    },
//    deleteCourseMainImage: function(evnt) {
//        evnt.preventDefault();
//        
//        evnt.target.style.display = 'none';
//        
//        const formData = new FormData();
//        formData.append('course_id', course_id);
//        $.ajax({
//            url: '/admin/courses/ajaxCourseRemoveMainImage/',
//            type: this.ajaxMethod,
//            data: formData,
//            processData: false,
//            contentType: false,
//            beforeSend: function(){
//
//            },
//            success: function(){
//                document.getElementById('main_image').src = "";            
//            }
//        });
//        
//    },
//    listItems: $('#listItems'),
//    addGalleryItem: function(evnt, course_id) {
//        evnt.preventDefault();
//        var formData = new FormData();
//        console.log(course_id);
//        formData.append('tableNameId', course_id);
//        formData.append('tableName', 'courses');
//        formData.append('imagePurpose', 'gallery_image');
//        
//        
//        var _this = this;
//        $.ajax({
//            url: '/admin/courses/ajaxGalleryImageAddItem/',
//            type: this.ajaxMethod,
//            data: formData,
//            processData: false,
//            contentType: false,
//            beforeSend: function(){
//
//            },
//            success: function(result){
//                if (result) {
//                    _this.listItems.append(result);
//                }
//            }
//        });
//        
//    },
//    deleteCourseGalleryImage: function(evnt, image_id) {
//        evnt.preventDefault();
//                
//        const formData = new FormData();
//        formData.append('image_id', image_id);
//        
//        console.log(image_id);
//        $.ajax({
//            url: '/admin/courses/ajaxCourseRemoveGalleryImage/',
//            type: this.ajaxMethod,
//            data: formData,
//            processData: false,
//            contentType: false,
//            beforeSend: function(){
//
//            },
//            success: function(){
//                $('.gallery-item-' + image_id).remove();           
//            }
//        });
//        
//    },
//    uploadCourseGalleryImage: function(event, image_id) {
//        const files = event.target.files;
//        const file = files[0];
//
//        const formData = new FormData();
//        formData.append('image_id', image_id);
//        formData.append('course_image', file);
//
//        $.ajax({
//            url: '/admin/courses/ajaxUploadCourseGalleryImage/',
//            type: this.ajaxMethod,
//            data: formData,
//            processData: false,
//            contentType: false,
//            beforeSend: function(){
//
//            },
//            success: function(result){
//                document.getElementById('gallery-image-' + image_id).src = result; 
//            }
//        });
//        
//    },
//    listStageItems: $('#listStageItems'),
//    addStageItem: function(course_id) {
//     
//        var formData = new FormData();
//        console.log(course_id);
//        formData.append('course_id', course_id);
//        
//        var _this = this;
//        $.ajax({
//            url: '/admin/courses/ajaxAddCourseStageItem/',
//            type: this.ajaxMethod,
//            data: formData,
//            processData: false,
//            contentType: false,
//            beforeSend: function(){
//
//            },
//            success: function(result){
//                if (result) {
//                    _this.listStageItems.append(result);
//                }
//            }
//        });
//        
//    },
//    onChangeCourseStageTitle: function(evnt, stage_id) {
//     
//        var formData = new FormData();
//        formData.append('stage_id', stage_id);
//        formData.append('stage_title', evnt.target.value);
//        
//        var _this = this;
//        $.ajax({
//            url: '/admin/courses/ajaxChangeCourseStageTitle/',
//            type: this.ajaxMethod,
//            data: formData,
//            processData: false,
//            contentType: false,
//            beforeSend: function(){
//
//            },
//            success: function(result){
//                console.log(result);
//            }
//        });
//        
//    },
//    onChangeCourseStageDescription: function(evnt, stage_id) {
//     
//        var formData = new FormData();
//        formData.append('stage_id', stage_id);
//        formData.append('stage_description', evnt.target.value);
//        
//        var _this = this;
//        $.ajax({
//            url: '/admin/courses/ajaxChangeCourseStageDescription/',
//            type: this.ajaxMethod,
//            data: formData,
//            processData: false,
//            contentType: false,
//            beforeSend: function(){
//
//            },
//            success: function(result){
//                console.log(result);
//            }
//        });
//        
//    },
//    deleteStageItem: function(stage_id) {
//        const formData = new FormData();
//        formData.append('stage_id', stage_id);
//        
//        $.ajax({
//            url: '/admin/courses/ajaxRemoveCourseStageItem/',
//            type: this.ajaxMethod,
//            data: formData,
//            processData: false,
//            contentType: false,
//            beforeSend: function(){
//
//            },
//            success: function(){
//                $('.stage-item-' + stage_id).remove();           
//            }
//        });
//        
//    },
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
                window.location = '/admin/courses/';           
            },
            error: function (e) {
                console.log(e);
            }
        });
        
    }
}