/*<!--右侧导航-->*/
	var backtop = document.getElementById("backtop");
	var topItem = document.getElementById("topItem");
	var cart = document.getElementById("cart");
	var cartItem = document.getElementById("cartItem");
	var person = document.getElementById("person");
	var personItem = document.getElementById("personItem");
	backtop.onmouseover = function() {
		topItem.innerHTML = "返回顶部";
	}
	backtop.onmouseout = function() {
		topItem.innerHTML = "";
	}

	cart.onmouseover = function() {
		cartItem.innerHTML = "购物车"
	}
	cart.onmouseout = function() {
		cartItem.innerHTML = ""
	}

	person.onmouseover = function() {
		personItem.innerHTML = "个人中心"
	}
	person.onmouseout = function() {
		personItem.innerHTML = ""
	}
	
	 function generateInput(type,id,name,value){  
	        var input;  
	        if(document.all){  
	            if(name != null && name != ""){  
	            input = document.createElement('<input name="' + name + '">');//IE代码  
	            }else{  
	                input = document.createElement('<input');//IE代码  
	            }  
	        }else{  
	            input = document.createElement('input'); //其他  
	            if(name != null && name != ""){  
	                input.name = name;  
	            }  
	        }  
	        if(type == null || type == ""){  
	            type = "text"  
	        }else{  
	            input.type = type;  
	        }  
	        if(id != null && id != ""){  
	            input.id = id;  
	        }  
	        if(value == null || value == ""){
	        	input.value = "";
	        }else{
	        	input.value = value;
	        }  
	        
	        //document.body.applyElement(input);  
	        document.body.appendChild(input);  
	    };  
	    
	window.onload = function() {
		var table = document.getElementById("table");
		var tr = document.createElement("tr");
		
		ajax({
			url : "/refuse_sorting/GoodsServlet?method=getAllGoods",
			params : null,
			type : "JSON",
			callback : function(data) {
				var count = data.length;
				
				for (var i = 0; i < data.length; i++) {
					
					var tr = document.createElement("tr");
					for (var j = 0; j < 2; j++) {
						var td = document.createElement("td");
						var h4 = document.createElement("h4");
						var p = document.createElement("p");
						var purchase = document.createElement("button");
						var exchange = document.createElement("button");
						
						if(data[i] != null){	
						td.innerHTML = "<img src='" + data[i].image + "' width='405' height='440'/>";
						h4.innerHTML = data[i].gname;
						p.innerHTML = data[i].detial
						purchase.innerHTML = "立即购买";
						exchange.innerHTML = "积分兑换";
						i++;
						/*  alert("j=" + j);
						alert("i=" + i); */
						td.appendChild(h4);
						td.appendChild(p);
						td.appendChild(purchase);
						td.appendChild(exchange);
						tr.appendChild(td);
						
						
						}
					}
						table.appendChild(tr);
						i--;
						
						
				}
			}
		});
	}