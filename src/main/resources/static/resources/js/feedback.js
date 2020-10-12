var feedback = {
    ajaxMethod: 'POST',

    sendMail: function(evnt) {
        console.log("Henlo");
        var isValid = true;
        evnt.preventDefault();
        
        if($('#email_subscription').val() === ""){ 
            $('#email_subscription').addClass('is-invalid');
            if(document.getElementById('email_subscription').classList.contains('is-valid')){
                document.getElementById('email_subscription').classList.remove('is-valid'); 
            }
            isValid = false;
        }else{
            $('#email_subscription').addClass('is-valid'); 
            if(document.getElementById('email_subscription').classList.contains('is-invalid')){
                document.getElementById('email_subscription').classList.remove('is-invalid'); 
            }
        }
        
        if(!isValid){
            alert('Заполните необходимые поля!');
            return;
        }
        var formData = new FormData();
        
        formData.append('email', $('#email_subscription').val());
        
        $.ajax({
            url: '/ajaxSendSubscritionFeedback/',
            type: this.ajaxMethod,
            data: formData,
            processData: false,
            contentType: false,
            beforeSend: function(){

            },
            success: function(){
                evnt.target.style.display = "none";
                alert('Спасибо за подписку!');
            }
        });
    }
    
};
