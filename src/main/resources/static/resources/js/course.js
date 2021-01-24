
function toggle(objName) {
 var obj = $(objName);
 
 if (obj.css("display") != "none") {
    obj.animate({ height: 'hide' }, 500);
    $('#plan_btn').html("Показать программу");
    $('html,body').stop().animate({ scrollTop: $('#plan').offset().top - 150 }, 500);
 } else {
     obj.animate({ height: 'show' }, 500);
     $('#plan_btn').html("Скрыть программу");
 }
 
}
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
        
        if(!isValid){

            alert('Заполните необходимые поля!');
            return;
        }
        
        var data = {
            "fullName": $('#fio_feedback').val(), 
            "email": $('#email_feedback').val(), 
            "phone": $('#number_feedback').val(),
            "courseTitle": $('#name_course').text()
        };
    
        console.log($('#name_course').val());
        $.ajax({
            url: '/courses/ajaxSendFeedback/',
            type: 'POST',
            data: JSON.stringify(data),
            contentType : 'application/json; charset=utf-8',
            beforeSend: function(){
                    $('#coursepage_registration_form').append('Подождите, заявка отправляется...');
		    document.getElementById('form_content').style.display = 'none';
                    document.getElementById('fio_feedback').value = "";
                    document.getElementById('email_feedback').value = "";
                    document.getElementById('number_feedback').value = "";
            },
            success: function(result){
      		    document.getElementById('coursepage_registration_form').value = "";
                    $('#coursepage_registration_form').html(result); 
            }
        });
    },
    listReviews: $('#listReviews'),
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
        var _this = this;
        
        var data = {};
        data["name"] = $("#review_name").val();
        data["text"] = $("#review_comment").val();
        $.ajax({
            url: document.URL + '/review/new',
            type: "POST",
            data: data,
            dataType: 'json',
            beforeSend: function(){
                location.reload();
                $('#add_comment_item').html('<p class="formSend_alert text-center">Спасибо за Ваш отзыв!</p>');
            },
            error: function(result) {
                console.log(result);
            },
            success: function(result){
                console.log("QWEQWEQWEQWEQWE");
                console.log(result);
                _this.listReviews.append(result);

                location.hash='comments';
            }
        });    
    }
};

    