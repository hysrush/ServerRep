<html>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
$(document).ready(function(){
   $("#okBtn").click(function(){
      alert("Hi");
    var param1 = $("#param1").val();
    var param2 = $("#param2").val();
  //    alert(param1);
   //   alert(param2);
      
      	$.ajax({
    	  url: "postSubmit", //url
    	  type: "post", //get, post 방식 
    	  data: {"param1" : $("#param1").val(),
    		  "param2" : $("#param2").val(),
   			}, //넘길 파라미터 
    	 // dataType: 'json', //or xml or script or html 
    	  async: true, // true:비동기, false:동기
    	 
    	  success: function(data){
    		 	alert('ajax success.')
    		  },
    		  error: function(json){ 
    			  alert('ajax error.'); 
    			  console.log(json);
    		 }
      });
      
   });
});
</script>
<head>
<meta charset="EUC-KR">
<title>postForm</title>
</head>
<body>
   <form action="/postSubmit" method="post">
      <input id="param1" type="text"/><br/>
      <input id="param2" type="text"/><br/>
      <input id="okBtn"   type="button" value="button"/><br/>
   </form>
</body>
</html>