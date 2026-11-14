function getClasses(){
	var college = $("#college").val();
	var grade = $("#grade").val();
	
	if(college == 0 && grade == 0){
		return;
	}else{
		var postData = "option=13"+"&college="+college+"&grade="+grade;
		
		$.ajax({
			   type: "POST",
			   url: "ClassServlet",
			   data: postData,
			   success: function(msg){
				   var $classes = $.parseJSON(msg);
				   var $clazz   = $("#clazz");
				   $clazz.empty();
				   
				   $("<option>",{
					   value:"0",
					   text:"全部"
				   }).appendTo($clazz);
				   $.each( $classes, function(i, clazz){
					   $("<option>",{
						   value:clazz.id,
						   text:clazz.num
					   }).appendTo($clazz);
					 });
			   }
			});
	}
}