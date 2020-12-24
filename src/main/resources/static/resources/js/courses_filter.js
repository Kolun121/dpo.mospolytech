$(document).ready(function(){
//    var page_number = Number(document.getElementById('current_page').value);
    var page_number = 0;


    filter_data();
    function filter_data(){
//        $('.filter_loading').html('<div id="loading" style=""></div>');

        var minimum_price_user = $('#user_minimum_price').val();
        var maximum_price_user = $('#user_maximum_price').val();

        var minimum_price = $('#min_price').val();
        var maximum_price = $('#max_price').val();

        filter = [];

        var course_form = [];
        var course_field = [];
        var course_type = [];
        var course_competency = [];
        var course_study_location = [];
        var course_target_entity = [];

        if ($('#filters').css('display') != "none"){
            course_form = get_filter('course_form');
            course_field = get_filter('course_field');
            course_type = get_filter('course_type');
            course_competency = get_filter('course_competency');
            course_study_location = get_filter('course_study_location');
            course_target_entity = get_filter('course_target_entity');
        }
        else{
            course_form = get_filter('course_form_mobile');
            course_field = get_filter('course_field_mobile');
            course_type = get_filter('course_type_mobile');
            course_competency = get_filter('course_competency_mobile');
            course_study_location = get_filter('course_study_location_mobile');
            course_target_entity = get_filter('course_target_entity_mobile');
        }

        var data = {
            "minimum_price_user": minimum_price_user, 
            "maximum_price_user": maximum_price_user, 
            "course_target_entity": course_target_entity,
            "course_field": course_field, 
            "course_type": course_type, 
            "course_competency": course_competency,
            "course_study_location": course_study_location, 
            "course_form": course_form 
        };

        $.ajax({
        url: '/courses/ajaxCoursesFilter/',
        type: 'POST',
//        data: data,
        data: JSON.stringify(data),
        contentType : 'application/json; charset=utf-8',
        beforeSend: function(){
            console.log(data);
        },
        success: function(data){
//            console.log(data);
            $('.filter_loading').html(data);           
        }
    });
    }

    function get_filter(class_name){
        var filter = [];
        $('.' + class_name + ':checked').each(function(){
            filter.push($(this).val());
        });

        return filter;
    }

    //Строка для параметров
    var str = "";   
    str = getCheckedFilters(); 

    //Строка для параметров цены
    var strPrice = "";
    strPrice = getPriceValues(str);

    //Строка для параметра номера страницы
    var strPage = "";

    $('.common_selector').click(function(){
        str = "";

        str = getCheckedFilters();            
        strPrice = getPriceValues(str);

        history.replaceState(null, null, 'courses' + str + strPrice);

        filter_data();
    });

    var min_price = Number(document.getElementById('min_price').value);
    var max_price = Number(document.getElementById('max_price').value);

    var min_price_user = Number(document.getElementById('user_minimum_price').value);
    var max_price_user = Number(document.getElementById('user_maximum_price').value);

    $('#price_range').slider({
        range:true,
        min:min_price,
        max:max_price,
        values:[min_price_user, max_price_user],
        stop:function(event, ui){
            $('#price_show').html(ui.values[0] + ' руб.' + ' - ' + ui.values[1] + ' руб.' );
            $('#user_minimum_price').val(ui.values[0]);
            $('#user_maximum_price').val(ui.values[1]);

            var price_arr = [ui.values[0], ui.values[1]];

            strPrice = "";
            strPrice = parametersConcat("price", price_arr, str);
            history.replaceState(null, null, 'courses' + str + strPrice);

            filter_data();
        }
    });

    $('#price_range_mobile').slider({
        range:true,
        min:min_price,
        max:max_price,
        values:[min_price_user, max_price_user],
        stop:function(event, ui){
            $('#price_show_mobile').html(ui.values[0] + ' руб.' + ' - ' + ui.values[1] + ' руб.' );
            $('#user_minimum_price').val(ui.values[0]);
            $('#user_maximum_price').val(ui.values[1]);

            var price_arr = [ui.values[0], ui.values[1]];

            strPrice = "";
            strPrice = parametersConcat("price", price_arr, str);
            history.replaceState(null, null, 'courses' + str + strPrice);

            filter_data();
        }
    });

    $(document).on('click', '.pagination_link', function(){
        var page = $(this).attr("id");

        strPage = "";
        if(str === "" && strPrice === ""){
            strPage = "?page=" + page;
        }else{
            strPage = "&page=" + page;
        }

        $('body,html').animate({scrollTop:130}, 800);
        history.replaceState(null, null, 'courses' + str + strPrice + strPage);
        filter_data();
    });

    $(document).on('click', '.checked_filters', function(){
        console.log("123");
        this.classList.forEach(function(item, i, arr){
            if(i == 1 && item != "price"){
                if ($('#filters').css('display') != "none"){
                    $('.' + item).prop('checked', false);
                }
                else{
                    $('.' + item + '_mobile').prop('checked', false);
                }
            }else if(item == "price"){
                $('#user_minimum_price').val(min_price);
                $('#user_maximum_price').val(max_price);

                $('#price_range_mobile').slider({values:[min_price, max_price]});
                $('#price_show_mobile').html(min_price + ' руб.' + ' - ' + max_price + ' руб.' );

                $('#price_range').slider({values:[min_price, max_price]});
                $('#price_show').html(min_price + ' руб.' + ' - ' + max_price + ' руб.' );
            }
        });

        str = "";
        str = getCheckedFilters();   

        strPrice = getPriceValues(str);

        history.replaceState(null, null, 'courses' + str + strPrice);
        this.parentElement.removeChild(this);
        filter_data();
    });

    $('#button_uncheck_all_inputs').click(function() {
        $('input:checked').prop('checked', false);
        $('#price_range').slider({values:[min_price, max_price]});
        $('#price_show').html(min_price + ' руб.' + ' - ' + max_price + ' руб.' );
        $('#user_minimum_price').val(min_price);
        $('#user_maximum_price').val(max_price);

        str = "";
        strPrice = "";
        strPage = "";

        history.replaceState(null, null, 'courses');
        filter_data();
      });

    $('#button_uncheck_all_inputs_mobile').click(function() {
        $('input:checked').prop('checked', false);
        $('#price_range_mobile').slider({values:[min_price, max_price]});
        $('#price_show_mobile').html(min_price + ' руб.' + ' - ' + max_price + ' руб.' );
        $('#user_minimum_price').val(min_price);
        $('#user_maximum_price').val(max_price);

        str = "";
        strPrice = "";
        strPage = "";

        history.replaceState(null, null, 'courses');
        filter_data();
      });

    function parametersConcat(paramName, filterArr, concatStr){
        var strTemp = "";
        if(filterArr.length !== 0){
            strTemp = "?";
            if(concatStr.length !== 0){
                strTemp = "&"
            }
            strTemp += paramName + "=";
            strTemp += filterArr.join("-");
            return strTemp;
        }
        return "";
    }

    function getCheckedFilters(){
        var course_form = [];
        var course_field = [];
        var course_type = [];
        var course_competency = [];
        var course_study_location = [];
        var course_target_entity = [];


        if ($('#filters').css('display') != "none"){
            course_form = get_filter('course_form');
            course_field = get_filter('course_field');
            course_type = get_filter('course_type');
            course_competency = get_filter('course_competency');
            course_study_location = get_filter('course_study_location');
            course_target_entity = get_filter('course_target_entity');
        }
        else{
            course_form = get_filter('course_form_mobile');
            course_field = get_filter('course_field_mobile');
            course_type = get_filter('course_type_mobile');
            course_competency = get_filter('course_competency_mobile');
            course_study_location = get_filter('course_study_location_mobile');
            course_target_entity = get_filter('course_target_entity_mobile');
        }

        var strTemp = "";   

        strTemp += parametersConcat("course_form", course_form, strTemp);
        strTemp += parametersConcat("course_field", course_field, strTemp);
        strTemp += parametersConcat("course_type", course_type, strTemp);
        strTemp += parametersConcat("course_competency", course_competency, strTemp);
        strTemp += parametersConcat("course_study_location", course_study_location, strTemp);
        strTemp += parametersConcat("course_target_entity", course_target_entity, strTemp);

        return strTemp;
    }

    function getPriceValues(str){

        var min_price_user_temp = Number(document.getElementById('user_minimum_price').value);
        var max_price_user_temp = Number(document.getElementById('user_maximum_price').value);

        var price_arr = [];
        if(min_price !== min_price_user_temp || max_price !== max_price_user_temp){
            price_arr = [min_price_user_temp, max_price_user_temp];
        }

        strPrice = "";
        strPrice = parametersConcat("price", price_arr, str);

        return strPrice;
    }
});