<div id="page-wrapper" ng-class="{'open': toggle}" ng-cloak>
	<!-- Sidebar -->
	<%@include file="./sidebar.jsp" %>
	<!-- End Sidebar -->

	<div class="row header">
		<div class="col-xs-12">
			<div class="meta" style="margin-bottom:20px;">
				<div class="page">Dashboard</div>
				<div class="breadcrumb-links">Home</div>
			</div>
		</div>
	
	</div>
	<div class="row">
		<div class="col-lg-6 col-md-6 col-xs-6">
			<h1>Contact Information</h1>
			<div class="col-lg-12 col-md-12 col-xs-12">
				<div class="col-lg-3 col-md-3 col-xs-12">
					Username:
				</div>
				<div class="col-lg-9 col-md-9 col-xs-12">
					{{overview.username}}
				</div>
				
				<div class="col-lg-3 col-md-3 col-xs-12">
					First name:
				</div>
				<div class="col-lg-9 col-md-9 col-xs-12">
					{{overview.firstName}}
				</div>
				
				<div class="col-lg-3 col-md-3 col-xs-12">
					Last name:
				</div>
				<div class="col-lg-9 col-md-9 col-xs-12">
					{{overview.lastName}}
				</div>
				
				<div class="col-lg-3 col-md-3 col-xs-12">
					Address:
				</div>
				<div class="col-lg-9 col-md-9 col-xs-12">
				 	{{overview.address.address}}, {{overview.address.zip}} {{overview.address.city}}. {{overview.address.country}}
				</div>
				
				<div class="col-lg-3 col-md-3 col-xs-12">
					Phone number:
				</div>
				<div class="col-lg-9 col-md-9 col-xs-12">
					{{overview.phone}}
				</div>
				
				<div class="col-lg-3 col-md-3 col-xs-12">
					Preferred currency:
				</div>
				<div class="col-lg-9 col-md-9 col-xs-12">
				 	{{overview.currency.name}}
				</div>
				
			</div>
			
		</div>
		</div>
		
		
		

	</div>
</div>
<!-- End Page Content -->
