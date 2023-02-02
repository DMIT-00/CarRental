$(document).ready(function () {
   $("#submit-user").prop('disabled', true);

    var checkPasswords = function() {
        var password = $("#password").val();
        var confirmPassword = $("#passwordRepeat").val();

        if (password != confirmPassword) {
            $("#submit-user").prop('disabled', true);
            $("#passwordRepeatError").prop('hidden', false);
        }
        else
        {
            $("#submit-user").prop('disabled', false);
            $("#passwordRepeatError").prop('hidden', true);
        }
    }

   $("#password").on('keyup', checkPasswords);
   $("#passwordRepeat").on('keyup', checkPasswords);
});