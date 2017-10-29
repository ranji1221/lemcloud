(function($){
	$(function(){
		$("#page").pagination({
				items 		: 100,
		        itemOnPage 	: 10,
		        currentPage : 1000,
		        cssStyle 	: '',
		        prevText 	: ' ',
		        nextText 	: ' ',
		        onInit: function () {
		            // fire first page loading
		        },
		        onPageClick: function (page, evt) {
		        	
		        }
				
			})
	})
})(jQuery)
