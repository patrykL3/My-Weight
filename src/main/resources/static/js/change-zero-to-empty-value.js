jQuery(function () {

    changeZeroToEmptyValue('#age');
    changeZeroToEmptyValue('#height');
    changeZeroToEmptyValue('#desiredWeight');

        function changeZeroToEmptyValue(id) {
            if($(id).val() === '0' || $(id).val() === '0.0') {
                $(id).val("");
            }
        }
});
