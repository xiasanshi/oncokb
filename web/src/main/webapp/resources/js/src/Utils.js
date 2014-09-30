var Utils = (function() {
    "use strict";

    function search() {
        var searchKeywards = $('#tumor_search input').val().toLowerCase(),
            result = tree.search(searchKeywards),
            resutlLength = result.length,
            infoText = (resutlLength === 0 ? "No" : resutlLength) + " result" + (resutlLength <= 1 ? "" :"s" );

        $("#searchResult").hide();
        $("#searchResult").css('z-index', 1);

        if(searchKeywards.length > 0) {
            $("#searchResult").text(infoText);
            $("#searchResult").css('z-index', 2);
            $("#searchResult").show();
        }
        result = null;
    }

    function backToTop() {
        if (    ($(window).height() + 100) < $(document).height() ||
                ($(window).width() + 50) < $(document).width() ) {
            $('#top-link-block').removeClass('hidden').affix({
                offset: {top:100}
            });
        }else {
             $('#top-link-block').addClass('hidden');
        }
    }
    return {
        search: search,
        backToTop: backToTop
    };
})();