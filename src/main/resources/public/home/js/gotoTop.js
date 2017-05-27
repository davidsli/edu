 $(function(){
	function gotoTop(min_height){
		$("#gotoTop").on("click",function(){
			$("html,body").animate({scrollTop:0},1000);
		})/*.hover(
			function(){$(this).addClass("hover");},
			function(){$(this).removeClass("hover");}
		)*/;
		min_height ? min_height = min_height : min_height = 600;
		$(window).scroll(function(){
			var top = $(window).scrollTop();

			if(top > min_height){
				$("#gotoTop").fadeIn(100);
			}else{
				$("#gotoTop").fadeOut(200);
			}
		});
	}
	gotoTop();
});
