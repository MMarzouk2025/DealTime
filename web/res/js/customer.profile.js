$(document).ready(function () {
    $("#edit_profile_btn").click(function () {
        $(':input[type="text"]').prop('disabled', false);

        $("#save_changes_btn").prop('disabled', false);
        $("#edit_profile_btn").prop('disabled', true);
    });
});

$(document).ready(function () {
    $("#save_changes_btn").click(function () {

        $("#edit_profile_btn").prop('disabled', false);
    });
});