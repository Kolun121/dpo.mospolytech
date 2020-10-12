var index = {
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
        var formData = new FormData();
        
//        var g_captcha_response = $("#g-recaptcha-response").val();
        formData.append('fio', $('#fio_feedback').val());
        formData.append('email', $('#email_feedback').val());
        formData.append('number', $('#number_feedback').val());
        formData.append('user_msg', $('#question_feedback').val());
//        formData.append('select_face', $('#select_face_feedback').val());
//        formData.append('captcha',g_captcha_response);
        
        $.ajax({
            url: '/ajaxSendFeedback/',
            type: this.ajaxMethod,
            data: formData,
            processData: false,
            contentType: false,
            beforeSend: function(){

            },
            success: function(result){
                if(result === 'required_captcha'){
                    alert('Подтвердите, что вы не робот!');
                }else{
                    document.getElementById('form_content').style.display = 'none';
                    document.getElementById('fio_feedback').value = "";
                    document.getElementById('email_feedback').value = "";
                    document.getElementById('number_feedback').value = "";

                    $('#mainpage_registration_form').append(result); 
                }
            }
        });
    }
};

    