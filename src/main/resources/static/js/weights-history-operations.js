jQuery(function() {


    $(document).on('click', '.edit', function() {
        let weight_id = $(this).attr("edit-id");
        let value = $('p[weight-value-id="' + weight_id + '"]').text();
        let date = $('p[weight-date-id="' + weight_id + '"]').text();

        $('#id').val(weight_id);
        $('#value').val(value);
        $('#date').val(reverseDate(date));
    });

    $(document).on('click', '.delete', function() {

        let delete_id = $(this).attr("delete-id");
        let value = $('p[weight-value-id="' + delete_id + '"]').text();
        let date = $('p[weight-date-id="' + delete_id + '"]').text();

        $('#deleteId').val(delete_id);
        $('#delete-value').val(value);
        $('#delete-date').val(reverseDate(date));
    });


    function reverseDate(date) {
        let dateArray = date.split('-');
        return dateArray[2] + "-" + dateArray[1] + "-" + dateArray[0];
    }

});
