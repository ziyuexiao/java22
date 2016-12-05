<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Document</title>
	<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" />
</head>
<body>
	<button id="btn">Load xml</button>
	<table class="table">
		<thead>
			<tr>
				<th>ID</th>
				<th>NAME</th>
				<th>ADDRESS</th>
			</tr>
		</thead>
		<tbody>
		
		
		</tbody>
	</table>
	<script>
		(function(){
			document.querySelector("#btn").onclick=function(){
				//创建XMLHttpServlet,获取Ajax引擎
				var xmlhttp = new XMLHttpRequest();
				//2. 指定请求方式(GET|POST)和请求地址
				xmlhttp.open("get","/users.xml");
				//3. 配置回调函数
				xmlhttp.onreadystatechange = function(){
					if(xmlhttp.readyState==4){
						if(xmlhttp.status==200){
							 //获取服务端返回的XML文档，并进行解析
							var xmlDoc = xmlhttp.responseXML;
							var userElements = xmlDoc.getElementsByTagName("user");
							
							var tbody = document.querySelector("tbody");
							for(var i=0;i<userElements.length;i++){
								var userElement = userElements[i];
								var id=userElement.getAttribute("id");
								var name=userElement.getElementsByTagName("name")[0].childNodes[0].nodeValue;
								var address=userElement.getElementsByTagName("address")[0].childNodes[0].nodeValue;
								
								 //创建tr并添加到tbody中
								 var tr = document.createElement("tr");
								 var idTd = document.createElement("td");
		                         var nameTd = document.createElement("td");
		                         var addressTd = document.createElement("td");
		                         
		                         //创建文本元素
		                         var idNode = document.createTextNode(id);
		                         var nameNode = document.createTextNode(name);
		                         var addressNode = document.createTextNode(address);
		                         
		                         idTd.appendChild(idNode);
		                         nameTd.appendChild(nameNode);
		                         addressTd.appendChild(addressNode);
		                         
		                         tr.appendChild(idTd);
		                         tr.appendChild(nameTd);
		                         tr.appendChild(addressTd);
		                         
		                         tbody.appendChild(tr); 
		                         
		                         
		                         
		         	
							}
								
						}else {
	                        alert("服务端异常：" + xmlHttp.status);
	                    }
					}
				}
				 //4. 发出请求
				 xmlhttp.send();
			}
			
		})();
	
	
	</script>
	
	
</body>
</html>