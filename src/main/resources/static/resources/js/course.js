var course = {
    listItems: $('#listItems'),

    ajaxMethod: 'POST',

    sendMail: function(evnt) {
        
        var isValid = true;
        evnt.preventDefault();
        
        if($('#fio_feedback').val() === ""){ 
            $('#fio_feedback').addClass('is-invalid');
            if(document.getElementById('fio_feedback').classList.contains('is-valid')){
                document.getElementById('fio_feedback').classList.remove('is-valid'); 
            }
            isValid = false;
        }else{
            $('#fio_feedback').addClass('is-valid'); 
            if(document.getElementById('fio_feedback').classList.contains('is-invalid')){
                document.getElementById('fio_feedback').classList.remove('is-invalid'); 
            }
        }
        
        if($('#email_feedback').val() === ""){ 
            $('#email_feedback').addClass('is-invalid');
            if(document.getElementById('email_feedback').classList.contains('is-valid')){
                document.getElementById('email_feedback').classList.remove('is-valid'); 
            }
            isValid = false;
        }else{
            $('#email_feedback').addClass('is-valid'); 
            if(document.getElementById('email_feedback').classList.contains('is-invalid')){
                document.getElementById('email_feedback').classList.remove('is-invalid'); 
            }
        }
        
        if($('#number_feedback').val() === ""){ 
            $('#number_feedback').addClass('is-invalid');
            if(document.getElementById('number_feedback').classList.contains('is-valid')){
                document.getElementById('number_feedback').classList.remove('is-valid'); 
            }
            isValid = false;
        }else{
            $('#number_feedback').addClass('is-valid'); 
            if(document.getElementById('number_feedback').classList.contains('is-invalid')){
                document.getElementById('number_feedback').classList.remove('is-invalid'); 
            }
        }
        
//        if($('#select_face_feedback').val() === ""){ 
//            $('#select_face_feedback').addClass('is-invalid');
//            if(document.getElementById('select_face_feedback').classList.contains('is-valid')){
//                document.getElementById('select_face_feedback').classList.remove('is-valid'); 
//            }
//            isValid = false;
//        }else{
//            $('#select_face_feedback').addClass('is-valid'); 
//            if(document.getElementById('select_face_feedback').classList.contains('is-invalid')){
//                document.getElementById('select_face_feedback').classList.remove('is-invalid'); 
//            }
//        }
        
        if(!isValid){
            alert('Заполните необходимые поля!');
            return;
        }
        var formData = new FormData();
       
        formData.append('select_course', $('#select_course').val());
        formData.append('fio', $('#fio_feedback').val());
        formData.append('email', $('#email_feedback').val());
        formData.append('number', $('#number_feedback').val());
        //formData.append('select_face', $('#select_face_feedback').val());
    
        
        $.ajax({
            url: '/courses/ajaxSendFeedback/',
            type: this.ajaxMethod,
            data: formData,
            processData: false,
            contentType: false,
            beforeSend: function(){
                    $('#coursepage_registration_form').append('Подождите, заявка отправляется...');
		    document.getElementById('form_content').style.display = 'none';
                    document.getElementById('fio_feedback').value = "";
                    document.getElementById('email_feedback').value = "";
                    document.getElementById('number_feedback').value = "";
            },
            success: function(result){
                    //document.getElementById('form_content').style.display = 'none';
                   // document.getElementById('fio_feedback').value = "";
                   // document.getElementById('email_feedback').value = "";
                    //document.getElementById('number_feedback').value = "";
                   //document.getElementById('select_face_feedback').value = "";
      		    document.getElementById('coursepage_registration_form').value = "";
                    $('#coursepage_registration_form').append(result); 
            }
        });
    },
    addReviewItem: function(evnt, course_id) {
        
        var isValid = true;
        evnt.preventDefault();
        
        if($('#review_name').val() === ""){ 
            $('#review_name').addClass('is-invalid');
            if(document.getElementById('review_name').classList.contains('is-valid')){
                document.getElementById('review_name').classList.remove('is-valid'); 
            }
            isValid = false;
        }else{
            $('#review_name').addClass('is-valid'); 
            if(document.getElementById('review_name').classList.contains('is-invalid')){
                document.getElementById('review_name').classList.remove('is-invalid'); 
            }
        }
        
        if($('#review_comment').val() === ""){ 
            $('#review_comment').addClass('is-invalid');
            if(document.getElementById('review_comment').classList.contains('is-valid')){
                document.getElementById('review_comment').classList.remove('is-valid'); 
            }
            isValid = false;
        }else{
            $('#review_comment').addClass('is-valid'); 
            if(document.getElementById('review_comment').classList.contains('is-invalid')){
                document.getElementById('review_comment').classList.remove('is-invalid'); 
            }
        }
    
        if(!isValid){
            alert('Заполните необходимые поля!');
            return;
        }
        var Data = new Date();
        var Year = Data.getFullYear();
        var Month = Data.getMonth() + 1
        Month = String(Month);
        
        if(Month.length === 1){
            Month = '0'+Month;
        }

        var Day = Data.getDate();
        var Hour = Data.getHours();
        var Minutes = Data.getMinutes();
        var Seconds = Data.getSeconds();
        
        var time = Year +'-'+Month+'-'+Day+' '+Hour + ':' + Minutes +':'+Seconds;
        
        var formData = new FormData();
        
        formData.append('name', $('#review_name').val());
        formData.append('text', $('#review_comment').val());
        formData.append('time', time);

        formData.append('course_id', course_id);
        
        $.ajax({
            url: '/courses/ajaxAddReview/',
            type: this.ajaxMethod,
            data: formData,
            processData: false,
            contentType: false,
            beforeSend: function(){
                $('#add_comment_item').html('<p class="formSend_alert text-center">Спасибо за Ваш отзыв!</p>');
            },
            success: function(result){
                if(document.getElementById('removableSign')){
                    $('#comments').html(result);
                }else{
                    $('#comments').prepend(result);
                }
                location.hash='comments';
            }
        });
        
    }
};

    