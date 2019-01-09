    <div class="container">
    
        <div class="row">

            <div class="col-md-3">
                <%@include file="./shared/sidebar.jsp" %>
            </div>

            <div class="col-md-9">
           
                      <div class="row">
					      <div class="col-md-offset-1 col-md-10 topSearchBar">
					         <form method="GET" action="${contextRoot}">
					             <div class="input-group">
					               <input type="text" id="searchTextBar" class="form-control" placeholder="Search" name="search"> 
					                     
					                           
							               <div class="input-group-btn">
							                  
							                    <button id="searchButton" class="btn btn-default" type="submit">
							                          <i class="glyphicon glyphicon-search"></i> 
							                    </button>
							               </div>
					             </div>
					             
					                       
					         </form>
					       </div>
					          <button class="btn btn-default" id="voiceSearch" onclick="triggerVoiceSearch()">
							                        <img src="${images}/voice.png" class="searchImage"/> 
							                    </button>
                    </div>
        <div class="row">
                <div class="row carousel-holder">

                    <div class="col-md-12">
                        <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
                            <ol class="carousel-indicators">
                                <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                                <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                                <li data-target="#carousel-example-generic" data-slide-to="2"></li>
                            </ol>
                            <div class="carousel-inner">
                                <div class="item active">
                                    <img class="slide-image" src="http://placehold.it/800x300" alt="">
                                </div>
                                <div class="item">
                                    <img class="slide-image" src="http://placehold.it/800x300" alt="">
                                </div>
                                <div class="item">
                                    <img class="slide-image" src="http://placehold.it/800x300" alt="">
                                </div>
                            </div>
                            <a class="left carousel-control" href="#carousel-example-generic" data-slide="prev">
                                <span class="glyphicon glyphicon-chevron-left"></span>
                            </a>
                            <a class="right carousel-control" href="#carousel-example-generic" data-slide="next">
                                <span class="glyphicon glyphicon-chevron-right"></span>
                            </a>
                        </div>
                    </div>

                </div>
          
                <div class="row">
                  <c:forEach items="${mproducts}" var="product">
                    <div class="col-sm-4 col-lg-4 col-md-4">
                                        <a href="${contextRoot}/show/${product.id}/product">                       
                            <img src="${images}/${product.code}.jpg" class="homeTableImg" alt="${product.name}">
                            <div class="caption">
                                <h4 class="pull-right"> &#8377;${product.unitPrice}</h4><br/>
                                <h4>${product.name}
                                </h4>
                                <p> ${product.description}</p>
                            </div>
                            <div class="ratings">
                                <p class="pull-right">15 reviews</p>
                                <p>
                                    <span class="glyphicon glyphicon-star"></span>
                                    <span class="glyphicon glyphicon-star"></span>
                                    <span class="glyphicon glyphicon-star"></span>
                                    <span class="glyphicon glyphicon-star"></span>
                                    <span class="glyphicon glyphicon-star"></span>
                                </p>
                            </div>
                             </a>
                    </div>
                    </c:forEach>
                      <c:if test="${not empty emptyMessage}">
                                 <div class="jumbotron">
			
				            <div class="text-center">
				
					
					<h2>${emptyMessage}</h2>
				     
				</div>
			
			     </div>
                 </c:if>	
    
                  
                  
                </div>

            </div>

        </div>

    </div>
    <!-- /.container -->
