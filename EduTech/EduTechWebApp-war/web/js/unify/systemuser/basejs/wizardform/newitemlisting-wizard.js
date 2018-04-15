searchVisible = 0;
transparent = true;

$(document).ready(function () {
    $('[rel="tooltip"]').tooltip();
    $('.wizard-card').bootstrapWizard({
        'tabClass': 'nav nav-pills',
        'nextSelector': '.btn-next',
        'previousSelector': '.btn-previous',
        onInit: function (tab, navigation, index) {
            var $total = navigation.find('li').length;
            $width = 100 / $total;
            $display_width = $(document).width();

            if ($display_width < 600 && $total > 3) {
                $width = 50;
            }
            navigation.find('li').css('width', $width + '%');

        },
        onNext: function(tab, navigation, index){
            if(index == 1){
                return validateFirstStep();
            } else if(index == 2){
                return validateSecondStep();
            }
        },
        onTabClick: function (tab, navigation, index) {
            return false;
        },
        onTabShow: function (tab, navigation, index) {
            var $total = navigation.find('li').length;
            var $current = index + 1;

            var wizard = navigation.closest('.wizard-card');
            if ($current >= $total) {
                $(wizard).find('.btn-next').hide();
                $(wizard).find('.btn-finish').show();
            } else {
                $(wizard).find('.btn-next').show();
                $(wizard).find('.btn-finish').hide();
            }
        }
    });

    $("#wizard-picture").change(function () {
        readURL(this);
    });

    $('[data-toggle="wizard-radio"]').click(function () {
        wizard = $(this).closest('.wizard-card');
        wizard.find('[data-toggle="wizard-radio"]').removeClass('active');
        $(this).addClass('active');
        $(wizard).find('[type="radio"]').removeAttr('checked');
        $(this).find('[type="radio"]').attr('checked', 'true');
    });

    $('[data-toggle="wizard-checkbox"]').click(function () {
        if ($(this).hasClass('active')) {
            $(this).removeClass('active');
            $(this).find('[type="checkbox"]').removeAttr('checked');
        } else {
            $(this).addClass('active');
            $(this).find('[type="checkbox"]').attr('checked', 'true');
        }
    });
    
    $('.wizard-card .finish').click(function() {
        $(".wizard-card form").validate({
            rules: {
                tradeLocation: "required",
                tradeInformation: "required"
            },
            messages: {
                tradeLocation: "Please select the Trade Location",
                tradeInformation: "Please enter the Trade Information"
            }
        });

        if (!$(".wizard-card form").valid()) {
            console.log('invalid');
            return false;
        }
        $('#newItemListingForm').submit();
    });
    
    $height = $(document).height();
    $('.set-full-height').css('height', $height);
});

function validateFirstStep() {
    $(".wizard-card form").validate({
        rules: {
            itemImage: "required",
            itemName: "required",
            itemCondition: "required",
            itemPrice: { required: true, range: [0, 9999] },
            itemDescription: "required"
            
            /*  other possible input validations
             ,username: {
             required: true,
             minlength: 2
             },
             password: {
             required: true,
             minlength: 5
             },
             confirm_password: {
             required: true,
             minlength: 5,
             equalTo: "#password"
             },
             
             topic: {
             required: "#newsletter:checked",
             minlength: 2
             },
             agree: "required"
             */

        },
        messages: {
            itemImage: "Please select an Item Image",
            itemName: "Please enter the Item Name",
            itemCondition: "Please enter the Item Condition",
            itemPrice: {
                required: "Please enter the Item Price",
                range: "Item Price must be between 0 to 9999 (Without the $)"
            },
            itemDescription: "Please enter the Item Description"
            
            /*   other posible validation messages
             username: {
             required: "Please enter a username",
             minlength: "Your username must consist of at least 2 characters"
             },
             password: {
             required: "Please provide a password",
             minlength: "Your password must be at least 5 characters long"
             },
             confirm_password: {
             required: "Please provide a password",
             minlength: "Your password must be at least 5 characters long",
             equalTo: "Please enter the same password as above"
             },
             email: "Please enter a valid email address",
             agree: "Please accept our policy",
             topic: "Please select at least 2 topics"
             */

        }
    });

    if (!$(".wizard-card form").valid()) {
        return false;
    }
    return true;
}

function validateSecondStep() {
    var checkedValue = $('#checkedValue').val();
    if (checkedValue != 'Checked'){
        alert("Please select an Item Category.");
        console.log('invalid');
        return false;
    }
    return true;
}

function readURL(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        
        reader.onload = function (e) {
            $('#wizardPicturePreview').attr('src', e.target.result).fadeIn('slow');
        };
        reader.readAsDataURL(input.files[0]);
    }
}