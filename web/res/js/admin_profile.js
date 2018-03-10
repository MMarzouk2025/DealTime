function edit(isEdited) {
    if (isEdited) {
        $(':input[type="text"]').prop('disabled', true);
        $(':input[type="password"]').prop('disabled', true);
        $(':input[type="date"]').prop('disabled', true);

        $("#save_changes_btn").prop('disabled', true);
        $("#edit_profile_btn").prop('disabled', false);
    } else {
        $(':input[type="text"]').prop('disabled', false);
        $(':input[type="password"]').prop('disabled', false);
        $(':input[type="date"]').prop('disabled', false);

        $("#save_changes_btn").prop('disabled', false);
        $("#edit_profile_btn").prop('disabled', true);
    }
}