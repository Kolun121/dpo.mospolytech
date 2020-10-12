var signin = {

    ajaxMethod: 'POST',

    sendCourseFeedback: function(evnt) {
        
        var isValid = true;
        evnt.preventDefault();
        
        if($('#select_course').val() === ""){ 
            $('#select_course').addClass('is-invalid');
            if(document.getElementById('select_course').classList.contains('is-valid')){
                document.getElementById('select_course').classList.remove('is-valid'); 
            }
            isValid = false;
        }else{
            $('#select_course').addClass('is-valid'); 
            if(document.getElementById('select_course').classList.contains('is-invalid')){
                document.getElementById('select_course').classList.remove('is-invalid'); 
            }
        }
        
        if($('#fio').val() === ""){ 
            $('#fio').addClass('is-invalid');
            if(document.getElementById('fio').classList.contains('is-valid')){
                document.getElementById('fio').classList.remove('is-valid'); 
            }
            isValid = false;
        }else{
            $('#fio').addClass('is-valid'); 
            if(document.getElementById('fio').classList.contains('is-invalid')){
                document.getElementById('fio').classList.remove('is-invalid'); 
            }
        }
        
        if($('#email').val() === ""){ 
            $('#email').addClass('is-invalid');
            if(document.getElementById('email').classList.contains('is-valid')){
                document.getElementById('email').classList.remove('is-valid'); 
            }
            isValid = false;
        }else{
            $('#email').addClass('is-valid'); 
            if(document.getElementById('email').classList.contains('is-invalid')){
                document.getElementById('email').classList.remove('is-invalid'); 
            }
        }
        
        if($('#number').val() === ""){ 
            $('#number').addClass('is-invalid');
            if(document.getElementById('number').classList.contains('is-valid')){
                document.getElementById('number').classList.remove('is-valid'); 
            }
            isValid = false;
        }else{
            $('#number').addClass('is-valid'); 
            if(document.getElementById('number').classList.contains('is-invalid')){
                document.getElementById('number').classList.remove('is-invalid'); 
            }
        }
        
        if($('#select_face').val() === ""){ 
            $('#select_face').addClass('is-invalid');
            if(document.getElementById('select_face').classList.contains('is-valid')){
                document.getElementById('select_face').classList.remove('is-valid'); 
            }
            isValid = false;
        }else{
            $('#select_face').addClass('is-valid'); 
            if(document.getElementById('select_face').classList.contains('is-invalid')){
                document.getElementById('select_face').classList.remove('is-invalid'); 
            }
        }
        
        if(!isValid){
            alert('Заполните необходимые поля!');
            return;
        }
        var formData = new FormData();
        formData.append('select_course', $('#select_course').val());
        formData.append('number', $('#number').val());
        formData.append('email', $('#email').val());
        formData.append('select_face', $('#select_face').val());
        formData.append('fio', $('#fio').val());
        
        var g_captcha_response = $("#g-recaptcha-response").val();
        formData.append('captcha',g_captcha_response);
        $.ajax({
            url: '/ajaxSendCourseFeedback/',
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
                    $('.signin_r_form').html(result); 
                }
            }
        });
    }
};

    